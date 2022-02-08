package HLegacy;
import java.io.*;
import java.math.*;
import java.util.*;

public class XseedCheckbox
{  

    public XseedGLB GLB;    
    public XseedCSS css;
    public PrintWriter out; 
    
    public int alignment = 0;
    // appearance
    public String backcolor = "FFFFFF";
    public String caption = "";
    public String checkedvalue = "";
    // clearwhen
    // datadisplay
    // decimals
    // description
    // edit
    public boolean enabled = true;    
    public double eventindex = 0.0;    
    public String [] eventname;
    public String [] eventmethod;    
    public double eventmaxindex = 0.0;    
    public String font = "VERDANA"; 
    public boolean fontbold = false;
    public boolean fontitalic =false;
    public int fontsize = 8;
    public String forecolor = "000000";
    public double height = 0.0;
    public double left = 0.0;
    // length
    // linkifpresent
    public String localeid = "";
    public String name = "";
    // noautomaticevents
    // noLookup
    // ordinate
    // required
    // sameas
    // // ' Style
    public int tabindex = -9999;
    // tabpage
    // tabstop
    // tooltiptext
    public double top = 0.0;
    public String uncheckedvalue = "";
    // usage
    public boolean visible = true;
    public String visibility = "inherit";
    public double width  = 120.0;
    //
    public String event[];
    public String method[];

public XseedCheckbox ()
{
    event = new String[20];
    method = new String[20];
    eventname = new String[20]; 
    eventmethod = new String[20]; 
}


public void print ()
	throws Exception
{
    printControl ();  
}

public void printControl ()
    throws Exception
{    
    String fieldCaption = caption;    
    String str ="";
    
    String strEventOnClick = "";
    String strEvents ="";
        
    for (int j=0; j < eventmaxindex;j++)
    {   if (eventname[j] != null)
        {   if (eventname[j].equalsIgnoreCase("onclick") == true)
            {
                strEventOnClick = strEventOnClick +  eventmethod[j] + "; ";
            }
            else
            {   strEvents = strEvents + eventname[j] + "=\" " ;
                strEvents = strEvents + eventmethod[j] + "\" ";
            }
        }   
    } 
    
    //'Criacao check        
    if (visible  == true)        
    {   
    	str = "<SPAN id=\"id" + name + "\" ";
    	str = str + "style=\"position:absolute; ";
        str = str + "top:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(top)) + "px; " +
        			"left:"+ XseedFunctions.cStr(XseedFunctions.twipsperPixel(left)) + "px; " +
        			"width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)) +  "px; " +
        			"height:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(height)) + "px;";            

        if (alignment == 1)
        {   str = str + "text-align:right;";
        }
        else if (alignment == 2)
        {   str = str + "text-align:center;";
        }
        else
        {   str = str + "text-align:left;";
        }       
                
        if ((forecolor.equals("000000") == false) && (forecolor.equals("") ==false))
		{	str = str + "color:" + forecolor + ";";
		}
		if ((backcolor.equals("FFFFFF") == false) && (backcolor.equals("") ==false))
		{	str = str + "background-color:" + backcolor + ";";
		}
		
        if (font.equals(css.font) == false)
        {	str = str + "font-family: " + font +  ";";
    	}
    	
    	if (fontsize != css.fontsize)
    	{	str = str +	"font-size: " + fontsize + "pt;";
    	}
    	
    	if (fontbold != css.fontbold)
    	{	if (fontbold == true)
    		{	str = str + "font-weight: bold;";
    		}
    		else
    		{	str = str + "font-weight: normal;";
    		}
    	}
    	
    	if (fontitalic != css.fontitalic)
    	{	if (fontitalic == true)
    		{	str = str + "font-style: italic;";
    		}
    		else
    		{	str = str + "font-style: normal;";
    		}
    	}        
        str = str + "\">";
	    str = str + "<input type=\"checkbox\" ";
    }
    else
    {   str = "<input type=\"hidden\" ";
    }       
    
    str = str + "name=\"" + name + "\" ";
    if (tabindex != -9999)
    {    str = str + "tabindex=\"" + tabindex + "\" ";        
    }
    
    if (enabled == false)
    {	str = str + "disabled ";
    }     
    	
    
    strEventOnClick =  name + "_Click('" + checkedvalue + "','" + uncheckedvalue + "'); " + strEventOnClick ;    
    
    if (strEventOnClick.equals("")==false)
    {
        str = str + " onclick=\"" + strEventOnClick + "\" ";
    }    
    if (strEvents.equals("")==false)
    {
        str = str + strEvents;
    }           
    str = str + ">";
        
	if (visible  == true)        
    {	
	    if ((localeid.equals("")==false) && (GLB.LANGUAGE.trim().equals("")==false))
	    {   try
	        {
	            Locale currentLocale;
	            ResourceBundle messages;                
	            currentLocale = new Locale(GLB.LANGUAGE);
	            messages =   ResourceBundle.getBundle("Locale",currentLocale);
	            fieldCaption = messages.getString(localeid);                  
	        }
	        catch (Exception e)
	        {              
	        }
	    }
	    str = str + checkTagU(fieldCaption) ;       	
        str = str + "</SPAN>";
	}   

    out.println(str);   
	
}

private String checkTagU(String pFieldCaption)
   throws Exception
{
    String newCaption = "";    
    int i;
    for ( i =0; i < pFieldCaption.length()-1; i++)
    {
        if (pFieldCaption.substring(i, i+1).equals("&") == true )
        {
            newCaption = newCaption + "<u>" + pFieldCaption.substring(i+1, i+2) + "</u>";
            i++;
        }
        else
        {
            newCaption = newCaption + pFieldCaption.substring(i, i+1);
        }
    }
    newCaption = newCaption + pFieldCaption.substring(i);
    return(newCaption);
}
}
