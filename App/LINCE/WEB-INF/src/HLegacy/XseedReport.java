package HLegacy;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.*;
import java.util.*;
import java.awt.print.*;

import javax.mail.*;
import javax.activation.*;
import javax.mail.internet.*;

import java.math.*;

public class XseedReport
{
    public double ACTMTH = 0;
    final public String HIGH_VALUE  = "" + (char)255;
    public String INPUT_DATE = "       ";
    public String ISPEC = "";
    final public String LOW_VALUE  = "" + (char)0;
    final public int SHADOW = 0;
    final public int SEQ = 1;
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

    public String routineErrorMsg="";
    public String routineErrorName="";
    public PrintWriter out;

	//Variaveis usadas no comando ACCEPT
    public boolean acceptFileOpen = false;
    public BufferedReader  acceptFile;

    public XseedGLB GLB = new XseedGLB();


	protected static DecimalFormat df;
	static
	{
    	df = new DecimalFormat("########");
	}
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
protected SimpleDateFormat sdf_MMddyyyy;
protected SimpleDateFormat sdf_MMM_dd_yy;
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
	df = new DecimalFormat("########");
	auxDate_BASE = Calendar.getInstance();
	auxDate_Current = Calendar.getInstance();
}

////////// Métodos iguais

//AuditCrash
public void AuditCrash(String pMsg)
    throws Exception
 {
 	PrintStream audit = new PrintStream(new FileOutputStream(GLB.SYSTEMDIR  + File.separatorChar + "Alert.Log",true));
    audit.println(pMsg);
    audit.close();
 }

