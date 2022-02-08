package HLegacy;
import java.io.*;
import java.math.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpSession;


public class XseedCombobox
{
    private BigDecimal bigZeros = new BigDecimal(0);

    public XseedGLB GLB;
    public XseedCSS css;
    public PrintWriter out;

    //Appearance
    //ClearWhen
    public String contents = "D";
    public String controlname = "";
    // DataDisplay;
    //Decimals
    //DecimalKeyed
    //Description
    public String edit = "A";
    //Enabled
    public String font = "VERDANA";
    public boolean fontbold = false;
    public boolean fontitalic = false;
    public int fontsize = 8;
    public String forecolor = "000000";
    //ShowLeadingZeros
    public boolean editable = false;
    public double eventindex = 0.0;
    public String [] eventname;
    public String [] eventmethod;
    public double eventmaxindex = 0.0;
    public double left = 0.0;
    //LeftFill
    public double length = 1.0;
    //LinkIfPresent
    public double listindex = 0.0;
    public String [] listname;
    public String [] listvalue;
    public boolean uselocaleid=false;
    public double maxindex = 0.0;
    //LockSign
    public String name = "";
    //NoAutomaticEvents
    //NoLookUp
    //NotEntered
    //OptionList
    //Ordinate
    //Required
    //SameAs
    //ShowSeparator
    //' Sorted
    public double size = 0.0;
    public String sessionid = "";
    public String source = "";
    public int tabindex = -9999;
    //TabPage
    //TabStop
    //ToolTipText
    public double top = 0.0;
    //Usage
    public String value = "";
    public boolean visible = true;
    public double width = 120.0;
    public boolean zerosuppress = false;


    public String event[];
    public String method[];

    public String sendlistmode = "Disk";


public XseedCombobox ()
{
    event = new  String[20];
    method = new  String[20];
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

        if (XseedFunctions.FileExists(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid +ispecName + "." + ctrName)==false)
        {
            return;
        }
        sldyFile.open(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid + ispecName + "." + ctrName, "INPUT");
    }
    else
    {
        if (XseedFunctions.FileExists(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid +"asterisk." + ctrName +"(Temp)")==true)
        {
            XseedFunctions.FileCopy(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid +"asterisk." + ctrName +"(Temp)",GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid +"asterisk." + ctrName );
        }

        if (XseedFunctions.FileExists(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid +"asterisk." + ctrName)==false)
        {
            return;
        }
        sldyFile.open(GLB.TEMPDIR + File.separatorChar + GLB.FULLSTN + sessionid +"asterisk." + ctrName, "INPUT");
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

            listvalue[(int)listindex] = sldyLine.substring(1,i);;

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
		      
    	if (editable==true)
    	{        
        	openPanel();
        	printTextbox();
        	printImage();
        	closePanel();
        	printListbox();        
        	printScripts();
    	}
    	else
    	{ 
        	printCombobox();
    	}    	
    } catch (Exception e){
		GLB.STATUS = "*****";
	}
}

public void print ()
    throws Exception
{
    if (sendlistmode.equals("Disk")==true)
    {  if ((contents.equals("L")==true) || (contents.equals("*")==true))
        {
            fillArrayList();
        }
    }

    if (editable==true)
    {
        openPanel();
        printTextbox();
        printImage();
        closePanel();
        printListbox();
        printScripts();
    }
    else
    {
        printCombobox();
    }

}
public void openPanel ()
   throws Exception
{
   // Panel
   String wBuffer= "<span id=\"" + name + "_pnl\"";
   wBuffer = wBuffer + " style = \"position:absolute;";
   wBuffer = wBuffer + " top:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(top)) + "px;" +
                       " left:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(left)) + "px;" +
                       " height:20px;" +
                       " width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)) + "px;" +
                       " border-color:0080C0;border-style:Solid;border-width:1;" ;
                       //"background-color:FFFFFF;font-family:Verdana;font-size:11;font-style:normal;font-weight:normal;+


   if (visible  == false)
   {   wBuffer = wBuffer + "visibility:hidden;";
   }
   wBuffer = wBuffer + "\">";
   out.println(wBuffer);

}

