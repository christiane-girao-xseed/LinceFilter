package HLegacy;
import java.sql.*;
import java.text.*;
import java.io.*;
import java.math.*;

public class XseedGLBRecovery implements Serializable
{
	public boolean ABORT = false;
	public  String  ABORTMSG = "";   
	public  String  ACCEPTFILE = "";
	public  String  ADD = "ADD";
	public  String  ALTUSER = "";
	public  String  APPNAME = "";
	public String  APPPATH = "";
	public short   ARRAYIDX = 0;
	public short   ARRAYIDX1 = 0;
	public short   ARRAYIDX2 = 0;
	public short   ARRAYIDX3 = 0;
	public String  ARRAYSTR = "";
	public String  ASCPRT = "";
	public String  AUDITACT = "";	
	public String  AUDITDIR = "";	
	public boolean AUDITJOB = false;


	public String BAC = "BAC";		
	public String BACKUPDIR = "";	
	public String BDNAME = "";
	public String BDSUFFIX = "";
	public double BASE = 0;
	public String BATCHDIR = "";
	public String BATCHTIMER = "";
	public String BODYTAG = "";
	public String[][] BP = new String[120][2];
	public int BP_INDEX = 0;
	public String BKPPREFIX = "";
	public double BUFFERSIZE =0;


	public String	   CALL = "     ";

	public transient CallableStatement    CALLABLECMD;
	public transient  Connection  CONNECTION;


	public String      CALLREPORT = "          ";		
	public double      CENTURY = 0;	
	public String      CHANGE = "                              "; 
	public String      CHG = "CHG";
	public boolean	   CLONEAPP = false;
	public String  	   CLONEAPPDIR = "";
	public boolean     CLONERTS = false;
	public String      CLONERTSDIR = "";		
	public String      CLOSE = "CLOSE"; 
	public BigDecimal  COMPUTE = new BigDecimal(0);

	public boolean     CONSOLE = false;
	public double      COPY = 0;		
	public String      CORSTATUS = "";
	public boolean     CONTINUE = false;
	public double      CP_COUNT = 0;
	public boolean     CRITPOINT = false;
	public String      CURSOR = "";


	public String DATE = "       ";	
	public double DBCODE;
	public String DBMSG;
	public String DBNAME = "";	
	public String DBTITLE = ""; //VERIFICAR OPENFILES		
	public String DBTYPE = "";
	public String DC_AUX = ""; 	
	public double DC_DAYNUM = 0;
	public double DC_CC = 0;
	public double DC_CCYYDDD = 0;
	public double DC_CCYYMMDD = 0;
	public String DC_CCYY_MM_DD = "          ";
	public String DC_CCYYMMMDD = "         ";
	public String DC_CCYY_MMM_DD = "           ";
	public String DC_DD_MM_YY = "        ";
	public String DC_DD_MM_CCYY = "          ";
	public double DC_DDMMCCYY = 0;
	public String DC_DD_MMM_CCYY = "           ";
	public String DC_DDMMMCCYY = "         ";
	public String DC_DD_MMM_YY = "         ";
	public String DC_DDMMMYY = "       ";
	public double DC_DDMMYY = 0;
	public String DC_IN_ALPHA = "                 ";
	public String DC_MM_DD_CCYY = "          ";
	public String DC_MM_DD_YY = "        ";
	public double DC_MMDDYY = 0;
	public double DC_MMDDCCYY = 0;
	public String DC_MMM_DD_YY = "         ";
	public String DC_MMM_DD_CCYY = "           ";
	public String DC_MMMDDYY = "       ";
	public String DC_MMMDDCCYY = "         ";
	public String DC_TODAY = "  	            ";
	public String DCTYPE = "  ";
	public String DC_UK_ALPHA = "                 ";
	public String DC_US_ALPHA = "                 ";
	public double DC_WEEKNO = 0;
	public double DC_YYDDD = 0;
	public double DC_YYMMDD = 0;
	public String DC_YY_MM_DD = "        ";
	public String DC_YYMMMDD = "       ";
	public String DC_YY_MMM_DD = "         ";	
	public String DCUPPERCASE = "FALSE";	
	public String DEL = "DEL";
	public int    DETACHDEL = 0; //USADA COMANDO DETACH
	public String DETACHSTR = "";
	public String DESTENV = "";
	public String DESTNOFORM = "                 ";
	public String DESTHOST = "";
	public String DESTINATION = "          ";		
	public String DOCDIR = "";		
	public char   DecimalChar;
	public String DSN = "";

	public String  EMAILATTACH = "";
	public String  EMAILCC = "";	
	public String  EMAILFROM = "";
	public String  EMAILHOST = "";
	public String  EMAILSUBJECT = "";
	public String  EMAILTEXT = "";
	public String  EMAILTO = "";
	public String  ERRLINE = "";
	public String  ERRMARK = " ";// UTILIZADA SKC
	public String  ERROR = "";
	public boolean EXIT = false;
	public String  EXTEXT = "";
	public boolean EXTRACT = false;
	public String  EXTRACTSDIR = "";
	public String  EXTRACTPREFIX = "";


	public String  FILESDIR = "";
	public String  FIR = "FIR";
	public String  FIREUPISPEC = "";	
	public String  FONT = "Courier New";
	public double  FONTSIZE = 8;;
	public boolean FONTBOLD = false;		
	public String  FULLSTN = "";

	public String  GUI = "Y";    
	final public String HIGH  = "" + (char)255;
	public String HUBSTATUS = ""; 
	public double HUBTIMEOUT = 0;


	public String ICONSDIR = "";	
	public String INITSTN = "                 ";
	public String INPUTBOX = "";
	public String INQ = "INQ";	
	public double ISPECLOCKTIMEOUT = 60.0;


	public String         LANGUAGE = "          ";             
	public String         LAS = "LAS";
	public double         LENGTH = 0;	
	public boolean        LOADED=false;	
	final public String   LOW  = "" + (char)0;
	public String         LSN = "     ";


