package HLegacy;
import java.io.*;
import java.math.*;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

public class XseedListbox
{   
    public XseedGLB GLB;    
    public XseedCSS css;
    public PrintWriter out;  
   
    // appearance
    public String backcolor = "FFFFFF";
    // clearwhen
    public String contents = "D";
    public String controlname = "";
    // datadisplay
    // decimals
    // description
    public String edit = "A";
    public boolean enabled = true;    
    public double eventindex = 0.0;        
    public String [] eventname;
    public String [] eventmethod;    
    public double eventmaxindex = 0.0;    
	public String font = "VERDANA";    
    public int fontsize = 8;
	public boolean fontbold = false;
	public boolean fontitalic = false;
    public String forecolor = "000000";
    public double height = 285.0;
    // showleadingzeros
    public double left = 0.0;
    // length
    // linkifpresent
    public double listindex = 0.0;    
    public String [] listname;
    public String [] listvalue;    
    public double maxindex = 0.0;
    public String  name = "";
    // noautomaticevents
    // nolookup
    // //' multiselect
    public boolean multiple = false;
    // optionlist
    // ordinate
    // required
    // sameas
    // showseparator
    // //' sorted
    // //' style
    public String ondblclickaction = "(none)";
    public String sessionid = "";
    public int tabindex = -9999;
    // tabpage    
    // tabstop
    // tooltiptext
    public double top = 0.0;
    // usage    
    public boolean visible = true;
    public String visibility = "inherit";
    public double width = 120.0;
    // zerosuppress
    //
    public String event[];
    public String method[];
    