public void printTextbox ()
   throws Exception
{
    String wBuffer;
    String strEvents = "";
    String strEventOnKeyUp="";
    String strEventOnBlur ="";
    String strEventOnFocus ="";
    wBuffer = "<input type=\"text\" name=\"" + name + "\"" +
              " id=\"" + name + "\""  +
              " maxlength=\"" + XseedFunctions.cStr(length) + "\"" +
              " style = \"position:absolute; border:0; "+
              " top:0px;" +
              " left:0px;" +
              " height:18px;" +
              " width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)-20) + "px;" +
              " visibility:hidden;" +
              "\">";

    out.println(wBuffer);


    wBuffer = "<input type=\"text\" name=\"" + name + "_txt\"" +
              " id=\"" + name + "_txt\""  +
              " maxlength=\"" + XseedFunctions.cStr(length) + "\"" +
              " style = \"position:absolute; border:0; "+
              " top:0px;" +
              " left:0px;" +
              " height:18px;" +
              " width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)-20) + "px;" ;


    if (forecolor.equals("000000")== false)
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
    if (visible  == false)
    {   wBuffer = wBuffer + "visibility:hidden;";
    }
    wBuffer = wBuffer + "\"";

    if (tabindex != -9999)
    {   wBuffer = wBuffer + " tabindex=\"" + tabindex + "\"";
    }

    out.println(wBuffer);

    //eventos
    wBuffer = "";
    /*

    for (int j=0; j < eventmaxindex;j++)
    {   if (eventname[j] != null)
        {   wBuffer = wBuffer + eventname[j] + "=\" ";
            wBuffer = wBuffer + eventmethod[j] + "\" ";
        }
    } */

     /*
     *Tratamento eventos
     */
     for (int i=0; i < eventmaxindex;i++)
     {
     	if (eventname[i] != null)
        {
        	if (eventname[i].equalsIgnoreCase("onfocus") == true)
            {
                strEventOnFocus = strEventOnFocus +  eventmethod[i] + "; ";
            }
            else if (eventname[i].equalsIgnoreCase("onkeyup") == true)
            {
               	strEventOnKeyUp = strEventOnKeyUp + eventmethod[i] + "; ";
            }
            else
            {   strEvents = strEvents + eventname[i] + "=\" " ;
                strEvents = strEvents + eventmethod[i] + "\" ";
            }
        }
    }

    for (int j=0; j < event.length;j++)
    {   if (event[j] != null)
         {   wBuffer = wBuffer + event[j] + "=\" ";
             wBuffer = wBuffer + method[j] + "\" ";
         }
    }

    wBuffer = wBuffer + " onKeyUp=\"" + name + "_txtKey(this.value)\";" + strEventOnKeyUp ;

    if ((edit.equals("A") == false ) && (edit.equals("D") == false))
    {   wBuffer = wBuffer + "onfocus=\"jFocusN(this)\"; " + strEventOnFocus;
        if (zerosuppress == false)
        {    wBuffer = wBuffer + "onblur=\"jBlurN(this)\"; " + strEventOnBlur;
        }
    }
    else
    {   wBuffer = wBuffer + "onfocus=\"jFocusA(this)\"; " + strEventOnFocus;
    }

    wBuffer = wBuffer + strEvents ;

    wBuffer = wBuffer + ">";
    out.println(wBuffer);
}

public void printImage()
throws Exception
{
    String wBuffer;
    wBuffer = "<img name=\"" + name + "_img\"" +
              " id=\"" + name + "_img\"" +
              " src=\"" + source + "\"" +
              " style = \"position:absolute;border:0;" +
              " height:16px;" +
              " top: 1px;" +
              " left:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)-19) + "px;" +
              " width:16px;" ;
    if (visible  == false)
    {   wBuffer = wBuffer + "visibility:hidden;";
    }
    wBuffer = wBuffer + "\"";
    wBuffer = wBuffer + " onclick=\"" + name + "_imageClick()\">";
    out.println(wBuffer);
}

public void closePanel ()
   throws Exception
{

    out.println("</span>");
}

