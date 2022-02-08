package HLegacy;
import java.util.*;
import java.io.*;

public class XseedOutputControl {


  private String printer;
  private String backupFile;
  private String systemPath;

//Auxiliar attributes
  private String model;
//Styles
  private HashMap<String,String> stylesMap;

//Constants
  private String FILE_STYLES_MODELS = "XseedOCConfig.ini";
  private String FILE_PRINTERS      = "XseedOCPrinters.ini";
  private String beginTag           = "<*@%";
  private String endTag             = "%@*>";

  public XseedOutputControl(String pPrinter, String pBackupFile, String pSystemPath)
  {
     printer=pPrinter;
     backupFile=pBackupFile;
     
     // Inserted Line
     systemPath = pSystemPath;
     
     stylesMap = new HashMap<String,String>();
  }

  public static void main(String[] args)
  throws Exception
  {
    String argPrinter="";
    String argBackupFile="";
    
    // Inserted Line
    String argSystemPath = "";
    
    if (args.length < 3)
    {
       System.out.println("Please inform printer, backup file or system path");
       System.exit(1); // Parameter Failure
    }
    argPrinter=args[0];
    argBackupFile=args[1];
    
    // Inserted Line
    argSystemPath=args[2];
    

    XseedOutputControl outputControls = new XseedOutputControl( argPrinter , argBackupFile , argSystemPath);
    outputControls.processBackupFile();
    System.exit(0); // Sucess
  }


  /**
   * Process the backup File converting the output controls in ascii bytes
   * @throws Exception
   */
  private void processBackupFile()
  throws Exception
  {
  	
  	/*
    // Get Printer Model
    model = getModelFromPrinterFile( printer );
    if (model.equals("<not-found>"))
    {
      //System.out.println("Printer Model not defined in file XseedOCPrinters.ini");
      //System.exit(1);
      processLineFeedFile(false);
      
      
    }
    
    	// Load Styles in Memory for that Model
    	loadStylesForModelInMemory ( model ) ;

    	// Converts tags in File
    	processLineFeedFile(true);
    
    */
    
    model = getModelFromPrinterFile( printer );
        
    // Load Styles in Memory for that Model
    loadStylesForModelInMemory ( model ) ;

    // Converts tags in File
    processLineFeedFile();
    

  } //processBackupFile


  /**
   * Processes the backup file in line-feed records
   * @throws Exception
   */
  //private void processLineFeedFile ()
  private void processLineFeedFile ()
  throws Exception
  {
  	
    FileReader fInBkpFile  = new FileReader( backupFile );
    BufferedReader inBkpFile = new BufferedReader( fInBkpFile );
    
    FileWriter fOutBkpFile = new FileWriter( backupFile + ".tmp" );
    PrintWriter outBkpFile = new PrintWriter( fOutBkpFile );

	
	/*
    // Read Input File
    String bkpFileLine = inBkpFile.readLine();
    
    while ( bkpFileLine != null )
    {
       // Convert line buffer
       
       bkpFileLine = convertTagsInBuffer ( bkpFileLine );

       // Write Output File
       //outBkpFile.println(bkpFileLine);
       outBkpFile.println(bkpFileLine);

       bkpFileLine = inBkpFile.readLine();
    } // while
    
    */
    
    String tmpBkpFileLine = "";
    
    // Read Input File
    String bkpFileLine = inBkpFile.readLine();
    tmpBkpFileLine = bkpFileLine;
    
    
    while ( bkpFileLine != null )
    {
       // Convert line buffer
       
       //bkpFileLine = convertTagsInBuffer ( bkpFileLine );
       // 
       
       tmpBkpFileLine = convertTagsInBuffer ( tmpBkpFileLine );
       

       // Write Output File
       bkpFileLine = inBkpFile.readLine();
       if (bkpFileLine != null)
       {
       		outBkpFile.println(tmpBkpFileLine);
       		tmpBkpFileLine = bkpFileLine;
       }
       else
       {
       		outBkpFile.print(tmpBkpFileLine);
       }
    } // while
    
      
    
    inBkpFile.close();
    inBkpFile=null;
    fInBkpFile=null;
    outBkpFile.close();
    outBkpFile=null;
    fOutBkpFile=null;
    
    

  } // class



