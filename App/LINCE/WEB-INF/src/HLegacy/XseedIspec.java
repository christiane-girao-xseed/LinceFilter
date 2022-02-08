package HLegacy;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.*;
import java.util.*;

import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;

import java.math.*;
import java.util.ArrayList;

public abstract class XseedIspec extends HttpServlet
{
    public double ACTMTH = 0;
    final public String HIGH_VALUE  = "" + (char)255;
    public String INPUT_DATE = "       ";
    public String ISPEC = "";
    final public String LOW_VALUE  = "" + (char)0;
    public String TODAY = "       ";
    public double TODAYS_DATE_NUM = 0;
    public double TODAYS_DAY = 0;
    public String TODAYS_MONTH = "   ";
    public double TODAYS_MONTH_NUM = 0;
    public double TODAYS_YEAR = 0;
    public double TRANNO = 0;
    public ResultSet XSEED_SQL=null;
    public Statement XSEED_CMD=null;
    public BigDecimal bigZeros = new BigDecimal(0);

    private Vector<Object> controlList = new Vector<Object>();

    public String routineErrorMsg="";
    public String routineErrorName="";

    public XseedGLB GLB = new XseedGLB();

    public HttpSession session;
    public HttpServletRequest request;
    public HttpServletResponse response;

    private String XseedSID;

	protected static DecimalFormat df;
	static
	{
    	df = new DecimalFormat("########");
	}
//DateConvert
	protected SimpleDateFormat sdf_DDD;
	protected SimpleDateFormat sdf_yyyy;
	protected SimpleDateFormat sdf_dd_MM_yy;
	protected SimpleDateFormat sdf_dd_MM_yyyy;
	protected SimpleDateFormat sdf_dd_MMM_yy;
	protected SimpleDateFormat sdf_dd_MMM_yyyy;
	protected SimpleDateFormat sdf_ddMMMyy;
	protected SimpleDateFormat sdf_ddMMMyyyy;
	protected SimpleDateFormat sdf_ddMMyy;
	protected SimpleDateFormat sdf_ddMMyyyy;
	protected SimpleDateFormat sdf_MM_dd_yy;	
	protected SimpleDateFormat sdf_MM_dd_yyyy;
	protected SimpleDateFormat sdf_MMddyy;
	protected SimpleDateFormat sdf_MMM_dd_yy;
	protected SimpleDateFormat sdf_MMddyyyy;		
	protected SimpleDateFormat sdf_MMM_dd_yyyy;
	protected SimpleDateFormat sdf_MMMddyy;
	protected SimpleDateFormat sdf_MMMddyyyy;
	protected SimpleDateFormat sdf_TODAY;
	protected SimpleDateFormat sdf_UK_ALPHA;
	protected SimpleDateFormat sdf_US_ALPHA;
	protected SimpleDateFormat sdf_IN_ALPHA;
	protected SimpleDateFormat sdf_yyDDD;
	protected SimpleDateFormat sdf_yyyyDDD;	
	protected SimpleDateFormat sdf_yyMMdd;
	protected SimpleDateFormat sdf_yyyyMMdd;
	protected SimpleDateFormat sdf_yy_MM_dd;
	protected SimpleDateFormat sdf_yyyy_MM_dd;
	protected SimpleDateFormat sdf_yyMMMdd;
	protected SimpleDateFormat sdf_yyyyMMMdd;
	protected SimpleDateFormat sdf_yy_MMM_dd;
	protected SimpleDateFormat sdf_yyyy_MMM_dd;
	protected SimpleDateFormat sdf_ww;

	
	protected SimpleDateFormat sdf_MMyyyy;
	protected SimpleDateFormat sdf_MM_yyyy;
	protected SimpleDateFormat sdf_yyyyMM;
	
	protected Calendar auxDate_BASE;
	protected Calendar auxDate_Current;
	protected boolean flagSettedSDF = true;

    protected long gDSTSavings;

	protected void setDateFormats() throws Exception
	{
        TimeZone tz;
		flagSettedSDF = false;

		sdf_DDD = new SimpleDateFormat();
		sdf_DDD.applyPattern("DDD");

		sdf_yyyy = new SimpleDateFormat();
		sdf_yyyy.applyPattern("yyyy");

        tz = sdf_yyyy.getTimeZone();

		sdf_dd_MM_yy = new SimpleDateFormat();
		sdf_dd_MM_yy.applyPattern("dd/MM/yy");

		sdf_dd_MM_yyyy = new SimpleDateFormat();
		sdf_dd_MM_yyyy.applyPattern("dd/MM/yyyy");

		sdf_dd_MMM_yy = new SimpleDateFormat();
		sdf_dd_MMM_yy.applyPattern("dd MMM yy");

		sdf_dd_MMM_yyyy = new SimpleDateFormat();
		sdf_dd_MMM_yyyy.applyPattern("dd MMM yyyy");

		sdf_ddMMMyy = new SimpleDateFormat();
		sdf_ddMMMyy.applyPattern("ddMMMyy");

		sdf_ddMMMyyyy = new SimpleDateFormat();
		sdf_ddMMMyyyy.applyPattern("ddMMMyyyy");

		sdf_ddMMyy = new SimpleDateFormat();
		sdf_ddMMyy.applyPattern("ddMMyy");

		sdf_ddMMyyyy = new SimpleDateFormat();
		sdf_ddMMyyyy.applyPattern("ddMMyyyy");
		
		sdf_MMyyyy = new SimpleDateFormat();
		sdf_MMyyyy.applyPattern("MMyyyy");

		sdf_MM_dd_yy = new SimpleDateFormat();
		sdf_MM_dd_yy.applyPattern("MM/dd/yy");

		sdf_MM_yyyy = new SimpleDateFormat();
		sdf_MM_yyyy.applyPattern("MM/yyyy");
		
		sdf_MM_dd_yyyy = new SimpleDateFormat();
		sdf_MM_dd_yyyy.applyPattern("MM/dd/yyyy");

		sdf_MMddyy = new SimpleDateFormat();
		sdf_MMddyy.applyPattern("MMddyy");

		sdf_MMddyyyy = new SimpleDateFormat();
		sdf_MMddyyyy.applyPattern("MMddyyyy");

		sdf_MMM_dd_yy = new SimpleDateFormat();
		sdf_MMM_dd_yy.applyPattern("MMM dd yy");

		sdf_MMM_dd_yyyy = new SimpleDateFormat();
		sdf_MMM_dd_yyyy.applyPattern("MMM dd yyyy");

		sdf_MMMddyy = new SimpleDateFormat();
		sdf_MMMddyy.applyPattern("MMMddyy");

		sdf_MMMddyyyy = new SimpleDateFormat();
		sdf_MMMddyyyy.applyPattern("MMMddyyyy");

		sdf_TODAY = new SimpleDateFormat();
		sdf_TODAY.applyPattern("EEEEEEEEEEEEEEEEEEEEEEE");

		sdf_UK_ALPHA = new SimpleDateFormat();
		sdf_UK_ALPHA.applyPattern("dd MMMM yyyy");

		sdf_US_ALPHA = new SimpleDateFormat();
		sdf_US_ALPHA.applyPattern("MMMM dd yyyy");

		sdf_IN_ALPHA = new SimpleDateFormat();
		sdf_IN_ALPHA.applyPattern("yyyy MMMM dd");

		sdf_yyDDD = new SimpleDateFormat();
		sdf_yyDDD.applyPattern("yyDDD");
		
		
		sdf_yyyyDDD = new SimpleDateFormat();
		sdf_yyyyDDD.applyPattern("yyyyDDD");

		sdf_yyyyMM = new SimpleDateFormat();
		sdf_yyyyMM.applyPattern("yyyyMM");
		
		sdf_yyMMdd = new SimpleDateFormat();
		sdf_yyMMdd.applyPattern("yyMMdd");

		sdf_yyyyMMdd = new SimpleDateFormat();
		sdf_yyyyMMdd.applyPattern("yyyyMMdd");

		sdf_yy_MM_dd = new SimpleDateFormat();
		sdf_yy_MM_dd.applyPattern("yy/MM/dd");

		sdf_yyyy_MM_dd = new SimpleDateFormat();
		sdf_yyyy_MM_dd.applyPattern("yyyy/MM/dd");

		sdf_yyMMMdd = new SimpleDateFormat();
		sdf_yyMMMdd.applyPattern("yyMMMdd");

		sdf_yyyyMMMdd = new SimpleDateFormat();
		sdf_yyyyMMMdd.applyPattern("yyyyMMMdd");

		sdf_yy_MMM_dd = new SimpleDateFormat();
		sdf_yy_MMM_dd.applyPattern("yy MMM dd");

		sdf_yyyy_MMM_dd = new SimpleDateFormat();
		sdf_yyyy_MMM_dd.applyPattern("yyyy MMM dd");

		sdf_ww = new SimpleDateFormat();
		sdf_ww.applyPattern("ww");

        gDSTSavings = 0;
        if (tz.useDaylightTime())
        {
           gDSTSavings = tz.getDSTSavings();
        }
	}

	protected void setDateVariables() throws Exception
	{

		auxDate_BASE = Calendar.getInstance();
		auxDate_Current = Calendar.getInstance();
	}


public void addControl (Object pObject)
throws Exception
{
    try
    {
        controlList.add((Object)pObject);
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "addControl");

    }
}
/* Métodos iguais no XseedIspec e XseedReport */

/* AuditCrash */
public void AuditCrash(String pMsg)
    throws Exception
{
    PrintStream audit = new PrintStream(new FileOutputStream(GLB.SYSTEMDIR  + File.separatorChar + "Alert.Log",true));
    audit.println(pMsg);
    audit.close();
}

/* formatDC */
public String formatDC(BigDecimal pNumber, String pMask)
	throws Exception
{

	String strNumber = df.format(pNumber);
	char[] buffer = new char[pMask.length()];

	if( strNumber.length() < pMask.length())
	{
		int difference = pMask.length() - strNumber.length();
		for(int i=0; i<difference; i++)
		{
			buffer[i] = '0';
		}
		strNumber = String.copyValueOf(buffer, 0, difference) + strNumber;
	}

	else if(strNumber.length() > pMask.length())
	{
		int difference = strNumber.length() - pMask.length();
		strNumber = strNumber.substring(difference);
	}
	return strNumber;
}

/* formatDC */
public String formatDC(double pNumber, String pMask)
	throws Exception
{

	String strNumber = df.format(pNumber);
	char[] buffer = new char[pMask.length()];

	if( strNumber.length() < pMask.length())
	{
		int difference = pMask.length() - strNumber.length();
		for(int i=0; i<difference; i++)
		{
			buffer[i] = '0';
		}
		strNumber = String.copyValueOf(buffer, 0, difference) + strNumber;
	}

	else if(strNumber.length() > pMask.length())
	{
		int difference = strNumber.length() - pMask.length();
		strNumber = strNumber.substring(difference);
	}
	return strNumber;
}



public String formatDate(String DCDate, String DCSign, double DCNumber, String dcFormat, String dcFormatResult)
    throws Exception
{
    try
    {
        String dcResult="";
        String pCentury = "";

        Date dt  = new Date();
        Calendar cal = Calendar.getInstance();
    	if(flagSettedSDF)
	    {
			setDateFormats();
			setDateVariables();
	    }

       GLB.STATUS = "";
        if (dcFormat.equals("DAYNUM") == false)
        {
            pCentury = validateDate (DCDate, dcFormat);
        }
        try
        {

			if (GLB.STATUS.equals("*****") == false)
			{
			    if (dcFormat.equals("DAYNUM")==true)
			    {
			        //dd_MM_yyyy
			    	dt = sdf_dd_MM_yyyy.parse("01/01/" + String.valueOf(GLB.BASE));
			        cal.setTime(dt);
			        cal.set(cal.DATE, cal.get(cal.DATE) + Integer.parseInt(DCDate));
			        dt = cal.getTime();
			    }

			    if (dcFormat.equals("DD-MM-YY")==true)
			    {
			        //dd_MM_yyyy
			    	dt = sdf_dd_MM_yyyy.parse(DCDate.substring(0,6) + pCentury + DCDate.substring(6,8));
			    }

			    if (dcFormat.equals("DD-MMM-YY")==true)
			    {
			        //dd_MMM_yyyy
			    	dt = sdf_dd_MMM_yyyy.parse(DCDate.substring(0,7) + pCentury + DCDate.substring(7,9));
			    }

			    if (dcFormat.equals("DDMMYY")==true)
			    {
			        //ddMMyyyy
			    	dt = sdf_ddMMyyyy.parse(DCDate.substring(0,4) + pCentury + DCDate.substring(4,6));
			    }

			    if (dcFormat.equals("DDMMMYY")==true)
			    {
			        //ddMMMyyyy
			    	dt = sdf_ddMMMyyyy.parse(DCDate.substring(0,5) + pCentury + DCDate.substring(5,7));
			    }			  
			    
			    if (dcFormat.equals("MM-DD-YY")==true)
			    {
			        //MM_dd_yyyy
			    	dt = sdf_MM_dd_yyyy.parse(DCDate.substring(0,6) + pCentury + DCDate.substring(6,8));
			    }

			    if (dcFormat.equals("MMM-DD-YY")==true)
			    {
			        //MMM_dd_yyyy
			    	dt = sdf_MMM_dd_yyyy.parse(DCDate.substring(0,7) + pCentury + DCDate.substring(7,9));
			    }

			    if (dcFormat.equals("MMDDYY")==true)
			    {
			        //MMddyyyy
			    	dt = sdf_MMddyyyy.parse(DCDate.substring(0,4) + pCentury + DCDate.substring(4,6));
			    }

			    if (dcFormat.equals("MMMDDYY")==true)
			    {
			        //MMMddyyyy
			    	dt = sdf_MMMddyyyy.parse(DCDate.substring(0,5) + pCentury + DCDate.substring(5,7));
			    }

			    if (dcFormat.equals("UK-ALPHA")==true)
			    {
			        //dd_MMMM_yyyy
			        dt = sdf_UK_ALPHA.parse(DCDate);
			    }

			    if (dcFormat.equals("US-ALPHA")==true)
			    {
			        //MMMM_dd_yyyy
			    	dt = sdf_US_ALPHA.parse(DCDate);
			    }

			    if (dcFormat.equals("IN-ALPHA")==true)
			    {
			        //yyyy_MMMM_dd
			    	dt = sdf_IN_ALPHA.parse(DCDate);
			    }

			    if (dcFormat.equals("YYDDD")==true)
			    {
			        //yyyyDDD
			    	dt = sdf_yyyyDDD.parse(pCentury + DCDate);
			    }

			    if (dcFormat.equals("YYMMDD")==true)
			    {
			        //yyyyMMdd
			    	dt = sdf_yyyyMMdd.parse(pCentury + DCDate);
			    }

			    if (dcFormat.equals("YYMMMDD")==true)
			    {
			        //yyyyMMMdd
			    	dt = sdf_yyyyMMMdd.parse(pCentury + DCDate);
			    }

			    if (dcFormat.equals("YY-MM-DD")==true)
			    {
			        //yyyy_MM_dd
			    	dt = sdf_yyyy_MM_dd.parse(pCentury + DCDate);
			    }

			    if (dcFormat.equals("YY-MMM-DD")==true)
			    {
			        //yyyy_MMM_dd
			    	dt = sdf_yyyy_MMM_dd.parse(pCentury + DCDate);
			    }

			    if (dcFormat.equals("DD-MM-CCYY")==true)
			    {
			        //dd_MM_yyyy
			    	dt = sdf_dd_MM_yyyy.parse(DCDate);
			    }

			    if (dcFormat.equals("DD-MMM-CCYY")==true)
			    {
			        //dd_MMM_yyyy
			    	dt = sdf_dd_MMM_yyyy.parse(DCDate);
			    }

			    if (dcFormat.equals("DDMMCCYY")==true)
			    {
			        //ddMMyyyy
			    	dt = sdf_ddMMyyyy.parse(DCDate);
			    }

			    if (dcFormat.equals("DDMMMCCYY")==true)
			    {
			        //ddMMMyyyy
			    	dt = sdf_ddMMMyyyy.parse(DCDate);
			    }

			    if (dcFormat.equals("MM-DD-CCYY")==true)
			    {
			        //MM_dd_yyyy
			        dt = sdf_MM_dd_yyyy.parse(DCDate);
			    }

			    if (dcFormat.equals("MMDDCCYY")==true)
			    {
			        //MMddyyyy
			        dt = sdf_MMddyyyy.parse(DCDate);
			    }

			    if (dcFormat.equals("MMM-DD-CCYY")==true)
			    {
			        //MMM_dd_yyyy
			        dt = sdf_MMM_dd_yyyy.parse(DCDate);
			    }

			    if (dcFormat.equals("MMMDDCCYY")==true)
			    {
			        //MMMddyyyy
			        dt = sdf_MMMddyyyy.parse(DCDate);
			    }

			    if (dcFormat.equals("CCYYDDD")==true)
			    {
			        //yyyyDDD
			        dt = sdf_yyyyDDD.parse(DCDate);
			    }			    
			    
			    if (dcFormat.equals("CCYYMMDD")==true)
			    {
			        //yyyyMMdd
			        dt = sdf_yyyyMMdd.parse(DCDate);
			    }

			    if (dcFormat.equals("CCYYMMMDD")==true)
			    {
			        //yyyyMMMdd
			        dt = sdf_yyyyMMMdd.parse(DCDate);
			    }

			    if (dcFormat.equals("CCYY-MM-DD")==true)
			    {
			        //yyyy_MM_dd
			        dt = sdf_yyyy_MM_dd.parse(DCDate);
			    }

			    if (dcFormat.equals("CCYY-MMM-DD")==true)
			    {
			        //yyyy_MMM_dd
			        dt = sdf_yyyy_MMM_dd.parse(DCDate);
			    }
			    
			    if (dcFormat.equals("MMCCYY")==true)
			    {
			        //MMyyyy
			    	dt = sdf_MMyyyy.parse(DCDate);
			    }
			    
			    if (dcFormat.equals("MM-CCYY")==true)
			    {
			        //MM_yyyy
			    	dt = sdf_MM_yyyy.parse(DCDate);
			    }
			    
			    if (dcFormat.equals("CCYYMM")==true)
			    {
			        //MM_yyyy
			    	dt = sdf_yyyyMM.parse(DCDate);
			    }			    
			    
			}
        }
        catch (Exception e)
        {
            GLB.STATUS = "*****";
        }

        // Check if is a Invalid Date
        if (dt == null)
        {
            GLB.STATUS = "*****";
        }

        // Return to Current Date
        if (GLB.STATUS.equals("*****")==true)
        {
            dt = new Date();
        }

        DCSign = DCSign.trim();


        if(DCSign.equals("")==false)
        {
            // { + ou - } <day number>
            if (DCSign.equals("+")==false && DCSign.equals("-")==false)
            {
                return ("");
            }

            cal.setTime(dt);
            if (dcFormat.equals ("MMCCYY") || dcFormat.equals("CCYYMM") ||dcFormat.equals("MM_CCYY") ) {
            	if (DCSign.equals("+")==true)
	    		{
	       			cal.set(cal.MONTH, cal.get(cal.MONTH) + (int)DCNumber);
	    		}
	
	    		if (DCSign.equals("-")==true)
	    		{
	       			cal.set(cal.MONTH, cal.get(cal.MONTH) + (int)(DCNumber * (-1)) );
	    		}
            	
            } else {
    
	            if (DCSign.equals("+")==true)
	            {
	                cal.set(cal.DATE, cal.get(cal.DATE) + (int)DCNumber);
	            }
	
	            if (DCSign.equals("-")==true)
	            {
	                cal.set(cal.DATE, cal.get(cal.DATE) + (int)(DCNumber * (-1)) );
	            }
            }
            dt = cal.getTime();
        }

        if (dcFormatResult.equals("DD-MM-YY")==true)
        {   //dd/MM/yy
            dcResult = sdf_dd_MM_yy.format(dt);
        }
        else if (dcFormatResult.equals("DD-MM-CCYY")==true)
        {   //dd/MM/yyyy
            dcResult = sdf_dd_MM_yyyy.format(dt);
        }
        else if (dcFormatResult.equals("DD-MMM-YY")==true)
        {   //dd MMM yy
            dcResult = sdf_dd_MMM_yy.format(dt);
        }
        else if (dcFormatResult.equals("DD-MMM-CCYY")==true)
        {   //dd MMM yyyy
            dcResult = sdf_dd_MMM_yyyy.format(dt);
        }
        else if (dcFormatResult.equals("DDMMMYY")==true)
        {   //ddMMMyy
            dcResult = sdf_ddMMMyy.format(dt);
        }
        else if (dcFormatResult.equals("DDMMMCCYY")==true)
        {   //ddMMMyyyy
            dcResult = sdf_ddMMMyyyy.format(dt);
        }
        else if (dcFormatResult.equals("DDMMYY")==true)
        {   //ddMMyy
            dcResult = sdf_ddMMyy.format(dt);
        }
        else if (dcFormatResult.equals("DDMMCCYY")==true)
        {    //ddMMyyyy
            dcResult = sdf_ddMMyyyy.format(dt);
        }
        else if (dcFormatResult.equals("MM-DD-YY")==true)
        {   //MM/dd/yy
            dcResult = sdf_MM_dd_yy.format(dt);
        }
        else if (dcFormatResult.equals("MM-DD-CCYY")==true)
        {    //MM/dd/yyyy
            dcResult = sdf_MM_dd_yyyy.format(dt);
        }
        else if (dcFormatResult.equals("MMDDYY")==true)
        {    //MMddyy
            dcResult = sdf_MMddyy.format(dt);
        }
        else if (dcFormatResult.equals("MMDDCCYY")==true)
        {    //MMddyyyy
            dcResult = sdf_MMddyyyy.format(dt);
        }
        else if (dcFormatResult.equals("MMM-DD-YY")==true)
        {   //MMM dd yy
            dcResult = sdf_MMM_dd_yy.format(dt);
        }
        else if (dcFormatResult.equals("MMM-DD-CCYY")==true)
        {   //MMM dd yyyy
            dcResult = sdf_MMM_dd_yyyy.format(dt);
        }
        else if (dcFormatResult.equals("MMMDDYY")==true)
        {   //MMMddyy
            dcResult = sdf_MMMddyy.format(dt);
        }
        else if (dcFormatResult.equals("MMMDDCCYY")==true)
        {    //MMMddyyyy
            dcResult = sdf_MMMddyyyy.format(dt);
        }
        else if (dcFormatResult.equals("TODAY")==true)
        {   //EEEEEEEEEEEEEEEEEEEEEEE
            dcResult = sdf_TODAY.format(dt);
        }
        else if (dcFormatResult.equals("UK-ALPHA") == true)
        {   //dd MMMM yyyy
            dcResult = sdf_UK_ALPHA.format(dt);
        }
        else if (dcFormatResult.equals("US-ALPHA") == true)
        {   //MMMM dd yyyy
            dcResult = sdf_US_ALPHA.format(dt);
        }
        else if (dcFormatResult.equals("IN-ALPHA") == true)
        {   //yyyy MMMM dd
            dcResult = sdf_IN_ALPHA.format(dt);
        }
        else if (dcFormatResult.equals("YYDDD")==true)
        {   //yyDDD
            dcResult = sdf_yyDDD.format(dt);
        }
        else if (dcFormatResult.equals("CCYYDDD")==true)
        {   //yyyyDDD
            dcResult = sdf_yyyyDDD.format(dt);
        }
        else if (dcFormatResult.equals("YYMMDD")==true)
        {   //yyMMdd
            dcResult = sdf_yyMMdd.format(dt);
        }
        else if (dcFormatResult.equals("CCYYMMDD")==true)
        {   //yyyyMMdd
            dcResult = sdf_yyyyMMdd.format(dt);
        }
        else if (dcFormatResult.equals("YY-MM-DD")==true)
        {   //yy/MM/dd
            dcResult = sdf_yy_MM_dd.format(dt);
        }
        else if (dcFormatResult.equals("CCYY-MM-DD")==true)
        {   //yyyy/MM/dd
            dcResult = sdf_yyyy_MM_dd.format(dt);
        }
        else if (dcFormatResult.equals("YYMMMDD")==true)
        {   //yyMMMdd
            dcResult = sdf_yyMMMdd.format(dt);
        }
        else if (dcFormatResult.equals("CCYYMMMDD")==true)
        {   //yyyyMMMdd
            dcResult = sdf_yyyyMMMdd.format(dt);
        }
        else if (dcFormatResult.equals("YY-MMM-DD")==true)
        {   //yy MMM dd
            dcResult = sdf_yy_MMM_dd.format(dt);
        }
        else if (dcFormatResult.equals("CCYY-MMM-DD")==true)
        {   //yyyy MMM dd
            dcResult = sdf_yyyy_MMM_dd.format(dt);
        }
        else if (dcFormatResult.equals("WEEKNO")==true)
        {   //ww
        	dcResult = sdf_ww.format(dt);
        }
        else if (dcFormatResult.equals("DAYNUM")==true)
        {
            //Alteracao feita para a GFG - Comentada
			//auxDate_BASE.setTime(sdf_yyyy.parse(String.valueOf(GLB.BASE)));
           	//auxDate_Current.setTime(dt);
			//dcResult =String.valueOf(XseedFunctions.calcDaynum(auxDate_Current,GLB.BASE) );
			
           	auxDate_BASE.setTime(sdf_yyyy.parse(String.valueOf(GLB.BASE)));
        	auxDate_Current.setTime(dt);

			long diffMillis = auxDate_Current.getTimeInMillis()-auxDate_BASE.getTimeInMillis();
            diffMillis = diffMillis + gDSTSavings;
			dcResult = String.valueOf(diffMillis/(24*60*60*1000));			
		}

        if (GLB.DCUPPERCASE.equals("TRUE") == true)
        {
            dcResult = dcResult.toUpperCase();
        }
        return (dcResult);
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "formatDate");
        return("");
    }
}

/* DCConvert */
public void DCConvert(String DCDate, String DCSign, double DCNumber, String dcFormat)
    throws Exception
{
	try
	{
    	String pCentury = "";
    	Date dt  = null;

		Calendar cal = Calendar.getInstance();

		if(flagSettedSDF)
	    {
			setDateFormats();
			setDateVariables();
	    }


    	GLB.STATUS = "";
    	if (dcFormat.equals("DAYNUM") == false)
    	{
    		pCentury = validateDate (DCDate, dcFormat);
		}

		try{

			if (GLB.STATUS.equals("*****") == false)
			{
		   		if (dcFormat.equals("DAYNUM")==true)
		   		{
		      		//dd/MM/yyyy
		      		dt = sdf_dd_MM_yyyy.parse("01/01/" + String.valueOf(GLB.BASE));
		      		cal.setTime(dt);
		            cal.set(cal.DATE, cal.get(cal.DATE) + Integer.parseInt(DCDate));
		      		dt = cal.getTime();
		   		}

		   		else if (dcFormat.equals("DD-MM-YY")==true)
		   		{
		      		//dd/MM/yyyy
		      		dt = sdf_dd_MM_yyyy.parse(DCDate.substring(0,6) + pCentury + DCDate.substring(6,8));
		   		}

			   	else if (dcFormat.equals("DD-MMM-YY")==true)
				{
		      		//dd/MMM/yyyy
		      		dt = sdf_dd_MMM_yyyy.parse(DCDate.substring(0,7) + pCentury + DCDate.substring(7,9));
		   		}

		   		else if (dcFormat.equals("DDMMYY")==true)
		   		{
		      		//ddMMyyyy
		      		dt = sdf_ddMMyyyy.parse(DCDate.substring(0,4) + pCentury + DCDate.substring(4,6));
		   		}

		   		else if (dcFormat.equals("DDMMMYY")==true)
		   		{
		      		//ddMMMyyyy
		      		dt = sdf_ddMMMyyyy.parse(DCDate.substring(0,5) + pCentury + DCDate.substring(5,7));
		   		}

		   		else if (dcFormat.equals("MM-DD-YY")==true)
		   		{
		      		//MM/dd/yyyy
		      		dt = sdf_MM_dd_yyyy.parse(DCDate.substring(0,6) + pCentury + DCDate.substring(6,8));
		   		}

		   		else if (dcFormat.equals("MMM-DD-YY")==true)
		   		{
		      		//MMM/dd/yyyy
		      		dt = sdf_MMM_dd_yyyy.parse(DCDate.substring(0,7) + pCentury + DCDate.substring(7,9));
		   		}

		   		else if (dcFormat.equals("MMDDYY")==true)
		   		{
		      		//MMddyyyy
		      		dt = sdf_MMddyyyy.parse(DCDate.substring(0,4) + pCentury + DCDate.substring(4,6));
		   		}

		   		else if (dcFormat.equals("MMMDDYY")==true)
		   		{
		      		//MMMddyyyy
		      		dt = sdf_MMMddyyyy.parse(DCDate.substring(0,5) + pCentury + DCDate.substring(5,7));
		   		}

		   		else if (dcFormat.equals("UK-ALPHA")==true)
		   		{
		      		//dd MMMM yyyy
		      		dt = sdf_UK_ALPHA.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("US-ALPHA")==true)
		   		{
		      		//MMMM dd yyyy"
		      		dt = sdf_US_ALPHA.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("IN-ALPHA")==true)
		   		{
		      		//yyyy MMMM dd;
		      		dt = sdf_IN_ALPHA.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("YYDDD")==true)
		   		{
		      		//yyyyDDD;
		      		dt = sdf_yyyyDDD.parse(pCentury + DCDate);
		   		}

		   		else if (dcFormat.equals("YYMMDD")==true)
		   		{
		      		//yyMMdd;
		      		dt = sdf_yyyyMMdd.parse(pCentury + DCDate);
		   		}

		   		else if (dcFormat.equals("YYMMMDD")==true)
		   		{
		      		//yyMMMdd;
		      		dt = sdf_yyMMMdd.parse(pCentury + DCDate);
		   		}

		   		else if (dcFormat.equals("YY-MM-DD")==true)
		   		{
		      		//yyyy/MM/dd;
		      		dt = sdf_yyyy_MM_dd.parse(pCentury + DCDate);
		   		}

		   		else if (dcFormat.equals("YY-MMM-DD")==true)
		   		{
		      		//yyyy/MMM/dd;
		      		dt = sdf_yyyy_MMM_dd.parse(pCentury + DCDate);
		   		}

		   		else if (dcFormat.equals("DD-MM-CCYY")==true)
		   		{
		      		//dd/MM/yyyy
		      		dt = sdf_dd_MM_yyyy.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("DD-MMM-CCYY")==true)
		   		{
		      		//dd MMM yyyy;
		      		dt = sdf_dd_MMM_yyyy.parse(DCDate);
		   		}

		       	else if (dcFormat.equals("DDMMCCYY")==true)
			   	{
		    		//ddMMyyyy
		      		dt = sdf_ddMMyyyy.parse(DCDate);
				}

		 	  	else if (dcFormat.equals("DDMMMCCYY")==true)
		   		{
		      		//ddMMMyyyy
		      		dt = sdf_ddMMMyyyy.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("MM-DD-CCYY")==true)
		   		{
		      		//MM/dd/yyyy;
		      		dt = sdf_MM_dd_yyyy.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("MMDDCCYY")==true)
		   		{
		      		//MMddyyyy
		      		dt = sdf_MMddyyyy.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("MMM-DD-CCYY")==true)
		   		{
		      		//MMM dd yyyy
		      		dt = sdf_MMM_dd_yyyy.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("MMMDDCCYY")==true)
		   		{
		      		//MMMddyyyy
		      		dt = sdf_MMMddyyyy.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("CCYYDDD")==true)
		   		{
		      		//yyyyDDD
		      		dt = sdf_yyyyDDD.parse(DCDate);
		   		}

				else if (dcFormat.equals("CCYYMMDD")==true)
		   		{
		      		//yyyyMMdd
		      		dt = sdf_yyyyMMdd.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("CCYYMMMDD")==true)
		   		{
		      		//yyyyMMMdd
		      		dt = sdf_yyyyMMMdd.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("CCYY-MM-DD")==true)
		   		{
		      		//yyyy/MM/dd;
		      		dt = sdf_yyyy_MM_dd.parse(DCDate);
		   		}

		   		else if (dcFormat.equals("CCYY-MMM-DD")==true)
		   		{
		      		//yyyy MMM dd
		      		dt = sdf_yyyy_MMM_dd.parse(DCDate);
		   		}
		   		////
		   		else if (dcFormat.equals("MMCCYY")==true)
			    {
			        //MMyyyy
			    	dt = sdf_MMyyyy.parse(DCDate);
			    }
			    
		   		else if (dcFormat.equals("MM-CCYY")==true)
			    {
			        //MM_yyyy
			    	dt = sdf_MM_yyyy.parse(DCDate);
			    }
			    
		   		else if (dcFormat.equals("CCYYMM")==true)
			    {
			        //MM_yyyy
			    	dt = sdf_yyyyMM.parse(DCDate);
			    }	
		   		
		   		

			}
		}
		catch (Exception e)
		{
			GLB.STATUS = "*****";
		}

    	// Check if is a Invalid Date
    	if (dt == null)
    	{
       		GLB.STATUS = "*****";
    	}

    	// Return to Current Date
    	if (GLB.STATUS.equals("*****")==true)
    	{
      		dt = new Date();
    	}
		DCSign = DCSign.trim();


		if(GLB.STATUS.equals("") && DCSign.equals("")==false)		
		{
    		// { + ou - } <day number>
    		if (DCSign.equals("+")==false && DCSign.equals("-")==false)
    		{
      			return;
	    	}
    		cal.setTime(dt);
    		
            if (dcFormat.equals ("MMCCYY") || dcFormat.equals("CCYYMM") ||dcFormat.equals("MM_CCYY") ) {
            	if (DCSign.equals("+")==true)
	    		{
	       			cal.set(cal.MONTH, cal.get(cal.MONTH) + (int)DCNumber);
	    		}
	
	    		if (DCSign.equals("-")==true)
	    		{
	       			cal.set(cal.MONTH, cal.get(cal.MONTH) + (int)(DCNumber * (-1)) );
	    		}
            	
            } else {
    		
		    	if (DCSign.equals("+")==true)
	    		{
	       			cal.set(cal.DATE, cal.get(cal.DATE) + (int)DCNumber);
	    		}
	
	    		if (DCSign.equals("-")==true)
	    		{
	       			cal.set(cal.DATE, cal.get(cal.DATE) + (int)(DCNumber * (-1)) );
	    		}
            }

    		dt = cal.getTime();
		}

   	// Glb.Dc Variables
    //yyyy
	auxDate_BASE.setTime(sdf_yyyy.parse(String.valueOf(GLB.BASE)));
    auxDate_Current.setTime(dt);

	//Alteracao feita para a GFG - Comentada
	//GLB.DC_DAYNUM =XseedFunctions.calcDaynum(auxDate_Current, GLB.BASE) ;
	long diffMillis = auxDate_Current.getTimeInMillis()-auxDate_BASE.getTimeInMillis();
    diffMillis = diffMillis + gDSTSavings;
	GLB.DC_DAYNUM = diffMillis/(24*60*60*1000);

	//dd/MM/yy
      	GLB.DC_DD_MM_YY = sdf_dd_MM_yy.format(dt);

 	//dd/MM/yyyy
      	GLB.DC_DD_MM_CCYY = sdf_dd_MM_yyyy.format(dt);

      	//dd MMM yy
      	GLB.DC_DD_MMM_YY = sdf_dd_MMM_yy.format(dt);

      	//dd MMM yyyy
      	GLB.DC_DD_MMM_CCYY = sdf_dd_MMM_yyyy.format(dt);

      	//ddMMMyy
      	GLB.DC_DDMMMYY = sdf_ddMMMyy.format(dt);

      	//ddMMMyyyy
      	GLB.DC_DDMMMCCYY = sdf_ddMMMyyyy.format(dt);

      	//ddMMyy
      	GLB.DC_DDMMYY = Double.parseDouble(sdf_ddMMyy.format(dt));

      	//ddMMyyyy
      	GLB.DC_DDMMCCYY = Double.parseDouble(sdf_ddMMyyyy.format(dt));

      	//MM/dd/yy
      	GLB.DC_MM_DD_YY = sdf_MM_dd_yy.format(dt);

      	//MM/dd/yyyy
      	GLB.DC_MM_DD_CCYY = sdf_MM_dd_yyyy.format(dt);

      	//MMddyy
      	GLB.DC_MMDDYY = Double.parseDouble(sdf_MMddyy.format(dt));

      	//MMddyyyy
      	GLB.DC_MMDDCCYY = Double.parseDouble(sdf_MMddyyyy.format(dt));

      	//MMM dd yy
      	GLB.DC_MMM_DD_YY = sdf_MMM_dd_yy.format(dt);

      	//MMM dd yyyy
      	GLB.DC_MMM_DD_CCYY = sdf_MMM_dd_yyyy.format(dt);

      	//MMMddyyyy
      	GLB.DC_MMMDDYY = sdf_MMMddyyyy.format(dt);

      	//MMMddyyyy
      	GLB.DC_MMMDDCCYY = sdf_MMMddyyyy.format(dt);

	// EEEEEEEEEEEEEEEEEEEEEEE
      	GLB.DC_TODAY = (sdf_TODAY.format(dt)).toUpperCase();

	// dd MMMM yyyy
      	GLB.DC_UK_ALPHA = sdf_UK_ALPHA.format(dt);

      	//MMMM dd yyyy
      	GLB.DC_US_ALPHA = sdf_US_ALPHA.format(dt);

      	//yyyy MMMM dd
      	GLB.DC_IN_ALPHA = sdf_IN_ALPHA.format(dt);

      	//yyDDD
      	GLB.DC_YYDDD = Double.parseDouble(sdf_yyDDD.format(dt));

  	//yyyyDDD
      	GLB.DC_CCYYDDD = Double.parseDouble(sdf_yyyyDDD.format(dt));

      	//yyMMdd
      	GLB.DC_YYMMDD = Double.parseDouble(sdf_yyMMdd.format(dt));

      	//"yyyyMMdd
      	GLB.DC_CCYYMMDD = Double.parseDouble(sdf_yyyyMMdd.format(dt));

      	//yy/MM/dd
      	GLB.DC_YY_MM_DD = sdf_yy_MM_dd.format(dt);

  	//yyyy/MM/dd
      	GLB.DC_CCYY_MM_DD = sdf_yyyy_MM_dd.format(dt);

      	//yyMMMdd
      	GLB.DC_YYMMMDD = sdf_yyMMMdd.format(dt);

  	//yyyyMMMdd
      	GLB.DC_CCYYMMMDD = sdf_yyyyMMMdd.format(dt);

      	//yy MMM dd
      	GLB.DC_YY_MMM_DD = sdf_yy_MMM_dd.format(dt);

      	//yyyy MMM dd;
      	GLB.DC_CCYY_MMM_DD = sdf_yyyy_MMM_dd.format(dt);

      	//ww
      	GLB.DC_WEEKNO = Double.parseDouble(sdf_ww.format(dt));

        GLB.DC_CC = cDbl(GLB.DC_CCYY_MMM_DD.substring(0,2));

      	if (GLB.UPDATEGLBCENTURY == true)
      	{	GLB.CENTURY = cDbl(GLB.DC_CCYY_MMM_DD.substring(0,2));
      	}
      	if (GLB.DCUPPERCASE.equals("TRUE") == true)
      	{
            GLB.DC_CCYYMMMDD = GLB.DC_CCYYMMMDD.toUpperCase();
            GLB.DC_CCYY_MMM_DD = GLB.DC_CCYY_MMM_DD.toUpperCase();
            GLB.DC_DD_MMM_CCYY = GLB.DC_DD_MMM_CCYY.toUpperCase();
            GLB.DC_DDMMMCCYY = GLB.DC_DDMMMCCYY.toUpperCase();
            GLB.DC_DD_MMM_YY = GLB.DC_DD_MMM_YY.toUpperCase();
            GLB.DC_DDMMMYY = GLB.DC_DDMMMYY.toUpperCase();
            GLB.DC_IN_ALPHA = GLB.DC_IN_ALPHA.toUpperCase();
            GLB.DC_MMM_DD_YY = GLB.DC_MMM_DD_YY.toUpperCase();
            GLB.DC_MMM_DD_CCYY = GLB.DC_MMM_DD_CCYY.toUpperCase();
            GLB.DC_MMMDDYY = GLB.DC_MMMDDYY.toUpperCase();
            GLB.DC_MMMDDCCYY = GLB.DC_MMMDDCCYY.toUpperCase();
            GLB.DC_TODAY = GLB.DC_TODAY.toUpperCase();
            GLB.DC_UK_ALPHA = GLB.DC_UK_ALPHA.toUpperCase();
            GLB.DC_US_ALPHA = GLB.DC_US_ALPHA.toUpperCase();
            GLB.DC_YYMMMDD = GLB.DC_YYMMMDD.toUpperCase();
            GLB.DC_YY_MMM_DD = GLB.DC_YY_MMM_DD.toUpperCase();
      	}
      	
      	GLB.DC_CCYYMM =  Double.parseDouble(sdf_yyyyMM.format(dt));
      	GLB.DC_MMCCYY =  Double.parseDouble(sdf_MMyyyy.format(dt));
      	GLB.DC_MM_CCYY =  sdf_MM_yyyy.format(dt);
      	SetLastDayMonth(dt);

    }
    catch(Exception e)
    {
       ShowErrorMsg (e, "DCConvert");
    }
}
private void SetLastDayMonth (Date pDt) throws Exception
{
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(pDt);      
	   calendar.add(Calendar.MONTH, 1);  
	   calendar.set(Calendar.DAY_OF_MONTH, 1);  
	   calendar.add(Calendar.DATE, -1);;
	   
	   pDt = calendar.getTime();
	   GLB.DC_LASTDAYMONTH= Double.parseDouble(sdf_ddMMyyyy.format(pDt).substring(0,2)); 	   
	   GLB.DC_CCYYMMLD = Double.parseDouble(sdf_yyyyMMdd.format(pDt)); 
}