public void printListbox ()
   throws Exception
{
    StringBuilder wBuffer= new StringBuilder("");
    int i=0;
    int height=0;    
    Locale wCurrentLocale;
    ResourceBundle wMessages; 
    String wCaption="";
    
    if (size==0)
    {   if (maxindex < 30) 
        {   size =  maxindex;
        }
        else
        {   size = 30;                 
        }
    }  
    if (size==1)
    {
        size=2;
        height=21;
    }   
    
    wBuffer.append("<select  name=\"").append(name).append("_lst\"");
    wBuffer.append(" id =\"").append(name).append("_lst\""); 
    wBuffer.append(" size=\"").append(XseedFunctions.cStr(size)).append("\"");
    wBuffer.append(" style = \"position:absolute;");                               
    wBuffer.append(" left:").append(XseedFunctions.cStr(XseedFunctions.twipsperPixel(left))).append("px;");
    wBuffer.append(" top:").append(XseedFunctions.cStr(XseedFunctions.twipsperPixel(top) + 22)).append("px;");
    wBuffer.append(" width:").append(XseedFunctions.cStr(XseedFunctions.twipsperPixel(width))).append("px;");
    wBuffer.append(" z-index:1;");
           
        
    if (forecolor.equals("000000")== false)
    {   wBuffer.append("color:").append(forecolor).append(";");
    }  
              
    if (font.equals(css.font) == false)
    {   wBuffer.append("font-family: ").append(font).append(";");
    }
       
    if (fontsize != css.fontsize)
    {   wBuffer.append("font-size: ").append(fontsize).append("pt;");
    }
       
    if (fontbold != css.fontbold)
    {   if (fontbold == true)
       {   wBuffer.append("font-weight: bold;");
       }
       else
       {   wBuffer.append("font-weight: normal;");
       }
    }
        
    if (fontitalic != css.fontitalic)
    {   if (fontitalic == true)
       {   wBuffer.append("font-style: italic;");
       }
       else
       {   wBuffer.append("font-style: normal;");
       }
    }   
               
    if (height>0)
    {
        wBuffer.append(" height:").append(height).append("px;") ;
    }               
    wBuffer.append(" visibility:Hidden;\""); 
    wBuffer.append(" onblur=\"").append(name).append("_listBlur()\"");
    wBuffer.append(" onclick=\"").append(name).append("_listClick()\">");
    out.println(wBuffer.toString());
    wBuffer = new StringBuilder("");
    while ((i < maxindex) && (listname[i]!=null))
     {     
    	 wBuffer.append("<option ");   

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
         
         if (edit.equals("A")== true)
         {  if (XseedFunctions.rTrim(listvalue[i].toUpperCase()).equals(XseedFunctions.rTrim(value.toUpperCase())))
            {
                wBuffer.append(" selected=\"true\" ");
            }
         }
         else
         { 
            if (XseedFunctions.cDbl(listvalue[i]) == XseedFunctions.cDbl(value))
            {
                wBuffer.append(" selected=\"true\" ");
            }
         }            
         
         if ((uselocaleid==true) && (GLB.LANGUAGE.trim().equals("")==false)) 
         {   try{ 
        	   wCurrentLocale = new Locale(GLB.LANGUAGE);
	           wMessages = ResourceBundle.getBundle("Locale",wCurrentLocale);               
	           wCaption =wMessages.getString(listname[i].trim());
             } catch (Exception e) {
            	 wCaption = listname[i]; 
             }             
	         wBuffer.append("value=\"").append(listvalue[i]).append("\">").append(wCaption).append("</option>");
         } else {
             wBuffer.append("value=\"").append(listvalue[i]).append("\">").append(listname[i]).append("</option>");
         } 
         i++;     
     } 
    out.println(wBuffer.toString());    
    out.println("</select>");
}    

public void printScripts()
  throws Exception
{

    out.println("<script language=\"JavaScript\">");
    //
    out.println("   List = document.getElementById('" + name + "_lst');");
    out.println("   TextList = document.getElementById('" + name + "_txt');");
    out.println("   TextValue = document.getElementById('" + name + "');");
    out.println("   for (i = 0; i < List.options.length; i++)");
    out.println("   {");
    out.println("      if (List.options[i].selected == true)");
    out.println("      {");
    out.println("         TextList.value = List.options[i].text;");
    out.println("         TextValue.value = List.options[i].value;");
    out.println("      }");
    out.println("   }");
    out.println("   if (i == List.options.length)");
    out.println("   {");
    out.println("         TextList.value = \""  + value + "\";");
    out.println("         TextValue.value = \""  + value + "\";");
    out.println("   }");
    if ( (zerosuppress ==true) && (edit.equals("A")==false) && (XseedFunctions.cDbl(value)==0))
    {
          out.println("   TextList.value = \"\";");
    }
    out.println(name + "_listClick();");
    out.println("function " + name + "_imageClick()");
    out.println("{");
    out.println("   List = document.getElementById('" + name + "_lst');");
    out.println("   if (List.style.visibility.toUpperCase() == 'HIDDEN')");
    out.println("   {");
    out.println("      Panel = document.getElementById('" + name + "_pnl');");
    out.println("      List.style.visibility = 'Visible';");
    out.println("      List.focus();");
    out.println("   }");
    out.println("   else if (" + name + "_isExpand == 1)");
    out.println("   {  List.style.visibility = 'Hidden';");
    out.println("   }");
    out.println("}");


    out.println("function " + name + "_listBlur()");
    out.println("{");
    out.println("   List = document.getElementById('" + name +  "_lst');");
    out.println("   List.style.visibility = 'Hidden';");
    out.println("   " + name + "_isExpand=1;");
    out.println("}");

    out.println("function " + name + "_listClick()");
    out.println("{");
    out.println("   List = document.getElementById('" + name + "_lst');");
    out.println("   TextList = document.getElementById('" + name + "_txt');");
    out.println("   TextValue = document.getElementById('" + name + "');");
    out.println("   for (i = 0; i < List.options.length; i++)");
    out.println("   {");
    out.println("      if (List.options[i].selected == true)");
    out.println("      {");
    out.println("         TextList.value = List.options[i].text;");
    out.println("         TextValue.value = List.options[i].value;");
    out.println("      }");
    out.println("   }");
    out.println("   List.style.visibility = 'Hidden';");
    out.println("}");

    out.println("function " + name + "_txtKey(pValue)");
    out.println("{");
    out.println("   List = document.getElementById('" + name + "_lst');");
    out.println("   TextValue = document.getElementById('" + name + "');");
    out.println("   for (i=0; i < List.length; i++)");
    out.println("   {   ");
    out.println("       if (rTrim(List.options[i].text.toUpperCase()) == rTrim(pValue).toUpperCase())");
    out.println("       {");
    out.println("           List.selectedIndex=i;");
    out.println("           TextValue.value = List.options[i].value;");
    out.println("           return;");
    out.println("       }");
    out.println("   }");
    out.println("   TextValue.value = pValue;");
    out.println("}");

    out.println("</script>");
}