    public String sendlistmode = "Disk";
       
public XseedListbox ()
{
    event = new String[20];
    method = new String[20];
    eventname = new String[20]; 
    eventmethod = new String[20]; 
}

private void fillArrayList()
    throws Exception
{
    XseedFile sldyFile = new XseedFile();
    String sldyLine;    
    String separator;
    String ispecName = XseedFunctions.replaceChar(css.name, '-', '_');  
    String ctrName;   
    int listindex=0;
    int i;         
    
    if (controlname.equals("") == true)
    {
        ctrName = XseedFunctions.replaceChar(name, '-', '_');  
    }
    else
    {
        ctrName = XseedFunctions.replaceChar(controlname, '-', '_');  
    }                        

    if (contents.equals("L") == true)
    {    
        if (XseedFunctions.FileExists(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid + ispecName + "." + ctrName +"(Temp)")==true)
        {
            XseedFunctions.FileCopy(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid + ispecName + "." + ctrName +"(Temp)",GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid + ispecName + "." + ctrName );
        }
        
        if (XseedFunctions.FileExists(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid + ispecName + "." + ctrName)==false)
        {            
            return;
        }
        sldyFile.open(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid + ispecName + "." + ctrName, "INPUT");
    }
    else
    {
        if (XseedFunctions.FileExists(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid + "asterisk." + ctrName +"(Temp)")==true)
        {
            XseedFunctions.FileCopy(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid + "asterisk." + ctrName +"(Temp)",GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid + "asterisk." + ctrName );
        }
        
        if (XseedFunctions.FileExists(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid + "asterisk." + ctrName)==false)
        {
            return;
        }
        sldyFile.open(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid + "asterisk." + ctrName, "INPUT");
    }
    
    if (maxindex == 0)
    {   maxindex = 1000;
        listname = new String [(int)maxindex];
        listvalue = new String [(int)maxindex];
    } 
   
    
    sldyLine = sldyFile.read();
    while (sldyLine!=null)
    {   
        separator = sldyLine.substring(0,1);                        

        i= sldyLine.indexOf(separator,1);        

        if (i!=-1)
        {   
        	 if ((int)maxindex <= (int)listindex) {
    			maxindex = maxindex + 500;
    			listname = XseedFunctions.ReDimPreserve(listname,(int) maxindex);
        		listvalue =XseedFunctions.ReDimPreserve(listvalue,(int) maxindex);        	
    		}
            listname[(int)listindex] = sldyLine.substring(i+1);        
            listvalue[(int)listindex]= sldyLine.substring(1,i);           
            listindex++;
        }  
        sldyLine = sldyFile.read();
    }                
    sldyFile.close();            
    maxindex = listindex;
}
private void sessionFillArrayList(HttpSession pSession)
	throws Exception
	{	   

	    String separator;
	    String ispecName = XseedFunctions.replaceChar(css.name, '-', '_');  
	    String ctrName;
	    String wSessionControlName = "";
	    ArrayList<?> wSessionControl =null;
	    int listindex=0;
	    int i;  
	    if (controlname.equals("") == true)
	    {
	         ctrName = XseedFunctions.replaceChar(name, '-', '_');  
	    }
	    else
	    {
	         ctrName = XseedFunctions.replaceChar(controlname, '-', '_');  
	    }    	   
	    if (contents.equals("L") == true)
	    {   
	      	wSessionControlName =  "XseedSendListDynamic_" + ispecName + "." + ctrName;   
	    }
	    else 
	    {	    
	        wSessionControlName =  "asterisk." + ctrName;	    	
	    }	    
	    if ((ArrayList<?>)pSession.getAttribute(wSessionControlName+ "(Temp)")!=null)
	    {	
	    	wSessionControl = (ArrayList<?>)pSession.getAttribute(wSessionControlName+"(Temp)");
	    	pSession.setAttribute(wSessionControlName,  wSessionControl);
	        pSession.removeAttribute(wSessionControlName+ "(Temp)");
	    }	        
	    
	    wSessionControl = (ArrayList<?>)pSession.getAttribute(wSessionControlName);

	    if (wSessionControl==null) return;
	  
	    if (maxindex == 0)
	    {   maxindex = 1000;
	        listname = new String [(int)maxindex];
	        listvalue = new String [(int)maxindex];
	    } 
	   
	    String wItem ="";
	    for (int j = 0; j < wSessionControl.size(); j++)  
	    {   
	    	wItem = wSessionControl.get(j).toString();
	        separator = wItem.substring(0,1);                        

	        i= wItem.indexOf(separator,1);        

	        if (i!=-1)
	        {   
	        	if ((int)maxindex <= (int)listindex) {
	    			maxindex = maxindex + 500;
	    			listname = XseedFunctions.ReDimPreserve(listname,(int) maxindex);
	        		listvalue =XseedFunctions.ReDimPreserve(listvalue,(int) maxindex);        	
	    		}
	            listname[(int)listindex] = wItem.substring(i+1);        
	            
	            listvalue[(int)listindex] = wItem.substring(1,i);;        
	            
	            listindex++;
	        }  	        

	    }                
	         
	    maxindex = listindex;    
	}

public void print (HttpSession pSession) throws Exception {
	try{
	
		if ((contents.equals("L")==true) || (contents.equals("*")==true))
		{
			sessionFillArrayList(pSession);
		}
		replaceSpaces();
    	printListBox();
	 } catch (Exception e){
		GLB.STATUS = "*****";
	}
    	
}

private void replaceSpaces()
  throws Exception  
{
    int i=0;
    int ind;
    while ((i < maxindex) && (listname[i]!=null))
    {       
        ind = listname[i].indexOf(" ",0);            
        while (ind!=-1)
        {   
            listname[i] = listname[i].substring(0,ind) + "&nbsp;" + listname[i].substring(ind+1) ;                    
            ind = listname[i].indexOf(" ",ind+1);            
        }
        i++;
    }
}

public void print ()
    throws Exception
{
    if (sendlistmode.equals("Disk")==true)
    {
        if ((contents.equals("L")==true) || (contents.equals("*")==true))
        {
            fillArrayList();
        }
    }
    replaceSpaces();
    printListBox();

}

private void printListBox ()
    throws Exception
{
      int i=0;
      boolean flag=false;
    
        String str ="";             
        str= "<select ";                        
        str = str + "name=\"" + name + "\" id=\"id" + name + "\" ";
        
        str = str + "style = \"position:absolute; " +
                    " top:" +  XseedFunctions.cStr(XseedFunctions.twipsperPixel(top)) + "px;" +
                    " left:"+ XseedFunctions.cStr(XseedFunctions.twipsperPixel(left))  + "px;" +
                    " width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)) + "px;" +
                    " height:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(height)) + "px;";
                        
        if (backcolor.equals("FFFFFF") == false)
        {   str = str + "background-color:" + backcolor + ";";
        }    
    
        if (forecolor.equals("000000")== false)
        {   str = str + "color:" + forecolor + ";";
        }    
        
        if (font.equals(css.font) == false)
        {   str = str + "font-family: " + font +  ";";
        }
       
        if (fontsize != css.fontsize)
        {   str = str +   "font-size: " + fontsize + "pt;";
        }
       
        if (fontbold != css.fontbold)
        {   if (fontbold == true)
            {   str = str + "font-weight: bold;";
            }
            else
            {   str = str + "font-weight: normal;";
            }
        }
        
        if (fontitalic != css.fontitalic)
        {   if (fontitalic == true)
            {   str = str + "font-style: italic;";
            }
            else
            {   str = str + "font-style: normal;";
            }
        }                 
              
                       
        if (visible  == false)   
        {   str = str + "visibility:hidden;";
        }
        str = str + "\"";
        
               
        if (multiple == true)
        {   str = str + " MULTIPLE ";
        }
                      
        str = str + "size=\"" + (int) height / 285 + "\" ";        
        
        if (enabled == false)
        {   str = str + "disabled ";
        }
            
        if (tabindex != -9999)  
        {   str = str + "tabindex=\"" + tabindex + "\" ";    
        }
                      
        
        
        for (int j=0; j < eventmaxindex;j++)
        {   if (eventname[j] != null)
            {   str = str + eventname[j] + "=\" ";
              
                if ( (eventname[j].toUpperCase().equals("ONDBLCLICK")) && (ondblclickaction.equals("Submit")))
                {
                    str = str + eventmethod[j] + "; jXmit();\" ";
                    flag= true;        
                }
                else
                {   str = str + eventmethod[j] + ";\" ";
                }
            }   
            
        }        
        for (int j=0; j < event.length;j++)
        {   if (event[j] != null)
            {   str = str + event[j] + "=\" ";
                str = str + method[j] + "\" ";
                if ( (eventname[j].toUpperCase().equals("ONDBLCLICK")) && (ondblclickaction.equals("Submit")))
                {
                    str = str + eventmethod[j] + "; jXmit();\" ";
                    flag= true;        
                }
                else
                {   str = str + eventmethod[j] + ";\" ";
                }
            }   
        }              
        if ((!flag) && (ondblclickaction.equals("Submit")))
        {   str = str + " ondblclick=\"jXmit();\"";
        }
        str = str + ">";
        while ((i < maxindex) && (listname[i]!=null))
        {   str = str + "<option ";                     
            if (listvalue[i].equals("ß") == true)
            {
                if (edit.equals("A")== true)
                {
                    listvalue[i]= "";
                }
                else
                {
                    listvalue[i]="0";
                }        
            }
            if (listname[i].equals("ß")== true)
            {
                listname[i]= "";
            }

            str = str + "value=\"" + listvalue[i] + "\">" + listname[i] + "</option>";                                        
            i++;
        }
        str = str + "</select>";                
        out.println(str);    
}

}