	public String MACHINE = "A";
	public String MASKDECIMALCHAR;
	public double MAXCOPY = 0;		
	public String MESSAGE = "";
	public String MSGHEADER[] = new  String [25];
	public int    MSGINDEX=0;
	public String MSGSDIR = "";
	public short  MSGTIMER = 0;
	public String MSGTRAILER[] = new  String [25];
	public String MYSTATUS = "";

	public String NEX = "NEX";	
	public String NOW = "";

	public double NUMCOPY = 0;	

	public double OCHFLAG = 0;
	public String ORIENTATION = "PORTRAIT";	
	public String ORIGIN = "";
	public String ORIGINHOST = "";

	public String PAGEMARK = "<.pa>";
	public String PAGESKIP = " ";	
	public String PARAM = "                                                                                                                                                                                                                                                                ";	
	public String PASSWORD = "                              ";	
	public String PHASE = " ";
	public String PID = "";
	public String PRINTAT = "          ";
	public String PRINTBANNER = " ";	
	public String PRINTHOST = "                 ";	
	public double PRIORITY = 0;
	public String PRODUCT = "XSEED";
	public String PUR = "PUR";


	public String      REC = "REC";
	public String      RECALL = "";	
	public double      RECOVER = 0;
	public String      RECOVERID = "";
	public String      REDIRECT = "";
	public String      REPEXECMODE = "";
	public String      REPLANG = "          ";
	public String      REPNAME = "                              "; 
	public double      REPORT = 0;
	public String      ReportFilename = "";
	public double      REPORTLOCKTIMEOUT = 0.0;
	public String      REPPREFIX = "          ";
	public String 	   REPSTATUS = "";
	public String      REQUEST = " ";
	public String      RESTART = " ";	
	public boolean     RESTORE = true;
	public boolean     RETURNPS = false;
	public BigDecimal  REMAINDER = new BigDecimal(0);
	public boolean     ROLLBACK = false;
	public String      RUNSTATUS = "";

	public double  SAVCOPIES = 0;
	public boolean SECURE;
	final public int SEQ = 1;
	public String  SELF = "";
	public String  SELFENV = "";
	public String  SELFHOST = "";
	public char    SeparatorChar;
	public double  SERIAL = 0;
	public String  SERVER = "";	
	public String  SMSGHEADER = "                ";
	public String  SMSGTRAILER = "                                                                ";
	public String  SPACES = "";
	public String  SOURCE = "";
	public String  STALANG = "          ";
	public long    STARTTIME = 0;
	public String  STATUS = "     ";
	public String  STATUSCODE = "  ";	
	public String  STYLE = "";
	public String  SYSTEMDIR = "";	


	public String TARGETOS = "";	
	public double TASK =0.0;	
	public String TEACH = "     ";
	public String TEMPDIR = "";
	public String TEMPPREFIX = "";
	public double TILE = 0;
	public double TIME = 0;
	public String TIMEOUTACTION = "app";	
	public String TODAY = "       ";
	public double TODAYS_DATE_NUM = 0;
	public double TODAYS_DAY = 0;
	public String TODAYS_MONTH = "   ";
	public double TODAYS_MONTH_NUM = 0;
	public double TODAYS_YEAR = 0;
	public double TOTAL = 0;	
	public String TRACEDIR = "";
	public String TRACESUFFIX = ".trace";

	public double  UNIQUE = 0;
	public boolean UPDATEGLBCENTURY = false;
	public String USER = "";
	public String USERCODE = "          ";
	public String USERNAME = "                              ";

	public boolean WFL = false;
	public String WORK = ""; 	
	public String WSTATION = "";	                     
	public String WTITLE = "";

	public String XML = "";
	public String XTITLE = "";		


	public double YYMMDD = 0;	

	public double ZEROS =0;



	/* modificado por causa da recuperacao*/ 
	public transient  FileOutputStream file_LP;

	/* modificado por causa da recuperacao*/
	public transient  PrintStream          LP;



	//REPORT.RSE

	public String   BACKUP = "";
	public String   BACKUPA = "";
	public String   BACKUPB = "";
	public String   BACKUPC = "";
	public String   BACKUPD = "";
	public String   BACKUPE = "";
	public String   BACKUPF = "";
	public String   BACKUPG = "";
	public String   BACKUPH = "";
	public String   BACKUPI = "";
	public String   BACKUPJ = "";
	public String   BACKUPK = "";
	public String   BACKUPL = "";
	public String   BACKUPM = "";
	public String   BACKUPN = "";
	public String   BACKUPO = "";
	public String   BACKUPP = "";
	public String   BACKUPQ = "";
	public String   BACKUPR = "";
	public String   BACKUPS = "";
	public String   BACKUPT = "";
	public String   BACKUPU = "";
	public String   BACKUPV = "";
	public String   BACKUPW = "";
	public String   BACKUPX = "";
	public String   BACKUPY = "";
	public String   BACKUPZ = "";    

	public String   CONTENTTYPE = "";    
	public String   CONTENTTYPEA = "";   
	public String   CONTENTTYPEB = "";   
	public String   CONTENTTYPEC = "";   
	public String   CONTENTTYPED = "";   
	public String   CONTENTTYPEE = "";   
	public String   CONTENTTYPEF = "";   
	public String   CONTENTTYPEG = "";   
	public String   CONTENTTYPEH = "";   
	public String   CONTENTTYPEI = "";   
	public String   CONTENTTYPEJ = "";   
	public String   CONTENTTYPEK = "";   
	public String   CONTENTTYPEL = "";   
	public String   CONTENTTYPEM = "";   
	public String   CONTENTTYPEN = "";   
	public String   CONTENTTYPEO = "";   
	public String   CONTENTTYPEP = "";   
	public String   CONTENTTYPEQ = "";   
	public String   CONTENTTYPER = "";   
	public String   CONTENTTYPES = "";   
	public String   CONTENTTYPET = "";   
	public String   CONTENTTYPEU = "";   
	public String   CONTENTTYPEV = "";   
	public String   CONTENTTYPEW = "";   
	public String   CONTENTTYPEX = "";   
	public String   CONTENTTYPEY = "";   
	public String   CONTENTTYPEZ = "";

