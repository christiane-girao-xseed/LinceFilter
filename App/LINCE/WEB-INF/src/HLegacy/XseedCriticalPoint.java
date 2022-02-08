package HLegacy;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;

public class XseedCriticalPoint
{

    final public int SHADOW = 0;
    final public int SEQ = 1;

	public String reportName = "";
	public String initStn    = "";
	public String startDate  = "";
	public String startTime  = "";
	public int cpCount       = 0;
	public String pid        = "0";
	public String cpBuffer   = "";
	public String ipAddress  = "";

	//Extracts
	public int writesInExtractA = 0;
    public int writesInExtractB = 0;
    public int writesInExtractC = 0;
    public int writesInExtractD = 0;
    public int writesInExtractE = 0;
    public int writesInExtractF = 0;
    public int writesInExtractG = 0;
    public int writesInExtractH = 0;
    public int writesInExtractI = 0;
    public int writesInExtractJ = 0;
    public int writesInExtractK = 0;
    public int writesInExtractL = 0;
    public int writesInExtractM = 0;
    public int writesInExtractN = 0;
    public int writesInExtractO = 0;
    public int writesInExtractP = 0;
    public int writesInExtractQ = 0;
    public int writesInExtractR = 0;
    public int writesInExtractS = 0;
    public int writesInExtractT = 0;
    public int writesInExtractU = 0;
    public int writesInExtractV = 0;
    public int writesInExtractW = 0;
    public int writesInExtractY = 0;
    public int writesInExtractX = 0;
    public int writesInExtractZ = 0;

	public String filenameOfExtract = "";
    public String filenameOfExtractA = "";
    public String filenameOfExtractB = "";
    public String filenameOfExtractC = "";
    public String filenameOfExtractD = "";
    public String filenameOfExtractE = "";
    public String filenameOfExtractF = "";
    public String filenameOfExtractG = "";
    public String filenameOfExtractH = "";
    public String filenameOfExtractI = "";
    public String filenameOfExtractJ = "";
    public String filenameOfExtractK = "";
    public String filenameOfExtractL = "";
    public String filenameOfExtractM = "";
    public String filenameOfExtractN = "";
    public String filenameOfExtractO = "";
    public String filenameOfExtractP = "";
    public String filenameOfExtractQ = "";
    public String filenameOfExtractR = "";
    public String filenameOfExtractS = "";
    public String filenameOfExtractT = "";
    public String filenameOfExtractU = "";
    public String filenameOfExtractV = "";
    public String filenameOfExtractW = "";
    public String filenameOfExtractY = "";
    public String filenameOfExtractX = "";
    public String filenameOfExtractZ = "";


    //Print
    public boolean advanceOfPrint  = false;
    public boolean advanceOfPrintA = false;
    public boolean advanceOfPrintB = false;
    public boolean advanceOfPrintC = false;
    public boolean advanceOfPrintD = false;
    public boolean advanceOfPrintE = false;
    public boolean advanceOfPrintF = false;
    public boolean advanceOfPrintG = false;
    public boolean advanceOfPrintH = false;
    public boolean advanceOfPrintI = false;
    public boolean advanceOfPrintJ = false;
    public boolean advanceOfPrintK = false;
    public boolean advanceOfPrintL = false;
    public boolean advanceOfPrintM = false;
    public boolean advanceOfPrintN = false;
    public boolean advanceOfPrintO = false;
    public boolean advanceOfPrintP = false;
    public boolean advanceOfPrintQ = false;
    public boolean advanceOfPrintR = false;
    public boolean advanceOfPrintS = false;
    public boolean advanceOfPrintT = false;
    public boolean advanceOfPrintU = false;
    public boolean advanceOfPrintV = false;
    public boolean advanceOfPrintW = false;
    public boolean advanceOfPrintX = false;
    public boolean advanceOfPrintY = false;
    public boolean advanceOfPrintZ = false;


    public int writesInPrint = 0;
    public int writesInPrintA = 0;
    public int writesInPrintB = 0;
    public int writesInPrintC = 0;
    public int writesInPrintD = 0;
    public int writesInPrintE = 0;
    public int writesInPrintF = 0;
    public int writesInPrintG = 0;
    public int writesInPrintH = 0;
    public int writesInPrintI = 0;
    public int writesInPrintJ = 0;
    public int writesInPrintK = 0;
    public int writesInPrintL = 0;
    public int writesInPrintM = 0;
    public int writesInPrintN = 0;
    public int writesInPrintO = 0;
    public int writesInPrintP = 0;
    public int writesInPrintQ = 0;
    public int writesInPrintR = 0;
    public int writesInPrintS = 0;
    public int writesInPrintT = 0;
    public int writesInPrintU = 0;
    public int writesInPrintV = 0;
    public int writesInPrintW = 0;
    public int writesInPrintY = 0;
    public int writesInPrintX = 0;
    public int writesInPrintZ = 0;

    public String filenameOfPrint  = "";
    public String filenameOfPrintA = "";
    public String filenameOfPrintB = "";
    public String filenameOfPrintC = "";
    public String filenameOfPrintD = "";
    public String filenameOfPrintE = "";
    public String filenameOfPrintF = "";
    public String filenameOfPrintG = "";
    public String filenameOfPrintH = "";
    public String filenameOfPrintI = "";
    public String filenameOfPrintJ = "";
    public String filenameOfPrintK = "";
    public String filenameOfPrintL = "";
    public String filenameOfPrintM = "";
    public String filenameOfPrintN = "";
    public String filenameOfPrintO = "";
    public String filenameOfPrintP = "";
    public String filenameOfPrintQ = "";
    public String filenameOfPrintR = "";
    public String filenameOfPrintS = "";
    public String filenameOfPrintT = "";
    public String filenameOfPrintU = "";
    public String filenameOfPrintV = "";
    public String filenameOfPrintW = "";
    public String filenameOfPrintY = "";
    public String filenameOfPrintX = "";
    public String filenameOfPrintZ = "";

    //TITLE
    public String fileTitle = "";
    public String fileTitleA = "";
    public String fileTitleB = "";
    public String fileTitleC = "";
    public String fileTitleD = "";
    public String fileTitleE = "";
    public String fileTitleF = "";
    public String fileTitleG = "";
    public String fileTitleH = "";
    public String fileTitleI = "";
    public String fileTitleJ = "";
    public String fileTitleK = "";
    public String fileTitleL = "";
    public String fileTitleM = "";
    public String fileTitleN = "";
    public String fileTitleO = "";
    public String fileTitleP = "";
    public String fileTitleQ = "";
    public String fileTitleR = "";
    public String fileTitleS = "";
    public String fileTitleT = "";
    public String fileTitleU = "";
    public String fileTitleV = "";
    public String fileTitleW = "";
    public String fileTitleY = "";
    public String fileTitleX = "";
    public String fileTitleZ = "";

    int linecountOfPrint  = 0;
    int linecountOfPrintA = 0;
    int linecountOfPrintB = 0;
    int linecountOfPrintC = 0;
    int linecountOfPrintD = 0;
    int linecountOfPrintE = 0;
    int linecountOfPrintF = 0;
    int linecountOfPrintG = 0;
    int linecountOfPrintH = 0;
    int linecountOfPrintI = 0;
    int linecountOfPrintJ = 0;
    int linecountOfPrintK = 0;
    int linecountOfPrintL = 0;
    int linecountOfPrintM = 0;
    int linecountOfPrintN = 0;
    int linecountOfPrintO = 0;
    int linecountOfPrintP = 0;
    int linecountOfPrintQ = 0;
    int linecountOfPrintR = 0;
    int linecountOfPrintS = 0;
    int linecountOfPrintT = 0;
    int linecountOfPrintU = 0;
    int linecountOfPrintV = 0;
    int linecountOfPrintW = 0;
    int linecountOfPrintX = 0;
    int linecountOfPrintY = 0;
    int linecountOfPrintZ = 0;

    int pagecountOfPrint  = 0;
    int pagecountOfPrintA = 0;
    int pagecountOfPrintB = 0;
    int pagecountOfPrintC = 0;
    int pagecountOfPrintD = 0;
    int pagecountOfPrintE = 0;
    int pagecountOfPrintF = 0;
    int pagecountOfPrintG = 0;
    int pagecountOfPrintH = 0;
    int pagecountOfPrintI = 0;
    int pagecountOfPrintJ = 0;
    int pagecountOfPrintK = 0;
    int pagecountOfPrintL = 0;
    int pagecountOfPrintM = 0;
    int pagecountOfPrintN = 0;
    int pagecountOfPrintO = 0;
    int pagecountOfPrintP = 0;
    int pagecountOfPrintQ = 0;
    int pagecountOfPrintR = 0;
    int pagecountOfPrintS = 0;
    int pagecountOfPrintT = 0;
    int pagecountOfPrintU = 0;
    int pagecountOfPrintV = 0;
    int pagecountOfPrintW = 0;
    int pagecountOfPrintX = 0;
    int pagecountOfPrintY = 0;
    int pagecountOfPrintZ = 0;


    String[] beginPage = new String[120];
    String[] beginPageA = new String[120];
    String[] beginPageB = new String[120];
    String[] beginPageC = new String[120];
    String[] beginPageD = new String[120];
    String[] beginPageE = new String[120];
    String[] beginPageF = new String[120];
    String[] beginPageG = new String[120];
    String[] beginPageH = new String[120];
    String[] beginPageI = new String[120];
    String[] beginPageJ = new String[120];
    String[] beginPageK = new String[120];
    String[] beginPageL = new String[120];
    String[] beginPageM = new String[120];
    String[] beginPageN = new String[120];
    String[] beginPageO = new String[120];
    String[] beginPageP = new String[120];
    String[] beginPageQ = new String[120];
    String[] beginPageR = new String[120];
    String[] beginPageS = new String[120];
    String[] beginPageT = new String[120];
    String[] beginPageU = new String[120];
    String[] beginPageV = new String[120];
    String[] beginPageW = new String[120];
    String[] beginPageX = new String[120];
    String[] beginPageY = new String[120];
    String[] beginPageZ = new String[120];

	public XseedGLB GLB;

	private XseedFile cpFile = new XseedFile();
	private String openType;
	private String openStatus;

	public XseedCriticalPoint()
	{
	  initialize();
	}

	public void open(String pOpenType)
        throws Exception
	{
        String wFilePath;
        reportName = GLB.REPNAME;
        GLB.RECOVERID = GLB.RECOVERID.trim();
        if (GLB.RECOVERID.equals("")==false)
        {
            wFilePath = GLB.TEMPDIR + File.separator +  reportName + "_" + GLB.RECOVERID + ".CP";
        }
        else
        {
            wFilePath = GLB.TEMPDIR + File.separator + reportName + ".CP";
        }

        openType = pOpenType;

	 	if(openType.equals("INPUT"))
	 	{
	 	    if(XseedFunctions.FileExists(wFilePath))
	 		{
			    cpFile.open(wFilePath,openType);
            }
        }
       	else
        {
       	    cpFile.open(wFilePath,openType);
        }
		openStatus = "";
	}

	public void close()
        throws Exception
	{
		cpFile.close();
	}

	public void delete()
        throws Exception
	{
        File delFile=null;
        GLB.RECOVERID = GLB.RECOVERID.trim();
        if (GLB.RECOVERID.equals("")==false)
        {
             delFile = new File(GLB.TEMPDIR + File.separator + GLB.REPNAME + "_" + GLB.RECOVERID +  ".CP");
        }
        else
        {
		    delFile = new File(GLB.TEMPDIR + File.separator + GLB.REPNAME + ".CP");
	    }

	 	if (delFile.exists())
        {
            delFile.delete();
       	}
       	initialize();
	}


