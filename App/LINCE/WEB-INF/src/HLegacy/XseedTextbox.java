package HLegacy;
import java.io.*;
import java.math.*;

public class XseedTextbox
{   

	private BigDecimal bigZeros = new BigDecimal(0);
	private XseedFunction xsd = new XseedFunction();

    public PrintWriter out;        
    
    public int  alignment = 0;
    public String appearance = "3D";
    public String backcolor = "FFFFFF";
	//borderStyle
	//ClearWhen
	//DataDisplay
	//Decimalkeyed
    public int decimals = 0;
	//Description
    public String edit = "A";
    public boolean enabled = true;  
    public double eventindex = 0.0;    
    public String [] eventname;
    public String [] eventmethod;    
    public double eventmaxindex = 0.0;    
    public String font = "VERDANA"; 
    public boolean fontbold = false;
    public boolean fontitalic = false;
    public int fontsize = 8;
    public String forecolor = "000000";
    public double height = 285.0;
    public double left = 0.0;
    public boolean leftfill = false;
    public int length = 1;
	//linkIfPresent
	//lockSign
    public String mask = "";
    public double maxlength = 1.0;
    public boolean multiline = false;
    public String name = "";
	//noAutomatic
	//noLookUp
    public boolean notentered = false;
	//ordinate
	//required
	//sameAs
    public String onclick = "";
    public boolean secure = false;
    public boolean showleading = false;
    public boolean showseparator = false;
    public boolean showzeros = false;
    public int tabindex = -9999;
	//tabPage
    public boolean tabstop = true;
    public String tooltiptext = "";
    public double top = 0.0;
    public String usage = "DEF";
    public String value = "";
    public boolean visible = true;    
    public String visibility = "inherit";
    public double width = 120.0 ;
    public boolean zerosuppress = false; 
                  