/* DCToAlpha */
public String DCToAlpha()
    throws Exception
{
    try
    {
        GLB.STATUS = "";
    	Date dt  = new Date();
    	Calendar cal = Calendar.getInstance();

    	SimpleDateFormat sdf = new SimpleDateFormat();

           	sdf.applyPattern("dd/MM/yyyy");
    	dt = sdf.parse("01/01/" + format(GLB.BASE,"9999"));

    	cal.setTime(dt);
    	cal.set(cal.DATE, cal.get(cal.DATE) + (int)GLB.TOTAL);
    	dt = cal.getTime();

    	sdf.applyPattern("ddMMMyy");
    	return(sdf.format(dt).toUpperCase());
    }
    catch(Exception e)
    {
        GLB.STATUS = "*****";
    	ShowErrorMsg (e, "DCToAlpha");
    	return("");
    }
}

/* DCToDate */
public String DCToDate()
    throws Exception
{
    try
    {
        GLB.STATUS = "";
	    Date dt = new Date();
    	Calendar cal = Calendar.getInstance();

    	SimpleDateFormat sdf = new SimpleDateFormat();

    	sdf.applyPattern("dd/MM/yyyy");
    	dt = sdf.parse("01/01/" + format(GLB.BASE,"9999"));

    	cal.setTime(dt);
    	cal.set(cal.DATE, cal.get(cal.DATE) + (int)GLB.TOTAL);
    	dt = cal.getTime();

        if (GLB.DCTYPE.equals("UK") == true)
        {   sdf.applyPattern("ddMMyy");
        }
        else if (GLB.DCTYPE.equals("US") == true)
        {   sdf.applyPattern("MMddyy");
        }
        else if (GLB.DCTYPE.equals("IN") == true)
        {   sdf.applyPattern("yyMMdd");
        }
        else
        {   sdf.applyPattern("ddMMyy");
        }
    	return(sdf.format(dt));
    }
    catch(Exception e)
    {   GLB.STATUS = "*****";
    	ShowErrorMsg (e, "DCToDate");
    	return("");
    }
}

/* DCToDate */
public String DCToDate(int pLength)
    throws Exception
{
    try
    {
        GLB.STATUS = "";
        Date dt = new Date();
        Calendar cal = Calendar.getInstance();

        SimpleDateFormat sdf = new SimpleDateFormat();

        sdf.applyPattern("dd/MM/yyyy");
        dt = sdf.parse("01/01/" + format(GLB.BASE,"9999"));

        cal.setTime(dt);
        cal.set(cal.DATE, cal.get(cal.DATE) + (int)GLB.TOTAL);
        dt = cal.getTime();

        if (pLength==7)
        {   if (GLB.DCTYPE.equals("UK") == true)
            {   sdf.applyPattern("ddMMMyy");
            }
            else if (GLB.DCTYPE.equals("US") == true)
            {   sdf.applyPattern("MMMddyy");
            }
            else if (GLB.DCTYPE.equals("IN") == true)
            {   sdf.applyPattern("yyMMMdd");
            }
            else
            {   sdf.applyPattern("ddMMMyy");
            }
        }
        else if (pLength==8)
        {   if (GLB.DCTYPE.equals("UK") == true)
            {   sdf.applyPattern("ddMMyyyy");
            }
            else if (GLB.DCTYPE.equals("US") == true)
            {   sdf.applyPattern("MMddyyyy");
            }
            else if (GLB.DCTYPE.equals("IN") == true)
            {   sdf.applyPattern("yyyyMMdd");
            }
            else
            {   sdf.applyPattern("ddMMyyyy");
            }
        }
        else if (pLength==9)
        {   if (GLB.DCTYPE.equals("UK") == true)
            {   sdf.applyPattern("ddMMMyyyy");
            }
            else if (GLB.DCTYPE.equals("US") == true)
            {   sdf.applyPattern("MMMddyyyy");
            }
            else if (GLB.DCTYPE.equals("IN") == true)
            {   sdf.applyPattern("yyyyMMMdd");
            }
            else
            {   sdf.applyPattern("ddMMMyyyy");
            }
        }
        else
        {   if (GLB.DCTYPE.equals("UK") == true)
            {   sdf.applyPattern("ddMMyy");
            }
            else if (GLB.DCTYPE.equals("US") == true)
            {   sdf.applyPattern("MMddyy");
            }
            else if (GLB.DCTYPE.equals("IN") == true)
            {   sdf.applyPattern("yyMMdd");
            }
            else
            {   sdf.applyPattern("ddMMyy");
            }
        }
        return(sdf.format(dt));
    }
    catch(Exception e)
    {   GLB.STATUS = "*****";
        ShowErrorMsg (e, "DCToDate");
        return("");
    }
}

/* DCToDayNumber */
public void DCToDayNumber(String DCDate)
{
    String pCentury;
    double DCDateyy = 0, DCDateccyy = 0;
    SimpleDateFormat sdf = new SimpleDateFormat();

    try
    {
        GLB.STATUS = "";
    	Date dt  = new Date();


        if ((DCDate.length() != 6) && (DCDate.length() != 7) && (DCDate.length() != 8))
        {

            throw(new Exception("Invalid Date"));
        }

        if (GLB.DCTYPE.equals("UK") == true)
        {
            if (DCDate.length() == 6)
            {
                DCDate = format(cDbl(DCDate), "999999");
                pCentury = validateDate (DCDate, "DDMMYY");

                if (GLB.STATUS.equals("*****") == false)
                {
                    sdf.applyPattern("ddMMyyyy");
                    dt = sdf.parse(DCDate.substring(0,4) + pCentury + DCDate.substring(4,6));

                    sdf.applyPattern("yyyy");
                    DCDateccyy = Double.valueOf(sdf.format(dt)).doubleValue();
                    GLB.TOTAL = ( (DCDateccyy - GLB.BASE) * 365 );
                    for (double i = GLB.BASE; i < DCDateccyy; i++)
                    {
                        if ((i % 400)==0)
                        {
                            GLB.TOTAL++;
                        }
                        else if((i % 4)==0)
                        {
                            if (!((i % 100)==0))
                            {
                                GLB.TOTAL++;
                            }
                        }
             	    }
                    sdf.applyPattern("DDD");
                    GLB.TOTAL = GLB.TOTAL + Double.valueOf(sdf.format(dt)).doubleValue() - 1;
                }
            }
            else if (DCDate.length() == 7)
            {
                pCentury = validateDate (DCDate, "DDMMMYY");

                if (GLB.STATUS.equals("*****") == false)
                {
                    sdf.applyPattern("ddMMMyyyy");
                    dt = sdf.parse(DCDate.substring(0,5) + pCentury + DCDate.substring(5,7));

                    sdf.applyPattern("yyyy");
                    DCDateccyy = Double.valueOf(sdf.format(dt)).doubleValue();
                    GLB.TOTAL = ( (DCDateccyy - GLB.BASE) * 365 );
                    for (double i = GLB.BASE; i < DCDateccyy; i++)
                    {
                        if ((i % 400)==0)
                        {
                            GLB.TOTAL++;
                        }
                        else if((i % 4)==0)
                        {
                            if (!((i % 100)==0))
                            {
                                GLB.TOTAL++;
                            }
                        }
                    }
                    sdf.applyPattern("DDD");
                    GLB.TOTAL = GLB.TOTAL + Double.valueOf(sdf.format(dt)).doubleValue() - 1;
                }
            }
       	    else
       	    {
       	        DCDate = format(cDbl(DCDate), "99999999");
                pCentury = validateDate (DCDate , "DDMMCCYY");

                if (GLB.STATUS.equals("*****") == false)
                {
                    sdf.applyPattern("ddMMyyyy");
                    dt = sdf.parse(DCDate);
                	sdf.applyPattern("yyyy");
                    DCDateccyy = Double.valueOf(sdf.format(dt)).doubleValue();
                    GLB.TOTAL = ( (DCDateccyy - GLB.BASE) * 365 );
                    for (double i = GLB.BASE; i < DCDateccyy; i++)
                    {
                        if ((i % 400)==0)
                        {
                            GLB.TOTAL++;
                        }
                        else if((i % 4)==0)
                        {
                            if (!((i % 100)==0))
                            {
                                GLB.TOTAL++;
                            }
                        }
                    }
                    sdf.applyPattern("DDD");
                    GLB.TOTAL = GLB.TOTAL + Double.valueOf(sdf.format(dt)).doubleValue() - 1;
                }
       	    }
        }

    	if (GLB.DCTYPE.equals("US") == true)
    	{
            if (DCDate.length() == 6)
            {
                DCDate = format(cDbl(DCDate), "999999");
                pCentury = validateDate (DCDate , "MMDDYY");

            	if (GLB.STATUS.equals("*****") == false)
                {
                    sdf.applyPattern("MMddyyyy");
                    dt = sdf.parse(DCDate.substring(0,4) + pCentury + DCDate.substring(4,6));

                    sdf.applyPattern("yyyy");
                    DCDateccyy = Double.valueOf(sdf.format(dt)).doubleValue();
                    GLB.TOTAL = ( (DCDateccyy - GLB.BASE) * 365 );
                    for (double i = GLB.BASE; i < DCDateccyy; i++)
                    {
                        if ((i % 400)==0)
                        {
                            GLB.TOTAL++;
                        }
                        else if((i % 4)==0)
                        {
                            if (!((i % 100)==0))
                            {
                                GLB.TOTAL++;
                            }
                        }
                    }
                    sdf.applyPattern("DDD");
                    GLB.TOTAL = GLB.TOTAL + Double.valueOf(sdf.format(dt)).doubleValue() - 1;
                }
       	    }
       	    else if (DCDate.length() == 7)
            {
                pCentury = validateDate (DCDate , "MMMDDYY");

                if (GLB.STATUS.equals("*****") == false)
                {
                    sdf.applyPattern("MMMddyyyy");
                    dt = sdf.parse(DCDate.substring(0,5) + pCentury + DCDate.substring(5,7));

                    sdf.applyPattern("yyyy");
                    DCDateccyy = Double.valueOf(sdf.format(dt)).doubleValue();
                    GLB.TOTAL = ( (DCDateccyy - GLB.BASE) * 365 );
                    for (double i = GLB.BASE; i < DCDateccyy; i++)
                    {
                        if ((i % 400)==0)
                        {
                            GLB.TOTAL++;
                        }
                        else if((i % 4)==0)
                        {
                            if (!((i % 100)==0))
                            {
                                GLB.TOTAL++;
                            }
                        }
                    }
                    sdf.applyPattern("DDD");
                    GLB.TOTAL = GLB.TOTAL + Double.valueOf(sdf.format(dt)).doubleValue() - 1;
                }
            }

       	    else
       	    {
                DCDate = format(cDbl(DCDate), "99999999");
                pCentury = validateDate (DCDate , "MMDDCCYY");

                if (GLB.STATUS.equals("*****") == false)
                {
                    sdf.applyPattern("MMddyyyy");
                    dt = sdf.parse(DCDate);
                	sdf.applyPattern("yyyy");
                    DCDateccyy = Double.valueOf(sdf.format(dt)).doubleValue();
                    GLB.TOTAL = ( (DCDateccyy - GLB.BASE) * 365 );
                    for (double i = GLB.BASE; i < DCDateccyy; i++)
                    {
                        if ((i % 400)==0)
                        {
                            GLB.TOTAL++;
                        }
                        else if((i % 4)==0)
                        {
                            if (!((i % 100)==0))
                            {
                                GLB.TOTAL++;
                            }
                        }
                    }
                    sdf.applyPattern("DDD");
                    GLB.TOTAL = GLB.TOTAL + Double.valueOf(sdf.format(dt)).doubleValue() - 1;
                }
       	    }
        }
        if (GLB.DCTYPE.equals("IN") == true)
        {
            if (DCDate.length() == 6)
            {
                DCDate = format(cDbl(DCDate), "999999");
                pCentury = validateDate (DCDate , "YYMMDD");

                if (GLB.STATUS.equals("*****") == false)
                {
                    sdf.applyPattern("yyyyMMdd");
                    dt = sdf.parse(pCentury + DCDate.substring(0,2) + DCDate.substring(2,6));
                    sdf.applyPattern("yyyy");
                    DCDateccyy = Double.valueOf(sdf.format(dt)).doubleValue();
                    GLB.TOTAL = ((DCDateccyy - GLB.BASE) * 365 );
                    for (double i = GLB.BASE; i < DCDateccyy; i++)
                    {
                        if ((i % 400)==0)
                        {
                            GLB.TOTAL++;
                        }
                        else if((i % 4)==0)
                        {
                            if (!((i % 100)==0))
                            {
                                GLB.TOTAL++;
                            }
                        }
                    }
                    sdf.applyPattern("DDD");
                    GLB.TOTAL = GLB.TOTAL + Double.valueOf(sdf.format(dt)).doubleValue() - 1;
                }
            }

            else if (DCDate.length() == 7)
            {
                pCentury = validateDate (DCDate , "YYMMMDD");

                if (GLB.STATUS.equals("*****") == false)
                {
                    sdf.applyPattern("yyyyMMMdd");
                    dt = sdf.parse(pCentury + DCDate);
                    sdf.applyPattern("yyyy");
                    DCDateccyy = Double.valueOf(sdf.format(dt)).doubleValue();
                    GLB.TOTAL = ((DCDateccyy - GLB.BASE) * 365 );
                    for (double i = GLB.BASE; i < DCDateccyy; i++)
                    {
                        if ((i % 400)==0)
                        {
                            GLB.TOTAL++;
                        }
                        else if((i % 4)==0)
                        {
                            if (!((i % 100)==0))
                            {
                                GLB.TOTAL++;
                            }
                        }
                    }
                    sdf.applyPattern("DDD");
                    GLB.TOTAL = GLB.TOTAL + Double.valueOf(sdf.format(dt)).doubleValue() - 1;
                }
            }
            //
            else
            {
                DCDate = format(cDbl(DCDate), "99999999");
                pCentury = validateDate (DCDate, "CCYYMMDD");

                if (GLB.STATUS.equals("*****") == false)
                {
                   sdf.applyPattern("yyyyMMdd");
                   dt = sdf.parse(DCDate);
                   sdf.applyPattern("yyyy");
                   DCDateccyy = Double.valueOf(sdf.format(dt)).doubleValue();
                   GLB.TOTAL = ( (DCDateccyy - GLB.BASE) * 365 );
                   for (double i = GLB.BASE; i < DCDateccyy; i++)
                   {
                        if ((i % 400)==0)
                        {
                            GLB.TOTAL++;
                        }
                        else if((i % 4)==0)
                        {
                            if (!((i % 100)==0))
                            {
                                GLB.TOTAL++;
                            }
                        }
                   }
                   sdf.applyPattern("DDD");
                   GLB.TOTAL = GLB.TOTAL + Double.valueOf(sdf.format(dt)).doubleValue() - 1;
                }
            }
        }
    }
    catch (Exception e)
    {
       GLB.STATUS = "*****";

       Date dtAtual = new Date();
       sdf.applyPattern("yyyy");
       DCDateccyy = Double.valueOf( sdf.format(dtAtual) ).doubleValue();

       GLB.TOTAL = ( (DCDateccyy - GLB.BASE) * 365 );

       for (double i = GLB.BASE; i < DCDateccyy; i++)
       {
            if ((i % 400)==0)
            {
                GLB.TOTAL++;
            }
            else if((i % 4)==0)
            {
                if (!((i % 100)==0))
                {
                    GLB.TOTAL++;
                }
            }
       }

       sdf.applyPattern("DDD");
       GLB.TOTAL = GLB.TOTAL + Double.valueOf(sdf.format(dtAtual)).doubleValue() - 1;
    }
}

//FileExists
public boolean  FileExists(String pFileName)
    throws Exception, IOException
{
    try
    {
        File wFile;
        wFile = new File(pFileName);
        return(wFile.exists());

    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"FileCopy");
        return(false);
    }
}

/* ReplaceQuotes */
public String ReplaceQuotes(String pStr)
    throws Exception
{
    try
    {
    	if (pStr.indexOf("'") == -1)
    	{
       	return(pStr);
    	}
    	String auxStr = "";
    	for (int i = 0; i < pStr.length(); i++)
    	{
    		if (pStr.charAt(i) == '\'')
        	{
           		auxStr = auxStr.concat("''");
        	}
        	else
        	{
           		auxStr = auxStr.concat(pStr.substring(i, i + 1));
        	}
    	}
    	return(auxStr);
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "ReplaceQuotes");
        return(pStr);
    }
}

/* RollBackTransaction */
public void RollBackTransaction()
    throws Exception
{
    try
    {
    	GLB.CONNECTION.rollback();
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "RollBackTransaction");
    }
}

/* RollBackFatalError */
public void RollBackTransaction(String msg)
{
    if ( GLB.ROLLBACK == false )
    {
        GLB.ROLLBACK = true;

        // Rollback Transaction
        try
        {
          GLB.CONNECTION.rollback();
        }
        catch (Exception e)
        {
            // do not  throw Exception
        }

        // Audit Error Message
        try
        {
            AuditERR (msg);
        }
        catch (Exception e)
        {
            // do not  throw Exception
        }
    }
}


/* SendEMail */
public void SendEMail()
    throws Exception
{
    Properties props = new Properties();
    props.put ("mail.smtp.host",  GLB.EMAILHOST.trim() );
    props.put ("mail.debug"    , "false");

    //Cria uma MailSession
    Session session = Session.getDefaultInstance(props, null);
    session.setDebug(false);

    try
    {
    	//Cria a Mensagem
        Message msg = new MimeMessage(session);

        //From
        msg.setFrom(new InternetAddress(GLB.EMAILFROM));

        //Subject
        msg.setSubject(GLB.EMAILSUBJECT);

        //Data
        msg.setSentDate(new java.util.Date());

        //To
        String arrayTo[] = splitElements(GLB.EMAILTO);
        if (arrayTo != null)
        {
        	InternetAddress address[] = new InternetAddress[arrayTo.length];
            for (int i=0; i < arrayTo.length; i++)
            {
            	address[i] = new InternetAddress(arrayTo[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, address);
        }

     // Cc
        String arrayCc[] = splitElements(GLB.EMAILCC);
        if (arrayCc != null)
        {
        	InternetAddress addressCc[] = new InternetAddress[arrayCc.length];
            for (int i=0; i < arrayCc.length; i++)
            {
                addressCc[i] = new InternetAddress(arrayCc[i]);
            }
            msg.setRecipients(Message.RecipientType.CC, addressCc);
        }

        String arrayAt[] = splitElements(GLB.EMAILATTACH);

     // Com Attachments
        if (arrayAt != null)
        {
        	Multipart mp = new MimeMultipart("alternative");

        	MimeBodyPart mbp1 = new MimeBodyPart();
            mbp1.setText(GLB.EMAILTEXT);
            mbp1.setHeader("Content-type","text/html");
            mp.addBodyPart(mbp1);
            FileDataSource fds;
            MimeBodyPart mbp2[] = new MimeBodyPart[arrayAt.length];

            for (int i=0; i < arrayAt.length; i++)
            {
            	fds = new FileDataSource(arrayAt[i]);


                mbp2[i] = new MimeBodyPart();
                mbp2[i].setDataHandler(new DataHandler(fds));
                mbp2[i].setFileName(fds.getName());
                mbp2[i].setHeader ("Content-ID", "imageID" + i);

                mp.addBodyPart(mbp2[i]);
            }
            msg.setContent(mp);
        }
        else // Sem Attachments
        {
            msg.setText(GLB.EMAILTEXT);
            msg.setHeader("Content-type","text/html");
        }

        Transport.send(msg);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "SendEMail");
    }
}

/* ShowDataMsg */
public void ShowDataMsg(SQLException e, String routineName, String sql)
    throws Exception
{
    if (routineErrorMsg.equals("") == true)
    {
	StringWriter strwrt = new StringWriter();
       	PrintWriter out = new PrintWriter(strwrt);
       	e.printStackTrace(out);
       	routineErrorMsg = strwrt.toString();

       	if (routineErrorMsg == null)
       	{
           routineErrorMsg = "Null";
       	}

       	routineErrorMsg  = routineErrorMsg.trim() + " -- " + sql;
    }

    if (routineErrorName.equals("") == true)
    {
    	routineErrorName = routineName;
    }
    else
    {
    	routineErrorName = routineErrorName + ", " + routineName;
    }
    RollBackTransaction(routineErrorMsg);
    throw e;
}

/* ShowErrorMsg */
public void ShowErrorMsg(Exception e, String routineName)
    throws Exception
{
    if (routineErrorMsg.equals("") == true)
    {
	StringWriter strwrt = new StringWriter();
       	PrintWriter out = new PrintWriter(strwrt);
       	e.printStackTrace(out);
       	routineErrorMsg = strwrt.toString();

       	if (routineErrorMsg == null)
       	{
           routineErrorMsg = "Null";
       	}
    }

    if (routineErrorName.equals("") == true)
    {
    	routineErrorName = routineName;
    }
    else
    {
    	routineErrorName = routineErrorName + ", " + routineName;
    }
    RollBackTransaction(routineErrorMsg);
    throw e;
}


/* ShowErrorMsg */
public void ShowErrorMsg(Exception e)
    throws Exception
{
	 String routineName ="";
	 if ((e.getStackTrace() != null) && (e.getStackTrace().length>=1)) {
	        routineName = e.getStackTrace()[0].getMethodName();
	 } 
	 
    if (routineErrorMsg.equals("") == true)
    {
	StringWriter strwrt = new StringWriter();
       	PrintWriter out = new PrintWriter(strwrt);
       	e.printStackTrace(out);
       	routineErrorMsg = strwrt.toString();

       	if (routineErrorMsg == null)
       	{
           routineErrorMsg = "Null";
       	}
    }

    if (routineErrorName.equals("") == true)
    {
    	routineErrorName = routineName;
    }
    else
    {
    	routineErrorName = routineErrorName + ", " + routineName;
    }
    RollBackTransaction(routineErrorMsg);
    throw e;
}

/* ShowOracleMsg */

public void ShowOracleMsg(SQLException e, String routineName, String sql)
    throws Exception
{
    int msgIndex;
    msgIndex = e.getMessage().indexOf("ORA-");
    if (msgIndex != -1)
    {   GLB.DBCODE = cDbl(e.getMessage().substring(msgIndex + 3,msgIndex + 9));
    }

    if (GLB.DBCODE == -30006)  // resource busy; acquire with WAIT timeout expired
    {
        lockTimeoutMsg();
    }
    else if (GLB.DBCODE == -2291)
    {
        GLB.STATUS = "*****";
        abort("TRANSACTION FAILED: Integrity constraint violated - parent key not found.");
    }
    else if (GLB.DBCODE == -2292)
    {
        GLB.STATUS = "*****";
        abort("TRANSACTION FAILED: Integrity constraint violated - child key found.");
    }
    else
    {
        if (GLB.DBCODE == -00060D)  // deadlock detected while waiting for resource
        {
            lockDeadlockMsg();
        }
        else
        {
            ShowDataMsg(e,routineName, sql );
        }
    }
}

/* lockTimeoutMsg */
public void lockTimeoutMsg()
  throws Exception
{
    abort("TRANSACTION FAILED: Database Lock Timeout");
}

/* lockDeadlockMsg */
public void lockDeadlockMsg()
  throws Exception
{
    abort("TRANSACTION FAILED: Database deadlock detected");
}

/* cDbl */
public double cDbl (String pText)
 throws Exception
{
    try
    {
        // Retira ,
        int positionComman = pText.indexOf(",");
        if (positionComman != -1)
        {
            pText = pText.substring(0, positionComman) + pText.substring(positionComman + 1);
        }
        if (isNumeric(pText) == false)
    	{
    		return(0.0);
    	}
        return(Double.valueOf(pText).doubleValue());
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "cDbl");
        return (0.0);
    }
}

/* cDbl */
public double cDbl (BigDecimal pBigDec)
 throws Exception
{
    try
    {
        return(pBigDec.doubleValue());
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "cDbl");
        return (0.0);
    }
}

/* cBigDec --> BigDecimal */
public BigDecimal cBigDec (String pText)
 throws Exception
{
    try
    {   pText = pText.trim();
        // Retira ,
        int positionComman = pText.indexOf(",");

        if (positionComman != -1)
        {
            pText = pText.substring(0, positionComman) + pText.substring(positionComman + 1);
        }

        if (isNumeric(pText) == false)
    	{
    		return(new BigDecimal(0));
    	}

        return(new BigDecimal(pText));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "cBigDec");
        return (new BigDecimal(0));
    }
}



/* cDec --> BigDecimal */
public BigDecimal cDec (String pText)
 throws Exception
{
    try
    {
        pText = pText.trim();
        // Retira ,
        int positionComman = pText.indexOf(",");

        if (positionComman != -1)
        {
            pText = pText.substring(0, positionComman) + pText.substring(positionComman + 1);
        }

        if (isNumeric(pText) == false)
        {
            return(new BigDecimal(0));
        }

        return(new BigDecimal(pText));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "cDec");
        return (new BigDecimal(0));
    }
}

/* cDec --> BigDecimal */
public BigDecimal cDec (double pNumber)
 throws Exception
{
    try
    {
        return(new BigDecimal((new Double(pNumber)).toString()));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "cDec");
        return (new BigDecimal(0));
    }
}

/* cDec --> BigDecimal */
public BigDecimal cDec (BigDecimal pNumber)
 throws Exception
{
    try
    {
        return(pNumber);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "cDec");
        return (new BigDecimal(0));
    }
}

/* convertTypeMonth */
private int convertTypeMonth (String pMonth) throws Exception
{
    try{/*works in english, portuguese and spanish*/
        if( pMonth.length() > 3 )
        {
            return 0;
        }

        if(pMonth.equalsIgnoreCase("jan")|| pMonth.equalsIgnoreCase("ene") )
            return 1;
        else if(pMonth.equalsIgnoreCase("fev") || pMonth.equalsIgnoreCase("feb") )
            return 2;
        else if(pMonth.equalsIgnoreCase("mar"))
            return 3;
        else if(pMonth.equalsIgnoreCase("abr")|| pMonth.equalsIgnoreCase("apr") )
            return 4;
        else if(pMonth.equalsIgnoreCase("mai")|| pMonth.equalsIgnoreCase("may") )
            return 5;
        else if(pMonth.equalsIgnoreCase("jun"))
            return 6;
        else if(pMonth.equalsIgnoreCase("jul"))
            return 7;
        else if(pMonth.equalsIgnoreCase("ago")|| pMonth.equalsIgnoreCase("aug") )
            return 8;
        else if(pMonth.equalsIgnoreCase("set")|| pMonth.equalsIgnoreCase("sep") )
            return 9;
        else if(pMonth.equalsIgnoreCase("out")|| pMonth.equalsIgnoreCase("oct") )
            return 10;
        else if(pMonth.equalsIgnoreCase("nov")|| pMonth.equalsIgnoreCase("nov") )
            return 11;
        else if(pMonth.equalsIgnoreCase("dez")|| pMonth.equalsIgnoreCase("dec") || pMonth.equalsIgnoreCase("dic") )
            return 12;

    return 0;
    }
    catch(Exception e)
    {
        return 0;
    }
}

/* checkDay */
private String checkDay(int day)
 throws Exception
{
    try
    {
        if ((day <= 0) || (day > 31))
        {
	   return("*****");
    	}

    	return("");
    }
    catch(Exception e)
    {
        return("*****");
    }
}

/* checkDayAndMonth */
private String checkDayAndMonth(int day, int month)
   throws Exception
{
   try
   {
        if ((day <= 0) || (day > 31))
        {
           return("*****");
    	}
    	if ((month <= 0) || (month > 12))
    	{
           return("*****");
    	}

    	return("");
    }
    catch(Exception e)
    {
        return("*****");
    }
}

/* checkYear */
private String checkYear(int pYear)
   throws Exception
{
   try
   {
       if (pYear < GLB.BASE)
       {
          return("*****");
       }

       return("");
   }
   catch(Exception e)
   {
       return("*****");
   }

}

/* checkDate */
private String checkDate(int pDay, int pMonth, int pYear)
   throws Exception
{
   try
   {

        boolean bissexto = false;
        if (pYear < GLB.BASE)
	    {
           return("*****");
	    }

    	if ((pMonth <= 0) || (pMonth > 12))
    	{
           return("*****");
    	}

    	if ((pDay <= 0) || (pDay > 31))
	    {
           return("*****");
    	}


        if(pMonth == 2)
        {
            //verifica se é bissexto
            if(pYear%100 == 0)
            {
                if(pYear % 400 == 0)
                    bissexto = true;
            }
            else
            {
                if(pYear % 4 == 0)
                    bissexto = true;
            }

            if ((pDay == 29) && bissexto)
            {
                return ("");
            }
            else if ((pDay <= 0) || (pDay > 28))
            {
                return("*****");
            }
        }
        else if((pMonth == 1) || (pMonth == 3) || (pMonth == 5) || (pMonth == 7) &&
                (pMonth == 8) || (pMonth == 10) || (pMonth == 12))
        {
            if ((pDay <= 0) || (pDay > 31))
            {
                return("*****");
            }
        }
        else if((pMonth == 4) || (pMonth == 6) || (pMonth == 9) || (pMonth == 11))
        {
            if ((pDay <= 0) || (pDay > 30))
            {
                return("*****");
            }
        }

        return ("");
    }
    catch(Exception e)
    {
        return ("*****");
    }
}

/* checkDate */
private String checkDate(int pDay, String pMonth, int pYear)
	throws Exception
{
	try
	{	if (pYear < GLB.BASE)
		{	return("*****");
		}

    	if ((pDay <= 0) || (pDay > 31))
		{	return("*****");
    	}
		return("");
	}
	catch(Exception e)
    {
        return("*****");
    }
}

/* cStr */
public String cStr (double pNumber)
    throws Exception
{
    try
    {
        String str = new BigDecimal(new Double(pNumber).toString()).toPlainString();
	if (str.substring (str.length()-2).equals(".0")==true)
	{
           str = str.substring(0,str.length()-2);
	}
	return(str);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "cStr");
    	return("");
    }
}

/* cStr --> BigDecimal */
public String cStr (BigDecimal pNumber)
    throws Exception
{
    try
    {
        String str = pNumber.toPlainString();

        if (str.length() >= 2)
        {
  	   if (str.substring (str.length()-2).equals(".0")==true)
	   {
              str = str.substring(0,str.length()-2);
	   }
        }

	return(str);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "cStr");
    	return("");
    }
}

