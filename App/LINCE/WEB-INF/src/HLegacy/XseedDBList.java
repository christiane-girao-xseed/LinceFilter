package HLegacy;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.math.*;

public class XseedDBList
{   

	private BigDecimal bigZeros = new BigDecimal(0);
	private XseedFunction xsd = new XseedFunction();

    public PrintWriter out;            
    public Connection sqlconnection;
    
    /* --> Arrays que guardam valores items e valores do DbList */
    public Vector<String> submitColumnArray = new Vector<String>();
    public Vector<String> listColumnArray = new Vector<String>();   

    public String name;
    //Appearance
    public String boundcolumn="";
    public String boundcolumntype ="";
    public String boundcolumnname="";
    public String boundcolumnmask="";
    //ClearWhen_ReadPPT
    public boolean createblank = false;
    public String datadisplay="";
    public String datasource="";
    public int decimals = 0;
    //DecimalKeyed_ReadPPT
    public String description="";
    public String edit= "A";
    public boolean enabled=true;
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
    //LeftFill_ReadPPT
    public int length=1;
    //LinkIfPresent_ReadPPT
    public String listfield="";
    public String listfieldtype="";
    public String listfieldname="";
    public String listfieldmask="";
    //LockSign_ReadPPT
    //NoAutomaticEvents_ReadPPT
    //NoLookUp_ReadPPT
    //NotEntered_ReadPPT
    //Ordinate_ReadPPT
    //Required_ReadPPT
    //SameAs_ReadPPT
    //ShowLeadingZeros_ReadPPT
    //ShowSeparator_ReadPPT
    //' Sorted_ReadPPT
    //TabPage_ReadPPT
    public int tabindex=-9999;
    public boolean tabstop= true;
    public String tooltiptext="";
    public double top=0;
    public String usage="DEF";
    public boolean visible=true;
    public double width=120.0;
    //ZeroSuppress_ReadPPT        
    
