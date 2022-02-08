package HLegacy;
import java.io.*;
import java.math.*;
import java.util.*;

public class XseedOptionbutton
{  

    public XseedGLB GLB;    
    public XseedCSS css;
    public PrintWriter out; 
    
    public String accesskey = "";
    public int    alignment = 0;
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
    public double width  = 120.0;
    //

public XseedOptionbutton ()
{

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
    
    //'Criacao check        
    if (visible  == true)        
    {   
    	str = "<SPAN id=\"idLayer" + name + "\" ";
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
	    str = str + "<input type=\"radio\" ";
    }
    else
    {   str = "<input type=\"hidden\" ";
    }       
    
    str = str + "name=\"" + name + "\" ";
    if (tabindex != -9999)
    {    str = str + "tabindex=\"" + tabindex + "\" ";        
    }
    if (accesskey.equals("") == false)
    {    str = str + "accesskey=\"" + accesskey + "\" ";        
    }    
    str = str + "onclick=\"jOption('" + name + "','" + checkedvalue + "')\"";
	if (enabled == false)
    {	str = str + "disabled ";
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