/* currentDate */
public void currentDate()
    throws Exception
{
    try
    {
        Date auxToday = new Date();
        SimpleDateFormat auxFormat = new SimpleDateFormat();

        GLB.TODAY = "";
        INPUT_DATE = "";
        GLB.DATE = "";
        GLB.DC_DAYNUM  = 0;

        auxFormat.applyPattern("ddMMyy");
        GLB.DC_DDMMYY = Double.valueOf(auxFormat.format(auxToday)).doubleValue();

        auxFormat.applyPattern("dd-MM-yy");
        GLB.DC_DD_MM_YY = auxFormat.format(auxToday);

        auxFormat.applyPattern("yyMMdd");
        GLB.DC_YYMMDD = Double.valueOf(auxFormat.format(auxToday)).doubleValue();

        if ((rTrim(GLB.DCTYPE.toUpperCase())).equals("UK"))
        {
           auxFormat.applyPattern("ddMMMyy");
           INPUT_DATE = (auxFormat.format(auxToday)).toUpperCase();
        }

        if ((rTrim(GLB.DCTYPE.toUpperCase())).equals("US"))
        {
           auxFormat.applyPattern("MMMddyy");
           INPUT_DATE = (auxFormat.format(auxToday)).toUpperCase();
        }

        if ((rTrim(GLB.DCTYPE.toUpperCase())).equals("IN"))
        {
           auxFormat.applyPattern("yyMMMdd");
           INPUT_DATE = (auxFormat.format(auxToday)).toUpperCase();
        }

        if ((rTrim(GLB.DCTYPE.toUpperCase())).equals("UK"))
        {
           auxFormat.applyPattern("ddMMMyy");
           GLB.TODAY = (auxFormat.format(auxToday)).toUpperCase();
        }

        if ((rTrim(GLB.DCTYPE.toUpperCase())).equals("US"))
        {
           auxFormat.applyPattern("MMMddyy");
           GLB.TODAY = (auxFormat.format(auxToday)).toUpperCase();
        }

        if ((rTrim(GLB.DCTYPE.toUpperCase())).equals("IN"))
        {
           auxFormat.applyPattern("yyMMMdd");
           GLB.TODAY = (auxFormat.format(auxToday)).toUpperCase();
        }

        TODAY = GLB.TODAY;

        auxFormat.applyPattern("dd");
        GLB.TODAYS_DAY = Double.valueOf(auxFormat.format(auxToday)).doubleValue();
        TODAYS_DAY = GLB.TODAYS_DAY;

        auxFormat.applyPattern("MMM");
        GLB.TODAYS_MONTH = auxFormat.format(auxToday);
        TODAYS_MONTH = GLB.TODAYS_MONTH;

        auxFormat.applyPattern("yy");
        GLB.TODAYS_YEAR = Double.valueOf(auxFormat.format(auxToday)).doubleValue();
        TODAYS_YEAR = GLB.TODAYS_YEAR;

        auxFormat.applyPattern("MMddyy");
        GLB.TODAYS_DATE_NUM = Double.valueOf(auxFormat.format(auxToday)).doubleValue();
        TODAYS_DATE_NUM = GLB.TODAYS_DATE_NUM;

        auxFormat.applyPattern("MM");
        GLB.TODAYS_MONTH_NUM = Double.valueOf(auxFormat.format(auxToday)).doubleValue();
        TODAYS_MONTH_NUM = GLB.TODAYS_MONTH_NUM;

        if ((rTrim(GLB.DCTYPE.toUpperCase())).equals("UK"))
        {
           auxFormat.applyPattern("ddMMMyy");
           GLB.DATE = (auxFormat.format(auxToday)).toUpperCase();
        }

        if ((rTrim(GLB.DCTYPE.toUpperCase())).equals("US"))
        {
           auxFormat.applyPattern("MMMddyy");
           GLB.DATE = (auxFormat.format(auxToday)).toUpperCase();
        }

        if ((rTrim(GLB.DCTYPE.toUpperCase())).equals("IN"))
        {
           auxFormat.applyPattern("yyMMMdd");
           GLB.DATE = (auxFormat.format(auxToday)).toUpperCase();
        }

        auxFormat.applyPattern("yyyy");
        GLB.DC_DAYNUM = ( (Double.valueOf(auxFormat.format(auxToday)).doubleValue() - GLB.BASE) * 365);
        for (double i = GLB.BASE; i < Double.valueOf(auxFormat.format(auxToday)).doubleValue(); i++)
        {
            if ((i % 400)==0)
            {
                GLB.DC_DAYNUM++;
            }
            else if((i % 4)==0)
            {
                if (!((i % 100)==0))
                {
                    GLB.DC_DAYNUM++;
                }
            }
        }
        auxFormat.applyPattern("DDD");
        GLB.DC_DAYNUM = GLB.DC_DAYNUM + Double.valueOf(auxFormat.format(auxToday)).doubleValue() - 1;

        auxFormat.applyPattern("dd/MM/yy");
        GLB.DC_DD_MM_YY = auxFormat.format(auxToday);

        auxFormat.applyPattern("ddMMMyy");
        GLB.DC_DDMMMYY = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("dd MMM yy");
        GLB.DC_DD_MMM_YY = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("ddMMyy");
        GLB.DC_DDMMYY = Double.valueOf(auxFormat.format(auxToday)).doubleValue();

        auxFormat.applyPattern("MM/dd/yy");
        GLB.DC_MM_DD_YY = auxFormat.format(auxToday);

        auxFormat.applyPattern("MMddyy");
        GLB.DC_MMDDYY = Double.valueOf(auxFormat.format(auxToday)).doubleValue();

        auxFormat.applyPattern("MMM dd yy");
        GLB.DC_MMM_DD_YY = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("MMMddyy");
        GLB.DC_MMMDDYY = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("EEEEEEEEEEEEEEEEEEEEEEE");
        GLB.DC_TODAY = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("dd MMMMM yyyy");
        GLB.DC_UK_ALPHA = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("MMMMM dd yyyy");
        GLB.DC_US_ALPHA = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("yyyy MMMMM dd");
        GLB.DC_IN_ALPHA = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("ww");
        GLB.DC_WEEKNO = Double.valueOf(auxFormat.format(auxToday)).doubleValue();

        auxFormat.applyPattern("yyDDD");
        GLB.DC_YYDDD = Double.valueOf(auxFormat.format(auxToday)).doubleValue();

        auxFormat.applyPattern("yyMMdd");
        GLB.DC_YYMMDD = Double.valueOf(auxFormat.format(auxToday)).doubleValue();

        auxFormat.applyPattern("yyMMMdd");
        GLB.DC_YYMMMDD = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("yy/MM/dd");
        GLB.DC_YY_MM_DD = auxFormat.format(auxToday);

        auxFormat.applyPattern("yy MMM dd");
        GLB.DC_YY_MMM_DD = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("dd/MM/yyyy");
        GLB.DC_DD_MM_CCYY = auxFormat.format(auxToday);

        auxFormat.applyPattern("ddMMMyyyy");
        GLB.DC_DDMMMCCYY = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("dd MMM yyyy");
        GLB.DC_DD_MMM_CCYY = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("ddMMyyyy");
        GLB.DC_DDMMCCYY = Double.valueOf(auxFormat.format(auxToday)).doubleValue();

        auxFormat.applyPattern("MM/dd/yyyy");
        GLB.DC_MM_DD_CCYY = auxFormat.format(auxToday);

        auxFormat.applyPattern("MMddyyyy");
        GLB.DC_MMDDCCYY = Double.valueOf(auxFormat.format(auxToday)).doubleValue();

        auxFormat.applyPattern("MMM dd yyyy");
        GLB.DC_MMM_DD_CCYY = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("MMMddyyyy");
        GLB.DC_MMMDDCCYY = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("yyyyDDD");
        GLB.DC_CCYYDDD = Double.valueOf(auxFormat.format(auxToday)).doubleValue();

        auxFormat.applyPattern("yyyyMMdd");
        GLB.DC_CCYYMMDD = Double.valueOf(auxFormat.format(auxToday)).doubleValue();

        auxFormat.applyPattern("yyyyMMMdd");
        GLB.DC_CCYYMMMDD = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("yyyy/MM/dd");
        GLB.DC_CCYY_MM_DD = auxFormat.format(auxToday);

        auxFormat.applyPattern("yyyy MMM dd");
        GLB.DC_CCYY_MMM_DD = (auxFormat.format(auxToday)).toUpperCase();

        auxFormat.applyPattern("HHmmssSSS");
        GLB.TIME = cDbl(auxFormat.format(auxToday).substring(0,8));

        if (GLB.DCUPPERCASE.equals("TRUE") == true)
        {
            GLB.DC_CCYYMMMDD = GLB.DC_CCYYMMMDD.toUpperCase();
            GLB.DC_CCYY_MMM_DD = GLB.DC_CCYY_MMM_DD.toUpperCase();
            GLB.DC_DD_MMM_CCYY = GLB.DC_DD_MMM_CCYY.toUpperCase();
            GLB.DC_DDMMMCCYY = GLB.DC_DDMMMCCYY.toUpperCase();
            GLB.DC_DD_MMM_YY = GLB.DC_DD_MMM_YY.toUpperCase();
            GLB.DC_DDMMMYY = GLB.DC_DDMMMYY.toUpperCase();
            GLB.DC_IN_ALPHA = GLB.DC_IN_ALPHA.toUpperCase();
            GLB.DC_MMM_DD_YY = GLB.DC_MMM_DD_YY.toUpperCase();
            GLB.DC_MMM_DD_CCYY = GLB.DC_MMM_DD_CCYY.toUpperCase();
            GLB.DC_MMMDDYY = GLB.DC_MMMDDYY.toUpperCase();
            GLB.DC_MMMDDCCYY = GLB.DC_MMMDDCCYY.toUpperCase();
            GLB.DC_TODAY = GLB.DC_TODAY.toUpperCase();
            GLB.DC_UK_ALPHA = GLB.DC_UK_ALPHA.toUpperCase();
            GLB.DC_US_ALPHA = GLB.DC_US_ALPHA.toUpperCase();
            GLB.DC_YYMMMDD = GLB.DC_YYMMMDD.toUpperCase();
            GLB.DC_YY_MMM_DD = GLB.DC_YY_MMM_DD.toUpperCase();
            GLB.TODAY = GLB.TODAY.toUpperCase();
            GLB.TODAYS_MONTH = GLB.TODAYS_MONTH.toUpperCase();
            TODAY = TODAY.toUpperCase();
            TODAYS_MONTH = TODAYS_MONTH.toUpperCase();
            GLB.DATE = GLB.DATE.toUpperCase();
            INPUT_DATE = INPUT_DATE.toUpperCase();
        }
    }
    catch(Exception e)
    {
   		ShowErrorMsg (e,"currentDate");
    }
}


/* fix double */
public double fix (double pDouble, int pLength, int pDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
    	NumberFormat nf = NumberFormat.getInstance(new Locale("en","US"));
    	nf.setGroupingUsed(false);
    	nf.setMaximumIntegerDigits(pLength - pDecimals);

    	if (pRounded.equals("ROUNDED") == true)
    	{
       		nf.setMaximumFractionDigits(pDecimals);
    	}
    	else
    	{
       		float dec = (float)((pDouble - (long)pDouble) * Math.pow(10, pDecimals));
            pDouble = (long) (pDouble) * Math.pow(10, pDecimals);
            pDouble = pDouble + (long)dec;
            pDouble = (pDouble / Math.pow(10,pDecimals));
        }

    	pDouble = cDbl(nf.format(pDouble));
    	if (pSigned.equals("UNSIGNED") == true)
    	{
       		pDouble = Math.abs(pDouble);
    	}
    	return(pDouble);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "fix");
        return (0.0);
    }
}

/* fix String */
public String fix(String pString, int pLength)
    throws Exception
{
    try
    {
    	if (pString.length() > pLength)
    	{
            pString = pString.substring(0,pLength);
    	}

    	return(rTrim(pString));

	}

    catch (Exception e)
    {
    	ShowErrorMsg (e, "fix");
        return ("");
    }
}

/* format Date */
public String format (Date pDate, String pMask)
   throws Exception
{
   try
   {
        SimpleDateFormat   auxFormat;
    	double             DateNow;

    	auxFormat = new SimpleDateFormat(pMask);
    	return(auxFormat.format(pDate));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "format");
        return ("");
    }
}

/* format com sinal */
public String format(double pNumber, String pMask, boolean pSignal)
    throws Exception
{
    String resultStr ;
    resultStr = format (pNumber,pMask);

    if ((pSignal) && (pNumber >= 0))
    {
        resultStr = " " + resultStr;
    }
    return (resultStr);
}


public String format(BigDecimal pNumber, String pMask, boolean pSignal)
    throws Exception
{
    String resultStr;
    resultStr = format (pNumber,pMask);

    if ((pSignal) && (pNumber.compareTo(bigZeros) >= 0) )
    {
        resultStr = " " + resultStr;
    }
    return (resultStr);
}


/* format double */
public String format(double pNumber, String pMask)
    throws Exception
{
    try
    {
    	DecimalFormat myFormatter;
        String strNumber="0";
    	int numberLength;
    	int numberDecimals;
    	int count=0;
    	int index;

        // Format de um numero com mascara 999, ### ou ZZZ
        if ( (pMask.indexOf("9") != -1) ||
             (pMask.indexOf("#") != -1) ||
             (pMask.indexOf("Z") != -1) ||
             (pMask.indexOf("*") != -1) ) //formato de um numero
    	{
            index = pMask.indexOf(",");
            while (index != -1)
            {
                count++;
           	index = pMask.indexOf(",",index+1);
            }

            index = pMask.indexOf(".");
    	    if (index != -1)
            {
               numberDecimals = (pMask.substring(index+1)).length();
               numberLength = pMask.length() - count - 1;
            }
            else
            {
               numberDecimals = 0;
               numberLength = pMask.length() - count;
            }

            BigDecimal bigDec = new BigDecimal((new Double(pNumber)).toString());
            pNumber = newFix(bigDec, numberLength, numberDecimals,"SIGNED", "TRUNCATE");

            if (pMask.indexOf("9") != -1)
            {
               pMask = pMask.replace('9','0');
               myFormatter = new DecimalFormat(pMask, new DecimalFormatSymbols(new Locale("en","US")));
               strNumber = myFormatter.format(pNumber);
            }
            else if (pMask.indexOf("#") != -1)
            {
                myFormatter = new DecimalFormat(pMask, new DecimalFormatSymbols(new Locale("en","US")));
                strNumber = myFormatter.format(pNumber);
            }
            else if (pMask.indexOf("Z") != -1)
            {
                pMask = pMask.replace('Z','#');
                myFormatter = new DecimalFormat(pMask, new DecimalFormatSymbols(new Locale("en","US")));
                if (pMask.indexOf(".") != -1)
                {
                    myFormatter.setMinimumFractionDigits(pMask.length() - pMask.indexOf(".") - 1);
                }
                strNumber = myFormatter.format(pNumber);

                numberLength =  strNumber.length();

                if (numberLength <  pMask.length())
                {
              	   for (int i = 0; i < (pMask.length() - numberLength); i++)
              	   {
                  	strNumber = " " + strNumber;
              	   }
                }
             }

            else if (pMask.indexOf("*") != -1)
            {
                pMask = pMask.replace('*','#');
                myFormatter = new DecimalFormat(pMask, new DecimalFormatSymbols(new Locale("en","US")));
                if (pMask.indexOf(".") != -1)
                {
                    myFormatter.setMinimumFractionDigits(pMask.length() - pMask.indexOf(".") - 1);
                }
                strNumber = myFormatter.format(pNumber);

                numberLength =  strNumber.length();

                if (numberLength <  pMask.length())
                {
                   for (int i = 0; i < (pMask.length() - numberLength); i++)
                   {
                    strNumber = "*" + strNumber;
                   }
                }
             }

             if (GLB.DecimalChar != '.')
             {
           	for (int i = 0; i < strNumber.length(); i++)
           	{
             	    if (strNumber.substring(i,i+1).equals(",") == true)
               	    {
                  	strNumber = strNumber.substring(0,i) + "." + strNumber.substring(i+1);
               	    }
               	    else if (strNumber.substring(i,i+1).equals(".") == true)
               	    {
                  	strNumber = strNumber.substring(0,i) + "," + strNumber.substring(i+1);
               	    }
           	}
             }
             return(strNumber);
       }  //formato de uma data
       else if (pMask.equals("DD/MM/YY") == true)
       {
          myFormatter = new DecimalFormat("000000");
       	  pMask = myFormatter.format(pNumber);
       	  return(pMask.substring(0,2) + "/" + pMask.substring(2,4) + "/" +  pMask.substring(4));
       }
       else if (pMask.equals("DD/MM/CCYY") == true)
       {
          myFormatter = new DecimalFormat("00000000");
       	  pMask = myFormatter.format(pNumber);
       	  return(pMask.substring(0,2) + "/" + pMask.substring(2,4) + "/" +  pMask.substring(4));
       }
       else if (pMask.equals("CCYY/MM/DD") == true)
       {
          myFormatter = new DecimalFormat("00000000");
       	  pMask = myFormatter.format(pNumber);
       	  return(pMask.substring(0,4) + "/" + pMask.substring(4,6) + "/" +  pMask.substring(6));
       }
       else
       {
          return(cStr(pNumber));
       }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "format");
        return ("");
    }
}

/* format BigDecimal --> BigDecimal */
public String format(BigDecimal pNumber, String pMask)
    throws Exception
{
    try
    {
        DecimalFormat myFormatter;
        String strNumber="0";
        int numberLength;
        int numberDecimals;
        int count=0;
        int index;

        BigDecimal numOriginal;

        numOriginal  = pNumber;
        pNumber = pNumber.abs();

        // Format de um numero com mascara 999, ### ou ZZZ
        if ( (pMask.indexOf("9") != -1) ||
             (pMask.indexOf("#") != -1) ||
             (pMask.indexOf("Z") != -1) ||
             (pMask.indexOf("*") != -1)) //formato de um numero
        {
            index = pMask.indexOf(",");
            while (index != -1)
            {
                count++;
            index = pMask.indexOf(",",index+1);
            }

            index = pMask.indexOf(".");
            if (index != -1)
            {
               numberDecimals = (pMask.substring(index+1)).length();
               numberLength = pMask.length() - count - 1;
            }
            else
            {
               numberDecimals = 0;
               numberLength = pMask.length() - count;
            }

            pNumber = fixBigDec (pNumber, numberLength, numberDecimals,"SIGNED", "TRUNCATE");

            if (pMask.indexOf("#") != -1)
            {   String str="";
                strNumber = pNumber.toPlainString();

                index = strNumber.indexOf(".");
                if (index != -1)
                {   if ((strNumber.length() - index - 1) > numberDecimals)
                    {   strNumber = strNumber.substring(0, index + numberDecimals+1);
                    }
                }

                numberLength =  strNumber.length();

                int j=pMask.length()-1;

                for (int i = numberLength - 1; i >= 0; i--)
                {   if ((pMask.charAt(j) == '.') || (pMask.charAt(j) == ',') )
                    {   str =  pMask.charAt(j) + str ;
                        j--;
                    }
                    if (strNumber.charAt(i)!='.')
                    {    str= strNumber.charAt(i) + str;
                        j--;
                    }
                    if (j<0)
                    {    break;
                    }

                }
                if (str.equals("")==false)
                {   strNumber = str;
                }
            }

            else if (pMask.indexOf("9") != -1)
            {
                strNumber = pNumber.toPlainString();
                numberLength =  strNumber.length();

                if (numberLength <  pMask.length())
                {
                    for (int i = 0; i < (pMask.length() - numberLength); i++)
                    {   if (pMask.charAt(i) == '9')
                        {   strNumber = "0" + strNumber;

                        }
                    }
                }
            }
            else if (pMask.indexOf("Z") != -1)
            {   String str="";
                strNumber = pNumber.toPlainString();
                numberLength =  strNumber.length();

                int j=pMask.length()-1;
                for (int i = numberLength - 1; i >= 0; i--)
                {   if ((pMask.charAt(j) == '.') || (pMask.charAt(j) == ',') )
                    {   str =  pMask.charAt(j) + str ;
                        j--;
                    }
                    if (strNumber.charAt(i)!='.')
                    {    str= strNumber.charAt(i) + str;
                        j--;
                    }
                    if (j<0)
                    {    break;
                    }

                }
                if (str.equals("")==false)
                {   strNumber = str;
                    numberLength = str.length();
                }

                if (numberLength <  pMask.length())
                {
                   for (int i = 0; i < (pMask.length() - numberLength); i++)
                   {
                       strNumber = " " + strNumber;
                   }
                }
            }

            else if (pMask.indexOf("*") != -1)
            {   String str="";
                strNumber = pNumber.toPlainString();
                numberLength =  strNumber.length();

                int j=pMask.length()-1;
                for (int i = numberLength - 1; i >= 0; i--)
                {   if ((pMask.charAt(j) == '.') || (pMask.charAt(j) == ',') )
                    {   str =  pMask.charAt(j) + str ;
                        j--;
                    }
                    if (strNumber.charAt(i)!='.')
                    {    str= strNumber.charAt(i) + str;
                        j--;
                    }
                    if (j<0)
                    {    break;
                    }

                }
                if (str.equals("")==false)
                {   strNumber = str;
                    numberLength = str.length();
                }

                if (numberLength <  pMask.length())
                {
                   for (int i = 0; i < (pMask.length() - numberLength); i++)
                   {
                       strNumber = "*" + strNumber;
                   }
                }
            }

            if (GLB.DecimalChar != '.')
            {
               for (int i = 0; i < strNumber.length(); i++)
               {
                    if (strNumber.substring(i,i+1).equals(",") == true)
                    {
                    strNumber = strNumber.substring(0,i) + "." + strNumber.substring(i+1);
                    }
                    else if (strNumber.substring(i,i+1).equals(".") == true)
                    {
                    strNumber = strNumber.substring(0,i) + "," + strNumber.substring(i+1);
                    }
                }
            }
            if (numOriginal.compareTo(new BigDecimal("0.0")) == -1)
            {
                strNumber = "-" + strNumber;
            }
            return(strNumber);

       }  //formato de uma data
       else if (pMask.equals("DD/MM/YY") == true)
       {
          myFormatter = new DecimalFormat("000000");
          pMask = myFormatter.format(pNumber);
          return(pMask.substring(0,2) + "/" + pMask.substring(2,4) + "/" +  pMask.substring(4));
       }
       else if (pMask.equals("DD/MM/CCYY") == true)
       {
          myFormatter = new DecimalFormat("00000000");
          pMask = myFormatter.format(pNumber);
          return(pMask.substring(0,2) + "/" + pMask.substring(2,4) + "/" +  pMask.substring(4));
       }
       else if (pMask.equals("CCYY/MM/DD") == true)
       {
          myFormatter = new DecimalFormat("00000000");
          pMask = myFormatter.format(pNumber);
          return(pMask.substring(0,4) + "/" + pMask.substring(4,6) + "/" +  pMask.substring(6));
       }
       else
       {
          return(cStr(pNumber));
       }
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "format");
        return ("");
    }
}

//format String
public String format(String pString, int pLength)
    throws Exception
{
    try
    {
        if (pString.length() < pLength)
    	{
            char init[];
            StringBuffer  mvStrBuffer =  new StringBuffer(pString);
            init = new char [pLength - mvStrBuffer.length()];
            for (int i = 0;i<pLength - mvStrBuffer.length();i++)
            {
                init[i]= ' ';
            }
            mvStrBuffer.append(init, 0,pLength - mvStrBuffer.length());

       		pString = mvStrBuffer.toString();
    	}
    	else if (pString.length() > pLength)
    	{
       		pString = pString.substring(0,pLength);
    	}

    	return(pString);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "format");
        return ("");
    }
}

//formatArray


public String formatArray(String pField, int pLength)
throws Exception
{
    try
    {
        String wText = pField;
        int wInputLength = pLength - wText.length();

        if(wInputLength > 0)
        {
            wText  = wText + string(wInputLength, " ");
        }
        else if(wInputLength < 0)
        {
            wText = wText.substring(0,pLength);
        }
        return wText;
    }
    catch (Exception e)
    {
       ShowErrorMsg (e, "formatArray");
       return (pField);
    }
}

public String formatArray(String pField, String pMask)
throws Exception
{
    try
    {
        if(pMask.indexOf("9") != -1)
        {
            int length ;
            int decimals;
            if(pMask.indexOf(".") != -1)
            {
                length = pMask.length() - 1;
                decimals = pMask.substring(pMask.indexOf(".") + 1).length();
            }
            else
            {
                length = pMask.length();
                decimals = 0;
            }

            String wNumber = pField;

            if(decimals > 0)
            {
                int idx;

                if(wNumber.indexOf(".") != -1)
                {
                    idx = wNumber.indexOf(".");
                }
                else
                {
                    idx = wNumber.length();
                }

                //trata a parte inteira
                String wIntPart = wNumber.substring(0, idx);
                int wInputLength = (length - decimals) - wIntPart.length();
                if(wInputLength > 0)
                {
                    wIntPart = string(wInputLength, "0") + wIntPart;
                }
                else if(wInputLength < 0)
                {
                    wIntPart = wIntPart.substring(Math.abs(wInputLength));
                }

                //trata a parte decimal
                String wDecimalsPart;
                if(wNumber.indexOf(".") != -1)
                {
                    wDecimalsPart = wNumber.substring(idx + 1);
                }
                else
                {
                    wDecimalsPart = "";
                }
                int wInputDecLength = decimals - wDecimalsPart.length();
                if(wInputDecLength > 0)
                {
                    wDecimalsPart = wDecimalsPart + string(wInputDecLength, "0");
                }
                else if(wInputDecLength < 0)
                {
                    wDecimalsPart = wDecimalsPart.substring(0,decimals);
                }

                wNumber = wIntPart + "." + wDecimalsPart;
                return wNumber;
            }
            else
            {
                int idx;
                if(wNumber.indexOf(".") != -1)
                {
                    idx = wNumber.indexOf(".");
                }
                else
                {
                    idx = wNumber.length();
                }

                wNumber = wNumber.substring(0, idx);

                int wInputLength = length - wNumber.length();

                if(wInputLength > 0)
                {
                    wNumber = string(wInputLength, "0") + wNumber;
                }
                else if(wInputLength < 0)
                {
                    wNumber = wNumber.substring(Math.abs(wInputLength));
                }

                return wNumber;
            }
        }
        return pField;
    }

    catch (Exception e)
    {
       ShowErrorMsg (e, "formatArray");
       return (pField);
    }

}


public String formatArray(double pField, String pMask, boolean pSignal)
throws Exception
{
    try
    {
        String resultNumber = "";
        if (pSignal)
        {
            if  (pField < 0)
            {
                resultNumber = "-";
            }
            else
            {
              resultNumber = " ";
            }
        }
        pField = Math.abs(pField);
        resultNumber = resultNumber + formatArray( String.format("%f", pField).replace(',','.'),pMask);
        return resultNumber ;
    }
    catch (Exception e)
    {
       ShowErrorMsg (e, "formatArray");
       return ("");
    }
}


public String formatArray(BigDecimal pField, String pMask, boolean pSignal) throws Exception
{
    try
    {
        String resultNumber = "";
        if (pSignal)
        {   if (pField.compareTo(bigZeros) < 0)
            {
                resultNumber = "-";
            }
            else
            {
                resultNumber = " ";
            }
        }
        pField = pField.abs();
        resultNumber  = resultNumber + formatArray(pField.toPlainString(), pMask);
        return resultNumber;
    }
    catch (Exception e)
    {
       ShowErrorMsg (e, "formatArray");
       return ("");
    }
}

//  formatCommand
public String formatCommand(BigDecimal pNumber, String pMask)
    throws Exception
{
    try
    {
        DecimalFormat myFormatter;
        String strNumber=pNumber.toPlainString();
        int numberLength;
        int numberDecimals;
        int count=0;
        int index;
        boolean hasChangeMask = false;
        boolean hasMaskMinus = false;
        boolean hasNumberMinus = false;
        String strAux;
        int posAntmask;
        int posAntNumber;
        int tam;

        if ((pMask.substring(pMask.length()-1)).equals("-")==true)
        {   hasMaskMinus = true;
            pMask = pMask.substring(0,pMask.length()-1);
        }

        if (pNumber.compareTo(new BigDecimal("0"))==-1 )
        {   hasNumberMinus = true;
            pNumber = pNumber.abs();

        }
        if(GLB.MASKDECIMALCHAR.equals(",") == true)
        {
            for (int i = 0; i < pMask.length(); i++)
            {
                if (pMask.substring(i,i+1).equals(",") == true)
                {
                    pMask = pMask.substring(0,i) + "." + pMask.substring(i+1);
                }
                else if (pMask.substring(i,i+1).equals(".") == true)
                {
                    pMask = pMask.substring(0,i) + "," + pMask.substring(i+1);
                }
            }
            hasChangeMask = true;
        }

        // Format de um numero com mascara 999, ### ou ZZZ
        if ( (pMask.indexOf("9") != -1) ||
             (pMask.indexOf("#") != -1) ||
             (pMask.indexOf("Z") != -1) ||
             (pMask.indexOf("*") != -1)) //formato de um numero
        {
            index = pMask.indexOf(",");
            while (index != -1)
            {
                count++;
                index = pMask.indexOf(",",index+1);
            }

            index = pMask.indexOf(":");
            while (index != -1)
            {
                count++;
                index = pMask.indexOf(":",index+1);
            }

            index = pMask.indexOf("/");
            while (index != -1)
            {
                count++;
                index = pMask.indexOf("/",index+1);
            }

            index = pMask.indexOf(".");
            if (index != -1)
            {
               numberDecimals = (pMask.substring(index+1)).length();
               numberLength = pMask.length() - count - 1;
            }
            else
            {
               numberDecimals = 0;
               numberLength = pMask.length() - count;
            }

            pNumber = fixBigDec (pNumber, numberLength, numberDecimals,"UNSIGNED", "TRUNCATE");

            if (pMask.indexOf("#") != -1)
            {   String str="";
                strNumber = pNumber.toPlainString();

                index = strNumber.indexOf(".");
                if (index != -1)
                {   if ((strNumber.length() - index - 1) > numberDecimals)
                    {   strNumber = strNumber.substring(0, index + numberDecimals+1);
                    }
                }

                numberLength =  strNumber.length();

                int j=pMask.length()-1;

                for (int i = numberLength - 1; i >= 0; i--)
                {   if ((pMask.charAt(j) == '.') || (pMask.charAt(j) == ',') )
                    {   str =  pMask.charAt(j) + str ;
                        j--;
                    }
                    if (strNumber.charAt(i)!='.')
                    {    str= strNumber.charAt(i) + str;
                        j--;
                    }
                    if (j<0)
                    {    break;
                    }

                }
                if (str.equals("")==false)
                {   strNumber = str;
                }
            }

            if ((pMask.indexOf("9") != -1) || (pMask.indexOf("Z") != -1))
            {
                if ((pMask.indexOf(":") != -1) || (pMask.indexOf("/") != -1))
                {
                    strNumber = pNumber.toPlainString();
                    strAux  =""   ;
                    posAntmask = pMask.length();
                    posAntNumber = strNumber.length();

                    for (int i = pMask.length() -1;i>=0;i--)
                    {
                        if ((pMask.charAt(i) == '/') || (pMask.charAt(i) == ':'))
                        {
                            if (i <= strNumber.length())
                            {
                                tam = posAntmask-i-1;
                                strAux = (pMask.charAt(i))+ strNumber.substring(posAntNumber-tam, posAntNumber) + strAux ;
                                posAntmask = i;
                                posAntNumber= posAntNumber-tam;
                            }
                            else
                            {
                                break;
                            }
                        }
                    }
                    if (strAux.equals("")==false)
                    {
                        if (posAntNumber < strNumber.length())
                        {
                            strNumber = strNumber.substring(0,posAntNumber)+ strAux;
                        }
                        else
                        {   strNumber= strAux;
                        }
                    }
                    numberLength =  strNumber.length();

                    if (numberLength <  pMask.length())
                    {   for (int i=pMask.length() - numberLength-1; i>=0;i--)
                        {
                            if (pMask.charAt(i) == '9')
                            {   strNumber = "0" + strNumber;
                            }
                            else
                            {   strNumber = pMask.charAt(i) +strNumber;
                            }
                        }
                    }

                }
                else
                {
                    pMask = pMask.replace('9','0');
                    pMask = pMask.replace('Z','#');
                    myFormatter = new DecimalFormat(pMask, new DecimalFormatSymbols(new Locale("en","US")));
                    if (pMask.indexOf(".") != -1)
                    {
                        myFormatter.setMinimumFractionDigits(pMask.length() - pMask.indexOf(".") - 1);
                    }
                    strNumber = myFormatter.format(pNumber);

                    numberLength =  strNumber.length();

                    if (numberLength <  pMask.length())
                    {
                        for (int i = 0; i < (pMask.length() - numberLength); i++)
                        {
                            strNumber = " " + strNumber;
                        }
                    }

                }
            }

            if (pMask.indexOf("*") != -1)
            {   String str="";
                strNumber = pNumber.toPlainString();
                numberLength =  strNumber.length();

                int j=pMask.length()-1;
                for (int i = numberLength - 1; i >= 0; i--)
                {   if ((pMask.charAt(j) == '.') || (pMask.charAt(j) == ',') )
                    {   str =  pMask.charAt(j) + str ;
                        j--;
                    }
                    if (strNumber.charAt(i)!='.')
                    {    str= strNumber.charAt(i) + str;
                        j--;
                    }
                    if (j<0)
                    {    break;
                    }

                }
                if (str.equals("")==false)
                {   strNumber = str;
                    numberLength = str.length();
                }

                if (numberLength <  pMask.length())
                {
                   for (int i = 0; i < (pMask.length() - numberLength); i++)
                   {
                       strNumber = "*" + strNumber;
                   }
                }
            }

            if (hasChangeMask == true)
            {
               for (int i = 0; i < strNumber.length(); i++)
               {
                    if (strNumber.substring(i,i+1).equals(",") == true)
                    {
                    strNumber = strNumber.substring(0,i) + "." + strNumber.substring(i+1);
                    }
                    else if (strNumber.substring(i,i+1).equals(".") == true)
                    {
                    strNumber = strNumber.substring(0,i) + "," + strNumber.substring(i+1);
                    }
                }
            }
        }

        if ((hasMaskMinus == true) && (hasNumberMinus == true))
        {
           strNumber = strNumber + "-";
        }
        return(strNumber);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "formatCommand");
        return ("");
    }
}

//getStringBuffer
public StringBuffer getStringBuffer (String pStr, int pLength)
throws Exception
{
    try
    {
        char init[];
        StringBuffer strBuffer = new StringBuffer (pStr);
        if (strBuffer.length() < pLength)
        {
            init = new char [pLength - strBuffer.length()];
            for (int i = 0;i<pLength - strBuffer.length();i++)
            {
                init[i]= ' ';
            }
            strBuffer.append(init, 0,pLength - strBuffer.length());
        }
        return(strBuffer);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "getStringBuffer");
        return (new StringBuffer (pStr));
    }
}

//getStringBuffer
public StringBuffer getStringBuffer (int pLength)
throws Exception
{
    try
    {
        char init[];
        StringBuffer strBuffer = new StringBuffer ("");

        if (pLength > 0)
        {
            init = new char [pLength];
            for (int i = 0;i<pLength;i++)
            {
                init[i]= ' ';
            }
            strBuffer.append(init, 0,pLength);
        }
        return(strBuffer);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "getStringBuffer");
        return (new StringBuffer (""));
    }
}

//isNumeric
public boolean isNumeric(String InputTXT)
    throws Exception
{
    try
    {   String lastChar = (InputTXT.substring(InputTXT.length() -1)).toUpperCase();
        if ((lastChar.equals("D") == true) || (lastChar.equals("F") == true))
            return(false);
		if (InputTXT.contains("E"))
			return (false);
        double doubleNumber = Double.valueOf(InputTXT).doubleValue();
        return (true);
    }
    catch (Exception e)
    {
        return (false);
    }
}

/* newFix */
public double newFix (BigDecimal pBigDec, int pLength, int pDecimals, String pSigned, String pRounded)
   throws Exception
{
   try
   {
        BigDecimal bigDec;
	    BigDecimal bigDecAbs;

	    bigDecAbs = pBigDec.abs();

	    //arredondamento parte inteira
        bigDec = bigDecAbs.movePointLeft(pLength -pDecimals);
        bigDec= bigDec.setScale(0,1);
   	    bigDec = bigDec.movePointRight(pLength -pDecimals);
        if (bigDec.compareTo(new BigDecimal("0.0")) > 0)
        {    bigDecAbs = bigDecAbs.subtract(bigDec);
  	    }

        //arredondamento parte decimal
        if (pRounded.equals("ROUNDED") == true)
        {
	    bigDecAbs = bigDecAbs.setScale(pDecimals,4);
  	    }
  	    else
  	    {
           bigDecAbs = bigDecAbs.setScale(pDecimals,1);
  	    }

  	    //Tratamento sinal
  	    if (pSigned.equals("UNSIGNED") == true)
    	{
            pBigDec = bigDecAbs;
	    }
    	else
    	{
            if (pBigDec.compareTo(new BigDecimal("0.0")) == -1)
            {
                pBigDec = bigDecAbs.multiply(new BigDecimal("-1.0"));
   	        }
   	        else
   	        {
                pBigDec = bigDecAbs;
            }
	    }

  	    return(pBigDec.doubleValue());

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "newFix");
        return (0.0);
    }
}

/* fixBigDec --> BigDecimals */
public BigDecimal fixBigDec (BigDecimal pBigDec, int pLength, int pDecimals, String pSigned, String pRounded)
   throws Exception
{
    try
    {
        BigDecimal bigDec;
	    BigDecimal bigDecAbs;

	    bigDecAbs = pBigDec.abs();

	    //arredondamento parte inteira
	    bigDec = bigDecAbs.movePointLeft(pLength -pDecimals);
	    bigDec= bigDec.setScale(0,1);
   	    bigDec = bigDec.movePointRight(pLength -pDecimals);
        if (bigDec.compareTo(new BigDecimal("0.0")) > 0)
        {   bigDecAbs = bigDecAbs.subtract(bigDec);
  	    }

        //arredondamento parte decimal
        if (pRounded.equals("ROUNDED") == true)
        {
	        bigDecAbs = bigDecAbs.setScale(pDecimals,4);
  	    }
  	    else
  	    {
            bigDecAbs = bigDecAbs.setScale(pDecimals,1);
        }

  	    //Tratamento sinal
  	    if (pSigned.equals("UNSIGNED") == true)
    	{
            pBigDec = bigDecAbs;
	    }
    	else
    	{
            if (pBigDec.compareTo(new BigDecimal("0.0")) == -1)
            {
                pBigDec = bigDecAbs.multiply(new BigDecimal("-1"));
            }
            else
            {
                pBigDec = bigDecAbs;
           }
	    }

  	    return(pBigDec);

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "newFix");
        return (new BigDecimal(0));
    }
}


/* rTrim */
public String rTrim(String pStr)
    throws Exception
{
    try
    {
    	int	len;
    	String wrkStr = pStr;
    	if (wrkStr==null)
    	{
    	   return("");
    	}
    	len = wrkStr.length();
    	for (int i=len;i>=1;i--)
    	{
    		if (wrkStr.substring(i-1,i).equals(" ") == true)
    		{
    			wrkStr= pStr.substring(0,i-1);
    		}
    		else
    		{
    			break;
    		}
    	}
    	return(wrkStr);
    }
    catch(Exception e)
    {
		ShowErrorMsg (e,"rTrim");
   		return(pStr);
    }
}

/* rTrim */
public String rTrim(String pStr, String pChar)
    throws Exception
{
    try
    {
        String wrkStr = pStr;
        if (wrkStr==null)
        {
           return("");
        }
        wrkStr = pChar + wrkStr;
        wrkStr= wrkStr.trim();
        wrkStr = wrkStr.substring(1);

        return(wrkStr);
    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"rTrim");
        return(pStr);
    }
}



/* splitElements */
private String[] splitElements(String elements)
    throws Exception
{
    try
    {
    	int n   = 0;
    	int ini = 0;
    	int pos = 0;

    	if ((elements != null) && (elements.equals("") == false))
    	{
    		for(int i=0; i < elements.length(); i++)
    		{
    			if (elements.charAt(i) == ';') n++;
        	}
       		if(elements.charAt(elements.length() - 1) != ';')
       		{
       			n++;
       		}

        	String arrayElem[] = new String[n];

        	for (int i=0; i < elements.length(); i++)
        	{
        		if (elements.charAt(i) == ';')
            	{
               		arrayElem[pos] = elements.substring(ini,i).trim();
                	ini = i + 1;
                	pos++;
            	}
        	}
        	if(pos <= (arrayElem.length -1))
        	{
        		arrayElem[pos] = elements.substring(ini, elements.length()).trim();
        	}
        	return(arrayElem);
    	}
    	return(null);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "splitElements");
    	return(null);
    }
}

//string
public String string(int pLen, String pStr)
    throws Exception
{
    try
    {
	if (pLen <= 0)
    	{
           return("");
    	}

    	String wrkStr = pStr;

    	for (int i=1; i < pLen; i++)
    	{
            wrkStr = wrkStr + pStr;
    	}

    	return(wrkStr);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "string");
    	return(pStr);
    }
}

/* subItem */
public double subItem(double pItem, StringBuffer pGroup, int pInit, int pEnd)
    throws Exception
{
    try
    {
        StringBuffer strBuffer = new StringBuffer(rTrim(pGroup.substring(pInit, pEnd)));

        for (int i = strBuffer.length(); i<(pEnd - pInit); i++)
        {
            strBuffer = strBuffer.append("0");
        }

        return(cDbl(rTrim(strBuffer.substring(0, pEnd-pInit))));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e,"subItem");
    	return(0);
    }
}

/* subItem --> BigDecimal */
public BigDecimal subItem(BigDecimal pItem, StringBuffer pGroup, int pInit, int pEnd)
    throws Exception
{
    try
    {
        StringBuffer strBuffer = new StringBuffer(rTrim(pGroup.substring(pInit, pEnd)));

        for (int i = strBuffer.length(); i<(pEnd - pInit); i++)
        {
            strBuffer = strBuffer.append("0");
        }

        return(cBigDec(rTrim(strBuffer.substring(0, pEnd-pInit))));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e,"subItem");
    	return(new BigDecimal(0));
    }
}


/* subItem */
public String subItem(String pItem,StringBuffer pGroup,int pInit, int pEnd)
   throws Exception
{
   try
   {
       return(rTrim(pGroup.substring(pInit, pEnd),"*"));
   }
   catch (Exception e)
   {
      ShowErrorMsg (e,"subItem");
      return("");
   }
}