	public XseedFileRecovery EXA;
	public XseedFileRecovery EXB;
	public XseedFileRecovery EXC;
	public XseedFileRecovery EXD;
	public XseedFileRecovery EXE;
	public XseedFileRecovery EXF;
	public XseedFileRecovery EXG;
	public XseedFileRecovery EXH;
	public XseedFileRecovery EXI;
	public XseedFileRecovery EXJ;
	public XseedFileRecovery EXK;
	public XseedFileRecovery EXL;
	public XseedFileRecovery EXM;
	public XseedFileRecovery EXN;
	public XseedFileRecovery EXO;
	public XseedFileRecovery EXP;
	public XseedFileRecovery EXQ;
	public XseedFileRecovery EXR;
	public XseedFileRecovery EXS;
	public XseedFileRecovery EXT;
	public XseedFileRecovery EXU;
	public XseedFileRecovery EXV;
	public XseedFileRecovery EXW;
	public XseedFileRecovery EXX;
	public XseedFileRecovery EXY;
	public XseedFileRecovery EXZ;    

	public String EXA_FILENAME = "";
	public String EXB_FILENAME = "";
	public String EXC_FILENAME = "";
	public String EXD_FILENAME = "";
	public String EXE_FILENAME = "";
	public String EXF_FILENAME = "";
	public String EXG_FILENAME = "";
	public String EXH_FILENAME = "";
	public String EXI_FILENAME = "";
	public String EXJ_FILENAME = "";
	public String EXK_FILENAME = "";
	public String EXL_FILENAME = "";
	public String EXM_FILENAME = "";
	public String EXN_FILENAME = "";
	public String EXO_FILENAME = "";
	public String EXP_FILENAME = "";
	public String EXQ_FILENAME = "";
	public String EXR_FILENAME = "";    
	public String EXS_FILENAME = "";
	public String EXT_FILENAME = "";
	public String EXU_FILENAME = "";
	public String EXV_FILENAME = "";
	public String EXW_FILENAME = "";
	public String EXX_FILENAME = "";
	public String EXY_FILENAME = "";
	public String EXZ_FILENAME = "";


	public String   FILEINFO = "";    
	public String   FILEINFOA = "";   
	public String   FILEINFOB = "";   
	public String   FILEINFOC = "";   
	public String   FILEINFOD = "";   
	public String   FILEINFOE = "";   
	public String   FILEINFOF = "";   
	public String   FILEINFOG = "";   
	public String   FILEINFOH = "";   
	public String   FILEINFOI = "";   
	public String   FILEINFOJ = "";   
	public String   FILEINFOK = "";   
	public String   FILEINFOL = "";   
	public String   FILEINFOM = "";   
	public String   FILEINFON = "";   
	public String   FILEINFOO = "";   
	public String   FILEINFOP = "";   
	public String   FILEINFOQ = "";   
	public String   FILEINFOR = "";   
	public String   FILEINFOS = "";   
	public String   FILEINFOT = "";   
	public String   FILEINFOU = "";   
	public String   FILEINFOV = "";   
	public String   FILEINFOW = "";   
	public String   FILEINFOX = "";   
	public String   FILEINFOY = "";   
	public String   FILEINFOZ = "";   


	public String INITHEADER  = "TRUE";
	public String INITHEADERA = "TRUE";
	public String INITHEADERB = "TRUE";
	public String INITHEADERC = "TRUE";
	public String INITHEADERD = "TRUE";
	public String INITHEADERE = "TRUE";
	public String INITHEADERF = "TRUE";
	public String INITHEADERG = "TRUE";
	public String INITHEADERH = "TRUE";
	public String INITHEADERI = "TRUE";
	public String INITHEADERJ = "TRUE";
	public String INITHEADERK = "TRUE";
	public String INITHEADERL = "TRUE";
	public String INITHEADERM = "TRUE";
	public String INITHEADERN = "TRUE";
	public String INITHEADERO = "TRUE";
	public String INITHEADERP = "TRUE";
	public String INITHEADERQ = "TRUE";
	public String INITHEADERR = "TRUE";
	public String INITHEADERS = "TRUE";
	public String INITHEADERT = "TRUE";
	public String INITHEADERU = "TRUE";
	public String INITHEADERV = "TRUE";
	public String INITHEADERW = "TRUE";
	public String INITHEADERX = "TRUE";
	public String INITHEADERY = "TRUE";
	public String INITHEADERZ = "TRUE";




	public transient PrintStream      LPA;
	public transient PrintStream      LPB;
	public transient PrintStream      LPC;
	public transient PrintStream      LPD;
	public transient PrintStream      LPE;
	public transient PrintStream      LPF;	
	public transient PrintStream      LPG;
	public transient PrintStream      LPH;
	public transient PrintStream      LPI;
	public transient PrintStream      LPJ;
	public transient PrintStream      LPK;
	public transient PrintStream      LPL;	
	public transient PrintStream      LPM;
	public transient PrintStream      LPN;
	public transient PrintStream      LPO;
	public transient PrintStream      LPP;
	public transient PrintStream      LPQ;	
	public transient PrintStream      LPR;	
	public transient PrintStream      LPS;
	public transient PrintStream      LPT;
	public transient PrintStream      LPU;
	public transient PrintStream      LPV;
	public transient PrintStream      LPW;
	public transient PrintStream      LPX;
	public transient PrintStream      LPY;
	public transient PrintStream      LPZ;


	public double NUMCOPIES  = 0;
	public double NUMCOPIESA = 0;
	public double NUMCOPIESB = 0;
	public double NUMCOPIESC = 0;
	public double NUMCOPIESD = 0;
	public double NUMCOPIESE = 0;
	public double NUMCOPIESF = 0;
	public double NUMCOPIESG = 0;
	public double NUMCOPIESH = 0;
	public double NUMCOPIESI = 0;
	public double NUMCOPIESJ = 0;
	public double NUMCOPIESK = 0;
	public double NUMCOPIESL = 0;
	public double NUMCOPIESM = 0;
	public double NUMCOPIESN = 0;
	public double NUMCOPIESO = 0;
	public double NUMCOPIESP = 0;
	public double NUMCOPIESQ = 0;
	public double NUMCOPIESR = 0;
	public double NUMCOPIESS = 0;
	public double NUMCOPIEST = 0;
	public double NUMCOPIESU = 0;
	public double NUMCOPIESV = 0;
	public double NUMCOPIESW = 0;
	public double NUMCOPIESX = 0;
	public double NUMCOPIESY = 0;
	public double NUMCOPIESZ = 0;

