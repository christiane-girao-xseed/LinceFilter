package HLegacy;
public class XseedImage
{   
     //appearance
	//borderstyle
    public boolean callsubmit = false;
	//description
    public boolean enabled = true;    
    public double eventindex = 0.0;    
    public String [] eventname;
    public String [] eventmethod;    
    public double eventmaxindex = 0.0;    
    public String fieldname = "";
    public String fieldvalue = "";
    public double height = 285.0;   
    public double left = 0.0;
    public String name = "";
	//noautomatic
    public String onclick="";
    public String picture = "";
    public boolean stretch = false;
    public int tabindex = -9999;
	//tabpage
    public String tooltiptext = "";
    public double top = 0.0;
    public boolean visible = true;
    public String visibility = "inherit";
    public double width = 120.0;  
    //
    public String event[];
    public String method[]; 
    
    
public XseedImage (boolean pCallsubmit,	boolean pEnabled,String pFieldname, String pFieldvalue,double pHeight,
	double pLeft, String pName, String pPicture, boolean pStretch, int pTabindex,double pTop, 
	boolean pVisible, double pWidth)

{	callsubmit = pCallsubmit;	
	enabled = pEnabled;
	fieldname = pFieldname;
	fieldvalue = pFieldvalue;
	height = pHeight;		
	left = pLeft;
	name = pName;
	picture = pPicture;
	stretch = pStretch;
	tabindex = pTabindex;
	top = pTop;
	visible = pVisible;
	width = pWidth;
}

public XseedImage ()
{
    event = new String[20];
    method = new String[20];
    eventname = new String[20]; 
    eventmethod = new String[20]; 
}
}
  