/*
 *
 */
 private int getyear(String DCDate, String dcFormat)
    throws Exception
{
    try
    {
        String year=null;
        if (dcFormat.equals("DD-MM-YY"))
        {
            year =DCDate.substring(6,8);
        }
        else if (dcFormat.equals("DD-MMM-YY"))
        {
            year=DCDate.substring(7,9);
        }
        else if (dcFormat.equals("DDMMYY"))
        {
            year=DCDate.substring(4,6);
        }
        else if (dcFormat.equals("DDMMMYY"))
        {
            year=DCDate.substring(5,7);
        }
        else if (dcFormat.equals("MM-DD-YY"))
        {
            year = DCDate.substring(6,8);
        }
        else if (dcFormat.equals("MMM-DD-YY"))
        {
            year =DCDate.substring(7,9);
        }
        else if (dcFormat.equals("MMDDYY"))
        {
            year =DCDate.substring(4,6);
        }
        else if (dcFormat.equals("MMMDDYY"))
        {
            year =DCDate.substring(5,7);
        }
        else if (dcFormat.equals("YYDDD"))
        {
            year =DCDate.substring(0,2);
        }
        else if (dcFormat.equals("YYMMDD"))
        {
            year=DCDate.substring(0,2);

        }
        else if (dcFormat.equals("YYMMMDD"))
        {
            year=DCDate.substring(0,2);
        }
        else if (dcFormat.equals("YY-MM-DD"))
        {
            year=DCDate.substring(0,2);
        }
        else if (dcFormat.equals("YY-MMM-DD"))
        {
            year =DCDate.substring(0,2);
        }
        else if (dcFormat.equals("DD-MM-CCYY"))
        {
            year=DCDate.substring(8,10);
        }
        else if (dcFormat.equals("DD-MMM-CCYY"))
        {
            year =DCDate.substring(9,11);
        }
        else if (dcFormat.equals("DDMMCCYY"))
        {
            year=DCDate.substring(6,8);
        }
        else if (dcFormat.equals("DDMMMCCYY"))
        {
            year =DCDate.substring(7,9);
        }
        else if (dcFormat.equals("MM-DD-CCYY"))
        {
            year=DCDate.substring(8,10);
        }
        else if (dcFormat.equals("MMDDCCYY"))
        {
            year=DCDate.substring(6,8);
        }
        else if (dcFormat.equals("MMM-DD-CCYY"))
        {
            year=DCDate.substring(9,11);
        }
        else if (dcFormat.equals("MMMDDCCYY"))
        {
            year=DCDate.substring(7,9);
        }
        else if (dcFormat.equals("CCYYDDD"))
        {
            year=DCDate.substring(2,4);
        }
        else if (dcFormat.equals("CCYYMMDD"))
        {
            year=DCDate.substring(2,4);
        }
        else if (dcFormat.equals("CCYYMMMDD"))
        {
            year=DCDate.substring(2,4);
        }
        else if (dcFormat.equals("CCYY-MM-DD"))
        {   year =DCDate.substring(2,4);
        }
        else if (dcFormat.equals("CCYY-MMM-DD"))
        {
            year=DCDate.substring(2,4);
        }
        return(Integer.parseInt(year));
      }
      catch (Exception e)
    {
        return(0);
    }

}
//	* validateDate */
public  String validateDate(String DCDate, String dcFormat)
    throws Exception
{
    String century = "";
    int glbBaseyy = 0;
    int glbBasecc = 0;
    int dcauxdd = 0;
    int dcauxmm = 0;
    int dcauxyy = 0;
    int dcauxccyy=0;

    try
    {

        GLB.STATUS = "";
        glbBaseyy = Integer.parseInt( String.valueOf(GLB.BASE).substring(2,4) );
        glbBasecc = Integer.parseInt( String.valueOf(GLB.BASE).substring(0,2) );

        century = formatDC(GLB.CENTURY,"99");

        if ((GLB.CENTURY==glbBasecc) || (GLB.CENTURY == 0.0))
        {
            if (getyear(DCDate,dcFormat) < glbBaseyy)
            {
                century = formatDC(glbBasecc + 1,"99");
            }
            else
            {
                century = formatDC(glbBasecc,"99");
            }

            if (GLB.CENTURY == 0.0 && glbBaseyy == 0.0)
            {
                century = "20";
            }
        }

        if (dcFormat.equals("UK-ALPHA")==false &&
            dcFormat.equals("IN-ALPHA")==false &&
            dcFormat.equals("UK-ALPHA")==false &&
            dcFormat.equals("TODAY")==false &&
            dcFormat.equals("WEEKNO")==false &&
            dcFormat.equals("DAYNUM")==false )
        {

            if(DCDate.length() != dcFormat.length())
            {
        	    GLB.STATUS = "*****";
               	return (century);
            }
        }

    	if (dcFormat.equals("DD-MM-YY"))
        {
        	if( DCDate.indexOf("/")!= 2 || DCDate.lastIndexOf("/")!=5 || DCDate.length()!= 8)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(0,2));
        	dcauxmm = Integer.parseInt(DCDate.substring(3,5));
        	dcauxyy = Integer.parseInt(century + DCDate.substring(6,8));
            GLB.STATUS = checkDate(dcauxdd, dcauxmm,dcauxyy);

        }
    	else if (dcFormat.equals("DD-MMM-YY"))
        {
        	if( DCDate.indexOf(" ")!= 2 || DCDate.lastIndexOf(" ")!=6 || DCDate.length()!= 9)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(0,2));
            dcauxmm = convertTypeMonth(DCDate.substring(3,6));
            dcauxyy = Integer.parseInt(century+DCDate.substring(7,9));
			GLB.STATUS = checkDate(dcauxdd,dcauxmm, dcauxyy);

        }
    	else if (dcFormat.equals("DDMMYY"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(0,2));
        	dcauxmm = Integer.parseInt(DCDate.substring(2,4));
            dcauxyy = Integer.parseInt(century+DCDate.substring(4,6));
			GLB.STATUS = checkDate(dcauxdd, dcauxmm, dcauxyy);

        }
    	else if (dcFormat.equals("DDMMMYY"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(0,2));
            dcauxmm = convertTypeMonth(DCDate.substring(2,5));
            dcauxyy = Integer.parseInt(century+DCDate.substring(5,7));
            GLB.STATUS = checkDate(dcauxdd,dcauxmm, dcauxyy);

        }
    	else if (dcFormat.equals("MM-DD-YY"))
        {
        	if( DCDate.indexOf("/")!= 2 || DCDate.lastIndexOf("/")!=5 || DCDate.length()!= 8)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(3,5));
        	dcauxmm = Integer.parseInt(DCDate.substring(0,2));
            dcauxyy = Integer.parseInt(century+DCDate.substring(6,8));
			GLB.STATUS = checkDate(dcauxdd, dcauxmm, dcauxyy);
        }
    	else if (dcFormat.equals("MMM-DD-YY"))
        {
        	if( DCDate.indexOf(" ")!= 3 || DCDate.lastIndexOf(" ")!=6 || DCDate.length()!= 9)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(4,6));
            dcauxmm = convertTypeMonth(DCDate.substring(0,3));
            dcauxyy = Integer.parseInt(century+DCDate.substring(7,9));
            GLB.STATUS = checkDate(dcauxdd,dcauxmm, dcauxyy);
        }
    	else if (dcFormat.equals("MMDDYY"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(2,4));
        	dcauxmm = Integer.parseInt(DCDate.substring(0,2));
            dcauxyy = Integer.parseInt(century+DCDate.substring(4,6));
            GLB.STATUS = checkDate(dcauxdd,dcauxmm, dcauxyy);
        }
    	else if (dcFormat.equals("MMMDDYY"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(3,5));
            dcauxmm = convertTypeMonth(DCDate.substring(0,3));
            dcauxyy = Integer.parseInt(century+DCDate.substring(5,7));
            GLB.STATUS = checkDate(dcauxdd,dcauxmm, dcauxyy);
        }
    	else if (dcFormat.equals("YYDDD"))
        {
            dcauxyy = Integer.parseInt(DCDate.substring(0,2));
		}
    	else if (dcFormat.equals("YYMMDD"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(4,6));
        	dcauxmm = Integer.parseInt(DCDate.substring(2,4));
            dcauxyy = Integer.parseInt(century+DCDate.substring(0,2));
            GLB.STATUS = checkDate(dcauxdd,dcauxmm, dcauxyy);
        }
    	else if (dcFormat.equals("YYMMMDD"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(5,7));
            dcauxmm = convertTypeMonth(DCDate.substring(2,5));
            dcauxyy = Integer.parseInt(century+DCDate.substring(0,2));
            GLB.STATUS = checkDate(dcauxdd,dcauxmm, dcauxyy);
        }
    	else if (dcFormat.equals("YY-MM-DD"))
        {
        	if( DCDate.indexOf("/")!= 2 || DCDate.lastIndexOf("/")!=5 || DCDate.length()!= 8)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(6,8));
        	dcauxmm = Integer.parseInt(DCDate.substring(3,5));
            dcauxyy = Integer.parseInt(century+DCDate.substring(0,2));
            GLB.STATUS = checkDate(dcauxdd,dcauxmm, dcauxyy);
        }
    	else if (dcFormat.equals("YY-MMM-DD"))
        {
        	if( DCDate.indexOf(" ")!= 2 || DCDate.lastIndexOf(" ")!=6 || DCDate.length()!= 9)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(7,9));
            dcauxmm = convertTypeMonth(DCDate.substring(3,6));
            dcauxyy = Integer.parseInt(century+DCDate.substring(0,2));
            GLB.STATUS = checkDate(dcauxdd,dcauxmm,dcauxyy);
        }
    	else if (dcFormat.equals("DD-MM-CCYY"))
        {
        	if( DCDate.indexOf("/")!= 2 || DCDate.lastIndexOf("/")!=5 || DCDate.length()!= 10)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(0,2));
        	dcauxmm = Integer.parseInt(DCDate.substring(3,5));
        	dcauxccyy = Integer.parseInt(DCDate.substring(6,10));
        	dcauxyy = Integer.parseInt(DCDate.substring(8,10));
			GLB.STATUS = checkDate(dcauxdd, dcauxmm, dcauxccyy);

        }
    	else if (dcFormat.equals("DD-MMM-CCYY"))
        {
        	if( DCDate.indexOf(" ")!= 2 || DCDate.lastIndexOf(" ")!=6 || DCDate.length()!= 11)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(0,2));
            dcauxmm = convertTypeMonth(DCDate.substring(3,6));
			dcauxccyy = Integer.parseInt(DCDate.substring(7,11));
        	dcauxyy = Integer.parseInt(DCDate.substring(9,11));

            GLB.STATUS = checkDate(dcauxdd,dcauxmm,dcauxccyy);
        }
    	else if (dcFormat.equals("DDMMCCYY"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(0,2));
        	dcauxmm = Integer.parseInt(DCDate.substring(2,4));
        	dcauxccyy =Integer.parseInt(DCDate.substring(4,8));
			dcauxyy =Integer.parseInt(DCDate.substring(6,8));
			GLB.STATUS = checkDate(dcauxdd, dcauxmm, dcauxccyy);
		}
    	else if (dcFormat.equals("DDMMMCCYY"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(0,2));
            dcauxmm = convertTypeMonth(DCDate.substring(2,5));
        	dcauxccyy = Integer.parseInt(DCDate.substring(5,9));
        	dcauxyy = Integer.parseInt(DCDate.substring(7,9));
            GLB.STATUS = checkDate(dcauxdd,dcauxmm,dcauxccyy);
        }
    	else if (dcFormat.equals("MM-DD-CCYY"))
        {
        	if( DCDate.indexOf("/")!= 2 || DCDate.lastIndexOf("/")!=5 || DCDate.length()!= 10)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(3,5));
        	dcauxmm = Integer.parseInt(DCDate.substring(0,2));
        	dcauxccyy = Integer.parseInt(DCDate.substring(6,10));
        	dcauxyy = Integer.parseInt(DCDate.substring(8,10));
			GLB.STATUS = checkDate(dcauxdd, dcauxmm,dcauxccyy);
        }
    	else if (dcFormat.equals("MMDDCCYY"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(2,4));
        	dcauxmm = Integer.parseInt(DCDate.substring(0,2));
        	dcauxccyy = Integer.parseInt(DCDate.substring(4,8));
        	dcauxyy = Integer.parseInt(DCDate.substring(6,8));
			GLB.STATUS = checkDate(dcauxdd, dcauxmm,dcauxccyy);
        }
    	else if (dcFormat.equals("MMM-DD-CCYY"))
        {
        	if( DCDate.indexOf(" ")!= 3 || DCDate.lastIndexOf(" ")!=6 || DCDate.length()!= 11)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(4,6));
            dcauxmm = convertTypeMonth(DCDate.substring(0,3));
        	dcauxccyy = Integer.parseInt(DCDate.substring(7,11));
        	dcauxyy = Integer.parseInt(DCDate.substring(9,11));
			GLB.STATUS = checkDate(dcauxdd,dcauxmm,dcauxccyy);
        }
    	else if (dcFormat.equals("MMMDDCCYY"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(3,5));
            dcauxmm = convertTypeMonth(DCDate.substring(0,3));
        	dcauxccyy = Integer.parseInt(DCDate.substring(5,9));
        	dcauxyy = Integer.parseInt(DCDate.substring(7,9));
			GLB.STATUS = checkDate(dcauxdd,dcauxmm,dcauxccyy);
        }
    	else if (dcFormat.equals("CCYYDDD"))
        {   dcauxccyy = Integer.parseInt(DCDate.substring(0,4));
        	dcauxyy = Integer.parseInt(DCDate.substring(2,4));
			GLB.STATUS = checkYear(dcauxccyy);
        }
    	else if (dcFormat.equals("CCYYMMDD"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(6,8));
        	dcauxmm = Integer.parseInt(DCDate.substring(4,6));
        	dcauxccyy = Integer.parseInt(DCDate.substring(0,4));
        	dcauxyy = Integer.parseInt(DCDate.substring(2,4));
			GLB.STATUS = checkDate(dcauxdd, dcauxmm,dcauxccyy);
        }
    	else if (dcFormat.equals("CCYYMMMDD"))
        {
        	dcauxdd = Integer.parseInt(DCDate.substring(7,9));
            dcauxmm = convertTypeMonth(DCDate.substring(4,7));
        	dcauxccyy = Integer.parseInt(DCDate.substring(0,4));
        	dcauxyy = Integer.parseInt(DCDate.substring(2,4));
			GLB.STATUS = checkDate(dcauxdd,dcauxmm,dcauxccyy);
        }
    	else if (dcFormat.equals("CCYY-MM-DD"))
        {
        	if( DCDate.indexOf("/")!= 4 || DCDate.lastIndexOf("/")!=7 || DCDate.length()!= 10)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(8,10));
        	dcauxmm = Integer.parseInt(DCDate.substring(5,7));
        	dcauxccyy = Integer.parseInt(DCDate.substring(0,4));
        	dcauxyy = Integer.parseInt(DCDate.substring(2,4));
			GLB.STATUS = checkDate(dcauxdd, dcauxmm,dcauxccyy);
        }
    	else if (dcFormat.equals("CCYY-MMM-DD"))
        {
        	if( DCDate.indexOf(" ")!= 4 || DCDate.lastIndexOf(" ")!=8 || DCDate.length()!= 11)
			{
				throw new Exception();
			}
			dcauxdd = Integer.parseInt(DCDate.substring(9,11));
            dcauxmm = convertTypeMonth(DCDate.substring(4,9));
        	dcauxccyy = Integer.parseInt(DCDate.substring(0,4));
        	dcauxyy = Integer.parseInt(DCDate.substring(2,4));
			GLB.STATUS = checkDate(dcauxdd,dcauxmm,dcauxccyy);
        } 
    	else if (dcFormat.equals("MMCCYY"))
        {
    		dcauxdd = 1;
            dcauxmm = Integer.parseInt(DCDate.substring(0,2));
        	dcauxccyy = Integer.parseInt(DCDate.substring(2,6));
        	dcauxyy = Integer.parseInt(DCDate.substring(4,6));
			GLB.STATUS = checkDate(dcauxdd,dcauxmm,dcauxccyy);	
        }
    	else if (dcFormat.equals("CCYYMM"))
        {
    		dcauxdd = 1;
            dcauxmm = Integer.parseInt(DCDate.substring(4,6));
        	dcauxccyy = Integer.parseInt(DCDate.substring(0,4));
        	dcauxyy = Integer.parseInt(DCDate.substring(2,4));
			GLB.STATUS = checkDate(dcauxdd,dcauxmm,dcauxccyy);	
        }
    	else if (dcFormat.equals("MM-CCYY"))
        {
    		if( DCDate.indexOf("/")!= 2)
			{
				throw new Exception();
			}
    		dcauxdd = 1;
            dcauxmm = Integer.parseInt(DCDate.substring(0,2));
        	dcauxccyy = Integer.parseInt(DCDate.substring(3,7));
        	dcauxyy = Integer.parseInt(DCDate.substring(3,5));
			GLB.STATUS = checkDate(dcauxdd,dcauxmm,dcauxccyy);	
        }
    	
    	return(century);
    }
    catch (Exception e)
    {

		GLB.STATUS = "*****";

        Calendar cal = Calendar.getInstance();

        glbBaseyy = Integer.parseInt( String.valueOf(GLB.BASE).substring(2,4) );
        glbBasecc = Integer.parseInt( String.valueOf(GLB.BASE).substring(0,2) );

        dcauxyy = Integer.parseInt(String.valueOf(cal.get(cal.YEAR)).substring(2,4));

        century = formatDC(GLB.CENTURY,"99");

        if ((GLB.CENTURY==glbBasecc) || (GLB.CENTURY == 0.0))
    	{
    		if (dcauxyy < glbBaseyy)
        	{
        		century = formatDC(glbBasecc + 1,"99");
        	}
        	else
        	{
    		    century = formatDC(glbBasecc,"99");
        	}

   	    if (GLB.CENTURY == 0.0 && glbBaseyy == 0.0)
            {
               century = "20";
            }
        }

       return(century);
	}
}



/* Fim Métodos iguais */


/* Métodos diferente do XseedReport */

/* AuditBOP */
public void AuditBOP()
   throws Exception
{

    if (GLB.AUDITJOB == false)
    {
    	return;
    }

    Date dateToday = new Date();

    File exeFile = new File(GLB.SYSTEMDIR + File.separatorChar + "WEB-INF" + File.separatorChar + "classes" + File.separatorChar + ISPEC + ".class");

    Date genDate = new Date() ;
    genDate.setTime(exeFile.lastModified());

    try
    {
    	PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
		audit.println("================================================================================");
    	audit.println("BOP:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: Begin Ispec (Generate " + format(genDate,"yyyy/MM/dd") + ")");
    	audit.close();
    }
    catch (Exception e)
    {
		AuditCrash("BOP:" + format(dateToday, "yyyy/MM/DD HH:mm:ss") + " Isp: "+ ISPEC + " Msg: Begin Ispec (Generate " + format(genDate,"yyyy/MM/dd") + ")");
    }
}

/* AuditBOT */
public void AuditBOT()
   throws Exception
{
    if (GLB.AUDITJOB == false)
    {
       return;
    }

    Date dateToday = new Date();

	try
	{
		PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
        audit.println("================================================================================");
    	audit.println("BOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: Begin Transaction");
    	audit.close();
    	GLB.STARTTIME = dateToday.getTime();
	}
	catch (Exception e)
	{
		AuditCrash("BOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: Begin Transaction");
	}
}


/* AuditBOT */
public void AuditBOT(HttpServletRequest request)
    throws Exception
{
    if (GLB.AUDITJOB == false)
    {
        return;
    }

    Date dateToday = new Date();

    try
    {
        PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
        audit.println("================================================================================");
        audit.println("BOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: Begin Transaction Client IP:" + request.getRemoteAddr() );
        audit.close();
        GLB.STARTTIME = dateToday.getTime();
    }
    catch (Exception e)
    {
        AuditCrash("BOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: Begin Transaction");
    }
}



/* AuditEOP */
public void AuditEOP()
   throws Exception
{
    if (GLB.AUDITJOB == false)
    {
       return;
    }

    Date dateToday = new Date();

	try
	{
    	PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
    	audit.println("EOP:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: End Ispec");
    	audit.close();
	}
	catch (Exception e)
	{
		AuditCrash("EOP:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: End Ispec");
	}

}

/* AuditEOT */
public void AuditEOT ()
   throws Exception
{
    if (GLB.AUDITJOB == false)
    {
       return;
    }

    Date dateToday = new Date();
    long elapsedTime = dateToday.getTime() - GLB.STARTTIME;
	double hours = (elapsedTime / 1000) / 3600;
	double minutes = ((elapsedTime / 1000) % 3600) / 60;
	double seconds = ((elapsedTime / 1000) % 3600) % 60;
	double miliseconds = elapsedTime % 1000;
	String timeString = format(hours,"99") + ":" + format(minutes,"99") +":"+ format (seconds,"99") + ":" + format (miliseconds,"999");

	try
	{
		PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));

		if (GLB.MSGINDEX > 0 )
		{
			for( int i=1; i<=GLB.MSGINDEX; i++)
			{
				audit.println("MSG: " + format(dateToday,"yyyy/MM/dd HH:mm:ss") + " Isp: " + ISPEC + " Msg: " + GLB.MSGHEADER[i].trim() + " " + GLB.MSGTRAILER[i].trim());
			}
		}
		else if (GLB.MSGINDEX < 0 )
		{
			for(int i=(GLB.MSGINDEX * -1); i>=1; i--)
			{
				audit.println("MSG: " + format(dateToday,"yyyy/MM/dd HH:mm:ss") + " Isp: " + ISPEC + " Msg: " + GLB.MSGHEADER[i].trim() + GLB.MSGTRAILER[i].trim());
			}
		}
    	audit.println("EOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: End Transaction (Elapsed : " + timeString.trim() + "hs)");
    	audit.close();
	}
	catch (Exception e)
	{
		AuditCrash("EOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: End Transaction (Elapsed : " + timeString.trim() + "hs)");
	}
}

/* AuditERR */
public void AuditERR(String pMsg)
   throws Exception
{
    if (GLB.AUDITJOB == false)
    {
       return;
    }

    Date dateToday = new Date();
    long elapsedTime = dateToday.getTime() - GLB.STARTTIME;
	double hours = (elapsedTime / 1000) / 3600;
	double minutes = ((elapsedTime / 1000) % 3600) / 60;
	double seconds = ((elapsedTime / 1000) % 3600) % 60;
	double miliseconds = elapsedTime % 1000;
	String timeString = format(hours,"99") + ":" + format(minutes,"99") +":"+ format (seconds,"99") + ":" + format (miliseconds,"999");

	try
	{
		PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
    	audit.println("ERR:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: "+ pMsg.trim());
    	audit.println("EOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: Aborted Transaction(Elapsed: " + timeString.trim() + "hs");
	   	audit.println("EOP:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: Aborted Ispec");
    	audit.close();
	}
	catch (Exception e)
	{
    	AuditCrash("ERR:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: "+ pMsg.trim());
    	AuditCrash("EOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: Aborted Transaction(Elapsed: " + timeString.trim() + "hs");
	   	AuditCrash("EOP:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: Aborted Ispec");
	}
}

/* CommitTransaction */
public void CommitTransaction()
    throws Exception
{
    try
    {
        TRANNO = TRANNO + 1;
        GLB.CONNECTION.commit();
    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"CommitTransaction");
    }
}

/* cDbl */
public double cDbl (String pText, char pDecimalChar)
{
    try
    {

    	int position = -1;

	// se DecimalChar for ',' e na string tiver algun '.' eliminar '.'
	if (pDecimalChar == ',')
	{
           position = pText.indexOf('.');
           while (position != -1)
           {
        	pText = pText.substring(0, position) + pText.substring(position + 1);
                position = pText.indexOf('.');
           }
    	}

    	// se DecimalChar for ',' e na string tiver algun '.' eliminar '.'
    	else if(pDecimalChar == '.')
    	{
    		position = pText.indexOf(',');
        	while (position != -1)
        	{
        		pText = pText.substring(0, position) + pText.substring(position + 1);
            	position = pText.indexOf(',');
        	}
    	}

    	//Se decimal char for , e numero tiver decimais trocar ',' por '.'
    	position = pText.indexOf(pDecimalChar);
    	if ((pDecimalChar != '.') && (position != -1))
    	{
	        pText = pText.replace(',','.');
    	}

    	return(Double.valueOf(pText).doubleValue());
    }
    catch (Exception e)
    {
        return 0.0;
    }
}

/* cBigDec --> BigDecimal */
public BigDecimal cBigDec (String pText, char pDecimalChar)
{
    try
    {
        pText = pText.trim();
    	int position = -1;

	// se DecimalChar for ',' e na string tiver algun '.' eliminar '.'
	if (pDecimalChar == ',')
	{
           position = pText.indexOf('.');
           while (position != -1)
           {
        	pText = pText.substring(0, position) + pText.substring(position + 1);
                position = pText.indexOf('.');
           }
    	}

    	// se DecimalChar for ',' e na string tiver algun '.' eliminar '.'
    	else if(pDecimalChar == '.')
    	{
    		position = pText.indexOf(',');
        	while (position != -1)
        	{
        		pText = pText.substring(0, position) + pText.substring(position + 1);
            	position = pText.indexOf(',');
        	}
    	}

    	//Se decimal char for , e numero tiver decimais trocar ',' por '.'
    	position = pText.indexOf(pDecimalChar);
    	if ((pDecimalChar != '.') && (position != -1))
    	{
	        pText = pText.replace(',','.');
    	}

    	return(new BigDecimal(pText));
    }
    catch (Exception e)
    {
        return (new BigDecimal(0));
    }
}


// cDbl
public double cDbl (String pText, int pDecimals)
{
    try
    {
    	int position = -1;
        // eliminar '.'
	position = pText.indexOf('.');
        while (position != -1)
        {
            pText = pText.substring(0, position) + pText.substring(position + 1);
            position = pText.indexOf('.');
	}

    	//eliminar ,
    	position = pText.indexOf(',');
        while (position != -1)
        {
            pText = pText.substring(0, position) + pText.substring(position + 1);
            position = pText.indexOf(',');
	}

        return((Double.valueOf(pText).doubleValue()) / (Math.pow(10, pDecimals)));

    }
    catch (Exception e)
    {
        return 0.0;
    }
}

/* cBigDec --> BigDecimal */
public BigDecimal cBigDec (String pText, int pDecimals)
{
    try
    {   pText = pText.trim();
    	int position = -1;
        // eliminar '.'
	    position = pText.indexOf('.');
        while (position != -1)
        {
            pText = pText.substring(0, position) + pText.substring(position + 1);
            position = pText.indexOf('.');
	}

    	//eliminar ,
    	position = pText.indexOf(',');
        while (position != -1)
        {
            pText = pText.substring(0, position) + pText.substring(position + 1);
            position = pText.indexOf(',');
	}

        return( (new BigDecimal(pText)).movePointLeft(pDecimals) );

    }
    catch (Exception e)
    {
        return (new BigDecimal(0));
    }
}


//Fim metodos diferentes

////////// Métodos exclusivos de Ispec

/* BuildAbortPage */
public void BuildAbortPage(PrintWriter out)
    throws Exception
{
    try
    {
        out.println("<HTML>");
        out.println("<style type=\"Text/css\">");
        out.println("BODY {");
        out.println("   background-color: #C0C0C0;");
        out.println("}");
        out.println("</style>");
        out.println("<script language=\"JavaScript\">");
        out.println("function validatePage()");
        out.println("{");
        out.println("return(true);");
        out.println("}");
        out.println("");
        out.println("function xmit()");
        out.println("{");
        out.println("   if (validatePage() == true)");
        out.println("       {       document.frmABORT.submit();");
        out.println("       }");
        out.println("}");
        out.println("</script>");
        out.println("<BODY>");
        out.println("<FORM Name = \"frmABORT\" id = \"frmABORT\" method = \"post\" ACTION = \"" + GLB.RECALL + "\">");
        out.println("<font color=\"blue\" size=5>ABORT</font>");
        out.println("<BR>");
        out.println("<P>");
        out.println("<font color=\"red\" size=4>" + GLB.ABORTMSG + "</font>");
        out.println("<BR>");
        out.println("<P>");
        out.println("<input type=\"button\" name=\"Button1\" id=\"Button1\" value=\"Go Back\" onClick=\"xmit();\">");
        out.println("");
        out.println("</FORM>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"BuildAbortPage");
    }
}

/* BuildPageTimeout */
public void BuildPageTimeout (PrintWriter out)
  throws Exception
  {
    try
    {
        out.println("<HTML>");
        out.println("<style type=\"Text/css\">");
        out.println("BODY {");
        out.println("   background-color: #C0C0C0;");
        out.println("}");
        out.println("</style>");
        out.println("<script language=\"JavaScript\">");
        out.println("function validatePage()");
        out.println("{");
        out.println("return(true);");
        out.println("}");
        out.println("");
        out.println("function xmit()");
        out.println("{");
        out.println("   if (validatePage() == true)");
        out.println("       {       document.frmCTRERROR.submit();");
        out.println("       }");
        out.println("}");
        out.println("</script>");
        out.println("<BODY>");
        out.println("<FORM Name = \"frmCTRERROR\" Action=\"" + GLB.TIMEOUTACTION + "\" id = \"frmCTRERROR\" method = \"post\">");
        out.println("<font color=\"blue\" size=5>Session Timeout expired</font>");
        out.println("<BR>");
        out.println("<P>");
        out.println("<font color=\"black\" size=4>Begin the application</font>");
        out.println("<BR>");
        out.println("<P>");
        out.println("");
        out.println("<input type=\"button\" name=\"Button1\" id=\"Button1\" value=\"   Ok    \" onClick=\"xmit();\">");
        out.println("");
        out.println("</FORM>");
        out.println("</BODY>");
        out.println("</HTML>");
    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"BuildPageTimeout");
    }
}

/* LoadIspec */
public void LoadIspec(HttpServletResponse response, HttpSession session,String IspecName)
    throws Exception
{
    try
    {
        GLB.LOADED = true;
        GLB.CALL = IspecName;
        ISPEC = IspecName;
        if (GLB.MSGINDEX !=0)
        {	GLB.MESSAGE = GLB.MSGHEADER[GLB.MSGINDEX].trim() + "," + GLB.MSGTRAILER[GLB.MSGINDEX].trim();
        }
        else
        { 	GLB.MESSAGE = "";
        }
        WriteClipboard(session);
        if (GLB.CALL.equals("app") == true)
        {
        	response.sendRedirect("app");
        }
        else
        {
        	if (XseedSID.trim().equals(""))
        	{
        		response.sendRedirect(IspecName.trim().toUpperCase());
        	}
        	else
        	{
        		response.sendRedirect(IspecName.trim().toUpperCase()+"?XSEEDSID="+XseedSID);
        	}
    	}
    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"LoadIspec");
    }
}

/* MessageFromAnotherIspec */
public void MessageFromAnotherIspec()
    throws Exception
{
    try
    {	if(GLB.MESSAGE.equals("")==false)
    	{	int positionComman = GLB.MESSAGE.indexOf(",");
        	if (positionComman != -1)
        	{	message(GLB.MESSAGE.substring(0, positionComman), GLB.MESSAGE.substring(positionComman + 1));
        	}
        	else
        	{	message("ATTENTION", GLB.MESSAGE);
        	}
        	GLB.MESSAGE = "";
    	}
    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"MessageFromAnotherIspec");
    }
}

/* ReadClipboard */
public void ReadClipboard(HttpSession session)
    throws Exception
{
    try
    {

        setXseedSID();
        GLB.DSN = (String)session.getAttribute("GLB_DSN");
        if (GLB.DSN==null)
        {
           session.setAttribute("GLB_DSN", "");
           GLB.DSN = "";
        }
        GLB.USERCODE = (String)session.getAttribute("GLB_USERCODE");
        if (GLB.USERCODE==null)
        {
           session.setAttribute("GLB_USERCODE", "");
           GLB.USERCODE = "";
        }
        GLB.STN = (String)session.getAttribute("GLB_STN");
        if (GLB.STN==null)
        {
           session.setAttribute("GLB_STN", "");
           GLB.STN = "";
        }
        GLB.INITSTN = (String)session.getAttribute("GLB_INITSTN");
        if (GLB.INITSTN==null)
        {
           session.setAttribute("GLB_INITSTN", "");
           GLB.INITSTN = "";
        }
        GLB.TEACH = (String)session.getAttribute("GLB_TEACH");
        if (GLB.TEACH==null)
        {
           session.setAttribute("GLB_TEACH", "");
           GLB.TEACH = "";
        }
        GLB.REQUEST = (String)session.getAttribute("GLB_REQUEST");
        if (GLB.REQUEST==null)
        {
           session.setAttribute("GLB_REQUEST", "");
           GLB.REQUEST = "";
        }
        GLB.CALL = (String)session.getAttribute("GLB_CALL");
        if (GLB.CALL==null)
        {
           session.setAttribute("GLB_CALL", "");
           GLB.CALL = "";
        }
        GLB.USERNAME = (String)session.getAttribute("GLB_USERNAME");
        if (GLB.USERNAME==null)
        {
           session.setAttribute("GLB_USERNAME", "");
           GLB.USERNAME = "";
        }
        GLB.PASSWORD = (String)session.getAttribute("GLB_PASSWORD");
        if (GLB.PASSWORD==null)
        {
           session.setAttribute("GLB_PASSWORD", "");
           GLB.PASSWORD = "";
        }
        GLB.WORK = (String)session.getAttribute(XseedSID + "GLB_WORK");
        if (GLB.WORK==null)
        {
           session.setAttribute(XseedSID + "GLB_WORK","");
           GLB.WORK = "";
        }
        String stringData = (String)session.getAttribute("GLB_AUDITJOB");
        if (stringData ==null)
        {
           session.setAttribute("GLB_AUDITJOB", "");
           stringData = "";
        }
        if (stringData.trim().toUpperCase().equals("TRUE")==true)
        {
            GLB.AUDITJOB = true;
        }
        else
        {
            GLB.AUDITJOB = false;
        }
        GLB.AUDITACT = (String)session.getAttribute("GLB_AUDITACT");
        if (GLB.AUDITACT==null)
        {
           session.setAttribute("GLB_AUDITACT", "");
           GLB.AUDITACT = "";
        }
        GLB.FILESDIR = (String)session.getAttribute("GLB_FILESDIR");
        if (GLB.FILESDIR==null)
        {
           session.setAttribute("GLB_FILESDIR", "");
           GLB.FILESDIR = "";
        }
        GLB.AUDITDIR = (String)session.getAttribute("GLB_AUDITDIR");
        if (GLB.AUDITDIR==null)
        {
           session.setAttribute("GLB_AUDITDIR", "");
           GLB.AUDITDIR = "";
        }
        GLB.BACKUPDIR = (String)session.getAttribute("GLB_BACKUPDIR");
        if (GLB.BACKUPDIR==null)
        {
           session.setAttribute("GLB_BACKUPDIR", "");
           GLB.BACKUPDIR = "";
        }
        GLB.TEMPDIR = (String)session.getAttribute("GLB_TEMPDIR");
        if (GLB.TEMPDIR==null)
        {
           session.setAttribute("GLB_TEMPDIR", "");
           GLB.TEMPDIR = "";
        }
        GLB.SYSTEMDIR = (String)session.getAttribute("GLB_SYSTEMDIR");
        if (GLB.SYSTEMDIR==null)
        {
        	session.setAttribute("GLB_SYSTEMDIR", "");
            GLB.SYSTEMDIR = "";
        }
        GLB.DOCDIR = (String)session.getAttribute("GLB_DOCDIR");
        if (GLB.DOCDIR==null)
        {
           session.setAttribute("GLB_DOCDIR", "");
           GLB.DOCDIR = "";
        }
        GLB.EXTRACTSDIR = (String)session.getAttribute("GLB_EXTRACTSDIR");
        if (GLB.EXTRACTSDIR==null)
        {
           session.setAttribute("GLB_EXTRACTSDIR", "");
           GLB.EXTRACTSDIR = "";
        }
        GLB.MSGSDIR = (String)session.getAttribute("GLB_MSGSDIR");
        if (GLB.MSGSDIR==null)
        {
           session.setAttribute("GLB_MSGSDIR", "");
           GLB.MSGSDIR = "";
        }
        GLB.ICONSDIR = (String)session.getAttribute("GLB_ICONSDIR");
        if (GLB.ICONSDIR==null)
        {
           session.setAttribute("GLB_ICONSDIR", "");
           GLB.ICONSDIR = "";
        }
        GLB.BATCHDIR = (String)session.getAttribute("GLB_BATCHDIR");
        if (GLB.BATCHDIR==null)
        {
           session.setAttribute("GLB_BATCHDIR", "");
           GLB.BATCHDIR = "";
        }
        GLB.CLONEAPPDIR = (String)session.getAttribute("GLB_CLONEAPPDIR");
        if (GLB.CLONEAPPDIR==null)
        {
           session.setAttribute("GLB_CLONEAPPDIR", "");
           GLB.CLONEAPPDIR = "";
        }
        GLB.CLONERTSDIR = (String)session.getAttribute("GLB_CLONERTSDIR");
        if (GLB.CLONERTSDIR==null)
        {
           session.setAttribute("GLB_CLONERTSDIR", "");
           GLB.CLONERTSDIR = "";
        }
        GLB.SERVER = (String)session.getAttribute("GLB_SERVER");
        if (GLB.SERVER==null)
        {
           session.setAttribute("GLB_SERVER", "");
           GLB.SERVER = "";
        }
        GLB.DBNAME = (String)session.getAttribute("GLB_DBNAME");
        if (GLB.DBNAME==null)
        {
           session.setAttribute("GLB_DBNAME", "");
           GLB.DBNAME = "";
        }
        GLB.MESSAGE = (String)session.getAttribute("GLB_MESSAGE");
        if (GLB.MESSAGE==null)
        {
           session.setAttribute("GLB_MESSAGE", "");
           GLB.MESSAGE = "";
        }
        GLB.FULLSTN = (String)session.getAttribute("GLB_FULLSTN");
        if (GLB.FULLSTN==null)
        {
           session.setAttribute("GLB_FULLSTN", "");
           GLB.FULLSTN = "";
        }
        GLB.DBTYPE = (String)session.getAttribute("GLB_DBTYPE");
        if (GLB.DBTYPE==null)
        {
           session.setAttribute("GLB_DBTYPE", "");
           GLB.DBTYPE = "";
        }
        GLB.LANGUAGE = (String)session.getAttribute("GLB_LANGUAGE");
        if (GLB.LANGUAGE==null)
        {
           session.setAttribute("GLB_LANGUAGE", "");
           GLB.LANGUAGE = "";
        }
        GLB.REPLANG = (String)session.getAttribute("GLB_REPLANG");
        if (GLB.REPLANG==null)
        {
           session.setAttribute("GLB_REPLANG", "");
           GLB.REPLANG = "";
        }
        GLB.STALANG = (String)session.getAttribute("GLB_STALANG");
        if (GLB.STALANG==null)
        {
           session.setAttribute("GLB_STALANG", "");
           GLB.STALANG = "";
        }


    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"ReadClipboard");
    }
}