	public String PRINTER  = "";
	public String PRINTERA = "";
	public String PRINTERB = "";
	public String PRINTERC = "";
	public String PRINTERD = "";
	public String PRINTERE = "";
	public String PRINTERF = "";
	public String PRINTERG = "";
	public String PRINTERH = "";
	public String PRINTERI = "";
	public String PRINTERJ = "";
	public String PRINTERK = "";
	public String PRINTERL = "";
	public String PRINTERM = "";
	public String PRINTERN = "";
	public String PRINTERO = "";
	public String PRINTERP = "";
	public String PRINTERQ = "";
	public String PRINTERR = "";
	public String PRINTERS = "";
	public String PRINTERT = "";
	public String PRINTERU = "";
	public String PRINTERV = "";
	public String PRINTERW = ""; 
	public String PRINTERX = "";
	public String PRINTERY = "";
	public String PRINTERZ = "";


	//REPORT.RFD

	public String   LP_XSEEDREG;
	public String   LPA_XSEEDREG;
	public String   LPB_XSEEDREG;
	public String   LPC_XSEEDREG;
	public String   LPD_XSEEDREG;
	public String   LPE_XSEEDREG;
	public String   LPF_XSEEDREG;
	public String   LPG_XSEEDREG;
	public String   LPH_XSEEDREG;
	public String   LPI_XSEEDREG;
	public String   LPJ_XSEEDREG;
	public String   LPK_XSEEDREG;
	public String   LPL_XSEEDREG;
	public String   LPM_XSEEDREG;
	public String   LPN_XSEEDREG;
	public String   LPO_XSEEDREG;
	public String   LPP_XSEEDREG;
	public String   LPQ_XSEEDREG;
	public String   LPR_XSEEDREG;
	public String   LPS_XSEEDREG;
	public String   LPT_XSEEDREG;
	public String   LPU_XSEEDREG;	
	public String   LPV_XSEEDREG;
	public String   LPW_XSEEDREG;
	public String   LPX_XSEEDREG;
	public String   LPY_XSEEDREG;
	public String   LPZ_XSEEDREG;


	//REPORT.RWS	
	public String   LP_XSEEDTITLE;
	public String   LPA_XSEEDTITLE;
	public String   LPB_XSEEDTITLE;
	public String   LPC_XSEEDTITLE;
	public String   LPD_XSEEDTITLE;
	public String   LPE_XSEEDTITLE;
	public String   LPF_XSEEDTITLE;
	public String   LPG_XSEEDTITLE;
	public String   LPH_XSEEDTITLE;
	public String   LPI_XSEEDTITLE;
	public String   LPJ_XSEEDTITLE;
	public String   LPK_XSEEDTITLE;
	public String   LPL_XSEEDTITLE;
	public String   LPM_XSEEDTITLE;
	public String   LPN_XSEEDTITLE;
	public String   LPO_XSEEDTITLE;
	public String   LPP_XSEEDTITLE;
	public String   LPQ_XSEEDTITLE;
	public String   LPR_XSEEDTITLE;
	public String   LPS_XSEEDTITLE;
	public String   LPT_XSEEDTITLE;
	public String   LPU_XSEEDTITLE;
	public String   LPV_XSEEDTITLE;
	public String   LPW_XSEEDTITLE;
	public String   LPX_XSEEDTITLE;
	public String   LPY_XSEEDTITLE;
	public String   LPZ_XSEEDTITLE;


	public String   LP_XSEEDSTATUS;
	public String   LPA_XSEEDSTATUS;   
	public String   LPB_XSEEDSTATUS;
	public String   LPC_XSEEDSTATUS;   
	public String   LPD_XSEEDSTATUS;
	public String   LPE_XSEEDSTATUS;
	public String   LPF_XSEEDSTATUS;
	public String   LPG_XSEEDSTATUS;   
	public String   LPH_XSEEDSTATUS;
	public String   LPI_XSEEDSTATUS;   
	public String   LPJ_XSEEDSTATUS;
	public String   LPK_XSEEDSTATUS;
	public String   LPL_XSEEDSTATUS;
	public String   LPM_XSEEDSTATUS;   
	public String   LPN_XSEEDSTATUS;
	public String   LPO_XSEEDSTATUS;   
	public String   LPP_XSEEDSTATUS;
	public String   LPQ_XSEEDSTATUS;   
	public String   LPR_XSEEDSTATUS;
	public String   LPS_XSEEDSTATUS;   
	public String   LPT_XSEEDSTATUS;
	public String   LPU_XSEEDSTATUS;   
	public String   LPV_XSEEDSTATUS;
	public String   LPW_XSEEDSTATUS;   
	public String   LPX_XSEEDSTATUS;   
	public String   LPY_XSEEDSTATUS;
	public String   LPZ_XSEEDSTATUS;


	public int      LP_XSEEDEOF;  
	public int      LPA_XSEEDEOF;  
	public int      LPB_XSEEDEOF;  
	public int      LPC_XSEEDEOF;  
	public int      LPD_XSEEDEOF;  
	public int      LPE_XSEEDEOF;  
	public int      LPF_XSEEDEOF;  
	public int      LPG_XSEEDEOF;  
	public int      LPH_XSEEDEOF;  
	public int      LPI_XSEEDEOF;  
	public int      LPJ_XSEEDEOF;  
	public int      LPK_XSEEDEOF;  
	public int      LPL_XSEEDEOF;  
	public int      LPM_XSEEDEOF;  
	public int      LPN_XSEEDEOF;  
	public int      LPO_XSEEDEOF;  
	public int      LPP_XSEEDEOF;  
	public int      LPQ_XSEEDEOF;  
	public int      LPR_XSEEDEOF;  
	public int      LPS_XSEEDEOF;  
	public int      LPT_XSEEDEOF;  
	public int      LPU_XSEEDEOF;  
	public int      LPV_XSEEDEOF;  
	public int      LPW_XSEEDEOF;  
	public int      LPX_XSEEDEOF;  
	public int      LPY_XSEEDEOF;  
	public int      LPZ_XSEEDEOF;     


