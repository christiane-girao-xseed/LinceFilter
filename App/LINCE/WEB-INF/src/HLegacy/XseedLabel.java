package HLegacy;
import java.io.*;
import java.util.*;
public class XseedLabel
{ 
    
    public PrintWriter out;            

    public String accesskey = "";
	public int alignment = 0;
	//appearance
	//autosize
    public String backcolor = "FFFFFF";
    public String backstyle = "opaque";
    public boolean callsubmit = false;
    public String caption = "";
	//enabled    
    public double eventindex = 0.0;    
    public String [] eventname;
    public String [] eventmethod;    
    public double eventmaxindex = 0.0;
    public String fieldname = "";
    public String fieldvalue = "";
    public String font = "VERDANA";
    public boolean fontbold = false;
    public boolean fontitalic = false;
    public int fontsize = 8;
    public String forecolor = "000000";
    public double height = 285.0;
    public String hyperlink = ""; // nao pode ser totalmente alterado na logica style criado na geracao
    public double left = 0.0;
    public String localeid = "";
    public String name = "";
    public String onclick = "";
	//tabpage
    public String tooltiptext = "";
    public double top = 0.0;
    public boolean visible = true;
	//voidcopyfrom	
    public double width = 120.0;       
    //
    public String event[];
    public String method[];
        
    public XseedCSS css;
    
    public XseedGLB GLB;
    