/* WriteClipboard */
public void WriteClipboard(HttpSession session)
    throws Exception
{
    try
    {
        session.setAttribute("GLB_DSN",GLB.DSN);
        session.setAttribute("GLB_USERCODE",GLB.USERCODE);
        session.setAttribute("GLB_STN",GLB.STN);
        session.setAttribute("GLB_INITSTN",GLB.INITSTN);
        session.setAttribute("GLB_TEACH",GLB.TEACH);
        session.setAttribute("GLB_REQUEST",GLB.REQUEST);
        session.setAttribute("GLB_CALL",GLB.CALL);
        session.setAttribute("GLB_USERNAME",GLB.USERNAME);
        session.setAttribute("GLB_PASSWORD",GLB.PASSWORD);
        session.setAttribute(XseedSID +"GLB_WORK",GLB.WORK);
        if (GLB.AUDITJOB == true)
        {
           session.setAttribute("GLB_AUDITJOB","TRUE");
        }
        else
        {
           session.setAttribute("GLB_AUDITJOB","FALSE");
	    }
        session.setAttribute("GLB_AUDITACT",GLB.AUDITACT);
        session.setAttribute("GLB_FILESDIR",GLB.FILESDIR);
        session.setAttribute("GLB_AUDITDIR",GLB.AUDITDIR);
        session.setAttribute("GLB_BACKUPDIR",GLB.BACKUPDIR);
        session.setAttribute("GLB_TEMPDIR",GLB.TEMPDIR);
        session.setAttribute("GLB_SYSTEMDIR",GLB.SYSTEMDIR);
        session.setAttribute("GLB_DOCDIR",GLB.DOCDIR);
        session.setAttribute("GLB_EXTRACTSDIR",GLB.EXTRACTSDIR);
        session.setAttribute("GLB_MSGSDIR",GLB.MSGSDIR);
        session.setAttribute("GLB_ICONSDIR",GLB.ICONSDIR);
        session.setAttribute("GLB_BATCHDIR",GLB.BATCHDIR);
        session.setAttribute("GLB_CLONEAPPDIR",GLB.CLONEAPPDIR);
        session.setAttribute("GLB_CLONERTSDIR",GLB.CLONERTSDIR);
        session.setAttribute("GLB_SERVER",GLB.SERVER);
        session.setAttribute("GLB_DBNAME",GLB.DBNAME);
        session.setAttribute("GLB_MESSAGE",GLB.MESSAGE);
        session.setAttribute("GLB_FULLSTN",GLB.FULLSTN);
        session.setAttribute("GLB_DBTYPE",GLB.DBTYPE);
        session.setAttribute("GLB_LANGUAGE",GLB.LANGUAGE);
        session.setAttribute("GLB_REPLANG",GLB.REPLANG);
        session.setAttribute("GLB_STALANG",GLB.STALANG);
        afterCycle ();
    }
    catch (Exception e)
    {
        GLB.ERRLINE = e.getMessage();
        ShowErrorMsg (e,"WriteClipboard");
    }
}

/* WriteRepClipboard */
public void WriteRepClipboard(HttpSession session)
    throws Exception
{
    try
    {
        session.setAttribute("GLB_DSN",GLB.DSN);
        session.setAttribute("GLB_INITSTN",GLB.INITSTN);
        session.setAttribute("GLB_USERNAME",GLB.USERNAME);
        session.setAttribute("GLB_PASSWORD",GLB.PASSWORD);
        if (GLB.AUDITJOB == true)
        {
           session.setAttribute("GLB_AUDITJOB","TRUE");
        }
        else
        {
           session.setAttribute("GLB_AUDITJOB","FALSE");
        }
        session.setAttribute("GLB_AUDITACT",GLB.AUDITACT);
        session.setAttribute("GLB_FILESDIR",GLB.FILESDIR);
        session.setAttribute("GLB_AUDITDIR",GLB.AUDITDIR);
        session.setAttribute("GLB_BACKUPDIR",GLB.BACKUPDIR);
        session.setAttribute("GLB_TEMPDIR",GLB.TEMPDIR);
        session.setAttribute("GLB_SYSTEMDIR",GLB.SYSTEMDIR);
        session.setAttribute("GLB_DOCDIR",GLB.DOCDIR);
        session.setAttribute("GLB_EXTRACTSDIR",GLB.EXTRACTSDIR);
        session.setAttribute("GLB_MSGSDIR",GLB.MSGSDIR);
        session.setAttribute("GLB_ICONSDIR",GLB.ICONSDIR);
        session.setAttribute("GLB_BATCHDIR",GLB.BATCHDIR);
        session.setAttribute("GLB_CLONEAPPDIR",GLB.CLONEAPPDIR);
        session.setAttribute("GLB_CLONERTSDIR",GLB.CLONERTSDIR);
        session.setAttribute("GLB_MYSTATUS",GLB.MYSTATUS);
        session.setAttribute("GLB_PARAM",GLB.PARAM);
        session.setAttribute("GLB_DEVICE",GLB.DEVICE);
        session.setAttribute("GLB_SERVER",GLB.SERVER);
        session.setAttribute("GLB_DBNAME",GLB.DBNAME);
        session.setAttribute("GLB_FULLSTN",GLB.FULLSTN);
        session.setAttribute("GLB_DBTYPE",GLB.DBTYPE);
        session.setAttribute("GLB_LANGUAGE",GLB.LANGUAGE);
        session.setAttribute("GLB_REPLANG",GLB.REPLANG);
        session.setAttribute("GLB_STALANG",GLB.STALANG);
    }
    catch (Exception e)
    {
      GLB.ERRLINE = e.getMessage();
      ShowErrorMsg (e,"WriteRepClipboard");
    }
}

/* checkTimeout */
public boolean checkTimeout(HttpSession session)
    throws Exception
{
    try
    {
        if ((session.getAttribute("GLB_DSN") == null) &&
            (session.getAttribute("GLB_USERCODE") == null) &&
            (session.getAttribute("GLB_STN") == null) &&
            (session.getAttribute("GLB_INITSTN") == null) &&
            (session.getAttribute("GLB_TEACH") == null ) &&
            (session.getAttribute("GLB_REQUEST") == null) &&
            (session.getAttribute("GLB_CALL") == null) &&
            (session.getAttribute("GLB_USERNAME") == null) &&
            (session.getAttribute("GLB_PASSWORD") == null) &&
            (session.getAttribute("GLB_WORK") == null) &&
            (session.getAttribute("GLB_AUDITJOB") == null) &&
            (session.getAttribute("GLB_AUDITACT") == null) &&
            (session.getAttribute("GLB_FILESDIR") == null) &&
            (session.getAttribute("GLB_AUDITDIR") == null) &&
            (session.getAttribute("GLB_BACKUPDIR") == null) &&
            (session.getAttribute("GLB_TEMPDIR") == null) &&
            (session.getAttribute("GLB_SYSTEMDIR") == null) &&
            (session.getAttribute("GLB_DOCDIR") == null) &&
            (session.getAttribute("GLB_EXTRACTSDIR") == null) &&
            (session.getAttribute("GLB_MSGSDIR") == null) &&
            (session.getAttribute("GLB_ICONSDIR") == null) &&
            (session.getAttribute("GLB_BATCHDIR") == null) &&
            (session.getAttribute("GLB_CLONEAPPDIR") == null) &&
            (session.getAttribute("GLB_CLONERTSDIR") == null) &&
            (session.getAttribute("GLB_SERVER") == null) &&
            (session.getAttribute("GLB_DBNAME") == null) &&
            (session.getAttribute("GLB_ReportFilename") == null) &&
            (session.getAttribute("GLB_FULLSTN") == null))
        {
            return(true);
        }
        else
        {   return(false);
        }
    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"checkTimeout");
        return(true);
    }
}

/* replaceSpecialCharacterString */
public String replaceSpecialCharacter(String pStr)
    throws Exception
{
    try
    {
        String auxStr = "";
        for (int i = 0; i < pStr.length(); i++)
        {
    	   if (pStr.charAt(i) == '\"')
            {
                auxStr = auxStr.concat("\\\"");
            }
            else if (pStr.charAt(i) == '\\')
            {
        	   auxStr = auxStr.concat("\\\\");
            }
            else if (pStr.charAt(i) == '/')
            {
               auxStr = auxStr.concat("\\/");
            }
            else
            {
                auxStr = auxStr.concat(pStr.substring(i, i + 1));
            }
        }
        return(auxStr);
    }
    catch (Exception e)
    {
      ShowErrorMsg (e,"replaceSpecialCharacter");
      return(pStr);
    }
}
//fim métodos exclusivos

/* equals */


/* equals BigDecimal e BigDecimal */
public boolean equals(BigDecimal pSource, BigDecimal pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = pSource;
	BigDecimal bigDec2 = pDest;


        if (bigDec1.compareTo(bigDec2) == 0)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "equals");
        return (false);
    }
}

/* equals BigDecimal e double */
public boolean equals(BigDecimal pSource, double pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = pSource;
	BigDecimal bigDec2 = new BigDecimal((new Double(pDest)).toString());


        if (bigDec1.compareTo(bigDec2) == 0)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "equals");
        return (false);
    }
}

/* equals double e BigDecimal */
public boolean equals(double pSource, BigDecimal pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = new BigDecimal((new Double(pSource)).toString());
	BigDecimal bigDec2 = pDest;


        if (bigDec1.compareTo(bigDec2) == 0)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "equals");
        return (false);
    }
}


/* equals BigDecimal e String */
public boolean equals(BigDecimal pSource, String pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = pSource;
	BigDecimal bigDec2 = new BigDecimal(pDest);


        if (bigDec1.compareTo(bigDec2) == 0)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "equals");
        return (false);
    }
}

/* equals String e BigDecimal */
public boolean equals(String pSource, BigDecimal pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = new BigDecimal(pSource);
	BigDecimal bigDec2 = pDest;


        if (bigDec1.compareTo(bigDec2) == 0)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "equals");
        return (false);
    }
}


/* greater */


/* greater BigDecimal e BigDecimal */
public boolean greater(BigDecimal pSource, BigDecimal pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = pSource;
	BigDecimal bigDec2 = pDest;


        if (bigDec1.compareTo(bigDec2) == 1)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "greater");
        return (false);
    }
}

/* greater BigDecimal e double */
public boolean greater(BigDecimal pSource, double pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = pSource;
	BigDecimal bigDec2 = new BigDecimal((new Double(pDest)).toString());


        if (bigDec1.compareTo(bigDec2) == 1)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "greater");
        return (false);
    }
}

/* greater double e BigDecimal */
public boolean greater(double pSource, BigDecimal pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = new BigDecimal((new Double(pSource)).toString());
	BigDecimal bigDec2 = pDest;


        if (bigDec1.compareTo(bigDec2) == 1)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "greater");
        return (false);
    }
}


/* greater BigDecimal e String */
public boolean greater(BigDecimal pSource, String pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = pSource;
	BigDecimal bigDec2 = new BigDecimal(pDest);


        if (bigDec1.compareTo(bigDec2) == 1)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "greater");
        return (false);
    }
}

/* greater String e BigDecimal */
public boolean greater(String pSource, BigDecimal pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = new BigDecimal(pSource);
	BigDecimal bigDec2 = pDest;


        if (bigDec1.compareTo(bigDec2) == 1)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "greater");
        return (false);
    }
}


/* less */


/* less BigDecimal e BigDecimal */
public boolean less(BigDecimal pSource, BigDecimal pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = pSource;
	BigDecimal bigDec2 = pDest;


        if (bigDec1.compareTo(bigDec2) == -1)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "less");
        return (false);
    }
}

/* less BigDecimal e double */
public boolean less(BigDecimal pSource, double pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = pSource;
	BigDecimal bigDec2 = new BigDecimal((new Double(pDest)).toString());


        if (bigDec1.compareTo(bigDec2) == -1)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "less");
        return (false);
    }
}

/* less double e BigDecimal */
public boolean less(double pSource, BigDecimal pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = new BigDecimal((new Double(pSource)).toString());
	BigDecimal bigDec2 = pDest;


        if (bigDec1.compareTo(bigDec2) == -1)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "less");
        return (false);
    }
}


/* less BigDecimal e String */
public boolean less(BigDecimal pSource, String pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = pSource;
	BigDecimal bigDec2 = new BigDecimal(pDest);


        if (bigDec1.compareTo(bigDec2) == -1)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "less");
        return (false);
    }
}

/* less String e BigDecimal */
public boolean less(String pSource, BigDecimal pDest)
    throws Exception
{
    try
    {
	BigDecimal bigDec1 = new BigDecimal(pSource);
	BigDecimal bigDec2 = pDest;


        if (bigDec1.compareTo(bigDec2) == -1)
        {
              return (true);
   	}
   	else
   	{
              return (false);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "less");
        return (false);
    }
}


/* C O M A N D O S */
/* Comandos iguais */

/* ACCESS.EXT CONNECT */
public Connection accessextConnect(String pDSN, String pUser, String pPassword)
 throws Exception
{
    try
    {
        Connection myConnection;
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        myConnection = DriverManager.getConnection("jdbc:odbc:" + pDSN, pUser, pPassword);
        myConnection.setAutoCommit(true);
        return(myConnection);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "accessextConnect");
        return (null);
    }
}

public Connection accessextConnect(String pDSN, String pUser, String pPassword, String pDbType)
 throws Exception
{
    try
    {
        Connection myConnection;
        String dbType = pDbType.trim().toUpperCase();
        if (dbType.equals("ORACLE")== true)
        {   Class.forName("oracle.jdbc.driver.OracleDriver");
    		myConnection = DriverManager.getConnection("jdbc:oracle:thin:@" + pDSN, pUser, pPassword);
    		myConnection.setAutoCommit(false);
        }
        else if (dbType.equals("SQL-SERVER")== true)
        {	//Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
       		//myConnection = DriverManager.getConnection("jdbc:microsoft:sqlserver://" + GLB.SERVER + ":1433;SelectMethod=cursor;User=" + pUser + ";PassWord=" + pPassword + ";DataBaseName="  + pDSN);
			//myConnection.setAutoCommit(false);
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        	myConnection = DriverManager.getConnection("jdbc:odbc:" + pDSN, pUser, pPassword);
        	myConnection.setAutoCommit(true);
        }
        else if (dbType.equals("ACCESS")== true)
        {	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        	myConnection = DriverManager.getConnection("jdbc:odbc:" + pDSN, pUser, pPassword);
        	myConnection.setAutoCommit(true);
        }
        else if (dbType.equals("DB2") == true)
        {
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            myConnection = DriverManager.getConnection("jdbc:db2:" + pDSN, pUser, pPassword);
            myConnection.setAutoCommit(false);
        }
        else
        {	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        	myConnection = DriverManager.getConnection("jdbc:odbc:" + pDSN, pUser, pPassword);
        	myConnection.setAutoCommit(true);
    	}
        return(myConnection);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "accessextConnect");
        return (null);
    }
}

public Connection accessextConnect(String pDSN, String pUser, String pPassword, String pServer, String pDbType)
 throws Exception
{
    try
    {
        Connection myConnection;
        String dbType = pDbType.trim().toUpperCase();
        if (dbType.equals("ORACLE")== true)
        {   Class.forName("oracle.jdbc.driver.OracleDriver");
            myConnection = DriverManager.getConnection("jdbc:oracle:thin:@" + pDSN, pUser, pPassword);
            myConnection.setAutoCommit(false);
        }
        else if (dbType.equals("SQL-SERVER")== true)
        {   Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
            myConnection = DriverManager.getConnection("jdbc:microsoft:sqlserver://" + pServer + ":1433;SelectMethod=cursor;User=" + pUser + ";PassWord=" + pPassword + ";DataBaseName="  + pDSN);
            myConnection.setAutoCommit(true);
        }
        else if (dbType.equals("ACCESS")== true)
        {   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            myConnection = DriverManager.getConnection("jdbc:odbc:" + pDSN, pUser, pPassword);
            myConnection.setAutoCommit(true);
        }
        else if (dbType.equals("DB2") == true)
        {
            Class.forName("com.ibm.db2.jcc.DB'2Driver");
            myConnection = DriverManager.getConnection("jdbc:db2:" + pDSN, pUser, pPassword);
            myConnection.setAutoCommit(false);
        }
        else
        {   Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            myConnection = DriverManager.getConnection("jdbc:odbc:" + pDSN, pUser, pPassword);
            myConnection.setAutoCommit(true);
        }
        return(myConnection);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "accessextConnect");
        return (null);
    }
}

/* ACCESS.EXT COMMIT */
public void accessextCommit(Connection pConnect)
 throws Exception
{
    try
    {
        pConnect.commit();
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "accessextCommit");
    }
}


/* ACCESS.EXT DISCONNECT */
public void accessextDisconnect(Connection pConnect,Statement pStatement, ResultSet pResultSet)
 throws Exception
{
    try
    {   pConnect.commit();
        if (pResultSet!=null)
        {   pResultSet.close();
            pResultSet = null;
        }
        if (pStatement!=null)
        {
            pStatement.close();
            pStatement=null;
        }

        pConnect.close();

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "accessextDisconnect");
    }
}


/* ACCESS.EXT EXECUTE */
public ResultSet accessextExecute(Connection pConnect, Statement pStatement,String pSql)
    throws Exception
{
    try
    {   pSql = pSql.trim();
        if (pSql.substring(0,pSql.indexOf(" ")).toUpperCase().equals("SELECT") == true)
        {
            return(accessextSelect(pConnect, pStatement,pSql));
        }
        else
        {
            accessextUpdate(pConnect, pStatement, pSql);
            return(null);
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "accessextExecute");
    	return(null);
    }

}

public ResultSet accessextSelect(Connection pConnect, Statement pStatement,String pSql)
    throws Exception
{
	try
	{   GLB.STATUS = "";

        pStatement = pConnect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		return(pStatement.executeQuery(pSql));
	}
	catch (Exception e)
    {
        GLB.STATUS = "*****";
    	ShowErrorMsg (e, "accessextSelect");
    	return(null);
    }
}

public void accessextUpdate(Connection pConnect, Statement pStatement, String pSql)
    throws Exception
{
	try
	{
	    GLB.STATUS = "";
        pStatement = pConnect.createStatement();
        if (pStatement.executeUpdate(pSql) == 0)
		{
		  GLB.STATUS = "*****";
        }
        if (pStatement!=null)
		{
			pStatement.close();
			pStatement=null;
    	}

	}
	catch (Exception e)
    {
        GLB.STATUS = "*****";
    	ShowErrorMsg (e, "accessextUpdate");
    }
}


///
public ResultSet accessextSelect(Statement pStatement,String pSql)
    throws Exception
{
    try
    {   GLB.STATUS = "";
        return(pStatement.executeQuery(pSql));
    }
    catch (Exception e)
    {
        GLB.STATUS = "*****";
        ShowErrorMsg (e, "accessextSelect");
        return(null);
    }
}

public void accessextUpdate(Statement pStatement, String pSql)
    throws Exception
{
    try
    {
        GLB.STATUS = "";
        if (pStatement.executeUpdate(pSql) == 0)
        {
          GLB.STATUS = "*****";
        }
        if (pStatement!=null)
        {
            pStatement.close();
            pStatement=null;
        }

    }
    catch (Exception e)
    {
        GLB.STATUS = "*****";
        ShowErrorMsg (e, "accessextUpdate");
    }
}


/* ACCESS.EXT GETFIELD */
public String accessextGetField(ResultSet pResultSet, String pTableField, String pDataField)
   throws Exception
{
	try
	{
        pDataField = pResultSet.getString(pTableField);
        if (pDataField == null)
        {
            pDataField = "";
        }
		return(pDataField);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "accessextGetFieldd");
    	return("");
    }
}

public String accessextGetField(ResultSet pResultSet, int pTableField, String pDataField)
   throws Exception
{
	try
	{
        pDataField = pResultSet.getString(pTableField);
        if (pDataField == null)
        {
            pDataField = "";
        }
		return(pDataField);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "accessextGetField");
        return ("");
    }
}

public double accessextGetField(ResultSet pResultSet, String pTableField, double pDataField)
   throws Exception
{
	try
	{
		pDataField = pResultSet.getDouble(pTableField);
		return(pDataField);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "accessextGetField");
        return (0.0);
    }
}

public double accessextGetField(ResultSet pResultSet, int pTableField, double pDataField)
   throws Exception
{
    try
    {
        pDataField = pResultSet.getDouble(pTableField);
        return(pDataField);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "accessextGetField");
        return (0.0);
    }
}
/* accessextGetField --> BigDecimal */
public BigDecimal accessextGetField(ResultSet pResultSet, String pTableField, BigDecimal pDataField)
   throws Exception
{
   try
   {
        pDataField = pResultSet.getBigDecimal(pTableField);
        if (pDataField == null)
        {
            pDataField = bigZeros;
        }
        return(pDataField);
   }
   catch (Exception e)
   {
   	ShowErrorMsg (e, "accessextGetField");
        return (new BigDecimal(0));
   }
}


/* accessextGetField --> BigDecimal */
public BigDecimal accessextGetField(ResultSet pResultSet, int pTableField, BigDecimal pDataField)
   throws Exception
{
   try
   {
        pDataField = pResultSet.getBigDecimal(pTableField);
        if (pDataField == null)
        {
            pDataField = bigZeros;
        }
        return(pDataField);
   }
   catch (Exception e)
   {
       ShowErrorMsg (e, "accessextGetField");
       return (new BigDecimal(0));
   }
}

/* ACCESS.EXT MOVEFIRST */
public void accessextMoveFirst(ResultSet pResultSet)
    throws Exception
{
	try
	{
		if (pResultSet.first())
		{
			GLB.STATUS = "";
		}
		else
		{
			GLB.STATUS = "*****";
		}
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "accessextMoveFirst");

    }
}

/* ACCESSEXT.MOVELAST */
public void accessextMoveLast(ResultSet pResultSet)
  throws Exception
{
    try
    {
		if (pResultSet.last())
		{
			GLB.STATUS = "";
		}
		else
		{
			GLB.STATUS = "*****";
		}
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "accessextMoveLast");
    }
}

/* ACCESSSEXT MOVENEXT */
public void accessextMoveNext(ResultSet pResultSet)
  throws Exception
{
	try
	{
		if (pResultSet.next())
		{
			GLB.STATUS = "";
		}
		else
		{
			GLB.STATUS = "*****";
		}
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "accessextMoveNext");
    }
}

/* ACCESSEXT MOVEPREVIOUS */
public void accessextMovePrevious(ResultSet pResultSet)
   throws Exception
{
	try
	{
		if (pResultSet.previous())
		{
			GLB.STATUS = "";
		}
		else
		{
			GLB.STATUS = "*****";
		}
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "accessextMovePrevious");
    }
}

/* ADD - double para double, retornando double */
public double add(double pNumber1, double pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
		BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
		bigDec2 = bigDec2.add(bigDec1);
		return(newFix(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return (0.0);
    }
}

/* ADD - BigDecimal para double, retornando double */
public double add(BigDecimal pNumber1, double pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{	BigDecimal bigDec1 = pNumber1;
		BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
		bigDec2 = bigDec2.add(bigDec1);
		return(newFix(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return (0.0);
    }
}

/* ADD - double para BigDecimal retornando BigDecimal */
public BigDecimal add(double pNumber1, BigDecimal pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
		BigDecimal bigDec2 = pNumber2;
		bigDec2 = bigDec2.add(bigDec1);
		return(fixBigDec(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return (bigZeros);
    }
}

/* ADD - BigDecimal para BigDecimal retornando BigDecimal */
public BigDecimal add(BigDecimal pNumber1, BigDecimal pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{	BigDecimal bigDec1 = pNumber1;
		BigDecimal bigDec2 = pNumber2;
		bigDec2 = bigDec2.add(bigDec1);
		return(fixBigDec(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return ( new BigDecimal(0) );
    }
}

/* ADD com 3 parametros retornando double --> BigDecimal */
public double add(double pNumber1, double pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
		BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
                BigDecimal bigDec3 = new BigDecimal(0);
		bigDec3 = bigDec2.add(bigDec1);
		return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return (0.0);
    }
}


public double add(double pNumber1, BigDecimal pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
		BigDecimal bigDec2 = pNumber2;
                BigDecimal bigDec3 = new BigDecimal(0);
		bigDec3 = bigDec2.add(bigDec1);
		return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return (0.0);
    }
}

public double add(BigDecimal pNumber1, double pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{
                BigDecimal bigDec1 = pNumber1;
		BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
                BigDecimal bigDec3 = new BigDecimal(0);
		bigDec3 = bigDec2.add(bigDec1);
		return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return (0.0);
    }
}

public double add(BigDecimal pNumber1, BigDecimal pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{
                BigDecimal bigDec1 = pNumber1;
		BigDecimal bigDec2 = pNumber2;
                BigDecimal bigDec3 = new BigDecimal(0);
		bigDec3 = bigDec2.add(bigDec1);
		return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return (0.0);
    }
}

/* ADD com 3 parametros retornando BigDecimal --> BigDecimal */
public BigDecimal add(BigDecimal pNumber1, BigDecimal pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{
                BigDecimal bigDec1 = pNumber1;
		BigDecimal bigDec2 = pNumber2;
                BigDecimal bigDec3 = new BigDecimal(0);
		bigDec3 = bigDec2.add(bigDec1);
		return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return (bigZeros);
    }
}

public BigDecimal add(double pNumber1, BigDecimal pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
		BigDecimal bigDec2 = pNumber2;
                BigDecimal bigDec3 = new BigDecimal(0);
		bigDec3 = bigDec2.add(bigDec1);
		return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return (bigZeros);
    }
}

public BigDecimal add(BigDecimal pNumber1, double pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{
                BigDecimal bigDec1 = pNumber1;
		BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
                BigDecimal bigDec3 = new BigDecimal(0);
		bigDec3 = bigDec2.add(bigDec1);
		return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return (bigZeros);
    }
}

public BigDecimal add(double pNumber1, double pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
	try
	{	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
		BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
                BigDecimal bigDec3 = new BigDecimal(0);
		bigDec3 = bigDec2.add(bigDec1);
		return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "add");
        return (bigZeros);
    }
}

/* add */
public BigDecimal add(double pNumber1, double pNumber2)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());

        return( bigDec2.add(bigDec1) );
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "add");
        return (bigZeros);
    }
}

public BigDecimal add(BigDecimal pNumber1, double pNumber2)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = pNumber1;
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());

        return( bigDec2.add(bigDec1) );
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "add");
        return (bigZeros);
    }
}

public BigDecimal add(double pNumber1, BigDecimal pNumber2)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = pNumber2;

        return( bigDec2.add(bigDec1) );
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "add");
        return (bigZeros);
    }
}

public BigDecimal add(BigDecimal pNumber1, BigDecimal pNumber2)
    throws Exception
{
    try
    {
        return( pNumber2.add(pNumber1) );
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "add");
        return (bigZeros);
    }
}

/* ATTACH */
public String attach(String pText1, String pText2, int pText2Length)
    throws Exception
{
	try
    {
    	pText2 = rTrim(pText2) + pText1;
    	pText2 = fix(pText2,pText2Length);

    	GLB.LENGTH = pText2.length();
    	return(pText2);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "attach");
        return ("");
    }
}

/* ATTACH&SPACES */
public String attachAndSpace(String pText1, String pText2, int pText2Length)
    throws Exception
{
	try
	{
    	if (pText2.equals("") == true)
    	{
       		pText2 = pText1;
    	}
    	else
    	{
       		pText2 = rTrim(pText2) + " " + pText1;
    	}

    	pText2 = fix(pText2,pText2Length);

    	GLB.LENGTH = pText2.length();
    	return(pText2);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "attachAndSpace");
        return ("");
    }
}

/*AUTO */
/* AUTO String para String */
public String auto (String pSource, String pDest, int pDestLength)
    throws Exception
{
	try
	{
    	pDest = fix(pSource,pDestLength);
    	return(pDest);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "auto");
        return ("");
    }
}

/* AUTO String para double */
public double auto (String pSource, double pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
        pSource = pSource.trim();
	    if (isNumeric(pSource) == false)
    	{
    		return(0.0);
    	}
		BigDecimal bigDec = new BigDecimal(pSource);
    	pDest = newFix(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");

    	return (pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "auto");
        return (0.0);
    }
}

/* AUTO String para BigDecimal --> BigDecimal */
public BigDecimal auto (String pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
        pSource = pSource.trim();
	    if (isNumeric(pSource) == false)
    	{
           return( new BigDecimal(0) );
    	}

	    BigDecimal bigDec = new BigDecimal(pSource);
    	pDest = fixBigDec(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");

    	return (pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "auto");
        return ( new BigDecimal(0) );
    }
}

/* AUTO double para double */
public double auto(double pSource, double pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
	    BigDecimal bigDec = new BigDecimal((new Double(pSource)).toString());
    	pDest = newFix(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");

    	return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "auto");
        return (0.0);
    }
}

/* AUTO BigDecimal para double */
public double auto(BigDecimal pSource, double pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
	    BigDecimal bigDec = pSource;
    	pDest = newFix(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");

    	return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "auto");
        return (0.0);
    }
}


/* AUTO double para BigDecimal --> BigDecimal */
public BigDecimal auto(double pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
	BigDecimal bigDec = new BigDecimal((new Double(pSource)).toString());
    	pDest = fixBigDec(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");

    	return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "auto");
        return (new BigDecimal(0));
    }
}

/* AUTO BigDecimal para BigDecimal --> BigDecimal */
public BigDecimal auto(BigDecimal pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
	BigDecimal bigDec = pSource;
    	pDest = fixBigDec(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");

    	return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "auto");
        return (new BigDecimal(0));
    }
}


/* CALL */
public void call(String pProgram,String pParam)
throws Exception
{
   try
   {
		Process myProcess = Runtime.getRuntime().exec("cmd /C "+ pProgram + " " + pParam);
   }
   catch (Exception e)
   {
    	ShowErrorMsg (e, "call");
   }
}

/* COMPUTE */
public double compute(double pExpression, int pResultLength, int pResultDecimals, String pSigned)
     throws Exception
{
     try
     {
		BigDecimal bigDec = new BigDecimal((new Double(pExpression)).toString());
    	return(newFix(bigDec, pResultLength, pResultDecimals, pSigned, "TRUNCATE"));
     }
     catch (Exception e)
     {
    	ShowErrorMsg (e, "compute");
        return (0.0);
     }
}

/* COMPUTE para double */
public double compute(BigDecimal pSource, double pDest, int pDestLength, int pDestDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec = pSource;
        pDest = newFix(bigDec, pDestLength, pDestDecimals, pSigned, pRounded);
        return(pDest);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "compute");
        return (0.0);
    }
}


/* COMPUTE para BigDecimal */
public BigDecimal compute(BigDecimal pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pSigned,String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec = pSource;
        pDest = fixBigDec(bigDec, pDestLength, pDestDecimals, pSigned, pRounded);
        return(pDest);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "compute");
        return (new BigDecimal (0));
    }
}

/* CONTRA */
public double contra (double pNumber)
  throws Exception
{
	try
	{
 	return( pNumber * (-1));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "contra");
        return (0.0);
    }
}

/* CONTRA --> BigDecimal */
public BigDecimal contra (BigDecimal pNumber)
   throws Exception
{
   try
   {
	return( pNumber.multiply( new BigDecimal(-1) ) );
   }
   catch (Exception e)
   {
    	ShowErrorMsg (e, "contra");
        return ( new BigDecimal (0) );
   }
}


/* DETACH */
public String detach (String pItem1, String pItem2, double pPosition, String pDelimiter, int pItem2Length)
   throws Exception
{
	try
	{
	    boolean findDelimiter = true;
		GLB.DETACHDEL= -1;
		GLB.DETACHSTR= "";

		if (pPosition == 0)
		{
			pPosition = 1;
		}

        if (pDelimiter.equals("") == true)
        {
            pDelimiter = " ";
            findDelimiter = false;
        }

  		GLB.DETACHDEL = pItem1.indexOf(pDelimiter,(int)pPosition-1);

		if (GLB.DETACHDEL == -1)
		{
			GLB.DETACHDEL = pItem1.length();
			findDelimiter = false;
		}

		if (pPosition <= pItem1.length())
		{
			GLB.DETACHSTR = pItem1.substring((int)pPosition-1,GLB.DETACHDEL);
		}

        if (pDelimiter.equals("") == false )
        {
            GLB.DETACHDEL++;
        }

        if (findDelimiter==true)
        {
            pItem2= GLB.DETACHSTR;

            if (pItem2.length() > pItem2Length)
            {
                pItem2 = pItem2.substring(0,pItem2Length);
            }

            if(pDelimiter.equals("") == false && GLB.DETACHDEL<pItem1.trim().length() )
            {
                while (pItem1.substring(GLB.DETACHDEL,GLB.DETACHDEL+1).equals(pDelimiter))
                {
                    GLB.DETACHDEL++;
                }
            }
        }
        else
        {
            pItem2 = fix(GLB.DETACHSTR,pItem2Length);
        }

		GLB.LENGTH = pItem2.length();

		return(pItem2);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "detach");
        return ("");
    }
}


/* DISCOUNTCASH */
public double discountcash (double pInit, double pRate, double pTime, String pAction, double pResult,int pLength, int pDecimals, String pSigned)
   throws Exception
{
   try
   {
	pAction = pAction.toUpperCase();
	pRate = divide(100,pRate,10,8,"UNSIGNED","TRUNCATE");
	if (pAction.equals("COMPOUND") == true)
	{
            pResult= pInit * (Math.pow((1+pRate),pTime));
	}
	else if (pAction.equals("DISCOUNT") == true)
	{
             pResult= pInit / (Math.pow((1+pRate),pTime));
	}
	else if (pAction.equals("PV.FLOW") == true)
	{
             pResult= pInit * ((Math.pow((1+pRate),pTime)-1)	/ (Math.pow((1+pRate),pTime) * Math.log(1+pRate) ) );
	}
	else if (pAction.equals("FLOW.PV") == true)
	{
             pResult= pInit * ( ( Math.pow((1+pRate),pTime) * Math.log(1+pRate) / ( ( Math.pow((1+pRate),pTime) - 1 ) ) ) );
	}
	else if (pAction.equals("FV.FLOW") == true)
	{
             pResult= pInit * ( ( (Math.pow((1+pRate),pTime) -1) / Math.log(1+pRate) ) );
	}
	else if (pAction.equals("FLOW.FV") == true)
	{
             pResult= pInit * ( Math.log((1+pRate) / ( Math.pow((1+pRate),pTime) -1) - 1 ) );
	}
	else if (pAction.equals("MORTGAGE") == true)
	{
             pResult= pInit * pRate * Math.pow((1+pRate),pTime) / ( Math.pow((1+pRate),pTime) -1 );
	}
	else if (pAction.equals("PV.ANNUITY") == true)
	{
             pResult= pInit * ( Math.pow((1+pRate),pTime) - 1 ) / ( pRate * Math.pow((1+pRate),pTime) );
	}
	else if (pAction.equals("FV.ANNUITY") == true)
	{
             pResult= pInit * ( Math.pow((1+pRate),pTime) - 1 ) / pRate ;
	}
	else if (pAction.equals("SINK") == true)
	{
             pResult= pInit * pRate / ( Math.pow((1+pRate),pTime) - 1 );
	}

        BigDecimal bigDec = new BigDecimal((new Double(pResult)).toString());

        return(newFix(bigDec, pLength, pDecimals, pSigned, "TRUNCATE"));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "discountcash");
        return (0.0);
    }
}

/* DISCOUNTCASH --> BigDecimal */
public BigDecimal discountcash (double pInit, double pRate, double pTime, String pAction, BigDecimal pResultado,int pLength, int pDecimals, String pSigned)
   throws Exception
{
   try
   {
        double pResult = 0;

	pAction = pAction.toUpperCase();

	pRate = divide(100, pRate, 10, 8, "UNSIGNED", "TRUNCATE");

	if (pAction.equals("COMPOUND") == true)
	{
            pResult= pInit * (Math.pow((1+pRate),pTime));
	}
	else if (pAction.equals("DISCOUNT") == true)
	{
             pResult= pInit / (Math.pow((1+pRate),pTime));
	}
	else if (pAction.equals("PV.FLOW") == true)
	{
             pResult= pInit * ((Math.pow((1+pRate),pTime)-1)	/ (Math.pow((1+pRate),pTime) * Math.log(1+pRate) ) );
	}
	else if (pAction.equals("FLOW.PV") == true)
	{
             pResult= pInit * ( ( Math.pow((1+pRate),pTime) * Math.log(1+pRate) / ( ( Math.pow((1+pRate),pTime) - 1 ) ) ) );
	}
	else if (pAction.equals("FV.FLOW") == true)
	{
             pResult= pInit * ( ( (Math.pow((1+pRate),pTime) -1) / Math.log(1+pRate) ) );
	}
	else if (pAction.equals("FLOW.FV") == true)
	{
             pResult= pInit * ( Math.log((1+pRate) / ( Math.pow((1+pRate),pTime) -1) - 1 ) );
	}
	else if (pAction.equals("MORTGAGE") == true)
	{
             pResult= pInit * pRate * Math.pow((1+pRate),pTime) / ( Math.pow((1+pRate),pTime) -1 );
	}
	else if (pAction.equals("PV.ANNUITY") == true)
	{
             pResult= pInit * ( Math.pow((1+pRate),pTime) - 1 ) / ( pRate * Math.pow((1+pRate),pTime) );
	}
	else if (pAction.equals("FV.ANNUITY") == true)
	{
             pResult= pInit * ( Math.pow((1+pRate),pTime) - 1 ) / pRate ;
	}
	else if (pAction.equals("SINK") == true)
	{
             pResult= pInit * pRate / ( Math.pow((1+pRate),pTime) - 1 );
	}

        BigDecimal bigDec = new BigDecimal((new Double(pResult)).toString());

        return(fixBigDec(bigDec, pLength, pDecimals, pSigned, "TRUNCATE"));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "discountcash");
        return ( new BigDecimal(0) );
    }
}