	public double   PAGECOUNT = 0;
	public double   PAGECOUNTA = 0;
	public double   PAGECOUNTB = 0;
	public double   PAGECOUNTC = 0;
	public double   PAGECOUNTD = 0;
	public double   PAGECOUNTE = 0;
	public double   PAGECOUNTF = 0;
	public double   PAGECOUNTG = 0;
	public double   PAGECOUNTH = 0;
	public double   PAGECOUNTI = 0;
	public double   PAGECOUNTJ = 0;
	public double   PAGECOUNTK = 0;
	public double   PAGECOUNTL = 0;
	public double   PAGECOUNTM = 0;
	public double   PAGECOUNTN = 0;
	public double   PAGECOUNTO = 0;
	public double   PAGECOUNTP = 0;
	public double   PAGECOUNTQ = 0;
	public double   PAGECOUNTR = 0;
	public double   PAGECOUNTS = 0;
	public double   PAGECOUNTT = 0;
	public double   PAGECOUNTU = 0;
	public double   PAGECOUNTV = 0;
	public double   PAGECOUNTW = 0;
	public double   PAGECOUNTX = 0;
	public double   PAGECOUNTY = 0;
	public double   PAGECOUNTZ = 0;           


	public double   LINECOUNT = 0;		
	public double   LINECOUNTA = 0;		
	public double   LINECOUNTB = 0;		
	public double   LINECOUNTC = 0;
	public double   LINECOUNTD = 0;		
	public double   LINECOUNTE = 0;		
	public double   LINECOUNTF = 0;		
	public double   LINECOUNTG = 0;			
	public double   LINECOUNTH = 0;		
	public double   LINECOUNTI = 0;		
	public double   LINECOUNTJ = 0;		
	public double   LINECOUNTK = 0;		
	public double   LINECOUNTL = 0;		
	public double   LINECOUNTM = 0;		
	public double   LINECOUNTN = 0;		
	public double   LINECOUNTO = 0;			
	public double   LINECOUNTP = 0;		
	public double   LINECOUNTQ = 0;		
	public double   LINECOUNTR = 0;		
	public double   LINECOUNTS = 0;			
	public double   LINECOUNTT = 0;		
	public double   LINECOUNTU = 0;		
	public double   LINECOUNTV = 0;		
	public double   LINECOUNTW = 0;			
	public double   LINECOUNTX = 0;		
	public double   LINECOUNTY = 0;		
	public double   LINECOUNTZ = 0;		


	public double   FORMDEPTH = 60;
	public double   FORMDEPTHA = 60;
	public double   FORMDEPTHB = 60;
	public double   FORMDEPTHC = 60;
	public double   FORMDEPTHD = 60;
	public double   FORMDEPTHE = 60;
	public double   FORMDEPTHF = 60;
	public double   FORMDEPTHG = 60;
	public double   FORMDEPTHH = 60;
	public double   FORMDEPTHI = 60;
	public double   FORMDEPTHJ = 60;
	public double   FORMDEPTHK = 60;
	public double   FORMDEPTHL = 60;
	public double   FORMDEPTHM = 60;
	public double   FORMDEPTHN = 60;
	public double   FORMDEPTHO = 60;
	public double   FORMDEPTHP = 60;
	public double   FORMDEPTHQ = 60;
	public double   FORMDEPTHR = 60;
	public double   FORMDEPTHS = 60;
	public double   FORMDEPTHT = 60;
	public double   FORMDEPTHU = 60;
	public double   FORMDEPTHV = 60;
	public double   FORMDEPTHW = 60;
	public double   FORMDEPTHX = 60;
	public double   FORMDEPTHY = 60;
	public double   FORMDEPTHZ = 60;

	public String  FORMID = " ";
	public String  FORMIDA = " ";
	public String  FORMIDB = " ";
	public String  FORMIDC = " ";
	public String  FORMIDD = " ";
	public String  FORMIDE = " ";
	public String  FORMIDF = " ";
	public String  FORMIDG = " ";
	public String  FORMIDI = " ";
	public String  FORMIDH = " ";
	public String  FORMIDJ = " ";
	public String  FORMIDK = " ";
	public String  FORMIDL = " ";
	public String  FORMIDM = " ";
	public String  FORMIDN = " ";
	public String  FORMIDO = " ";
	public String  FORMIDP = " ";
	public String  FORMIDQ = " ";
	public String  FORMIDR = " ";
	public String  FORMIDS = " ";
	public String  FORMIDT = " ";
	public String  FORMIDU = " ";
	public String  FORMIDV = " ";
	public String  FORMIDW = " ";
	public String  FORMIDX = " "; 
	public String  FORMIDY = " ";
	public String  FORMIDZ = " ";

	public String   TITLE = "";	
	public String   TITLEA = "";	
	public String   TITLEB = "";	
	public String   TITLEC = "";	
	public String   TITLED = "";	
	public String   TITLEE = "";	
	public String   TITLEF = "";	
	public String   TITLEG = "";	
	public String   TITLEH = "";	
	public String   TITLEI = "";	
	public String   TITLEJ = "";	
	public String   TITLEK = "";	
	public String   TITLEL = "";	
	public String   TITLEM = "";	
	public String   TITLEN = "";	
	public String   TITLEO = "";	
	public String   TITLEP = "";	
	public String   TITLEQ = "";	
	public String   TITLER = "";	
	public String   TITLES = "";	
	public String   TITLET = "";	
	public String   TITLEU = "";	
	public String   TITLEV = "";	
	public String   TITLEW = "";	
	public String   TITLEX = "";	
	public String   TITLEY = "";	
	public String   TITLEZ = "";	

