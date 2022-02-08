package HLegacy;
import java.io.*;
import java.util.*;
import java.awt.print.*;
import javax.print.*;

public class XseedPrint
{
	String printerName;	
	String paperOrientation;
	String font;
    int fontSize; 
    String paperSize;
    double top;
    double bottom;
    double left;
    double right;
    int pageIni;
    int pageEnd;
	
	public XseedPrint()
	{		
		this.printerName = "Default";		
		this.paperOrientation = "PORTRAIT";
		this.font = "Courier New";
		this.fontSize = 8;
		this.paperSize = "CARTA";
		this.top = 1.0;
		this.bottom = 1.0;
		this.left = 1.25;
		this.right = 1.25;
		this.pageIni = 1;
		this.pageEnd = 999;		
	}
		
	public String start(String pSpoolFile, String pPrinterName, String pPaperOrientation, String pFont, int pFontSize ,String pPaperSize, double pTop, double pBottom, double pLeft, double pRight, int pPageIni, int pPageEnd)
    {
    	printerName = pPrinterName;    	
    	paperOrientation = pPaperOrientation;
    	font = pFont;
    	fontSize = pFontSize;
    	paperSize = pPaperSize;
    	top = pTop;
    	bottom = pBottom;
    	left = pLeft;
    	right = pRight;
    	pageIni = pPageIni;
    	pageEnd = pPageEnd;
    	
    	return(printFile(pSpoolFile));
    }	
    
    public String start(String pSpoolFile)
    {
    	return(printFile(pSpoolFile));
    }
    	
    
    private String printFile(String pSpoolFile)	
   	{  
		PrintService printList[];
   	    String linha;
   	    String buffer[];
   	    double paperWidth;
   	    double paperHeight;
   	    
    	try
    	{
    		Book  book = new Book();
            Paper papr = new Paper();       

            BufferedReader br = new BufferedReader(new FileReader(pSpoolFile));                 
			
			if (paperSize.equals("OFICIO") == true)
			{
				//Formula (Width = 8.5 * 72, Height =14 *72));	
				paperWidth = 612;
				paperHeight = 1008;
								
			}
			else if (paperSize.equals("EXECUTIVO") == true)
			{
				//Formula (Width = 7.25 * 72, Height = 10.5 * 72));	
				paperWidth = 7.25;
				paperHeight = 10.5;				
				
			}
            else if (paperSize.equals("A4") == true)
			{
				//Formula (Width = 8.27 * 72, Height = 11.69 * 72));	
				paperWidth = 8.27;
				paperHeight = 11.69;				
			}	
			else if (paperSize.equals("CARTA") == true)
			{
				//Formula (Width = 8.5 * 72, Height = 11 * 72));	
				paperWidth = 8.5;
				paperHeight = 11;								
			}
			else 		//Qualquer outro tipo será considerado CARTA
			{
				//Formula (Width = 8.5 * 72, Height = 11 * 72));	
				paperWidth = 8.5;
				paperHeight = 1;								
			}
			
			papr.setSize(paperWidth*72,paperHeight*72);
			
            // Printable Area Dimensions            
            papr.setImageableArea(left*72,top*72,(paperWidth-left-right)*72,(paperHeight-top-bottom)* 72);

            PageFormat pagf = new PageFormat();
            pagf.setPaper(papr);
            
            // Page Orientation:Portrait ou Landscape
            if(paperOrientation.equals("LANDSCAPE"))
            {
            	pagf.setOrientation(PageFormat.LANDSCAPE);
            }
            
   	        buffer = new String[120];
   	        
   	        int j = 0;
   	        int numPages = 0;   	    
   	        while( (linha = br.readLine())!= null)
   	        {
   	        	if ((linha.equals(".PA") == false) && (linha.equals("<.pa>") == false)) 
   	    	    {   if (linha.equals("") == true)
   	    	    	{	linha = "    ";
   	    	    	}	
   	    	    	buffer[j] = linha;   	    	    	
   	    	        j++;
   	    	    }
   	    	    else
   	    	    {   numPages++;
   	    	        if ((numPages >= pageIni) && (numPages <= pageEnd))
   	    		    {   book.append(new XseedSpool(buffer,font,fontSize), pagf);   	    		
   	    		    }
   	    		    buffer = new String[120];
	   	    		j = 0;
	   	    		
   	    	    }
   	        }
   	        
   	     // The last page doesn't have .PA
   	        if ((linha == null) && (buffer[0] != null))
   	        {    numPages++;
   	          	 if ((numPages >= pageIni) && (numPages <= pageEnd))
                 {   book.append(new XseedSpool(buffer,font,fontSize), pagf);
                 }
            }
            
         // Close BufferedReader   
       	    br.close();

     	 // Creates a Job
     	    PrinterJob printerJob = PrinterJob.getPrinterJob();
            printerJob.setPageable(book);
            
         // Selects a Printer
         	printList = PrinterJob.lookupPrintServices();
            if (printerName.equals("Default") == false)            
            {	int i = 0;
            	for (i=0; i < printList.length; i ++)
                {                	
                	if (printerName.equals(printList[i].getName()) == true)
            	    {  
            	      printerJob.setPrintService(printList[i]);            	 
            	      break; 
            	  	}            	  	
                }
                //Impressora não existe
                if (i == printList.length) 
                {
                	return("Printer " + printerName + " not found");
                }
            }
            
         	// Sends the Job to the Printer
	      	printerJob.print();
	      	
	      	return("");
    	}
    	catch(Exception e)
    	{
    	 	return(e.getMessage());
    	}
    }
}
