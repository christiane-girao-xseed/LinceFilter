package HLegacy;
import java.io.*;
import java.text.*;
import java.util.*;

public class XseedRun
{
static String batchPath = "";
static String classPath = "";
static String msgPath = "";
static String queueNumber = "";	
static String asyncMode = "FALSE";
static String appIniPath = "";
static String initMsg = "Executando ...";
static String endMsg = "Finalizando ...";
static long timer = 0;        
	
static String initSTN = "XSEED";
static String param = "";
	
	
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
       showMsg("AsyncMode = " + asyncMode);  			
       showMsg("AppIniPath = " + appIniPath);  			
       showMsg("BatchPath = " + batchPath);  			
       showMsg("ClassPath = " + classPath);  			
       showMsg("MsgsPath = " + msgPath);

       
       boolean x = true;
       
      LOOP:
      {
       while (x) 
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
  							
                     XseedRepList repList = new XseedRepList(fileReport,initSTN, param,appIniPath,classPath,asyncMode);
                     Thread threadRepList = new Thread (repList);
                     threadRepList.start();
    					
                     if (asyncMode.equals("FALSE") == true)
                     {
                        threadRepList.sleep(timer);				
                        threadRepList.join();   	
                        threadRepList = null;					    	
                        showMsg("Finishing " + fileName);
                     }
                     else
                     {
                        showMsg("Run.Async " + fileName + "...");
                     }
                  }				
               } 	
           }
           filesList = null;
           f = null;
   	   } // final de while (x) 
      } // final de LOOP
   }
   catch (Exception e)
   {
       System.out.println(e.getMessage());
       System.out.println("err");
   }
}
  
private static void readXseedRunIni ()  	
{ 	
	try
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
    catch (Exception e)
    {   
        // System.out.println("readXseedRunIni - " + e.getMessage());
    }
}
	
private static void getXseedRunOptions (String pLine)  	
{   
   try
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

          if (beforeWord.trim().toUpperCase().equals("ASYNCMODE") )
             asyncMode = afterWord.trim().toUpperCase();

          else if (beforeWord.trim().toUpperCase().equals("APPINIPATH") )
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
   catch (Exception e)
   {    System.out.println("getRunOptions - " + e.getMessage());
   }
}
  
private static void readReportRun (String pRunFileName)  	
{ 	
   try
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
                afterWord = runIniLine.substring(i + 1);

                if (beforeWord.trim().toUpperCase().equals("GLB_INITSTN") )
                   initSTN = afterWord.trim();
                else if (beforeWord.trim().toUpperCase().equals("GLB_PARAM") )
                   param = afterWord.trim();			
             }
          }
      }
      runIni = null;
      runIniStream = null;						
   }		
   catch (Exception e)
   {   // System.out.println("readXseedRunIni - " + e.getMessage());
   }
}

private static boolean checkMsg ()  	
{ 	
   try
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
   catch (Exception e)
   {   // System.out.println("checkMsg - " + e.getMessage());
       return false;  // --> continua executando XseedRun
   }
}

private static String readMsg (String pFilename)  	
{ 	
   try
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
   catch (Exception e)
   {   // System.out.println("checkMsg - " + e.getMessage());
       return "";  // --> continua executando XseedRun
   }
}


	
private static void showMsg(String pMsg)
{
   try
   {
       Date dateToday = new Date();

       pMsg = "MSG:" + format(dateToday, "yyyy/MM/dd HH:mm:ss") + " --> " + pMsg;
   	
       System.out.println(pMsg);
				
       //gravar em arquivo de log
       FileOutputStream logFile = new FileOutputStream(batchPath + File.separatorChar + "XseedRun" + queueNumber + ".log", true);
       PrintStream log = new PrintStream(logFile);			
       log.println(pMsg);
       log.close();
       logFile.close();			
    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
    }
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


} // Final da Classe