	public boolean  INITREPORT  = false;
	public boolean  INITREPORTA = false;
	public boolean  INITREPORTB = false;
	public boolean  INITREPORTC = false;
	public boolean  INITREPORTD = false;
	public boolean  INITREPORTE = false;
	public boolean  INITREPORTF = false;
	public boolean  INITREPORTG = false;
	public boolean  INITREPORTH = false;
	public boolean  INITREPORTI = false;
	public boolean  INITREPORTJ = false;
	public boolean  INITREPORTK = false;
	public boolean  INITREPORTL = false;
	public boolean  INITREPORTM = false;
	public boolean  INITREPORTN = false;
	public boolean  INITREPORTO = false;
	public boolean  INITREPORTP = false;
	public boolean  INITREPORTQ = false;
	public boolean  INITREPORTR = false;
	public boolean  INITREPORTS = false;
	public boolean  INITREPORTT = false;
	public boolean  INITREPORTU = false;
	public boolean  INITREPORTV = false;
	public boolean  INITREPORTW = false;
	public boolean  INITREPORTX = false;
	public boolean  INITREPORTY = false;
	public boolean  INITREPORTZ = false;

	public boolean  ADVANCE  = true;
	public boolean  ADVANCEA = true;
	public boolean  ADVANCEB = true;
	public boolean  ADVANCEC = true;
	public boolean  ADVANCED = true;
	public boolean  ADVANCEE = true;
	public boolean  ADVANCEF = true;
	public boolean  ADVANCEG = true;
	public boolean  ADVANCEH = true;
	public boolean  ADVANCEI = true;
	public boolean  ADVANCEJ = true;
	public boolean  ADVANCEK = true;
	public boolean  ADVANCEL = true;
	public boolean  ADVANCEM = true;
	public boolean  ADVANCEN = true;
	public boolean  ADVANCEO = true;	
	public boolean  ADVANCEP = true;
	public boolean  ADVANCEQ = true;
	public boolean  ADVANCER = true;
	public boolean  ADVANCES = true;
	public boolean  ADVANCET = true;
	public boolean  ADVANCEU = true;
	public boolean  ADVANCEV = true;
	public boolean  ADVANCEW = true;
	public boolean  ADVANCEX = true;
	public boolean  ADVANCEY = true;
	public boolean  ADVANCEZ = true;

	public double   RANDOM = 0;
	public double   RECORD = 0;
	public double   RECORDA = 0;
	public double   RECORDB = 0;
	public double   RECORDC = 0;
	public double   RECORDD = 0;
	public double   RECORDE = 0;
	public double   RECORDF = 0;
	public double   RECORDG = 0;
	public double   RECORDH = 0;
	public double   RECORDI = 0;
	public double   RECORDJ = 0;
	public double   RECORDK = 0;
	public double   RECORDL = 0;
	public double   RECORDM = 0;
	public double   RECORDN = 0;
	public double   RECORDO = 0;
	public double   RECORDP = 0;
	public double   RECORDQ = 0;
	public double   RECORDR = 0;
	public double   RECORDS = 0;
	public double   RECORDT = 0;
	public double   RECORDU = 0;
	public double   RECORDV = 0;
	public double   RECORDW = 0;
	public double   RECORDX = 0;
	public double   RECORDY = 0;
	public double   RECORDZ = 0;	


	public String   STATION = "";
	public String   STATIONA = "";
	public String   STATIONB = "";
	public String   STATIONC = "";
	public String   STATIOND = "";
	public String   STATIONE = "";
	public String   STATIONF = "";
	public String   STATIONG = "";
	public String   STATIONH = "";
	public String   STATIONI = "";
	public String   STATIONJ = "";
	public String   STATIONK = "";
	public String   STATIONL = "";
	public String   STATIONM = "";
	public String   STATIONN = "";
	public String   STATIONO = "";
	public String   STATIONP = "";
	public String   STATIONQ = "";
	public String   STATIONR = "";
	public String   STATIONS = "";
	public String   STATIONT = "";
	public String   STATIONU = "";
	public String   STATIONV = "";
	public String   STATIONW = "";
	public String   STATIONX = "";
	public String   STATIONY = "";
	public String   STATIONZ = "";

	public String   STN  = "";
	public String   STNA = "";
	public String   STNB = "";
	public String   STNC = "";
	public String   STND = "";
	public String   STNE = "";
	public String   STNF = "";
	public String   STNG = "";
	public String   STNH = "";
	public String   STNI = "";
	public String   STNJ = "";
	public String   STNK = "";
	public String   STNL = "";
	public String   STNM = "";
	public String   STNN = "";
	public String   STNO = "";
	public String   STNP = "";
	public String   STNQ = "";	
	public String   STNR = "";
	public String   STNS = "";
	public String   STNT = "";
	public String   STNU = "";	
	public String   STNV = "";
	public String   STNW = "";
	public String   STNX = "";
	public String   STNY = "";	
	public String   STNZ = "";

	public String   DEVICE = "";  
	public String   DEVICEA = "";  
	public String   DEVICEB = "";  
	public String   DEVICEC = "";  
	public String   DEVICED = "";  
	public String   DEVICEE = "";  
	public String   DEVICEF = "";  
	public String   DEVICEG = "";  
	public String   DEVICEH = "";  
	public String   DEVICEI = "";  	
	public String   DEVICEJ = "";  
	public String   DEVICEK = "";  
	public String   DEVICEL = "";  
	public String   DEVICEM = "";  
	public String   DEVICEN = "";  
	public String   DEVICEO = "";  
	public String   DEVICEP = "";  
	public String   DEVICEQ = "";  
	public String   DEVICER = "";  
	public String   DEVICES = "";  
	public String   DEVICET = "";  
	public String   DEVICEU = "";  
	public String   DEVICEV = "";  
	public String   DEVICEW = "";  
	public String   DEVICEX = "";  
	public String   DEVICEY = "";  
	public String   DEVICEZ = "";  	


