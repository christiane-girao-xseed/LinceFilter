package HLegacy;
import java.io.*;
import java.math.*;
import java.util.*;

public class XseedButton
{   	
    private BigDecimal bigZeros = new BigDecimal(0);
    
    public XseedGLB GLB;    
    public XseedCSS css;
    public PrintWriter out;  
    
    public String name = "";
    public String accesskey = "";
    //public appearance
    public String backcolor = "";
    public String caption = "";
    //public clearwhen
    public boolean callsubmit = true;
    public String datadisplay = "";
    public int decimals =0;
    public String description = "";
    public String edit= "A";
    public boolean enabled = true;
    public double eventindex = 0.0;    
    public String [] eventname;
    public String [] eventmethod;    
    public double eventmaxindex = 0.0; 
    public String fieldname = "";
    public String fieldvalue = "";
    public String font = "";
    public boolean fontbold = false;
    public boolean fontitalic = false;
    public int fontsize = 8;    
    public String forecolor = "000000";
    public double height = 285.0;
    public double left = 0.0;
    public int length = 1;
    public String localeid = "";
    //public linkifpresent
    //public noautomaticevents
    //public nolookup
    //public ordinate
    //public required
    //public sameas
    public int tabindex = -9999;
    //public tabpage
    public boolean tabstop = true;
    public String tooltiptext = "";
    public double top = 0.0;
    public String usage = "DEF";
    public boolean visible = true;
    public double width = 120.0;
    
    public boolean hotkey = false;
    

public XseedButton()
{ 
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
    
    int i=0;
    String fieldCaption = caption;
    String strEventOnKeydown ="";
    String strEventOnClick = "";
    String strEvents ="";
        
    for (int j=0; j < eventmaxindex;j++)
    {   if (eventname[j] != null)
        {   if (eventname[j].equalsIgnoreCase("onkeydown") == true)
            {
                strEventOnKeydown = strEventOnKeydown +  eventmethod[j] + "; ";
            }
            else if (eventname[j].equalsIgnoreCase("onclick") == true)
            {
                strEventOnClick = strEventOnClick +  eventmethod[j] + "; ";
            }
            else
            {   strEvents = strEvents + eventname[j] + "=\" " ;
                strEvents = strEvents + eventmethod[j] + "\" ";
            }
        }   
    }   
    
    String str ="";             
    str= "<button ";                        
    str = str + "name=\"" + name + "\" id=\"id" + name + "\" ";        
    str = str + "style = \"position:absolute; " +                   
                " top:" +  XseedFunctions.cStr(XseedFunctions.twipsperPixel(top)) + "px;" +
                " left:"+ XseedFunctions.cStr(XseedFunctions.twipsperPixel(left))  + "px;" +
                " width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)) + "px;" +
                " height:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(height)) + "px;" +
                " text-align:center;" ;        
           
    if (font.equals("") == false)
    {   str = str + "font-family: " + font +  ";";
    }
       
    if (fontsize != 8)
    {   str = str +   "font-size: " + fontsize + "pt;";
    }
       
    if (fontbold == true)
    {   str = str + "font-weight: bold;";
    }    
        
    if (fontitalic == true)
    {   str = str + "font-style: italic;";
    }
      
    if (visible  == false)   
    {   str = str + "visibility:hidden;";
    }
        
    str = str + "\"";
        
    if (tabindex != -9999)  
    {   str = str + " tabindex=\"" + tabindex + "\"";        
    }
	
    str = str + " type=\"Button\" ";
    if (accesskey.equals("") == false)
    {
        str = str + " accesskey=\"" + accesskey + "\" ";
    }         
    
    if (callsubmit == true || fieldname.equals("") == false)
    {
    	/*if (hotkey == true)
    		str = str + " onkeydown=\"" + name + "_onkeybutton();\"";
    	else*/
    	
        strEventOnKeydown = name + "_onkeybutton(event); " + strEventOnKeydown ;
        strEventOnClick =  name + "_OnClick(); " + strEventOnClick ;        
    }
    if (strEventOnKeydown.equals("")==false)
    {
        str = str + " onkeydown=\"" + strEventOnKeydown + "\" ";
    }    
    if (strEventOnClick.equals("")==false)
    {
        str = str + " onClick=\"" + strEventOnClick + "\" ";
    }    
    if (strEvents.equals("")==false)
    {
        str = str + strEvents;
    }       

    
    if (tooltiptext.equals("") == false)
    {
        str = str + "title=\"" + tooltiptext + "\"";
    }
    
    str = str + ">";
  
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
    str = str + "</BUTTON>";
    out.println(str);   
    if (usage.equals("CTR") == false)
    {
        out.println("<input type=\"hidden\" name=\"_" + name + "\" id=\"_id" + name + "\" maxlength=\"" + length + "\">");
    }
	
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