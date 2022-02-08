/**
 * XseedRecoveryTools.java
 * Utility procedures for report recovery
 * Copyright 2010 - Xseed Software ltda
 *
 **/
 
package HLegacy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.*;
import java.sql.*;
import java.io.*;

/**
 * XseedRecoveryTools
 * @author Xseed Software
 **/

public class XseedRecoveryTools<T> {

	private Connection connection;
	private String jobId;
	private String reportId;
	private double queueId;
	private double recoveryId;
	private double integrityPointId;
	private String status;
	private T reportInstance;
	private boolean processing;
	
	public String getStatus() {
		return status;
	}	

	public void setStatus(String status) {
		this.status = status;
	}
	public double getIntegrityPointId() {
		return integrityPointId;
	}

	public void setIntegrityPointId(double integrityPointId) {
		this.integrityPointId = integrityPointId;
	}
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
	    if (jobId.trim().equals("")) {
		    jobId = "  ";
		}
		this.jobId = jobId;
	}

	public double getQueueId() {
		return queueId;
	}

	public void setQueueId(double queueId) {
		this.queueId = queueId;
	}

	public double getRecoveryId() {
		return recoveryId;
	}

	public void setRecoveryId(double recoveryId) {
		this.recoveryId = recoveryId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public T getReportInstance() {
		return reportInstance;
	}

	public void setReportInstance(T reportInstance) {
		this.reportInstance = reportInstance;
	}

	public Connection getConnection() {
		return connection;
	}
	
	public boolean isProcessing() {
		return processing;
	}

	public void setProcessing(boolean processing) {
		this.processing = processing;
	}
		
	/**
	 * getRecoveryStatus - Get recovery status for this report upon start.<br>
	 * This method uses the following parameter properties<br>
	 * getJobId() – Current Job Id<br>	 
	 * getReportId() – Current Report Id<br>
	 * getQueueId() – Current Queue Id<br>
	 * On start the report can have one of the following recovery status:<br>
	 * E – There were errors on the last running<br>
	 * P – There is another instance of this report currently processing<br>
	 * R – There is another instance of this report currently recovering<br>
     * C – The last report running has sucessfully completed<br>
	 * Empty string - The recovery control record has not been created yet for this job, report and queue id.<br>
	 * @return The current recovery status.
	 **/ 
	public String getRecoveryStatus() throws Exception  {

		PreparedStatement stmt = null;
		ResultSet result = null;
		String status = "";
		String pID = "";

		prepareRecoveryArea();
		
		stmt = connection.prepareStatement(
				" select STATUS, RECOVERY_ID, LAST_INTGR_POINT_ID, PID from XSEEDRECOVERYAREA " +
				" where JOB_ID = ? " +
				"   and REPORT = ? " +
				"   and QUEUE_ID = ? " +
				"   and RECOVERY_ID <= 9999999999 " +
				"   order by  RECOVERY_ID desc "
		);
		stmt.setString(1, getJobId());
		stmt.setString(2, getReportId());
		stmt.setDouble(3, getQueueId());

		setRecoveryId(0);

		result = stmt.executeQuery();
		if(result.next()){
			status = result.getString(1);
			setRecoveryId(result.getDouble(2));
			setIntegrityPointId(result.getDouble(3));
			pID = result.getString(4);
		}
		stmt.close();
		result.close();		
		stmt = null;
		result = null;

		// Treats if the report is really processing or recovering (Status P or R).
		// If XSEEDRECOVERYAREA indicates P (processing) or R (recovering) but the
		// process is not running (invalid PID saved in XSEEDRECOVERYAREA) the 
		// last running may be killed or crashed. In this case the STATUS will be 
		// marked as A (ABORTED) in XSEEDRECOVERYAREA.
		if (status.equals("P") || 
		    status.equals("R")) {
			
			// Check if the process is active at the operating system
			if (isProccessActive(pID) == false) {				
				// Process is not active anymore, mark process as aborted
				status = "A";
				// Update XSEEDRECOVERYAREA with A(ABORTED) 
				setStatus("A");
				updateRecoveryStatus();
			}
		}
		return status;
	}

	
	
	/**
	 * setConnection - Set the current connection<br>
	 * @param pConnection Current connection 
	 **/
	public void setConnection(Connection pConnection) {
		this.connection = pConnection;
	}

	/**
	 * saveIntegrityPoint - Save report state upon integrity point<br>
	 * This method uses the following parameter properties<br>
	 * getJobId() – Current Job Id<br>
	 * getReportId() – Current Report Id<br>
	 * getQueueId() – Current Queue Id<br>
	 * getRecoverId() -  Recovery Id for this report <br>
	 * getIntegrityPointId() – Integrity point sequence<br>
	 * getReportInstance() – Current report instance<br>
	 * @return true indicates success.
	 **/ 
	public boolean saveIntegrityPoint() throws Exception {
		
		PreparedStatement stmt = connection.prepareStatement(
				" update XSEEDRECOVERYAREA set " +
				"  LAST_INTGR_POINT_DATE = " + String.format("%1$tY%1$tm%1$td", Calendar.getInstance()) +
				" ,LAST_INTGR_POINT_ID = ?  " +
				" ,LAST_INTGR_POINT_TIME = " + String.format("%1$tH%1$tM%1$tS00", Calendar.getInstance()) +
				" ,RECOVERY_AREA = ? " +
				" where JOB_ID = ? " +
				"   and REPORT = ? " +
				"   and QUEUE_ID = ? " +
				"   and RECOVERY_ID = ? "
		);
		int success=0;

		// Save report instance                                                                                                                                                                               
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();		
		ObjectOutputStream out = new ObjectOutputStream(buffer);
		out.writeObject(getReportInstance());
		out.flush();
		out.close();
		byte [] byteArray = buffer.toByteArray();

		// Parameters 
		stmt.setDouble(1,getIntegrityPointId());
		stmt.setBinaryStream(2,new ByteArrayInputStream(byteArray), byteArray.length );
		stmt.setString(3,getJobId());
		stmt.setString(4,getReportId());
		stmt.setDouble(5,getQueueId());
		stmt.setDouble(6,getRecoveryId());

		success = stmt.executeUpdate();

		stmt.close();
		stmt = null;

		// Commit transaction on integrity point
		connection.commit();
	
		return (success > 0 ? true : false );
	} 

    /**
	 * updateRecoveryStatus - Update recovery status for this report,save current PID<br>
	 * This method uses the following parameter properties<br>
	 * getJobId() – Current Job Id<br>	 
	 * getReportId() – Current Report Id<br>
	 * getQueueId() – Current Queue Id<br>
	 * getRecoverId() -  Recovery Id for this report <br>
	 * getStatus() -  The new recovery status for this report <br>
	 * This method clean the recovery status for this report putting it on <br>
	 * P status (Processing). It also increases the last recovery id sequence<br>
	 * @return true indicates success.
	 **/ 
	public boolean updateRecoveryStatus() throws Exception {

		PreparedStatement pStm = null;
		int sucess = 0;
		
		// Update XSEEDRECOVERYAREA
		if (getStatus().equals("C")) {
			pStm = connection.prepareStatement("update XSEEDRECOVERYAREA " +
					" set STATUS = ? " +
					"    ,PID = ? " +  				
					"    ,FINISH_DATE = " +  String.format("%1$tY%1$tm%1$td", Calendar.getInstance())  +
					"    ,FINISH_TIME = " +  String.format("%1$tH%1$tM%1$tS00", Calendar.getInstance()) +
					" where JOB_ID = ? " +
					"   and REPORT = ? " +
					"   and QUEUE_ID = ? " +
					"   and RECOVERY_ID = ? " 
			);	
		} else if (getStatus().equals("R")) {
			pStm = connection.prepareStatement("update XSEEDRECOVERYAREA " +
					" set STATUS = ? " +
					"    ,PID = ? " +  							
					"    ,RECOVERY_DATE = " +  String.format("%1$tY%1$tm%1$td", Calendar.getInstance())  +
					"    ,RECOVERY_TIME = " +  String.format("%1$tH%1$tM%1$tS00", Calendar.getInstance()) +
					"    ,FINISH_DATE = 0 " +  
					"    ,FINISH_TIME = 0 " +  
					" where JOB_ID = ? " +
					"   and REPORT = ? " +
					"   and QUEUE_ID = ? " +
					"   and RECOVERY_ID = ? " 
			);	
		} else if (getStatus().equals("E")) {
			pStm = connection.prepareStatement("update XSEEDRECOVERYAREA " +
					" set STATUS = ? " +
					"    ,PID = ? " +  							
					"    ,ERROR_DATE = " +  String.format("%1$tY%1$tm%1$td", Calendar.getInstance())  +
					"    ,ERROR_TIME = " +  String.format("%1$tH%1$tM%1$tS00", Calendar.getInstance()) +
					"    ,FINISH_DATE = 0 " +  
					"    ,FINISH_TIME = 0 " +  
					" where JOB_ID = ? " +
					"   and REPORT = ? " +
					"   and QUEUE_ID = ? " +
					"   and RECOVERY_ID = ? " 
			);	
		} else if (getStatus().equals("P")) {
			pStm = connection.prepareStatement("update XSEEDRECOVERYAREA " +
					" set STATUS = ? " +
					"    ,PID = ? " +  							
					"    ,START_DATE = " +  String.format("%1$tY%1$tm%1$td", Calendar.getInstance())  +
					"    ,START_TIME = " +  String.format("%1$tH%1$tM%1$tS00", Calendar.getInstance()) +
					"    ,FINISH_DATE = 0 " +  
					"    ,FINISH_TIME = 0 " +  
					" where JOB_ID = ? " +
					"   and REPORT = ? " +
					"   and QUEUE_ID = ? " +
					"   and RECOVERY_ID = ? " 
			);	
		} else {
			pStm = connection.prepareStatement("update XSEEDRECOVERYAREA " +
					" set STATUS = ? " +
					"    ,PID = ? " +  							
					" where JOB_ID = ? " +
					"   and REPORT = ? " +
					"   and QUEUE_ID = ? " +
					"   and RECOVERY_ID = ? " 
			);	
		}  
		
		pStm.setString(1,getStatus());
		pStm.setString(2,getPID());
		pStm.setString(3,getJobId());
		pStm.setString(4,getReportId());
		pStm.setDouble(5,getQueueId());
		pStm.setDouble(6,getRecoveryId());
		sucess = pStm.executeUpdate();
		pStm.close();
		pStm = null;

		return (sucess > 0 ? true:false);
	}


	
    /**
	 * recoverIntegrityPoint - Recover report instance from the last saved integrity point<br>
	 * This method uses the following parameter properties<br>
	 * getJobId() – Current Job Id<br>	 
	 * getReportId() – Current Report Id<br>
	 * getQueueId() – Current Queue Id<br>
	 * getRecoverId() -  Recovery Id for this report <br>
	 * This method recovers report items from the last saved integrity point<br>
	 * After this method you should call getReportInstance() to refer to the last saved<br>
	 * report instance and getIntegrityPointId() to obtain the last saved integrity point. 
	 * @return true indicates success.
	 **/ 
	@SuppressWarnings("unchecked")
	public boolean recoverIntegrityPoint() throws Exception {

		PreparedStatement stmt = null;
		ResultSet result = null;
		boolean returnMethod = false;

		stmt = connection.prepareStatement(
				" select RECOVERY_AREA, LAST_INTGR_POINT_ID  from XSEEDRECOVERYAREA " +
				" where JOB_ID = ? " +
				"   and REPORT = ? " +
				"   and QUEUE_ID = ? " +
				"   and RECOVERY_ID = ? " +				
				"   order by  RECOVERY_ID desc" 
		);
		
		stmt.setString(1, getJobId());
		stmt.setString(2, getReportId());
		stmt.setDouble(3, getQueueId());
		stmt.setDouble(4, getRecoveryId());

		result = stmt.executeQuery();
 
        setIntegrityPointId(0);

		if(result.next()){
			//obtencao dos dados binarios gravados na tabela
			InputStream inputStream = result.getBlob(1).getBinaryStream();			
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

			setReportInstance((T)objectInputStream.readObject());
			objectInputStream.close();
			inputStream.close();
			setIntegrityPointId(result.getDouble(2));

			returnMethod = true;
		}

		stmt.close();
		result.close();		
		stmt = null;
		result = null;

		return returnMethod;
	}

    /**
	 * initializeRecoveryStatus - Initialize recovery status for this report<br>
	 * This method uses the following parameter properties<br>
	 * getJobId() – Current Job Id<br>	 
	 * getReportId() – Current Report Id<br>
	 * getQueueId() – Current Queue Id<br>
	 * getRecoverId() -  Recovery Id for this report <br>
	 * This method clean the recovery status for this report putting it on <br>
	 * P status (Processing). It also increases the last recovery id sequence<br>
	 * @return true indicates success.
	 **/ 
	public boolean initializeRecoveryStatus() throws Exception{

		PreparedStatement stmt = null;
		ResultSet resultset = null;
		int success = 0;

		// get last recover id
		stmt = connection.prepareStatement(	" select RECOVERY_ID from XSEEDRECOVERYAREA " +
				" where JOB_ID = ? " +
				"   and REPORT = ? " +
				"   and QUEUE_ID = ? " + 
				"   and RECOVERY_ID <= 9999999999 " +
				"   order by  RECOVERY_ID desc for update" 
				);		
				
		stmt.setString(1,getJobId());
		stmt.setString(2,getReportId());
		stmt.setDouble(3,getQueueId());		
		resultset = stmt.executeQuery();
		
		setRecoveryId(0);
		if(resultset.next()){
			setRecoveryId(resultset.getDouble(1));
		}
		stmt.close();
		stmt  = null;
		stmt = connection.prepareStatement(
				" insert into XSEEDRECOVERYAREA ( " +
				"   ERROR_DATE "  +
				"  ,ERROR_TIME "  +
				"  ,FINISH_DATE "  +
				"  ,FINISH_TIME "  +
				"  ,JOB_ID "  +
				"  ,LAST_INTGR_POINT_DATE " + 
				"  ,LAST_INTGR_POINT_ID "  +
				"  ,LAST_INTGR_POINT_TIME "  +
				"  ,PARALLEL_MODE "  +
				"  ,PID "  +
				"  ,QUEUE_ID "  +
				"  ,RECOVERY_ID " +
				"  ,RECOVERY_DATE " +
				"  ,RECOVERY_TIME " +				
				"  ,REPORT "  +
				"  ,START_DATE " + 
				"  ,START_TIME "  +
				"  ,STATUS "  +
				"  ,RECOVERY_AREA ) values ( " +
				"   0 "  +
				"  ,0 "  +
				"  ,0 "  +
				"  ,0 "  +
				"  ,? "  +
				"  ,0 " + 
				"  ,0 "  +
				"  ,0 "  +
				"  ,' '  "  +
				"  ,? "  +    
				"  ,? "  +    
				"  ,? "  +
				"  ,0 "  +
				"  ,0 "  +    
				"  ,? "  +
				"  , " + String.format("%1$tY%1$tm%1$td", Calendar.getInstance()) + 
				"  , " + String.format("%1$tH%1$tM%1$tS00", Calendar.getInstance()) +
				"  , 'P' " +
				"  , empty_blob() ) " 
		);

		// Parameters
		stmt.setString(1,getJobId());
		stmt.setString(2,getPID());
		stmt.setDouble(3,getQueueId());
		stmt.setDouble(4,getRecoveryId() + 1);
		stmt.setString(5,getReportId());

		success = stmt.executeUpdate();
		
		// Increase recovery Id and set P status
		if (success > 0) {
		   setRecoveryId(getRecoveryId() + 1);
		   setStatus("P");
		}
		return (success > 0 ? true : false );
	}

    /**
	 * recoverShadowReports - Recover shadow reports for this report<br>
	 * This method uses the following parameter properties<br>
	 * getReportInstance() – Current Report Id<br>
	 * This method recover shadow report files state according to the GLB items<br>
	 * @return true indicates success.
	 **/ 
	public boolean recoverShadowReports() throws Exception{
	    XseedGLBRecovery GLB = null;
        Map<Integer,String> wShadowReports = new HashMap<Integer,String>();
		Map<Integer,Double> wWrittenRecords = new HashMap<Integer,Double>();
	    
		// Validations
		if (getReportInstance()==null) {
		    return false;
		}
                // Access recovered GLB items
		GLB = ((XseedReportRecovery)getReportInstance()).GLB;
		  
		// Shadow report table 
		wShadowReports.put(0,GLB.LP_XSEEDTITLE);
		wShadowReports.put(1,GLB.LPA_XSEEDTITLE);
		wShadowReports.put(2,GLB.LPB_XSEEDTITLE);
		wShadowReports.put(3,GLB.LPC_XSEEDTITLE);
		wShadowReports.put(4,GLB.LPD_XSEEDTITLE);
		wShadowReports.put(5,GLB.LPE_XSEEDTITLE);
		wShadowReports.put(6,GLB.LPF_XSEEDTITLE);
		wShadowReports.put(7,GLB.LPG_XSEEDTITLE);
		wShadowReports.put(8,GLB.LPH_XSEEDTITLE);
		wShadowReports.put(9,GLB.LPI_XSEEDTITLE);
		wShadowReports.put(10,GLB.LPJ_XSEEDTITLE);
		wShadowReports.put(11,GLB.LPK_XSEEDTITLE);
		wShadowReports.put(12,GLB.LPL_XSEEDTITLE);
		wShadowReports.put(13,GLB.LPM_XSEEDTITLE);
		wShadowReports.put(14,GLB.LPN_XSEEDTITLE);
		wShadowReports.put(15,GLB.LPO_XSEEDTITLE);
		wShadowReports.put(16,GLB.LPP_XSEEDTITLE);
		wShadowReports.put(17,GLB.LPQ_XSEEDTITLE);
		wShadowReports.put(18,GLB.LPR_XSEEDTITLE);
		wShadowReports.put(19,GLB.LPS_XSEEDTITLE);
		wShadowReports.put(20,GLB.LPT_XSEEDTITLE);
		wShadowReports.put(21,GLB.LPU_XSEEDTITLE);
		wShadowReports.put(22,GLB.LPV_XSEEDTITLE);
		wShadowReports.put(23,GLB.LPW_XSEEDTITLE);
		wShadowReports.put(24,GLB.LPX_XSEEDTITLE);
		wShadowReports.put(25,GLB.LPY_XSEEDTITLE);
		wShadowReports.put(26,GLB.LPZ_XSEEDTITLE);
		
		// Written records tabel
		wWrittenRecords.put(0,GLB.PRINT_XSEEDWRITES);
		wWrittenRecords.put(1,GLB.PRINTA_XSEEDWRITES);
		wWrittenRecords.put(2,GLB.PRINTB_XSEEDWRITES);
		wWrittenRecords.put(3,GLB.PRINTC_XSEEDWRITES);
		wWrittenRecords.put(4,GLB.PRINTD_XSEEDWRITES);
		wWrittenRecords.put(5,GLB.PRINTE_XSEEDWRITES);
		wWrittenRecords.put(6,GLB.PRINTF_XSEEDWRITES);
		wWrittenRecords.put(7,GLB.PRINTG_XSEEDWRITES);
		wWrittenRecords.put(8,GLB.PRINTH_XSEEDWRITES);
		wWrittenRecords.put(9,GLB.PRINTI_XSEEDWRITES);
		wWrittenRecords.put(10,GLB.PRINTJ_XSEEDWRITES);
		wWrittenRecords.put(11,GLB.PRINTK_XSEEDWRITES);
		wWrittenRecords.put(12,GLB.PRINTL_XSEEDWRITES);
		wWrittenRecords.put(13,GLB.PRINTM_XSEEDWRITES);
		wWrittenRecords.put(14,GLB.PRINTN_XSEEDWRITES);
		wWrittenRecords.put(15,GLB.PRINTO_XSEEDWRITES);
		wWrittenRecords.put(16,GLB.PRINTP_XSEEDWRITES);
		wWrittenRecords.put(17,GLB.PRINTQ_XSEEDWRITES);
		wWrittenRecords.put(18,GLB.PRINTR_XSEEDWRITES);
		wWrittenRecords.put(19,GLB.PRINTS_XSEEDWRITES);
		wWrittenRecords.put(20,GLB.PRINTT_XSEEDWRITES);
		wWrittenRecords.put(21,GLB.PRINTU_XSEEDWRITES);
		wWrittenRecords.put(22,GLB.PRINTV_XSEEDWRITES);
		wWrittenRecords.put(23,GLB.PRINTW_XSEEDWRITES);
		wWrittenRecords.put(24,GLB.PRINTX_XSEEDWRITES);
		wWrittenRecords.put(25,GLB.PRINTY_XSEEDWRITES);
		wWrittenRecords.put(26,GLB.PRINTZ_XSEEDWRITES);
		
		// Recover shadow reports
		for (int shadow = 0; shadow <= 26; shadow++) { 
		
			   System.out.println(shadow+" wShadowReports.get(shadow) :" +wShadowReports.get(shadow) );
			if (wShadowReports.get(shadow) != null) {
                                if (wWrittenRecords.get(shadow) != null &&
                                    wWrittenRecords.get(shadow) != 0.0) {
                            
		                    System.out.println("Recovering shadow:" + wShadowReports.get(shadow));
                       		    System.out.println("  Records generated until last integrity point:" + wWrittenRecords.get(shadow));

				    recoverFile(wShadowReports.get(shadow),wWrittenRecords.get(shadow));
				}	
			} 
		}
		
		// Initialize shadow reports status. This will force the report
		// to reopen files for append at the next PRINT.FRAME/BEGIN.PAGE command
		// from the last point saved and from the GLB.LP<shadow>_XSEEDTITLE file name.
		GLB.INITREPORT = false;
		GLB.INITREPORTA = false;
		GLB.INITREPORTB = false;
		GLB.INITREPORTC = false;
		GLB.INITREPORTD = false;
		GLB.INITREPORTE = false;
		GLB.INITREPORTF = false;
		GLB.INITREPORTG = false;
		GLB.INITREPORTH = false;
		GLB.INITREPORTI = false;
		GLB.INITREPORTJ = false;
		GLB.INITREPORTK = false;
		GLB.INITREPORTL = false;
		GLB.INITREPORTM = false;
		GLB.INITREPORTN = false;
		GLB.INITREPORTO = false;
		GLB.INITREPORTP = false;
		GLB.INITREPORTQ = false;
		GLB.INITREPORTR = false;
		GLB.INITREPORTS = false;
		GLB.INITREPORTT = false;
		GLB.INITREPORTU = false;
		GLB.INITREPORTV = false;
		GLB.INITREPORTW = false;
		GLB.INITREPORTX = false;
		GLB.INITREPORTY = false;
		GLB.INITREPORTZ = false;
		
		return true;
	}
	
	/**
	 * recoverExtractFiles - Recover extract files for this report<br>
	 * This method uses the following parameter properties<br>
	 * getReportInstance() – Current Report Id<br>
	 * This method recover extract files state according to the GLB items<br>
	 * @return true indicates success.
	 **/ 
	public boolean recoverExtractFiles() throws Exception{
	    XseedGLBRecovery GLB = null;
        Map<Integer,String> wExtractFiles = new HashMap<Integer,String>();
		Map<Integer,Double> wWrittenRecords = new HashMap<Integer,Double>();
	
		// Validations  
		if (getReportInstance()==null) {
		    return false;
		}
        // Access recovered GLB items
		GLB = ((XseedReportRecovery)getReportInstance()).GLB;
			  
		// Shadow report table 
		wExtractFiles.put(1,GLB.EXA_XSEEDTITLE);
		wExtractFiles.put(2,GLB.EXB_XSEEDTITLE);
		wExtractFiles.put(3,GLB.EXC_XSEEDTITLE);
		wExtractFiles.put(4,GLB.EXD_XSEEDTITLE);
		wExtractFiles.put(5,GLB.EXE_XSEEDTITLE);
		wExtractFiles.put(6,GLB.EXF_XSEEDTITLE);
		wExtractFiles.put(7,GLB.EXG_XSEEDTITLE);
		wExtractFiles.put(8,GLB.EXH_XSEEDTITLE);
		wExtractFiles.put(9,GLB.EXI_XSEEDTITLE);
		wExtractFiles.put(10,GLB.EXJ_XSEEDTITLE);
		wExtractFiles.put(11,GLB.EXK_XSEEDTITLE);
		wExtractFiles.put(12,GLB.EXL_XSEEDTITLE);
		wExtractFiles.put(13,GLB.EXM_XSEEDTITLE);
		wExtractFiles.put(14,GLB.EXN_XSEEDTITLE);
		wExtractFiles.put(15,GLB.EXO_XSEEDTITLE);
		wExtractFiles.put(16,GLB.EXP_XSEEDTITLE);
		wExtractFiles.put(17,GLB.EXQ_XSEEDTITLE);
		wExtractFiles.put(18,GLB.EXR_XSEEDTITLE);
		wExtractFiles.put(19,GLB.EXS_XSEEDTITLE);
		wExtractFiles.put(20,GLB.EXT_XSEEDTITLE);
		wExtractFiles.put(21,GLB.EXU_XSEEDTITLE);
		wExtractFiles.put(22,GLB.EXV_XSEEDTITLE);
		wExtractFiles.put(23,GLB.EXW_XSEEDTITLE);
		wExtractFiles.put(24,GLB.EXX_XSEEDTITLE);
		wExtractFiles.put(25,GLB.EXY_XSEEDTITLE);
		wExtractFiles.put(26,GLB.EXZ_XSEEDTITLE);
		
		// Written records tables
		wWrittenRecords.put(1,GLB.EXA_XSEEDWRITES);
		wWrittenRecords.put(2,GLB.EXB_XSEEDWRITES);
		wWrittenRecords.put(3,GLB.EXC_XSEEDWRITES);
		wWrittenRecords.put(4,GLB.EXD_XSEEDWRITES);
		wWrittenRecords.put(5,GLB.EXE_XSEEDWRITES);
		wWrittenRecords.put(6,GLB.EXF_XSEEDWRITES);
		wWrittenRecords.put(7,GLB.EXG_XSEEDWRITES);
		wWrittenRecords.put(8,GLB.EXH_XSEEDWRITES);
		wWrittenRecords.put(9,GLB.EXI_XSEEDWRITES);
		wWrittenRecords.put(10,GLB.EXJ_XSEEDWRITES);
		wWrittenRecords.put(11,GLB.EXK_XSEEDWRITES);
		wWrittenRecords.put(12,GLB.EXL_XSEEDWRITES);
		wWrittenRecords.put(13,GLB.EXM_XSEEDWRITES);
		wWrittenRecords.put(14,GLB.EXN_XSEEDWRITES);
		wWrittenRecords.put(15,GLB.EXO_XSEEDWRITES);
		wWrittenRecords.put(16,GLB.EXP_XSEEDWRITES);
		wWrittenRecords.put(17,GLB.EXQ_XSEEDWRITES);
		wWrittenRecords.put(18,GLB.EXR_XSEEDWRITES);
		wWrittenRecords.put(19,GLB.EXS_XSEEDWRITES);
		wWrittenRecords.put(20,GLB.EXT_XSEEDWRITES);
		wWrittenRecords.put(21,GLB.EXU_XSEEDWRITES);
		wWrittenRecords.put(22,GLB.EXV_XSEEDWRITES);
		wWrittenRecords.put(23,GLB.EXW_XSEEDWRITES);
		wWrittenRecords.put(24,GLB.EXX_XSEEDWRITES);
		wWrittenRecords.put(25,GLB.EXY_XSEEDWRITES);
		wWrittenRecords.put(26,GLB.EXZ_XSEEDWRITES);
			
		// Recover extract files
		for (int extract = 1; extract <= 26; extract++) { 
		
    		if (wExtractFiles.get(extract) != null) {
                    if (wWrittenRecords.get(extract) != null &&
                        wWrittenRecords.get(extract) != 0.0) {
                            
		                    System.out.println("Recovering extract file:" + wExtractFiles.get(extract));
                       	    System.out.println("  Records generated until last integrity point:" + wWrittenRecords.get(extract));

				    recoverFile(wExtractFiles.get(extract),wWrittenRecords.get(extract));
				}	
			} 
		}
		
		// Initialize extract files status. This will force the report
		// to reopen extract files for read skiping the number of read records in 
		// DT; ACTUAL commands and reopen extract files for append in case of EXTRACT commands.		
		
		if (GLB.EXA_STATUS.equals("INPUT")) {
            GLB.EXA_STATUS = "";
			GLB.RECORDA = 0.0;
			if (GLB.EXA_XSEEDREADS > 0.0) {
			   GLB.RECORDA = GLB.EXA_XSEEDREADS - 1;
		    }
		} else if (GLB.EXA_STATUS.equals("OUTPUT") &&
		           GLB.EXA_XSEEDWRITES != 0.0) {
            GLB.EXA_STATUS = "RECOVER-EXTRACT";
		}	
	
		if (GLB.EXB_STATUS.equals("INPUT")) {
            GLB.EXB_STATUS = "";
			GLB.RECORDB = 0.0;
			if (GLB.EXB_XSEEDREADS > 0.0) {
			   GLB.RECORDB = GLB.EXB_XSEEDREADS - 1;
		    }
		} else if (GLB.EXB_STATUS.equals("OUTPUT") &&
		           GLB.EXB_XSEEDWRITES != 0.0) {
            GLB.EXB_STATUS = "RECOVER-EXTRACT";
		}	
		
		if (GLB.EXC_STATUS.equals("INPUT")) {
            GLB.EXC_STATUS = "";
			GLB.RECORDC = 0.0;
			if (GLB.EXC_XSEEDREADS > 0.0) {
			   GLB.RECORDC = GLB.EXC_XSEEDREADS - 1;
		    }
		} else if (GLB.EXC_STATUS.equals("OUTPUT") &&
		           GLB.EXC_XSEEDWRITES != 0.0) {
            GLB.EXC_STATUS = "RECOVER-EXTRACT";
		}	
		
		if (GLB.EXD_STATUS.equals("INPUT")) {
            GLB.EXD_STATUS = "";
			GLB.RECORDD = 0.0;
			if (GLB.EXD_XSEEDREADS > 0.0) {
			   GLB.RECORDD = GLB.EXD_XSEEDREADS - 1;
		    }
		} else if (GLB.EXD_STATUS.equals("OUTPUT") &&
		           GLB.EXD_XSEEDWRITES != 0.0) {
            GLB.EXD_STATUS = "RECOVER-EXTRACT";
		}	
		
		if (GLB.EXE_STATUS.equals("INPUT")) {
            GLB.EXE_STATUS = "";
			GLB.RECORDE = 0.0;
			if (GLB.EXE_XSEEDREADS > 0.0) {
			   GLB.RECORDE = GLB.EXE_XSEEDREADS - 1;
		    }
		} else if (GLB.EXE_STATUS.equals("OUTPUT") &&
		           GLB.EXE_XSEEDWRITES != 0.0) {
            GLB.EXE_STATUS = "RECOVER-EXTRACT";
		}	
		
		if (GLB.EXF_STATUS.equals("INPUT")) {
            GLB.EXF_STATUS = "";
			GLB.RECORDF = 0.0;
			if (GLB.EXF_XSEEDREADS > 0.0) {
			   GLB.RECORDF = GLB.EXF_XSEEDREADS - 1;
		    }
		} else if (GLB.EXF_STATUS.equals("OUTPUT") &&
		           GLB.EXF_XSEEDWRITES != 0.0) {
            GLB.EXF_STATUS = "RECOVER-EXTRACT";
		}	
		
		if (GLB.EXG_STATUS.equals("INPUT")) {
            GLB.EXG_STATUS = "";
			GLB.RECORDG = 0.0;
			if (GLB.EXG_XSEEDREADS > 0.0) {
			   GLB.RECORDG = GLB.EXG_XSEEDREADS - 1;
		    }
		} else if (GLB.EXG_STATUS.equals("OUTPUT") &&
		           GLB.EXG_XSEEDWRITES != 0.0) {
            GLB.EXG_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXH_STATUS.equals("INPUT")) {
            GLB.EXH_STATUS = "";
			GLB.RECORDH = 0.0;
			if (GLB.EXH_XSEEDREADS > 0.0) {
			   GLB.RECORDH = GLB.EXH_XSEEDREADS - 1;
		    }
		} else if (GLB.EXH_STATUS.equals("OUTPUT") &&
		           GLB.EXH_XSEEDWRITES != 0.0) {
            GLB.EXH_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXI_STATUS.equals("INPUT")) {
            GLB.EXI_STATUS = "";
			GLB.RECORDI = 0.0;
			if (GLB.EXI_XSEEDREADS > 0.0) {
			   GLB.RECORDI = GLB.EXI_XSEEDREADS - 1;
		    }
		} else if (GLB.EXI_STATUS.equals("OUTPUT") &&
		           GLB.EXI_XSEEDWRITES != 0.0) {
            GLB.EXI_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXJ_STATUS.equals("INPUT")) {
            GLB.EXJ_STATUS = "";
			GLB.RECORDJ = 0.0;
			if (GLB.EXJ_XSEEDREADS > 0.0) {
			   GLB.RECORDJ = GLB.EXJ_XSEEDREADS - 1;
		    }
		} else if (GLB.EXJ_STATUS.equals("OUTPUT") &&
		           GLB.EXJ_XSEEDWRITES != 0.0) {
            GLB.EXJ_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXK_STATUS.equals("INPUT")) {
            GLB.EXK_STATUS = "";
			GLB.RECORDK = 0.0;
			if (GLB.EXK_XSEEDREADS > 0.0) {
			   GLB.RECORDK = GLB.EXK_XSEEDREADS - 1;
		    }
		} else if (GLB.EXK_STATUS.equals("OUTPUT") &&
		           GLB.EXK_XSEEDWRITES != 0.0) {
            GLB.EXK_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXL_STATUS.equals("INPUT")) {
            GLB.EXL_STATUS = "";
			GLB.RECORDL = 0.0;
			if (GLB.EXL_XSEEDREADS > 0.0) {
			   GLB.RECORDL = GLB.EXL_XSEEDREADS - 1;
		    }
		} else if (GLB.EXL_STATUS.equals("OUTPUT") &&
		           GLB.EXL_XSEEDWRITES != 0.0) {
            GLB.EXL_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXM_STATUS.equals("INPUT")) {
            GLB.EXM_STATUS = "";
			GLB.RECORDM = 0.0;
			if (GLB.EXM_XSEEDREADS > 0.0) {
			   GLB.RECORDM = GLB.EXM_XSEEDREADS - 1;
		    }
		} else if (GLB.EXM_STATUS.equals("OUTPUT") &&
		           GLB.EXM_XSEEDWRITES != 0.0) {
            GLB.EXM_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXN_STATUS.equals("INPUT")) {
            GLB.EXN_STATUS = "";
			GLB.RECORDN = 0.0;
			if (GLB.EXN_XSEEDREADS > 0.0) {
			   GLB.RECORDN = GLB.EXN_XSEEDREADS - 1;
		    }
		} else if (GLB.EXN_STATUS.equals("OUTPUT") &&
		           GLB.EXN_XSEEDWRITES != 0.0) {
            GLB.EXN_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXO_STATUS.equals("INPUT")) {
            GLB.EXO_STATUS = "";
			GLB.RECORDO = 0.0;
			if (GLB.EXO_XSEEDREADS > 0.0) {
			   GLB.RECORDO = GLB.EXO_XSEEDREADS - 1;
		    }
		} else if (GLB.EXO_STATUS.equals("OUTPUT") &&
		           GLB.EXO_XSEEDWRITES != 0.0) {
            GLB.EXO_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXP_STATUS.equals("INPUT")) {
            GLB.EXP_STATUS = "";
			GLB.RECORDP = 0.0;
			if (GLB.EXP_XSEEDREADS > 0.0) {
			   GLB.RECORDP = GLB.EXP_XSEEDREADS - 1;
		    }
		} else if (GLB.EXP_STATUS.equals("OUTPUT") &&
		           GLB.EXP_XSEEDWRITES != 0.0) {
            GLB.EXP_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXQ_STATUS.equals("INPUT")) {
            GLB.EXQ_STATUS = "";
			GLB.RECORDQ = 0.0;
			if (GLB.EXQ_XSEEDREADS > 0.0) {
			   GLB.RECORDQ = GLB.EXQ_XSEEDREADS - 1;
		    }
		} else if (GLB.EXQ_STATUS.equals("OUTPUT") &&
		           GLB.EXQ_XSEEDWRITES != 0.0) {
            GLB.EXQ_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXR_STATUS.equals("INPUT")) {
            GLB.EXR_STATUS = "";
			GLB.RECORDR = 0.0;
			if (GLB.EXR_XSEEDREADS > 0.0) {
			   GLB.RECORDR = GLB.EXR_XSEEDREADS - 1;
		    }
		} else if (GLB.EXR_STATUS.equals("OUTPUT") &&
		           GLB.EXR_XSEEDWRITES != 0.0) {
            GLB.EXR_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXS_STATUS.equals("INPUT")) {
            GLB.EXS_STATUS = "";
			GLB.RECORDS = 0.0;
			if (GLB.EXS_XSEEDREADS > 0.0) {
			   GLB.RECORDS = GLB.EXS_XSEEDREADS - 1;
		    }
		} else if (GLB.EXS_STATUS.equals("OUTPUT") &&
		           GLB.EXS_XSEEDWRITES != 0.0) {
            GLB.EXS_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXT_STATUS.equals("INPUT")) {
            GLB.EXT_STATUS = "";
			GLB.RECORDT = 0.0;
			if (GLB.EXT_XSEEDREADS > 0.0) {
			   GLB.RECORDT = GLB.EXT_XSEEDREADS - 1;
		    }
		} else if (GLB.EXT_STATUS.equals("OUTPUT") &&
		           GLB.EXT_XSEEDWRITES != 0.0) {
            GLB.EXT_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXU_STATUS.equals("INPUT")) {
            GLB.EXU_STATUS = "";
			GLB.RECORDU = 0.0;
			if (GLB.EXU_XSEEDREADS > 0.0) {
			   GLB.RECORDU = GLB.EXU_XSEEDREADS - 1;
		    }
		} else if (GLB.EXU_STATUS.equals("OUTPUT") &&
		           GLB.EXU_XSEEDWRITES != 0.0) {
            GLB.EXU_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXV_STATUS.equals("INPUT")) {
            GLB.EXV_STATUS = "";
			GLB.RECORDV = 0.0;
			if (GLB.EXV_XSEEDREADS > 0.0) {
			   GLB.RECORDV = GLB.EXV_XSEEDREADS - 1;
		    }
		} else if (GLB.EXV_STATUS.equals("OUTPUT") &&
		           GLB.EXV_XSEEDWRITES != 0.0) {
            GLB.EXV_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXW_STATUS.equals("INPUT")) {
            GLB.EXW_STATUS = "";
			GLB.RECORDW = 0.0;
			if (GLB.EXW_XSEEDREADS > 0.0) {
			   GLB.RECORDW = GLB.EXW_XSEEDREADS - 1;
		    }
		} else if (GLB.EXW_STATUS.equals("OUTPUT") &&
		           GLB.EXW_XSEEDWRITES != 0.0) {
            GLB.EXW_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXX_STATUS.equals("INPUT")) {
            GLB.EXX_STATUS = "";
			GLB.RECORDX = 0.0;
			if (GLB.EXX_XSEEDREADS > 0.0) {
			   GLB.RECORDX = GLB.EXX_XSEEDREADS - 1;
		    }
		} else if (GLB.EXX_STATUS.equals("OUTPUT") &&
		           GLB.EXX_XSEEDWRITES != 0.0) {
            GLB.EXX_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXY_STATUS.equals("INPUT")) {
            GLB.EXY_STATUS = "";
			GLB.RECORDY = 0.0;
			if (GLB.EXY_XSEEDREADS > 0.0) {
			   GLB.RECORDY = GLB.EXY_XSEEDREADS - 1;
		    }
		} else if (GLB.EXY_STATUS.equals("OUTPUT") &&
		           GLB.EXY_XSEEDWRITES != 0.0) {
            GLB.EXY_STATUS = "RECOVER-EXTRACT";
		}			
		
		if (GLB.EXZ_STATUS.equals("INPUT")) {
            GLB.EXZ_STATUS = "";
			GLB.RECORDZ = 0.0;
			if (GLB.EXZ_XSEEDREADS > 0.0) {
			   GLB.RECORDZ = GLB.EXZ_XSEEDREADS - 1;
		    }
		} else if (GLB.EXZ_STATUS.equals("OUTPUT") &&
		           GLB.EXZ_XSEEDWRITES != 0.0) {
            GLB.EXZ_STATUS = "RECOVER-EXTRACT";
		}			

		return true;
	}
	
	
	/**
	 * recoverFile - Recover file (shadow and extract) for this report<br>
	 * This method restore the file with the exact number of<br>
	 * written records at the moment of the last integrity point<br>
	 **/ 
	public void  recoverFile(String pFileName, double pWrittenRecords) throws Exception {	
        String wTempFileName;						
        String wLine;
        double wLineCont = 0.0;
        XseedFileRecovery wTempFile;
        XseedFileRecovery wRecoverFile;     
        int wLastSeparator= pFileName.lastIndexOf( File.separatorChar);   
        
		if (wLastSeparator!=-1){
			wTempFileName= pFileName.substring(0,wLastSeparator+1) + "Recover_" + pFileName.substring(wLastSeparator+1) ;
		} else {
			wTempFileName= pFileName;
		}
        
        if (pWrittenRecords == 0.0) {
            return;
        }            
        if ( (pFileName.equals("") == true)  || (XseedFunctions.FileExists(pFileName) == false)) {            
            return;
        }        
        // Copy source file to recover file
        if (XseedFunctions.FileCopy(pFileName, wTempFileName)==false) {
            return;
        }        
        if ( XseedFunctions.FileExists(wTempFileName) == false) {            
            return;
        }  		
		// Restore file from temp file with exact number of written records
        wTempFile = new XseedFileRecovery();
        wTempFile.open(wTempFileName, "INPUT");
        wRecoverFile = new XseedFileRecovery();
        wRecoverFile.open(pFileName, "OUTPUT");
        wLine = wTempFile.read();
        wLineCont = 0.0;		
		while (wLine!=null) {
           wRecoverFile.write(wLine);           
           wLineCont++;
           if (wLineCont==pWrittenRecords) {
                break;
           }         
           wLine = wTempFile.read();            
        }                  
        wTempFile.close();
        wRecoverFile.close();          
		wTempFile = null;
        wRecoverFile = null;          
    }
    

	/**
	 * prepareRecoveryArea - Prepare recovery area .<br>
	 * @return true indicates success.
	 **/ 
	public void prepareRecoveryArea() throws Exception  {

		PreparedStatement stmt = null;
		ResultSet result = null;
		String status = "";
        boolean wExists = false;
		
		// Check if recovery area already defined. If it does not exist create it
		stmt = connection.prepareStatement(
				" select TABLE_NAME from USER_TABLES " +
				" where TABLE_NAME = 'XSEEDRECOVERYAREA' "
		);
		result = stmt.executeQuery();
		wExists = false;
		if(result.next()){
			wExists = true;
		}
		stmt.close();
		result.close();		
		stmt = null;
		result = null;

		// Check if recovery area already defined. If it does not exist create it
		if (wExists == false) {
            try {
				stmt = connection.prepareStatement(
					"create table XSEEDRECOVERYAREA ( \n" +
					" ERROR_DATE              NUMBER(8)      NOT NULL \n" + 
					",ERROR_TIME              NUMBER(8)      NOT NULL \n" +				
					",FINISH_DATE             NUMBER(8)      NOT NULL \n" + 
					",FINISH_TIME             NUMBER(8)      NOT NULL \n" +
					",JOB_ID                  VARCHAR2(100)  NOT NULL \n" +
					",LAST_INTGR_POINT_DATE   NUMBER(8)      NOT NULL \n" +
					",LAST_INTGR_POINT_ID     NUMBER(4)      NOT NULL \n" +
					",LAST_INTGR_POINT_TIME   NUMBER(8)      NOT NULL \n" +
					",PARALLEL_MODE           VARCHAR2(1)    NOT NULL \n" +
					",PID                     VARCHAR2(10)   NOT NULL \n" +
					",QUEUE_ID                NUMBER(4)      NOT NULL \n" +
					",RECOVERY_ID             NUMBER(10)     NOT NULL \n" +
					",RECOVERY_DATE           NUMBER(8)      NOT NULL \n" + 
					",RECOVERY_TIME           NUMBER(8)      NOT NULL \n" +
					",REPORT                  VARCHAR2(100)  NOT NULL \n" +
					",START_DATE              NUMBER(8)      NOT NULL \n" +
					",START_TIME              NUMBER(8)      NOT NULL \n" +
					",STATUS                  VARCHAR2(1)    NOT NULL \n" +
					",RECOVERY_AREA           BLOB )" 
				);
				stmt.executeUpdate();
				stmt.close();
				stmt = null;
			} catch (SQLException ex) {
			    // Ignore SQL errors creating table
			}
		}
	}	
	
	
	/**
	 * isProccessActive - Check if the process is active on the operating system.<br>
	 * @return true indicates process active.
	 **/ 
	public boolean isProccessActive(String pID) throws Exception  {
        boolean isActive = false;
	    Process proc;
        String cmdLine [] = new String[3];
		
		System.out.println("XseedRecoveryTools: Checking if PID is active" );			
        
		// Validations
        if (pID==null) {
            return false;
        }
        if (pID.trim().equals("")) {
            return false;
        }
		
        // Check process on UNIX&LINUX operating systems
        if ( File.separatorChar == '/' ) {
            cmdLine[0] = "/bin/ps"  ;
            cmdLine[1] = "-p";
			cmdLine[2] = pID; 
            proc = Runtime.getRuntime().exec(cmdLine);
			
            // Check PS results
	        InputStream is = proc.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
     	    BufferedReader in = new BufferedReader(isr);
	        String stdoutLine = in.readLine();
			isActive = false;
	        while (stdoutLine!=null) {
	        	System.out.println(stdoutLine);
			    // return true if PS returns the PID as an active process ID
	            if (stdoutLine.indexOf(pID) != -1) {
			       isActive = true;
				   break;
				}   
	            stdoutLine = in.readLine();
            }
     	    in.close();
			in = null;
			
	    // Check process on Windows operating systems    
        } else {
			cmdLine[0] = "tasklist"  ;
            cmdLine[1] = "/FI";
			cmdLine[2] = "\"PID eq " + pID + "\""; 
			proc = Runtime.getRuntime().exec(cmdLine);
			
            // Check PS results
	        InputStream is = proc.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
     	    BufferedReader in = new BufferedReader(isr);
	        String stdoutLine = in.readLine();
			isActive = false;
			while (stdoutLine!=null) {
	        	System.out.println(stdoutLine);
			    // return true if TASKLIST returns the PID as an active process ID
	            if (stdoutLine.indexOf(pID) != -1) {
			       isActive = true;
				   break;
				}   
	            stdoutLine = in.readLine();
            }
     	    in.close();
			in = null;
		}
		return isActive;
	}
	
	/**
	 * getPID - Get the current process ID<br>
	 * @return The current process ID.
	 **/ 
	public String getPID() {
	    String pID = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
	    System.out.println("Report PID:" + pID);
		return pID;
	}
	
	
	/**
	 * isBlockingTransaction - Check if the current transaction is a blocking session<br>
	 * @return true indicates that this is a blocking session.
	 **/
	public boolean isBlockingTransaction() throws Exception {     
        PreparedStatement ps = null;
	    ResultSet rs = null;
	    boolean isBlocker = false;
	      
	    ps = connection.prepareStatement(
			" select waiters.sid   ," +
			"   tables.object_name ," +
			"   locks.sid " +
			" from  v$lock  locks,   " +     
			" (  select  sid, id1 from v$lock " +
			"   where sid in  ( " +
			"      select distinct sid " + 
			"      from   v$session " +
			"       where  lockwait is not null " +
			"  )  and lmode  = 0    " +
			" )  waiters,   /* BLOCKED SESSIONS */ " +
			" v$session   sessions,   /* OPEN SESSIONS    */ " +
			" all_objects tables     /* TABLES           */ " +
			" where   locks.id1         = waiters.id1  " +       
			"    and locks.lmode       != 0            " +   
			"    and locks.type        != 'TM'         " + 
			"    and sessions.sid       = waiters.sid  " +
			"    and sessions.lockwait  is not null    " +
			"    and tables.object_id = sessions.row_wait_obj#  " + 
			"    and locks.sid = ( " + 
			"        select sid from v$session " +
			"        where audsid = SYS_CONTEXT('userenv','sessionid') " + 
			"    ) "
		);  
		rs = ps.executeQuery();
		isBlocker = false;
		while (rs.next()) {
		    isBlocker = true;
		    System.out.println("Blocked SID:" + rs.getString(1)+"Table:" + rs.getString(2));
		}
		rs.close();
		rs = null;
		ps.close();
		ps=null;
		return isBlocker;	  
    }
	
}