/*formatDate -antigo*/
public String formatDate(String DCDate, String dcFormat, String dcFormatResult)
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

        SimpleDateFormat sdf = new SimpleDateFormat();

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
		            sdf.applyPattern("dd/MM/yyyy");
		            dt = sdf.parse("01/01/" + String.valueOf(GLB.BASE));
		            cal.setTime(dt);
		                    cal.set(cal.DATE, cal.get(cal.DATE) + Integer.parseInt(DCDate));
		            dt = cal.getTime();
		        }

		        if (dcFormat.equals("DD-MM-YY")==true)
		        {
		            sdf.applyPattern("dd/MM/yyyy");
		            dt = sdf.parse(DCDate.substring(0,6) + pCentury + DCDate.substring(6,8));
		        }

		        if (dcFormat.equals("DD-MMM-YY")==true)
		        {
		            sdf.applyPattern("dd/MMM/yyyy");
		            dt = sdf.parse(DCDate.substring(0,7) + pCentury + DCDate.substring(7,9));
		        }

		        if (dcFormat.equals("DDMMYY")==true)
		        {
		            sdf.applyPattern("ddMMyyyy");
		            dt = sdf.parse(DCDate.substring(0,4) + pCentury + DCDate.substring(4,6));
		        }

		        if (dcFormat.equals("DDMMMYY")==true)
		        {
		            sdf.applyPattern("ddMMMyyyy");
		            dt = sdf.parse(DCDate.substring(0,5) + pCentury + DCDate.substring(5,7));
		        }

		        if (dcFormat.equals("MM-DD-YY")==true)
		        {
		            sdf.applyPattern("MM/dd/yyyy");
		            dt = sdf.parse(DCDate.substring(0,6) + pCentury + DCDate.substring(6,8));
		        }

		        if (dcFormat.equals("MMM-DD-YY")==true)
		        {
		            sdf.applyPattern("MMM/dd/yyyy");
		            dt = sdf.parse(DCDate.substring(0,7) + pCentury + DCDate.substring(7,9));
		        }

		        if (dcFormat.equals("MMDDYY")==true)
		        {
		            sdf.applyPattern("MMddyyyy");
		            dt = sdf.parse(DCDate.substring(0,4) + pCentury + DCDate.substring(4,6));
		        }

		        if (dcFormat.equals("MMMDDYY")==true)
		        {
		            sdf.applyPattern("MMMddyyyy");
		            dt = sdf.parse(DCDate.substring(0,5) + pCentury + DCDate.substring(5,7));
		        }

		        if (dcFormat.equals("UK-ALPHA")==true)
		        {
		            sdf.applyPattern("dd MMMM yyyy");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("US-ALPHA")==true)
		        {
		            sdf.applyPattern("MMMM dd yyyy");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("IN-ALPHA")==true)
		        {
		            sdf.applyPattern("yyyy MMMM dd");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("YYDDD")==true)
		        {
		            sdf.applyPattern("yyyyDDD");
		            dt = sdf.parse(pCentury + DCDate);
		        }

		        if (dcFormat.equals("YYMMDD")==true)
		        {
		            sdf.applyPattern("yyyyMMdd");
		            dt = sdf.parse(pCentury + DCDate);
		        }

		        if (dcFormat.equals("YYMMMDD")==true)
		        {
		            sdf.applyPattern("yyyyMMMdd");
		            dt = sdf.parse(pCentury + DCDate);
		        }

		        if (dcFormat.equals("YY-MM-DD")==true)
		        {
		            sdf.applyPattern("yyyy/MM/dd");
		            dt = sdf.parse(pCentury + DCDate);
		        }

		        if (dcFormat.equals("YY-MMM-DD")==true)
		        {
		            sdf.applyPattern("yyyy/MMM/dd");
		            dt = sdf.parse(pCentury + DCDate);
		        }

		        if (dcFormat.equals("DD-MM-CCYY")==true)
		        {
		            sdf.applyPattern("dd/MM/yyyy");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("DD-MMM-CCYY")==true)
		        {
		            sdf.applyPattern("dd MMM yyyy");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("DDMMCCYY")==true)
		        {
		            sdf.applyPattern("ddMMyyyy");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("DDMMMCCYY")==true)
		        {
		            sdf.applyPattern("ddMMMyyyy");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("MM-DD-CCYY")==true)
		        {
		            sdf.applyPattern("MM/dd/yyyy");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("MMDDCCYY")==true)
		        {
		            sdf.applyPattern("MMddyyyy");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("MMM-DD-CCYY")==true)
		        {
		            sdf.applyPattern("MMM dd yyyy");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("MMMDDCCYY")==true)
		        {
		            sdf.applyPattern("MMMddyyyy");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("CCYYDDD")==true)
		        {
		            sdf.applyPattern("yyyyDDD");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("CCYYMMDD")==true)
		        {
		            sdf.applyPattern("yyyyMMdd");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("CCYYMMMDD")==true)
		        {
		            sdf.applyPattern("yyyyMMMdd");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("CCYY-MM-DD")==true)
		        {
		            sdf.applyPattern("yyyy/MM/dd");
		            dt = sdf.parse(DCDate);
		        }

		        if (dcFormat.equals("CCYY-MMM-DD")==true)
		        {
		            sdf.applyPattern("yyyy MMM dd");
		            dt = sdf.parse(DCDate);
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
        if (dcFormatResult.equals("DD-MM-YY")==true)
        {   sdf.applyPattern("dd/MM/yy");
        }
        else if (dcFormatResult.equals("DD-MM-CCYY")==true)
        {   sdf.applyPattern("dd/MM/yyyy");
        }
        else if (dcFormatResult.equals("DD-MMM-YY")==true)
        {   sdf.applyPattern("dd MMM yy");
        }
        else if (dcFormatResult.equals("DD-MMM-CCYY")==true)
        {   sdf.applyPattern("dd MMM yyyy");
        }
        else if (dcFormatResult.equals("DDMMMYY")==true)
        {   sdf.applyPattern("ddMMMyy");
        }
        else if (dcFormatResult.equals("DDMMMCCYY")==true)
        {   sdf.applyPattern("ddMMMyyyy");
        }
        else if (dcFormatResult.equals("DDMMYY")==true)
        {   sdf.applyPattern("ddMMyy");
        }
        else if (dcFormatResult.equals("DDMMCCYY")==true)
        {    sdf.applyPattern("ddMMyyyy");
        }
        else if (dcFormatResult.equals("MM-DD-YY")==true)
        {   sdf.applyPattern("MM/dd/yy");
        }
        else if (dcFormatResult.equals("MM-DD-CCYY")==true)
        {    sdf.applyPattern("MM/dd/yyyy");
        }
        else if (dcFormatResult.equals("MMDDYY")==true)
        {    sdf.applyPattern("MMddyy");
        }
        else if (dcFormatResult.equals("MMDDCCYY")==true)
        {    sdf.applyPattern("MMddyyyy");
        }
        else if (dcFormatResult.equals("MMM-DD-YY")==true)
        {   sdf.applyPattern("MMM dd yy");
        }
        else if (dcFormatResult.equals("MMM-DD-CCYY")==true)
        {   sdf.applyPattern("MMM dd yyyy");
        }
        else if (dcFormatResult.equals("MMMDDYY")==true)
        {   sdf.applyPattern("MMMddyy");
        }
        else if (dcFormatResult.equals("MMMDDCCYY")==true)
        {    sdf.applyPattern("MMMddyyyy");
        }
        else if (dcFormatResult.equals("TODAY")==true)
        {   sdf.applyPattern("EEEEEEEEEEEEEEEEEEEEEEE");
        }
        else if (dcFormatResult.equals("UK-ALPHA") == true)
        {   sdf.applyPattern("dd MMMM yyyy");
        }
        else if (dcFormatResult.equals("US-ALPHA") == true)
        {   sdf.applyPattern("MMMM dd yyyy");
        }
        else if (dcFormatResult.equals("IN-ALPHA") == true)
        {   sdf.applyPattern("yyyy MMMM dd");
        }
        else if (dcFormatResult.equals("YYDDD")==true)
        {   sdf.applyPattern("yyDDD");
        }
        else if (dcFormatResult.equals("CCYYDDD")==true)
        {   sdf.applyPattern("yyyyDDD");
        }
        else if (dcFormatResult.equals("YYMMDD")==true)
        {   sdf.applyPattern("yyMMdd");
        }
        else if (dcFormatResult.equals("CCYYMMDD")==true)
        {   sdf.applyPattern("yyyyMMdd");
        }
        else if (dcFormatResult.equals("YY-MM-DD")==true)
        {   sdf.applyPattern("yy/MM/dd");
        }
        else if (dcFormatResult.equals("CCYY-MM-DD")==true)
        {   sdf.applyPattern("yyyy/MM/dd");
        }
        else if (dcFormatResult.equals("YYMMMDD")==true)
        {   sdf.applyPattern("yyMMMdd");
        }
        else if (dcFormatResult.equals("CCYYMMMDD")==true)
        {   sdf.applyPattern("yyyyMMMdd");
        }
        else if (dcFormatResult.equals("YY-MMM-DD")==true)
        {   sdf.applyPattern("yy MMM dd");
        }
        else if (dcFormatResult.equals("CCYY-MMM-DD")==true)
        {   sdf.applyPattern("yyyy MMM dd");
        }

        if (dcFormatResult.equals("DAYNUM")==true)
        {
            sdf.applyPattern("yyyy");
            double result= ( (Double.valueOf(sdf.format(dt)).doubleValue() - GLB.BASE) * 365);
            for (double i = GLB.BASE; i < Double.valueOf(sdf.format(dt)).doubleValue(); i++)
            {    if ((i % 400)==0)
                 {
                    result++;
                 }
                 else if((i % 4)==0)
                 {
                    if (!((i % 100)==0))
                    {
                        result++;
                    }
                 }
            }

            sdf.applyPattern("DDD");
            result = result + Double.valueOf(sdf.format(dt)).doubleValue() - 1;
            dcResult=cStr(result);
        }
        else
        {
            dcResult = sdf.format(dt);
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
  		try{

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

    	if(flagSettedSDF)
	    {
			setDateFormats();
			setDateVariables();
	    }

    	Calendar cal = Calendar.getInstance();

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
	   calendar.add(Calendar.DATE, -1);
	   pDt = calendar.getTime();
	   GLB.DC_LASTDAYMONTH= Double.parseDouble(sdf_ddMMyyyy.format(pDt).substring(0,2)); 
	   GLB.DC_CCYYMMLD = Double.parseDouble(sdf_yyyyMMdd.format(pDt));
}
	

//DCToAlpha
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
    {   GLB.STATUS = "*****";
    	ShowErrorMsg (e, "DCToAlpha");
    	return("");
    }
}

//DCToDate
public String DCToDate()
    throws Exception
{
	try
    {   GLB.STATUS = "";
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
    {   GLB.STATUS = "";
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



//ReplaceQuotes
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

//RollBackTransaction
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

//SendEMail
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

//ShowDataMsg
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
    throw e;
}

//ShowErrorMsg
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
    throw e;
}



//ShowErrorMsg
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
         ShowDataMsg(e,routineName, sql );
    }
}

/* lockTimeoutMsg */
public void lockTimeoutMsg()
    throws Exception
{
    abort("TRANSACTION FAILED: Database Lock Timeout");
}