    public String event[];
    public String method[];   


public XseedDBList()
{
    event = new String[20];
    method = new String[20];
}


public void print ()
	throws Exception
{
   openTag();
   printProperties();
   printStyles();
   printEvents();
   closeTag();
//   printScript();
	
}

public void openTag ()
   throws Exception
{
        String wBuffer= "<select ";

        wBuffer = wBuffer + " name=\"" + name + "\"";        
        wBuffer =  wBuffer + " id=\"id" + name + "\""; 
        
        wBuffer = wBuffer + " size=\"" + (int)(height / 285) + "\"" ;      
        out.print(wBuffer);
}


public void closeTag ()
   throws Exception
{
        String wBuffer = ">";
        out.println(wBuffer);
        Refresh();        //Ler tabela e carrega o list com o submitColumn e listColumn

        for (int i=0; i < listColumnArray.size(); i++)
        {
           if (listColumnArray.get(i) == null)
           {
              break;
           }

          /*" if(((Object)submitColumnArray.get(i)).equals(value) == true)
           {
                   wBuffer =  "<option selected=\"true\" value=\"" + ((XseedObject)submitColumnArray.get(i)).get() + "\">";
           }
           else*/
           {
                   wBuffer =  "<option value=\"" + ((Object)submitColumnArray.get(i)) + "\">";
           }

           wBuffer = wBuffer + ((Object)listColumnArray.get(i)) + "</option>";

           out.println(wBuffer);
        }

        wBuffer = "</select>";

        out.println(wBuffer);

}

private void printProperties ()
   throws Exception
{
   String wBuffer= "";   
   /*if ( accessKey               != null) { wBuffer = wBuffer + " accesskey=\"" + accessKey + "\""; }
   if ( align                   != null) { wBuffer = wBuffer + " align=\"" + align + "\""; }
   if ( atomicselection         != null) { wBuffer = wBuffer + " atomicselection=\"" + atomicselection + "\""; }
   if ( htmlclass               != null) { wBuffer = wBuffer + " class=\"" + htmlclass + "\""; }
   if ( dataFld                 != null) { wBuffer = wBuffer + " datafld=\"" + dataFld + "\""; }
   if ( dir                     != null) { wBuffer = wBuffer + " dir=\"" + dir + "\""; }
   */
   if (enabled == false)
   {
       wBuffer = wBuffer + " disabled";       
   }
   
   /* ( hideFocus               != null) { wBuffer = wBuffer + " hidefocus=\"" + hideFocus + "\""; }
   if ( lang                    != null) { wBuffer = wBuffer + " lang=\"" + lang + "\""; }
   if ( language                != null) { wBuffer = wBuffer + " language=\"" + language + "\""; }
   
   if (multiple != null && multiple.equals("True"))
   {
      wBuffer = wBuffer + " multiple";
   }
   
   //if ( size                    != null) { wBuffer = wBuffer + " size=\"" + size + "\""; }
   */
//   if ( tabindex                != null) { wBuffer = wBuffer + " tabindex=\"" + tabIndex + "\""; }
   /*
   if ( unselectable            != null) { wBuffer = wBuffer + " unselectable=\"" + unselectable + "\""; }
   */
   if (wBuffer.equals("") == false)
   {
      out.print(wBuffer);
   }
}

private void printStyles ()
   throws Exception
{
        String wBuffer= "position:absolute;";
        

       /* if ( accelerator           != null) { wBuffer = wBuffer + "accelerator:" + accelerator + ";"; }
        if ( backgroundcolor       != null) { wBuffer = wBuffer + "background-color:" + backgroundColor + ";"; }
        if ( behavior              != null) { wBuffer = wBuffer + "behavior:" + behavior + ";"; }
        if ( bottom                != null) { wBuffer = wBuffer + "bottom:" + bottom + ";"; }
        if ( clear                 != null) { wBuffer = wBuffer + "clear:" + clear + ";"; }
        if ( clip                  != null) { wBuffer = wBuffer + "clip:" + clip + ";"; }
        */
        if ( forecolor             != null) { wBuffer = wBuffer + "color:" + forecolor + ";"; }
        /*if ( direction             != null) { wBuffer = wBuffer + "direction:" + direction + ";"; }
        if ( display               != null) { wBuffer = wBuffer + "display:" + display + ";"; }
        */
        if ( font                  != null) { wBuffer = wBuffer + "font:" + font + ";"; }
        //if ( fontfamily            != null) { wBuffer = wBuffer + "font-family:" + fontfamily + ";"; }
        if ( fontsize              > 0) { wBuffer = wBuffer + "font-size:" + fontsize + "pt;"; }
        /*
        if ( fontstyle             != null) { wBuffer = wBuffer + "font-style:" + fontStyle + ";"; }
        if ( fontWeight            != null) { wBuffer = wBuffer + "font-weight:" + fontWeight + ";"; }
        */
        if ( height                >  0   ) { wBuffer = wBuffer + "height:" + xsd.cStr(xsd.twipsperPixel(height)) + "px;"; }
        /*
        if ( layoutFlow            != null) { wBuffer = wBuffer + "layout-flow:" + layoutFlow + ";"; }
        if ( layoutGrid            != null) { wBuffer = wBuffer + "layout-grid:" + layoutGrid + ";"; }
        if ( layoutGridMode        != null) { wBuffer = wBuffer + "layout-grid-mode:" + layoutGridMode + ";"; }
        */
        if ( left                  >  0   ) { wBuffer = wBuffer + "left:" + xsd.cStr(xsd.twipsperPixel(left)) + "px;"; }
        /*
        if ( letterSpacing         != null) { wBuffer = wBuffer + "letter-spacing:" + letterSpacing + ";"; }
        if ( lineHeight            != null) { wBuffer = wBuffer + "line-height:" + lineHeight + ";"; }
        if ( padding               != null) { wBuffer = wBuffer + "padding:" + padding + ";"; }
        if ( paddingBottom         != null) { wBuffer = wBuffer + "padding-bottom:" + paddingBottom + ";"; }
        if ( paddingLeft           != null) { wBuffer = wBuffer + "padding-left:" + paddingLeft + ";"; }
        if ( paddingRight          != null) { wBuffer = wBuffer + "padding-right:" + paddingRight + " ;"; }
        if ( paddingTop            != null) { wBuffer = wBuffer + "padding-top:" + paddingTop + ";"; }
        if ( position              != null) { wBuffer = wBuffer + "position:" + position + ";"; }
        if ( right                 >  0   ) { wBuffer = wBuffer + "right:" + right + unit +";"; }
        if ( textAutospace         != null) { wBuffer = wBuffer + "text-auto-space:" + textAutospace + ";"; }
        if ( textDecoration        != null) { wBuffer = wBuffer + "text-decoration:" + textDecoration + ";"; }
        if ( textTransform         != null) { wBuffer = wBuffer + "text-transform:" + textTransform + ";"; }
        if ( textUnderlinePosition != null) { wBuffer = wBuffer + "text-underline-position:" + textUnderlinePosition + ";"; }
        */
        if ( top                   >  0   ) { wBuffer = wBuffer + "top:" + xsd.cStr(xsd.twipsperPixel(top)) + "px;"; }
        /*
        if ( unicodeBidi           != null) { wBuffer = wBuffer + "unicode-bidi:" + unicodeBidi + ";"; }
        if ( visibility            != null) { wBuffer = wBuffer + "visibility:" + visibility + ";"; }
        */
        if ( width                 >= 0   ) { wBuffer = wBuffer + "width:" + xsd.cStr(xsd.twipsperPixel(width)) + "px;"; }
        /*
        if ( wordSpacing           != null) { wBuffer = wBuffer + "word-spacing:" + wordSpacing + ";"; }
        if ( wordWrap              != null) { wBuffer = wBuffer + "word-wrap:" + wordWrap + ";"; }
        if ( zoom                  != null) { wBuffer = wBuffer + "zoom:" + zoom + ";"; x}
*/
        if (wBuffer.equals("") == false)
        {
            out.print(" style = \"");
            wBuffer = wBuffer + "\"";
            out.print(wBuffer);
        }
}

private void printEvents ()
   throws Exception
{
        String wBuffer= "";

/*        if ( onactivate         != null) { wBuffer = wBuffer + " onactivate=\"" + onactivate + "\""; }
        if ( onafterupdate      != null) { wBuffer = wBuffer + " onafterupdate=\"" + onafterupdate + "\""; }
        if ( onbeforeactivate   != null) { wBuffer = wBuffer + " onbeforeactivate=\"" + onbeforeactivate + "\""; }
        if ( onbeforecut        != null) { wBuffer = wBuffer + " onbeforecut=\"" + onbeforecut + "\""; }
        if ( onbeforedeactivate != null) { wBuffer = wBuffer + " onbeforedeactivate=\"" + onbeforedeactivate + "\""; }
        if ( onbeforeeditfocus  != null) { wBuffer = wBuffer + " onbeforeeditfocus=\"" + onbeforeeditfocus + "\""; }
        if ( onbeforepaste      != null) { wBuffer = wBuffer + " onbeforepaste=\"" + onbeforepaste + "\""; }
        if ( onbeforeupdate     != null) { wBuffer = wBuffer + " onbeforeupdate=\"" + onbeforeupdate + "\""; }
        if ( onblur             != null) { wBuffer = wBuffer + " onblur=\"" + onblur + "\""; }
        if ( onchange           != null) { wBuffer = wBuffer + " onchange=\"" + onchange + "\""; }
        if ( onclick            != null) { wBuffer = wBuffer + " onclick=\"" + onclick + "\""; }
        if ( oncontextmenu      != null) { wBuffer = wBuffer + " oncontextmenu=\"" + oncontextmenu + "\""; }
        if ( oncontrolselect    != null) { wBuffer = wBuffer + " oncontrolselect=\"" + oncontrolselect + "\""; }
        if ( oncut              != null) { wBuffer = wBuffer + " oncut=\"" + oncut + "\""; }
        if ( ondblclick         != null) { wBuffer = wBuffer + " ondblclick=\"" + ondblclick + "\""; }
        if ( ondeactivate       != null) { wBuffer = wBuffer + " ondeactivate=\"" + ondeactivate + "\""; }
        if ( ondragenter        != null) { wBuffer = wBuffer + " ondragenter=\"" + ondragenter + "\""; }
        if ( ondragleave        != null) { wBuffer = wBuffer + " ondragleave=\"" + ondragleave + "\""; }
        if ( ondragover         != null) { wBuffer = wBuffer + " ondragover=\"" + ondragover + "\""; }
        if ( ondrop             != null) { wBuffer = wBuffer + " ondrop=\"" + ondrop + "\""; }
        if ( onerrorupdate      != null) { wBuffer = wBuffer + " onerrorupdate=\"" + onerrorupdate + "\""; }
        if ( onfocus            != null) { wBuffer = wBuffer + " onfocus=\"" + onfocus + "\""; }
        if ( onfocusin          != null) { wBuffer = wBuffer + " onfocusin=\"" + onfocusin + "\""; }
        if ( onfocusout         != null) { wBuffer = wBuffer + " onfocusout=\"" + onfocusout + "\""; }
        if ( onhelp             != null) { wBuffer = wBuffer + " onhelp=\"" + onhelp + "\""; }
        if ( onkeydown          != null) { wBuffer = wBuffer + " onkeydown=\"" + onkeydown + "\""; }
        if ( onkeypress         != null) { wBuffer = wBuffer + " onkeypress=\"" + onkeypress + "\""; }
        if ( onkeyup            != null) { wBuffer = wBuffer + " onkeyup=\"" + onkeyup + "\""; }
        if ( onlosecapture      != null) { wBuffer = wBuffer + " onlosecapture=\"" + onlosecapture + "\""; }
        if ( onmousedown        != null) { wBuffer = wBuffer + " onmousedown=\"" + onmousedown + "\""; }
        if ( onmouseenter       != null) { wBuffer = wBuffer + " onmouseenter=\"" + onmouseenter + "\""; }
        if ( onmouseleave       != null) { wBuffer = wBuffer + " onmouseleave=\"" + onmouseleave + "\""; }
        if ( onmousemove        != null) { wBuffer = wBuffer + " onmousemove=\"" + onmousemove + "\""; }
        if ( onmouseout         != null) { wBuffer = wBuffer + " onmouseout=\"" + onmouseout + "\""; }
        if ( onmouseover        != null) { wBuffer = wBuffer + " onmouseover=\"" + onmouseover + "\""; }
        if ( onmouseup          != null) { wBuffer = wBuffer + " onmouseup=\"" + onmouseup + "\""; }
        if ( onmousewheel       != null) { wBuffer = wBuffer + " onmousewheel=\"" + onmousewheel + "\""; }
        if ( onmove             != null) { wBuffer = wBuffer + " onmove=\"" + onmove + "\""; }
        if ( onmoveend          != null) { wBuffer = wBuffer + " onmoveend=\"" + onmoveend + "\""; }
        if ( onmovestart        != null) { wBuffer = wBuffer + " onmovestart=\"" + onmovestart + "\""; }
        if ( onpaste            != null) { wBuffer = wBuffer + " onpaste=\"" + onpaste + "\""; }
        if ( onpropertychange   != null) { wBuffer = wBuffer + " onpropertychange=\"" + onpropertychange + "\""; }
        if ( onreadystatechange != null) { wBuffer = wBuffer + " onreadystatechange=\"" + onreadystatechange + "\""; }
        if ( onresize           != null) { wBuffer = wBuffer + " onresize=\"" + onresize + "\""; }
        if ( onresizeend        != null) { wBuffer = wBuffer + " onresizeend=\"" + onresizeend + "\""; }
        if ( onresizestart      != null) { wBuffer = wBuffer + " onresizestart=\"" + onresizestart + "\""; }
        if ( onselectstart      != null) { wBuffer = wBuffer + " onselectstart=\"" + onselectstart + "\""; }
*/
        if (wBuffer.equals("") == false)
        {
            out.print(wBuffer);
        }
}


public void Refresh ()
   throws Exception
{
    try
    {
        ResultSet wResultSet;
        Statement wStatement;

        String wSubmitColumn   = "";
        String wListColumn     = "";
        boolean wIsInvalid     = false;

        String wDatasource = XseedFunctions.replaceChar(datasource,'-','_');
        String wBoundcolumn = XseedFunctions.replaceChar(boundcolumn,'-','_');
        String wListfield = XseedFunctions.replaceChar(listfield,'-','_');

        // Evitar acessar database se estiverem vazios

        if( boundcolumn.equals("") || 
            listfield.equals("")   || 
            datasource.equals("")       )
        {
            return;
        }

        // Inclui linha em branco nos arrays
        if(createblank == true)
        {
            addElement("","");            
        }

        // Acessa database para incluir novos campos
        if(sqlconnection != null)
        {
      
            wStatement = sqlconnection.createStatement();
        }
        else
        {   return;
            //wStatement = session.DBConnection.connection.createStatement();
        }
        wResultSet = wStatement.executeQuery(wDatasource);

        //Captura type do submitColumn e listColumn
        getDataSourceFields(wResultSet);

        while  (wResultSet.next())
        {
            // Captura valor do submitColumn
            if (boundcolumnname.equals("") == false)
            {
               if (boundcolumntype.equals("java.lang.String") == true)
               {
                  wSubmitColumn = wResultSet.getString(wBoundcolumn);
               }
               else 
               {
                  wSubmitColumn = xsd.format(wResultSet.getBigDecimal(wBoundcolumn),boundcolumnmask);
               }
            }   
            /*else
            {
               wSubmitColumn = "";
               if ( wIsInvalid == false )
               {
                  XseedFunctions.alert("Error on loading " + name + ":", session);
                  wIsInvalid = true;
               }    
               XseedFunctions.alert("    " + submitColumn + " is an invalid submitColumn!", session);
            }*/

            // Captura valor do listColumn
            if (wBoundcolumn != wListfield)
            {
               if (listfield.equals("") == false)
               {
                 if (listfieldtype.equals("java.lang.String") == true)                                       
                 {
                     wListColumn = wResultSet.getString(wListfield);
                 }
                 else 
                 {                      
                     wListColumn = xsd.format(wResultSet.getBigDecimal(wListfield),listfieldmask);
                 }
               }   
               /*else
               {
                   wListColumn = "";
                   if ( wIsInvalid == false )
                   {
                      XseedFunctions.alert("Error on loading " + name + ":", session);
                      wIsInvalid = true;
                   }    
                   XseedFunctions.alert("    " + listColumn + " is an invalid listColumn!", session);
               }*/
             }
            else
            {
                 wListColumn = wSubmitColumn;
            }

            // Inclui nos arrays
            if ( wIsInvalid )
            {
               break;
            }
            else 
            {
               addElement (wListColumn, wSubmitColumn);
            }
               
        }
        wResultSet.close();
        wStatement.close();

        //session.database.commit();

    }
    catch (SQLException e)
    {
        //XseedFunctions.alert("Error on loading " + name + ":", session);
        //XseedFunctions.alert("    " + e.getMessage(), session);
        //XseedFunctions.alert("    " + "when executing sql:" + dataSrc, session);
    }
}



/** addElement - sobrecargas com 1 parametro */
public void addElement(String listItem, String boundItem)
    throws Exception
{
    listColumnArray.addElement(listItem);
    submitColumnArray.addElement(boundItem);
}


public void getDataSourceFields (ResultSet pResultSet)
    throws Exception
{
        ResultSet           wResultSet;
        ResultSetMetaData   wResultSetMetaData;
        int                 wFieldsCount;

        wResultSetMetaData = pResultSet.getMetaData();
        wFieldsCount       = wResultSetMetaData.getColumnCount();

        boundcolumntype = ""; 
        boundcolumnname = "";
        listfieldtype   = "";
        listfieldname   = "";

        for(int i = 1; i <= wFieldsCount; i++)
        {
            if( wResultSetMetaData.getColumnName(i).toUpperCase().equals( boundcolumn.toUpperCase() ) == true )
            {
                boundcolumntype = wResultSetMetaData.getColumnClassName(i);
                boundcolumnname = wResultSetMetaData.getColumnName(i);
                if ((boundcolumntype.equals("java.lang.String") == false) && (boundcolumnmask.equals("") == true))
                {
                    boundcolumnmask ="";
                    for (int j=1;j<=length-1;j++)
                    {
                        boundcolumnmask=boundcolumnmask+"#";
                    }                        
                    boundcolumnmask =boundcolumnmask + "9"; 
                    
                    if (decimals !=0)
                    {                        
                        boundcolumnmask =boundcolumnmask + "."; 
                        for (int j=1;j<=decimals;j++)
                        {
                            boundcolumnmask=boundcolumnmask+"9";
                        }
                    }
                }
                
            }
            if( wResultSetMetaData.getColumnName(i).toUpperCase().equals(listfield.toUpperCase()) == true )
            {
                listfieldtype = wResultSetMetaData.getColumnClassName(i);
                listfieldname = wResultSetMetaData.getColumnName(i);                 
                
                if ((listfieldtype.equals("java.lang.String") == false) && (listfieldmask.equals("") == true))
                {
                    int lstLength =wResultSetMetaData.getPrecision(i);                    
                    int lstDecimals =wResultSetMetaData.getScale(i);                                        
                    listfieldmask ="#################9";
                    if (lstDecimals !=0)
                    {
                        listfieldmask ="";
                        for (int j=1;j<=lstLength-1;j++)
                        {
                            listfieldmask=listfieldmask+"#";
                        }
                        
                        listfieldmask =listfieldmask + "9."; 
                        for (int j=1;j<=lstDecimals;j++)
                        {
                            listfieldmask=listfieldmask+"9";
                        }
                    }
                }
            }
        }
}
  
} 