    //
    public String event[];
    public String method[];
    

public XseedTextbox(int pAlignment,String pBackcolor, int pDecimals, String pEdit, boolean pEnabled,String pFont, 
	boolean pFontbold,boolean pFontitalic,int pFontsize,String pForecolor, double pHeight,
	double pLeft, boolean pLeftfill,int pLength, double pMaxlength, boolean pMultiline,String pName,boolean pNotentered,
	boolean pSecure,boolean pShowseparator, int pTabindex,double pTop,String pUsage, String pValue, boolean pVisible, double pWidth)

{
	alignment = pAlignment;
	backcolor = pBackcolor;
	decimals = pDecimals;
	edit =pEdit;
	enabled = pEnabled;
	font = pFont;
	fontbold = pFontbold;
	fontitalic = pFontitalic;
	fontsize  = pFontsize;
	forecolor = pForecolor;	
	height = pHeight;
	left = pLeft;
	leftfill = pLeftfill;
	length = pLength;
	maxlength = pMaxlength;
	multiline = pMultiline;
	name = pName;
	notentered = pNotentered;
	secure = pSecure;
	showseparator = pShowseparator;
	tabindex = pTabindex;
	top = pTop;
	usage = pUsage;	
	value = pValue;
	visible = pVisible;
    width = pWidth;
}

public XseedTextbox()
{
    event = new String[20];
    method = new String[20];
    eventname = new String[20]; 
    eventmethod = new String[20]; 
}


public void print (String pValue)
	throws Exception
{
	if (usage.equals("INQ")	== true)
	{
		printInquiry(pValue);		
	}
	
}

public void print (double pValue)
	throws Exception
{
	BigDecimal pBigValue = new BigDecimal((new Double(pValue)).toString());             
	
	if (usage.equals("INQ")	== true)
	{
		printInquiry(pBigValue);		
	}
}

public void print (BigDecimal pValue)
	throws Exception
{
	if (usage.equals("INQ")	== true)
	{
		printInquiry(pValue);		
	}
}

private void printInquiry (String pValue)
	throws Exception
{
	String tooltip = ""; 
    if (tooltiptext.equals("") == true)
    {
    	tooltip = " title=\"" + tooltiptext + "\"";
	}    
	if (pValue.length() >  length)
	{

		pValue = pValue.substring(0,length);
	}	
    out.println("  <SPAN id =\"id" + name + "\"" + tooltip + ">" + pValue + "</SPAN>");
    
}

private void printInquiry(BigDecimal pValue)
	throws Exception
{
	String tooltip = ""; 
	String printMask = "";
	
    if (tooltiptext.equals("") == true)
    {
    	tooltip = " title=\"" + tooltiptext + "\"";
	}    

    if ( (edit.equals("N") == false) && (edit.equals("D") == false) )
	{   
    	if (xsd.greater(pValue, bigZeros ) == true)
        {
        	//Check for +, -, DR, CR positive number
            if ( (edit.equals("-") == true) || (edit.equals("+") == true) )
	        {
    	    	out.println(" <SPAN id=\"id" + name + "XSD\"" + tooltip + ">+</SPAN>");
			}
            else if( (edit.equals("C") == true) || (edit.equals("S") == true ))
			{
            	out.println("  <SPAN id=\"id" + name + "XSD\"" + tooltip + ">CR</SPAN>");
			}                      
		}
        else if (xsd.less(pValue, bigZeros) == true)
        {
			pValue = xsd.multiply(pValue ,-1.0);
			if( (edit.equals("-") == true) || (edit.equals("+") == true) )
			{
				out.println("  <SPAN id=\"id" + name + "XSD\"" + tooltip + ">-</SPAN>");
			}
            else if ( (edit.equals("C") == true) || (edit.equals("S") == true) )
            {
            	out.println("  <SPAN id=\"id" + name + "XSD\"" + tooltip + ">DR</SPAN>");
            }
        }        
        else   // Caso valor do campo igual a zeros
        {
            if (showzeros == true)
            {
                if  (edit.equals("-") == true) 
                {
                    out.println(" <SPAN id=\"id" + name + "XSD\"" + tooltip + ">-</SPAN>");
                }
                else if (edit.equals("+") == true) 
                {
                    out.println(" <SPAN id=\"id" + name + "XSD\"" + tooltip + ">+</SPAN>");
                }
                else if (edit.equals("C") == true) 
                {
                    out.println("  <SPAN id=\"id" + name + "XSD\"" + tooltip + ">CR</SPAN>");
                }                      
                else if (edit.equals("S") == true )
                {
                    out.println("  <SPAN id=\"id" + name + "XSD\"" + tooltip + ">DR</SPAN>");
                }                      
            }
            else
            {    
        	    out.println("  <SPAN id=\"id" + name + "XSD\"" + tooltip + "> </SPAN>");
            }
		} 	
	}
    
    if (mask.equals("") == true)
    {	//Format the number for output
    	if (showleading == true)
    	{
    		if (decimals == 0)
			{
	      		printMask = xsd.string(length, "9");
        	}
       		else
       		{
	       		printMask = xsd.string(length - decimals, "9") + "." + xsd.string(decimals, "9");
			}
		}
    	else
    	{
    		if (decimals == 0)
    		{        	
       			printMask = xsd.string(length, "#");
       		}
			else
			{				
				printMask = xsd.string(length - decimals - 1, "#") + "9" + "." + xsd.string(decimals, "9");
			}			
		}
    	if (showseparator == true)
    	{
    		printMask = xsd.insertSeparatorMask(printMask);		     	        
      	}		
    } 		
    else
    {
    	printMask= mask;
	}
	
    if ((xsd.equals(pValue, bigZeros) == true) && (showzeros == false))
    {
      	out.println("  <SPAN id=\"id" + name + "\"" + tooltip + "></SPAN>");
	}
    else           	
    {
		out.println("  <SPAN id=\"id" + name + "\"" + tooltip + ">" + xsd.format(pValue,  printMask ) + "</SPAN>");
	}	
}
  
} 