	public double   SAVEDAYS  = 0; 
	public double   SAVEDAYSA = 0; 
	public double   SAVEDAYSB = 0; 
	public double   SAVEDAYSC = 0; 
	public double   SAVEDAYSD = 0; 
	public double   SAVEDAYSE = 0; 
	public double   SAVEDAYSF = 0; 
	public double   SAVEDAYSG = 0; 
	public double   SAVEDAYSH = 0; 
	public double   SAVEDAYSI = 0; 
	public double   SAVEDAYSJ = 0; 
	public double   SAVEDAYSK = 0; 
	public double   SAVEDAYSL = 0; 
	public double   SAVEDAYSM = 0; 
	public double   SAVEDAYSN = 0; 
	public double   SAVEDAYSO = 0; 
	public double   SAVEDAYSP = 0; 
	public double   SAVEDAYSQ = 0; 
	public double   SAVEDAYSR = 0; 
	public double   SAVEDAYSS = 0; 
	public double   SAVEDAYST = 0; 
	public double   SAVEDAYSU = 0; 
	public double   SAVEDAYSV = 0; 
	public double   SAVEDAYSW = 0; 
	public double   SAVEDAYSX = 0; 
	public double   SAVEDAYSY = 0; 
	public double   SAVEDAYSZ = 0; 

	public String   SHADOW = " ";
	public String   SHADOWA = " ";
	public String   SHADOWB = " ";
	public String   SHADOWC = " ";
	public String   SHADOWD = " ";
	public String   SHADOWE = " ";
	public String   SHADOWF = " ";
	public String   SHADOWG = " ";
	public String   SHADOWH = " ";
	public String   SHADOWI = " ";
	public String   SHADOWJ = " ";
	public String   SHADOWK = " ";
	public String   SHADOWL = " ";
	public String   SHADOWM = " ";
	public String   SHADOWN = " ";
	public String   SHADOWO = " ";
	public String   SHADOWP = " ";
	public String   SHADOWQ = " ";
	public String   SHADOWR = " ";
	public String   SHADOWS = " ";
	public String   SHADOWT = " ";
	public String   SHADOWU = " ";
	public String   SHADOWV = " ";
	public String   SHADOWW = " ";
	public String   SHADOWX = " ";
	public String   SHADOWY = " ";
	public String   SHADOWZ = " ";


	public StringBuffer   EXA_XSEEDBUFFER = new  StringBuffer();
	public StringBuffer   EXB_XSEEDBUFFER = new  StringBuffer();
	public StringBuffer   EXC_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXD_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXE_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXF_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXG_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXH_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXI_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXJ_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXK_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXL_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXM_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXN_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXO_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXP_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXQ_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXR_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXS_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXT_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXU_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXV_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXW_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXX_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXY_XSEEDBUFFER = new StringBuffer();
	public StringBuffer   EXZ_XSEEDBUFFER = new StringBuffer();

	public int   EXA_XSEEDCOUNT = 0;
	public int   EXB_XSEEDCOUNT = 0;
	public int   EXC_XSEEDCOUNT = 0;
	public int   EXD_XSEEDCOUNT = 0;
	public int   EXE_XSEEDCOUNT = 0;
	public int   EXF_XSEEDCOUNT = 0;		
	public int   EXG_XSEEDCOUNT = 0;
	public int   EXH_XSEEDCOUNT = 0;
	public int   EXI_XSEEDCOUNT = 0;
	public int   EXJ_XSEEDCOUNT = 0;
	public int   EXK_XSEEDCOUNT = 0;
	public int   EXL_XSEEDCOUNT = 0;
	public int   EXM_XSEEDCOUNT = 0;
	public int   EXN_XSEEDCOUNT = 0;
	public int   EXO_XSEEDCOUNT = 0;
	public int   EXP_XSEEDCOUNT = 0;
	public int   EXQ_XSEEDCOUNT = 0;
	public int   EXR_XSEEDCOUNT = 0;
	public int   EXS_XSEEDCOUNT = 0;
	public int   EXT_XSEEDCOUNT = 0;
	public int   EXU_XSEEDCOUNT = 0;
	public int   EXV_XSEEDCOUNT = 0;
	public int   EXW_XSEEDCOUNT = 0;
	public int   EXX_XSEEDCOUNT = 0;
	public int   EXY_XSEEDCOUNT = 0;
	public int   EXZ_XSEEDCOUNT = 0;	

	public String   EXA_XSEEDTITLE = "";
	public String   EXB_XSEEDTITLE = "";
	public String   EXC_XSEEDTITLE = "";
	public String   EXD_XSEEDTITLE = "";
	public String   EXE_XSEEDTITLE = "";
	public String   EXF_XSEEDTITLE = "";        
	public String   EXG_XSEEDTITLE = "";
	public String   EXH_XSEEDTITLE = "";
	public String   EXI_XSEEDTITLE = "";
	public String   EXJ_XSEEDTITLE = "";
	public String   EXK_XSEEDTITLE = "";
	public String   EXL_XSEEDTITLE = "";
	public String   EXM_XSEEDTITLE = "";
	public String   EXN_XSEEDTITLE = "";
	public String   EXO_XSEEDTITLE = "";
	public String   EXP_XSEEDTITLE = "";
	public String   EXQ_XSEEDTITLE = "";
	public String   EXR_XSEEDTITLE = "";
	public String   EXS_XSEEDTITLE = "";
	public String   EXT_XSEEDTITLE = "";
	public String   EXU_XSEEDTITLE = "";
	public String   EXV_XSEEDTITLE = "";
	public String   EXW_XSEEDTITLE = "";
	public String   EXX_XSEEDTITLE = "";
	public String   EXY_XSEEDTITLE = "";
	public String   EXZ_XSEEDTITLE = "";   

	public String   EXA_STATUS = "";
	public String   EXB_STATUS = "";
	public String   EXC_STATUS = "";
	public String   EXD_STATUS = "";
	public String   EXE_STATUS = "";
	public String   EXF_STATUS = "";
	public String   EXG_STATUS = "";
	public String   EXH_STATUS = "";
	public String   EXI_STATUS = "";
	public String   EXJ_STATUS = "";
	public String   EXK_STATUS = "";
	public String   EXL_STATUS = "";
	public String   EXM_STATUS = "";
	public String   EXN_STATUS = "";
	public String   EXO_STATUS = "";
	public String   EXP_STATUS = "";    
	public String   EXQ_STATUS = "";
	public String   EXR_STATUS = "";
	public String   EXS_STATUS = "";
	public String   EXT_STATUS = "";
	public String   EXU_STATUS = "";
	public String   EXV_STATUS = "";        
	public String   EXW_STATUS = "";
	public String   EXX_STATUS = "";
	public String   EXY_STATUS = "";
	public String   EXZ_STATUS = "";

