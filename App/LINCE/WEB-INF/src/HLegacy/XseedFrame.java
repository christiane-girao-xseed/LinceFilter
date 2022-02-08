package HLegacy;
import java.io.*;
import java.math.*;
import java.util.*;
import javax.servlet.http.*;

public class XseedFrame
{
    private BigDecimal bigZeros = new BigDecimal(0);

    public XseedGLB GLB;
    public XseedCSS css;
    public PrintWriter out;

    //Optionlist
    public Vector<String> captionarray = new Vector<String>();
    public Vector<String> valuearray = new Vector<String>();
    public Vector<String> localeidarray = new Vector<String>();

    public String name = "";
    public String backcolor = "";
    public int borderstyle = 0;
    public String caption = "";
    public String datadisplay = "";
    public int decimals = 0;
    public String description = "";
    public String edit = "A";
    public boolean enabled = true;
    public String font = "VERDANA";
    public boolean fontbold = false;
    public boolean fontitalic = false;
    public int fontsize = 8;
    public String forecolor = "000000";
    public double height = 285.0;
    public String implementedas = "";
    public double left = 0.0;
    public int length = 1;
    public String localeid = "";
    public String orientation = "V";
    public int tabindex = -9999;
    public String tooltiptext = "";
    public double top = 0.0;
    public String usage = "DEF";
    public boolean visible = true;
    public double width = 120.0;

