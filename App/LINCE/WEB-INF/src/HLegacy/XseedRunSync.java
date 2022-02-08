package HLegacy;
import java.io.*;
import java.text.*;
import java.util.*;
import java.lang.reflect.*;

public class XseedRunSync
{
static String batchPath = "";
static String classPath = "";
static String msgPath = "";
static String queueNumber = "";	
static String appIniPath = "";
static String initMsg = "Executando ...";
static String endMsg = "Finalizando ...";
static long timer = 0;        
	
static String initSTN = "XSEED";
static String param = "";
static String device="";
	
	
public static void main (String args[])
{ 
   try
   {	
       int fileIndex =0;
       boolean msgAbort = false;
       // deve ser preenchida com o nome do report a ser executado
  			
       String fileName;
       String fileReport;
       String fileExt;
       File filesList[];
       File f;  

       queueNumber = args[0].trim();						
	

       readXseedRunIni();  			  			
			
       showMsg(initMsg);  			
       showMsg("QueueNumber = " + queueNumber);  			
       showMsg("Timer = " + timer);  			       
       showMsg("AppIniPath = " + appIniPath);  			
       showMsg("BatchPath = " + batchPath);  			
       showMsg("ClassPath = " + classPath);  			
       showMsg("MsgsPath = " + msgPath);

       
       boolean x = true;
       
      LOOP:
      {
       while (x) 
       {
        // Processamento de um item 
        try          	            
       	{                                  	
       	   msgAbort = checkMsg();
       	   if (msgAbort == true)
       	   {
       	      break LOOP;	
       	   }

           System.gc();
       	     		
           XseedSleep xSleep = new XseedSleep();
           Thread threadSleep = new Thread(xSleep);			 	
           threadSleep.start();				
           threadSleep.sleep(timer);				
           threadSleep.join();   
           threadSleep=null;				 							

           f = new File(batchPath); 
           filesList = f.listFiles();	  			
			
           fileIndex =0;
           while (fileIndex < filesList.length)
           {
           	  msgAbort = checkMsg();
       	      if (msgAbort == true)
       	      {
                 break LOOP;	
       	      }

               fileName = filesList[fileIndex].getName();	
               
               fileIndex++;				

               // Somente tratar arquivos com extensao .RUN<fila>       
               String extensaoArquivo    = "";                          
               String extensaoArquivoRUN = ".RUN" + queueNumber;        
               int posExtRun = fileName.indexOf(extensaoArquivoRUN);    
               if (posExtRun != -1)                                     
               {                                                        
                 extensaoArquivo = fileName.substring(posExtRun);       
                 if (extensaoArquivo.equals(extensaoArquivoRUN))        
                 {                                                      
                
                  int ind1 = fileName.indexOf(')');
                  int ind2 = fileName.indexOf('.');
						  			    
                  if (ind1 != -1) 
                  { 
		            fileReport = fileName.substring(ind1+1,ind2);					
		            fileExt = fileName.substring(ind2+1);					
	  			
                    if (fileExt.equals("RUN"+ queueNumber) == true)
                    {
                       //substituit .RUN por .RUN(Readed)
                       File fileSource = new File(batchPath + File.separatorChar + fileName);
                       File fileDest = new File(batchPath + File.separatorChar + fileName + "(Readed)");
                       fileSource.renameTo(fileDest);
			    	
                       readReportRun(batchPath + File.separatorChar + fileName + "(Readed)");  			  						  													
                       showMsg(" ");
                       showMsg("Starting  " + fileName);
                       ExecRunSync(fileReport);                    
                       showMsg("Finishing " + fileName);
                    }				
                  }
                  
                 	
                }   // Extensao .RUN<fila>
              }     // Extensao .RUN<fila>
               
           }
           filesList = null;
           f = null;
           
          
          // Processamento de um item 
          }                           
          catch (Exception e)         
          {                           
            auditErr("* Error processing queue. Details: " + errorDetails(e) ); 
          }                                                                     
          // Tratamento de Erros de um item  
           
   	   } // final de while (x) 
      } // final de LOOP
   }
   catch (Exception e)
   {                                                                            
       auditErr("* Fatal Error. Details: " + errorDetails(e));                  
   }                                                                            
}
  
private static void readXseedRunIni ()  	
throws Exception
{ 	
	    String runIniLine;			
			
		FileInputStream runIniStream = new FileInputStream("XseedRun" + queueNumber + ".ini");
		BufferedReader  runIni = new BufferedReader(new InputStreamReader(runIniStream));
		runIniLine = "";

		while (runIniLine != null)
		{
			runIniLine = runIni.readLine();

			if (runIniLine != null)
			{
			   getXseedRunOptions(runIniLine);
			}
		}

		runIni = null;
		runIniStream = null;
}
	
private static void getXseedRunOptions (String pLine)  	
throws Exception

{   
       String beforeWord;
       String afterWord;
       int i;
       i = pLine.indexOf(";");

       if (i == -1)
       { 
          i = pLine.indexOf("=");
       }

       if (i != -1)
       {
          beforeWord = pLine.substring(0, i).toUpperCase();
          afterWord = pLine.substring(i + 1);

          if (beforeWord.trim().toUpperCase().equals("APPINIPATH") )
             appIniPath = afterWord.trim();

          else if (beforeWord.trim().toUpperCase().equals("BATCHPATH") )
             batchPath = afterWord.trim();				

          else if (beforeWord.trim().toUpperCase().equals("CLASSPATH") )
             classPath = afterWord.trim();	

          else if (beforeWord.trim().toUpperCase().equals("MSGSPATH") )
             msgPath = afterWord.trim();	

          else if (beforeWord.trim().toUpperCase().equals("INITMSG") )
             initMsg = afterWord.trim();

          else if (beforeWord.trim().toUpperCase().equals("ENDMSG") )
             endMsg = afterWord.trim();					

          else if (beforeWord.trim().toUpperCase().equals("TIMER") )
          {	
             timer = Long.valueOf(afterWord.trim()).longValue();
          }				
       } 
}
  
private static void readReportRun (String pRunFileName)  	
throws Exception

{ 	
      String runIniLine;			
      String afterWord;
      String beforeWord;
      int i;
	
      FileInputStream runIniStream = new FileInputStream(pRunFileName);
      BufferedReader  runIni = new BufferedReader(new InputStreamReader(runIniStream));

      runIniLine = "";
      
      while (runIniLine != null)
      {   
          runIniLine = runIni.readLine();

          if (runIniLine != null)
          {
             i = runIniLine.indexOf(";");
             
             if (i == -1)
             {
                i = runIniLine.indexOf("=");
             }

             if (i != -1)
             {
                beforeWord = runIniLine.substring(0, i).toUpperCase();
                afterWord = runIniLine.substring(i + 2); 

                if (beforeWord.trim().toUpperCase().equals("GLB_INITSTN") )
                   initSTN = afterWord.trim();
                else if (beforeWord.trim().toUpperCase().equals("GLB_PARAM") )
                   param = XseedFunctions.rTrim(afterWord);			
                else if (beforeWord.trim().toUpperCase().equals("GLB_DEVICE") )
                   device = afterWord.trim();            
             }
          }
      }
      runIni = null;
      runIniStream = null;						
}

private static boolean checkMsg ()  	
throws Exception

{ 	
      String checkName = msgPath + File.separatorChar + "XseedRun" + queueNumber + ".msg";
      File   checkFile;
      boolean deleteStatus;

      /* Verificando se arquivo existe */
      checkFile = new File(checkName);
      if (checkFile.exists() == false)
      {
        return false;  // --> continua executando XseedRun 
      }
      
      /* Lendo conteudo do arquivo */
      String msgLine;
      
      msgLine = readMsg(checkName);			

      /* Removendo arquivo de mensagem */
      if (msgLine != null)
      {
         if (msgLine.trim().toUpperCase().equals("STOP"))
         {
            showMsg("Stopping by user message ...");

            checkFile = new File(checkName);
            if (checkFile.exists() == true)
            {
               deleteStatus = checkFile.delete();
               if (deleteStatus == false)
               { 
                  showMsg("Couldn't remove " + checkName);
               }
            }

            return true;  // --> aborta execução do XseedRun
         }
         else if (msgLine.trim().toUpperCase().equals("ABORT") )
         {
            showMsg("Aborting by user message ...");

            checkFile = new File(checkName);
            if (checkFile.exists() == true)
            {
               deleteStatus = checkFile.delete();
               if (deleteStatus == false)
               { 
                  showMsg("Couldn't remove " + checkName);
               }
            }

            return true;  // --> aborta execução do XseedRun
         }
         else 
         {
            showMsg(msgLine);

            checkFile = new File(checkName);
            if (checkFile.exists() == true)
            {
               deleteStatus = checkFile.delete();
               if (deleteStatus == false)
               { 
                  showMsg("Couldn't remove " + checkName);
               }
            }

            return false;  // --> continua executando XseedRun
         }
      }
      
      return false;  // --> continua executando XseedRun

}

private static String readMsg (String pFilename)  	
throws Exception

{ 	
      /* Lendo conteudo do arquivo */
      String msgLine;			

      FileInputStream msgStream = new FileInputStream(pFilename);
      BufferedReader  msgFile = new BufferedReader(new InputStreamReader(msgStream));

      msgLine = "";
      
      while (msgLine != null)
      {   
          msgLine = msgFile.readLine();
          break;
      }

      msgFile.close();
      msgStream.close();
      
      msgFile = null;
      msgStream = null;

      return msgLine;

}

// Escreve mensagem no arquivo de Log, no diretorio Batch. Se nao conseguir escrever direciona para o Alert.
	
private static void showMsg(String pMsg)
{
   try
   {
       Date dateToday = new Date();

       String msg = "MSG:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " --> " + pMsg;
   	
       System.out.println(msg);
				
       //gravar em arquivo de log
       FileOutputStream logFile = new FileOutputStream(batchPath + File.separatorChar + "XseedRun" + queueNumber + ".log", true);
       PrintStream log = new PrintStream(logFile);			
       log.println(msg);
       log.close();
       logFile.close();			
    }
    catch (Exception e)
    {
        auditCrash (pMsg);                    
    }
}

// Escreve erro no arquivo de Log, no diretorio Batch

private static void auditErr (String pMsg)
{
   try
   {
       Date dateToday = new Date();

       String msg = "ERR:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " --> " + pMsg;
   	
       System.out.println(msg);
				
       //gravar em arquivo de log
       FileOutputStream logFile = new FileOutputStream(batchPath + File.separatorChar + "XseedRun" + queueNumber + ".log", true);
       PrintStream log = new PrintStream(logFile);			
       log.println(msg);
       log.close();
       logFile.close();			
    }
    catch (Exception e)
    {
        auditCrash (pMsg);                    
    }
}

// Escreve erro no arquivo de Alert, qdo nao for possivel escrever o arquivo de Log

private static void auditCrash(String pMsg)
{
   try
   {
       Date dateToday = new Date();

       String msg = "ERR:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " --> " + pMsg;
   				
       //gravar em arquivo temporario
       FileOutputStream logFile = new FileOutputStream( "XseedRun" + queueNumber + ".Alert", true);
       PrintStream log = new PrintStream(logFile);			
       log.println(msg);
       log.close();
       logFile.close();			
    }
    catch (Exception e)
    {
       System.out.println(pMsg);  // Manda para saida padrao em ultimo caso.
    }
}

// Recupera Pilha de Erros detectados

private static String errorDetails (Exception e)
{
  try 
  {	
     StringWriter sw = new StringWriter();
     PrintWriter pw = new PrintWriter(sw);            
     e.printStackTrace(pw);
     return sw.toString();
  }   
  catch (Exception ex) 
  {  return "<no details>"; }
}

private static String format (Date pDate, String pMask)
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
        System.out.println(e.getMessage());
        return ("");
    }	
}