//
// discountcash
//
public BigDecimal discountcash (BigDecimal pInit, BigDecimal pRate, BigDecimal pTime, String pAction, BigDecimal pResultado,int pLength, int pDecimals, String pSigned)
throws Exception
{
    try
    {
        BigDecimal pResult = null;


        double rate;

        pAction = pAction.toUpperCase();
        pTime = pTime.abs();

        pRate = divide(100, pRate, 10, 8, "UNSIGNED", "TRUNCATE");
        rate = pRate.doubleValue();

        if (pAction.equals("COMPOUND") == true)
        {
              /* Future Value from Present Value
               * Calculation: SN = P . (1+i)^n */

              pResult = pInit.multiply(pow(add(1,pRate),pTime));

              //pResult= pInit * (Math.pow((1+pRate),pTime));

        }
        else if (pAction.equals("DISCOUNT") == true)
        {
              /* Present Value yielding Future Value
               * Calculation: P = SN / (1+i)^n */

              pResult = pInit.divide(pow(add(1,pRate),pTime), 20, BigDecimal.ROUND_CEILING);
              //pResult= pInit / (Math.pow((1+pRate),pTime));
        }
        else if (pAction.equals("PV.FLOW") == true)
        {
              /* Present Value
               * Calculation : P = A* . ( (1+i)^n - 1) / ( (1+i)^n . ln(1+i) ) */

              BigDecimal termo1 = pow(add(1,pRate),pTime).subtract(new BigDecimal(1));
              BigDecimal termo2 = pow(add(1,pRate),pTime).multiply( new BigDecimal(Math.log(1+rate)) );
              pResult = pInit.multiply(termo1).divide(termo2, 20, BigDecimal.ROUND_CEILING);
              //pResult= pInit * ((Math.pow((1+pRate),pTime)-1) / (Math.pow((1+pRate),pTime) * Math.log(1+pRate) ) );
        }
        else if (pAction.equals("FLOW.PV") == true)
        {
              /* Annual Rate of Flow
               * Calculation : A* = P . ( (1+i)^n . ln(1+i) ) / ( (1+i)^n - 1) */

              BigDecimal termo1 = pow(add(1,pRate),pTime).multiply( new BigDecimal(Math.log(1+rate)) );
              BigDecimal termo2 = pow(add(1,pRate),pTime).subtract(new BigDecimal(1));
              pResult = pInit.multiply((termo1).divide(termo2, 20, BigDecimal.ROUND_CEILING));
              //pResult= pInit * ( ( Math.pow((1+pRate),pTime) * Math.log(1+pRate) / ( ( Math.pow((1+pRate),pTime) - 1 ) ) ) );
        }
        else if (pAction.equals("FV.FLOW") == true)
        {
              /* Future Value
               * Calculation : SN = A* . ( (1+i)^n - 1 ) / ln(1+i) */

              BigDecimal termo1 = pow(add(1,pRate),pTime).subtract(new BigDecimal(1));
              BigDecimal termo2 = new BigDecimal(Math.log(1+rate));
              pResult = pInit.multiply(termo1).divide(termo2);
              //pResult= pInit * ( ( (Math.pow((1+pRate),pTime) -1) / Math.log(1+pRate) ) );
        }
        else if (pAction.equals("FLOW.FV") == true)
        {
              /* Rate of Flow yielding Future Value
                 Calculation : A* = SN . ln(1+i) / ( (1+i)^n - 1) */

              BigDecimal termo1 = new BigDecimal(Math.log(1+rate));
              BigDecimal termo2 = pow(add(1,pRate),pTime).subtract(new BigDecimal(1));
              pResult = pInit.multiply(termo1).divide(termo2);
              //pResult= pInit * ( Math.log((1+pRate) / ( Math.pow((1+pRate),pTime) -1) - 1 ) );
        }
        else if (pAction.equals("MORTGAGE") == true)
        {
              /* Annuity to repay Present Value
                 Calculation: A = P . i . (1+i)^n / ( (1+i)^n - 1) */

              BigDecimal termo1 = pow(add(1,pRate),pTime);
              BigDecimal termo2 = pow(add(1,pRate),pTime).subtract(new BigDecimal(1));
              pResult = pInit.multiply(pRate).multiply(termo1).divide(termo2, 20, BigDecimal.ROUND_CEILING);
              //pResult= pInit * pRate * Math.pow((1+pRate),pTime) / ( Math.pow((1+pRate),pTime) -1 );
        }
        else if (pAction.equals("PV.ANNUITY") == true)
        {
              /* Present Value yielding Annuity
                Calculation: P = A . ( (1+i)^n - 1) / (i . (1+i)^n ) */

              BigDecimal termo1 = pow(add(1,pRate),pTime).subtract(new BigDecimal(1));
              BigDecimal termo2 = add(1,pRate).multiply( pow(add(1,pRate),pTime) );
              pResult = pInit.multiply(termo1).divide(termo2, 20, BigDecimal.ROUND_CEILING);
              //pResult= pInit * ( Math.pow((1+pRate),pTime) - 1 ) / ( pRate * Math.pow((1+pRate),pTime) );
        }
        else if (pAction.equals("FV.ANNUITY") == true)
        {
              /* Future Value resulting from Annuity
                 Calculation: SN = A . ( (1+i)^n - 1) / i */

              BigDecimal termo1 = pow(add(1,pRate),pTime).subtract(new BigDecimal(1));
              pResult = pInit.multiply(termo1).divide(pRate, 20, BigDecimal.ROUND_CEILING);
              //pResult= pInit * ( Math.pow((1+pRate),pTime) - 1 ) / pRate ;
        }
        else if (pAction.equals("SINK") == true)
        {
              /* Annuity yielding Future Value
                 Calculation: SN . i / ( (1+i)^n - 1) */

              BigDecimal termo1 = pow(add(1,pRate),pTime).subtract(new BigDecimal(1));
              pResult = pInit.multiply(pRate).divide(termo1, 20, BigDecimal.ROUND_CEILING);
              //pResult= pInit * pRate / ( Math.pow((1+pRate),pTime) - 1 );
        }

        /*Legend os Conventions Symbols:
         * i  = interest rate
         * n  = number of time units
         * P  = present value of the financial amount
         * SN = financial amount after time period n
         * A  = annuity payment ocurring at the end of each time period
         * A* = annual rate of flow
         */

         return(fixBigDec(pResult, pLength, pDecimals, pSigned, "TRUNCATE"));
     }
     catch (Exception e)
     {
         //ShowErrorMsg (e, "discountcash");
         e.printStackTrace();
         return ( new BigDecimal(0) );
     }
}
public BigDecimal discountcash(BigDecimal pInit, BigDecimal pRate, BigDecimal pTime, String pAction, double pResult, int pLength, int pDecimals,   String pSigned)
     throws Exception
{
      return discountcash(pInit, pRate, pTime, pAction, cDec(pResult), pLength, pDecimals, pSigned);
}


/* DIVIDE - double para double */
public double divide(double pNumber1, double pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        bigDec2 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(newFix(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (0.0);
    }
}

/* DIVIDE --> BigDecimal */
public double divide(BigDecimal pNumber1, double pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = pNumber1;
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        bigDec2 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(newFix(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (0.0);
    }
}

public BigDecimal divide(double pNumber1, BigDecimal pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = pNumber2;
        bigDec2 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(fixBigDec(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (bigZeros);
    }
}

public BigDecimal divide(BigDecimal pNumber1, BigDecimal pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = pNumber1;
        BigDecimal bigDec2 = pNumber2;
        bigDec2 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(fixBigDec(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (new BigDecimal(0));
    }
}

/* DIVIDE - 3 parametros, retornando double */
public double divide(double pNumber1, double pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (0.0);
    }
}

public double divide(double pNumber1, BigDecimal pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = pNumber2;
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (0.0);
    }
}

public double divide(BigDecimal pNumber1, double pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = pNumber1;
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (0.0);
    }
}

public double divide(BigDecimal pNumber1, BigDecimal pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = pNumber1;
        BigDecimal bigDec2 = pNumber2;
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (0.0);
    }
}

/* DIVIDE - 3 parametros, retornando BigDecimal */
public BigDecimal divide(double pNumber1, double pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (bigZeros);
    }
}

public BigDecimal divide(double pNumber1, BigDecimal pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = pNumber2;
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (bigZeros);
    }
}

public BigDecimal divide(BigDecimal pNumber1, double pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = pNumber1;
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (bigZeros);
    }
}

public BigDecimal divide(BigDecimal pNumber1, BigDecimal pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = pNumber1;
        BigDecimal bigDec2 = pNumber2;
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.divide(bigDec1,pResultDecimals + 1,1);
        return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (bigZeros);
    }
}

/* divide */
public BigDecimal divide(double pNumber1, double pNumber2)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigResult = bigDec2.divide(bigDec1,18,1);

        return(bigResult);
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "divide");
        return (bigZeros);
    }
}

public BigDecimal divide(BigDecimal pNumber1, double pNumber2)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = pNumber1;
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        return(bigDec2.divide(bigDec1,18,1));
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "divide");
        return (bigZeros);
    }
}

public BigDecimal divide(double pNumber1, BigDecimal pNumber2)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = pNumber2;
        return(bigDec2.divide(bigDec1,18,1));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
        return (bigZeros);
    }
}

public BigDecimal divide(BigDecimal pNumber1, BigDecimal pNumber2)
    throws Exception
{
    try
    {
        return(pNumber2.divide(pNumber1,18,1));
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "divide");
        return (bigZeros);
    }
}

/* DIVIDE - REMAINDER */
public double remainder(double pNumber1, double pNumber2, int pResultLength)
    throws Exception
{
	try
	{
        BigDecimal bigDivisor = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDividendo = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigResult = bigDividendo.divide(bigDivisor,1);
        BigInteger intvalue = bigResult.toBigInteger();
        bigResult = bigDivisor.multiply(new BigDecimal(intvalue));
        bigResult = bigDividendo.subtract(bigResult);
        return(newFix(bigResult, pResultLength, 0, "UNSIGNED", "TRUNCATE"));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "remainder");
        return (0.0);
    }
}

public double remainder(double pNumber1, double pNumber2, int pResultLength, int pResultDecimals)
    throws Exception
{
	try
	{	BigDecimal bigDivisor = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDividendo = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigResult = bigDividendo.divide(bigDivisor,1);
        BigInteger intvalue = bigResult.toBigInteger();
        bigResult = bigDivisor.multiply(new BigDecimal(intvalue));
        bigResult = bigDividendo.subtract(bigResult);
        return(newFix(bigResult, pResultLength, pResultDecimals, "UNSIGNED", "TRUNCATE"));
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "remainder");
        return (0.0);
    }
}

/* DIVIDE - REMAINDER - retornando BigDecimal */
public BigDecimal remainder(double pNumber1, double pNumber2)
    throws Exception
{
    try
    {   BigDecimal bigDivisor = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDividendo = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigResult = bigDividendo.divide(bigDivisor,1);
        BigInteger intvalue = bigResult.toBigInteger();
        bigResult = bigDivisor.multiply(new BigDecimal(intvalue));
        bigResult = bigDividendo.subtract(bigResult);
        return(bigResult);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "remainder");
        return (bigZeros);
    }
}

public BigDecimal remainder(BigDecimal pNumber1, double pNumber2)
    throws Exception
{
	try
	{
        BigDecimal bigDivisor = pNumber1;
        BigDecimal bigDividendo = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigResult = bigDividendo.divide(bigDivisor,1);
        BigInteger intvalue = bigResult.toBigInteger();
        bigResult = bigDivisor.multiply(new BigDecimal(intvalue));
        bigResult = bigDividendo.subtract(bigResult);
        return(bigResult);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "remainder");
        return (bigZeros);
    }
}

public BigDecimal remainder(double pNumber1, BigDecimal pNumber2)
    throws Exception
{
    try
    {
        BigDecimal bigDivisor = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDividendo = pNumber2;
        BigDecimal bigResult = bigDividendo.divide(bigDivisor,1);
        BigInteger intvalue = bigResult.toBigInteger();
        bigResult = bigDivisor.multiply(new BigDecimal(intvalue));
        bigResult = bigDividendo.subtract(bigResult);
        return(bigResult);

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "remainder");
        return (bigZeros);
    }
}

public BigDecimal remainder(BigDecimal pNumber1, BigDecimal pNumber2)
    throws Exception
{
    try
    {
        BigDecimal bigDivisor = pNumber1;
        BigDecimal bigDividendo = pNumber2;
        BigDecimal bigResult = bigDividendo.divide(bigDivisor,1);
        BigInteger intvalue = bigResult.toBigInteger();
        bigResult = bigDivisor.multiply(new BigDecimal(intvalue));
        bigResult = bigDividendo.subtract(bigResult);
        return(bigResult);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "remainder");
        return (bigZeros);
    }
}

//

public BigDecimal remainder(BigDecimal pNumber1, BigDecimal pNumber2, int pResultDecimals,BigDecimal pRemainder)
    throws Exception
{
    try
    {
        BigDecimal bigDivisor = pNumber1;
        BigDecimal bigDividendo = pNumber2;
        BigDecimal bigResult = bigDividendo.divide(bigDivisor, pResultDecimals + 1,1);
        BigDecimal bigValue = bigResult.movePointRight(pResultDecimals);
        BigInteger intvalue = bigValue.toBigInteger();
        bigValue = (new BigDecimal(intvalue)).movePointLeft(pResultDecimals);
        bigResult = bigDivisor.multiply(bigValue);
        bigResult = bigDividendo.subtract(bigResult);
        return(bigResult);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "remainder");
        return (bigZeros);
    }
}

public BigDecimal remainder(double pNumber1, double pNumber2, int pResultDecimals,BigDecimal pRemainder)
    throws Exception
{
    try
    {
        BigDecimal bigDivisor = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDividendo = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigResult = bigDividendo.divide(bigDivisor, pResultDecimals + 1,1);
        BigDecimal bigValue = bigResult.movePointRight(pResultDecimals);
        BigInteger intvalue = bigValue.toBigInteger();
        bigValue = (new BigDecimal(intvalue)).movePointLeft(pResultDecimals);
        bigResult = bigDivisor.multiply(bigValue);
        bigResult = bigDividendo.subtract(bigResult);
        return(bigResult);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "remainder");
        return (bigZeros);
    }
}


public BigDecimal remainder(double pNumber1, BigDecimal pNumber2, int pResultDecimals,BigDecimal pRemainder)
    throws Exception
{
    try
    {
        BigDecimal bigDivisor = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDividendo = pNumber2;
        BigDecimal bigResult = bigDividendo.divide(bigDivisor, pResultDecimals + 1,1);
        BigDecimal bigValue = bigResult.movePointRight(pResultDecimals);
        BigInteger intvalue = bigValue.toBigInteger();
        bigValue = (new BigDecimal(intvalue)).movePointLeft(pResultDecimals);
        bigResult = bigDivisor.multiply(bigValue);
        bigResult = bigDividendo.subtract(bigResult);
        return(bigResult);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "remainder");
        return (bigZeros);
    }
}

public BigDecimal remainder(BigDecimal pNumber1, double pNumber2, int pResultDecimals ,BigDecimal pRemainder)
    throws Exception
{
    try
    {
        BigDecimal bigDivisor = pNumber1;
        BigDecimal bigDividendo = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigResult = bigDividendo.divide(bigDivisor, pResultDecimals + 1,1);
        BigDecimal bigValue = bigResult.movePointRight(pResultDecimals);
        BigInteger intvalue = bigValue.toBigInteger();
        bigValue = (new BigDecimal(intvalue)).movePointLeft(pResultDecimals);
        bigResult = bigDivisor.multiply(bigValue);
        bigResult = bigDividendo.subtract(bigResult);
        return(bigResult);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "remainder");
        return (bigZeros);
    }
}


/* FLAG */
/* FLAG String para String */
public String flag (String pSource, String pDest, int pDestLength)
    throws Exception
{
	try
	{
    	pDest = fix(pSource,pDestLength);
    	return(pDest);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "flag");
        return ("");
    }
}

/* FLAG String para double */
public double flag (String pSource, double pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
        pSource = pSource.trim();
        if (isNumeric(pSource) == false)
    	{
    		return(0.0);
    	}

	    BigDecimal bigDec = new BigDecimal(pSource);
    	pDest = newFix(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return (pDest);

    }
	catch (Exception e)
    {
    	ShowErrorMsg (e, "flag");
        return (0.0);
    }
}

/* FLAG String para BigDecimal --> Big Decimal */
public BigDecimal flag (String pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
        pSource = pSource.trim();
        if (isNumeric(pSource) == false)
    	{
    	   return(new BigDecimal(0));
    	}

	    BigDecimal bigDec = new BigDecimal(pSource);
    	pDest = fixBigDec(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return (pDest);

    }
	catch (Exception e)
    {
    	ShowErrorMsg (e, "flag");
        return (new BigDecimal(0));
    }
}


/* FLAG double para double */
public double flag (double pSource, double pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
	try
	{
		BigDecimal bigDec = new BigDecimal((new Double(pSource)).toString());
    	pDest = newFix(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return(pDest);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "flag");
        return (0.0);
    }
}

/* FLAG BigDecimal para double */
public double flag (BigDecimal pSource, double pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
	try
	{
        BigDecimal bigDec = pSource;
    	pDest = newFix(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return(pDest);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "flag");
        return (0.0);
    }
}


/* FLAG double para BigDecimal --> BigDecimal */
public BigDecimal flag (double pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
	BigDecimal bigDec = new BigDecimal((new Double(pSource)).toString());
    	pDest = fixBigDec (bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return(pDest);
    }
	catch (Exception e)
    {
    	ShowErrorMsg (e, "flag");
        return (new BigDecimal(0));
    }
}

/* FLAG BigDecimal para BigDecimal --> BigDecimal */
public BigDecimal flag (BigDecimal pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
	BigDecimal bigDec = pSource;
    	pDest = fixBigDec (bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return(pDest);
    }
	catch (Exception e)
    {
    	ShowErrorMsg (e, "flag");
        return (new BigDecimal(0));
    }
}

/* GET.CLIPAREA */
public String getCliparea(HttpSession pSession, String pSource, String pDest, int pDestLength )
    throws Exception
{
    try
    {
        String strGet;
        strGet = (String)pSession.getAttribute("CLIP->" + pSource.toUpperCase());
        if (strGet==null)
        {
           strGet = "";
        }
        pDest = move(strGet, pDest,pDestLength);
        return(pDest);

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "getCliparea");
        return("");
    }
}

public double getCliparea(HttpSession pSession, String pSource, double pDest, int pDestLength, int pDestDecimals, String pDestSigned)
    throws Exception
{
    try
    {

        String strGet;
        strGet = (String)pSession.getAttribute("CLIP->" + pSource.toUpperCase());
        if (strGet==null)
        {
           strGet = "";
        }
        pDest = move(strGet, pDest,pDestLength, pDestDecimals, pDestSigned);
        return(pDest);

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "getCliparea");
        return(0);
    }
}

public BigDecimal getCliparea(HttpSession pSession, String pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pDestSigned)
    throws Exception
{
    try
    {

        String strGet;
        strGet = (String)pSession.getAttribute("CLIP->" + pSource.toUpperCase());
        if (strGet==null)
        {
           strGet = "";
        }
        pDest = move(strGet, pDest ,pDestLength, pDestDecimals, pDestSigned);
        return(pDest);

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "getCliparea");
        return(bigZeros);
    }
}

/* GET.LENGTH */
public void getLength(String pDataItem)
    throws Exception
{
    try
    {
	GLB.LENGTH = (rTrim(pDataItem)).length();
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "getLength");
    }
}

/* GET.PARAMETER */
public String getParameter(HttpServletRequest pRequest, String pSource, String pDest, int pDestLength )
    throws Exception
{
    try
    {
        String strGet;
        strGet = (String)pRequest.getParameter(pSource);
        if (strGet==null)
        {
           strGet = "";
        }
        pDest = move(strGet, pDest,pDestLength);
        return(pDest);

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "getCliparea");
        return("");
    }
}

public double getParameter(HttpServletRequest pRequest, String pSource, double pDest, int pDestLength, int pDestDecimals, String pDestSigned)
    throws Exception
{
    try
    {
        String strGet;
        strGet = (String)pRequest.getParameter(pSource);
        if (strGet==null)
        {
           strGet = "";
        }
        pDest = move(strGet, pDest,pDestLength, pDestDecimals, pDestSigned);
        return(pDest);

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "getParameter");
        return(0);
    }
}

public BigDecimal getParameter(HttpServletRequest pRequest, String pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pDestSigned)
    throws Exception
{
    try
    {
        String strGet;
        strGet = (String)pRequest.getParameter(pSource);
        if (strGet==null)
        {
           strGet = "";
        }
        pDest = move(strGet, pDest ,pDestLength, pDestDecimals, pDestSigned);
        return(pDest);

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "getParameter");
        return(bigZeros);
    }
}

/* LEFT.ALIGN */
public String leftAlign (double pNumber, String pString, boolean  pRetainZeros, String pDecimalChar,int pStringLength)
    throws Exception
{
    try
    {
        int i=-1;
        BigDecimal bigDec = new BigDecimal((new Double(pNumber)).toString());

        pString = cStr(bigDec);
        pString = pString.trim();

        if ((pRetainZeros == false) && (pString.equals("0") == true))
        {
            pString = "";
        }

        if (pDecimalChar.equals(",") ==true)
        {
            i=pString.indexOf(".");

            if (i != -1)
            {
                pString = pString.replace('.',',');
            }
        }

        pString = fix(pString, pStringLength);

        return(pString);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "leftAlign");
        return ("");
    }
}

public String leftAlign (BigDecimal pNumber, String pString, boolean  pRetainZeros, String pDecimalChar,int pStringLength)
    throws Exception
{
    try
    {
        int i=-1;

        pString = cStr(pNumber);
        pString = pString.trim();

        if ((pRetainZeros == false) && (pString.equals("0") == true))
        {
            pString = "";
        }

        if (pDecimalChar.equals(",") ==true)
        {
            i=pString.indexOf(".");

            if (i != -1)
            {
                pString = pString.replace('.',',');
            }
        }

        pString = fix(pString, pStringLength);

        return(pString);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "leftAlign");
        return ("");
    }
}





/* LEFT.ALIGN */
public String leftAlign (double pNumber, String pString, boolean  pRetainZeros, String pDecimalChar,int pStringLength, int pNumberDecimal)
    throws Exception
{
    try
    {
        int i=-1;
        String sInt = "";
        String sDec = "";
        boolean isNeg = false;

        pDecimalChar = pDecimalChar.trim();
        if (pNumber < 0.0 )
        {   isNeg = true;
            pNumber = Math.abs(pNumber);
        }

        BigDecimal bigDec = new BigDecimal((new Double(pNumber)).toString());
        pString = cStr(bigDec);

        pString = pString.trim();


        if ( pString.equals("0") == true)
        {
            if (pRetainZeros == false)
            {   if (pDecimalChar.equals("") == true)
                {   pString = "";
                }
                else
                {   if (pNumberDecimal == 0)
                    {   pString = "";
                    }
                    else
                    {   pString = ".";
                        while (pString.length() <= pNumberDecimal)
                        {   pString = pString + "0";
                        }
                    }
                }
            }
            else
            {   if (pNumberDecimal == 0)
                {   pString = "0";
                }
                else
                {   pString = ".";
                    while (pString.length() <= pNumberDecimal)
                    {   pString = pString + "0";
                    }
                }
            }
        }
        else
        {
            if (pNumberDecimal != 0)
            {
                i=pString.indexOf(".");
                if (i!=-1)
                {   sInt = pString.substring(0,i);
                    if (cDbl(sInt) == 0.0)
                    { sInt = "";
                    }
                    sDec = fix (pString.substring(i+1),pNumberDecimal);
                    while (sDec.length() < pNumberDecimal)
                    {   sDec = sDec + "0";
                    }
                    pString = sInt + "." + sDec;
                }
                else
                {
                    pString = pString + ".";
                    for (i =1 ; i <= pNumberDecimal; i++)
                    {
                     pString = pString + "0";
                    }
                }
            }
        }


        if (pDecimalChar.equals(",") ==true)
        {   i=pString.indexOf(".");
            if (i != -1)
            {   pString = pString.replace('.',',');
            }
        }
        if (isNeg == true)
        {   pString = "-" + pString;
        }
        pString = fix(pString, pStringLength);

        return(pString);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "leftAlign");
        return ("");
    }
}

/* LEFT.ALIGN */
public String leftAlign (BigDecimal pNumber, String pString, boolean  pRetainZeros, String pDecimalChar,int pStringLength, int pNumberDecimal)
    throws Exception
{
    try
    {
        int i=-1;
        String sInt = "";
        String sDec = "";
        boolean isNeg = false;
        BigDecimal bigZeros = new BigDecimal(0);
        pDecimalChar = pDecimalChar.trim();

        if (less(pNumber,bigZeros) == true )
        {   isNeg = true;
            pNumber = pNumber.abs();
        }


        pString = cStr(pNumber);

        pString = pString.trim();

        if ( pString.equals("0") == true)
        {
            if (pRetainZeros == false)
            {   if (pDecimalChar.equals("") == true)
                {   pString = "";
                }
                else
                {   if (pNumberDecimal == 0)
                    {   pString = "";
                    }
                    else
                    {   pString = ".";
                        while (pString.length() <= pNumberDecimal)
                        {   pString = pString + "0";
                        }
                    }
                }
            }
            else
            {   if (pNumberDecimal == 0)
                {   pString = "0";
                }
                else
                {   pString = ".";
                    while (pString.length() <= pNumberDecimal)
                    {   pString = pString + "0";
                    }
                }
            }

        }

        else
        {   if (pNumberDecimal != 0)
            {   i=pString.indexOf(".");
                if (i!=-1)
                {   sInt = pString.substring(0,i);
                    if (cDbl(sInt) == 0.0)
                    { sInt = "";
                    }
                    sDec = fix (pString.substring(i+1),pNumberDecimal);
                    while (sDec.length() < pNumberDecimal)
                    {   sDec = sDec + "0";
                    }
                    pString = sInt + "." + sDec;
                }
                else
                {   pString = pString + ".";
                    for (i =1 ; i <= pNumberDecimal; i++)
                    {   pString = pString + "0";
                    }
                }
            }
        }


        if (pDecimalChar.equals(",") ==true)
        {   i=pString.indexOf(".");
            if (i != -1)
            {   pString = pString.replace('.',',');
            }
        }
        if (isNeg == true)
        {   pString = "-" + pString;
        }
        pString = fix(pString, pStringLength);

        return(pString);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "leftAlign");
        return ("");
    }
}

/* LOWERCASE */
public String lowerCase (String pDataName)
    throws Exception
{
    try
    {
        return(pDataName.toLowerCase());
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "lowerCase");
        return (pDataName);
    }
}


/* MOVE */
/* MOVE String para String */
public String move (String pSource, String pDest, int pDestLength)
    throws Exception
{
    try
    {
    	pDest = fix(pSource,pDestLength);
    	return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "move");
        return ("");
    }
}

/* MOVE String para double */
public double move (String pSource, double pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
        pSource = pSource.trim();

	if (isNumeric(pSource) == false)
    	{
    		return(0.0);
    	}

	BigDecimal bigDec = new BigDecimal(pSource);
    	pDest = newFix(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return (pDest);

    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "move");
        return (0.0);
    }
}

/* MOVE String para BigDecimal --> BigDecimal */
public BigDecimal move (String pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
        pSource = pSource.trim();

	if (isNumeric(pSource) == false)
    	{
    	   return(new BigDecimal(0));
    	}

	BigDecimal bigDec = new BigDecimal(pSource);
    	pDest = fixBigDec (bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return (pDest);

    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "move");
        return (new BigDecimal(0));
    }
}

/* MOVE double para double */
public double move(double pSource, double pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {

	BigDecimal bigDec = new BigDecimal((new Double(pSource)).toString());
    	pDest = newFix(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "move");
        return (0.0);
    }
}

/* MOVE BigDecimal para double --> BigDecimal */
public double move(BigDecimal pSource, double pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {

	BigDecimal bigDec = pSource;
    	pDest = newFix(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "move");
        return (0.0);
    }
}

/* MOVE double para BigDecimal */
public BigDecimal move(double pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {

	BigDecimal bigDec = new BigDecimal((new Double(pSource)).toString());
    	pDest = fixBigDec(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "move");
        return (new BigDecimal(0));
    }
}

/* MOVE BigDecimal para BigDecimal --> BigDecimal */
public BigDecimal move(BigDecimal pSource, BigDecimal pDest, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {

	BigDecimal bigDec = pSource;
    	pDest = fixBigDec(bigDec, pDestLength, pDestDecimals, pSigned, "TRUNCATE");
    	return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "move");
        return (new BigDecimal (0));
    }
}

/* Move Complexo - retorno String */
public  String moveComplex(String pSource, int pPosition1, int pLength, String pDest, int pPosition2, int pDestLength)
    throws Exception
{
    try
    {
	    if (pPosition1 > pSource.length())
            return pDest;

        if (pPosition2 > pDestLength)
            return pDest;

        // Criticar e spacos extra apos final do campo origem
        int posEnd = pPosition1 + pLength - 1;
        int extraSpaces = 0 ;
        if (posEnd > pSource.length())
        {   extraSpaces = posEnd - pSource.length();
            pLength = pLength - extraSpaces;
        }

        pDest = format(pDest, pDestLength);

        StringBuffer mvStrBuffer = new StringBuffer(pDest);

        if (pLength == 0)
        {
            pLength = pSource.length() - pPosition1 + 1;
        }

        if (pPosition2 + pLength - 1 < pDest.length())
        {
            mvStrBuffer = mvStrBuffer.replace(pPosition2 - 1, pPosition2 + pLength - 1, pSource.substring(pPosition1 - 1,pPosition1 + pLength - 1));
        }
        else
        {
            mvStrBuffer = mvStrBuffer.replace(pPosition2 - 1, pDest.length(), pSource.substring(pPosition1-1, pPosition1 +  pLength - 1));
        }

        pDest =fix(mvStrBuffer.toString(),pDestLength);

        return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "moveComplex");
        return ("");
    }
}

/* Move Complexo - Retorno double */
public  double moveComplex(String pSource, int pPosition1, int pLength, double pDest, int pPosition2, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
    	String dest;

        if (pPosition1 > pSource.length())
            return pDest;

        if (pPosition2 > pDestLength)
           return pDest;

        // Criticar e spacos extra apos final do campo origem
        int posEnd = pPosition1 + pLength - 1;
        int extraSpaces = 0 ;
        if (posEnd > pSource.length())
        {   extraSpaces = posEnd - pSource.length();
            pLength = pLength - extraSpaces;
        }

    	pDest = pDest * Math.pow (10 , pDestDecimals);
    	dest = format(pDest, string (pDestLength,"9"));

    	StringBuffer mvStrBuffer = new StringBuffer(dest);

    	if (pLength == 0)
    	{
       		pLength = pSource.length() - pPosition1 + 1;
    	}

    	if (pPosition2 + pLength - 1 < dest.length())
    	{
       		mvStrBuffer = mvStrBuffer.replace(pPosition2 - 1, pPosition2 + pLength - 1, pSource.substring(pPosition1 - 1,pPosition1 + pLength - 1));
    	}
    	else
    	{
       		mvStrBuffer = mvStrBuffer.replace(pPosition2 - 1, dest.length(), pSource.substring(pPosition1-1, pPosition1 +  pLength - 1));
    	}

    	if (pDestDecimals != 0)
    	{
       		dest = mvStrBuffer.substring(0,pDestLength - pDestDecimals) + "." + mvStrBuffer.substring(pDestLength - pDestDecimals,pDestLength);
    	}
    	else
    	{
       		dest = mvStrBuffer.substring(0,pDestLength);
    	}

    	pDest = cDbl(dest);

    	return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "moveComplex");
        return (0.0);
    }
}

/* Move Complexo - Retorno BigDecimal --> BigDecimal */
public  BigDecimal moveComplex(String pSource, int pPosition1, int pLength, BigDecimal pDest, int pPosition2, int pDestLength, int pDestDecimals, String pSigned)
    throws Exception
{
    try
    {
        String dest;

        if (pPosition1 > pSource.length())
            return pDest;

        if (pPosition2 > pDestLength)
            return pDest;

        // Criticar e spacos extra apos final do campo origem
        int posEnd = pPosition1 + pLength - 1;
        int extraSpaces = 0 ;
        if (posEnd > pSource.length())
        {   extraSpaces = posEnd - pSource.length();
            pLength = pLength - extraSpaces;
        }

    	pDest = pDest.movePointRight(pDestDecimals);
    	dest = format(pDest, string (pDestLength,"9"));

    	StringBuffer mvStrBuffer = new StringBuffer(dest);

    	if (pLength == 0)
    	{
       		pLength = pSource.length() - pPosition1 + 1;
    	}

    	if (pPosition2 + pLength - 1 < dest.length())
    	{
       		mvStrBuffer = mvStrBuffer.replace(pPosition2 - 1, pPosition2 + pLength - 1, pSource.substring(pPosition1 - 1,pPosition1 + pLength - 1));
    	}
    	else
    	{
       		mvStrBuffer = mvStrBuffer.replace(pPosition2 - 1, dest.length(), pSource.substring(pPosition1-1, pPosition1 +  pLength - 1));
    	}

    	if (pDestDecimals != 0)
    	{
       		dest = mvStrBuffer.substring(0,pDestLength - pDestDecimals) + "." + mvStrBuffer.substring(pDestLength - pDestDecimals,pDestLength);
    	}
    	else
    	{
       		dest = mvStrBuffer.substring(0,pDestLength);
    	}

    	pDest = cBigDec(dest);

    	return(pDest);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "moveComplex");
        return (new BigDecimal (0));
    }
}


/* MOVE.DATE */
public double moveDate()
    throws Exception
{
    try
    {
	return(new Double(new SimpleDateFormat("yyMMdd").format(new java.util.Date())).doubleValue());
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "moveDate");
        return (0.0);
    }
}


/* MOVE.DATE */
public double moveDate(String pFormat)
    throws Exception
{
    try
    {
 	    if ((pFormat.toUpperCase()).equals("CCYYMMDD") == true)
	    {
	     return(new Double(new SimpleDateFormat("yyyyMMdd").format(new java.util.Date())).doubleValue());
        }
        else if ((pFormat.toUpperCase()).equals("DDMMCCYY") == true)
        {
            return(new Double(new SimpleDateFormat("ddMMyyyy").format(new java.util.Date())).doubleValue());
        }
        else if ((pFormat.toUpperCase()).equals("DDMMYY") == true)
        {
            return(new Double(new SimpleDateFormat("ddMMyy").format(new java.util.Date())).doubleValue());
        }
        else if ((pFormat.toUpperCase()).equals("MMDDCCYY") == true)
        {
            return(new Double(new SimpleDateFormat("MMddyyyy").format(new java.util.Date())).doubleValue());
        }
        else if ((pFormat.toUpperCase()).equals("MMDDYY") == true)
        {
            return(new Double(new SimpleDateFormat("MMddyy").format(new java.util.Date())).doubleValue());
        }
        else
        {
	        return(new Double(new SimpleDateFormat("yyMMdd").format(new java.util.Date())).doubleValue());
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "moveDate");
        return (0.0);
    }
}


/* MOVE.TIME */
public double moveTime()
    throws Exception
{
    try
    {   String resultTime;
        resultTime = new SimpleDateFormat("HHmmssSSS").format(new java.util.Date());
        resultTime = fix(resultTime,8);
	    return(new Double(resultTime).doubleValue());
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "moveTime");
        return (0.0);
    }

}


/* MULTIPLY double para double, retornando double */
public double multiply(double pNumber1, double pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
	BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());

        bigDec2 = bigDec2.multiply(bigDec1);
	return(newFix(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (0.0);
    }
}

/* MULTIPLY BigDecimal para double, , retornando double --> BigDecimal */
public double multiply(BigDecimal pNumber1, double pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = pNumber1;
	BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());

        bigDec2 = bigDec2.multiply(bigDec1);
	return(newFix(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (0.0);
    }
}

/* MULTIPLY double para BigDecimal, retornando BigDecimal --> BigDecimal */
public BigDecimal multiply(double pNumber1, BigDecimal pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
	BigDecimal bigDec2 = pNumber2;

        bigDec2 = bigDec2.multiply(bigDec1);
	return(fixBigDec(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (new BigDecimal(0));
    }
}

/* MULTIPLY BigDecimal para BigDecimal, retornando double */
public BigDecimal multiply(BigDecimal pNumber1, BigDecimal pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = pNumber1;
	BigDecimal bigDec2 = pNumber2;

        bigDec2 = bigDec2.multiply(bigDec1);
	return(fixBigDec(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (new BigDecimal(0));
    }
}

/* MULTIPLY - 3 parametros, retornando double */
public double multiply(double pNumber1, double pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
 	BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.multiply(bigDec1);
	return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (0.0);
    }
}

public double multiply(double pNumber1, BigDecimal pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
 	BigDecimal bigDec2 = pNumber2;
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.multiply(bigDec1);
	return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (0.0);
    }
}

public double multiply(BigDecimal pNumber1, double pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = pNumber1;
 	BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.multiply(bigDec1);
	return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (0.0);
    }
}

public double multiply(BigDecimal pNumber1, BigDecimal pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = pNumber1;
 	BigDecimal bigDec2 = pNumber2;
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.multiply(bigDec1);
	return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (0.0);
    }
}

/* MULTIPLY - 3 parametros, retornando BigDecimal */
public BigDecimal multiply(double pNumber1, double pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
 	BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.multiply(bigDec1);
	return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (bigZeros);
    }
}

public BigDecimal multiply(double pNumber1, BigDecimal pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
 	BigDecimal bigDec2 = pNumber2;
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.multiply(bigDec1);
	return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (bigZeros);
    }
}

public BigDecimal multiply(BigDecimal pNumber1, double pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = pNumber1;
 	BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.multiply(bigDec1);
	return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (bigZeros);
    }
}

public BigDecimal multiply(BigDecimal pNumber1, BigDecimal pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = pNumber1;
        BigDecimal bigDec2 = pNumber2;
        BigDecimal bigDec3 = new BigDecimal(0);
        bigDec3 = bigDec2.multiply(bigDec1);
	return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "multiply");
        return (bigZeros);
    }
}

/* multiply */
public BigDecimal multiply(double pNumber1, double pNumber2)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());

        return( bigDec2.multiply(bigDec1) );
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "multiply");
        return (bigZeros);
    }
}

public BigDecimal multiply(BigDecimal pNumber1, double pNumber2)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = pNumber1;
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());

        return( bigDec2.multiply(bigDec1) );
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "multiply");
        return (bigZeros);
    }
}

public BigDecimal multiply(double pNumber1, BigDecimal pNumber2)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = pNumber2;

        return( bigDec2.multiply(bigDec1) );
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "multiply");
        return (bigZeros);
    }
}

public BigDecimal multiply(BigDecimal pNumber1, BigDecimal pNumber2)
    throws Exception
{
    try
    {
        return(pNumber2.multiply(pNumber1));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "multiply");
        return (bigZeros);
    }
}

/* pow */
public BigDecimal pow(double pBase, double pExp)
    throws Exception
{
    try
    {   //Expoente 0 retorna 1
        if ( pExp == 0.0 )
        {   return(new BigDecimal("1"));
        }

        //Expoente negativo
        if ( pExp < 0.0)
        {   BigDecimal powReturn = new BigDecimal((new Double(Math.pow(pBase, pExp))).toString());
            return(powReturn);
        }

        // Expoente com casas decimais
        if ( (pExp - (long)pExp) > 0.0 )
        {
            BigDecimal powReturn = new BigDecimal((new Double(Math.pow(pBase, pExp))).toString());

            return(powReturn);
        }

        // Expoente sem casas decimais
        BigDecimal pReturn = new BigDecimal((new Double(pBase)).toString());
        BigDecimal pBigBase = new BigDecimal((new Double(pBase)).toString());

        for (double i = 1.0; i < pExp; i++)
        {
            pReturn = pReturn.multiply(pBigBase);
        }

        return(pReturn);
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "pow");
        return (bigZeros);
    }
}