  /**
   * Convert Tags in a Buffer
   * @param The Buffer to convert
   * @return The converted buffer
   * @throws Exception
   */
  private String convertTagsInBuffer ( String bufferLine )
  throws Exception
  {
    StringBuffer buffer=new StringBuffer( bufferLine );
    StringBuffer bytes=new StringBuffer("");
    String styleTag = "";
    String seqBytes="";

    int tagBeginPosition;
    int tagEndPosition;
    int tagPosition;

    tagBeginPosition = buffer.indexOf( beginTag );

    while (tagBeginPosition != -1)
    {
       // Find the End Tag
       tagEndPosition = buffer.indexOf( endTag );
              
       if ( tagEndPosition != -1 )
       {
                      
           tagEndPosition += endTag.length();
           styleTag = buffer.substring( tagBeginPosition , tagEndPosition );
           seqBytes = (String) stylesMap.get(  styleTag  );

           if ( seqBytes != null )
           {
              tagPosition = buffer.indexOf( styleTag );

              while ( tagPosition != -1 )
              {
              	
                 // Ajustar Bytes para Impressora
                   bytes.setLength(0);
                   for (int numByte=0; numByte < seqBytes.length(); numByte+=2)
                   {
                      bytes.append((char) Integer.parseInt(  seqBytes.substring( numByte, numByte+2  ), 16 ));
                   }


                 // Ajustar essa linha
                   buffer.replace(  tagPosition, tagPosition + styleTag.length(), bytes.toString()  );
				   tagPosition = buffer.indexOf( styleTag );
              }
           }
           else  
           {
              buffer.replace(tagBeginPosition,tagEndPosition,"");
              //buffer.replace(tagBeginPosition,tagEndPosition,"\0");
           }
       }
       else
       {
           // Return the buffer if end tag not found
           return buffer.toString();
       }
       tagBeginPosition = buffer.indexOf(beginTag);
    } // while
    // Sucess
    return buffer.toString();

  } // convertTagsInBuffer

  /**
   * Search model associated with a printer.
   * @param printer The printer name to search model for.
   * @return The model of printer or <not-found> if not found.
   * @throws Exception
   */
  private String getModelFromPrinterFile( String pPrinter )
  throws Exception
  {
    int posSeparator=0;
    String printerName="";
    String modelName="";

    //FileReader fPrn= new FileReader( systemPath + FILE_PRINTERS );
    FileReader fPrn= new FileReader( systemPath + File.separatorChar + FILE_PRINTERS );
    BufferedReader inPrn = new BufferedReader( fPrn );
    String prnLine = inPrn.readLine();
    while (prnLine != null)
    {
       posSeparator=prnLine.indexOf(";");
       if ( posSeparator != -1 )
       {
           printerName=prnLine.substring(0,posSeparator).trim();
           modelName=prnLine.substring(posSeparator + 1).trim();

           // Model found for that printer
           if (  printerName.toUpperCase().trim().equals( pPrinter.toUpperCase().trim() )  )
           {
               inPrn.close();
               inPrn=null;
               fPrn=null;
               return  modelName;
           } //if
       } //if
       prnLine = inPrn.readLine();
    } // while
    inPrn.close();
    inPrn=null;
    fPrn=null;
    return "<not-found>";
  } // class


  /**
   * Load Styles associated with a model in memory.
   * @param model The printer model name to load styles for.
   * @throws Exception
   */
  private void loadStylesForModelInMemory ( String pModel )
  throws Exception
  {
    int posSeparator=0,
        posSeparator2=0;
    String modelName="";
    String styleName="";
    String seqBytes="";

    //FileReader fStlMdl= new FileReader( systemPath + FILE_STYLES_MODELS );
    FileReader fStlMdl= new FileReader( systemPath + File.separatorChar + FILE_STYLES_MODELS );
    BufferedReader inStlMdl = new BufferedReader( fStlMdl );
    String styleModelLine = inStlMdl.readLine();
    while (styleModelLine != null)
    {
       posSeparator = styleModelLine.indexOf(";");
       if ( posSeparator != -1 )
       {
           modelName = styleModelLine.substring( 0 , posSeparator ).trim();
           posSeparator2 = styleModelLine.indexOf( ";" , posSeparator + 1 );
           if ( posSeparator2 != -1 )
           {
              styleName = styleModelLine.substring( posSeparator + 1 , posSeparator2 ).trim();
              seqBytes = styleModelLine.substring( posSeparator2 + 1 ).trim();

              // Style found for that model
              if (  modelName.toUpperCase().trim().equals( pModel.toUpperCase().trim() )  )
              {
                 // Load style in memory
                 stylesMap.put( beginTag + styleName + endTag, seqBytes );
              }
           } //if
       } // if
       styleModelLine = inStlMdl.readLine();
    } // while
    inStlMdl.close();
    inStlMdl=null;
    fStlMdl=null;
  } // loadStylesForModelInMemory


} // class