    private HttpServletRequest request;


public XseedFrame()
{
}

public void print (HttpServletRequest pRequest) throws Exception
{
    request=pRequest;
    print();
}

public void print ()
	throws Exception
	{

    if (implementedas.equalsIgnoreCase("FIELDSET")== true)
    {
        printFieldSet ();
    }
    else
    {
        printControl ();
    }
}

public void printControl ()
    throws Exception
{
	Locale currentLocale;
	ResourceBundle messages;
	String str = "", buffer = "", accessKey = "", fieldCaption = "";
	double currentTop = 0, currentLeft = 0, currentWidth = 0;

    if (forecolor.equals(css.forecolor) == false)
    {   buffer = buffer + "color: " + forecolor +  ";";
    }

    if (backcolor.equals(css.backcolor) == false)
    {   buffer = buffer + "background-color: " + backcolor +  ";";
    }

    if (font.equals(css.font) == false)
    {   buffer = buffer + "font-family: " + font +  ";";
    }

    if (fontsize != css.fontsize)
    {   buffer = buffer +   "font-size: " + fontsize + "pt;";
    }

    if (fontbold != css.fontbold)
    {   if (fontbold == true)
        {   buffer = buffer + "font-weight: bold;";
        }
        else
        {   buffer = buffer + "font-weight: normal;";
        }
    }

    if (fontitalic != css.fontitalic)
    {   if (fontitalic == true)
        {   buffer = buffer + "font-style: italic;";
        }
        else
        {   buffer = buffer + "font-style: normal;";
        }
    }

    if (visible  == false)
    {   buffer = buffer + "visibility:hidden;";
    }

    if (borderstyle == 0)
    {
    	buffer = buffer + "border=\"0\"";
    }

    if (borderstyle == 0)
    {

	   if (request.getHeader("user-agent").indexOf("Firefox") != -1)
	   {

	        str = "<SPAN id=\"id" + name + "\" ";

			str = str + "style = \"position:absolute;" +
			                "top:" +  XseedFunctions.cStr(XseedFunctions.twipsperPixel(top)) + "px;" +
			                "left:"+ XseedFunctions.cStr(XseedFunctions.twipsperPixel(left))  + "px;" +
			                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)- 18) + "px;" +
			                "height:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(height)- 16) + "px;" +
			                buffer;

	        str = str +  "\">";
	        out.println(str);
	    } else {
	        str = "<SPAN id=\"id" + name + "\" ";

			str = str + "style = \"position:absolute;" +
			                "top:" +  XseedFunctions.cStr(XseedFunctions.twipsperPixel(top)) + "px;" +
			                "left:"+ XseedFunctions.cStr(XseedFunctions.twipsperPixel(left))  + "px;" +
			                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)) + "px;" +
			                "height:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(height)) + "px;" +
			                buffer;

	        str = str +  "\">";
	        out.println(str);
	    }




	    if(orientation.equalsIgnoreCase("H"))
	    {
	    	currentLeft = 0;
	    	if(captionarray.size() > 0)
	    	{
	    		currentWidth = width / captionarray.size();
	    	}
	    }
	    else
	    {
	    	currentTop = 0;
		}

        for (int i=0; i < captionarray.size(); i++)
        {
            if (request.getHeader("user-agent").indexOf("Firefox") != -1)
	   		{
	            str = "   <SPAN id=\"idLayer" + name + "_" + XseedFunctions.cStr(i) + "\" style = \"position:absolute;";

		        if(orientation.equalsIgnoreCase("H"))
		        {

				    str = str + "top:0px;" +
				                "left:"+ XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentLeft)-8)  + "px;" +
				                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentWidth)-10) + "px;" +
				                "height:16px; " +
				                buffer;
		        	currentLeft = currentLeft + currentWidth;
		        }
		        else
		        {
				    str = str + "top:" +  XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentTop)-16) + "px;" +
				                "left:1px;" +
				                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width) - 16) + "px;" +
				                "height:16px; " +
				                buffer;
		        	currentTop = currentTop + 300;
		    	}

	            str = str + "\">";
	            out.println(str);
	        } else {

		        str = "   <SPAN id=\"idLayer" + name + "_" + XseedFunctions.cStr(i) + "\" style = \"position:absolute;";

		        if(orientation.equalsIgnoreCase("H"))
		        {

				    str = str + "top:0px;" +
				                "left:"+ XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentLeft))  + "px;" +
				                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentWidth)) + "px;" +
				                "height:16px; " +
				                buffer;
		        	currentLeft = currentLeft + currentWidth;
		        }
		        else
		        {
				    str = str + "top:" +  XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentTop)) + "px;" +
				                "left:1px;" +
				                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)) + "px;" +
				                "height:16px; " +
				                buffer;
		        	currentTop = currentTop + 300;
		    	}

	            str = str + "\">";
	            out.println(str);


	        }
            str = "      <input type=\"radio\" ";
            str = str + "name=\"" + name + "\" ";
            if( tabindex != -9999)
            {
                str = str + "tabindex=\"" + tabindex + "\" ";
            }
            str = str + "value=\"" + (Object)valuearray.get(i) + "\" ";
		    if ((((Object)localeidarray.get(i)).equals("") == false) &&
		    	(GLB.LANGUAGE.trim().equals("") == false ) )
		    {
		    	try
		        {
		            currentLocale = new Locale(GLB.LANGUAGE);
		            messages = ResourceBundle.getBundle("Locale",currentLocale);
		        	fieldCaption = messages.getString((String)localeidarray.get(i));
		            accessKey = getAccessKey(fieldCaption);
		            if(accessKey.equals("") == false)
		            {
		            	str = str + " accesskey = \"" + accessKey + "\"";
		            }
		        	str = str + ">" + checkTagU(fieldCaption);
		        }
		        catch (Exception e)
		        {
		        	fieldCaption = (String)captionarray.get(i);
		            accessKey = getAccessKey(fieldCaption);
		            if(accessKey.equals("") == false)
		            {
		            	str = str + " accesskey = \"" + accessKey + "\"";
		            }
		        	str = str + ">" + checkTagU(fieldCaption);
		        }
		    }
		    else
		    {
	        	fieldCaption = (String)captionarray.get(i);
	            accessKey = getAccessKey(fieldCaption);
	            if(accessKey.equals("") == false)
	            {
	            	str = str + " accesskey = \"" + accessKey + "\"";
	            }
	        	str = str + ">" + checkTagU(fieldCaption);
		    }
            out.println(str);
            str = "    </SPAN>";
            out.println(str);
        }
        str = "</SPAN>";
        out.println(str);
    }
    else
    {
        str = "<TABLE id=\"id" + name + "\" border=\"0\" cellpadding=\"1\" cellspacing=\"0\" width=\"100%\" ";
		str = str + "style = \"position:absolute;" +
		                "top:" +  XseedFunctions.cStr(XseedFunctions.twipsperPixel(top)) + "px;" +
		                "left:"+ XseedFunctions.cStr(XseedFunctions.twipsperPixel(left))  + "px;" +
		                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width)-18) + "px;" +
		                "height:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(height)-16) + "px;" +
		                "background-color:#666699;\">";
        out.println(str);

        out.println("   <TR>");
        out.println("   <TD>");

        str = "   <TABLE id=\"id" + name + "Table\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\" width=\"100%\" ";
		str = str + "style = \"position:absolute; " +
		                "top:1px;" +
		                "left:1px;" +
		                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width) - 2) + "px;" +
		                "height:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(height) - 2) + "px;" +
		                buffer + " \">";
        out.println(str);

        out.println("      <TR>");
        out.println("      <TD>");

        str = "         <SPAN id=\"id" + name + "Header\"";
		str = str + "style = \"position:absolute; " +
		                "top:1px; " +
		                "left:1px; " +
		                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width) - 2) + "px; " +
		                "height:1px;" +
		                "background-color:#666699;color:#FFFFFF \">";

	    if ( (localeid.equals("") == false) &&
	    	 (GLB.LANGUAGE.trim().equals("") == false ) )
	    {
	    	try
	        {
	            currentLocale = new Locale(GLB.LANGUAGE);
	            messages = ResourceBundle.getBundle("Locale",currentLocale);
	        	fieldCaption = messages.getString(localeid);
	        	str = str + fieldCaption;
	        }
	        catch (Exception e)
	        {
	        	str = str + caption;
	        }
	    }
	    else
	    {
        	str = str + caption;
	    }

        str = str + "</SPAN>";
        out.println(str);

        out.println("      </TD>");
        out.println("      </TR>");


	    if(orientation.equalsIgnoreCase("H"))
	    {
	    	currentLeft = 15;
	    	if(captionarray.size() > 0)
	    	{
	    		currentWidth = (width / captionarray.size()) - 30;
	    	}
	    }
	    else
	    {
	    	currentTop = 300;
		}

        for (int i=0; i < captionarray.size(); i++)
        {
            out.println("      <TR>");
            out.println("      <TD>");

            str = "         <SPAN id=\"idLayer" + name + "_" + XseedFunctions.cStr(i) + "\" ";
            str = str + "style = \"position:absolute; ";
	        if(orientation.equalsIgnoreCase("H"))
	        {
			    str = str + "top:20px;" +
			                "left:"+ XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentLeft))  + "px;" +
			                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentWidth)) + "px;" +
			                "height:16px; " +
			                buffer;
	        	currentLeft = currentLeft + currentWidth;
	        }
	        else
	        {
			    str = str + "top:" +  XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentTop)) + "px;" +
			                "left:1px;" +
			                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width) - 6) + "px;" +
			                "height:16px; " +
			                buffer;
	        	currentTop = currentTop + 300;
	    	}
            str = str + "\">";
            out.println(str);

            str = "            <input type=\"radio\" ";
            str = str + "name=\"" + name + "\" ";
            if( tabindex != -9999)
            {
                str = str + "tabindex=\"" + tabindex + "\" ";
        	}
            str = str + "value=\"" +  (Object)valuearray.get(i)  + "\" ";
		    if ((((Object)localeidarray.get(i)).equals("") == false) &&
		    	(GLB.LANGUAGE.trim().equals("") == false ) )
		    {
		    	try
		        {
		            currentLocale = new Locale(GLB.LANGUAGE);
		            messages = ResourceBundle.getBundle("Locale",currentLocale);
		        	fieldCaption = messages.getString((String)localeidarray.get(i));
		            accessKey = getAccessKey(fieldCaption);
		            if(accessKey.equals("") == false)
		            {
		            	str = str + " accesskey = \"" + accessKey + "\"";
		            }
		        	str = str + ">" + checkTagU(fieldCaption);

		        }
		        catch (Exception e)
		        {
		        	fieldCaption = (String)captionarray.get(i);
		            accessKey = getAccessKey(fieldCaption);
		            if(accessKey.equals("") == false)
		            {
		            	str = str + " accesskey = \"" + accessKey + "\"";
		            }
		        	str = str + ">" + checkTagU(fieldCaption);
		        }
		    }
		    else
		    {
	        	fieldCaption = (String)captionarray.get(i);
	            accessKey = getAccessKey(fieldCaption);
	            if(accessKey.equals("") == false)
	            {
	            	str = str + " accesskey = \"" + accessKey + "\"";
	            }
	        	str = str + ">" + checkTagU(fieldCaption);
		    }
            out.println(str);

            str = "         </SPAN>";
            out.println(str);

            out.println("      </TD>");
            out.println("      </TR>");

    	}

        out.println("   </TABLE>");

        out.println("   </TD>");
        out.println("   </TR>");
        out.println("</TABLE>");
    }

}

