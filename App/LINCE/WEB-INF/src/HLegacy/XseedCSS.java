package HLegacy;
public class XseedCSS
{   	
    public boolean autotab=true;
    public boolean autotabtexts=false;
    public String backcolor="FFFFFF";
    public String font="";
    public boolean fontbold=false;
    public boolean fontitalic=false;
    public int fontsize=8;    
    public String forecolor="000000";   
    public String name="";   
    
    

public XseedCSS(boolean pAutotab,String pBackcolor,String pFont, boolean pFontbold, boolean pFontitalic,
       int pFontsize,String pForecolor)
{	autotab = pAutotab;
	backcolor = pBackcolor;    	
	font = pFont;
	fontbold = pFontbold;
	fontitalic = pFontitalic;
	fontsize = pFontsize;	
    forecolor = pForecolor;  
}

public XseedCSS()
{  
}

}
  