public BigDecimal pow(BigDecimal pBase, double pExp)
    throws Exception
{
    try
    {   //Expoente 0 retorna 1
        if ( pExp == 0.0 )
        {   return(new BigDecimal("1"));
        }

        //Expoente negativo
        if ( pExp < 0.0)
        {   BigDecimal powReturn = new BigDecimal((new Double(Math.pow(pBase.doubleValue(), pExp))).toString());
            return(powReturn);
        }

        // Expoente com casas decimais
        if ( (pExp - (long)pExp) > 0.0 )
        {
           BigDecimal powReturn = new BigDecimal((new Double(Math.pow(pBase.doubleValue(), pExp))).toString());

           return(powReturn);
        }

        // Expoente sem casas decimais
        BigDecimal pReturn = new BigDecimal("0");
        pReturn = pReturn.add(pBase);

        for (double i = 1.0; i < pExp; i++)
        {
            pReturn = pReturn.multiply(pBase);
        }

        return(pReturn);

    }
    catch (Exception e)
    {   ShowErrorMsg (e, "pow");
        return (bigZeros);
    }
}

public BigDecimal pow(double pBase, BigDecimal pExp)
    throws Exception
{
    try
    {   //Expoente 0 retorna 1
        if ( pExp.doubleValue() == 0.0 )
        {   return(new BigDecimal("1"));
        }

        //Expoente negativo
        if ( pExp.doubleValue() < 0.0)
        {   BigDecimal powReturn = new BigDecimal((new Double(Math.pow(pBase, pExp.doubleValue()))).toString());
            return(powReturn);
        }

        // Expoente com casas decimais
        if ( (pExp.doubleValue() - (long)pExp.doubleValue()) > 0.0 )
        {
           BigDecimal powReturn = new BigDecimal((new Double(Math.pow(pBase, pExp.doubleValue()))).toString());

           return(powReturn);
        }

        // Expoente sem casas decimais
        BigDecimal pBigBase = new BigDecimal((new Double(pBase)).toString());
        BigDecimal pReturn = new BigDecimal((new Double(pBase)).toString());
        BigDecimal hum = new BigDecimal("1");


        for (BigDecimal i = new BigDecimal("1"); i.compareTo(pExp) < 0; i = i.add(hum))
        {
            pReturn = pReturn.multiply(pBigBase);
        }

        return(pReturn);

    }
    catch (Exception e)
    {   ShowErrorMsg (e, "pow");
        return (bigZeros);
    }
}

public BigDecimal pow(BigDecimal pBase, BigDecimal pExp)
    throws Exception
{
    try
    {   //Expoente 0 retorna 1
        if ( pExp.doubleValue() == 0.0 )
        {   return(new BigDecimal("1"));
        }

        //Expoente negativo
        if ( pExp.doubleValue() < 0.0)
        {   BigDecimal powReturn = new BigDecimal((new Double(Math.pow(pBase.doubleValue(), pExp.doubleValue()))).toString());
            return(powReturn);
        }

        // Expoente com casas decimais
        if ( (pExp.doubleValue() - (long)pExp.doubleValue()) > 0.0 )
        {
           BigDecimal powReturn = new BigDecimal((new Double(Math.pow(pBase.doubleValue(), pExp.doubleValue()))).toString());

           return(powReturn);
        }

        // Expoente sem casas decimais
        BigDecimal pReturn = new BigDecimal("0");
        BigDecimal hum = new BigDecimal("1");

        pReturn = pReturn.add(pBase);

        for (BigDecimal i = new BigDecimal("1"); i.compareTo(pExp) < 0; i = i.add(hum))
        {
            pReturn = pReturn.multiply(pBase);
        }

        return(pReturn);
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "pow");
        return (bigZeros);
    }
}

/* SQL.EXEC */
public void sqlExec(String pSql)
    throws Exception
{
    try
    {   pSql = pSql.trim();

        if (pSql.substring(0,pSql.indexOf(" ")).toUpperCase().equals("SELECT") == true)
        {
            sqlExecSelect(pSql);
        }
        else
        {
            sqlExecUpdate(pSql);
        }
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "sqlExec");
    }

}

public void sqlExecSelect(String pSql)
    throws Exception
{
	try
	{
        if (XSEED_SQL!=null)
        {
            XSEED_SQL.close();
            XSEED_SQL = null;
        }
        if (XSEED_CMD!=null)
        {
            XSEED_CMD.close();
            XSEED_CMD = null;
        }

		XSEED_CMD = GLB.CONNECTION.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

		XSEED_SQL = XSEED_CMD.executeQuery(pSql);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "sqlExecSelect");
    }
}

public void sqlExecUpdate(String pSql)
    throws Exception
{
    try
    {
        if (XSEED_SQL!=null)
        {
            XSEED_SQL.close();
            XSEED_SQL = null;
        }
        if (XSEED_CMD!=null)
        {
            XSEED_CMD.close();
            XSEED_CMD = null;
        }
        XSEED_CMD = GLB.CONNECTION.createStatement();

        int i = XSEED_CMD.executeUpdate(pSql);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "sqlExecUpdate");
    }
}

/* SQL.GETFIELD */
public String sqlGetField(String pTableField, String pDataField)
   throws Exception
{
	try
	{
        pDataField = XSEED_SQL.getString(pTableField);
        if ( pDataField == null)
        {
            pDataField = "";
        }

		return(pDataField);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "sqlGetField");
    	return("");
    }
}

public String sqlGetField(int pTableField, String pDataField)
   throws Exception
{
	try
	{
        pDataField = XSEED_SQL.getString(pTableField);
        if ( pDataField  == null)
        {
            pDataField = "";
        }

		return(pDataField);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "sqlGetField");
        return ("");
    }
}

public double sqlGetField(String pTableField, double pDataField)
   throws Exception
{
	try
	{
        pDataField = XSEED_SQL.getDouble(pTableField);
		return(pDataField);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "sqlGetFiled");
        return (0.0);
    }
}

public double sqlGetField(int pTableField, double pDataField)
   throws Exception
{
	try
	{
        pDataField = XSEED_SQL.getDouble(pTableField);
        return(pDataField);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "sqlGetField");
        return (0.0);
    }
}

/* SQL.GETFIELD --> BigDecimal */
public BigDecimal sqlGetField(String pTableField, BigDecimal pDataField)
   throws Exception
{
   try
   {
        pDataField = XSEED_SQL.getBigDecimal(pTableField);
        if ( pDataField == null)
        {
            pDataField = bigZeros;
        }
	    return(pDataField);
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "sqlGetFiled");
        return (new BigDecimal(0));
    }
}

public BigDecimal sqlGetField(int pTableField, BigDecimal pDataField)
   throws Exception
{
   try
   {
        pDataField = XSEED_SQL.getBigDecimal(pTableField);
        if ( pDataField == null)
        {
            pDataField = bigZeros;
        }
	    return(pDataField);
   }
   catch (Exception e)
   {
       ShowErrorMsg (e, "sqlGetField");
       return (new BigDecimal(0));
   }
}

/* SQL.MOVEFIRST */
public void sqlMoveFirst()
  throws Exception
{
	try
	{
		if (XSEED_SQL.first())
		{
			GLB.STATUS = "";
		}
		else
		{
			GLB.STATUS = "*****";
		}
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "sqlmoveFirst");
    }
}

/* SQL.MOVELAST */
public void sqlMoveLast()
  throws Exception
{
	try
	{
		if (XSEED_SQL.last())
		{
			GLB.STATUS = "";
		}
		else
		{
			GLB.STATUS = "*****";
		}
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "sqlMoveLast");
    }
}

/* SQL.MOVENEXT */
public void sqlMoveNext()
  throws Exception
{
	try
	{
		if (XSEED_SQL.next())
		{
			GLB.STATUS = "";
		}
		else
		{
			GLB.STATUS = "*****";
		}
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "sqlMoveNext");
    }
}

/* SQL.MOVEPREVIOUS */
public void sqlMovePrevious()
  throws Exception
{
	try
	{
		if (XSEED_SQL.previous())
		{
			GLB.STATUS = "";
		}
		else
		{
			GLB.STATUS = "*****";
		}
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "sqlMovePrevious");
    }
}


/* START */
public void start(String pProgram,String pParam)
throws Exception
{
	try
   	{
		Process myProcess = null;
		// Start processes on UNIX/LINUX
		if (File.separatorChar == '/')
      	{	// Prepare unix command line
			String unixCmdLine [] = new String[3];
			unixCmdLine[0] = "/bin/sh";
			unixCmdLine[1] = pProgram;
			unixCmdLine[2] = pParam;

			myProcess = Runtime.getRuntime().exec(unixCmdLine);
       }
       else        // Start processes on WINDOWS
		{	myProcess = Runtime.getRuntime().exec("cmd /C "+ pProgram + " " + pParam);

		}
	}
    catch (Exception e)
    {
    	ShowErrorMsg (e, "start");
    }
}

/* START.SYNC */
public void startSync(String pProgram,String pParam)
throws Exception
{
	try
   	{
		Process myProcess = null;
		// Start processes on UNIX/LINUX
		if (File.separatorChar == '/')
      	{	// Prepare unix command line
			String unixCmdLine [] = new String[3];
			unixCmdLine[0] = "/bin/sh";
			unixCmdLine[1] = pProgram;
			unixCmdLine[2] = pParam;

			myProcess = Runtime.getRuntime().exec(unixCmdLine);
			myProcess.waitFor();
       }
       else        // Start processes on WINDOWS
		{	myProcess = Runtime.getRuntime().exec("cmd /C "+ pProgram + " " + pParam);
			myProcess.waitFor();
		}
	}
    catch (Exception e)
    {
    	ShowErrorMsg (e, "start sync");
    }
}

/* SUBTRACT */
public double subtract(double pNumber1, double pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
	    BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
	    bigDec2 = bigDec2.subtract(bigDec1);
	    return(newFix(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (0.0);
    }
}

/* SUBTRACT BigDecimal para double --> BigDecimal */
public double subtract(BigDecimal pNumber1, double pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
          BigDecimal bigDec1 = pNumber1;
	  BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
	  bigDec2 = bigDec2.subtract(bigDec1);
	  return(newFix(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (0.0);
    }
}

/* SUBTRACT double para BigDecimal --> BigDecimal */
public BigDecimal subtract(double pNumber1, BigDecimal pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
          BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
	  BigDecimal bigDec2 = pNumber2;
	  bigDec2 = bigDec2.subtract(bigDec1);
	  return(fixBigDec(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (new BigDecimal(0));
    }
}

/* SUBTRACT BigDecimal para BigDecimal --> BigDecimal */
public BigDecimal subtract(BigDecimal pNumber1, BigDecimal pNumber2, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
          BigDecimal bigDec1 = pNumber1;
	  BigDecimal bigDec2 = pNumber2;
	  bigDec2 = bigDec2.subtract(bigDec1);
	  return(fixBigDec(bigDec2,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (new BigDecimal(0));
    }
}

/* SUBTRACT - 3 parametros, retornando double */
public double subtract(double pNumber1, double pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
          BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
	  BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
          BigDecimal bigDec3 = new BigDecimal(0);
	  bigDec3 = bigDec2.subtract(bigDec1);
	  return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (0.0);
    }
}

public double subtract(double pNumber1, BigDecimal pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
          BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
	  BigDecimal bigDec2 = pNumber2;
          BigDecimal bigDec3 = new BigDecimal(0);
	  bigDec3 = bigDec2.subtract(bigDec1);
	  return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (0.0);
    }
}

public double subtract(BigDecimal pNumber1, double pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
          BigDecimal bigDec1 = pNumber1;
	  BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
          BigDecimal bigDec3 = new BigDecimal(0);
	  bigDec3 = bigDec2.subtract(bigDec1);
	  return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (0.0);
    }
}

public double subtract(BigDecimal pNumber1, BigDecimal pNumber2, double pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
          BigDecimal bigDec1 = pNumber1;
	  BigDecimal bigDec2 = pNumber2;
          BigDecimal bigDec3 = new BigDecimal(0);
	  bigDec3 = bigDec2.subtract(bigDec1);
	  return(newFix(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (0.0);
    }
}

/* SUBTRACT - 3 parametros, retornando BigDecimal */
public BigDecimal subtract(double pNumber1, double pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
          BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
	  BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
          BigDecimal bigDec3 = new BigDecimal(0);
	  bigDec3 = bigDec2.subtract(bigDec1);
	  return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (bigZeros);
    }
}

public BigDecimal subtract(double pNumber1, BigDecimal pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
          BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
	  BigDecimal bigDec2 = pNumber2;
          BigDecimal bigDec3 = new BigDecimal(0);
	  bigDec3 = bigDec2.subtract(bigDec1);
	  return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (bigZeros);
    }
}

public BigDecimal subtract(BigDecimal pNumber1, double pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
          BigDecimal bigDec1 = pNumber1;
	  BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
          BigDecimal bigDec3 = new BigDecimal(0);
	  bigDec3 = bigDec2.subtract(bigDec1);
	  return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (bigZeros);
    }
}

public BigDecimal subtract(BigDecimal pNumber1, BigDecimal pNumber2, BigDecimal pResult, int pResultLength, int pResultDecimals, String pSigned, String pRounded)
    throws Exception
{
    try
    {
          BigDecimal bigDec1 = pNumber1;
	  BigDecimal bigDec2 = pNumber2;
          BigDecimal bigDec3 = new BigDecimal(0);
	  bigDec3 = bigDec2.subtract(bigDec1);
	  return(fixBigDec(bigDec3,pResultLength,pResultDecimals, pSigned, pRounded));
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "subtract");
        return (bigZeros);
    }
}

/* Subtract*/
public BigDecimal subtract(double pNumber1, double pNumber2)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());

        return( bigDec2.subtract(bigDec1) );
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "subtract");
        return (bigZeros);
    }
}

public BigDecimal subtract(BigDecimal pNumber1, double pNumber2)
    throws Exception
{
    try
    {   BigDecimal bigDec1 = pNumber1;
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());

        return( bigDec2.subtract(bigDec1) );
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "subtract");
        return (bigZeros);
    }
}

public BigDecimal subtract(double pNumber1, BigDecimal pNumber2)
    throws Exception
{
    try
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = pNumber2;

        return( bigDec2.subtract(bigDec1) );
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "subtract");
        return (bigZeros);
    }
}

public BigDecimal subtract(BigDecimal pNumber1, BigDecimal pNumber2)
    throws Exception
{
    try
    {
        return( pNumber2.subtract(pNumber1) );
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "subtract");
        return (bigZeros);
    }
}

/* TRACE */
public void trace(String header, String trailer)
    throws Exception
{  	Date dateToday = new Date();
    try
    {
        PrintStream trace;
        if (GLB.TRACEDIR.equals("") == true)
        {
            trace = new PrintStream(new FileOutputStream(GLB.AUDITDIR + File.separatorChar + ISPEC + GLB.TRACESUFFIX,true));
        }
        else
        {
            trace = new PrintStream(new FileOutputStream(GLB.TRACEDIR + File.separatorChar + ISPEC + GLB.TRACESUFFIX,true));
        }
        trace.println("TRC:" + format(dateToday,"yyyy/MM/dd HH:mm:ss") + " msg: " + rTrim(header) + " " + rTrim(trailer) );
        trace.close();
    }
    catch (Exception e)
    {
     ShowErrorMsg (e, "trace");
    }
}

/* UPPERCASE */
public String upperCase (String pDataName)
    throws Exception
{
	try
	{
		return(pDataName.toUpperCase());
	}
	catch (Exception e)
    {  	ShowErrorMsg (e, "upperCase");
        return (pDataName);
    }
}

/* WFL */
public void wfl(String pProgram,String pParam)
throws Exception
{
	try
   	{
		Process myProcess = null;
		pProgram = GLB.SYSTEMDIR + File.separatorChar + "Scripts" + File.separatorChar + pProgram;
		// Start processes on UNIX/LINUX
		if (File.separatorChar == '/')
      	{	// Prepare unix command line
			String unixCmdLine [] = new String[3];
			unixCmdLine[0] = "/bin/sh";
			unixCmdLine[1] = pProgram;
			unixCmdLine[2] = pParam;

			myProcess = Runtime.getRuntime().exec(unixCmdLine);
       }
       else        // Start processes on WINDOWS
		{	myProcess = Runtime.getRuntime().exec("cmd /C "+ pProgram + " " + pParam);

		}
	}
    catch (Exception e)
    {
    	ShowErrorMsg (e, "wfl");
    }
}

/* WFL.SYNC */
public void wflSync(String pProgram,String pParam)
throws Exception
{
	try
   	{
		Process myProcess = null;
		pProgram = GLB.SYSTEMDIR + File.separatorChar + "Scripts" + File.separatorChar + pProgram;
		// Start processes on UNIX/LINUX
		if (File.separatorChar == '/')
      	{	// Prepare unix command line
			String unixCmdLine [] = new String[3];
			unixCmdLine[0] = "/bin/sh";
			unixCmdLine[1] = pProgram;
			unixCmdLine[2] = pParam;

			myProcess = Runtime.getRuntime().exec(unixCmdLine);
			myProcess.waitFor();
       }
       else        // Start processes on WINDOWS
		{	myProcess = Runtime.getRuntime().exec("cmd /C "+ pProgram + " " + pParam);
			myProcess.waitFor();
		}
	}
    catch (Exception e)
    {
    	ShowErrorMsg (e, "wfl sync");
    }
}


//Fim comandos iguais

////////// Comandos diferentes
/* ABORT */
public void abort(String pMsg,String pIspec)
   throws Exception
{
    try
    {
        GLB.ABORT = true;
    	GLB.EXIT = true;

        RollBackTransaction();

        message ("", pMsg);
        recall(pIspec, "");

        Exception e = new Exception();
        throw e;
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "abort");
    }
}

public void abort(String pMsg)
   throws Exception
{
    try
    {
        GLB.ABORT = true;
    	GLB.EXIT = true;

        RollBackTransaction();

        message ("", pMsg);
        recall(ISPEC, "");

        Exception e = new Exception();
        throw e;
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "abort");
    }
}


/* MESSAGE */
public void message(String header, String trailer)
    throws Exception
{
    try
    {
        GLB.MSGINDEX = GLB.MSGINDEX + 1;
        if (GLB.MSGINDEX > 24)
        {   for (int i = 1; i < GLB.MSGINDEX - 1; i++)
            {   GLB.MSGHEADER[i] = GLB.MSGHEADER[i+1];
                GLB.MSGTRAILER[i] = GLB.MSGTRAILER[i+1];
            }
            GLB.MSGINDEX = 24;
        }
        GLB.MSGHEADER[GLB.MSGINDEX] = header;
        GLB.MSGTRAILER[GLB.MSGINDEX] = trailer;
        if (GLB.PHASE.equals("U") == false)
        {   GLB.ERROR = "*****";
        }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "message");
    }
}

/* run */
public void run(String pReportName,String pParam)
    throws Exception
{
    try
    {
       Process proc;
       String classPath;
       String execCmd;
       String initSTN;
       String appIniPath;
       String programName;
       String deviceName;
       String libPath;

      // Execute Report on UNIX/LINUX
      if (File.separatorChar == '/')
      {
          programName = GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "classes" + File.separatorChar +
                        "XseedRunAsync.sh";

          classPath  =  GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "classes";

          libPath    =  GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "lib";

          classPath  =  rTrim(classPath);
          initSTN    =  rTrim(GLB.STN) ;
          pParam     =  rTrim(pParam)  ;
          appIniPath =  rTrim(GLB.SYSTEMDIR)  ;
          deviceName =  rTrim(GLB.DEVICE)  ;
          libPath    =  rTrim(libPath);

          // Prepare unix command line
          String unixCmdLine [] = new String[9];

          unixCmdLine[0] = "/bin/sh";
          unixCmdLine[1] = programName;
          unixCmdLine[2] = classPath;
          unixCmdLine[3] = pReportName;
          unixCmdLine[4] = initSTN;
          unixCmdLine[5] = pParam;
          unixCmdLine[6] = appIniPath;
          unixCmdLine[7] = deviceName;
          unixCmdLine[8] = libPath;

          // Execute XseedRunAsync.sh unix shell
          proc = Runtime.getRuntime().exec(unixCmdLine);
       }
       else
       {
          // Execute reports on WINDOWS
          programName = GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "classes" + File.separatorChar +
                        "XseedRunAsync.exe";

          classPath  =  GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "classes";

          libPath    =  GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "lib";

          classPath  =  rTrim(classPath);
          initSTN    =  rTrim(GLB.STN) ;
          pParam     =  rTrim(pParam)  ;
          appIniPath =  rTrim(GLB.SYSTEMDIR)  ;
          deviceName =  rTrim(GLB.DEVICE)  ;
          libPath    =  rTrim(libPath);

          // Prepare windows command line
          String winCmdLine [] = new String[14];

          winCmdLine[0] = programName;
          winCmdLine[1] = classPath;
          winCmdLine[2] = ",";
          winCmdLine[3] = pReportName;
          winCmdLine[4] = ",";
          winCmdLine[5] = initSTN;
          winCmdLine[6] = ",";
          winCmdLine[7] = pParam;
          winCmdLine[8] = ",";
          winCmdLine[9] = appIniPath;
          winCmdLine[10] = ",";
          winCmdLine[11] = deviceName;
          winCmdLine[12] = ",";
          winCmdLine[13] = libPath;

/*          winCmdLine[0] = programName;
          winCmdLine[1] = classPath;
          winCmdLine[2] = pReportName;
          winCmdLine[3] = initSTN;
          winCmdLine[4] = pParam;
          winCmdLine[5] = appIniPath;
          winCmdLine[6] = deviceName;
          winCmdLine[7] = libPath;*/

          // Execute XseedRunAsync.exe on Windows
          proc = Runtime.getRuntime().exec(winCmdLine);
       }
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "run");
    }
}


/* runAsync */
public void runAsync(String pReportName,String pParam)
   throws Exception
{
   try
   {
	   	Process proc;
	   	String classPath;
	   	String execCmd;
	   	String initSTN;
	   	String appIniPath;
	   	String programName;
	   	String deviceName;
        String libPath;

	   			// Execute Report on UNIX/LINUX
      	if (File.separatorChar == '/')
      	{
        	programName = GLB.SYSTEMDIR + File.separatorChar +
            			  "WEB-INF" + File.separatorChar +
                          "classes" + File.separatorChar +
                          "XseedRunAsync.sh";

        	classPath  =  GLB.SYSTEMDIR + File.separatorChar +
                          "WEB-INF" + File.separatorChar +
                          "classes";

            libPath    =  GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "lib";

        	classPath  =  rTrim(classPath);
        	initSTN    =  rTrim(GLB.STN) ;
        	pParam     =  rTrim(pParam)  ;
        	appIniPath =  rTrim(GLB.SYSTEMDIR)  ;
        	deviceName =  rTrim(GLB.DEVICE)  ;
        	libPath    =  rTrim(libPath);

        	// Prepare unix command line
        	String unixCmdLine [] = new String[9];

        	unixCmdLine[0] = "/bin/sh";
        	unixCmdLine[1] = programName;
        	unixCmdLine[2] = classPath;
        	unixCmdLine[3] = pReportName;
        	unixCmdLine[4] = initSTN;
        	unixCmdLine[5] = pParam;
        	unixCmdLine[6] = appIniPath;
        	unixCmdLine[7] = deviceName;
        	unixCmdLine[8] = libPath;

        	// Execute XseedRunAsync.sh unix shell
        	proc = Runtime.getRuntime().exec(unixCmdLine);
	    }
	    else
	    {
           // Execute reports on WINDOWS
          programName = GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "classes" + File.separatorChar +
                        "XseedRunAsync.exe";

          classPath  =  GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "classes";

          libPath    =  GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "lib";

          classPath  =  rTrim(classPath);
          initSTN    =  rTrim(GLB.STN) ;
          pParam     =  rTrim(pParam)  ;
          appIniPath =  rTrim(GLB.SYSTEMDIR)  ;
          deviceName =  rTrim(GLB.DEVICE)  ;
          libPath    =  rTrim(libPath);


          // Prepare windows command line
          String winCmdLine [] = new String[14];

          winCmdLine[0] = programName;
          winCmdLine[1] = classPath;
          winCmdLine[2] = ",";
          winCmdLine[3] = pReportName;
          winCmdLine[4] = ",";
          winCmdLine[5] = initSTN;
          winCmdLine[6] = ",";
          winCmdLine[7] = pParam;
          winCmdLine[8] = ",";
          winCmdLine[9] = appIniPath;
          winCmdLine[10] = ",";
          winCmdLine[11] = deviceName;
          winCmdLine[12] = ",";
          winCmdLine[13] = libPath;


          // Execute XseedRunAsync.exe on Windows
          proc = Runtime.getRuntime().exec(winCmdLine);

	    }
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "runAsync");
    }
}

/* runAt */
public void runAt(String pReportName, String pDevice,String pParam, String pTime,String pDate)
   throws Exception
{
    try
    {
        XseedFile jobFile = new XseedFile();
    	SimpleDateFormat formatterNow = new SimpleDateFormat("yyyy-MM-dd HHmmss");
    	String now = formatterNow.format(new java.util.Date());

        Date dt = new Date();
        SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        dt = formatterDate.parse(pDate + " " + pTime);
        formatterDate.applyPattern("yyyyMMdd HHmmss");
        String dateAux = formatterDate.format(dt);

    	jobFile.open(GLB.BATCHDIR + File.separatorChar + GLB.STN.trim() +  "(" + now.trim() + ")" + pReportName.trim() + ".AT(" + dateAux.trim() + ")" , "OUTPUT");
    	jobFile.write(pReportName.trim() + " Start parameters:");
    	jobFile.write("GLB_INITSTN = " + GLB.STN);
    	jobFile.write("GLB_PARAM = " + pParam);
    	jobFile.write("GLB_BATCHDIR = " + GLB.BATCHDIR);
		jobFile.close();
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "runAt");
        return;
    }
}

/* runRemote */
public void runRemote(String pReportName, String pDevice,String pParam,String pRunQueueNumber)
    throws Exception
{
    try
    {
    	XseedFile jobFile = new XseedFile();
    	String wRunName;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
    	String now = formatter.format(new java.util.Date());
        wRunName = GLB.BATCHDIR + File.separatorChar + GLB.STN + "(" + now.trim() + ")" + pReportName.trim() + ".RUN" + pRunQueueNumber.trim();
        if (FileExists(wRunName) == true)
        {
            for(int i=0;i<999;i++)
            {
                wRunName = GLB.BATCHDIR + File.separatorChar + GLB.STN + "(" + now.trim() + i + ")" + pReportName.trim() + ".RUN" + pRunQueueNumber.trim();
                if (FileExists(wRunName) == false)
                {
                    break;
                }
            }
        }
        jobFile.open(wRunName, "OUTPUT");
    	jobFile.write(pReportName.trim() + " Start parameters:");
    	jobFile.write("GLB_INITSTN = " + GLB.STN);
    	jobFile.write("GLB_PARAM = " + pParam);
        jobFile.write("GLB_BATCHDIR = " + GLB.BATCHDIR);
        jobFile.write("GLB_DEVICE = " + pDevice);
	    jobFile.close();
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "runRemote");
        return;
    }
}

//Fim comandos diferentes

////////// Exclusivos XseedIspec

/* checkOnXmit */
public boolean checkOnXmit(HttpSession pSession)
	throws Exception
{
	if (pSession.getAttribute("XSEEDONXMIT")!=null)
    {
    	String onXmit = (String) pSession.getAttribute("XSEEDONXMIT");
		if (onXmit.trim().equals("TRUE"))
        {
        	return true;
		}
	}
	return false;
}

/* CURSOR */
public void cursor (String pFieldName)
   throws Exception
{
	GLB.CURSOR = pFieldName;
}

public void cursor (Object pControl)
	throws Exception
{
	if (pControl instanceof XseedButton)
	{
		XseedButton xsdControl = (XseedButton) pControl;
		GLB.CURSOR = rTrim(xsdControl.name).toUpperCase();
		xsdControl=null;
	}
	else if (pControl instanceof XseedCheckbox)
	{
		XseedCheckbox xsdControl = (XseedCheckbox) pControl;
		GLB.CURSOR = rTrim(xsdControl.name).toUpperCase() ;
		xsdControl=null;
	}
	else if (pControl instanceof XseedCombobox)
	{
		XseedCombobox xsdControl = (XseedCombobox) pControl;
		if (xsdControl.editable==true)
		{	GLB.CURSOR = rTrim(xsdControl.name).toUpperCase() + "_txt";
		}
		else
		{
			GLB.CURSOR = rTrim(xsdControl.name).toUpperCase() ;
		}
		xsdControl=null;
	}
	else if (pControl instanceof XseedDBCombo)
	{
		XseedDBCombo xsdControl = (XseedDBCombo) pControl;
		GLB.CURSOR = rTrim(xsdControl.name).toUpperCase();
		xsdControl=null;
	}
	else if (pControl instanceof XseedDBList)
	{
		XseedDBList xsdControl = (XseedDBList) pControl;
		GLB.CURSOR = rTrim(xsdControl.name).toUpperCase();
		xsdControl=null;
	}
	else if (pControl instanceof XseedDBGrid)
	{
		XseedDBGrid xsdControl = (XseedDBGrid) pControl;
		GLB.CURSOR = rTrim(xsdControl.name).toUpperCase();
		xsdControl=null;
	}
	else if (pControl instanceof XseedImage)
	{
		XseedImage xsdControl = (XseedImage) pControl;
		GLB.CURSOR = rTrim(xsdControl.name).toUpperCase();
		xsdControl=null;
	}

	else if (pControl instanceof XseedLabel)
	{
		XseedLabel xsdControl = (XseedLabel) pControl;
		GLB.CURSOR = rTrim(xsdControl.name).toUpperCase() ;
		xsdControl=null;
	}
	else if (pControl instanceof XseedListbox)
	{
		XseedListbox xsdControl = (XseedListbox) pControl;
		GLB.CURSOR = rTrim(xsdControl.name).toUpperCase() ;
		xsdControl=null;
	}
	else if (pControl instanceof XseedTextbox)
	{
		XseedTextbox xsdControl = (XseedTextbox) pControl;
		GLB.CURSOR = rTrim(xsdControl.name).toUpperCase();
		xsdControl=null;
	}

}

/* printCheckbox */
public String printCheckbox (XseedCheckbox pControl)
    throws Exception
{
    try
    {   int i=0;

        String str ="";

        //'Criacao do LAYER


        //'Criacao check
        if (pControl.visible  == true)
        {   str = "<SPAN id=\"id" + pControl.name + "\" ";
            if (pControl.visibility.equals("inherit") == false)
            {  str = str + "style=\"visibility:" + pControl.visibility  + "\" ";
            }
            str = str + ">";
    	    str = str + "<input type=\"checkbox\" ";
        }
        else
        {   str = "<input type=\"hidden\" ";
        }

        str = str + "name=\"" + pControl.name + "\" ";
        if (pControl.tabindex != -9999)
        {    str = str + "tabindex=\"" + pControl.tabindex + "\" ";
        }
        if (pControl.visibility.equals("inherit") == false)
        {  str = str + "style=\"visibility:" + pControl.visibility  + "\" ";
        }
        str = str + "onclick=\"" + pControl.name + "_Click('" + pControl.checkedvalue + "','" + pControl.uncheckedvalue + "')\" ";
		if (pControl.enabled == false)
        {	str = str + "disabled ";
        }
        for (int j=0; j < pControl.eventmaxindex;j++)
        {   if (pControl.eventname[j] != null)
            {   str = str + pControl.eventname[j] + "=\" ";
                str = str + pControl.eventmethod[j] + "\" ";
            }
        }
        for (int j=0; j < pControl.event.length;j++)
        {   if (pControl.event[j] != null)
            {   str = str + pControl.event[j] + "=\" ";
                str = str + pControl.method[j] + "\" ";
            }
        }
        str = str + ">";

		if (pControl.visible  == true)
        {	str = str + pControl.caption ;
            str = str + "</SPAN>";
    	}
        return (str);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "printCheckbox");
        return("");
    }

}

/* printCombobox */
public String printCombobox (XseedCombobox pControl)
	throws Exception
{
    try
    {	int i=0;

        String str ="";
    	str= "<select ";
    	str = str + "name=\"" + pControl.name + "\" id=\"id" + pControl.name + "\" ";
        if (pControl.tabindex != -9999)
        {    str = str + "tabindex=\"" + pControl.tabindex + "\"";
        }
        for (int j=0; j < pControl.eventmaxindex;j++)
        {   if (pControl.eventname[j] != null)
            {   str = str + pControl.eventname[j] + "=\" ";
                str = str + pControl.eventmethod[j] + "\" ";
            }
        }
        for (int j=0; j < pControl.event.length;j++)
        {   if (pControl.event[j] != null)
            {   str = str + pControl.event[j] + "=\" ";
                str = str + pControl.method[j] + "\" ";
            }
        }
        str = str + ">";
        while ((i < pControl.maxindex) && (pControl.listname[i]!=null))
        {	str = str + "<option ";
            str = str + "value=\"" + pControl.listvalue[i] + "\">" + pControl.listname[i] + "</option>";
        	i++;
        }
        str = str + "</select>";
    	return(str);

    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "printCombobox");
    	return("");
    }

}

/* printListbox */
public String printListbox (XseedListbox pControl)
    throws Exception
{
    try
    {   int i=0;
        String str ="";
        if (pControl.visible  == true)
        {   str= "<select ";
        }
        else
        {   str = "<input type=\"hidden\" ";
        }

        str = str + "name=\"" + pControl.name + "\" id=\"id" + pControl.name + "\" ";

        if (pControl.visibility.equals("inherit") == false)
        {   str = str + "style=\"visibility:" + pControl.visibility  + "\" ";
        }

        if (pControl.multiple == true)
        {	str = str + " MULTIPLE ";
        }
        if (pControl.tabindex != -9999)
        {   str = str + "tabindex=\"" + pControl.tabindex + "\" ";
        }

        str = str + "size=\"" + (int) pControl.height / 285 + "\" ";

        if (pControl.enabled == false)
        {	str = str + "disabled ";
        }
        for (int j=0; j < pControl.eventmaxindex;j++)
        {   if (pControl.eventname[j] != null)
            {   str = str + pControl.eventname[j] + "=\" ";
                str = str + pControl.eventmethod[j] + "\" ";
            }
        }
        for (int j=0; j < pControl.event.length;j++)
        {   if (pControl.event[j] != null)
            {   str = str + pControl.event[j] + "=\" ";
                str = str + pControl.method[j] + "\" ";
            }
        }
        str = str + " >";
        if (pControl.visible  == true)
        {   while ((i < pControl.maxindex) && (pControl.listname[i]!=null))
            {   str = str + "<option ";

                if (pControl.listvalue[i].equals("ß")== true)
                {
                    if (pControl.edit.equals("A")== true)
                    {
                        pControl.listvalue[i]= "";
                    }
                    else
                    {
                        pControl.listvalue[i]="0";
                    }
                }
                if (pControl.listname[i].equals("ß")== true)
                {
                    pControl.listname[i]= "";
                }

                str = str + "value=\"" + pControl.listvalue[i] + "\">" + pControl.listname[i] + "</option>";
                i++;
            }
            str = str + "</select>";
        }
        return(str);

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "printListbox");
        return("");
    }
}

/* printSpan */
public String printSpan (XseedLabel pControl)
	throws Exception
{
    try
	{	String str= "";

    	str = "<SPAN id=\"id"+ pControl.name +  "\"";
    	if (pControl.onclick.equals("") == false)
    	{	str = str + " onClick=\"" + pControl.onclick + ";\"";
    	}
    	else
    	{	if ((pControl.callsubmit == true )	|| (pControl.fieldname.equals("")== false))
    		{	if (pControl.fieldname.equals("")== false)
    			{	str = str + " onClick=\"" + pControl.name + "_OnClick(frm" + ISPEC + "." + pControl.fieldname + ",'"+ pControl.fieldvalue + "' );\"";
    			}
    			else
    			{	str = str + " onClick=\"" + pControl.name + "_OnClick(null,'"+ pControl.fieldvalue + "' );\"";
    			}

    		}
    	}
    	if (pControl.tooltiptext != "")
        {
        	str = str + "title = \"" + pControl.tooltiptext + "\"";
        }
        for (int i=0; i < pControl.eventmaxindex;i++)
        {   if (pControl.eventname[i] != null)
            {   str = str + pControl.eventname[i] + "=\" ";
                str = str + pControl.eventmethod[i] + "\" ";
            }
        }
        for (int i=0; i < pControl.event.length;i++)
        {   if (pControl.event[i] != null)
            {   str = str + pControl.event[i] + "=\" ";
                str = str + pControl.method[i] + "\" ";
            }
        }
    	str = str +  ">";
    	if (pControl.hyperlink.equals("")== false)
    	{	str = str + "<a CLASS=id" + pControl.name + " href=\"" + pControl.hyperlink +  "\">" + pControl.caption + "</a>";
    	}
    	else
    	{	str = str + pControl.caption;
    	}
    	str = str + "</SPAN>";
    	return(str);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "printSpan");
    	return("");
    }
}