public void printCombobox ()
    throws Exception
{
      int i=0;
      Locale wCurrentLocale;
      ResourceBundle wMessages; 
      String wCaption="";
           
      
        StringBuilder str = new StringBuilder("");             
        str.append("<select ");  
        //str = str + "name=\"" + name + "\" id=\"id" + name + "\" "; 
        str.append("name=\"").append(name).append("\" id=\"id").append(name).append("\" ");
                
        str.append("style = \"position:absolute; ");                   
        str.append(" top:").append(XseedFunctions.cStr(XseedFunctions.twipsperPixel(top))).append("px;");
        str.append(" left:").append(XseedFunctions.cStr(XseedFunctions.twipsperPixel(left))).append("px;"); 
        str.append(" width:").append(XseedFunctions.cStr(XseedFunctions.twipsperPixel(width))).append("px;");
        str.append(" height:19px;") ;
        
        if (forecolor.equals("000000")== false)
        {   str.append("color:").append(forecolor).append(";");
        }  
         
        if (font.equals(css.font) == false)
        {   str.append("font-family: ").append(font).append(";");
        }
       
        if (fontsize != css.fontsize)
        {   str.append("font-size: ").append(fontsize).append("pt;");
        }
       
        if (fontbold != css.fontbold)
        {   if (fontbold == true)
            {   str.append("font-weight: bold;");
            }
            else
            {   str.append("font-weight: normal;");
            }
        }
        
        if (fontitalic != css.fontitalic)
        {   if (fontitalic == true)
            {   str.append("font-style: italic;");
            }
            else
            {   str.append("font-style: normal;");
            }
        }   
        
        if (visible  == false)   
        {   str.append("visibility:hidden;");
        }
        str.append("\"");
        
        if (tabindex != -9999)  
        {   str.append(" tabindex=\"").append(tabindex).append("\"");        
        }
        
        for (int j=0; j < eventmaxindex;j++)
        {   if (eventname[j] != null)
            {   str.append(eventname[j]).append("=\" ");
                str.append(eventmethod[j]).append("\" ");
            }   
        }        
        for (int j=0; j < event.length;j++)
        {   if (event[j] != null)
            {   str.append( event[j]).append("=\" ");
                str.append(method[j]).append("\" ");
            }   
        }              
        str.append(">");
        while ((i < maxindex) && (listname[i]!=null))
        {   str.append("<option ");                     
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
           
            if ((uselocaleid==true) && (GLB.LANGUAGE.trim().equals("")==false)) 
            {  try {
           	      wCurrentLocale = new Locale(GLB.LANGUAGE);
   	              wMessages = ResourceBundle.getBundle("Locale",wCurrentLocale);               
   	              wCaption =wMessages.getString(listname[i].trim());
               } catch (Exception e) {
            	   wCaption= listname[i];
            	  
               }
               str.append("value=\"").append(listvalue[i]).append("\">").append(wCaption).append("</option>");	
               
            } else {
               str.append("value=\"").append(listvalue[i]).append("\">").append(listname[i]).append("</option>");	
            }
                                                    
            i++;
        }
        str.append("</select>");                
        out.println(str.toString());    
}
}