    private String eventOnKeydown = "";
    
public XseedLabel (int pAlignment, String pBackcolor, boolean pCallsubmit,String pCaption,
	String pFieldname, String pFieldvalue,String pFont,	boolean pFontbold,	boolean pFontitalic,
	int pFontsize, String pForecolor, double pHeight,String pHyperlink, double pLeft,String pName,
	double pTop, boolean pVisible, double pWidth)    

{	alignment = pAlignment;
	backcolor = pBackcolor;
	callsubmit = pCallsubmit;
	caption = pCaption;
	fieldname = pFieldname;
	fieldvalue = pFieldvalue;
	font = pFont;
	fontbold = pFontbold;
	fontitalic = pFontitalic;
	fontsize = pFontsize;
	forecolor = pForecolor;
	height = pHeight;	
	hyperlink = pHyperlink;
	left = pLeft;
	name = pName;
	top = pTop;
	visible = pVisible;
	width = pWidth;
} 

public XseedLabel ()
{
    event = new String[20];
    method = new String[20]; 
    eventname = new String[20]; 
    eventmethod = new String[20]; 
    
}

//innovation
public void print ()
   throws Exception
{
   openTag();
   printProperties();
   printStyles();
   printEvents();
   closeTag();
//   printOnClick();
//   printScript();
}

/** 
 * Starts the printting of the label Html code.
 * @throws Exception Return an exception if occurs an error.
 */  
private void openTag ()
   throws Exception
{
    String wBuffer= "<SPAN";        
    wBuffer = wBuffer + " name=\"" + name + "\"";
    wBuffer =  wBuffer + " id=\"id" + name + "\"";        
    if (accesskey.equals("") == false)
    {
        wBuffer = wBuffer + " accesskey=\"" + accesskey + "\" ";
    }   
    out.print(wBuffer);
}

/**
 * Prints label properties on the Html code.
 * @throws Exception Return an exception if occurs an error.
 */ 
private void printProperties ()
   throws Exception
{
    String wBuffer= "";    
    if (tooltiptext != "")
    {
        wBuffer = wBuffer + "title = \"" + tooltiptext + "\"";  
    }   
    if (wBuffer.equals("") == false)
    {
        out.print(wBuffer);
    }
}


/**
 * Prints label styles on the Html code.
 * @throws Exception Return an exception if occurs an error.
 */ 
private void printStyles ()
   throws Exception
{
    String wBuffer= "position:absolute;";   
   
    wBuffer = wBuffer + " top:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(top)) + "px;" +
                       " left:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(left)) + "px;" + 
                       " height:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(height)) + "px;" +  
                       " width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)) + "px;" ;
   
        
    if (backstyle.equals("transparent") == true)
    {   wBuffer = wBuffer + "background-color:transparent;";
    }
    else
    {
        if ((backcolor.equals(css.backcolor) == false)&& (backcolor.equals("") ==false))
        {   wBuffer = wBuffer + "background-color:" + backcolor + ";";
        }
    }
    
    if ((forecolor.equals(css.forecolor)== false)&& (forecolor.equals("") ==false))
    {   wBuffer = wBuffer + "color:" + forecolor + ";";
    }
    
    if (font.equals(css.font) == false)
    {   wBuffer = wBuffer + "font-family: " + font +  ";";
    }
        
    if (fontsize != css.fontsize)
    {   wBuffer = wBuffer +   "font-size: " + fontsize + "pt;";
    }
        
    if (fontbold != css.fontbold)
    {   if (fontbold == true)
        {   wBuffer = wBuffer + "font-weight: bold;";
        }
        else
        {   wBuffer = wBuffer + "font-weight: normal;";
        }
    }
        
    if (fontitalic != css.fontitalic)
    {   if (fontitalic == true)
        {   wBuffer = wBuffer + "font-style: italic;";
        }
        else
        {   wBuffer = wBuffer + "font-style: normal;";
        }
    }   
        
    if (alignment == 1)
    {   wBuffer = wBuffer + "text-align:right;";
    }
    else if (alignment == 2)
    {   wBuffer = wBuffer + "text-align:center;";
    }
        
    if ((callsubmit == true) || (fieldname.equals("") == false))
    {   wBuffer = wBuffer + "cursor:hand;";
    }
        
    if (visible == false)
    {   wBuffer = wBuffer + "visibility:hidden;";
    }                              
    
    
   if (wBuffer.equals("") == false)
   {
      out.print(" style = \"");
      wBuffer = wBuffer + "\"";
      out.print(wBuffer);
   }
}
/**
 * Prints label events on the Html code.
 * @throws Exception Return an exception if occurs an error.
 */
private void printEvents ()
   throws Exception
{
    String wBuffer= "";
    String wEventOnClick = "";
    String wEvents = "";

    
    for (int j=0; j < eventmaxindex;j++)
    {   if (eventname[j] != null)
        {   if (eventname[j].equalsIgnoreCase("onkeydown") == true)
            {
                eventOnKeydown = eventOnKeydown +  eventmethod[j] + "; ";
            }
            else if (eventname[j].equalsIgnoreCase("onclick") == true)
            {
                wEventOnClick = wEventOnClick +  eventmethod[j] + "; ";
            }
            else
            {   wEvents = wEvents + eventname[j] + "=\" " ;
                wEvents = wEvents + eventmethod[j] + "\" ";
            }
        }   
    }   
    
    
    if (onclick.equals("") == false)
    {   //wBuffer = wBuffer + " onClick=\"" + onclick + ";\"";
        wEventOnClick = onclick + "; " + wEventOnClick;
    }
    else
    {   if ((callsubmit == true )  || (fieldname.equals("")== false))
        {   if (fieldname.equals("")== false)
            {   //wBuffer = wBuffer + " onClick=\"" + name + "_OnClick(frm" + css.name + "." + fieldname + ",'"+ fieldvalue + "' );\"";
                wEventOnClick =  name + "_OnClick(frm" + css.name + "." + fieldname + ",'"+ fieldvalue + "' ); " + wEventOnClick;
            }
            else
            {   //wBuffer = wBuffer + " onClick=\"" + name + "_OnClick(null,'"+ fieldvalue + "' );\"";
                wEventOnClick =  name + "_OnClick(null,'"+ fieldvalue + "' ); " + wEventOnClick ;
            }                       
        }
    }
    
    /*for (int i=0; i < eventmaxindex;i++)
    {   if (eventname[i] != null)
        {   wBuffer = wBuffer + eventname[i] + "=\" ";
            wBuffer = wBuffer + eventmethod[i] + "\" ";
        }   
    } */
    
    if (wEventOnClick.equals("")==false)
    {
        wBuffer = wBuffer + " onClick=\"" + wEventOnClick + "\" ";
    }    
    if (wEvents.equals("")==false)
    {
        wBuffer = wBuffer + wEvents;
    }        
        
    for (int i=0; i < event.length;i++)
    {   if (event[i] != null)
        {   wBuffer = wBuffer + event[i] + "=\" ";
            wBuffer = wBuffer + method[i] + "\" ";
        }   
    }              

    if (wBuffer.equals("") == false)
    {
        out.print(wBuffer);
    }
}

private void closeTag ()
  throws Exception
{
    String wFieldCaption = caption;
    String wBuffer;
  
    if ((localeid.equals("")==false) && (GLB.LANGUAGE.trim().equals("")==false))
    {   try
        {
            Locale currentLocale;
            ResourceBundle messages;                
            currentLocale = new Locale(GLB.LANGUAGE);
            messages =   ResourceBundle.getBundle("Locale",currentLocale);
            wFieldCaption = messages.getString(localeid);                  
        }
        catch (Exception e)
        {              
        }
        
    }
    wBuffer = "";
    if (hyperlink.equals("")== false)
    {   
        wBuffer = wBuffer + " onkeydown=\"return(jKey(event)) " + eventOnKeydown + "\"><a CLASS=id" + name + " href=\"" + hyperlink +  "\">" + wFieldCaption + "</a>";
    }
    else
    {   
        if (eventOnKeydown.equals("")==false)
        {
            wBuffer = wBuffer + " onkeydown=\"" + eventOnKeydown + "\" ";
        } 
        wBuffer = wBuffer + ">";
        wBuffer = wBuffer + wFieldCaption;
    }    
    
    
   out.print(wBuffer);

   wBuffer = "</SPAN>";

   out.println(wBuffer);
  
}


}
  