/* printImage */
public String printImage (XseedImage pControl)
	throws Exception
{
    try
	{	String strEventOnClick="";
		String strEvents="";
		String str;
		String picturePath;
		String auxPath;
		String controlPicture;
		int j;

		str = "<";

		if (pControl.visible  == false)
        {	str = str + "input type=\"hidden\" ";
		}
		else
		{	str = str +"IMG ";
		}

        str = str +" name=\"" + pControl.name + "\" id=\"id" + pControl.name + "\"";


        if (pControl.enabled == false)
		{	str = str + " tabindex=\"-1\" ";
	    }
	    else
        {
            if (pControl.tabindex != -9999)
            {    str = str + " tabindex=\"" +  pControl.tabindex + "\" ";
            }
	    }


        if (pControl.visibility.equals("inherit") == false)
        {   str = str + "style=\"visibility:" + pControl.visibility  + "\" ";
        }

        j = pControl.picture.toUpperCase().indexOf("$(ICONSPATH)");

        if (j != -1)
        {
        	controlPicture = pControl.picture.substring(j+12);

        	if (controlPicture.substring(0,1).equals("/")==true)
        	{	controlPicture = controlPicture.substring(1);
        	}

        	if (GLB.ICONSDIR.substring(GLB.ICONSDIR.length()-1).equals("/")==true)
        	{	auxPath = GLB.ICONSDIR;
        	}
        	else
        	{	auxPath = GLB.ICONSDIR + "/";
        	}

        	picturePath = auxPath + controlPicture;

    	}
    	else if (pControl.picture.toUpperCase().indexOf("$(SYSTEMPATH)") != -1)
        {	j = pControl.picture.toUpperCase().indexOf("$(SYSTEMPATH)");

        	controlPicture = pControl.picture.substring(j+13);

        	if (controlPicture.substring(0,1).equals(File.separator)==true)
        	{	controlPicture=controlPicture.substring(1);
        	}

        	if (GLB.SYSTEMDIR.substring(GLB.SYSTEMDIR.length()-1).equals(File.separator)==true)
        	{	auxPath = GLB.SYSTEMDIR;
        	}
        	else
        	{	auxPath = GLB.SYSTEMDIR + File.separator;
        	}
        	picturePath = auxPath + controlPicture;

    	}
    	else
    	{	picturePath = pControl.picture;
    	}

        str = str + " src=\"" + picturePath + "\" ";

        if (pControl.tooltiptext.equals("") == false)
        {   str = str + " title =\"" + pControl.tooltiptext + "\" "; 
        }

        if (pControl.enabled == false)
        {	str = str + "disabled ";
        }

		if (pControl.onclick.equals("") == false)
    	{	//str = str + " onClick=\"" + pControl.onclick + ";\"";
    	    strEventOnClick = strEventOnClick + pControl.onclick + ";";
    	}
    	else
		{	if ((pControl.callsubmit == true) || (pControl.fieldname.equals("") == false))
			{	if (pControl.fieldname.equals("")== false)
    			{	//str = str + " onClick=\"" + pControl.name + "_OnClick(frm" + ISPEC + "." + pControl.fieldname + ",'"+ pControl.fieldvalue + "' );\"";
    			    strEventOnClick = strEventOnClick + pControl.name + "_OnClick(frm" + ISPEC + "." + pControl.fieldname + ",'"+ pControl.fieldvalue + "' );";
    			}
    			else
    			{	//str = str + " onClick=\"" + pControl.name + "_OnClick(null,'"+ pControl.fieldvalue + "' );\"";
    			    strEventOnClick = strEventOnClick + pControl.name + "_OnClick(null,'"+ pControl.fieldvalue + "' );";
    			}
    		}
		}
			/*
         *Tratamento eventos
         **/
        for (int i=0; i < pControl.eventmaxindex;i++)
        {   if (pControl.eventname[i] != null)
        	{
            	if (pControl.eventname[i].equalsIgnoreCase("onclick") == true)
            	{
                	strEventOnClick = strEventOnClick +  pControl.eventmethod[i] + "; ";
            	}
            	else
            	{   strEvents = strEvents + pControl.eventname[i] + "=\" " ;
                	strEvents = strEvents + pControl.eventmethod[i] + "\" ";
            	}
        	}
    	}


    	if (strEventOnClick.equals("")==false)
    	{
        	str = str + " onclick=\"" + strEventOnClick + "\" ";
    	}

 		str = str + strEvents ;


		/*for (int i=0; i < pControl.eventmaxindex;i++)
        {   if (pControl.eventname[i] != null)
            {   str = str + pControl.eventname[i] + "=\" ";
                str = str + pControl.eventmethod[i] + "\" ";
            }
        }*/
        for (int i=0; i < pControl.event.length;i++)
        {   if (pControl.event[i] != null)
            {   str = str + pControl.event[i] + "=\" ";
                str = str + pControl.method[i] + "\" ";
            }
        }
        str = str +  "> ";
       	return(str);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "printImage");
    	return("");
    }
}

/* printStyle */
public String printStyle(XseedCheckbox pControl,XseedCSS pIspec)
 throws Exception
{
    try
    {   String ctrStyle= "";
        ctrStyle ="{position:absolute;top:" + cStr(twipsperPixel(pControl.top)) + "px;left:"+ cStr(twipsperPixel(pControl.left))
        + "px;width:" + cStr(twipsperPixel(pControl.width)) +  "px;height:" + cStr(twipsperPixel(pControl.height)) + "px;";

        if (pControl.alignment == 1)
        {   ctrStyle = ctrStyle + "text-align:right;";
        }
        else if (pControl.alignment == 2)
        {   ctrStyle = ctrStyle + "text-align:center;";
        }
        else
        {   ctrStyle = ctrStyle + "text-align:left;";
        }

        if ((pControl.forecolor.equals("000000") == false)&& (pControl.forecolor.equals("") ==false))
		{	ctrStyle = ctrStyle + "color:" + pControl.forecolor + ";";
		}
		if ((pControl.backcolor.equals("FFFFFF") == false)&& (pControl.backcolor.equals("") ==false))
		{	ctrStyle = ctrStyle + "background-color:" + pControl.backcolor + ";";
		}

        if (pControl.font.equals(pIspec.font) == false)
        {	ctrStyle = ctrStyle + "font-family: " + pControl.font +  ";";
    	}

    	if (pControl.fontsize != pIspec.fontsize)
    	{	ctrStyle = ctrStyle +	"font-size: " + pControl.fontsize + "pt;";
    	}

    	if (pControl.fontbold != pIspec.fontbold)
    	{	if (pControl.fontbold == true)
    		{	ctrStyle = ctrStyle + "font-weight: bold;";
    		}
    		else
    		{	ctrStyle = ctrStyle + "font-weight: normal;";
    		}
    	}

    	if (pControl.fontitalic != pIspec.fontitalic)
    	{	if (pControl.fontitalic == true)
    		{	ctrStyle = ctrStyle + "font-style: italic;";
    		}
    		else
    		{	ctrStyle = ctrStyle + "font-style: normal;";
    		}
    	}

        ctrStyle = ctrStyle + "}";
        return(ctrStyle);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "printStyle");
        return("");
    }
}


/* printStyle */
public String printStyle(XseedCombobox pControl,XseedCSS pIspec)
 throws Exception
{
    try
	{	String ctrStyle= "";
		ctrStyle ="{position:absolute;top:" + cStr(twipsperPixel(pControl.top)) + "px;left:"+ cStr(twipsperPixel(pControl.left))
		+ "px;width:" + cStr(twipsperPixel(pControl.width)) +  "px;height:21px;";
        if (pControl.visible  == false)
        {   ctrStyle = ctrStyle + "visibility:hidden;";
        }
		ctrStyle = ctrStyle + "}";
		return(ctrStyle);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "printStyle");
    	return("");
    }
}

/* printStyle */
public String printStyle(XseedListbox pControl,XseedCSS pIspec)
 throws Exception
{
    try
    {   String ctrStyle= "";
        ctrStyle ="{position:absolute;top:" + cStr(twipsperPixel(pControl.top)) + "px;left:"+ cStr(twipsperPixel(pControl.left))
        + "px;width:" + cStr(twipsperPixel(pControl.width)) +  "px;height:"+ cStr(twipsperPixel(pControl.height)) + "px;";

        if ((pControl.forecolor.equals("000000") == false)&& (pControl.forecolor.equals("") ==false))
		{	ctrStyle = ctrStyle + "color:" + pControl.forecolor + ";";
		}
		if ((pControl.backcolor.equals("FFFFFF") == false)&& (pControl.backcolor.equals("") ==false))
		{	ctrStyle = ctrStyle + "background-color:" + pControl.backcolor + ";";
		}

        if (pControl.font.equals(pIspec.font) == false)
        {	ctrStyle = ctrStyle + "font-family: " + pControl.font +  ";";
    	}

    	if (pControl.fontsize != pIspec.fontsize)
    	{	ctrStyle = ctrStyle +	"font-size: " + pControl.fontsize + "pt;";
    	}

    	if (pControl.fontbold != pIspec.fontbold)
    	{	if (pControl.fontbold == true)
    		{	ctrStyle = ctrStyle + "font-weight: bold;";
    		}
    		else
    		{	ctrStyle = ctrStyle + "font-weight: normal;";
    		}
    	}
    	if (pControl.fontitalic != pIspec.fontitalic)
    	{	if (pControl.fontitalic == true)
    		{	ctrStyle = ctrStyle + "font-style: italic;";
    		}
    		else
    		{	ctrStyle = ctrStyle + "font-style: normal;";
    		}
    	}

        ctrStyle = ctrStyle + "}";
        return(ctrStyle);
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "printStyle");
        return("");
    }
}


/* printStyle */
public String printStyle(XseedTextbox pControl,XseedCSS pIspec)
 throws Exception
{
    try
	{	String ctrStyle= "";

		ctrStyle ="{position:absolute;top:" + cStr(twipsperPixel(pControl.top)) + "px;left:"+ cStr(twipsperPixel(pControl.left))
		+ "px;width:" + cStr(twipsperPixel(pControl.width)) +  "px;height:"+ cStr(twipsperPixel(pControl.height)) + "px;";

        if (pControl.appearance.equalsIgnoreCase("flat")==true)
        {
            ctrStyle = ctrStyle + "border-color: #B9B9B9; border-width: 1px; border-style: solid;";
        }

		if ((pControl.notentered  == true) || (pControl.name.equals("XSEEDMSG") == true))
		{	ctrStyle = ctrStyle + "border:0;";
        }
        if ((pControl.forecolor.equals("000000") == false)&& (pControl.forecolor.equals("") ==false))
		{	ctrStyle = ctrStyle + "color:" + pControl.forecolor + ";";
		}
		if ((pControl.backcolor.equals("FFFFFF") == false)&& (pControl.backcolor.equals("") ==false))
		{	ctrStyle = ctrStyle + "background-color:" + pControl.backcolor + ";";
		}

        if (pControl.font.equals(pIspec.font) == false)
        {	ctrStyle = ctrStyle + "font-family: " + pControl.font +  ";";
    	}

    	if (pControl.fontsize != pIspec.fontsize)
    	{	ctrStyle = ctrStyle +	"font-size: " + pControl.fontsize + "pt;";
    	}

    	if (pControl.fontbold != pIspec.fontbold)
    	{	if (pControl.fontbold == true)
    		{	ctrStyle = ctrStyle + "font-weight: bold;";
    		}
    		else
    		{	ctrStyle = ctrStyle + "font-weight: normal;";
    		}
    	}

    	if (pControl.fontitalic != pIspec.fontitalic)
    	{	if (pControl.fontitalic == true)
    		{	ctrStyle = ctrStyle + "font-style: italic;";
    		}
    		else
    		{	ctrStyle = ctrStyle + "font-style: normal;";
    		}
    	}

        if ((pControl.edit.equals("A") == false ) && (pControl.edit.equals("D") == false ))
		{	if (pControl.leftfill == false)
            {	ctrStyle = ctrStyle + "text-align:right;";
            }
		}
        else
        {	if (pControl.alignment == 1)
         	{	ctrStyle = ctrStyle + "text-align:right;";
            }
			else if (pControl.alignment == 2)
            {	ctrStyle = ctrStyle + "text-align:center;";
        	}

        }
        if (pControl.visible == false)
        {   ctrStyle = ctrStyle + "visibility:hidden;";
        }

		ctrStyle = ctrStyle + "}";
		return(ctrStyle);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "printStyle");
    	return("");
    }
}

/* printStyle */
public String printStyle(XseedLabel pControl,XseedCSS pIspec)
 throws Exception
{
    try
	{	String ctrStyle= "";
		ctrStyle ="{position:absolute;top:" + cStr(twipsperPixel(pControl.top)) + "px;left:"+ cStr(twipsperPixel(pControl.left))
		+ "px;width:" + cStr(twipsperPixel(pControl.width)) +  "px;height:"+ cStr(twipsperPixel(pControl.height)) + "px;";

		if ((pControl.forecolor.equals(pIspec.forecolor)== false)&& (pControl.forecolor.equals("") ==false))
		{	ctrStyle = ctrStyle + "color:" + pControl.forecolor + ";";
        }
		if (pControl.backstyle.equals("transparent") == true)
        {    ctrStyle = ctrStyle + "background-color:transparent;";
        }
        else
        {
		    if ((pControl.backcolor.equals(pIspec.backcolor) == false)&& (pControl.backcolor.equals("") ==false))
		    {	ctrStyle = ctrStyle + "background-color:" + pControl.backcolor + ";";
		    }
	    }
        if (pControl.font.equals(pIspec.font) == false)
        {	ctrStyle = ctrStyle + "font-family: " + pControl.font +  ";";
    	}

    	if (pControl.fontsize != pIspec.fontsize)
    	{	ctrStyle = ctrStyle +	"font-size: " + pControl.fontsize + "pt;";
    	}

    	if (pControl.fontbold != pIspec.fontbold)
    	{	if (pControl.fontbold == true)
    		{	ctrStyle = ctrStyle + "font-weight: bold;";
    		}
    		else
    		{	ctrStyle = ctrStyle + "font-weight: normal;";
    		}
    	}

    	if (pControl.fontitalic != pIspec.fontitalic)
    	{	if (pControl.fontitalic == true)
    		{	ctrStyle = ctrStyle + "font-style: italic;";
    		}
    		else
    		{	ctrStyle = ctrStyle + "font-style: normal;";
    		}
    	}

		if (pControl.alignment == 1)
		{	ctrStyle = ctrStyle + "text-align:right;";
        }
		else if (pControl.alignment == 2)
        {	ctrStyle = ctrStyle + "text-align:center;";
    	}

    	if ((pControl.callsubmit == true) || (pControl.fieldname.equals("") == false))
		{	ctrStyle = ctrStyle + "cursor:hand;";
		}

		if (pControl.visible == false)
		{	ctrStyle = ctrStyle + "visibility:hidden;";
		}
		//falta hyperlink

		ctrStyle = ctrStyle + "}";
		return(ctrStyle);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "printStyle");
    	return("");
    }
}

/* printStyle */
public String printStyle(XseedImage pControl,XseedCSS pIspec)
 throws Exception
{
    try
	{	String ctrStyle= "";
		ctrStyle ="{position:absolute;top:" + cStr(twipsperPixel(pControl.top)) + "px;left:"+ cStr(twipsperPixel(pControl.left))
		+ "px;width:" + cStr(twipsperPixel(pControl.width)) +  "px;height:"+ cStr(twipsperPixel(pControl.height)) + "px; ";

        if ((pControl.callsubmit == true) || (pControl.fieldname.equals("") == false))
        {   ctrStyle = ctrStyle + "cursor:hand;";
        }

		ctrStyle = ctrStyle + "}";
		return(ctrStyle);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "printStyle");
    	return("");
    }
}

/* printTextbox */
public String printTextbox (XseedTextbox pControl, XseedCSS pIspec)
	throws Exception
{
    try
	{	String strEventOnBlur="";
	    String strEventOnFocus="";
	    String strEventOnClick="";
	    String strEventOnKeyPress="";
	    String strEventOnKeyUp="";
	    String strEvents="";
		String str= "<";
		double iLength=0;
        double Maxgrp=0;

		if ((pControl.usage.equals("OUT") == true) || (pControl.visible  == false))
        {	str = str + "input type=\"hidden\" ";
		}
        else if (pControl.secure== true)
		{	str = str + "input type=\"password\" ";
		}
		else if (pControl.multiline == true)
		{	str = str + "textarea ";
		}
		else
		{	str = str + "input type=\"text\"";
		}

		str = str + " name=\"" + pControl.name + "\" id=\"id" + pControl.name + "\" ";

        if (pControl.visibility.equals("inherit") == false)
        {   str = str + "style=\"visibility:" + pControl.visibility  + "\" ";
        }


		if ((pControl.notentered == true) || (pControl.name.equals("XSEEDMSG") == true))
		{	str = str + "readonly ";
			str = str + "tabindex=\"-1\" ";
		}

		else if ((pControl.edit.equals("A") == false ) && (pControl.edit.equals("D") == false))
        {   //str = str + "onfocus=\"flagTxt=999;jFocusN(this)\" ";
            strEventOnFocus = strEventOnFocus + "flagTxt=999;jFocusN(this); ";
            if (pControl.zerosuppress == false)
            {    //str = str + "onblur=\"flagTxt=0;jBlurN(this)" ";
            	strEventOnBlur = strEventOnBlur + "flagTxt=0;jBlurN(this); ";
            }
            else
            {    //str = str + "onblur=\"flagTxt=0\" ";
            	strEventOnBlur = strEventOnBlur + "flagTxt=0; ";
            }
            if (pControl.tabstop == false)
            {   str = str + "tabindex=\"-1\" ";
            }
            else
            {   if (pControl.tabindex != -9999)
                {   str = str + "tabindex=\"" + pControl.tabindex +  "\" ";
                }
            }
    	}
        else
        {   //str = str + "onfocus=\"flagTxt=999;jFocusA(this)\" ";
            strEventOnFocus = strEventOnFocus + "flagTxt=999;jFocusA(this); ";
            //str = str + "onblur=\"flagTxt=0\" ";
            strEventOnBlur = strEventOnBlur + "flagTxt=0; ";


            if (pControl.tabstop == false)
            {   str = str + "tabindex=\"-1\" ";
            }
            else
            {   if (pControl.tabindex != -9999)
                {   str = str + "tabindex=\"" + pControl.tabindex + "\" ";
                }
            }
		}
		if (pControl.onclick.equals("") == false)
    	{	//str = str + " onClick=\"" + pControl.onclick + ";\"";
    	    strEventOnClick = strEventOnClick + pControl.onclick + "; " ;
    	}


		// O procedimento abaixo calcula o width correto para os campo
        // sinalizado, levando em consideracao o width especificado
        // no painter
        if ((pControl.edit.equals("A")== false) && (pControl.edit.equals("D")== false))
        {   //O MinWidth depende do width do campo sinalizado (fontsize)

			iLength = pControl.maxlength;
			if (pControl.decimals > 0)
			{	iLength = iLength + 1;
			}
            if (pControl.showseparator == true)
            {   Maxgrp = pControl.length - pControl.decimals;

				if (Maxgrp%3 ==0)    //If (Maxgrp Mod 3) = 0 Then
                {	Maxgrp = (Maxgrp / 3) - 1;
                }
                else
                {   Maxgrp = divide(3.0,Maxgrp,5,0,"TRUNCATE","UNSIGEND");
                }
			}

			str = str + "maxlength=\"" + cStr(iLength + Maxgrp) + "\" ";

            if ((pControl.usage.equals("OUT") == false) &&
                (pControl.visible == true) && (pControl.notentered == false))
            {
                //str = str + "onkeypress=\"return(jKeyPress(event))\" ";
                strEventOnKeyPress = strEventOnKeyPress + "return(jKeyPress(event)); ";

                if (pIspec.autotab == true)
                {   //str = str + "onkeyup=\"jTab(this, " + cStr(iLength + Maxgrp) + ", event) \"";
                    strEventOnKeyUp = strEventOnKeyUp + "jTab(this, " + cStr(iLength + Maxgrp) + ", event); ";
                }

            }
		}
		else
        {	str = str + "maxlength=\"" + cStr(pControl.maxlength) + "\" ";

            if ((pControl.usage.equals("OUT") == false) && (pControl.visible == true) && (pControl.notentered == false))
            {
                if ( (pIspec.autotab == true) || ((pControl.edit.equals("A")== true) && (pIspec.autotabtexts == true) ))
                {
                    //str = str + "onkeyup=\"jTab(this, " + cStr(pControl.maxlength) + ", event)\" ";
                    strEventOnKeyUp = strEventOnKeyUp + "jTab(this, " + cStr(pControl.maxlength) + ", event); ";
                }
            }

        }

        if (pControl.enabled == false)
        {	str = str + "disabled ";
        }

		if (pControl.tooltiptext != "")
        {
        	str = str + "title = \"" + pControl.tooltiptext + "\"";
        }

        if (pControl.multiline == true)
		{	//str = str + " onKeyPress = \"" + pControl.name +  "_MaxLength()\"";
		    strEventOnKeyPress = strEventOnKeyPress + pControl.name +  "_MaxLength(); ";
		}

	    /*
         *Tratamento eventos
         **/
        for (int i=0; i < pControl.eventmaxindex;i++)
        {   if (pControl.eventname[i] != null)
        	{
            	if (pControl.eventname[i].equalsIgnoreCase("onblur") == true)
            	{
                	strEventOnBlur = strEventOnBlur +  pControl.eventmethod[i] + "; ";
            	}
            	else if (pControl.eventname[i].equalsIgnoreCase("onclick") == true)
            	{
                	strEventOnClick = strEventOnClick +  pControl.eventmethod[i] + "; ";
            	}
            	else if (pControl.eventname[i].equalsIgnoreCase("onfocus") == true)
            	{
                	strEventOnFocus = strEventOnFocus +  pControl.eventmethod[i] + "; ";
            	}
            	else if (pControl.eventname[i].equalsIgnoreCase("onkeypress") == true)
            	{
                	strEventOnKeyPress = strEventOnKeyPress +  pControl.eventmethod[i] + "; ";
            	}
            	else if (pControl.eventname[i].equalsIgnoreCase("onkeyup") == true)
            	{
                	strEventOnKeyUp = strEventOnKeyUp + pControl.eventmethod[i] + "; ";
            	}
            	else
            	{   strEvents = strEvents + pControl.eventname[i] + "=\" " ;
                	strEvents = strEvents + pControl.eventmethod[i] + "\" ";
            	}
        	}
    	}

		if (strEventOnBlur.equals("")==false)
    	{
        	str = str + " onblur=\"" + strEventOnBlur + "\" ";
    	}
    	if (strEventOnFocus.equals("")==false)
    	{
        	str = str + " onfocus=\"" + strEventOnFocus + "\" ";
    	}
    	if (strEventOnClick.equals("")==false)
    	{
        	str = str + " onclick=\"" + strEventOnClick + "\" ";
    	}
    	if (strEventOnKeyPress.equals("")==false)
    	{
        	str = str + " onkeypress=\"" + strEventOnKeyPress + "\" ";
    	}
    	if (strEventOnKeyUp.equals("")==false)
    	{
        	str = str + " onkeyup=\"" + strEventOnKeyUp + "\" ";
    	}

 		str = str + strEvents ;


		/*for (int i=0; i < pControl.eventmaxindex;i++)
        {   if (pControl.eventname[i] != null)
            {   str = str + pControl.eventname[i] + "=\" ";
                str = str + pControl.eventmethod[i] + "\" ";
            }
        }*/


        for (int i=0; i < pControl.event.length;i++)
        {   if (pControl.event[i] != null)
            {   str = str + pControl.event[i] + "=\" ";
                str = str + pControl.method[i] + "\" ";
            }
        }
		str  = str + ">";


		return(str);
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "printInputType");
    	return("");
    }
}

/* RECALL */
public void recall(String pRecall, String pTeach)
     throws Exception
{
    try
    {
	GLB.RECALL = pRecall;
	if (pTeach.equals("") == false)
	{
           GLB.TEACH = pRecall + pTeach;
	}
	else
	{
	   GLB.TEACH = "";
	}

	GLB.ERROR = "*****";
    }
    catch (Exception e)
    {
    	ShowErrorMsg (e, "recall");
    }
}

public void recall()
     throws Exception
{
    recall (ISPEC,"");
}

/* redirect */
public void redirect(String pRedirect)
     throws Exception
{
    try
    {
       GLB.REDIRECT = pRedirect;
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "redirect");
    }
}

/* reloadRts */
public void reloadRts(HttpSession pSession)
       throws Exception
{
    try
    {

      /*  pSession.setAttribute("GLB_APPNAME",XseedINI.getAppName());
        pSession.setAttribute("GLB_AUDITDIR", XseedINI.getAuditPath());
        pSession.setAttribute("GLB_BACKUPDIR", XseedINI.getBackupPath());
        pSession.setAttribute("GLB_BATCHDIR", XseedINI.getBatchPath());
        pSession.setAttribute("GLB_CLONEAPP",XseedINI.getCloneApp());
        pSession.setAttribute("GLB_CLONEAPPDIR", XseedINI.getCloneAppPath());
        pSession.setAttribute("GLB_CLONERTS", XseedINI.getCloneRts());
        pSession.setAttribute("GLB_CLONERTSDIR", XseedINI.getCloneRtsPath());
        pSession.setAttribute("GLB_DBNAME",XseedINI.getDBName());
        pSession.setAttribute("GLB_PASSWORD", XseedINI.getDBPassword());
        pSession.setAttribute("GLB_USERNAME",XseedINI.getDBUsername());
        pSession.setAttribute("GLB_DOCDIR",  XseedINI.getDocPath());
        pSession.setAttribute("GLB_DSN",  XseedINI.getDSN());
        pSession.setAttribute("GLB_EXTRACTSDIR", XseedINI.getExtractsPath());
        pSession.setAttribute("GLB_FILESDIR",  XseedINI.getFilesPath());
        pSession.setAttribute("GLB_FIREUPISPEC", XseedINI.getFireupIspec());
        pSession.setAttribute("GLB_ICONSDIR", XseedINI.getIconsPath());
        pSession.setAttribute("GLB_MSGSDIR", XseedINI.getMsgsPath());
        pSession.setAttribute("GLB_MSGTIMER", XseedINI.getMsgTimer());
        pSession.setAttribute("GLB_SERVER",XseedINI.getServer());
        pSession.setAttribute("GLB_SYSTEMDIR", XseedINI.getSystemPath());
        pSession.setAttribute("GLB_STN", XseedINI.getSTN());
        pSession.setAttribute("GLB_TARGETOS", XseedINI.getTargetOS());
        pSession.setAttribute("GLB_TEMPDIR", XseedINI.getTempPath());
        pSession.setAttribute("GLB_USERCODE", XseedINI.getUserCode());*/

   }
   catch (Exception e)
   {
      ShowErrorMsg (e,"readXseedIni");
   }
}


/* reloadRts */
public void reloadRts(HttpSession pSession, XseedConfiguration pXsdConfig)
       throws Exception
{
    try
    {

        pSession.setAttribute("GLB_APPNAME",pXsdConfig.getProperty("AppName"));
        pSession.setAttribute("GLB_AUDITDIR", pXsdConfig.getProperty("AuditPath"));
        pSession.setAttribute("GLB_BACKUPDIR", pXsdConfig.getProperty("BackupPath"));
        pSession.setAttribute("GLB_BATCHDIR", pXsdConfig.getProperty("BatchPath"));
        pSession.setAttribute("GLB_CLONEAPP",pXsdConfig.getProperty("CloneApp"));
        pSession.setAttribute("GLB_CLONEAPPDIR", pXsdConfig.getProperty("CloneAppPath"));
        pSession.setAttribute("GLB_CLONERTS", pXsdConfig.getProperty("CloneRts"));
        pSession.setAttribute("GLB_CLONERTSDIR", pXsdConfig.getProperty("CloneRtsPath"));
        pSession.setAttribute("GLB_DBNAME",pXsdConfig.getProperty("DBName"));
        pSession.setAttribute("GLB_PASSWORD", pXsdConfig.getProperty("DBPassword"));
        pSession.setAttribute("GLB_USERNAME",pXsdConfig.getProperty("DBUsername"));
        pSession.setAttribute("GLB_DOCDIR",  pXsdConfig.getProperty("DocPath"));
        pSession.setAttribute("GLB_DSN",  pXsdConfig.getProperty("DSN"));
        pSession.setAttribute("GLB_EXTRACTSDIR", pXsdConfig.getProperty("ExtractsPath"));
        pSession.setAttribute("GLB_FILESDIR",  pXsdConfig.getProperty("FilesPath"));
        pSession.setAttribute("GLB_FIREUPISPEC", pXsdConfig.getProperty("FireupIspec"));
        pSession.setAttribute("GLB_ICONSDIR", pXsdConfig.getProperty("IconsPath"));
        pSession.setAttribute("GLB_MSGSDIR", pXsdConfig.getProperty("MsgsPath"));
        pSession.setAttribute("GLB_MSGTIMER", pXsdConfig.getProperty("MsgTimer"));
        pSession.setAttribute("GLB_SERVER",pXsdConfig.getProperty("Server"));
        pSession.setAttribute("GLB_SYSTEMDIR", pXsdConfig.getProperty("SystemPath"));
        pSession.setAttribute("GLB_STN", pXsdConfig.getProperty("STN"));
        pSession.setAttribute("GLB_TARGETOS", pXsdConfig.getProperty("TargetOS"));
        pSession.setAttribute("GLB_TEMPDIR", pXsdConfig.getProperty("TempPath"));
        pSession.setAttribute("GLB_USERCODE", pXsdConfig.getProperty("UserCode"));

   }
   catch (Exception e)
   {
      ShowErrorMsg (e,"readXseedIni");
   }
}


/* resetOnXmit */
public void resetOnXmit(HttpSession pSession)
{
   pSession.setAttribute("XSEEDONXMIT","");
}

//Session mode 
public void sendListDynamic (String pControl, String pSdGroup, boolean pUseSessionId, String pMode) throws Exception {
	 try
	    {
		 	if (pMode.equalsIgnoreCase("Session")==true)
		 		sendListDynamicSession ( pControl,  pSdGroup,  pUseSessionId);
		 	else 
		 		sendListDynamic ( pControl,  pSdGroup,  pUseSessionId);  
	    }
	    catch (Exception e)
	    {
	       ShowErrorMsg (e, "sendListDynamic");
	    }
}


//Session mode 
public void sendListDynamicSession (String pControl, String pSdGroup, boolean pUseSessionId) throws Exception {
	 try
	    {
		    ArrayList <String> wSessionControl;
		    String wSessionControlName = "";
		    		    
	        String wControlName = XseedFunctions.replaceChar(pControl, '-', '_');
	       
	        if (wControlName.trim().substring(0,2).equals("*.")==true)
	        {   wSessionControlName =  "asterisk." + wControlName.trim().substring(2);
	        } else {
	        	 wSessionControlName =  "XseedSendListDynamic_" +  wControlName;
	        }

	        wSessionControlName = wSessionControlName + "(Temp)";
	        wSessionControl = (ArrayList<String>)session.getAttribute(wSessionControlName);	    
	        if (wSessionControl == null) {
	        	wSessionControl = new ArrayList<String>();
	        }
	       
	        wSessionControl.add(pSdGroup);	        	
	        session.setAttribute(wSessionControlName,  wSessionControl);
	        
	    }
	    catch (Exception e)
	    {
	       ShowErrorMsg (e, "sendListDynamicSession");
	    }
}

/* sendListDynamic */
public void sendListDynamic (String pControl, String pSdGroup, boolean pUseSessionId)
  throws Exception
{
    try
    {
        String wControlName = XseedFunctions.replaceChar(pControl, '-', '_');
        String wSessionId = "";
        if (pUseSessionId  == true)
        {
            wSessionId = request.getRequestedSessionId();
        }
        XseedFile sldyFile = new XseedFile();

        if (wControlName.trim().substring(0,2).equals("*.")==true)
        {   wControlName = "asterisk." + wControlName.trim().substring(2);
        }

        sldyFile.open(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + wSessionId +wControlName + "(Temp)", "APPEND");

        sldyFile.write(pSdGroup);
        sldyFile.close();

    }
    catch (Exception e)
    {
       ShowErrorMsg (e, "sendListDynamic");
    }
}

/* sendListDynamic */
public void sendListDynamic (String pControl, String pSdGroup)
  throws Exception
{
    try
    {
        String wControlName = XseedFunctions.replaceChar(pControl, '-', '_');
        XseedFile sldyFile = new XseedFile();

        if (wControlName.trim().substring(0,2).equals("*.")==true)
        {   wControlName = "asterisk." + wControlName.trim().substring(2);
        }

        sldyFile.open(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + wControlName + "(Temp)", "APPEND");

        sldyFile.write(pSdGroup);
        sldyFile.close();

    }
    catch (Exception e)
    {
       ShowErrorMsg (e, "sendListDynamic");
    }
}

/* sendListDynamic */
public void sendListDynamic (XseedCombobox pControl, String pSdGroup)
  throws Exception
{
    try
    {

        String listname="";
        String listvalue="";
        String separator="";
        String sdGroup= pSdGroup;
        int i;

        if (pControl.maxindex == 0)
        {   pControl.maxindex = 1000;
            pControl.listname = new String [(int)pControl.maxindex];
            pControl.listvalue = new String [(int)pControl.maxindex];
        }

        if ((int)pControl.maxindex <= (int)pControl.listindex) {
        	pControl.maxindex = pControl.maxindex + 500;
        	pControl.listname = XseedFunctions.ReDimPreserve(pControl.listname,(int) pControl.maxindex);
            pControl.listvalue =XseedFunctions.ReDimPreserve(pControl.listvalue,(int) pControl.maxindex);        	
        }
        separator = pSdGroup.substring(0,1);

        i= sdGroup.indexOf(separator,1);

        if (i!=-1)
        {   listvalue = sdGroup.substring(1,i);
            listname = sdGroup.substring(++i);
            pControl.listname[(int)pControl.listindex] = listname;
            pControl.listvalue[(int)pControl.listindex] = listvalue;
            pControl.listindex++;
        }
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "sendListDynamic");
    }
}

/* sendListDynamic */
public void sendListDynamic (XseedListbox pControl, String pSdGroup)
  throws Exception
{
    try
    {
        String listname="";
        String listvalue="";
        String separator="";
        String sdGroup= pSdGroup;
        int i;

        if (pControl.maxindex == 0)
        {   pControl.maxindex = 1000;
            pControl.listname = new String [(int)pControl.maxindex];
            pControl.listvalue = new String [(int)pControl.maxindex];
        }

        separator = pSdGroup.substring(0,1);

        i= sdGroup.indexOf(separator,1);

        if (i!=-1)
        {   listvalue = sdGroup.substring(1,i);
            listname = sdGroup.substring(++i);
            pControl.listname[(int)pControl.listindex] = listname;
            pControl.listvalue[(int)pControl.listindex] = listvalue;
            pControl.listindex++;
        }
    }
    catch (Exception e)
    {   ShowErrorMsg (e, "sendListDynamic");
    }
}
// SET.CLIPAREA
public void setCliparea(HttpSession pSession, String pSource, String pDest)
    throws Exception
{
    try
    {
        pSession.setAttribute("CLIP->" + pDest.toUpperCase(),pSource);

    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "setCliparea");

    }
}


/* setOnXmit */
public void setOnXmit(HttpSession pSession)
   throws Exception
{
   pSession.setAttribute("XSEEDONXMIT","TRUE");
}

/* twipsperPixel */
private double twipsperPixel(double pValue)
    throws Exception
{
    return((int)pValue/15);
}


/* singleCycle*/
public void singleCycle ()
    throws Exception
{
     beforeCycle();
     cycle:
     {

        /* Executing Prelogic */
        PreLogic();
        updGlbWork();

        if (GLB.ERROR.equals("*****"))
        {
            break cycle;
        }

        /* Executing Validate */
        Validate();
        if (GLB.ERROR.equals("*****"))
        {
            break cycle;
        }

        /* Executing Logic */
        Logic();
        updGlbWork();

        if (GLB.ERROR.equals("*****"))
        {
            break cycle;
        }

        Update();

     }  //end cycle

    afterCycle();
    if ( (GLB.RECALL.trim().equals(ISPEC)==true) &&  (GLB.RETURNPS == true))
    {
        IspecRefresh();
        return;
    }

    if ((GLB.RECALL.trim()).equals("")==false)
    {
        return;
    }

    if (GLB.RETURNPS == true)
    {
        IspecRefresh();
        return;
    }

    if ( GLB.MSGINDEX != 0 )
    {
       if (GLB.RESTORE == true)
       {
            FormToScreen(request);
       }
       return;
    }

    IspecRefresh();

}

/* copyFromCycle*/
public void copyFromCycle ()
    throws Exception
{
     beforeCycle();
     cycle:
     {

        /* Executing ValidatePhase */
        ValidatePhase();
        updGlbWork();

        if (GLB.ERROR.equals("*****"))
        {
            break cycle;
        }

        /* Executing UpdatePhase */
        InitialSettings();
        UpdatePhase();
        updGlbWork();

        if (GLB.ERROR.equals("*****"))
        {
            break cycle;
        }


     }  //end cycle

    afterCycle();
    if ( (GLB.RECALL.trim().equals(ISPEC)==true) &&  (GLB.RETURNPS == true))
    {
        IspecRefresh();
        return;
    }

    if ((GLB.RECALL.trim()).equals("")==false)
    {
        return;
    }

    if (GLB.RETURNPS == true)
    {
        IspecRefresh();
        return;
    }


    if ( GLB.MSGINDEX != 0 )
    {
       if (GLB.RESTORE == true)
       {
            FormToScreen(request);
       }
       return;
    }

    IspecRefresh();

}

public void beforeCycle () throws Exception  {}
public void afterCycle () throws Exception  {}

// Métodos Abstratos

public abstract void PreLogic()     throws Exception;
public abstract void PreScreen()    throws Exception;
public abstract void Logic()        throws Exception;
public abstract void Validate()     throws Exception;
public abstract void Update()       throws Exception;
public abstract void IspecRefresh() throws Exception;
public abstract void FormToScreen(HttpServletRequest pRequest) throws Exception;

public abstract void ValidatePhase()   throws Exception;
public abstract void UpdatePhase ()    throws Exception;
public abstract void InitialSettings() throws Exception;


public abstract void updGlbWork()  throws Exception;
//Fim comandos exclusivos


private void setXseedSID() {

	if (request.getParameter("XSEEDSID")==null){
		XseedSID = "";
	}
	else
	{
		XseedSID = request.getParameter("XSEEDSID");
	}
}

private String nameFileErr () throws Exception { 
	String filename = "";
	SimpleDateFormat formatter;
	formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
	filename = GLB.AUDITDIR.trim() + File.separatorChar + ISPEC.trim() + "(" + formatter.format(new java.util.Date()) + ").ERR";
	if (FileExists(filename) == true)
	{
		for(int i=0;i<999;i++)
		{
			filename = GLB.AUDITDIR.trim() + File.separatorChar + ISPEC.trim() + "(" + formatter.format(new java.util.Date()) + i + ").ERR";
			if (FileExists(filename) == false)
			{
				break;
			}
     	}
	}
	return filename;
}

//AuditERR
public void AuditErr(String pRoutineErrorName, String pRoutineErrorMsg)
{
	Date dateToday = new Date();
	try
	{
		
		String filename =  nameFileErr();
		if (filename.equals("")==true) return;
		PrintStream audit = new PrintStream(new FileOutputStream(filename,true));
		audit.println("ERR:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Routine: "+ pRoutineErrorName.trim());  	   	
		audit.println("ERR:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Isp: "+ ISPEC + " Msg: "+ pRoutineErrorMsg.trim());  	   	
		audit.close();  	
	}
	catch (Exception e)
	{}
		
}
public void cleanSLDSession() throws Exception {
	
	String attrName="";
	Enumeration<?> attrNames=session.getAttributeNames();
	while(attrNames.hasMoreElements()){  
	    attrName = (String)attrNames.nextElement();       
        if (attrName.startsWith("XseedSendListDynamic_")) {
        	 session.removeAttribute(attrName);        
        }
	}	
}

} // End Class XseedIspec