/* cBigDec --> BigDecimal */
public BigDecimal cBigDec (String pText, char pDecimalChar)
{
	try
    {	int position = -1;
        pText = pText.trim();
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

//cDbl
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

//checkDay
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

//checkDayAndMonth
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

//checkYear
private String checkYear(int pYear)
	throws Exception
{
	try
	{	if (pYear < GLB.BASE)
		{	return("*****");
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

//checkDate
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

//cStr
public String cStr (double pNumber)
    throws Exception
{
	try
	{	String str = new BigDecimal(new Double(pNumber).toString()).toPlainString();
		if (str.substring (str.length()-2).equals(".0")==true)
		{	str = str.substring(0,str.length()-2);
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

//currentDate
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
        {    if ((i % 400)==0)
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

//fillStringBuffer
public void fillStringBuffer (StringBuffer pStrBuffer, int pLength)
throws Exception
{
    try
    {
        char init[];
        if (pStrBuffer.length() < pLength)
        {
            init = new char [pLength - pStrBuffer.length()];
            pStrBuffer.append(init, 0,pLength - pStrBuffer.length());
        }
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "fillStringBuffer");
        return ;
    }
}

//fix double
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

//fix String
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

//format Date
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

//format double
public String format(double pNumber, String pMask)
    throws Exception
{
	try
	{
	    String strNumber="0";
    	int numberLength;
    	int numberDecimals;
    	int count=0;
    	int index;
    	DecimalFormat myFormatter;

    	if ((pMask.indexOf("9") != -1) ||
        	(pMask.indexOf("#") != -1) ||
            (pMask.indexOf("Z") != -1) ||
        	(pMask.indexOf("*") != -1))  //formato de um numero
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

//rTrim
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

//splitElements
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

// subItem
public double subItem(double pItem,StringBuffer pGroup,int pInit, int pEnd)
  throws Exception
{   try
	{   StringBuffer strBuffer = new StringBuffer(rTrim(pGroup.substring(pInit, pEnd)));
        for (int i = strBuffer.length(); i<(pEnd - pInit); i++)
        {
        	strBuffer = strBuffer.append("0");
        }
        return(cDbl(rTrim(strBuffer.substring(0, pEnd-pInit))));
	}
	catch (Exception e)
    {   ShowErrorMsg (e,"subItem");
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

//subItem
public String subItem(String pItem,StringBuffer pGroup,int pInit, int pEnd)
  throws Exception
{   try
	{
        return(rTrim(pGroup.substring(pInit, pEnd)));
	}
	catch (Exception e)
    {   ShowErrorMsg (e,"subItem");
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
//  * validateDate */
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



//Fim Métodos iguais

////////// Métodos Diferentes

//AuditBOP
public void AuditBOP()
   throws Exception
{
	GLB.AUDITJOB = true; // Em report a arquivo de audit sempre sera criado.

	GLB.AUDITACT = newName("AUDIT");

	Date dateToday = new Date();

    File exeFile = new File(GLB.SYSTEMDIR + File.separatorChar +"WEB-INF" + File.separatorChar + "classes" + File.separatorChar + GLB.REPNAME + ".class");
    Date genDate = new Date() ;
    genDate.setTime(exeFile.lastModified());

	try
	{
    	PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
		audit.println("================================================================================");
    	audit.println("BOP:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: Begin Report (Generate " + format(genDate,"yyyy/MM/dd") + ")");
    	audit.close();
	}
	catch (Exception e)
	{
		AuditCrash("BOP:" + format(dateToday, "yyyy/MM/DD HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: Begin Report (Generate " + format(genDate,"yyyy/MM/dd") + ")");
	}
}

//AuditBOT
public void AuditBOT()
   throws Exception
{
 	Date dateToday = new Date();

	try
	{
		PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
    	audit.println("BOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: Begin Transaction (TraNo: " + TRANNO + ")");
    	audit.close();
    	GLB.STARTTIME = dateToday.getTime();
	}
	catch (Exception e)
	{
		AuditCrash("BOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: Begin Transaction (TraNo: " + TRANNO  + ")");
	}
}

//AuditEOP
public void AuditEOP()
   throws Exception
{
    Date dateToday = new Date();;

	try
	{
    	PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
    	audit.println("EOP:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: End Report(GLB.TASK="+(int)GLB.TASK+")");
    	audit.close();
	}
	catch (Exception e)
	{
		AuditCrash("EOP:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: End Report");
	}
}

//AuditERR
public void AuditERR(String pMsg)
  throws Exception
{
  	Date dateToday = new Date();
    long elapsedTime = dateToday.getTime() - GLB.STARTTIME;

	double hours = (elapsedTime / 1000) / 3600;
	double minutes = ((elapsedTime / 1000) % 3600) / 60;
	double seconds = ((elapsedTime / 1000) % 3600) % 60;
	double miliseconds = elapsedTime % 1000;
	String timeString = format(hours,"99") + ":" + format(minutes,"99") +":"+ format (seconds,"99") + ":" + format (miliseconds,"999");
	String errLog="";
	String msg="";

	try
	{
		PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
    	audit.println("ERR:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: "+ pMsg.trim());
    	audit.println("EOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: Aborted Transaction(Elapsed: " + timeString.trim() + "hs");
	   	audit.println("EOP:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: Aborted Report (GLB.TASK="+(int)GLB.TASK+")");
    	audit.close();

    	// Copia .ACT para .ERR
    	if (GLB.AUDITACT.indexOf(".") != -1)
	    {
	    	errLog = (GLB.AUDITACT.substring(0, GLB.AUDITACT.lastIndexOf("."))).trim() + ".ERR";
	    }

	    msg = "FileCopy " + GLB.AUDITACT + ", " + errLog;
	    FileCopy (GLB.AUDITACT, errLog);


	    msg = "Kill " + GLB.AUDITACT;
	    File auditFile = new File(GLB.AUDITACT);
	    auditFile.delete();


	}
	catch (Exception e)
	{
		if (msg.equals("") == false)
		{
			AuditCrash("ERR:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: "+ msg.trim());
		}
    	AuditCrash("ERR:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: "+ pMsg.trim());
    	AuditCrash("EOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: Aborted Transaction(Elapsed: " + timeString.trim() + "hs");
	   	AuditCrash("EOP:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: Aborted Report");
	}
}

//CommitTransaction
public void CommitTransaction()
    throws Exception
{
    try
    {
        GLB.CONNECTION.commit();
    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"CommitTransaction");
    }
}


//Fim Métodos diferentes

////////// Metodos exclusivos

//AuditEOJ
public void AuditEOJ()
   throws Exception
{
 	Date dateToday = new Date();

    String msg="";
	try
	{	String auditLog="";

	    if (GLB.AUDITACT.indexOf(".") != -1)
	    {
	    	auditLog = (GLB.AUDITACT.substring(0, GLB.AUDITACT.lastIndexOf("."))).trim() + ".LOG";
	    }

	    msg = "FileCopy " + GLB.AUDITACT + ", " + auditLog;
	    FileCopy (GLB.AUDITACT, auditLog);


	    msg = "Kill " + GLB.AUDITACT;
	    File auditFile = new File(GLB.AUDITACT);
	    auditFile.delete();

	}
	catch (Exception e)
	{
		AuditCrash("EOJ:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: " + msg.trim());
	}
}

//AuditEOT
public void AuditEOT ()
   throws Exception
{
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

		audit.println("EOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: End Transaction (Elapsed : " + timeString.trim() + "hs)");
    	audit.close();
	}
	catch (Exception e)
	{
		AuditCrash("EOT:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: End Transaction (Elapsed : " + timeString.trim() + "hs)");
	}
}

//AuditEVE
public void AuditEVE(String pHeader, String pMsg)
  throws Exception
{

    Date dateToday = new Date();

	try
	{
		PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
    	audit.println(pHeader.trim() + ": " + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: " + pMsg.trim());
    	audit.close();
	}
	catch (Exception e)
	{
    	AuditCrash(pHeader.trim() + ": " + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: " + pMsg.trim());
	}
}


//AuditMSG
public void AuditMSG(String pMsg)
  throws Exception
{

    Date dateToday = new Date();

	try
	{
		PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
    	audit.println("MSG:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: " + pMsg.trim());
    	audit.close();
	}
	catch (Exception e)
	{
    	AuditCrash("MSG:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: "+ pMsg.trim());
	}
}

//CriticalPoint
public void CriticalPoint(String Action)
{
}

//FileCopy
public void FileCopy(String inFileName, String outFileName)
    throws Exception, IOException
{
    try
    {
        String line;
        File outFile;

        outFile = new File(outFileName);
        if (outFile.exists() == true)
        {
           outFile.delete();
        }
        outFile.createNewFile();

        BufferedReader in = new BufferedReader(new FileReader(inFileName));
        PrintStream out = new PrintStream(new FileOutputStream(outFileName));

        while((line = in.readLine()) != null)
        {
            out.println(line);
        }
        in.close();
        out.close();
    }
    catch(Exception e)
    {
        ShowErrorMsg (e,"FileCopy");
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

//XSDLINEP
public String XSDLINEP(String STBACKUP, String BKPPREFIX, String REPPREFIX, String WTitle, String XTITLE, String LSN)
    throws Exception
{
    try
    {
        File reportFile;
        String OUTPUTNAME;
        String INPUTNAME;
        int Cont=0;
        NumberFormat nf = NumberFormat.getInstance();

        INPUTNAME = WTitle;

        nf.setGroupingUsed(false);
        nf.setMinimumIntegerDigits(4);

        do
        {
            if (XTITLE.trim().equals(GLB.SPACES))
            {
               OUTPUTNAME = BKPPREFIX.trim() + REPPREFIX.trim() + "." + nf.format(Cont);
            }
            else
            {	if (GLB.WFL == true)
            	{	OUTPUTNAME = XTITLE.trim() + "." + nf.format(Cont);
            	}
            	else
            	{	OUTPUTNAME = STBACKUP.trim() + File.separatorChar + XTITLE.trim() + "." + nf.format(Cont);
            	}
            }
            Cont++;
            reportFile = new File(OUTPUTNAME);
        } while(reportFile.exists());

        FileCopy(INPUTNAME, OUTPUTNAME);

        return (OUTPUTNAME);
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "XSDLINEP");
        return ("");
	}
}

//createUserTempDir
public void createUserTempDir()
    throws Exception
{
    try
    {
        if (GLB.INITSTN.trim().equals("") == true)
        {
           GLB.TEMPPREFIX = GLB.TEMPDIR.trim() + File.separatorChar + "DEFAULT";
        }
        else
        {
           GLB.TEMPPREFIX = GLB.TEMPDIR.trim() + File.separatorChar + GLB.INITSTN.trim();
        }

        File fileTemp = new File(GLB.TEMPPREFIX);

        if (fileTemp.isDirectory() == false)
        {
	       fileTemp.mkdir();
	    }
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "createUserTempDir");
    }
}

//initBPArray
public void initBPArray()
    throws Exception
{
    try
    {
        int i,j;
        for (i = 1; i <= GLB.BP.length; i++)
        {   for (j = 1; j <= GLB.BP[0].length; j++)
            {
                GLB.BP[i-1][j-1] = "";
            }
        }
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "initBPArray");
    }
}

//newBackup
public String newBackup (String pTitle, String pShadow)
    throws Exception
{
    try
    {
        File reportFile;
        String backupName;
        String shadowSuffix;
        int Cont=-1;
        NumberFormat nf = NumberFormat.getInstance();
        SimpleDateFormat formatter;

        formatter = new SimpleDateFormat("yyyy-MM-dd HHmmss");
    	GLB.NOW = formatter.format(new java.util.Date());

        if (pShadow.equals("") == true)
        {
           shadowSuffix = "";
        }
        else
        {
           shadowSuffix = "_" + pShadow;
        }

        /* Tratamento do nome dependendo de GLB.BDNAME estiver preenchido */
        if (GLB.BDNAME.equals("") == true)
        {
            if (pTitle.equals("") == true)
            {
               backupName = GLB.BACKUPDIR.trim() + File.separatorChar + GLB.INITSTN.trim() + "(" + GLB.NOW.trim() + ")" + GLB.REPNAME.trim() + shadowSuffix;
            }
            else
            {
                if (GLB.WFL == true)
            	{
                   backupName = pTitle.trim();
            	}
            	else
            	{
                   backupName = GLB.BACKUPDIR.trim() + File.separatorChar + pTitle.trim();
           	}
            }
        }
        else
        {   if (pTitle.equals("") == true)
            {
                backupName = GLB.BACKUPDIR.trim() + File.separatorChar + GLB.BDNAME + "_PRINT" + pShadow + "_"+ GLB.BDSUFFIX;
            }
            else
            {
                backupName = GLB.BACKUPDIR.trim() + File.separatorChar + GLB.BDNAME + "_" + pTitle + "_"+ GLB.BDSUFFIX;
            }
        }

        /* Anexar .0000 e verificar se já existe o arquivo, se existir soma 1 ao contador */
        nf.setGroupingUsed(false);
        nf.setMinimumIntegerDigits(4);

        do
        {
            Cont++;
            reportFile = new File(backupName + "." + nf.format(Cont));

        } while(reportFile.exists());

 		backupName = backupName + "." + nf.format(Cont);
        return (backupName);
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "newBackup");
        return ("");
    }
}

//newName
public String newName(String pFileType)
    throws Exception
{
    try
    {
		String auditFile;

		if (pFileType.equals("AUDIT") == true)
	    {	if (GLB.AUDITACT.trim().equals("") == true)
		    {	SimpleDateFormat formatter;
    	    	formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
                auditFile = GLB.BATCHDIR.trim() + File.separatorChar + GLB.INITSTN.trim() + "(" + formatter.format(new java.util.Date()) + ")" + GLB.REPNAME.trim() + ".ACT";
                if (FileExists(auditFile) == true)
                {
                   for(int i=0;i<999;i++)
                   {
                      auditFile = GLB.BATCHDIR.trim() + File.separatorChar + GLB.INITSTN.trim() + "(" + formatter.format(new java.util.Date()) + i + ")" + GLB.REPNAME.trim() + ".ACT";
                      if (FileExists(auditFile) == false)
                      {
                         break;
                      }
                   }
                }
    	 	}
    	 	else
    	 	{	int positionAct = GLB.AUDITACT.indexOf(".ACT");
    	 		if (positionAct==-1)
    	 		{   auditFile = GLB.AUDITACT + ".ACT";
    	 		}
    	 		else
    	 		{   auditFile = GLB.AUDITACT;
    	 		}
    	 	}
    	    return(auditFile);
	    }
	    else
	    {
	        return("");
	    }
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "newName");
        return("");
    }
}

//newName
public String newName(String pFileType, String pExtension)
    throws Exception
{
    try
    {
         String fileName;
         SimpleDateFormat formatter;

         if ((pFileType.equals("BACKUP") == true) ||
         	 (pFileType.equals("EXTRACT") == true))
	     {
    	    formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
    	    GLB.NOW = formatter.format(new java.util.Date());

            fileName = GLB.TEMPDIR.trim() + File.separatorChar + GLB.INITSTN.trim() + File.separatorChar + GLB.INITSTN.trim() + "("+ GLB.NOW.trim() +")" + GLB.REPNAME.trim() + "." + pExtension;
            if (FileExists(fileName) == true)
                {
                   for(int i=0;i<999;i++)
                   {
                      fileName = GLB.TEMPDIR.trim() + File.separatorChar + GLB.INITSTN.trim() + File.separatorChar + GLB.INITSTN.trim() + "("+ GLB.NOW.trim() + i +")" + GLB.REPNAME.trim() + "." + pExtension;
                      if (FileExists(fileName) == false)
                      {
                         break;
                      }
                   }
                }

    	    return(fileName);
	     }
         else
         {
	        return("");
	     }
    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "newName");
        return("");
    }
}

//xsdSpool
public void xsdSpool(String spoolFile, double formDepth, String paperOrientation)
    throws Exception
{
	String linha;
    String buffer[];

    Book  book = new Book();
    Paper papr = new Paper();

    BufferedReader br = new BufferedReader(new FileReader(spoolFile));

    // Paper Dimensions: width, height
    papr.setSize(792, (formDepth * 10 + 144));
    // Printable Area Dimensions
    papr.setImageableArea(72,72,792 - 144,(formDepth * 10));

    PageFormat pagf = new PageFormat();

    pagf.setPaper(papr);

    //Page Orientation:Portrait ou Landscape
    if(paperOrientation.equals("LANDSCAPE"))
    {  pagf.setOrientation(PageFormat.LANDSCAPE);
    }

    buffer = new String[(int)formDepth];

   	int numPages = 0;
   	int j=0;
   	while( (linha = br.readLine())!= null)
   	{
   		if (linha.equals(".PA") == false)
   	    {   buffer[j] = linha;
   	    	j++;
   	    }
   	    else
   	    {   numPages++;
   	    	book.append(new XseedSpool(buffer), pagf);
   	    	buffer = new String[(int)formDepth];
   	    	j=0;
		}
	}

   	if ((linha == null) && (buffer[0] != null))
   	{    numPages++;
         book.append(new XseedSpool(buffer), pagf);
    }

    br.close();

	PrinterJob printerJob = PrinterJob.getPrinterJob();
    printerJob.setPageable(book);

    try
	{
		if(GLB.NUMCOPIES != 0)
		{
			for (int i=1;i>= GLB.NUMCOPIES;i++)
			{
				printerJob.print();
			}
		}
		else
		{
			printerJob.print();
		}
	}
	catch (PrinterException e)
	{
		ShowErrorMsg (e, "xsdSpool");
	}
}

//xseedSpool
public void xseedSpool(String pPrinter, String pBackupName)
    throws Exception
{
	try
	{
		Process proc;
		String execCmd;
	   	String programName;

		// Unix Printing
		if (File.separatorChar=='/')
      	{
        	programName = GLB.SYSTEMDIR + File.separatorChar +
                         "WEB-INF" + File.separatorChar +
                         "classes" + File.separatorChar +
                         "XseedSpool.sh";

        	// Prepare unix command line
        	String unixCmdLine [] = new String[4];

        	unixCmdLine[0] = "/bin/sh";
        	unixCmdLine[1] = programName;
        	unixCmdLine[2] = pPrinter;
        	unixCmdLine[3] = pBackupName;

	        // Execute XseedSpool.sh unix shell
    	    proc = Runtime.getRuntime().exec(unixCmdLine);

      	}
	   	else   // Windows Printing
	   	{
	   		programName= GLB.SYSTEMDIR + File.separatorChar +
	   		             "WEB-INF" + File.separatorChar +
	   		             "classes" + File.separatorChar +
	   		             "XseedSpool.exe";

            execCmd = programName + " " + pPrinter + "," + pBackupName + "," +
                      GLB.SYSTEMDIR + File.separatorChar + "WEB-INF" + File.separatorChar + "classes," + GLB.ORIENTATION;
	   		proc = Runtime.getRuntime().exec(execCmd);
	   	}

		// Show error messages
        InputStream is =proc.getErrorStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader in = new BufferedReader(isr);
        String errMsg  = "";
        String errLine = in.readLine();
        while (errLine!=null)
        {
           errMsg += errLine + "\n";
           errLine = in.readLine();
        }
        if (!errMsg.equals(""))
        {  message("XseedSpool Error",errMsg);
    	}
        in.close();
	}
	catch (Exception e)
	{
		ShowErrorMsg (e, "xseedSpool");
	}
}

public void xseedSpool(String pPrinter, String pBackupName, String pNumCopies, String pFormID)
    throws Exception
{
    try
    {
        Process proc;
        String execCmd;
        String programName;

        if(File.separatorChar == '/')
        {
            programName = GLB.SYSTEMDIR + File.separatorChar +
                          "WEB-INF" + File.separatorChar +
                          "classes" + File.separatorChar +
                          "XseedSpool.sh";

            if (pNumCopies.trim().equals("") == true)
            {   pNumCopies = "1";
            }
            else if (cDbl(pNumCopies) == 0)
            {   pNumCopies = "1";
            }
            // Prepare unix command line
            String unixCmdLine [] = new String[6];
            unixCmdLine[0] = "/bin/sh";
            unixCmdLine[1] = programName;
            unixCmdLine[2] = pPrinter;
            unixCmdLine[3] = pBackupName;
            unixCmdLine[4] = pNumCopies;
            unixCmdLine[5] = pFormID;

            // Execute XseedSpool.sh unix shell
            proc = Runtime.getRuntime().exec(unixCmdLine);

        }
        else   // Windows Printing
        {
            programName= GLB.SYSTEMDIR + File.separatorChar +
                         "WEB-INF" + File.separatorChar +
                         "classes" + File.separatorChar +
                         "XseedSpool.exe";

            execCmd = programName + " " + pPrinter + "," + pBackupName + "," +
                      GLB.SYSTEMDIR + File.separatorChar + "WEB-INF" + File.separatorChar + "classes," + GLB.ORIENTATION;
            proc = Runtime.getRuntime().exec(execCmd);
        }

        // Show error messages
        InputStream is =proc.getErrorStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader in = new BufferedReader(isr);
        String errMsg  = "";
        String errLine = in.readLine();
        while (errLine!=null)
        {
           errMsg += errLine + "\n";
           errLine = in.readLine();
        }
        if (!errMsg.equals(""))
        {  message("XseedSpool Error",errMsg);
        }
        in.close();
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "xseedSpool");
    }
}

public void xseedSpoolOC(String pPrinter, String pBackupName, String pNumCopies, String pFormID)
    throws Exception
{
    try
    {
        Process proc;
        String execCmd;
        String programName;

        if(File.separatorChar == '/') // Unix/Linux Printing
        {
            programName = GLB.SYSTEMDIR + File.separatorChar +
                          "WEB-INF" + File.separatorChar +
                          "classes" + File.separatorChar +
                          "XseedSpoolOC.sh";

            if (pNumCopies.trim().equals("") == true)
            {   pNumCopies = "1";
            }
            else if (cDbl(pNumCopies) == 0)
            {   pNumCopies = "1";
            }
            // Prepare unix command line
            String unixCmdLine [] = new String[7];
            unixCmdLine[0] = "/bin/sh";
            unixCmdLine[1] = programName;
            unixCmdLine[2] = pPrinter;
            unixCmdLine[3] = pBackupName;
            unixCmdLine[4] = pNumCopies;
            unixCmdLine[5] = pFormID;
            unixCmdLine[6] = GLB.SYSTEMDIR + File.separatorChar + "WEB-INF" + File.separatorChar + "classes" + File.separatorChar;


            // Execute XseedSpoolOC.sh unix shell
            proc = Runtime.getRuntime().exec(unixCmdLine);

        }
        else   // Windows Printing
        {
            Process procBat;
            String classPath;
            //Execute XseedOutputControl
            programName = GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "classes" + File.separatorChar +
                        "XseedOutputControl.bat";

            classPath  =  GLB.SYSTEMDIR + File.separatorChar +
                        "WEB-INF" + File.separatorChar +
                        "classes";

            classPath  =  rTrim(classPath);

            proc = Runtime.getRuntime().exec(programName + " " + classPath + " XseedOutputControl " + pPrinter + " " + pBackupName);

            //Execute XseedSpool
            programName= GLB.SYSTEMDIR + File.separatorChar +
                         "WEB-INF" + File.separatorChar +
                         "classes" + File.separatorChar +
                         "XseedSpool.exe";

            execCmd = programName + " " + pPrinter + "," + pBackupName + ".tmp";
            proc = Runtime.getRuntime().exec(execCmd);

            //Remove backup.tmp
            File fileTmp  = new File(pBackupName + ".tmp");
            if (fileTmp.exists() == true)
            {
                fileTmp.delete();
            }
        }

        // Show error messages
        InputStream is =proc.getErrorStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader in = new BufferedReader(isr);
        String errMsg  = "";
        String errLine = in.readLine();
        while (errLine!=null)
        {
           errMsg += errLine + "\n";
           errLine = in.readLine();
        }
        if (!errMsg.equals(""))
        {  message("XseedSpoolOC Error",errMsg);
        }
        in.close();
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "xseedSpoolOC");
    }
}

//Fim Metodos exclusivos


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




///////////////////
// C O M A N D O S
//////////////////

////////// Comandos iguais

//ACCESS.EXT CONNECT
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
            Class.forName("com.ibm.db2.jcc.DB2Driver");
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

//ACCESS.EXT DISCONNECT
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


///ACCESS.EXT EXECUTE
public ResultSet accessextExecute(Connection pConnect, Statement pStatement,String pSql)
    throws Exception
{
    try
    {   pSql = pSql.trim();
        if (pSql.substring(0,pSql.indexOf(" ")).trim().toUpperCase().equals("SELECT") == true)
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

//ACCESS.EXT MOVEFIRST
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

//ACCESSEXT.MOVELAST
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

//ACCESSSEXT MOVENEXT
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

//ACCESSEXT MOVEPREVIOUS
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


//ATTACH
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

//ATTACH&SPACES
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

//CALL
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

//COMPUTE
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

//CONTRA
public double contra (double pNumber)
  throws Exception
{
	try
	{
		return(pNumber * (-1));
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
	{  	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
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
	{  	BigDecimal bigDec1 = pNumber1;
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
	{  	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
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
	{  	BigDecimal bigDec1 = pNumber1;
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
	{  	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
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
	{  	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
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
	{  	BigDecimal bigDec1 = pNumber1;
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
	{  	BigDecimal bigDec1 = pNumber1;
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
	{  	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
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
	{  	BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
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
	{  	BigDecimal bigDec1 = pNumber1;
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
	{  	BigDecimal bigDec1 = pNumber1;
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
    {
        BigDecimal bigDec1 = new BigDecimal((new Double(pNumber1)).toString());
        BigDecimal bigDec2 = new BigDecimal((new Double(pNumber2)).toString());
        return(bigDec2.divide(bigDec1,18,1));
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "divide");
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
    {   BigDecimal bigDivisor = new BigDecimal((new Double(pNumber1)).toString());
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


//GET.LENGTH
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
    {   BigDecimal bigDec = new BigDecimal((new Double(pSource)).toString());
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
    {   BigDecimal bigDec = pSource;
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

//Move Complexo - retorno String
public  String moveComplex(String pSource, int pPosition1, int pLength, String pDest, int pPosition2, int pDestLength)
    throws Exception
{
	try
	{   if (pPosition1 > pSource.length())
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

//Move Complexo - Retorno double
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


//MOVE.DATE
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

//MOVE.TIME
public double moveTime()
    throws Exception
{
	try
	{
        String resultTime;
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
        if ( pDataField == null)
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


//SQL.MOVEFIRST
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

//SQL.MOVELAST
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

//SQL.MOVENEXT
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

//SQL.MOVEPREVIOUS
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

//START
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

//START.SYNC
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

//TRACE
public void trace(String header, String trailer)
    throws Exception
{
	Date dateToday = new Date();
    try
    {
        PrintStream auditTrace;
        if (GLB.TRACEDIR.equals("") == true)
        {
            auditTrace = new PrintStream(new FileOutputStream(GLB.AUDITDIR + File.separatorChar + GLB.REPNAME + GLB.TRACESUFFIX,true));
        }
        else
        {
            auditTrace = new PrintStream(new FileOutputStream(GLB.TRACEDIR + File.separatorChar + GLB.REPNAME + GLB.TRACESUFFIX,true));
        }
        auditTrace.println("TRC:" + format(dateToday,"yyyy/MM/dd HH:mm:ss") + " msg: " + rTrim(header) + " " + rTrim(trailer) );
        auditTrace.close();
    }
    catch (Exception e)
    {
     ShowErrorMsg (e, "trace");
    }
}

// UPPERCASE
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

//WFL
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

//WFL.SYNC
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

//Fim comandos Iguais

////////// Comandos Diferentes

//ABORT
public void abort(String pMsg)
    throws Exception
{
	try
    {	GLB.ABORT = true;
    	GLB.EXIT = true;
    	GLB.MYSTATUS = "U-DS";
    	if (GLB.AUDITJOB == true)
    	{
    		AuditMSG ("User Abort : " + pMsg);
    		if (GLB.TASK==51 || GLB.TASK==52 || GLB.TASK==53 || GLB.TASK==54)
    			System.out.println(" Abort : " + pMsg);
		}
		Exception e = new Exception();
		throw e;
	}
	catch (Exception e)
    {
    	ShowErrorMsg (e, "abort");
    }
}

//MESSAGE
public void message(String header, String trailer)
    throws Exception
{
	try
	{   if (GLB.CONSOLE == true)
	    {
           System.out.println(header + " " + trailer);
	    }
	    else
	    {   //comando Message suja a variavel GLB.ERROR para ***** somente em ispec
    	    AuditMSG(header + " " + trailer);
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

          classPath  =  rTrim(classPath);
          initSTN    =  rTrim(GLB.STN) ;
          pParam     =  rTrim(pParam)  ;
          appIniPath =  rTrim(GLB.SYSTEMDIR)  ;
          deviceName =  rTrim(GLB.DEVICE)  ;

          // Prepare unix command line
          String unixCmdLine [] = new String[8];

          unixCmdLine[0] = "/bin/sh";
          unixCmdLine[1] = programName;
          unixCmdLine[2] = classPath;
          unixCmdLine[3] = pReportName;
          unixCmdLine[4] = initSTN;
          unixCmdLine[5] = pParam;
          unixCmdLine[6] = appIniPath;
          unixCmdLine[7] = deviceName;

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

          classPath  =  rTrim(classPath);
          initSTN    =  rTrim(GLB.STN) ;
          pParam     =  rTrim(pParam)  ;
          appIniPath =  rTrim(GLB.SYSTEMDIR)  ;
          deviceName =  rTrim(GLB.DEVICE)  ;

          // Prepare windows command line
          String winCmdLine [] = new String[12];

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

          // Execute XseedRunAsync.exe on Windows
          proc = Runtime.getRuntime().exec(winCmdLine);
       }
    }
    catch (Exception e)
    {
        ShowErrorMsg (e, "run");
    }
}


//runAt
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

    	jobFile.open(GLB.BATCHDIR + File.separatorChar + GLB.INITSTN.trim() +  "(" + now.trim() + ")" + pReportName.trim() + ".AT(" + dateAux.trim() + ")" , "OUTPUT");
    	jobFile.write(pReportName.trim() + " Start parameters:");
    	jobFile.write("GLB_INITSTN = " + GLB.INITSTN);
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

//runRemote
public void runRemote(String pReportName, String pDevice,String pParam, String pRunQueueNumber)
    throws Exception
{
    try
    {
    	XseedFile jobFile = new XseedFile();
    	String wRunName= "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmssSSS");
    	String now = formatter.format(new java.util.Date());
        wRunName = GLB.BATCHDIR + File.separatorChar + GLB.INITSTN + "(" + now.trim() + ")" + pReportName.trim() + ".RUN" + pRunQueueNumber.trim();
        if (FileExists(wRunName) == true)
        {
            for(int i=0;i<999;i++)
            {
                wRunName = GLB.BATCHDIR + File.separatorChar + GLB.INITSTN + "(" + now.trim() + i +")" + pReportName.trim() + ".RUN" + pRunQueueNumber.trim();
                if (FileExists(wRunName) == false)
                {
                    break;
                }
            }
        }
        jobFile.open(wRunName,"OUTPUT");
    	jobFile.write(pReportName.trim() + " Start parameters:");
    	jobFile.write("GLB_INITSTN = " + GLB.INITSTN);
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

//Fim Comandos diferentes

//////////Comandos exclusivos

//beginPage
public void beginPage (String pShadow, String pFrame)
    throws Exception
{
	try
	{	/* Verifica se já foi definido */
    	for (int i=1; i <= GLB.BP_INDEX; i++)
    	{
    		if ( (GLB.BP[i][SHADOW].equals(pShadow) == true) && (GLB.BP[i][GLB.SEQ].equals(pFrame) == true) )
        	{
        		return;
			}
		}

    	GLB.BP_INDEX = GLB.BP_INDEX + 1;
    	if (GLB.BP_INDEX > GLB.BP.length)
    	{
    		for (int i=1; i <= GLB.BP_INDEX; i++)
        	{
	        	if ( (GLB.BP[i][SHADOW].equals("") == true) && (GLB.BP[i][GLB.SEQ].equals("") == true) )
    	    	{
        	    	GLB.BP[i][SHADOW] = pShadow;
            		GLB.BP[i][GLB.SEQ] = pFrame;
            		return;
				}
			}
			GLB.BP = (String[][])resizeArray(GLB.BP.length + 50, 2);
		}
    	else
    	{
    		GLB.BP[GLB.BP_INDEX][SHADOW] = pShadow;
        	GLB.BP[GLB.BP_INDEX][GLB.SEQ] = pFrame;
		}
	}
	catch(Exception e)
    {
    	ShowErrorMsg (e, "beginpage");
   		return;
    }
}

//beginPageClear
public void beginPageClear (String pShadow)
    throws Exception
{
	try
    {
        for (int i=1; i <= GLB.BP_INDEX; i++)
        {
            if (GLB.BP[i][SHADOW].equals(pShadow) == true)
            {
               GLB.BP[i][SHADOW] = "";
               GLB.BP[i][GLB.SEQ] = "";
            }
        }
         // Calcular o maior indice válido
        int maxIndex = 0;
        for (int i=1; i <= GLB.BP_INDEX; i++)
        {
            if (GLB.BP[i][SHADOW].equals("") == false)
            {
               maxIndex = i;
            }
        }
        GLB.BP_INDEX = maxIndex;
    }
    catch(Exception e)
    {
    	ShowErrorMsg (e, "beginPageClear");
   		return;
    }
}

//accept
public boolean hasAcceptFile()
    throws Exception
{
    try
    {
        String acceptTitle ="";
        File wFile=null;
        if (GLB.CONSOLE == true)
        {
            return(true);
        }
        if (acceptFileOpen == false)
        {   if (GLB.ACCEPTFILE.trim().equals("") == true)
            {   acceptTitle = GLB.BATCHDIR + File.separatorChar + GLB.REPNAME + ".acceptFile";
            }
            else
            {   acceptTitle = GLB.ACCEPTFILE;
            }

            wFile = new File(acceptTitle);
            if (wFile.exists() == false)
            {   AuditMSG ("AcceptFile (" + acceptTitle + ") not found");
                return(false);
            }

            FileInputStream acceptStream = new FileInputStream(acceptTitle);
            acceptFile = new BufferedReader(new InputStreamReader(acceptStream));
            acceptFileOpen = true;
        }
        return(true) ;

    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "hasAcceptFile");
        return(false);
    }
}


public String accept()
    throws Exception
{
	try
	{
        String acceptLine ="";
	    if (GLB.CONSOLE == true)
	    {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("ACCEPT>");
            acceptLine = in.readLine();
            in = null;
        }
        else
        {
   		    acceptLine = acceptFile.readLine();
        }

        if (acceptLine != null)
        {   return(acceptLine);
        }
        else
        {   return("");
        }
	}
    catch(Exception e)
    {
    	ShowErrorMsg (e, "accept");
   		return("");
    }
}

// printPageMark
public void printPageMark(PrintStream pLP, String pPageMark)
throws Exception
{
    try
    {
        pLP.println(pPageMark);
    }
    catch(Exception exception)
    {
        ShowErrorMsg(exception, "printPageMark");
    }
}

//pack
public String pack (String pField, String pFieldValue)
   throws Exception
{
    try
    {
         return("<" + pField + ">" + pFieldValue + "</" + pField + ">");
    }
    catch(Exception exception)
    {
        ShowErrorMsg(exception, "pack");
        return("");
    }
}

//unpack
public String unpack(String pCurrentLine,String pField)
  throws Exception
{
    int posicaoIni = 0;
    int posicaoFim = 0;

    String field="";

    try
    {

        pField = pField.trim();

        posicaoIni = pCurrentLine.indexOf("<" + pField + ">");
        posicaoFim = pCurrentLine.indexOf("</" + pField + ">");

        if ((posicaoIni != -1) && (posicaoFim != -1))
        {
            if (posicaoIni + pField.length() + 2 < posicaoFim)
            {
                field = pCurrentLine.substring(posicaoIni + pField.length() + 2,posicaoFim);
            }
            else
            {
                field = "";
            }
        }
        return (field);
    }
    catch(Exception exception)
    {
        ShowErrorMsg(exception, "unpack");
        return("");
    }

}

//sort
public String sort(String sortFileName, int sortPosition[], int sortLength[], boolean sortDescend[])
    throws Exception
{
	try
    {
    	XseedSort      mySort;
    	String         sortLine;
        List<XseedSort>          sortList = new ArrayList<XseedSort>();
        int            numOrdin = sortLength.length;

         // Set descending ordinates
        XseedSort.descend = sortDescend;

    	// Load
    	BufferedReader br       = new BufferedReader(new FileReader(sortFileName));

    	while( (sortLine = br.readLine()) != null )
    	{
    		mySort = new XseedSort(numOrdin);
    		for(int i=0; i < numOrdin; i++)
    		{
    			mySort.ordinate[i] = sortLine.substring(sortPosition[i], sortPosition[i] + sortLength[i]);
			}
            mySort.buffer  = sortLine;
    		sortList.add(mySort);
		}
    	br.close();

   		// Sort
    	Collections.sort(sortList);

        // Download
    	PrintWriter pw = new PrintWriter(new FileWriter(sortFileName));

    	for(int i=0; i < sortList.size(); i++)
    	{
    		mySort = (XseedSort)sortList.get(i);
    		pw.println(mySort.buffer);
    	}
    	pw.close();

    	return("");

	}
    catch(Exception e)
    {
    	ShowErrorMsg (e, "sort");
   		return("*****");
    }
}

//sort
public String sort(String sortFileName, int sortPosition[], int sortLength[], boolean sortDescend[], String sortEdit[])
    throws Exception
{
    try
    {
        XseedSort      mySort;
        String         sortLine;
        List<XseedSort>           sortList = new ArrayList<XseedSort>();
        int            numOrdin = sortLength.length;

         // Set descending ordinates
        XseedSort.descend = sortDescend;

        // Load
        BufferedReader br       = new BufferedReader(new FileReader(sortFileName));

        while( (sortLine = br.readLine()) != null )
        {
            mySort = new XseedSort(numOrdin);
            for(int i=0; i < numOrdin; i++)
            {   mySort.edit[i] = sortEdit[i];
                mySort.ordinate[i] = sortLine.substring(sortPosition[i], sortPosition[i] + sortLength[i]);
                if (mySort.edit[i].equals("A")==false)
                {
                    mySort.ordinate[i] = mySort.ordinate[i].trim();
                }
            }
            mySort.buffer  = sortLine;
            sortList.add(mySort);
        }
        br.close();

        // Sort
        Collections.sort(sortList);

        // Download
        PrintWriter pw = new PrintWriter(new FileWriter(sortFileName));

        for(int i=0; i < sortList.size(); i++)
        {
            mySort = (XseedSort)sortList.get(i);
            pw.println(mySort.buffer);
        }
        pw.close();

        return("");

    }
    catch(Exception e)
    {
        ShowErrorMsg (e, "sort");
        return("*****");
    }
}


public String getCaption (String pCaption, String pLocaleId )
   throws Exception
{
    String wFieldCaption = pCaption;

    if ((pLocaleId.equals("")==false) && (GLB.LANGUAGE.trim().equals("")==false))
    {   try
        {
            Locale currentLocale;
            ResourceBundle messages;
            currentLocale = new Locale(GLB.LANGUAGE);
            messages =   ResourceBundle.getBundle("Locale",currentLocale);
            wFieldCaption = messages.getString(pLocaleId);
        }
        catch (Exception e)
        {
        }

    }

    return (wFieldCaption);
}

 private  Object resizeArray (Object pOldArray, int pNewSize)
 	 throws Exception
 {
 	try {
   	   Class wElementType = pOldArray.getClass().getComponentType();
   	   Object pNewArray = java.lang.reflect.Array.newInstance(wElementType, pNewSize);
   	   return pNewArray;
 	} catch (Exception e){
 		ShowErrorMsg (e, "resizeArray");
        return(pOldArray);
 	}

 }

//Fim Comandos exclusivos
} // end Class XseedReport