private void printFieldSet ()
 throws Exception
{
	Locale currentLocale;
	ResourceBundle messages;
	String buffer = "", accessKey = "", fieldCaption = "";
	double currentTop = 0, currentLeft = 0, currentWidth = 0;

    String str ="";
    str= "<fieldset ";
    str = str + "name=\"field" + name + "\" id=\"id" + name + "\" ";

    str = str + "style = \"position:absolute;" +
                "top:" +  XseedFunctions.cStr(XseedFunctions.twipsperPixel(top)) + "px;" +
                "left:"+ XseedFunctions.cStr(XseedFunctions.twipsperPixel(left))  + "px;" +
                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width-18)) + "px;" +
                "height:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(height-16)) + "px;" ;


    if (forecolor.equals(css.forecolor) == false && forecolor.equals("") == false)
    {   buffer = buffer + "color: " + forecolor +  ";";
    }

    if (backcolor.equals(css.backcolor) == false && backcolor.equals("") == false)
    {   buffer = buffer + "background-color:" + backcolor +  ";";
    }

    if (font.equals(css.font) == false && font.equals("") == false)
    {   buffer = buffer + "font-family:" + font +  ";";
    }

    if (fontsize != css.fontsize)
    {   buffer = buffer +   "font-size:" + fontsize + "pt;";
    }

    if (fontbold != css.fontbold)
    {   if (fontbold == true)
        {   buffer = buffer + "font-weight:bold;";
        }
        else
        {   buffer = buffer + "font-weight:normal;";
        }
    }

    if (fontitalic != css.fontitalic)
    {   if (fontitalic == true)
        {   buffer = buffer + "font-style:italic;";
        }
        else
        {   buffer = buffer + "font-style:normal;";
        }
    }

    str = str + buffer;

    if (visible  == false)
    {   str = str + "visibility:hidden;";
    }

    if (borderstyle == 0)
    {
    	str = str + "border=\"0\"";
    }

    str = str + "\"";

    str = str + ">";

    out.println(str);

    str = "<LEGEND id=\"idLegend" + name + "\" style = \"" + buffer + "\">";

    if ((localeid.equals("") == false) &&
    	(GLB.LANGUAGE.trim().equals("") == false ) )
    {
    	try
        {
            currentLocale = new Locale(GLB.LANGUAGE);
            messages = ResourceBundle.getBundle("Locale",currentLocale);
            fieldCaption = messages.getString(localeid);
			str = str + fieldCaption;
        }
        catch (Exception e)
        {
        	str = str + caption;
        }
    }
    else
    {
    	str = str + caption;
    }

    str = str + "</LEGEND>" ;
    if((caption.trim().equals("") == false) || (fieldCaption.trim().equals("") == false))
    {
    	out.println(str);
    }

    if(orientation.equalsIgnoreCase("H"))
    {
    	currentLeft = 1;
    	if(captionarray.size() > 0)
    	{
    		currentWidth = (width / captionarray.size()) - 30;
    	}
    }
    else
    {
    	currentTop = 225;
	}

    for (int i=0; i < captionarray.size(); i++)
    {

        str = "<SPAN id=\"idLayer" + name + "_" + XseedFunctions.cStr(i) + "\" ";

	    str = str + "style = \"position:absolute;";

        if(orientation.equalsIgnoreCase("H"))
        {
		    str = str + "top:15px;" +
		                "left:"+ XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentLeft))  + "px;" +
		                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentWidth)) + "px;" +
		                "height:16px; " +
		                buffer;
        	currentLeft = currentLeft + currentWidth;
        }
        else
        {
		    str = str + "top:" +  XseedFunctions.cStr(XseedFunctions.twipsperPixel(currentTop)) + "px;" +
		                "left:1px;" +
		                "width:" + XseedFunctions.cStr(XseedFunctions.twipsperPixel(width) - 6) + "px;" +
		                "height:16px; " +
		                buffer;
        	currentTop = currentTop + 300;
    	}


        str = str + "\">";
        out.println(str);

        str  = "<input type=\"radio\" ";
        str = str + "name=\"" + name + "\" ";
        if (tabindex != -9999)
        {    str = str + "tabindex=\"" + tabindex + "\" ";
        }
        str = str + "value=\"" + (Object)valuearray.get(i) + "\" ";


	    if ((((Object)localeidarray.get(i)).equals("") == false) &&
	    	(GLB.LANGUAGE.trim().equals("") == false ) )
	    {
	    	try
	        {
	            currentLocale = new Locale(GLB.LANGUAGE);
	            messages = ResourceBundle.getBundle("Locale",currentLocale);
	            fieldCaption = messages.getString((String)localeidarray.get(i));
	            accessKey = getAccessKey(fieldCaption);
	            if(accessKey.equals("") == false)
	            {
	            	str = str + " accesskey = \"" + accessKey + "\"";
	            }
	            str = str + ">" + checkTagU(fieldCaption);
	        }
	        catch (Exception e)
	        {
	        	fieldCaption = (String)captionarray.get(i);
	            accessKey = getAccessKey(fieldCaption);
	            if(accessKey.equals("") == false)
	            {
	            	str = str + " accesskey = \"" + accessKey + "\"";
	            }
	        	str = str + ">" + checkTagU(fieldCaption);
	        }
	    }
	    else
	    {
        	fieldCaption = (String)captionarray.get(i);
            accessKey = getAccessKey(fieldCaption);
            if(accessKey.equals("") == false)
            {
            	str = str + " accesskey = \"" + accessKey + "\"";
            }
        	str = str + ">" + checkTagU(fieldCaption);
	    }

        out.println(str);

        str = "</SPAN>";
        out.println(str);

    }

    str = "</FIELDSET>";

    out.println(str);

}

public void addElement(String pCaption, String pValue, String pLocale)
    throws Exception
{
    captionarray.addElement((String)pCaption);
    valuearray.addElement((String)pValue);
    localeidarray.addElement((String)pLocale);
}

public String getAccessKey(String pCaption)
    throws Exception
{
    int pos = pCaption.indexOf("&");

    if (pos > -1)
    {
    	if(pCaption.length() >= pos + 2)
    	{
    		return pCaption.substring(pos + 1,pos + 2);
    	}
    }

    return "";
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