	public double   EXA_XSEEDREADS = 0;
	public double   EXB_XSEEDREADS = 0;
	public double   EXC_XSEEDREADS = 0;
	public double   EXD_XSEEDREADS = 0;
	public double   EXE_XSEEDREADS = 0;
	public double   EXF_XSEEDREADS = 0;		
	public double   EXG_XSEEDREADS = 0;
	public double   EXH_XSEEDREADS = 0;
	public double   EXI_XSEEDREADS = 0;
	public double   EXJ_XSEEDREADS = 0;
	public double   EXK_XSEEDREADS = 0;
	public double   EXL_XSEEDREADS = 0;
	public double   EXM_XSEEDREADS = 0;
	public double   EXN_XSEEDREADS = 0;
	public double   EXO_XSEEDREADS = 0;
	public double   EXP_XSEEDREADS = 0;
	public double   EXQ_XSEEDREADS = 0;
	public double   EXR_XSEEDREADS = 0;
	public double   EXS_XSEEDREADS = 0;
	public double   EXT_XSEEDREADS = 0;
	public double   EXU_XSEEDREADS = 0;
	public double   EXV_XSEEDREADS = 0;
	public double   EXW_XSEEDREADS = 0;
	public double   EXX_XSEEDREADS = 0;
	public double   EXY_XSEEDREADS = 0;
	public double   EXZ_XSEEDREADS = 0;

	public double   EXA_XSEEDWRITES = 0;
	public double   EXB_XSEEDWRITES = 0;
	public double   EXC_XSEEDWRITES = 0;
	public double   EXD_XSEEDWRITES = 0;
	public double   EXE_XSEEDWRITES = 0;
	public double   EXF_XSEEDWRITES = 0;		
	public double   EXG_XSEEDWRITES = 0;
	public double   EXH_XSEEDWRITES = 0;
	public double   EXI_XSEEDWRITES = 0;
	public double   EXJ_XSEEDWRITES = 0;
	public double   EXK_XSEEDWRITES = 0;
	public double   EXL_XSEEDWRITES = 0;
	public double   EXM_XSEEDWRITES = 0;
	public double   EXN_XSEEDWRITES = 0;
	public double   EXO_XSEEDWRITES = 0;
	public double   EXP_XSEEDWRITES = 0;
	public double   EXQ_XSEEDWRITES = 0;
	public double   EXR_XSEEDWRITES = 0;
	public double   EXS_XSEEDWRITES = 0;
	public double   EXT_XSEEDWRITES = 0;
	public double   EXU_XSEEDWRITES = 0;
	public double   EXV_XSEEDWRITES = 0;
	public double   EXW_XSEEDWRITES = 0;
	public double   EXX_XSEEDWRITES = 0;
	public double   EXY_XSEEDWRITES = 0;
	public double   EXZ_XSEEDWRITES = 0;


	public double   PRINT_XSEEDWRITES = 0;
	public double   PRINTA_XSEEDWRITES = 0;
	public double   PRINTB_XSEEDWRITES = 0;
	public double   PRINTC_XSEEDWRITES = 0;
	public double   PRINTD_XSEEDWRITES = 0;
	public double   PRINTE_XSEEDWRITES = 0;
	public double   PRINTF_XSEEDWRITES = 0;
	public double   PRINTG_XSEEDWRITES = 0;
	public double   PRINTH_XSEEDWRITES = 0;
	public double   PRINTI_XSEEDWRITES = 0;
	public double   PRINTJ_XSEEDWRITES = 0;
	public double   PRINTK_XSEEDWRITES = 0;
	public double   PRINTL_XSEEDWRITES = 0;
	public double   PRINTM_XSEEDWRITES = 0;
	public double   PRINTN_XSEEDWRITES = 0;
	public double   PRINTO_XSEEDWRITES = 0;
	public double   PRINTP_XSEEDWRITES = 0;
	public double   PRINTQ_XSEEDWRITES = 0;
	public double   PRINTR_XSEEDWRITES = 0;
	public double   PRINTS_XSEEDWRITES = 0;
	public double   PRINTT_XSEEDWRITES = 0;
	public double   PRINTU_XSEEDWRITES = 0;
	public double   PRINTV_XSEEDWRITES = 0;
	public double   PRINTW_XSEEDWRITES = 0;
	public double   PRINTX_XSEEDWRITES = 0;
	public double   PRINTY_XSEEDWRITES = 0;
	public double   PRINTZ_XSEEDWRITES = 0;


        // Recovery based on Integrity Points
        public String RECOVER_JOB_ID = "";
        public double RECOVER_QUEUE_ID = 0.0;
        public String RECOVER_STATUS = "";
        public double RECOVER_IP = 0.0;


//	DUVIDA	
//RJGREP ? TEM NUMERO DO FRAME	
		//
		//public String REPTITLE = "";	
//public String STATUSCODE = "  ";	
	//public String START = "";
	//public boolean UPDATE = false;		
	//public int MSGCOPY[];	

	//public int DBCURSORLOCATION = 0;  //Variaveis nao estao sendo utilizadas
	//int DBCURSORTYPE = 0;
	//int DBCACHESIZE = 0;
	//int DBLOCKTYPE = 0;

	//public String DBTYPE = "";				


	public XseedGLBRecovery()  
	{
		DecimalChar = getDecimalSeparator();	
		if (DecimalChar == ',') 
		{    SeparatorChar = '.';
		MASKDECIMALCHAR = ",";  
		}
		else if  (DecimalChar == '.') 
		{  SeparatorChar = ',';
		MASKDECIMALCHAR = ".";
		}

		for (int i=0; i < MSGHEADER.length; i++)
		{
			MSGHEADER[i]="";
			MSGTRAILER[i]="";	 	
		}
	}

	public char getDecimalSeparator()  
	{
		DecimalFormatSymbols sb=new DecimalFormatSymbols();
		return sb.getDecimalSeparator();
	}

}