private static void ExecRunSync(String parNmReport) throws Exception


{

    String methodNameRemote = "remote" ;
    String methodNameSet = "setGlbParam" ;
    String methodNameRun = "runSync" ;
    
    String methodNameRep = "getReportFilename" ;
    String methodNamePar = "getGlbParam" ;
    String methodNameTas = "getGlbTask" ;
    String methodNameSta = "getGlbRepStatus" ;
    String localPARAM = "";
    String reportFilename = "";
    
    double localTASK = 0;

    String localREPSTATUS = "";
    Class xsdClass;
    Object xsdObj;
    Method xsdMethod; 

    Object[] initargs = new Object[] {initSTN, param, appIniPath, device};    
    Object[] paramValues = new Object[] {param};

    xsdClass = Class.forName(parNmReport);
    xsdObj = xsdClass.newInstance();    
    
    xsdMethod = xsdClass.getMethod(methodNameRemote,  new Class[] { String.class, String.class, String.class,String.class });
    xsdMethod.invoke(xsdObj, initargs);
    
    xsdMethod = xsdClass.getMethod(methodNameSet, new Class[] { String.class });
    xsdMethod.invoke(xsdObj, paramValues);

    xsdMethod = xsdClass.getMethod(methodNameRun, null);
    xsdMethod.invoke(xsdObj, null);
    
    xsdMethod = xsdClass.getMethod(methodNameRep, null);
    reportFilename = (String) xsdMethod.invoke(xsdObj, null);

    xsdMethod = xsdClass.getMethod(methodNamePar, null);
    localPARAM = (String) xsdMethod.invoke(xsdObj, null);

    xsdMethod = xsdClass.getMethod(methodNameTas, null);
    localTASK = ((Double) xsdMethod.invoke(xsdObj, null)).doubleValue();    
    
    xsdMethod = xsdClass.getMethod(methodNameSta, null);
    localREPSTATUS = (String) xsdMethod.invoke(xsdObj, null);   


}


} // Final da Classe