    public void read()
        throws Exception
    {
            String wLine = "";
            String wTagName = "";
            int wCloseFirstTag = 0;
            int wOpenEndTag = 0;

            open("INPUT");

            wLine = cpFile.read();

            while(wLine != null)
            {
                wCloseFirstTag = wLine.trim().indexOf(">");
                wOpenEndTag = wLine.trim().lastIndexOf("<");

                wTagName = wLine.trim().substring(1,wCloseFirstTag);

                if(wTagName.equalsIgnoreCase("reportName"))
                {
                    reportName = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("initStn"))
                {
                    initStn = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("startDate"))
                {
                    startDate = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("startTime"))
                {
                    startTime = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("cpCount"))
                {
                    cpCount = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pid"))
                {
                    pid = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("cpBuffer"))
                {
                    cpBuffer = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("ipAddress"))
                {
                    ipAddress = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }

                //Extract
                else if(wTagName.equalsIgnoreCase("writesInExtractA"))
                {
                    writesInExtractA = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractB"))
                {
                    writesInExtractB = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractC"))
                {
                    writesInExtractC = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractD"))
                {
                    writesInExtractD = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractE"))
                {
                    writesInExtractE = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractF"))
                {
                    writesInExtractF = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractG"))
                {
                    writesInExtractG = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractH"))
                {
                    writesInExtractH = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractI"))
                {
                    writesInExtractI = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractJ"))
                {
                    writesInExtractJ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractK"))
                {
                    writesInExtractK = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractL"))
                {
                    writesInExtractL = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractM"))
                {
                    writesInExtractM = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractN"))
                {
                    writesInExtractN = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractO"))
                {
                    writesInExtractO = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractP"))
                {
                    writesInExtractP = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractQ"))
                {
                    writesInExtractQ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractR"))
                {
                    writesInExtractR = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractS"))
                {
                    writesInExtractS = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractT"))
                {
                    writesInExtractT = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractU"))
                {
                    writesInExtractU = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractV"))
                {
                    writesInExtractV = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractW"))
                {
                    writesInExtractW = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractY"))
                {
                    writesInExtractY = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractX"))
                {
                    writesInExtractX = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInExtractZ"))
                {
                    writesInExtractZ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }

                else if(wTagName.equalsIgnoreCase("filenameOfExtractA"))
                {
                    filenameOfExtractA = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractB"))
                {
                    filenameOfExtractB = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractC"))
                {
                    filenameOfExtractC = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractD"))
                {
                    filenameOfExtractD = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractE"))
                {
                    filenameOfExtractE = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractF"))
                {
                    filenameOfExtractF = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractG"))
                {
                    filenameOfExtractG = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractH"))
                {
                    filenameOfExtractH = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractI"))
                {
                    filenameOfExtractI = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractJ"))
                {
                    filenameOfExtractJ = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractK"))
                {
                    filenameOfExtractK = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractL"))
                {
                    filenameOfExtractL = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractM"))
                {
                    filenameOfExtractM = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractN"))
                {
                    filenameOfExtractN = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractO"))
                {
                    filenameOfExtractO = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractP"))
                {
                    filenameOfExtractP = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractQ"))
                {
                    filenameOfExtractQ = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractR"))
                {
                    filenameOfExtractR = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractS"))
                {
                    filenameOfExtractS = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractT"))
                {
                    filenameOfExtractT = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractU"))
                {
                    filenameOfExtractU = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractV"))
                {
                    filenameOfExtractV = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractW"))
                {
                    filenameOfExtractW = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractY"))
                {
                    filenameOfExtractY = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractX"))
                {
                    filenameOfExtractX = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfExtractZ"))
                {
                    filenameOfExtractZ = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }


                //Print
                else if(wTagName.equalsIgnoreCase("advanceOfPrint"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrint = true;
                    }
                    else
                    {
                        advanceOfPrint = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintA"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintA = true;
                    }
                    else
                    {
                        advanceOfPrintA = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintB"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintB = true;
                    }
                    else
                    {
                        advanceOfPrintB = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintC"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintC = true;
                    }
                    else
                    {
                        advanceOfPrintC = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintD"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintD = true;
                    }
                    else
                    {
                        advanceOfPrintD = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintE"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintE = true;
                    }
                    else
                    {
                        advanceOfPrintE = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintF"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintF = true;
                    }
                    else
                    {
                        advanceOfPrintF = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintG"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintG = true;
                    }
                    else
                    {
                        advanceOfPrintG = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintH"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintH = true;
                    }
                    else
                    {
                        advanceOfPrintH = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintI"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintI = true;
                    }
                    else
                    {
                        advanceOfPrintI = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintJ"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintJ = true;
                    }
                    else
                    {
                        advanceOfPrintJ = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintK"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintK = true;
                    }
                    else
                    {
                        advanceOfPrintK = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintL"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintL = true;
                    }
                    else
                    {
                        advanceOfPrintL = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintM"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintM = true;
                    }
                    else
                    {
                        advanceOfPrintM = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintN"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintN = true;
                    }
                    else
                    {
                        advanceOfPrintN = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintO"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintO = true;
                    }
                    else
                    {
                        advanceOfPrintO = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintP"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintP = true;
                    }
                    else
                    {
                        advanceOfPrintP = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintQ"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintQ = true;
                    }
                    else
                    {
                        advanceOfPrintQ = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintR"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintR = true;
                    }
                    else
                    {
                        advanceOfPrintR = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintS"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintS = true;
                    }
                    else
                    {
                        advanceOfPrintS = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintT"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintT = true;
                    }
                    else
                    {
                        advanceOfPrintT = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintU"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintU = true;
                    }
                    else
                    {
                        advanceOfPrintU = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintV"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintV = true;
                    }
                    else
                    {
                        advanceOfPrintV = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintW"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintW = true;
                    }
                    else
                    {
                        advanceOfPrintW = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintX"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintX = true;
                    }
                    else
                    {
                        advanceOfPrintX = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintY"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintY = true;
                    }
                    else
                    {
                        advanceOfPrintY = false;
                    }
                }
                else if(wTagName.equalsIgnoreCase("advanceOfPrintZ"))
                {
                    if ((wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag).equals("true")))
                    {
                        advanceOfPrintZ = true;
                    }
                    else
                    {
                        advanceOfPrintZ = false;
                    }
                }

                else if(wTagName.equalsIgnoreCase("linecountOfPrint"))
                {
                    linecountOfPrint = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintA"))
                {
                    linecountOfPrintA = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintB"))
                {
                    linecountOfPrintB = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintC"))
                {
                    linecountOfPrintC = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintD"))
                {
                    linecountOfPrintD = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintE"))
                {
                    linecountOfPrintE = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintF"))
                {
                    linecountOfPrintF = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintG"))
                {
                    linecountOfPrintG = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintH"))
                {
                    linecountOfPrintH = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintI"))
                {
                    linecountOfPrintI = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintJ"))
                {
                    linecountOfPrintJ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintK"))
                {
                    linecountOfPrintK = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintL"))
                {
                    linecountOfPrintL = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintM"))
                {
                    linecountOfPrintM = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintN"))
                {
                    linecountOfPrintN = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintO"))
                {
                    linecountOfPrintO = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintP"))
                {
                    linecountOfPrintP = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintQ"))
                {
                    linecountOfPrintQ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintR"))
                {
                    linecountOfPrintR = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintS"))
                {
                    linecountOfPrintS = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintT"))
                {
                    linecountOfPrintT = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintU"))
                {
                    linecountOfPrintU = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintV"))
                {
                    linecountOfPrintV = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintW"))
                {
                    linecountOfPrintW = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintX"))
                {
                    linecountOfPrintX = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintY"))
                {
                    linecountOfPrintY = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("linecountOfPrintZ"))
                {
                    linecountOfPrintZ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }

                else if(wTagName.equalsIgnoreCase("pagecountOfPrint"))
                {
                    pagecountOfPrint = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintA"))
                {
                    pagecountOfPrintA = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintB"))
                {
                    pagecountOfPrintB = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintC"))
                {
                    pagecountOfPrintC = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintD"))
                {
                    pagecountOfPrintD = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintE"))
                {
                    pagecountOfPrintE = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintF"))
                {
                    pagecountOfPrintF = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintG"))
                {
                    pagecountOfPrintG = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintH"))
                {
                    pagecountOfPrintH = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintI"))
                {
                    pagecountOfPrintI = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintJ"))
                {
                    pagecountOfPrintJ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintK"))
                {
                    pagecountOfPrintK = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintL"))
                {
                    pagecountOfPrintL = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintM"))
                {
                    pagecountOfPrintM = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintN"))
                {
                    pagecountOfPrintN = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintO"))
                {
                    pagecountOfPrintO = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintP"))
                {
                    pagecountOfPrintP = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintQ"))
                {
                    pagecountOfPrintQ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintR"))
                {
                    pagecountOfPrintR = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintS"))
                {
                    pagecountOfPrintS = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintT"))
                {
                    pagecountOfPrintT = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintU"))
                {
                    pagecountOfPrintU = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintV"))
                {
                    pagecountOfPrintV = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintW"))
                {
                    pagecountOfPrintW = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintX"))
                {
                    pagecountOfPrintX = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintY"))
                {
                    pagecountOfPrintY = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("pagecountOfPrintZ"))
                {
                    pagecountOfPrintZ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }


                else if(wTagName.equalsIgnoreCase("writesInPrint"))
                {
                    writesInPrint = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintA"))
                {
                    writesInPrintA = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintB"))
                {
                    writesInPrintB = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintC"))
                {
                    writesInPrintC = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintD"))
                {
                    writesInPrintD = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintE"))
                {
                    writesInPrintE = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintF"))
                {
                    writesInPrintF = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintG"))
                {
                    writesInPrintG = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintH"))
                {
                    writesInPrintH = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintI"))
                {
                    writesInPrintI = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintJ"))
                {
                    writesInPrintJ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintK"))
                {
                    writesInPrintK = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintL"))
                {
                    writesInPrintL = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintM"))
                {
                    writesInPrintM = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintN"))
                {
                    writesInPrintN = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintO"))
                {
                    writesInPrintO = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintP"))
                {
                    writesInPrintP = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintQ"))
                {
                    writesInPrintQ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintR"))
                {
                    writesInPrintR = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintS"))
                {
                    writesInPrintS = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintT"))
                {
                    writesInPrintT = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintU"))
                {
                    writesInPrintU = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintV"))
                {
                    writesInPrintV = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintW"))
                {
                    writesInPrintW = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintY"))
                {
                    writesInPrintY = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintX"))
                {
                    writesInPrintX = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }
                else if(wTagName.equalsIgnoreCase("writesInPrintZ"))
                {
                    writesInPrintZ = Integer.parseInt(wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag));
                }

                else if(wTagName.equalsIgnoreCase("filenameOfPrint"))
                {
                    filenameOfPrint = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintA"))
                {
                    filenameOfPrintA = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintB"))
                {
                    filenameOfPrintB = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintC"))
                {
                    filenameOfPrintC = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintD"))
                {
                    filenameOfPrintD = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintE"))
                {
                    filenameOfPrintE = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintF"))
                {
                    filenameOfPrintF = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintG"))
                {
                    filenameOfPrintG = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintH"))
                {
                    filenameOfPrintH = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintI"))
                {
                    filenameOfPrintI = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintJ"))
                {
                    filenameOfPrintJ = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintK"))
                {
                    filenameOfPrintK = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintL"))
                {
                    filenameOfPrintL = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintM"))
                {
                    filenameOfPrintM = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintN"))
                {
                    filenameOfPrintN = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintO"))
                {
                    filenameOfPrintO = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintP"))
                {
                    filenameOfPrintP = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintQ"))
                {
                    filenameOfPrintQ = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintR"))
                {
                    filenameOfPrintR = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintS"))
                {
                    filenameOfPrintS = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintT"))
                {
                    filenameOfPrintT = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintU"))
                {
                    filenameOfPrintU = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintV"))
                {
                    filenameOfPrintV = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintW"))
                {
                    filenameOfPrintW = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintY"))
                {
                    filenameOfPrintY = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintX"))
                {
                    filenameOfPrintX = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("filenameOfPrintZ"))
                {
                    filenameOfPrintZ = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }

                //title
                if(wTagName.equalsIgnoreCase("fileTitle"))
                {
                    fileTitle = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleA"))
                {
                    fileTitleA = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleB"))
                {
                    fileTitleB = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleC"))
                {
                    fileTitleC = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleD"))
                {
                    fileTitleD = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleE"))
                {
                    fileTitleE = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleF"))
                {
                    fileTitleF = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleG"))
                {
                    fileTitleG = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleH"))
                {
                    fileTitleH = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleI"))
                {
                    fileTitleI = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleJ"))
                {
                    fileTitleJ = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleK"))
                {
                    fileTitleK = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleL"))
                {
                    fileTitleL = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleM"))
                {
                    fileTitleM = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleN"))
                {
                    fileTitleN = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleO"))
                {
                    fileTitleO = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleP"))
                {
                    fileTitleP = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleQ"))
                {
                    fileTitleQ = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleR"))
                {
                    fileTitleR = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleS"))
                {
                    fileTitleS = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleT"))
                {
                    fileTitleT = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleU"))
                {
                    fileTitleU = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleV"))
                {
                    fileTitleV = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleW"))
                {
                    fileTitleW = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleY"))
                {
                    fileTitleY = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleX"))
                {
                    fileTitleX = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                else if(wTagName.equalsIgnoreCase("fileTitleZ"))
                {
                    fileTitleZ = wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag);
                }
                //
                else if(wTagName.equalsIgnoreCase("beginPage"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPage,"");
                }
                else if(wTagName.equalsIgnoreCase("beginPageA"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageA,"A");
                }
                else if(wTagName.equalsIgnoreCase("beginPageB"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageB,"B");
                }
                else if(wTagName.equalsIgnoreCase("beginPageC"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageC,"C");
                }
                else if(wTagName.equalsIgnoreCase("beginPageD"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageD,"D");
                }
                else if(wTagName.equalsIgnoreCase("beginPageE"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageE,"E");
                }
                else if(wTagName.equalsIgnoreCase("beginPageF"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageF,"F");
                }
                else if(wTagName.equalsIgnoreCase("beginPageG"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageG,"G");
                }
                else if(wTagName.equalsIgnoreCase("beginPageH"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageH,"H");
                }
                else if(wTagName.equalsIgnoreCase("beginPageI"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageI,"I");
                }
                else if(wTagName.equalsIgnoreCase("beginPageJ"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageJ,"J");
                }
                else if(wTagName.equalsIgnoreCase("beginPageK"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageK,"K");
                }
                else if(wTagName.equalsIgnoreCase("beginPageL"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageL,"L");
                }
                else if(wTagName.equalsIgnoreCase("beginPageM"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageM,"M");
                }
                else if(wTagName.equalsIgnoreCase("beginPageN"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageN,"N");
                }
                else if(wTagName.equalsIgnoreCase("beginPageO"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageO,"O");
                }
                else if(wTagName.equalsIgnoreCase("beginPageP"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageP,"P");
                }
                else if(wTagName.equalsIgnoreCase("beginPageQ"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageQ,"Q");
                }
                else if(wTagName.equalsIgnoreCase("beginPageR"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageR,"R");
                }
                else if(wTagName.equalsIgnoreCase("beginPageS"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageS,"S");
                }
                else if(wTagName.equalsIgnoreCase("beginPageT"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageT,"T");
                }
                else if(wTagName.equalsIgnoreCase("beginPageU"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageU,"U");
                }
                else if(wTagName.equalsIgnoreCase("beginPageV"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageV,"V");
                }
                else if(wTagName.equalsIgnoreCase("beginPageW"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageW,"W");
                }
                else if(wTagName.equalsIgnoreCase("beginPageX"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageX,"X");
                }
                else if(wTagName.equalsIgnoreCase("beginPageY"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageY,"Y");
                }
                else if(wTagName.equalsIgnoreCase("beginPageZ"))
                {
                    readBeginPage (wLine.trim().substring(wCloseFirstTag + 1,wOpenEndTag),beginPageZ,"Z");
                }

                wLine = cpFile.read();

            }

 			close();
    }

private void readBeginPage (String pLine, String [] pBP,String pShadow)
{

    String wAuxBP = pLine.trim();
    int wContBP = 0;
    int wSeparator = pLine.trim().indexOf(",");
    while  (wSeparator > -1)
    {
        pBP[wContBP] = wAuxBP.substring(0,wSeparator);
        setBeginPage(pShadow,pBP[wContBP]);
        wContBP++;
        wAuxBP = wAuxBP.substring(wSeparator+1);
        wSeparator = wAuxBP.trim().indexOf(",");
    }
    if (wAuxBP.trim().equals("") == false)
    {
        pBP[wContBP] = wAuxBP;
        setBeginPage(pShadow,pBP[wContBP]);
        wContBP++;
    }

}


public void write()
        throws Exception
    {

            //GetPid gid = new GetPid();
            //pID = "" + gid.getPid();

            filenameOfExtractA = GLB.EXA_FILENAME;
            if ((GLB.EXA_XSEEDWRITES != 0) || (GLB.EXA_XSEEDREADS != 0))
            {
                writesInExtractA = (new Double(GLB.EXA_XSEEDWRITES)).intValue();
                if (( GLB.EXA != null ) && (GLB.EXA_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXA.close();
                    GLB.EXA.open(GLB.EXA_FILENAME,"APPEND");
                }
            }
            filenameOfExtractB = GLB.EXB_FILENAME;
            if ((GLB.EXB_XSEEDWRITES != 0) || (GLB.EXB_XSEEDREADS != 0))
            {
                writesInExtractB = (new Double(GLB.EXB_XSEEDWRITES)).intValue();
                if (( GLB.EXB != null )  && (GLB.EXB_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXB.close();
                    GLB.EXB.open(GLB.EXB_FILENAME,"APPEND");
                }
            }
            filenameOfExtractC = GLB.EXC_FILENAME;
            if ((GLB.EXC_XSEEDWRITES != 0) || (GLB.EXC_XSEEDREADS != 0))
            {
                writesInExtractC = (new Double(GLB.EXC_XSEEDWRITES)).intValue();
                if (( GLB.EXC != null )  && (GLB.EXC_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXC.close();
                    GLB.EXC.open(GLB.EXC_FILENAME,"APPEND");
                }
            }
            filenameOfExtractD = GLB.EXD_FILENAME;
            if ((GLB.EXD_XSEEDWRITES != 0) || (GLB.EXD_XSEEDREADS != 0))
            {
                writesInExtractD = (new Double(GLB.EXD_XSEEDWRITES)).intValue();
                if (( GLB.EXD != null )  && (GLB.EXC_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXD.close();
                    GLB.EXD.open(GLB.EXD_FILENAME,"APPEND");
                }
            }
            filenameOfExtractE = GLB.EXE_FILENAME;
            if ((GLB.EXE_XSEEDWRITES != 0) || (GLB.EXE_XSEEDREADS != 0))
            {
                writesInExtractE = (new Double(GLB.EXE_XSEEDWRITES)).intValue();
                if (( GLB.EXE != null ) && (GLB.EXE_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXE.close();
                    GLB.EXE.open(GLB.EXE_FILENAME,"APPEND");
                }
            }
            filenameOfExtractF = GLB.EXF_FILENAME;
            if ((GLB.EXF_XSEEDWRITES != 0) || (GLB.EXF_XSEEDREADS != 0))
            {
                writesInExtractF = (new Double(GLB.EXF_XSEEDWRITES)).intValue();
                if (( GLB.EXF != null )  && (GLB.EXF_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXF.close();
                    GLB.EXF.open(GLB.EXF_FILENAME,"APPEND");
                }
            }
            filenameOfExtractG = GLB.EXG_FILENAME;
            if ((GLB.EXG_XSEEDWRITES != 0) || (GLB.EXG_XSEEDREADS != 0))
            {
                writesInExtractG = (new Double(GLB.EXG_XSEEDWRITES)).intValue();
                if (( GLB.EXG != null )  && (GLB.EXG_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXG.close();
                    GLB.EXG.open(GLB.EXG_FILENAME,"APPEND");
                }
            }
            filenameOfExtractH = GLB.EXH_FILENAME;
            if ((GLB.EXH_XSEEDWRITES != 0) || (GLB.EXH_XSEEDREADS != 0))
            {
                writesInExtractH = (new Double(GLB.EXH_XSEEDWRITES)).intValue();
                if (( GLB.EXH != null )  && (GLB.EXH_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXH.close();
                    GLB.EXH.open(GLB.EXH_FILENAME,"APPEND");
                }
            }
            filenameOfExtractI = GLB.EXI_FILENAME;
            if ((GLB.EXI_XSEEDWRITES != 0) || (GLB.EXI_XSEEDREADS != 0))
            {
                writesInExtractI = (new Double(GLB.EXI_XSEEDWRITES)).intValue();
                if (( GLB.EXI != null )  && (GLB.EXI_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXI.close();
                    GLB.EXI.open(GLB.EXI_FILENAME,"APPEND");
                }
            }
            filenameOfExtractJ = GLB.EXJ_FILENAME;
            if ((GLB.EXJ_XSEEDWRITES != 0) || (GLB.EXJ_XSEEDREADS != 0))
            {
                writesInExtractJ = (new Double(GLB.EXJ_XSEEDWRITES)).intValue();
                if (( GLB.EXJ != null ) && (GLB.EXJ_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXJ.close();
                    GLB.EXJ.open(GLB.EXJ_FILENAME,"APPEND");
                }
            }
            filenameOfExtractK = GLB.EXK_FILENAME;
            if ((GLB.EXK_XSEEDWRITES != 0) || (GLB.EXK_XSEEDREADS != 0))
            {
                writesInExtractK = (new Double(GLB.EXK_XSEEDWRITES)).intValue();
                if (( GLB.EXK != null ) && (GLB.EXK_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXK.close();
                    GLB.EXK.open(GLB.EXK_FILENAME,"APPEND");
                }
            }
            filenameOfExtractL = GLB.EXL_FILENAME;
            if ((GLB.EXL_XSEEDWRITES != 0) || (GLB.EXL_XSEEDREADS != 0))
            {
                writesInExtractL = (new Double(GLB.EXL_XSEEDWRITES)).intValue();
                if (( GLB.EXL != null ) && (GLB.EXL_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXL.close();
                    GLB.EXL.open(GLB.EXL_FILENAME,"APPEND");
                }
            }
            filenameOfExtractM = GLB.EXM_FILENAME;
            if ((GLB.EXM_XSEEDWRITES != 0) || (GLB.EXM_XSEEDREADS != 0))
            {
                writesInExtractM = (new Double(GLB.EXM_XSEEDWRITES)).intValue();
                if (( GLB.EXM != null ) && (GLB.EXM_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXM.close();
                    GLB.EXM.open(GLB.EXM_FILENAME,"APPEND");
                }
            }
            filenameOfExtractN = GLB.EXN_FILENAME;
            if ((GLB.EXN_XSEEDWRITES != 0) || (GLB.EXN_XSEEDREADS != 0))
            {
                writesInExtractN = (new Double(GLB.EXN_XSEEDWRITES)).intValue();
                if (( GLB.EXN != null )  && (GLB.EXN_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXN.close();
                    GLB.EXN.open(GLB.EXN_FILENAME,"APPEND");
                }
            }
            filenameOfExtractO = GLB.EXO_FILENAME;
            if ((GLB.EXO_XSEEDWRITES != 0) || (GLB.EXO_XSEEDREADS != 0))
            {
                writesInExtractO = (new Double(GLB.EXO_XSEEDWRITES)).intValue();
                if (( GLB.EXO != null ) && (GLB.EXO_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXO.close();
                    GLB.EXO.open(GLB.EXO_FILENAME,"APPEND");
                }
            }
            filenameOfExtractP = GLB.EXP_FILENAME;
            if ((GLB.EXP_XSEEDWRITES != 0) || (GLB.EXP_XSEEDREADS != 0))
            {
                writesInExtractP = (new Double(GLB.EXP_XSEEDWRITES)).intValue();
                if (( GLB.EXP != null ) && (GLB.EXP_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXP.close();
                    GLB.EXP.open(GLB.EXP_FILENAME,"APPEND");
                }
            }
            filenameOfExtractQ = GLB.EXQ_FILENAME;
            if ((GLB.EXQ_XSEEDWRITES != 0) || (GLB.EXQ_XSEEDREADS != 0))
            {
                writesInExtractQ = (new Double(GLB.EXQ_XSEEDWRITES)).intValue();
                if (( GLB.EXQ != null ) && (GLB.EXQ_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXQ.close();
                    GLB.EXQ.open(GLB.EXQ_FILENAME,"APPEND");
                }
            }
            filenameOfExtractR = GLB.EXR_FILENAME;
            if ((GLB.EXR_XSEEDWRITES != 0) || (GLB.EXR_XSEEDREADS != 0))
            {
                writesInExtractR = (new Double(GLB.EXR_XSEEDWRITES)).intValue();
                if (( GLB.EXR != null ) && (GLB.EXR_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXR.close();
                    GLB.EXR.open(GLB.EXR_FILENAME,"APPEND");
                }
            }
            filenameOfExtractS = GLB.EXS_FILENAME;
            if ((GLB.EXS_XSEEDWRITES != 0) || (GLB.EXS_XSEEDREADS != 0))
            {
                writesInExtractS = (new Double(GLB.EXS_XSEEDWRITES)).intValue();
                if (( GLB.EXS != null ) && (GLB.EXS_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXS.close();
                    GLB.EXS.open(GLB.EXS_FILENAME,"APPEND");
                }
            }
            filenameOfExtractT = GLB.EXT_FILENAME;
            if ((GLB.EXT_XSEEDWRITES != 0) || (GLB.EXT_XSEEDREADS != 0))
            {
                writesInExtractT = (new Double(GLB.EXT_XSEEDWRITES)).intValue();
                if (( GLB.EXT != null ) && (GLB.EXT_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXT.close();
                    GLB.EXT.open(GLB.EXT_FILENAME,"APPEND");
                }
            }
            filenameOfExtractU = GLB.EXU_FILENAME;
            if ((GLB.EXU_XSEEDWRITES != 0) || (GLB.EXU_XSEEDREADS != 0))
            {
                writesInExtractU = (new Double(GLB.EXU_XSEEDWRITES)).intValue();
                if (( GLB.EXU != null ) && (GLB.EXU_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXU.close();
                    GLB.EXU.open(GLB.EXU_FILENAME,"APPEND");
                }
            }
            filenameOfExtractV = GLB.EXV_FILENAME;
            if ((GLB.EXV_XSEEDWRITES != 0) || (GLB.EXV_XSEEDREADS != 0))
            {
                writesInExtractV = (new Double(GLB.EXV_XSEEDWRITES)).intValue();
                if (( GLB.EXV != null ) && (GLB.EXV_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXV.close();
                    GLB.EXV.open(GLB.EXV_FILENAME,"APPEND");
                }
            }
            filenameOfExtractW = GLB.EXW_FILENAME;
            if ((GLB.EXW_XSEEDWRITES != 0) || (GLB.EXW_XSEEDREADS != 0))
            {
                writesInExtractW = (new Double(GLB.EXW_XSEEDWRITES)).intValue();
                if (( GLB.EXW != null ) && (GLB.EXW_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXW.close();
                    GLB.EXW.open(GLB.EXW_FILENAME,"APPEND");
                }
            }
            filenameOfExtractX = GLB.EXX_FILENAME;
            if ((GLB.EXX_XSEEDWRITES != 0) || (GLB.EXX_XSEEDREADS != 0))
            {
                writesInExtractX = (new Double(GLB.EXX_XSEEDWRITES)).intValue();
                if (( GLB.EXX != null ) && (GLB.EXX_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXX.close();
                    GLB.EXX.open(GLB.EXX_FILENAME,"APPEND");
                }
            }
            filenameOfExtractY = GLB.EXY_FILENAME;
            if ((GLB.EXY_XSEEDWRITES != 0) || (GLB.EXY_XSEEDREADS != 0))
            {
                writesInExtractY = (new Double(GLB.EXY_XSEEDWRITES)).intValue();
                if (( GLB.EXY != null ) && (GLB.EXY_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXY.close();
                    GLB.EXY.open(GLB.EXY_FILENAME,"APPEND");
                }
            }
            filenameOfExtractZ = GLB.EXZ_FILENAME;
            if ((GLB.EXZ_XSEEDWRITES != 0) || (GLB.EXZ_XSEEDREADS != 0))
            {
                writesInExtractZ = (new Double(GLB.EXZ_XSEEDWRITES)).intValue();
                if (( GLB.EXZ != null ) && (GLB.EXZ_STATUS.trim().equals("OUTPUT")))
                {
                    GLB.EXZ.close();
                    GLB.EXZ.open(GLB.EXZ_FILENAME,"APPEND");
                }
            }

            //PRINT
           int wIndex =0;
           if (GLB.PRINT_XSEEDWRITES != 0)
            {
                writesInPrint = (new Double(GLB.PRINT_XSEEDWRITES)).intValue();
                advanceOfPrint = GLB.ADVANCE;
                linecountOfPrint = (int) GLB.LINECOUNT;
                pagecountOfPrint = (int) GLB.PAGECOUNT;
                if ( GLB.LP !=null ) {
                    GLB.LP.close();
                }
                GLB.LP = new PrintStream(new FileOutputStream(GLB.LP_XSEEDTITLE ,true));
            }

            if (GLB.LP_XSEEDTITLE!=null && GLB.LP_XSEEDTITLE.trim().equals("")==false)   {
            	filenameOfPrint = GLB.LP_XSEEDTITLE;
            }
            if (GLB.TITLE!=null && GLB.TITLE.trim().equals("")==false)   {
            	fileTitle = GLB.TITLE;
            }

            wIndex =0;
			for (int i = 1; i < GLB.BP.length; i++)
			{
            	if (GLB.BP[i][SHADOW].equals(" ") == true)
                {
                    beginPage[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                }
            }

            if (GLB.PRINTA_XSEEDWRITES != 0)
            {
                writesInPrintA = (new Double(GLB.PRINTA_XSEEDWRITES)).intValue();
                advanceOfPrintA = GLB.ADVANCEA;
                linecountOfPrintA = (int) GLB.LINECOUNTA;
                pagecountOfPrintA = (int) GLB.PAGECOUNTA;
                if ( GLB.LPA !=null ) {
                    GLB.LPA.close();
                }
                GLB.LPA = new PrintStream(new FileOutputStream(GLB.LPA_XSEEDTITLE ,true));
            }

            if (GLB.LPA_XSEEDTITLE!=null && GLB.LPA_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintA = GLB.LPA_XSEEDTITLE;
            }
            if (GLB.TITLEA!=null && GLB.TITLEA.trim().equals("")==false)   {
            	fileTitleA = GLB.TITLEA;
            }

            wIndex=0;
			for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("A") == true)
                {
                    beginPageA[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                }
            }

            if (GLB.PRINTB_XSEEDWRITES != 0)
            {
                writesInPrintB = (new Double(GLB.PRINTB_XSEEDWRITES)).intValue();
                advanceOfPrintB = GLB.ADVANCEB;
                linecountOfPrintB = (int) GLB.LINECOUNTB;
                pagecountOfPrintB = (int) GLB.PAGECOUNTB;
                if ( GLB.LPB !=null ) {
                   GLB.LPB.close();
                }
                GLB.LPB = new PrintStream(new FileOutputStream(GLB.LPB_XSEEDTITLE ,true));

            }

            if (GLB.LPB_XSEEDTITLE!=null && GLB.LPB_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintB = GLB.LPB_XSEEDTITLE;
            }
            if (GLB.TITLEB!=null && GLB.TITLEB.trim().equals("")==false)   {
            	fileTitleB = GLB.TITLEB;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("B") == true)
                {
                    beginPageB[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTC_XSEEDWRITES != 0)
            {
                writesInPrintC = (new Double(GLB.PRINTC_XSEEDWRITES)).intValue();
                advanceOfPrintC = GLB.ADVANCEC;
                linecountOfPrintC = (int) GLB.LINECOUNTC;
                pagecountOfPrintC = (int) GLB.PAGECOUNTC;
                if ( GLB.LPC !=null ) {
                   GLB.LPC.close();
                }
                GLB.LPC = new PrintStream(new FileOutputStream(GLB.LPC_XSEEDTITLE ,true));
            }

            if (GLB.LPC_XSEEDTITLE!=null && GLB.LPC_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintC = GLB.LPC_XSEEDTITLE;
            }
            if (GLB.TITLEC!=null && GLB.TITLEC.trim().equals("")==false)   {
            	fileTitleC = GLB.TITLEC;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("C") == true)
                {
                    beginPageC[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTD_XSEEDWRITES != 0)
            {
                writesInPrintD = (new Double(GLB.PRINTD_XSEEDWRITES)).intValue();
                advanceOfPrintD = GLB.ADVANCED;
                linecountOfPrintD = (int) GLB.LINECOUNTD;
                pagecountOfPrintD = (int) GLB.PAGECOUNTD;
                if ( GLB.LPD !=null ) {
                    GLB.LPD.close();
                }
                GLB.LPD = new PrintStream(new FileOutputStream(GLB.LPD_XSEEDTITLE ,true));
            }

            if (GLB.LPD_XSEEDTITLE!=null && GLB.LPD_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintD = GLB.LPD_XSEEDTITLE;
            }
            if (GLB.TITLED!=null && GLB.TITLED.trim().equals("")==false)   {
            	fileTitleD = GLB.TITLED;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("D") == true)
                {
                    beginPageD[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTE_XSEEDWRITES != 0)
            {
                writesInPrintE = (new Double(GLB.PRINTE_XSEEDWRITES)).intValue();
                advanceOfPrintE = GLB.ADVANCEE;
                linecountOfPrintE = (int) GLB.LINECOUNTE;
                pagecountOfPrintE = (int) GLB.PAGECOUNTE;
                if ( GLB.LPE !=null ) {
                    GLB.LPE.close();
                }
                GLB.LPE = new PrintStream(new FileOutputStream(GLB.LPE_XSEEDTITLE ,true));
            }

            if (GLB.LPE_XSEEDTITLE!=null && GLB.LPE_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintE = GLB.LPE_XSEEDTITLE;
            }
            if (GLB.TITLEE!=null && GLB.TITLEE.trim().equals("")==false)   {
            	fileTitleE = GLB.TITLEE;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("E") == true)
                {
                    beginPageE[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTF_XSEEDWRITES != 0)
            {
                writesInPrintF = (new Double(GLB.PRINTF_XSEEDWRITES)).intValue();
                advanceOfPrintF = GLB.ADVANCEF;
                linecountOfPrintF = (int) GLB.LINECOUNTF;
                pagecountOfPrintF = (int) GLB.PAGECOUNTF;
                if ( GLB.LPF !=null ) {
                    GLB.LPF.close();
                }
                GLB.LPF = new PrintStream(new FileOutputStream(GLB.LPF_XSEEDTITLE ,true));
            }

            if (GLB.LPF_XSEEDTITLE!=null && GLB.LPF_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintF = GLB.LPF_XSEEDTITLE;
            }
            if (GLB.TITLEF!=null && GLB.TITLEF.trim().equals("")==false)   {
            	fileTitleF = GLB.TITLEF;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("F") == true)
                {
                    beginPageF[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTG_XSEEDWRITES != 0)
            {
                writesInPrintG = (new Double(GLB.PRINTG_XSEEDWRITES)).intValue();
                advanceOfPrintG = GLB.ADVANCEG;
                linecountOfPrintG = (int) GLB.LINECOUNTG;
                pagecountOfPrintG = (int) GLB.PAGECOUNTG;
                if ( GLB.LPG !=null ) {
                    GLB.LPG.close();
                }
                GLB.LPG = new PrintStream(new FileOutputStream(GLB.LPG_XSEEDTITLE ,true));
            }

            if (GLB.LPG_XSEEDTITLE!=null && GLB.LPG_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintG = GLB.LPG_XSEEDTITLE;
            }
            if (GLB.TITLEG!=null && GLB.TITLEG.trim().equals("")==false)   {
            	fileTitleG = GLB.TITLEG;
            }

			wIndex=0;
			for (int i = 1; i < GLB.BP.length; i++)
			{
                if (GLB.BP[i][SHADOW].equals("G") == true)
                {
                    beginPageG[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTH_XSEEDWRITES != 0)
            {
                writesInPrintH = (new Double(GLB.PRINTH_XSEEDWRITES)).intValue();
                advanceOfPrintH = GLB.ADVANCEH;
                linecountOfPrintH = (int) GLB.LINECOUNTH;
                pagecountOfPrintH = (int) GLB.PAGECOUNTH;
                if ( GLB.LPH !=null ) {
                    GLB.LPH.close();
                }
                GLB.LPH = new PrintStream(new FileOutputStream(GLB.LPH_XSEEDTITLE ,true));
            }

            if (GLB.LPH_XSEEDTITLE!=null && GLB.LPH_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintH = GLB.LPH_XSEEDTITLE;
            }
            if (GLB.TITLEH!=null && GLB.TITLEH.trim().equals("")==false)   {
            	fileTitleH = GLB.TITLEH;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("H") == true)
                {
                    beginPageH[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTI_XSEEDWRITES != 0)
            {
                writesInPrintI = (new Double(GLB.PRINTI_XSEEDWRITES)).intValue();
                advanceOfPrintI = GLB.ADVANCEI;
                linecountOfPrintI = (int) GLB.LINECOUNTI;
                pagecountOfPrintI = (int) GLB.PAGECOUNTI;
                if ( GLB.LPI !=null ) {
                    GLB.LPI.close();
                }
                GLB.LPI = new PrintStream(new FileOutputStream(GLB.LPI_XSEEDTITLE ,true));
            }

            if (GLB.LPI_XSEEDTITLE!=null && GLB.LPI_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintI = GLB.LPI_XSEEDTITLE;
            }
            if (GLB.TITLEI!=null && GLB.TITLEI.trim().equals("")==false)   {
            	fileTitleI = GLB.TITLEI;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("I") == true)
                {
                    beginPageI[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTJ_XSEEDWRITES != 0)
            {
                writesInPrintJ = (new Double(GLB.PRINTJ_XSEEDWRITES)).intValue();
                advanceOfPrintJ = GLB.ADVANCEJ;
                linecountOfPrintJ = (int) GLB.LINECOUNTJ;
                pagecountOfPrintJ = (int) GLB.PAGECOUNTJ;
                if ( GLB.LPJ !=null ) {
                    GLB.LPJ.close();
                }
                GLB.LPJ = new PrintStream(new FileOutputStream(GLB.LPJ_XSEEDTITLE ,true));
            }

            if (GLB.LPJ_XSEEDTITLE!=null && GLB.LPJ_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintJ = GLB.LPJ_XSEEDTITLE;
            }
            if (GLB.TITLEJ!=null && GLB.TITLEJ.trim().equals("")==false)   {
            	fileTitleJ = GLB.TITLEJ;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("J") == true)
                {
                    beginPageJ[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTK_XSEEDWRITES != 0)
            {
                writesInPrintK = (new Double(GLB.PRINTK_XSEEDWRITES)).intValue();
                filenameOfPrintK = GLB.LPK_XSEEDTITLE;
                advanceOfPrintK = GLB.ADVANCEK;
                linecountOfPrintK = (int) GLB.LINECOUNTK;
                pagecountOfPrintK = (int) GLB.PAGECOUNTK;
                if ( GLB.LPK !=null ) {
                    GLB.LPK.close();
                }
                GLB.LPK = new PrintStream(new FileOutputStream(GLB.LPK_XSEEDTITLE ,true));
            }

            if (GLB.LPK_XSEEDTITLE!=null && GLB.LPK_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintK = GLB.LPK_XSEEDTITLE;
            }
            if (GLB.TITLEK!=null && GLB.TITLEK.trim().equals("")==false)   {
            	fileTitleK = GLB.TITLEK;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("K") == true)
                {
                    beginPageK[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTL_XSEEDWRITES != 0)
            {
                writesInPrintL = (new Double(GLB.PRINTL_XSEEDWRITES)).intValue();
                advanceOfPrintL = GLB.ADVANCEL;
                linecountOfPrintL = (int) GLB.LINECOUNTL;
                pagecountOfPrintL = (int) GLB.PAGECOUNTL;
                if ( GLB.LPL !=null ) {
                    GLB.LPL.close();
                }
                GLB.LPL = new PrintStream(new FileOutputStream(GLB.LPL_XSEEDTITLE ,true));
            }

            if (GLB.LPL_XSEEDTITLE!=null && GLB.LPL_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintL = GLB.LPL_XSEEDTITLE;
            }
            if (GLB.TITLEL!=null && GLB.TITLEL.trim().equals("")==false)   {
            	fileTitleL = GLB.TITLEL;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("L") == true)
                {
                    beginPageL[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTM_XSEEDWRITES != 0)
            {
                writesInPrintM = (new Double(GLB.PRINTM_XSEEDWRITES)).intValue();
                advanceOfPrintM = GLB.ADVANCEM;
                linecountOfPrintM = (int) GLB.LINECOUNTM;
                pagecountOfPrintM = (int) GLB.PAGECOUNTM;
                if ( GLB.LPM !=null ) {
                    GLB.LPM.close();
                }
                GLB.LPM = new PrintStream(new FileOutputStream(GLB.LPM_XSEEDTITLE ,true));
            }

            if (GLB.LPM_XSEEDTITLE!=null && GLB.LPM_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintM = GLB.LPM_XSEEDTITLE;
            }
            if (GLB.TITLEM!=null && GLB.TITLEM.trim().equals("")==false)   {
            	fileTitleM = GLB.TITLEM;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("M") == true)
                {
                    beginPageM[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }


            if (GLB.PRINTN_XSEEDWRITES != 0)
            {
                writesInPrintN = (new Double(GLB.PRINTN_XSEEDWRITES)).intValue();
                advanceOfPrintN = GLB.ADVANCEN;
                linecountOfPrintN = (int) GLB.LINECOUNTN;
                pagecountOfPrintN = (int) GLB.PAGECOUNTN;
                if ( GLB.LPN !=null ) {
                    GLB.LPN.close();
                }
                GLB.LPN = new PrintStream(new FileOutputStream(GLB.LPN_XSEEDTITLE ,true));
            }

            if (GLB.LPN_XSEEDTITLE!=null && GLB.LPN_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintN = GLB.LPN_XSEEDTITLE;
            }
            if (GLB.TITLEN!=null && GLB.TITLEN.trim().equals("")==false)   {
            	fileTitleN = GLB.TITLEN;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("N") == true)
                {
                    beginPageN[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTO_XSEEDWRITES != 0)
            {
                writesInPrintO = (new Double(GLB.PRINTO_XSEEDWRITES)).intValue();
                advanceOfPrintO = GLB.ADVANCEO;
                linecountOfPrintO = (int) GLB.LINECOUNTO;
                pagecountOfPrintO = (int) GLB.PAGECOUNTO;
                if ( GLB.LPO !=null ) {
                    GLB.LPO.close();
                }
                GLB.LPO = new PrintStream(new FileOutputStream(GLB.LPO_XSEEDTITLE ,true));
            }
            wIndex=0;

            if (GLB.LPO_XSEEDTITLE!=null && GLB.LPO_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintO = GLB.LPO_XSEEDTITLE;
            }
            if (GLB.TITLEO!=null && GLB.TITLEO.trim().equals("")==false)   {
            	fileTitleO = GLB.TITLEO;
            }

            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("O") == true)
                {
                    beginPageO[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTP_XSEEDWRITES != 0)
            {
                writesInPrintP = (new Double(GLB.PRINTP_XSEEDWRITES)).intValue();
                advanceOfPrintP = GLB.ADVANCEP;
                linecountOfPrintP = (int) GLB.LINECOUNTP;
                pagecountOfPrintP = (int) GLB.PAGECOUNTP;
                if ( GLB.LPP !=null ) {
                    GLB.LPP.close();
                }
                GLB.LPP = new PrintStream(new FileOutputStream(GLB.LPP_XSEEDTITLE ,true));
            }

            if (GLB.LPP_XSEEDTITLE!=null && GLB.LPP_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintP = GLB.LPP_XSEEDTITLE;
            }
            if (GLB.TITLEP!=null && GLB.TITLEP.trim().equals("")==false)   {
            	fileTitleP = GLB.TITLEP;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("P") == true)
                {
                    beginPageP[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTQ_XSEEDWRITES != 0)
            {
                writesInPrintQ = (new Double(GLB.PRINTQ_XSEEDWRITES)).intValue();
                advanceOfPrintQ = GLB.ADVANCEQ;
                linecountOfPrintQ = (int) GLB.LINECOUNTQ;
                pagecountOfPrintQ = (int) GLB.PAGECOUNTQ;
                if ( GLB.LPQ !=null ) {
                    GLB.LPQ.close();
                }
                GLB.LPQ = new PrintStream(new FileOutputStream(GLB.LPQ_XSEEDTITLE ,true));
            }

            if (GLB.LPQ_XSEEDTITLE!=null && GLB.LPQ_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintQ = GLB.LPQ_XSEEDTITLE;
            }
            if (GLB.TITLEQ!=null && GLB.TITLEQ.trim().equals("")==false)   {
            	fileTitleQ = GLB.TITLEQ;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("Q") == true)
                {
                    beginPageQ[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }


            if (GLB.PRINTR_XSEEDWRITES != 0)
            {
                writesInPrintR = (new Double(GLB.PRINTR_XSEEDWRITES)).intValue();
                advanceOfPrintR = GLB.ADVANCER;
                linecountOfPrintR = (int) GLB.LINECOUNTR;
                pagecountOfPrintR = (int) GLB.PAGECOUNTR;
                if ( GLB.LPR !=null ) {
                    GLB.LPR.close();
                }
                GLB.LPR = new PrintStream(new FileOutputStream(GLB.LPR_XSEEDTITLE ,true));
            }

            if (GLB.LPR_XSEEDTITLE!=null && GLB.LPR_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintR = GLB.LPR_XSEEDTITLE;
            }
            if (GLB.TITLER!=null && GLB.TITLER.trim().equals("")==false)   {
            	fileTitleR = GLB.TITLER;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("R") == true)
                {
                    beginPageR[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTS_XSEEDWRITES != 0)
            {
                writesInPrintS = (new Double(GLB.PRINTS_XSEEDWRITES)).intValue();
                advanceOfPrintS = GLB.ADVANCES;
                linecountOfPrintS = (int) GLB.LINECOUNTS;
                pagecountOfPrintS = (int) GLB.PAGECOUNTS;
                if ( GLB.LPS !=null ) {
                    GLB.LPS.close();
                }
                GLB.LPS = new PrintStream(new FileOutputStream(GLB.LPS_XSEEDTITLE ,true));
            }

            if (GLB.LPS_XSEEDTITLE!=null && GLB.LPS_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintS = GLB.LPS_XSEEDTITLE;
            }
            if (GLB.TITLES!=null && GLB.TITLES.trim().equals("")==false)   {
            	fileTitleS = GLB.TITLES;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("S") == true)
                {
                    beginPageS[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTT_XSEEDWRITES != 0)
            {
                writesInPrintT = (new Double(GLB.PRINTT_XSEEDWRITES)).intValue();
                advanceOfPrintT = GLB.ADVANCET;
                linecountOfPrintT = (int) GLB.LINECOUNTT;
                pagecountOfPrintT = (int) GLB.PAGECOUNTT;
                if ( GLB.LPT !=null ) {
                    GLB.LPT.close();
                }
                GLB.LPT = new PrintStream(new FileOutputStream(GLB.LPT_XSEEDTITLE ,true));
            }

            if (GLB.LPT_XSEEDTITLE!=null && GLB.LPT_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintT = GLB.LPT_XSEEDTITLE;
            }
            if (GLB.TITLET!=null && GLB.TITLET.trim().equals("")==false)   {
            	fileTitleT = GLB.TITLET;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("T") == true)
                {
                    beginPageT[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTU_XSEEDWRITES != 0)
            {
                writesInPrintU = (new Double(GLB.PRINTU_XSEEDWRITES)).intValue();
                advanceOfPrintU = GLB.ADVANCEU;
                linecountOfPrintU = (int) GLB.LINECOUNTU;
                pagecountOfPrintU = (int) GLB.PAGECOUNTU;
                if ( GLB.LPU !=null ) {
                    GLB.LPU.close();
                }
                GLB.LPU = new PrintStream(new FileOutputStream(GLB.LPU_XSEEDTITLE ,true));
            }

            if (GLB.LPU_XSEEDTITLE!=null && GLB.LPU_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintU = GLB.LPU_XSEEDTITLE;
            }
            if (GLB.TITLEU!=null && GLB.TITLEU.trim().equals("")==false)   {
            	fileTitleU = GLB.TITLEU;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("U") == true)
                {
                    beginPageU[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTV_XSEEDWRITES != 0)
            {
                writesInPrintV = (new Double(GLB.PRINTV_XSEEDWRITES)).intValue();
                advanceOfPrintV = GLB.ADVANCEV;
                linecountOfPrintV = (int) GLB.LINECOUNTV;
                pagecountOfPrintV = (int) GLB.PAGECOUNTV;
                if ( GLB.LPV !=null ) {
                    GLB.LPV.close();
                }
                GLB.LPV = new PrintStream(new FileOutputStream(GLB.LPV_XSEEDTITLE ,true));
            }

            if (GLB.LPV_XSEEDTITLE!=null && GLB.LPV_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintV = GLB.LPV_XSEEDTITLE;
            }
            if (GLB.TITLEV!=null && GLB.TITLEV.trim().equals("")==false)   {
            	fileTitleV = GLB.TITLEV;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("V") == true)
                {
                    beginPageV[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }


            if (GLB.PRINTW_XSEEDWRITES != 0)
            {
                writesInPrintW = (new Double(GLB.PRINTW_XSEEDWRITES)).intValue();
                advanceOfPrintW = GLB.ADVANCEW;
                linecountOfPrintW = (int) GLB.LINECOUNTW;
                pagecountOfPrintW = (int) GLB.PAGECOUNTW;
                if ( GLB.LPW !=null ) {
                    GLB.LPW.close();
                }
                GLB.LPW = new PrintStream(new FileOutputStream(GLB.LPW_XSEEDTITLE ,true));
            }

            if (GLB.LPW_XSEEDTITLE!=null && GLB.LPW_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintW = GLB.LPW_XSEEDTITLE;
            }
            if (GLB.TITLEW!=null && GLB.TITLEW.trim().equals("")==false)   {
            	fileTitleW = GLB.TITLEW;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("W") == true)
                {
                    beginPageW[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTX_XSEEDWRITES != 0)
            {
                writesInPrintX = (new Double(GLB.PRINTX_XSEEDWRITES)).intValue();
                advanceOfPrintX = GLB.ADVANCEX;
                linecountOfPrintX = (int) GLB.LINECOUNTX;
                pagecountOfPrintX = (int) GLB.PAGECOUNTX;
                if ( GLB.LPX !=null ) {
                    GLB.LPX.close();
                }
                GLB.LPX = new PrintStream(new FileOutputStream(GLB.LPX_XSEEDTITLE ,true));
            }

            if (GLB.LPX_XSEEDTITLE!=null && GLB.LPX_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintX = GLB.LPX_XSEEDTITLE;
            }
            if (GLB.TITLEX!=null && GLB.TITLEX.trim().equals("")==false)   {
            	fileTitleX = GLB.TITLEX;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("X") == true)
                {
                    beginPageX[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTY_XSEEDWRITES != 0)
            {
                writesInPrintY = (new Double(GLB.PRINTY_XSEEDWRITES)).intValue();
                advanceOfPrintY = GLB.ADVANCEY;
                linecountOfPrintY = (int) GLB.LINECOUNTY;
                pagecountOfPrintY = (int) GLB.PAGECOUNTY;
                if ( GLB.LPY !=null ) {
                    GLB.LPY.close();
                }
                GLB.LPY = new PrintStream(new FileOutputStream(GLB.LPY_XSEEDTITLE ,true));
            }

            if (GLB.LPY_XSEEDTITLE!=null && GLB.LPY_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintY = GLB.LPY_XSEEDTITLE;
            }
            if (GLB.TITLEY!=null && GLB.TITLEY.trim().equals("")==false)   {
            	fileTitleY = GLB.TITLEY;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("Y") == true)
                {
                    beginPageY[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }

            if (GLB.PRINTZ_XSEEDWRITES != 0)
            {
                writesInPrintZ = (new Double(GLB.PRINTZ_XSEEDWRITES)).intValue();
                advanceOfPrintZ = GLB.ADVANCEZ;
                linecountOfPrintZ = (int) GLB.LINECOUNTZ;
                pagecountOfPrintZ = (int) GLB.PAGECOUNTZ;
                if ( GLB.LPZ !=null ) {
                    GLB.LPZ.close();
                }
                GLB.LPZ = new PrintStream(new FileOutputStream(GLB.LPZ_XSEEDTITLE ,true));
            }

            if (GLB.LPZ_XSEEDTITLE!=null && GLB.LPZ_XSEEDTITLE.trim().equals("")==false) {
            	filenameOfPrintZ = GLB.LPZ_XSEEDTITLE;
            }
            if (GLB.TITLEZ!=null && GLB.TITLEZ.trim().equals("")==false)   {
            	fileTitleZ = GLB.TITLEZ;
            }

            wIndex=0;
            for (int i = 1; i < GLB.BP.length; i++)
            {
                if (GLB.BP[i][SHADOW].equals("Z") == true)
                {
                    beginPageZ[wIndex] = GLB.BP[i][GLB.SEQ];
                    wIndex++;
                 }
            }



            reportName = GLB.REPNAME;
            initStn = GLB.INITSTN;
            startDate = new SimpleDateFormat("yyyyMMdd").format(new java.util.Date());
            startTime = new SimpleDateFormat("HHmmssSSS").format(new java.util.Date());

            open("OUTPUT");

            cpFile.write("<criticalPoint>");
            cpFile.write("   <reportName>" + reportName + "</reportName>");
            cpFile.write("   <initStn>" + initStn + "</initStn>");
            cpFile.write("   <startDate>" + format(startDate,"00000000") + "</startDate>");
            cpFile.write("   <startTime>" + format(startTime,"00000000") + "</startTime>");
            cpFile.write("   <cpCount>" + cpCount + "</cpCount>");
            cpFile.write("   <pid>" + pid + "</pid>");
            cpFile.write("   <cpBuffer>" + cpBuffer + "</cpBuffer>");
            cpFile.write("   <ipAddress>" + ipAddress + "</ipAddress>");

            cpFile.write("   <writesInExtractA>" + writesInExtractA + "</writesInExtractA>");
            cpFile.write("   <writesInExtractB>" + writesInExtractB + "</writesInExtractB>");
            cpFile.write("   <writesInExtractC>" + writesInExtractC + "</writesInExtractC>");
            cpFile.write("   <writesInExtractD>" + writesInExtractD + "</writesInExtractD>");
            cpFile.write("   <writesInExtractE>" + writesInExtractE + "</writesInExtractE>");
            cpFile.write("   <writesInExtractF>" + writesInExtractF + "</writesInExtractF>");
            cpFile.write("   <writesInExtractG>" + writesInExtractG + "</writesInExtractG>");
            cpFile.write("   <writesInExtractH>" + writesInExtractH + "</writesInExtractH>");
            cpFile.write("   <writesInExtractI>" + writesInExtractI + "</writesInExtractI>");
            cpFile.write("   <writesInExtractJ>" + writesInExtractJ + "</writesInExtractJ>");
            cpFile.write("   <writesInExtractK>" + writesInExtractK + "</writesInExtractK>");
            cpFile.write("   <writesInExtractL>" + writesInExtractL + "</writesInExtractL>");
            cpFile.write("   <writesInExtractM>" + writesInExtractM + "</writesInExtractM>");
            cpFile.write("   <writesInExtractN>" + writesInExtractN + "</writesInExtractN>");
            cpFile.write("   <writesInExtractO>" + writesInExtractO + "</writesInExtractO>");
            cpFile.write("   <writesInExtractP>" + writesInExtractP + "</writesInExtractP>");
            cpFile.write("   <writesInExtractQ>" + writesInExtractQ + "</writesInExtractQ>");
            cpFile.write("   <writesInExtractR>" + writesInExtractR + "</writesInExtractR>");
            cpFile.write("   <writesInExtractS>" + writesInExtractS + "</writesInExtractS>");
            cpFile.write("   <writesInExtractT>" + writesInExtractT + "</writesInExtractT>");
            cpFile.write("   <writesInExtractU>" + writesInExtractU + "</writesInExtractU>");
            cpFile.write("   <writesInExtractV>" + writesInExtractV + "</writesInExtractV>");
            cpFile.write("   <writesInExtractW>" + writesInExtractW + "</writesInExtractW>");
            cpFile.write("   <writesInExtractX>" + writesInExtractX + "</writesInExtractX>");
            cpFile.write("   <writesInExtractY>" + writesInExtractY + "</writesInExtractY>");
            cpFile.write("   <writesInExtractZ>" + writesInExtractZ + "</writesInExtractZ>");

            cpFile.write("   <filenameOfExtractA>" + filenameOfExtractA + "</filenameOfExtractA>");
            cpFile.write("   <filenameOfExtractB>" + filenameOfExtractB + "</filenameOfExtractB>");
            cpFile.write("   <filenameOfExtractC>" + filenameOfExtractC + "</filenameOfExtractC>");
            cpFile.write("   <filenameOfExtractD>" + filenameOfExtractD + "</filenameOfExtractD>");
            cpFile.write("   <filenameOfExtractE>" + filenameOfExtractE + "</filenameOfExtractE>");
            cpFile.write("   <filenameOfExtractF>" + filenameOfExtractF + "</filenameOfExtractF>");
            cpFile.write("   <filenameOfExtractG>" + filenameOfExtractG + "</filenameOfExtractG>");
            cpFile.write("   <filenameOfExtractH>" + filenameOfExtractH + "</filenameOfExtractH>");
            cpFile.write("   <filenameOfExtractI>" + filenameOfExtractI + "</filenameOfExtractI>");
            cpFile.write("   <filenameOfExtractJ>" + filenameOfExtractJ + "</filenameOfExtractJ>");
            cpFile.write("   <filenameOfExtractK>" + filenameOfExtractK + "</filenameOfExtractK>");
            cpFile.write("   <filenameOfExtractL>" + filenameOfExtractL + "</filenameOfExtractL>");
            cpFile.write("   <filenameOfExtractM>" + filenameOfExtractM + "</filenameOfExtractM>");
            cpFile.write("   <filenameOfExtractN>" + filenameOfExtractN + "</filenameOfExtractN>");
            cpFile.write("   <filenameOfExtractO>" + filenameOfExtractO + "</filenameOfExtractO>");
            cpFile.write("   <filenameOfExtractP>" + filenameOfExtractP + "</filenameOfExtractP>");
            cpFile.write("   <filenameOfExtractQ>" + filenameOfExtractQ + "</filenameOfExtractQ>");
            cpFile.write("   <filenameOfExtractR>" + filenameOfExtractR + "</filenameOfExtractR>");
            cpFile.write("   <filenameOfExtractS>" + filenameOfExtractS + "</filenameOfExtractS>");
            cpFile.write("   <filenameOfExtractT>" + filenameOfExtractT + "</filenameOfExtractT>");
            cpFile.write("   <filenameOfExtractU>" + filenameOfExtractU + "</filenameOfExtractU>");
            cpFile.write("   <filenameOfExtractV>" + filenameOfExtractV + "</filenameOfExtractV>");
            cpFile.write("   <filenameOfExtractW>" + filenameOfExtractW + "</filenameOfExtractW>");
            cpFile.write("   <filenameOfExtractX>" + filenameOfExtractX + "</filenameOfExtractX>");
            cpFile.write("   <filenameOfExtractY>" + filenameOfExtractY + "</filenameOfExtractY>");
            cpFile.write("   <filenameOfExtractZ>" + filenameOfExtractZ + "</filenameOfExtractZ>");

            if (advanceOfPrint == true)
            {   cpFile.write("   <advanceOfPrint>true</advanceOfPrint>");
            }
            else
            {   cpFile.write("   <advanceOfPrint>false</advanceOfPrint>");
            }
            if (advanceOfPrintA == true)
            {   cpFile.write("   <advanceOfPrintA>true</advanceOfPrintA>");
            }
            else
            {   cpFile.write("   <advanceOfPrintA>false</advanceOfPrintA>");
            }
            if (advanceOfPrintB == true)
            {   cpFile.write("   <advanceOfPrintB>true</advanceOfPrintB>");
            }
            else
            {   cpFile.write("   <advanceOfPrintB>false</advanceOfPrintB>");
            }
            if (advanceOfPrintC == true)
            {   cpFile.write("   <advanceOfPrintC>true</advanceOfPrintC>");
            }
            else
            {   cpFile.write("   <advanceOfPrintC>false</advanceOfPrintC>");
            }
            if (advanceOfPrintD == true)
            {   cpFile.write("   <advanceOfPrintD>true</advanceOfPrintD>");
            }
            else
            {   cpFile.write("   <advanceOfPrintD>false</advanceOfPrintD>");
            }
            if (advanceOfPrintE == true)
            {   cpFile.write("   <advanceOfPrintE>true</advanceOfPrintE>");
            }
            else
            {   cpFile.write("   <advanceOfPrintE>false</advanceOfPrintE>");
            }
            if (advanceOfPrintF == true)
            {   cpFile.write("   <advanceOfPrintF>true</advanceOfPrintF>");
            }
            else
            {   cpFile.write("   <advanceOfPrintF>false</advanceOfPrintF>");
            }
            if (advanceOfPrintG == true)
            {   cpFile.write("   <advanceOfPrintG>true</advanceOfPrintG>");
            }
            else
            {   cpFile.write("   <advanceOfPrintG>false</advanceOfPrintG>");
            }
            if (advanceOfPrintH == true)
            {   cpFile.write("   <advanceOfPrintH>true</advanceOfPrintH>");
            }
            else
            {   cpFile.write("   <advanceOfPrintH>false</advanceOfPrintH>");
            }
            if (advanceOfPrintI == true)
            {   cpFile.write("   <advanceOfPrintI>true</advanceOfPrintI>");
            }
            else
            {   cpFile.write("   <advanceOfPrintI>false</advanceOfPrintI>");
            }
            if (advanceOfPrintJ == true)
            {   cpFile.write("   <advanceOfPrintJ>true</advanceOfPrintJ>");
            }
            else
            {   cpFile.write("   <advanceOfPrintJ>false</advanceOfPrintJ>");
            }
            if (advanceOfPrintK == true)
            {   cpFile.write("   <advanceOfPrintK>true</advanceOfPrintK>");
            }
            else
            {   cpFile.write("   <advanceOfPrintK>false</advanceOfPrintK>");
            }
            if (advanceOfPrintL == true)
            {   cpFile.write("   <advanceOfPrintL>true</advanceOfPrintL>");
            }
            else
            {   cpFile.write("   <advanceOfPrintL>false</advanceOfPrintL>");
            }
            if (advanceOfPrintM == true)
            {   cpFile.write("   <advanceOfPrintM>true</advanceOfPrintM>");
            }
            else
            {   cpFile.write("   <advanceOfPrintM>false</advanceOfPrintM>");
            }
            if (advanceOfPrintN == true)
            {   cpFile.write("   <advanceOfPrintN>true</advanceOfPrintN>");
            }
            else
            {   cpFile.write("   <advanceOfPrintN>false</advanceOfPrintN>");
            }
            if (advanceOfPrintO == true)
            {   cpFile.write("   <advanceOfPrintO>true</advanceOfPrintO>");
            }
            else
            {   cpFile.write("   <advanceOfPrintO>false</advanceOfPrintO>");
            }
            if (advanceOfPrintP == true)
            {   cpFile.write("   <advanceOfPrintP>true</advanceOfPrintP>");
            }
            else
            {   cpFile.write("   <advanceOfPrintP>false</advanceOfPrintP>");
            }
            if (advanceOfPrintQ == true)
            {   cpFile.write("   <advanceOfPrintQ>true</advanceOfPrintQ>");
            }
            else
            {   cpFile.write("   <advanceOfPrintQ>false</advanceOfPrintQ>");
            }
            if (advanceOfPrintR == true)
            {   cpFile.write("   <advanceOfPrintR>true</advanceOfPrintR>");
            }
            else
            {   cpFile.write("   <advanceOfPrintR>false</advanceOfPrintR>");
            }
            if (advanceOfPrintS == true)
            {   cpFile.write("   <advanceOfPrintS>true</advanceOfPrintS>");
            }
            else
            {   cpFile.write("   <advanceOfPrintS>false</advanceOfPrintS>");
            }
            if (advanceOfPrintT == true)
            {   cpFile.write("   <advanceOfPrintT>true</advanceOfPrintT>");
            }
            else
            {   cpFile.write("   <advanceOfPrintT>false</advanceOfPrintT>");
            }
            if (advanceOfPrintU == true)
            {   cpFile.write("   <advanceOfPrintU>true</advanceOfPrintU>");
            }
            else
            {   cpFile.write("   <advanceOfPrintU>false</advanceOfPrintU>");
            }
            if (advanceOfPrintV == true)
            {   cpFile.write("   <advanceOfPrintV>true</advanceOfPrintV>");
            }
            else
            {   cpFile.write("   <advanceOfPrintV>false</advanceOfPrintV>");
            }
            if (advanceOfPrintW == true)
            {   cpFile.write("   <advanceOfPrintW>true</advanceOfPrintW>");
            }
            else
            {   cpFile.write("   <advanceOfPrintW>false</advanceOfPrintW>");
            }
            if (advanceOfPrintX == true)
            {   cpFile.write("   <advanceOfPrintX>true</advanceOfPrintX>");
            }
            else
            {   cpFile.write("   <advanceOfPrintX>false</advanceOfPrintX>");
            }
            if (advanceOfPrintY == true)
            {   cpFile.write("   <advanceOfPrintY>true</advanceOfPrintY>");
            }
            else
            {   cpFile.write("   <advanceOfPrintY>false</advanceOfPrintY>");
            }
            if (advanceOfPrintZ == true)
            {   cpFile.write("   <advanceOfPrintZ>true</advanceOfPrintZ>");
            }
            else
            {   cpFile.write("   <advanceOfPrintZ>false</advanceOfPrintZ>");
            }

            cpFile.write("   <linecountOfPrint>" + linecountOfPrint + "</linecountOfPrint>");
            cpFile.write("   <linecountOfPrintA>" + linecountOfPrintA + "</linecountOfPrintA>");
            cpFile.write("   <linecountOfPrintB>" + linecountOfPrintB + "</linecountOfPrintB>");
            cpFile.write("   <linecountOfPrintC>" + linecountOfPrintC + "</linecountOfPrintC>");
            cpFile.write("   <linecountOfPrintD>" + linecountOfPrintD + "</linecountOfPrintD>");
            cpFile.write("   <linecountOfPrintE>" + linecountOfPrintE + "</linecountOfPrintE>");
            cpFile.write("   <linecountOfPrintF>" + linecountOfPrintF + "</linecountOfPrintF>");
            cpFile.write("   <linecountOfPrintG>" + linecountOfPrintG + "</linecountOfPrintG>");
            cpFile.write("   <linecountOfPrintH>" + linecountOfPrintH + "</linecountOfPrintH>");
            cpFile.write("   <linecountOfPrintI>" + linecountOfPrintI + "</linecountOfPrintI>");
            cpFile.write("   <linecountOfPrintJ>" + linecountOfPrintJ + "</linecountOfPrintJ>");
            cpFile.write("   <linecountOfPrintK>" + linecountOfPrintK + "</linecountOfPrintK>");
            cpFile.write("   <linecountOfPrintL>" + linecountOfPrintL + "</linecountOfPrintL>");
            cpFile.write("   <linecountOfPrintM>" + linecountOfPrintM + "</linecountOfPrintM>");
            cpFile.write("   <linecountOfPrintN>" + linecountOfPrintN + "</linecountOfPrintN>");
            cpFile.write("   <linecountOfPrintO>" + linecountOfPrintO + "</linecountOfPrintO>");
            cpFile.write("   <linecountOfPrintP>" + linecountOfPrintP + "</linecountOfPrintP>");
            cpFile.write("   <linecountOfPrintQ>" + linecountOfPrintQ + "</linecountOfPrintQ>");
            cpFile.write("   <linecountOfPrintR>" + linecountOfPrintR + "</linecountOfPrintR>");
            cpFile.write("   <linecountOfPrintS>" + linecountOfPrintS + "</linecountOfPrintS>");
            cpFile.write("   <linecountOfPrintT>" + linecountOfPrintT + "</linecountOfPrintT>");
            cpFile.write("   <linecountOfPrintU>" + linecountOfPrintU + "</linecountOfPrintU>");
            cpFile.write("   <linecountOfPrintV>" + linecountOfPrintV + "</linecountOfPrintV>");
            cpFile.write("   <linecountOfPrintW>" + linecountOfPrintW + "</linecountOfPrintW>");
            cpFile.write("   <linecountOfPrintX>" + linecountOfPrintX + "</linecountOfPrintX>");
            cpFile.write("   <linecountOfPrintY>" + linecountOfPrintY + "</linecountOfPrintY>");
            cpFile.write("   <linecountOfPrintZ>" + linecountOfPrintZ + "</linecountOfPrintZ>");


            cpFile.write("   <pagecountOfPrint>" + pagecountOfPrint + "</pagecountOfPrint>");
            cpFile.write("   <pagecountOfPrintA>" + pagecountOfPrintA + "</pagecountOfPrintA>");
            cpFile.write("   <pagecountOfPrintB>" + pagecountOfPrintB + "</pagecountOfPrintB>");
            cpFile.write("   <pagecountOfPrintC>" + pagecountOfPrintC + "</pagecountOfPrintC>");
            cpFile.write("   <pagecountOfPrintD>" + pagecountOfPrintD + "</pagecountOfPrintD>");
            cpFile.write("   <pagecountOfPrintE>" + pagecountOfPrintE + "</pagecountOfPrintE>");
            cpFile.write("   <pagecountOfPrintF>" + pagecountOfPrintF + "</pagecountOfPrintF>");
            cpFile.write("   <pagecountOfPrintG>" + pagecountOfPrintG + "</pagecountOfPrintG>");
            cpFile.write("   <pagecountOfPrintH>" + pagecountOfPrintH + "</pagecountOfPrintH>");
            cpFile.write("   <pagecountOfPrintI>" + pagecountOfPrintI + "</pagecountOfPrintI>");
            cpFile.write("   <pagecountOfPrintJ>" + pagecountOfPrintJ + "</pagecountOfPrintJ>");
            cpFile.write("   <pagecountOfPrintK>" + pagecountOfPrintK + "</pagecountOfPrintK>");
            cpFile.write("   <pagecountOfPrintL>" + pagecountOfPrintL + "</pagecountOfPrintL>");
            cpFile.write("   <pagecountOfPrintM>" + pagecountOfPrintM + "</pagecountOfPrintM>");
            cpFile.write("   <pagecountOfPrintN>" + pagecountOfPrintN + "</pagecountOfPrintN>");
            cpFile.write("   <pagecountOfPrintO>" + pagecountOfPrintO + "</pagecountOfPrintO>");
            cpFile.write("   <pagecountOfPrintP>" + pagecountOfPrintP + "</pagecountOfPrintP>");
            cpFile.write("   <pagecountOfPrintQ>" + pagecountOfPrintQ + "</pagecountOfPrintQ>");
            cpFile.write("   <pagecountOfPrintR>" + pagecountOfPrintR + "</pagecountOfPrintR>");
            cpFile.write("   <pagecountOfPrintS>" + pagecountOfPrintS + "</pagecountOfPrintS>");
            cpFile.write("   <pagecountOfPrintT>" + pagecountOfPrintT + "</pagecountOfPrintT>");
            cpFile.write("   <pagecountOfPrintU>" + pagecountOfPrintU + "</pagecountOfPrintU>");
            cpFile.write("   <pagecountOfPrintV>" + pagecountOfPrintV + "</pagecountOfPrintV>");
            cpFile.write("   <pagecountOfPrintW>" + pagecountOfPrintW + "</pagecountOfPrintW>");
            cpFile.write("   <pagecountOfPrintX>" + pagecountOfPrintX + "</pagecountOfPrintX>");
            cpFile.write("   <pagecountOfPrintY>" + pagecountOfPrintY + "</pagecountOfPrintY>");
            cpFile.write("   <pagecountOfPrintZ>" + pagecountOfPrintZ + "</pagecountOfPrintZ>");
            cpFile.write("   <writesInPrint>" + writesInPrint + "</writesInPrint>");
            cpFile.write("   <writesInPrintA>" + writesInPrintA + "</writesInPrintA>");
            cpFile.write("   <writesInPrintB>" + writesInPrintB + "</writesInPrintB>");
            cpFile.write("   <writesInPrintC>" + writesInPrintC + "</writesInPrintC>");
            cpFile.write("   <writesInPrintD>" + writesInPrintD + "</writesInPrintD>");
            cpFile.write("   <writesInPrintE>" + writesInPrintE + "</writesInPrintE>");
            cpFile.write("   <writesInPrintF>" + writesInPrintF + "</writesInPrintF>");
            cpFile.write("   <writesInPrintG>" + writesInPrintG + "</writesInPrintG>");
            cpFile.write("   <writesInPrintH>" + writesInPrintH + "</writesInPrintH>");
            cpFile.write("   <writesInPrintI>" + writesInPrintI + "</writesInPrintI>");
            cpFile.write("   <writesInPrintJ>" + writesInPrintJ + "</writesInPrintJ>");
            cpFile.write("   <writesInPrintK>" + writesInPrintK + "</writesInPrintK>");
            cpFile.write("   <writesInPrintL>" + writesInPrintL + "</writesInPrintL>");
            cpFile.write("   <writesInPrintM>" + writesInPrintM + "</writesInPrintM>");
            cpFile.write("   <writesInPrintN>" + writesInPrintN + "</writesInPrintN>");
            cpFile.write("   <writesInPrintO>" + writesInPrintO + "</writesInPrintO>");
            cpFile.write("   <writesInPrintP>" + writesInPrintP + "</writesInPrintP>");
            cpFile.write("   <writesInPrintQ>" + writesInPrintQ + "</writesInPrintQ>");
            cpFile.write("   <writesInPrintR>" + writesInPrintR + "</writesInPrintR>");
            cpFile.write("   <writesInPrintS>" + writesInPrintS + "</writesInPrintS>");
            cpFile.write("   <writesInPrintT>" + writesInPrintT + "</writesInPrintT>");
            cpFile.write("   <writesInPrintU>" + writesInPrintU + "</writesInPrintU>");
            cpFile.write("   <writesInPrintV>" + writesInPrintV + "</writesInPrintV>");
            cpFile.write("   <writesInPrintW>" + writesInPrintW + "</writesInPrintW>");
            cpFile.write("   <writesInPrintX>" + writesInPrintX + "</writesInPrintX>");
            cpFile.write("   <writesInPrintY>" + writesInPrintY + "</writesInPrintY>");
            cpFile.write("   <writesInPrintZ>" + writesInPrintZ + "</writesInPrintZ>");

            cpFile.write("   <filenameOfPrint>" + filenameOfPrint + "</filenameOfPrint>");
            cpFile.write("   <filenameOfPrintA>" + filenameOfPrintA + "</filenameOfPrintA>");
            cpFile.write("   <filenameOfPrintB>" + filenameOfPrintB + "</filenameOfPrintB>");
            cpFile.write("   <filenameOfPrintC>" + filenameOfPrintC + "</filenameOfPrintC>");
            cpFile.write("   <filenameOfPrintD>" + filenameOfPrintD + "</filenameOfPrintD>");
            cpFile.write("   <filenameOfPrintE>" + filenameOfPrintE + "</filenameOfPrintE>");
            cpFile.write("   <filenameOfPrintF>" + filenameOfPrintF + "</filenameOfPrintF>");
            cpFile.write("   <filenameOfPrintG>" + filenameOfPrintG + "</filenameOfPrintG>");
            cpFile.write("   <filenameOfPrintH>" + filenameOfPrintH + "</filenameOfPrintH>");
            cpFile.write("   <filenameOfPrintI>" + filenameOfPrintI + "</filenameOfPrintI>");
            cpFile.write("   <filenameOfPrintJ>" + filenameOfPrintJ + "</filenameOfPrintJ>");
            cpFile.write("   <filenameOfPrintK>" + filenameOfPrintK + "</filenameOfPrintK>");
            cpFile.write("   <filenameOfPrintL>" + filenameOfPrintL + "</filenameOfPrintL>");
            cpFile.write("   <filenameOfPrintM>" + filenameOfPrintM + "</filenameOfPrintM>");
            cpFile.write("   <filenameOfPrintN>" + filenameOfPrintN + "</filenameOfPrintN>");
            cpFile.write("   <filenameOfPrintO>" + filenameOfPrintO + "</filenameOfPrintO>");
            cpFile.write("   <filenameOfPrintP>" + filenameOfPrintP + "</filenameOfPrintP>");
            cpFile.write("   <filenameOfPrintQ>" + filenameOfPrintQ + "</filenameOfPrintQ>");
            cpFile.write("   <filenameOfPrintR>" + filenameOfPrintR + "</filenameOfPrintR>");
            cpFile.write("   <filenameOfPrintS>" + filenameOfPrintS + "</filenameOfPrintS>");
            cpFile.write("   <filenameOfPrintT>" + filenameOfPrintT + "</filenameOfPrintT>");
            cpFile.write("   <filenameOfPrintU>" + filenameOfPrintU + "</filenameOfPrintU>");
            cpFile.write("   <filenameOfPrintV>" + filenameOfPrintV + "</filenameOfPrintV>");
            cpFile.write("   <filenameOfPrintW>" + filenameOfPrintW + "</filenameOfPrintW>");
            cpFile.write("   <filenameOfPrintX>" + filenameOfPrintX + "</filenameOfPrintX>");
            cpFile.write("   <filenameOfPrintY>" + filenameOfPrintY + "</filenameOfPrintY>");
            cpFile.write("   <filenameOfPrintZ>" + filenameOfPrintZ + "</filenameOfPrintZ>");

            cpFile.write("   <fileTitle>" + fileTitle + "</fileTitle>");
            cpFile.write("   <fileTitleA>" + fileTitleA + "</fileTitleA>");
            cpFile.write("   <fileTitleB>" + fileTitleB + "</fileTitleB>");
            cpFile.write("   <fileTitleC>" + fileTitleC + "</fileTitleC>");
            cpFile.write("   <fileTitleD>" + fileTitleD + "</fileTitleD>");
            cpFile.write("   <fileTitleE>" + fileTitleE + "</fileTitleE>");
            cpFile.write("   <fileTitleF>" + fileTitleF + "</fileTitleF>");
            cpFile.write("   <fileTitleG>" + fileTitleG + "</fileTitleG>");
            cpFile.write("   <fileTitleH>" + fileTitleH + "</fileTitleH>");
            cpFile.write("   <fileTitleI>" + fileTitleI + "</fileTitleI>");
            cpFile.write("   <fileTitleJ>" + fileTitleJ + "</fileTitleJ>");
            cpFile.write("   <fileTitleK>" + fileTitleK + "</fileTitleK>");
            cpFile.write("   <fileTitleL>" + fileTitleL + "</fileTitleL>");
            cpFile.write("   <fileTitleM>" + fileTitleM + "</fileTitleM>");
            cpFile.write("   <fileTitleN>" + fileTitleN + "</fileTitleN>");
            cpFile.write("   <fileTitleO>" + fileTitleO + "</fileTitleO>");
            cpFile.write("   <fileTitleP>" + fileTitleP + "</fileTitleP>");
            cpFile.write("   <fileTitleQ>" + fileTitleQ + "</fileTitleQ>");
            cpFile.write("   <fileTitleR>" + fileTitleR + "</fileTitleR>");
            cpFile.write("   <fileTitleS>" + fileTitleS + "</fileTitleS>");
            cpFile.write("   <fileTitleT>" + fileTitleT + "</fileTitleT>");
            cpFile.write("   <fileTitleU>" + fileTitleU + "</fileTitleU>");
            cpFile.write("   <fileTitleV>" + fileTitleV + "</fileTitleV>");
            cpFile.write("   <fileTitleW>" + fileTitleW + "</fileTitleW>");
            cpFile.write("   <fileTitleX>" + fileTitleX + "</fileTitleX>");
            cpFile.write("   <fileTitleY>" + fileTitleY + "</fileTitleY>");
            cpFile.write("   <fileTitleZ>" + fileTitleZ + "</fileTitleZ>");


            cpFile.write("   <beginPage>" + writeBeginPage(beginPage) + "</beginPage>");
            cpFile.write("   <beginPageA>" + writeBeginPage(beginPageA) + "</beginPageA>");
            cpFile.write("   <beginPageB>" + writeBeginPage(beginPageB) + "</beginPageB>");
            cpFile.write("   <beginPageC>" + writeBeginPage(beginPageC) + "</beginPageC>");
            cpFile.write("   <beginPageD>" + writeBeginPage(beginPageD) + "</beginPageD>");
            cpFile.write("   <beginPageE>" + writeBeginPage(beginPageE) + "</beginPageE>");
            cpFile.write("   <beginPageF>" + writeBeginPage(beginPageF) + "</beginPageF>");
            cpFile.write("   <beginPageG>" + writeBeginPage(beginPageG) + "</beginPageG>");
            cpFile.write("   <beginPageH>" + writeBeginPage(beginPageH) + "</beginPageH>");
            cpFile.write("   <beginPageI>" + writeBeginPage(beginPageI) + "</beginPageI>");
            cpFile.write("   <beginPageJ>" + writeBeginPage(beginPageJ) + "</beginPageJ>");
            cpFile.write("   <beginPageK>" + writeBeginPage(beginPageK) + "</beginPageK>");
            cpFile.write("   <beginPageL>" + writeBeginPage(beginPageL) + "</beginPageL>");
            cpFile.write("   <beginPageM>" + writeBeginPage(beginPageM) + "</beginPageM>");
            cpFile.write("   <beginPageN>" + writeBeginPage(beginPageN) + "</beginPageN>");
            cpFile.write("   <beginPageO>" + writeBeginPage(beginPageO) + "</beginPageO>");
            cpFile.write("   <beginPageP>" + writeBeginPage(beginPageP) + "</beginPageP>");
            cpFile.write("   <beginPageQ>" + writeBeginPage(beginPageQ) + "</beginPageQ>");
            cpFile.write("   <beginPageR>" + writeBeginPage(beginPageR) + "</beginPageR>");
            cpFile.write("   <beginPageS>" + writeBeginPage(beginPageS) + "</beginPageS>");
            cpFile.write("   <beginPageT>" + writeBeginPage(beginPageT) + "</beginPageT>");
            cpFile.write("   <beginPageU>" + writeBeginPage(beginPageU) + "</beginPageU>");
            cpFile.write("   <beginPageV>" + writeBeginPage(beginPageV) + "</beginPageV>");
            cpFile.write("   <beginPageW>" + writeBeginPage(beginPageW) + "</beginPageW>");
            cpFile.write("   <beginPageX>" + writeBeginPage(beginPageX) + "</beginPageX>");
            cpFile.write("   <beginPageY>" + writeBeginPage(beginPageY) + "</beginPageY>");
            cpFile.write("   <beginPageZ>" + writeBeginPage(beginPageZ) + "</beginPageZ>");


            cpFile.write("</criticalPoint>");
            if (cpFile.out.checkError()) { abort("Error on File : CP File","CP");  }
            close();

    }

    private String writeBeginPage(String [] pBP)
    {

          String wStrBP = "";
          String wVirgula ="";
          for (int i=0;i<pBP.length;i++)
          {  if  (pBP[i].equals("")==false)
             {    wStrBP = wStrBP + wVirgula + pBP[i];
                  wVirgula = ",";
             }
          }
          return(wStrBP);
    }
	private void initialize()
	{
        reportName = "";
        initStn    = "";
        startDate  = "";
        startTime  = "";
        cpCount       = 0;
        pid        = "0";
        cpBuffer   = "";
        ipAddress  = "";

        //Extracts
        writesInExtractA = 0;
        writesInExtractB = 0;
        writesInExtractC = 0;
        writesInExtractD = 0;
        writesInExtractE = 0;
        writesInExtractF = 0;
        writesInExtractG = 0;
        writesInExtractH = 0;
        writesInExtractI = 0;
        writesInExtractJ = 0;
        writesInExtractK = 0;
        writesInExtractL = 0;
        writesInExtractM = 0;
        writesInExtractN = 0;
        writesInExtractO = 0;
        writesInExtractP = 0;
        writesInExtractQ = 0;
        writesInExtractR = 0;
        writesInExtractS = 0;
        writesInExtractT = 0;
        writesInExtractU = 0;
        writesInExtractV = 0;
        writesInExtractW = 0;
        writesInExtractY = 0;
        writesInExtractX = 0;
        writesInExtractZ = 0;

        filenameOfExtractA = "";
        filenameOfExtractB = "";
        filenameOfExtractC = "";
        filenameOfExtractD = "";
        filenameOfExtractE = "";
        filenameOfExtractF = "";
        filenameOfExtractG = "";
        filenameOfExtractH = "";
        filenameOfExtractI = "";
        filenameOfExtractJ = "";
        filenameOfExtractK = "";
        filenameOfExtractL = "";
        filenameOfExtractM = "";
        filenameOfExtractN = "";
        filenameOfExtractO = "";
        filenameOfExtractP = "";
        filenameOfExtractQ = "";
        filenameOfExtractR = "";
        filenameOfExtractS = "";
        filenameOfExtractT = "";
        filenameOfExtractU = "";
        filenameOfExtractV = "";
        filenameOfExtractW = "";
        filenameOfExtractY = "";
        filenameOfExtractX = "";
        filenameOfExtractZ = "";

        //prints
        advanceOfPrint  = false;
        advanceOfPrintA = false;
        advanceOfPrintB = false;
        advanceOfPrintC = false;
        advanceOfPrintD = false;
        advanceOfPrintE = false;
        advanceOfPrintF = false;
        advanceOfPrintG = false;
        advanceOfPrintH = false;
        advanceOfPrintI = false;
        advanceOfPrintJ = false;
        advanceOfPrintK = false;
        advanceOfPrintL = false;
        advanceOfPrintM = false;
        advanceOfPrintN = false;
        advanceOfPrintO = false;
        advanceOfPrintP = false;
        advanceOfPrintQ = false;
        advanceOfPrintR = false;
        advanceOfPrintS = false;
        advanceOfPrintT = false;
        advanceOfPrintU = false;
        advanceOfPrintV = false;
        advanceOfPrintW = false;
        advanceOfPrintX = false;
        advanceOfPrintY = false;
        advanceOfPrintZ = false;

        linecountOfPrint = 0;
        linecountOfPrintA = 0;
        linecountOfPrintB = 0;
        linecountOfPrintC = 0;
        linecountOfPrintD = 0;
        linecountOfPrintE = 0;
        linecountOfPrintF = 0;
        linecountOfPrintG = 0;
        linecountOfPrintH = 0;
        linecountOfPrintI = 0;
        linecountOfPrintJ = 0;
        linecountOfPrintK = 0;
        linecountOfPrintL = 0;
        linecountOfPrintM = 0;
        linecountOfPrintN = 0;
        linecountOfPrintO = 0;
        linecountOfPrintP = 0;
        linecountOfPrintQ = 0;
        linecountOfPrintR = 0;
        linecountOfPrintS = 0;
        linecountOfPrintT = 0;
        linecountOfPrintU = 0;
        linecountOfPrintV = 0;
        linecountOfPrintW = 0;
        linecountOfPrintY = 0;
        linecountOfPrintX = 0;
        linecountOfPrintZ = 0;

        pagecountOfPrint = 0;
        pagecountOfPrintA = 0;
        pagecountOfPrintB = 0;
        pagecountOfPrintC = 0;
        pagecountOfPrintD = 0;
        pagecountOfPrintE = 0;
        pagecountOfPrintF = 0;
        pagecountOfPrintG = 0;
        pagecountOfPrintH = 0;
        pagecountOfPrintI = 0;
        pagecountOfPrintJ = 0;
        pagecountOfPrintK = 0;
        pagecountOfPrintL = 0;
        pagecountOfPrintM = 0;
        pagecountOfPrintN = 0;
        pagecountOfPrintO = 0;
        pagecountOfPrintP = 0;
        pagecountOfPrintQ = 0;
        pagecountOfPrintR = 0;
        pagecountOfPrintS = 0;
        pagecountOfPrintT = 0;
        pagecountOfPrintU = 0;
        pagecountOfPrintV = 0;
        pagecountOfPrintW = 0;
        pagecountOfPrintY = 0;
        pagecountOfPrintX = 0;
        pagecountOfPrintZ = 0;


        writesInPrint = 0;
        writesInPrintA = 0;
        writesInPrintB = 0;
        writesInPrintC = 0;
        writesInPrintD = 0;
        writesInPrintE = 0;
        writesInPrintF = 0;
        writesInPrintG = 0;
        writesInPrintH = 0;
        writesInPrintI = 0;
        writesInPrintJ = 0;
        writesInPrintK = 0;
        writesInPrintL = 0;
        writesInPrintM = 0;
        writesInPrintN = 0;
        writesInPrintO = 0;
        writesInPrintP = 0;
        writesInPrintQ = 0;
        writesInPrintR = 0;
        writesInPrintS = 0;
        writesInPrintT = 0;
        writesInPrintU = 0;
        writesInPrintV = 0;
        writesInPrintW = 0;
        writesInPrintY = 0;
        writesInPrintX = 0;
        writesInPrintZ = 0;

        filenameOfPrint  = "";
        filenameOfPrintA = "";
        filenameOfPrintB = "";
        filenameOfPrintC = "";
        filenameOfPrintD = "";
        filenameOfPrintE = "";
        filenameOfPrintF = "";
        filenameOfPrintG = "";
        filenameOfPrintH = "";
        filenameOfPrintI = "";
        filenameOfPrintJ = "";
        filenameOfPrintK = "";
        filenameOfPrintL = "";
        filenameOfPrintM = "";
        filenameOfPrintN = "";
        filenameOfPrintO = "";
        filenameOfPrintP = "";
        filenameOfPrintQ = "";
        filenameOfPrintR = "";
        filenameOfPrintS = "";
        filenameOfPrintT = "";
        filenameOfPrintU = "";
        filenameOfPrintV = "";
        filenameOfPrintW = "";
        filenameOfPrintY = "";
        filenameOfPrintX = "";
        filenameOfPrintZ = "";

        fileTitle  = "";
        fileTitleA = "";
        fileTitleB = "";
        fileTitleC = "";
        fileTitleD = "";
        fileTitleE = "";
        fileTitleF = "";
        fileTitleG = "";
        fileTitleH = "";
        fileTitleI = "";
        fileTitleJ = "";
        fileTitleK = "";
        fileTitleL = "";
        fileTitleM = "";
        fileTitleN = "";
        fileTitleO = "";
        fileTitleP = "";
        fileTitleQ = "";
        fileTitleR = "";
        fileTitleS = "";
        fileTitleT = "";
        fileTitleU = "";
        fileTitleV = "";
        fileTitleW = "";
        fileTitleY = "";
        fileTitleX = "";
        fileTitleZ = "";

        initBPArray(beginPage);
        initBPArray(beginPageA);
        initBPArray(beginPageB);
        initBPArray(beginPageC);
        initBPArray(beginPageD);
        initBPArray(beginPageE);
        initBPArray(beginPageF);
        initBPArray(beginPageG);
        initBPArray(beginPageH);
        initBPArray(beginPageI);
        initBPArray(beginPageJ);
        initBPArray(beginPageK);
        initBPArray(beginPageL);
        initBPArray(beginPageM);
        initBPArray(beginPageN);
        initBPArray(beginPageO);
        initBPArray(beginPageP);
        initBPArray(beginPageQ);
        initBPArray(beginPageR);
        initBPArray(beginPageS);
        initBPArray(beginPageT);
        initBPArray(beginPageU);
        initBPArray(beginPageV);
        initBPArray(beginPageW);
        initBPArray(beginPageZ);
        initBPArray(beginPageX);
        initBPArray(beginPageY);
        initBPArray(beginPageZ);

    }

    public void initBPArray(String pBP[])
    {

        for (int i = 0; i < pBP.length; i++)
        {
            pBP[i] = "";
        }
    }


	public boolean check()
        throws Exception
	{
	    String cpFileName ="";
        GLB.RECOVERID = GLB.RECOVERID.trim();
        if (GLB.RECOVERID.equals("")==false)
        {
            cpFileName = GLB.TEMPDIR + File.separator + GLB.REPNAME + "_" + GLB.RECOVERID + ".CP";
        }
        else
        {
            cpFileName = GLB.TEMPDIR + File.separator + GLB.REPNAME + ".CP";
        }

        if(XseedFunctions.FileExists(cpFileName)==true)
        {
            return(true);
        }
        return(false);
    }

    public void recover()
        throws Exception
    {
        read();


        if (filenameOfExtractA.trim().equals("")==false)
        {
            GLB.EXA_XSEEDTITLE = filenameOfExtractA.trim();
            GLB.EXA_FILENAME = GLB.EXA_XSEEDTITLE;
        }
        if (filenameOfExtractB.trim().equals("")==false)
        {
            GLB.EXB_XSEEDTITLE = filenameOfExtractB.trim();
            GLB.EXB_FILENAME = GLB.EXB_XSEEDTITLE;
        }
        if (filenameOfExtractC.trim().equals("")==false)
        {
            GLB.EXC_XSEEDTITLE = filenameOfExtractC.trim();
            GLB.EXC_FILENAME = GLB.EXC_XSEEDTITLE;
        }
        if (filenameOfExtractD.trim().equals("")==false)
        {
            GLB.EXD_XSEEDTITLE = filenameOfExtractD.trim();
            GLB.EXD_FILENAME = GLB.EXD_XSEEDTITLE;
        }
        if (filenameOfExtractE.trim().equals("")==false)
        {
            GLB.EXE_XSEEDTITLE = filenameOfExtractE.trim();
            GLB.EXE_FILENAME = GLB.EXE_XSEEDTITLE;
        }
        if (filenameOfExtractF.trim().equals("")==false)
        {
            GLB.EXF_XSEEDTITLE = filenameOfExtractF.trim();
            GLB.EXF_FILENAME = GLB.EXF_XSEEDTITLE;
        }
        if (filenameOfExtractG.trim().equals("")==false)
        {
            GLB.EXG_XSEEDTITLE = filenameOfExtractG.trim();
            GLB.EXG_FILENAME = GLB.EXG_XSEEDTITLE;
        }
        if (filenameOfExtractH.trim().equals("")==false)
        {
            GLB.EXH_XSEEDTITLE = filenameOfExtractH.trim();
            GLB.EXH_FILENAME = GLB.EXH_XSEEDTITLE;
        }
        if (filenameOfExtractI.trim().equals("")==false)
        {
            GLB.EXI_XSEEDTITLE = filenameOfExtractI.trim();
            GLB.EXI_FILENAME = GLB.EXI_XSEEDTITLE;
        }
        if (filenameOfExtractJ.trim().equals("")==false)
        {
            GLB.EXJ_XSEEDTITLE = filenameOfExtractJ.trim();
            GLB.EXJ_FILENAME = GLB.EXJ_XSEEDTITLE;
        }
        if (filenameOfExtractK.trim().equals("")==false)
        {
            GLB.EXK_XSEEDTITLE = filenameOfExtractK.trim();
            GLB.EXK_FILENAME = GLB.EXK_XSEEDTITLE;
        }
        if (filenameOfExtractL.trim().equals("")==false)
        {
            GLB.EXL_XSEEDTITLE = filenameOfExtractL.trim();
            GLB.EXL_FILENAME = GLB.EXL_XSEEDTITLE;
        }
        if (filenameOfExtractM.trim().equals("")==false)
        {
            GLB.EXM_XSEEDTITLE = filenameOfExtractM.trim();
            GLB.EXM_FILENAME = GLB.EXM_XSEEDTITLE;
        }
        if (filenameOfExtractN.trim().equals("")==false)
        {
            GLB.EXN_XSEEDTITLE = filenameOfExtractN.trim();
            GLB.EXN_FILENAME = GLB.EXN_XSEEDTITLE;
        }
        if (filenameOfExtractO.trim().equals("")==false)
        {
            GLB.EXO_XSEEDTITLE = filenameOfExtractO.trim();
            GLB.EXO_FILENAME = GLB.EXO_XSEEDTITLE;
        }
        if (filenameOfExtractP.trim().equals("")==false)
        {
            GLB.EXP_XSEEDTITLE = filenameOfExtractP.trim();
            GLB.EXP_FILENAME = GLB.EXP_XSEEDTITLE;
        }
        if (filenameOfExtractQ.trim().equals("")==false)
        {
            GLB.EXQ_XSEEDTITLE = filenameOfExtractQ.trim();
            GLB.EXQ_FILENAME = GLB.EXQ_XSEEDTITLE;
        }
        if (filenameOfExtractR.trim().equals("")==false)
        {
            GLB.EXR_XSEEDTITLE = filenameOfExtractR.trim();
            GLB.EXR_FILENAME = GLB.EXR_XSEEDTITLE;
        }
        if (filenameOfExtractS.trim().equals("")==false)
        {
            GLB.EXS_XSEEDTITLE = filenameOfExtractS.trim();
            GLB.EXS_FILENAME = GLB.EXS_XSEEDTITLE;
        }
        if (filenameOfExtractT.trim().equals("")==false)
        {
            GLB.EXT_XSEEDTITLE = filenameOfExtractT.trim();
            GLB.EXT_FILENAME = GLB.EXT_XSEEDTITLE;
        }
        if (filenameOfExtractU.trim().equals("")==false)
        {
            GLB.EXU_XSEEDTITLE = filenameOfExtractU.trim();
            GLB.EXU_FILENAME = GLB.EXU_XSEEDTITLE;
        }
        if (filenameOfExtractV.trim().equals("")==false)
        {
            GLB.EXV_XSEEDTITLE = filenameOfExtractV.trim();
            GLB.EXV_FILENAME = GLB.EXV_XSEEDTITLE;
        }
        if (filenameOfExtractW.trim().equals("")==false)
        {
            GLB.EXW_XSEEDTITLE = filenameOfExtractW.trim();
            GLB.EXW_FILENAME = GLB.EXW_XSEEDTITLE;
        }
        if (filenameOfExtractX.trim().equals("")==false)
        {
            GLB.EXX_XSEEDTITLE = filenameOfExtractX.trim();
            GLB.EXX_FILENAME = GLB.EXX_XSEEDTITLE;
        }
        if (filenameOfExtractY.trim().equals("")==false)
        {
            GLB.EXY_XSEEDTITLE = filenameOfExtractY.trim();
            GLB.EXY_FILENAME = GLB.EXY_XSEEDTITLE;
        }
        if (filenameOfExtractZ.trim().equals("")==false)
        {
            GLB.EXZ_XSEEDTITLE = filenameOfExtractZ.trim();
            GLB.EXZ_FILENAME = GLB.EXZ_XSEEDTITLE;
        }

        if(filenameOfPrint.trim().equals("") == false)
        {
            GLB.LP_XSEEDTITLE = filenameOfPrint;
        }
        if(filenameOfPrintA.trim().equals("") == false)
        {
            GLB.LPA_XSEEDTITLE = filenameOfPrintA;
        }
        if(filenameOfPrintB.trim().equals("") == false)
        {
            GLB.LPB_XSEEDTITLE = filenameOfPrintB;
        }
        if(filenameOfPrintC.trim().equals("") == false)
        {
            GLB.LPC_XSEEDTITLE = filenameOfPrintC;
        }
        if(filenameOfPrintD.trim().equals("") == false)
        {
            GLB.LPD_XSEEDTITLE = filenameOfPrintD;
        }
        if(filenameOfPrintE.trim().equals("") == false)
        {
            GLB.LPE_XSEEDTITLE = filenameOfPrintE;
        }
        if(filenameOfPrintF.trim().equals("") == false)
        {
            GLB.LPF_XSEEDTITLE = filenameOfPrintF;
        }
        if(filenameOfPrintG.trim().equals("") == false)
        {
            GLB.LPG_XSEEDTITLE = filenameOfPrintG;
        }
        if(filenameOfPrintH.trim().equals("") == false)
        {
            GLB.LPH_XSEEDTITLE = filenameOfPrintH;
        }
        if(filenameOfPrintI.trim().equals("") == false)
        {
            GLB.LPI_XSEEDTITLE = filenameOfPrintI;
        }
        if(filenameOfPrintJ.trim().equals("") == false)
        {
            GLB.LPJ_XSEEDTITLE = filenameOfPrintJ;
        }
        if(filenameOfPrintK.trim().equals("") == false)
        {
            GLB.LPK_XSEEDTITLE = filenameOfPrintK;
        }
        if(filenameOfPrintL.trim().equals("") == false)
        {
            GLB.LPL_XSEEDTITLE = filenameOfPrintL;
        }
        if(filenameOfPrintM.trim().equals("") == false)
        {
            GLB.LPM_XSEEDTITLE = filenameOfPrintM;
        }
        if(filenameOfPrintN.trim().equals("") == false)
        {
            GLB.LPN_XSEEDTITLE = filenameOfPrintN;
        }
        if(filenameOfPrintO.trim().equals("") == false)
        {
            GLB.LPO_XSEEDTITLE = filenameOfPrintO;
        }
        if(filenameOfPrintP.trim().equals("") == false)
        {
            GLB.LPP_XSEEDTITLE = filenameOfPrintP;
        }
        if(filenameOfPrintQ.trim().equals("") == false)
        {
            GLB.LPQ_XSEEDTITLE = filenameOfPrintQ;
        }
        if(filenameOfPrintR.trim().equals("") == false)
        {
            GLB.LPR_XSEEDTITLE = filenameOfPrintR;
        }
        if(filenameOfPrintS.trim().equals("") == false)
        {
            GLB.LPS_XSEEDTITLE = filenameOfPrintS;
        }
        if(filenameOfPrintT.trim().equals("") == false)
        {
            GLB.LPT_XSEEDTITLE = filenameOfPrintT;
        }
        if(filenameOfPrintU.trim().equals("") == false)
        {
            GLB.LPU_XSEEDTITLE = filenameOfPrintU;
        }
        if(filenameOfPrintV.trim().equals("") == false)
        {
            GLB.LPV_XSEEDTITLE = filenameOfPrintV;
        }
        if(filenameOfPrintW.trim().equals("") == false)
        {
            GLB.LPW_XSEEDTITLE = filenameOfPrintW;
        }
        if(filenameOfPrintX.trim().equals("") == false)
        {
            GLB.LPX_XSEEDTITLE = filenameOfPrintX;
        }
        if(filenameOfPrintY.trim().equals("") == false)
        {
            GLB.LPY_XSEEDTITLE = filenameOfPrintY;
        }
        if(filenameOfPrintZ.trim().equals("") == false)
        {
            GLB.LPZ_XSEEDTITLE = filenameOfPrintZ;
        }

        ///////////////////FILETITLE
        if(fileTitle.trim().equals("") == false)
        {
            GLB.TITLE = fileTitle;
        }
        if(fileTitleA.trim().equals("") == false)
        {
            GLB.TITLEA = fileTitleA;
        }
        if(fileTitleB.trim().equals("") == false)
        {
            GLB.TITLEB = fileTitleB;
        }
        if(fileTitleC.trim().equals("") == false)
        {
            GLB.TITLEC = fileTitleC;
        }
        if(fileTitleD.trim().equals("") == false)
        {
            GLB.TITLED = fileTitleD;
        }
        if(fileTitleE.trim().equals("") == false)
        {
            GLB.TITLEE = fileTitleE;
        }
        if(fileTitleF.trim().equals("") == false)
        {
            GLB.TITLEF = fileTitleF;
        }
        if(fileTitleG.trim().equals("") == false)
        {
            GLB.TITLEG = fileTitleG;
        }
        if(fileTitleH.trim().equals("") == false)
        {
            GLB.TITLEH = fileTitleH;
        }
        if(fileTitleI.trim().equals("") == false)
        {
            GLB.TITLEI = fileTitleI;
        }
        if(fileTitleJ.trim().equals("") == false)
        {
            GLB.TITLEJ = fileTitleJ;
        }
        if(fileTitleK.trim().equals("") == false)
        {
            GLB.TITLEK = fileTitleK;
        }
        if(fileTitleL.trim().equals("") == false)
        {
            GLB.TITLEL = fileTitleL;
        }
        if(fileTitleM.trim().equals("") == false)
        {
            GLB.TITLEM = fileTitleM;
        }
        if(fileTitleN.trim().equals("") == false)
        {
            GLB.TITLEN = fileTitleN;
        }
        if(fileTitleO.trim().equals("") == false)
        {
            GLB.TITLEO = fileTitleO;
        }
        if(fileTitleP.trim().equals("") == false)
        {
            GLB.TITLEP = fileTitleP;
        }
        if(fileTitleQ.trim().equals("") == false)
        {
            GLB.TITLEQ = fileTitleQ;
        }
        if(fileTitleR.trim().equals("") == false)
        {
            GLB.TITLER = fileTitleR;
        }
        if(fileTitleS.trim().equals("") == false)
        {
            GLB.TITLES = fileTitleS;
        }
        if(fileTitleT.trim().equals("") == false)
        {
            GLB.TITLET = fileTitleT;
        }
        if(fileTitleU.trim().equals("") == false)
        {
            GLB.TITLEU = fileTitleU;
        }
        if(fileTitleV.trim().equals("") == false)
        {
            GLB.TITLEV = fileTitleV;
        }
        if(fileTitleW.trim().equals("") == false)
        {
            GLB.TITLEW = fileTitleW;
        }
        if(fileTitleX.trim().equals("") == false)
        {
            GLB.TITLEX = fileTitleX;
        }
        if(fileTitleY.trim().equals("") == false)
        {
            GLB.TITLEY = fileTitleY;
        }
        if(fileTitleZ.trim().equals("") == false)
        {
            GLB.TITLEZ = fileTitleZ;
        }
        ///////////////////////////



        if (writesInExtractA != 0)
        {
            GLB.EXA_XSEEDWRITES = writesInExtractA;
            recoverFile(filenameOfExtractA,writesInExtractA);
        }
        if (writesInExtractB != 0)
        {
            GLB.EXB_XSEEDWRITES = writesInExtractB;
            recoverFile(filenameOfExtractB,writesInExtractB);
        }
        if (writesInExtractC != 0)
        {
            GLB.EXC_XSEEDWRITES = writesInExtractC;
            recoverFile(filenameOfExtractC,writesInExtractC);
        }
        if (writesInExtractD != 0)
        {
            GLB.EXD_XSEEDWRITES = writesInExtractD;
            recoverFile(filenameOfExtractD,writesInExtractD);
        }
        if (writesInExtractE != 0)
        {
            GLB.EXE_XSEEDWRITES = writesInExtractE;
            recoverFile(filenameOfExtractE,writesInExtractE);
        }
        if (writesInExtractF != 0)
        {
            GLB.EXF_XSEEDWRITES = writesInExtractF;
            recoverFile(filenameOfExtractF,writesInExtractF);
        }
        if (writesInExtractG != 0)
        {
            GLB.EXG_XSEEDWRITES = writesInExtractG;
            recoverFile(filenameOfExtractG,writesInExtractG);
        }
        if (writesInExtractH != 0)
        {
            GLB.EXH_XSEEDWRITES = writesInExtractH;
            recoverFile(filenameOfExtractH,writesInExtractH);
        }
        if (writesInExtractI != 0)
        {
            GLB.EXI_XSEEDWRITES = writesInExtractI;
            recoverFile(filenameOfExtractI,writesInExtractI);
        }
        if (writesInExtractJ != 0)
        {
            GLB.EXJ_XSEEDWRITES = writesInExtractJ;
            recoverFile(filenameOfExtractJ,writesInExtractJ);
        }
        if (writesInExtractK != 0)
        {
            GLB.EXK_XSEEDWRITES = writesInExtractK;
            recoverFile(filenameOfExtractK,writesInExtractK);
        }
        if (writesInExtractL != 0)
        {
            GLB.EXL_XSEEDWRITES = writesInExtractL;
            recoverFile(filenameOfExtractL,writesInExtractL);
        }
        if (writesInExtractM != 0)
        {
            GLB.EXM_XSEEDWRITES = writesInExtractM;
            recoverFile(filenameOfExtractM,writesInExtractM);
        }
        if (writesInExtractN != 0)
        {
            GLB.EXN_XSEEDWRITES = writesInExtractN;
            recoverFile(filenameOfExtractN,writesInExtractN);
        }
        if (writesInExtractO != 0)
        {
            GLB.EXO_XSEEDWRITES = writesInExtractO;
            recoverFile(filenameOfExtractO,writesInExtractO);
        }
        if (writesInExtractP != 0)
        {
            GLB.EXP_XSEEDWRITES = writesInExtractP;
            recoverFile(filenameOfExtractP,writesInExtractP);
        }
        if (writesInExtractQ != 0)
        {
            GLB.EXQ_XSEEDWRITES = writesInExtractQ;
            recoverFile(filenameOfExtractQ,writesInExtractQ);
        }
        if (writesInExtractR != 0)
        {
            GLB.EXR_XSEEDWRITES = writesInExtractR;
            recoverFile(filenameOfExtractR,writesInExtractR);
        }
        if (writesInExtractS != 0)
        {
            GLB.EXS_XSEEDWRITES = writesInExtractS;
            recoverFile(filenameOfExtractS,writesInExtractS);
        }
        if (writesInExtractT != 0)
        {
            GLB.EXT_XSEEDWRITES = writesInExtractT;
            recoverFile(filenameOfExtractT,writesInExtractT);
        }
        if (writesInExtractU != 0)
        {
            GLB.EXU_XSEEDWRITES = writesInExtractU;
            recoverFile(filenameOfExtractU,writesInExtractU);
        }
        if (writesInExtractV != 0)
        {
            GLB.EXV_XSEEDWRITES = writesInExtractV;
            recoverFile(filenameOfExtractV,writesInExtractV);
        }
        if (writesInExtractW != 0)
        {
            GLB.EXW_XSEEDWRITES = writesInExtractW;
            recoverFile(filenameOfExtractW,writesInExtractW);
        }
        if (writesInExtractY != 0)
        {
            GLB.EXY_XSEEDWRITES = writesInExtractY;
            recoverFile(filenameOfExtractY,writesInExtractY);
        }
        if (writesInExtractX != 0)
        {
            GLB.EXX_XSEEDWRITES = writesInExtractX;
            recoverFile(filenameOfExtractX,writesInExtractX);
        }
        if (writesInExtractZ != 0)
        {
            GLB.EXZ_XSEEDWRITES = writesInExtractZ;
            recoverFile(filenameOfExtractZ,writesInExtractZ);
        }
        int wIndexBP = 1;
        boolean flagExist = false;
        if (writesInPrint != 0)
        {
            GLB.PRINT_XSEEDWRITES = writesInPrint;
            GLB.ADVANCE = advanceOfPrint;
            GLB.LINECOUNT = linecountOfPrint;
            GLB.PAGECOUNT = pagecountOfPrint;
            for (int i=1; i < beginPage.length; i++)
            {  flagExist=false;
               if (beginPage[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals(" ") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPage[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = " ";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPage[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrint,writesInPrint);
        }

        flagExist =false;
        if (writesInPrintA != 0)
        {
            GLB.PRINTA_XSEEDWRITES = writesInPrintA;
            GLB.ADVANCEA = advanceOfPrintA;
            GLB.LINECOUNTA = linecountOfPrintA;
            GLB.PAGECOUNTA = pagecountOfPrintA;
            for (int i=1; i < beginPageA.length; i++)
            {   flagExist=false;
                if (beginPageA[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("A") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageA[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "A";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageA[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintA,writesInPrintA);

        }

        flagExist=false;
        if (writesInPrintB != 0)
        {
            GLB.PRINTB_XSEEDWRITES = writesInPrintB;
            GLB.ADVANCEB = advanceOfPrintB;
            GLB.LINECOUNTB = linecountOfPrintB;
            GLB.PAGECOUNTB = pagecountOfPrintB;
            for (int i=1; i < beginPageB.length; i++)
            {  flagExist=false;
               if (beginPageB[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("B") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageB[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "B";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageB[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintB,writesInPrintB);
        }

        flagExist=false;
        if (writesInPrintC != 0)
        {
            GLB.PRINTC_XSEEDWRITES = writesInPrintC;
            GLB.ADVANCEC = advanceOfPrintC;
            GLB.LINECOUNTC = linecountOfPrintC;
            GLB.PAGECOUNTC = pagecountOfPrintC;
            for (int i=1; i < beginPageC.length; i++)
            {  flagExist=false;
               if (beginPageC[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("C") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageC[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "C";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageC[i-1];
                    	wIndexBP++;;
                	}
                }
            }
            recoverFile(filenameOfPrintC,writesInPrintC);
        }

        flagExist = false;
        if (writesInPrintD != 0)
        {
            GLB.PRINTD_XSEEDWRITES = writesInPrintD;
            GLB.ADVANCED = advanceOfPrintD;
            GLB.LINECOUNTD = linecountOfPrintD;
            GLB.PAGECOUNTD = pagecountOfPrintD;
            for (int i=1; i < beginPageD.length; i++)
            {  flagExist=false;
               if (beginPageD[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("D") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageD[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "D";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageD[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintD,writesInPrintD);
        }

        flagExist=false;
        if (writesInPrintE != 0)
        {
            GLB.PRINTE_XSEEDWRITES = writesInPrintE;
            GLB.ADVANCEE = advanceOfPrintE;
            GLB.LINECOUNTE = linecountOfPrintE;
            GLB.PAGECOUNTE = pagecountOfPrintE;
            for (int i=1; i < beginPageE.length; i++)
            {  flagExist=false;
               if (beginPageE[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("E") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageE[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "E";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageE[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintE,writesInPrintE);
        }

        flagExist=false;
        if (writesInPrintF != 0)
        {
            GLB.PRINTF_XSEEDWRITES = writesInPrintF;
            GLB.ADVANCEF = advanceOfPrintF;
            GLB.LINECOUNTF = linecountOfPrintF;
            GLB.PAGECOUNTF = pagecountOfPrintF;
            for (int i=1; i < beginPageF.length; i++)
            {  flagExist=false;
               if (beginPageF[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("F") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageF[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "F";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageF[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintF,writesInPrintF);
        }

        flagExist=false;
        if (writesInPrintG != 0)
        {
            GLB.PRINTG_XSEEDWRITES = writesInPrintG;
            GLB.ADVANCEG = advanceOfPrintG;
            GLB.LINECOUNTG = linecountOfPrintG;
            GLB.PAGECOUNTG = pagecountOfPrintG;
            for (int i=1; i < beginPageG.length; i++)
            {  flagExist=false;
               if (beginPageG[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("G") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageG[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "G";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageG[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintG,writesInPrintG);
        }

        flagExist=false;
        if (writesInPrintH != 0)
        {
            GLB.PRINTH_XSEEDWRITES = writesInPrintH;
            GLB.ADVANCEH = advanceOfPrintH;
            GLB.LINECOUNTH = linecountOfPrintH;
            GLB.PAGECOUNTH = pagecountOfPrintH;
            for (int i=1; i < beginPageH.length; i++)
            {  flagExist=false;
               if (beginPageH[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("H") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageH[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "H";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageH[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintH,writesInPrintH);
        }

        flagExist=false;
        if (writesInPrintI != 0)
        {
            GLB.PRINTI_XSEEDWRITES = writesInPrintI;
            GLB.ADVANCEI = advanceOfPrintI;
            GLB.LINECOUNTI = linecountOfPrintI;
            GLB.PAGECOUNTI = pagecountOfPrintI;
            for (int i=1; i < beginPageI.length; i++)
            {  flagExist=false;
               if (beginPageI[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("I") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageI[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "I";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageI[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintI,writesInPrintI);
        }

        flagExist=false;
        if (writesInPrintJ != 0)
        {
            GLB.PRINTJ_XSEEDWRITES = writesInPrintJ;
            GLB.ADVANCEJ = advanceOfPrintJ;
            GLB.LINECOUNTJ = linecountOfPrintJ;
            GLB.PAGECOUNTJ = pagecountOfPrintJ;
            for (int i=1; i < beginPageJ.length; i++)
            {  flagExist=false;
               if (beginPageJ[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("J") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageJ[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "J";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageJ[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintJ,writesInPrintJ);
        }

        flagExist=false;
        if (writesInPrintK != 0)
        {
            GLB.PRINTK_XSEEDWRITES = writesInPrintK;
            GLB.ADVANCEK = advanceOfPrintK;
            GLB.LINECOUNTK = linecountOfPrintK;
            GLB.PAGECOUNTK = pagecountOfPrintK;
            for (int i=1; i < beginPageK.length; i++)
            {  flagExist=false;
               if (beginPageK[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("K") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageK[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "K";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageK[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintK,writesInPrintK);
        }

        flagExist=false;
        if (writesInPrintL != 0)
        {
            GLB.PRINTL_XSEEDWRITES = writesInPrintL;
            GLB.ADVANCEL = advanceOfPrintL;
            GLB.LINECOUNTL = linecountOfPrintL;
            GLB.PAGECOUNTL = pagecountOfPrintL;
            for (int i=1; i < beginPageL.length; i++)
            {  flagExist=false;
               if (beginPageL[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("L") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageL[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "L";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageL[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintL,writesInPrintL);
        }

        flagExist=false;
        if (writesInPrintM != 0)
        {
            GLB.PRINTM_XSEEDWRITES = writesInPrintM;
            GLB.ADVANCEM = advanceOfPrintM;
            GLB.LINECOUNTM = linecountOfPrintM;
            GLB.PAGECOUNTM = pagecountOfPrintM;
            for (int i=1; i < beginPageM.length; i++)
            {  flagExist=false;
               if (beginPageM[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("M") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageM[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                		GLB.BP[wIndexBP][SHADOW] = "M";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageM[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintM,writesInPrintM);
        }

        flagExist=false;
        if (writesInPrintN != 0)
        {
            GLB.PRINTN_XSEEDWRITES = writesInPrintN;
            GLB.ADVANCEN = advanceOfPrintN;
            GLB.LINECOUNTN = linecountOfPrintN;
            GLB.PAGECOUNTN = pagecountOfPrintN;
            for (int i=1; i < beginPageN.length; i++)
            {  flagExist=false;
               if (beginPageN[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("N") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageN[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "N";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageN[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintN,writesInPrintN);
        }

        flagExist=false;
        if (writesInPrintO != 0)
        {
            GLB.PRINTO_XSEEDWRITES = writesInPrintO;
            GLB.ADVANCEO = advanceOfPrintO;
            GLB.LINECOUNTO = linecountOfPrintO;
            GLB.PAGECOUNTO = pagecountOfPrintO;
            for (int i=1; i < beginPageO.length; i++)
            {  flagExist=false;
               if (beginPageO[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("O") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageO[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "O";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageO[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintO,writesInPrintO);
        }

        flagExist=false;
        if (writesInPrintP != 0)
        {
            GLB.PRINTP_XSEEDWRITES = writesInPrintP;
            GLB.ADVANCEP = advanceOfPrintP;
            GLB.LINECOUNTP = linecountOfPrintP;
            GLB.PAGECOUNTP = pagecountOfPrintP;
            for (int i=1; i < beginPageP.length; i++)
            {  flagExist=false;
               if (beginPageP[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("P") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageP[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "P";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageP[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintP,writesInPrintP);
        }

        flagExist=false;
        if (writesInPrintQ != 0)
        {
            GLB.PRINTQ_XSEEDWRITES = writesInPrintQ;
            GLB.ADVANCEQ = advanceOfPrintQ;
            GLB.LINECOUNTQ = linecountOfPrintQ;
            GLB.PAGECOUNTQ = pagecountOfPrintQ;
            for (int i=1; i < beginPageQ.length; i++)
            {  flagExist=false;
               if (beginPageQ[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("Q") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageQ[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "Q";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageQ[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintQ,writesInPrintQ);
        }

        flagExist=false;
        if (writesInPrintR != 0)
        {
            GLB.PRINTR_XSEEDWRITES = writesInPrintR;
            GLB.ADVANCER = advanceOfPrintR;
            GLB.LINECOUNTR = linecountOfPrintR;
            GLB.PAGECOUNTR = pagecountOfPrintR;
            for (int i=1; i < beginPageR.length; i++)
            {  flagExist=false;
               if (beginPageR[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("R") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageR[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "R";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageR[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintR,writesInPrintR);
        }

        flagExist=false;
        if (writesInPrintS != 0)
        {
            GLB.PRINTS_XSEEDWRITES = writesInPrintS;
            GLB.ADVANCES = advanceOfPrintS;
            GLB.LINECOUNTS = linecountOfPrintS;
            GLB.PAGECOUNTS = pagecountOfPrintS;
            for (int i=1; i < beginPageS.length; i++)
            {  flagExist=false;
               if (beginPageS[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("S") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageS[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "S";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageS[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintS,writesInPrintS);
        }

        flagExist=false;
        if (writesInPrintT != 0)
        {
            GLB.PRINTT_XSEEDWRITES = writesInPrintT;
            GLB.ADVANCET = advanceOfPrintT;
            GLB.LINECOUNTT = linecountOfPrintT;
            GLB.PAGECOUNTT = pagecountOfPrintT;
            for (int i=1; i < beginPageT.length; i++)
            {  flagExist=false;
               if (beginPageT[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("T") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageT[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "T";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageT[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintT,writesInPrintT);
        }

        flagExist=false;
        if (writesInPrintU != 0)
        {
            GLB.PRINTU_XSEEDWRITES = writesInPrintU;
            GLB.ADVANCEU = advanceOfPrintU;
            GLB.LINECOUNTU = linecountOfPrintU;
            GLB.PAGECOUNTU = pagecountOfPrintU;
            for (int i=1; i < beginPageU.length; i++)
            {  flagExist=false;
               if (beginPageU[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("U") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageU[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "U";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageU[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintU,writesInPrintU);
        }

        flagExist=false;
        if (writesInPrintV != 0)
        {
            GLB.PRINTV_XSEEDWRITES = writesInPrintV;
            GLB.ADVANCEV = advanceOfPrintV;
            GLB.LINECOUNTV = linecountOfPrintV;
            GLB.PAGECOUNTV = pagecountOfPrintV;
            for (int i=1; i < beginPageV.length; i++)
            {  flagExist=false;
               if (beginPageV[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("V") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageV[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "V";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageV[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintV,writesInPrintV);
        }

        flagExist=false;
        if (writesInPrintW != 0)
        {
            GLB.PRINTW_XSEEDWRITES = writesInPrintW;
            GLB.ADVANCEW = advanceOfPrintW;
            GLB.LINECOUNTW = linecountOfPrintW;
            GLB.PAGECOUNTW = pagecountOfPrintW;
            for (int i=1; i < beginPageW.length; i++)
            {  flagExist=false;
               if (beginPageW[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("W") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageW[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "W";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageW[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintW,writesInPrintW);
        }

        flagExist=false;
        if (writesInPrintY != 0)
        {
            GLB.PRINTY_XSEEDWRITES = writesInPrintY;
            GLB.ADVANCEY = advanceOfPrintY;
            GLB.LINECOUNTY = linecountOfPrintY;
            GLB.PAGECOUNTY = pagecountOfPrintY;
            for (int i=1; i < beginPageY.length; i++)
            {  flagExist=false;
               if (beginPageY[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("Y") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageY[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "Y";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageY[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintY,writesInPrintY);
        }
        flagExist=false;
        if (writesInPrintX != 0)
        {
            GLB.PRINTX_XSEEDWRITES = writesInPrintX;
            GLB.ADVANCEX = advanceOfPrintX;
            GLB.LINECOUNTX = linecountOfPrintX;
            GLB.PAGECOUNTX = pagecountOfPrintX;
            for (int i=1; i < beginPageX.length; i++)
            {  flagExist=false;
               if (beginPageX[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("X") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageX[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "X";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageX[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintX,writesInPrintX);
        }

        flagExist=false;
        if (writesInPrintZ != 0)
        {
            GLB.PRINTZ_XSEEDWRITES = writesInPrintZ;
            GLB.ADVANCEZ = advanceOfPrintZ;
            GLB.LINECOUNTZ = linecountOfPrintZ;
            GLB.PAGECOUNTZ = pagecountOfPrintZ;
            for (int i=1; i < beginPageZ.length; i++)
            {  flagExist=false;
               if (beginPageZ[i-1].equals("")== false)
                {
                	/* Verifica se já foi definido */
    				for (int j=1; j <= GLB.BP_INDEX; j++)
    				{
    					if ( (GLB.BP[j][SHADOW].equals("Z") == true) && (GLB.BP[j][GLB.SEQ].equals(beginPageZ[i-1]) == true) )
        				{
        					flagExist=true;
						}
					}
                	if (flagExist==false)
                	{
                    	GLB.BP[wIndexBP][SHADOW] = "Z";
                    	GLB.BP[wIndexBP][GLB.SEQ] = beginPageZ[i-1];
                    	wIndexBP++;
                	}
                }
            }
            recoverFile(filenameOfPrintZ,writesInPrintZ);
        }
    }

    public void recoverFile(String pFilename, int pWrites)
       throws Exception
    {
        String wTempFilename = pFilename + "_cpRecover";
        String wLine;
        int wLineCont = 0;

        XseedFile wTempFile;
        XseedFile wRecoverFile;

        if (pWrites == 0)
        {
            return;
        }

        if ( (pFilename.equals("") == true)  || (XseedFunctions.FileExists(pFilename) == false))
        {
            return;
        }

        if (XseedFunctions.FileCopy(pFilename, wTempFilename)==false)
        {
            return;
        }

        wTempFile = new XseedFile();
        wTempFile.open(wTempFilename, "INPUT");

        wRecoverFile = new XseedFile();
        wRecoverFile.open(pFilename, "OUTPUT");

        wLine = wTempFile.read();
        while (wLine!=null)
        {
           wRecoverFile.write(wLine);
           wLineCont++;
           if (wLineCont==pWrites)
           {
                break;
           }
           wLine = wTempFile.read();
        }
        if (wRecoverFile.out.checkError()) { abort("Error on File : " + pFilename ,"RF");  }  //Recorver Files
        wTempFile.close();
        wRecoverFile.close();
    }

    /* format double */
    public String format(String pString, String pMask)
        throws Exception
    {
        try
        {
            DecimalFormat wMyFormatter;
            double wNumber= Double.parseDouble(pString);

            if (pMask.equals("")==false)
            {
              wMyFormatter = new DecimalFormat(pMask);
              return(wMyFormatter.format(wNumber));
            }
            else
            {
                return(XseedFunctions.cStr(wNumber));
            }
        }
        catch (Exception e)
        {
            return ("");
        }
    }


    public String getRecoverID()
        throws Exception
    {
        try
        {   File wFile;
            String wFilePath;

            FileOutputStream wOutFile;
            PrintStream wOut;
            long wInd=1;
            wFilePath = GLB.TEMPDIR + File.separator + GLB.REPNAME + "_" +  cStr(wInd++) + ".CP";
            wFile = new File(wFilePath);
            while (wFile.exists())
            {
               wFile=null;
               wFilePath = GLB.TEMPDIR + File.separator + GLB.REPNAME + "_" + cStr(wInd++) + ".CP";
               wFile = new File(wFilePath);
            }

            wOutFile = new FileOutputStream(wFilePath, false);
            wOut = new PrintStream( new BufferedOutputStream (wOutFile) );
            wOut.close();
            wOut=null;
            wOutFile=null;
            wFile=null;
            return(cStr(wInd-1));

        }
        catch (Exception e)
        {
            return ("0");
        }
    }

    private String cStr(long pNumber)
        throws Exception
    {
        String str = new BigDecimal(new Long(pNumber).toString()).toPlainString();
         if ( (str.length()>2 ) &&(str.substring (str.length()-2).equals(".0")==true))
        {   str = str.substring(0,str.length()-2);
        }
        return(str);
    }

    private  void abort(String pMsg, String pFileType)
    throws Exception
	{
		if (pFileType.equals("CP")) GLB.TASK=51;
		else if (pFileType.equals("LP")) GLB.TASK=52;
		else if (pFileType.equals("EX")) GLB.TASK=53;
		else if (pFileType.equals("RF")) GLB.TASK=54;
		else GLB.TASK=1;

		GLB.ABORT = true;
    	GLB.EXIT = true;
    	GLB.MYSTATUS = "U-DS";

    	if (GLB.AUDITJOB == true)
    	{
    		AuditMSG ("XseedCriticalPoint : " + pMsg);
    		System.out.println(" Abort XseedCriticalPoint: " + pMsg);
		}
		throw new Exception();
	}

	private void AuditMSG(String pMsg)
  	throws Exception
	{
		Date dateToday = new Date();
		PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));
    	audit.println("MSG:" + formatDate(dateToday, "yyyy/MM/dd HH:mm:ss") + " Rep: "+ GLB.REPNAME + " Msg: " + pMsg.trim());
    	audit.close();
	}

	public String formatDate (Date pDate, String pMask)
	throws Exception
	{
		SimpleDateFormat   auxFormat;
    	double             DateNow;
    	auxFormat = new SimpleDateFormat(pMask);
    	return(auxFormat.format(pDate));
	}

	public void checkFileError() throws Exception
	{
    	if (GLB.LP!=null && GLB.LP.checkError ())     { abort("Error on File : " + GLB.LP_XSEEDTITLE, "LP");  }
		if (GLB.LPA!=null && GLB.LPA.checkError ())   { abort("Error on File : " + GLB.LPA_XSEEDTITLE, "LP"); }
       	if (GLB.LPB!=null && GLB.LPB.checkError ())   { abort("Error on File : " + GLB.LPB_XSEEDTITLE, "LP");  }
       	if (GLB.LPC!=null && GLB.LPC.checkError ())   { abort("Error on File : " + GLB.LPC_XSEEDTITLE, "LP");  }
       	if (GLB.LPD!=null && GLB.LPD.checkError ())   { abort("Error on File : " + GLB.LPD_XSEEDTITLE, "LP");  }
       	if (GLB.LPE!=null && GLB.LPE.checkError ())   { abort("Error on File : " + GLB.LPE_XSEEDTITLE, "LP");  }
       	if (GLB.LPF!=null && GLB.LPF.checkError ())   { abort("Error on File : " + GLB.LPF_XSEEDTITLE, "LP");  }
       	if (GLB.LPG!=null && GLB.LPG.checkError ())   { abort("Error on File : " + GLB.LPG_XSEEDTITLE, "LP");  }
       	if (GLB.LPH!=null && GLB.LPH.checkError ())   { abort("Error on File : " + GLB.LPH_XSEEDTITLE, "LP");  }
       	if (GLB.LPI!=null && GLB.LPI.checkError ())   { abort("Error on File : " + GLB.LPI_XSEEDTITLE, "LP");  }
       	if (GLB.LPJ!=null && GLB.LPJ.checkError ())   { abort("Error on File : " + GLB.LPJ_XSEEDTITLE, "LP");  }
       	if (GLB.LPK!=null && GLB.LPK.checkError ())   { abort("Error on File : " + GLB.LPK_XSEEDTITLE, "LP");  }
       	if (GLB.LPL!=null && GLB.LPL.checkError ())   { abort("Error on File : " + GLB.LPL_XSEEDTITLE, "LP");  }
       	if (GLB.LPM!=null && GLB.LPM.checkError ())   { abort("Error on File : " + GLB.LPM_XSEEDTITLE, "LP");  }
       	if (GLB.LPN!=null && GLB.LPN.checkError ())   { abort("Error on File : " + GLB.LPN_XSEEDTITLE, "LP");  }
       	if (GLB.LPO!=null && GLB.LPO.checkError ())   { abort("Error on File : " + GLB.LPO_XSEEDTITLE, "LP");  }
       	if (GLB.LPP!=null && GLB.LPP.checkError ())   { abort("Error on File : " + GLB.LPP_XSEEDTITLE, "LP");  }
 		if (GLB.LPQ!=null && GLB.LPQ.checkError ())   { abort("Error on File : " + GLB.LPQ_XSEEDTITLE, "LP");  }
       	if (GLB.LPR!=null && GLB.LPR.checkError ())   { abort("Error on File : " + GLB.LPR_XSEEDTITLE, "LP");  }
       	if (GLB.LPS!=null && GLB.LPS.checkError ())   { abort("Error on File : " + GLB.LPS_XSEEDTITLE, "LP");  }
       	if (GLB.LPT!=null && GLB.LPT.checkError ())   { abort("Error on File : " + GLB.LPT_XSEEDTITLE, "LP");  }
       	if (GLB.LPU!=null && GLB.LPU.checkError ())   { abort("Error on File : " + GLB.LPU_XSEEDTITLE, "LP");  }
       	if (GLB.LPV!=null && GLB.LPV.checkError ())   { abort("Error on File : " + GLB.LPV_XSEEDTITLE, "LP");  }
       	if (GLB.LPW!=null && GLB.LPW.checkError ())   { abort("Error on File : " + GLB.LPW_XSEEDTITLE, "LP");  }
		if (GLB.LPX!=null && GLB.LPX.checkError ())   { abort("Error on File : " + GLB.LPX_XSEEDTITLE, "LP");  }
		if (GLB.LPY!=null && GLB.LPY.checkError ())   { abort("Error on File : " + GLB.LPY_XSEEDTITLE, "LP");  }
		if (GLB.LPZ!=null && GLB.LPZ.checkError ())   { abort("Error on File : " + GLB.LPZ_XSEEDTITLE, "LP");  }

    	if (GLB.EXA!=null && GLB.EXA.out!=null && GLB.EXA.out.checkError()) { abort("Error on File : " + GLB.EXA_FILENAME, "EX");  }
    	if (GLB.EXB!=null && GLB.EXB.out!=null && GLB.EXB.out.checkError()) { abort("Error on File : " + GLB.EXB_FILENAME, "EX");  }
    	if (GLB.EXC!=null && GLB.EXC.out!=null && GLB.EXC.out.checkError()) { abort("Error on File : " + GLB.EXC_FILENAME, "EX");  }
    	if (GLB.EXD!=null && GLB.EXD.out!=null && GLB.EXD.out.checkError()) { abort("Error on File : " + GLB.EXD_FILENAME, "EX");  }
    	if (GLB.EXE!=null && GLB.EXE.out!=null && GLB.EXE.out.checkError()) { abort("Error on File : " + GLB.EXE_FILENAME, "EX");  }
    	if (GLB.EXF!=null && GLB.EXF.out!=null && GLB.EXF.out.checkError()) { abort("Error on File : " + GLB.EXF_FILENAME, "EX");  }
    	if (GLB.EXG!=null && GLB.EXG.out!=null && GLB.EXG.out.checkError()) { abort("Error on File : " + GLB.EXG_FILENAME, "EX");  }
    	if (GLB.EXH!=null && GLB.EXH.out!=null && GLB.EXH.out.checkError()) { abort("Error on File : " + GLB.EXH_FILENAME, "EX");  }
    	if (GLB.EXI!=null && GLB.EXI.out!=null && GLB.EXI.out.checkError()) { abort("Error on File : " + GLB.EXI_FILENAME, "EX");  }
    	if (GLB.EXJ!=null && GLB.EXJ.out!=null && GLB.EXJ.out.checkError()) { abort("Error on File : " + GLB.EXJ_FILENAME, "EX");  }
		if (GLB.EXK!=null && GLB.EXK.out!=null && GLB.EXK.out.checkError()) { abort("Error on File : " + GLB.EXK_FILENAME, "EX");  }
    	if (GLB.EXL!=null && GLB.EXL.out!=null && GLB.EXL.out.checkError()) { abort("Error on File : " + GLB.EXL_FILENAME, "EX");  }
		if (GLB.EXM!=null && GLB.EXM.out!=null && GLB.EXM.out.checkError()) { abort("Error on File : " + GLB.EXM_FILENAME, "EX");  }
    	if (GLB.EXN!=null && GLB.EXN.out!=null && GLB.EXN.out.checkError()) { abort("Error on File : " + GLB.EXN_FILENAME, "EX");  }
		if (GLB.EXO!=null && GLB.EXO.out!=null && GLB.EXO.out.checkError()) { abort("Error on File : " + GLB.EXO_FILENAME, "EX");  }
    	if (GLB.EXP!=null && GLB.EXP.out!=null && GLB.EXP.out.checkError()) { abort("Error on File : " + GLB.EXP_FILENAME, "EX");  }
		if (GLB.EXQ!=null && GLB.EXQ.out!=null && GLB.EXQ.out.checkError()) { abort("Error on File : " + GLB.EXQ_FILENAME, "EX");  }
    	if (GLB.EXR!=null && GLB.EXR.out!=null && GLB.EXR.out.checkError()) { abort("Error on File : " + GLB.EXR_FILENAME, "EX");  }
		if (GLB.EXS!=null && GLB.EXS.out!=null && GLB.EXS.out.checkError()) { abort("Error on File : " + GLB.EXS_FILENAME, "EX");  }
    	if (GLB.EXT!=null && GLB.EXT.out!=null && GLB.EXT.out.checkError()) { abort("Error on File : " + GLB.EXT_FILENAME, "EX");  }
		if (GLB.EXU!=null && GLB.EXU.out!=null && GLB.EXU.out.checkError()) { abort("Error on File : " + GLB.EXU_FILENAME, "EX");  }
		if (GLB.EXV!=null && GLB.EXV.out!=null && GLB.EXV.out.checkError()) { abort("Error on File : " + GLB.EXV_FILENAME, "EX");  }
		if (GLB.EXW!=null && GLB.EXW.out!=null && GLB.EXW.out.checkError()) { abort("Error on File : " + GLB.EXW_FILENAME, "EX");  }
	   	if (GLB.EXX!=null && GLB.EXX.out!=null && GLB.EXX.out.checkError()) { abort("Error on File : " + GLB.EXX_FILENAME, "EX");  }
    	if (GLB.EXY!=null && GLB.EXY.out!=null && GLB.EXY.out.checkError()) { abort("Error on File : " + GLB.EXY_FILENAME, "EX");  }
		if (GLB.EXZ!=null && GLB.EXZ.out!=null && GLB.EXZ.out.checkError()) { abort("Error on File : " + GLB.EXZ_FILENAME, "EX");  }
	}

	//setBeginPage
	private void setBeginPage (String pShadow, String pFrame)
	{
		/* Verifica se já foi definido */
	    for (int i=1; i <= GLB.BP_INDEX; i++)
    	{
    		if ( (GLB.BP[i][SHADOW].equals(pShadow) == true) && (GLB.BP[i][GLB.SEQ].equals(pFrame) == true) )
        	{
        		return;
			}
		}
     	GLB.BP_INDEX = GLB.BP_INDEX + 1;
    	if (GLB.BP_INDEX > 100)
    	{
    		/* Se chegar ao limite do array permanecer com INDEX = 100 */
        	GLB.BP_INDEX = 100;
        	for (int i=1; i <= GLB.BP_INDEX; i++)
        	{
	       		if ( (GLB.BP[i][SHADOW].equals("") == true) && (GLB.BP[i][GLB.SEQ].equals("") == true) )
    	   		{
            		GLB.BP[i][SHADOW] = pShadow;
           			GLB.BP[i][GLB.SEQ] = pFrame;
				}
			}
		}
    	else
    	{
    		GLB.BP[GLB.BP_INDEX][SHADOW] = pShadow;
        	GLB.BP[GLB.BP_INDEX][GLB.SEQ] = pFrame;
		}
	}

}