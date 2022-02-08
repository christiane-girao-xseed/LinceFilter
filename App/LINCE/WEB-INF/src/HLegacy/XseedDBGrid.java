package HLegacy;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.math.*;

public class XseedDBGrid 
{

    private BigDecimal bigZeros = new BigDecimal(0);
    private XseedFunction xsd = new XseedFunction();
    
    private ResultSet         resultSet;
    private ResultSetMetaData resultSetMetaData;


    public PrintWriter out;            
    public Connection sqlconnection;    
    
    public String [] columnLabel;             /* --> Propriedade que guarda os columnLabels do DBGRID */
    public String [] columnName;              /* --> Propriedade que guarda os columnNames do DBGRID */
    public String [] columnType;              /* --> Propriedade que guarda o type dos columnNames do DBGRID */
    
    public int       columnCount;
        
    public String name;
    public String backcolor="";
    public String borderstyle="";
    public String caption="";
    public String datasource="";
    public String description="";
    public boolean enabled = true; 
    public String font = "VERDANA";    
    public boolean fontbold = false;
    public boolean fontitalic = false;
    public int fontsize = 8;
    public double height = 285.0;
    public double left =0.0;
    //tabpage
    public boolean tabstop = true;
    public String tooltiptext = "";
    public double top = 0.0;
    public boolean visible = true;       
    public double width = 120.0 ;
    
        
    public String event[];
    public String method[];


/* Constructores */

public XseedDBGrid()
{
    event = new String[20];
    method = new String[20];
}

/* methods */
public void print ()
   throws Exception
{
   openTag();
   printProperties();
   printStyles();
   printEvents();
   closeTag();
  
}

private void printProperties ()
   throws Exception
{
        String wBuffer= "";

        /* if ( accessKey               != null) { wBuffer = wBuffer + " accesskey=\"" + accessKey + "\""; }
        if ( align                   != null) { wBuffer = wBuffer + " align=\"" + align + "\""; }
        if ( atomicselection         != null) { wBuffer = wBuffer + " atomicselection=\"" + atomicselection + "\""; }
        if ( begin                   != null) { wBuffer = wBuffer + " begin=\"" + begin + "\""; }
        if ( bgColor                 != null) { wBuffer = wBuffer + " bgcolor=\"" + bgColor + "\""; }
        */
        //if ( cellPadding             != null) 
        {
             wBuffer = wBuffer + " cellpadding=\"1\""; 
        }
        //if ( cellSpacing             != null) 
        { 
            wBuffer = wBuffer + " cellspacing=\"1\""; 
        }
        /*
        if ( htmlclass               != null) { wBuffer = wBuffer + " class=\"" + htmlclass + "\""; }
        if ( cols                    != null) { wBuffer = wBuffer + " cols=\"" + cols + "\""; }
        if ( dataPageSize            != null) { wBuffer = wBuffer + " datapagesize=\"" + dataPageSize + "\""; }
        if ( dir                     != null) { wBuffer = wBuffer + " dir=\"" + dir + "\""; }
        if ( end                     != null) { wBuffer = wBuffer + " end=\"" + end + "\""; }
        if ( frame                   != null) { wBuffer = wBuffer + " frame=\"" + frame + "\""; }
        if ( hideFocus               != null) { wBuffer = wBuffer + " hidefocus=\"" + hideFocus + "\""; }
        if ( lang                    != null) { wBuffer = wBuffer + " lang=\"" + lang + "\""; }
        if ( language                != null) { wBuffer = wBuffer + " language=\"" + language + "\""; }
        if ( rules                   != null) { wBuffer = wBuffer + " rules=\"" + rules + "\""; }
        if ( summary                 != null) { wBuffer = wBuffer + " summary=\"" + summary + "\""; }
        if ( syncMaster              != null) { wBuffer = wBuffer + " syncmaster=\"" + syncMaster + "\""; }
        if ( systembitrate           != null) { wBuffer = wBuffer + " systembitrate=\"" + systembitrate + "\""; }
        if ( systemcaptions          != null) { wBuffer = wBuffer + " systemcaptions=\"" + systemcaptions + "\""; }
        if ( systemlanguage          != null) { wBuffer = wBuffer + " systemlanguage=\"" + systemlanguage + "\""; }
        if ( systemoverduborsubtitle != null) { wBuffer = wBuffer + " systemoverduborsubtitle=\"" + systemoverduborsubtitle + "\""; }
        if ( tabIndex                != null) { wBuffer = wBuffer + " tabindex=\"" + tabIndex + "\""; }
        if ( timeContainer           != null) { wBuffer = wBuffer + " timecontainer=\"" + timeContainer + "\""; }
        if ( title                   != null) { wBuffer = wBuffer + " title=\"" + title + "\""; }
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
   String wBuffer= "";
   
   wBuffer = wBuffer + "border-style:window-inset;border-width:1px;";   
   wBuffer = wBuffer + "position:relative;";
   /*if ( accelerator           != null) { wBuffer = wBuffer + "accelerator:" + accelerator + ";"; }
   if ( background            != null) { wBuffer = wBuffer + "background:" + background + ";"; }
   if ( backgroundAttachment  != null) { wBuffer = wBuffer + "background-attachment:" + backgroundAttachment + ";"; }
   
   if ( backcolor       != null) { wBuffer = wBuffer + "background-color:" + backcolor + ";"; }
   
   if (backgroundImage != null)
   {
      if(backgroundImage.indexOf("$(imagesPath)") != -1)
      {
         String wBeforeImagesPath = backgroundImage.substring(0,backgroundImage.indexOf("$(imagesPath)"));
         String wAfterImagesPath = backgroundImage.substring(backgroundImage.indexOf("$(imagesPath)") + 13,backgroundImage.length());
               
         backgroundImage = wBeforeImagesPath + session.imagesPath + wAfterImagesPath;
      }
      
      if(backgroundImage.indexOf("$(systemPath)") != -1)
      {
         String wBeforeSystemPath = backgroundImage.substring(0,backgroundImage.indexOf("$(systemPath)"));
         String wAfterSystemPath = backgroundImage.substring(backgroundImage.indexOf("$(systemPath)") + 13,backgroundImage.length());
               
         backgroundImage = wBeforeSystemPath + session.systemPath + wAfterSystemPath;
      }

      wBuffer = wBuffer + "background-image:url(" + backgroundImage + ");";
   }

   if ( backgroundPosition    != null) { wBuffer = wBuffer + "background-position:" + backgroundPosition + ";"; }
   if ( backgroundPositionX   != null) { wBuffer = wBuffer + "background-position-x:" + backgroundPositionX + ";"; }
   if ( backgroundPositionY   != null) { wBuffer = wBuffer + "background-position-y:" + backgroundPositionY + ";"; }
   if ( backgroundRepeat      != null) { wBuffer = wBuffer + "background-repeat:" + backgroundRepeat + ";"; }
   if ( behavior              != null) { wBuffer = wBuffer + "behavior:" + behavior + ";"; }
   
   if ( borderstyle           != null) { wBuffer = wBuffer + "border:" + borderstyle + ";"; }
   
   if ( borderBottom          != null) { wBuffer = wBuffer + "border-bottom:" + borderBottom + ";"; }
   if ( borderBottomColor     != null) { wBuffer = wBuffer + "border-bottom-color:" + borderBottomColor + ";"; }
   if ( borderBottomStyle     != null) { wBuffer = wBuffer + "border-bottom-style:" + borderBottomStyle + ";"; }
   if ( borderBottomWidth     != null) { wBuffer = wBuffer + "border-bottom-width:" + borderBottomWidth + ";"; }
   if ( borderCollapse        != null) { wBuffer = wBuffer + "border-collapse:" + borderCollapse + ";"; }
   if ( borderColor           != null) { wBuffer = wBuffer + "border-color:" + borderColor + ";"; }
   if ( borderLeft            != null) { wBuffer = wBuffer + "border-left:" + borderLeft + ";"; }
   if ( borderLeftColor       != null) { wBuffer = wBuffer + "border-left-color:" + borderLeftColor + ";"; }
   if ( borderLeftStyle       != null) { wBuffer = wBuffer + "border-left-style:" + borderLeftStyle + ";"; }
   if ( borderLeftWidth       != null) { wBuffer = wBuffer + "border-left-width:" + borderLeftWidth + ";"; }
   if ( borderRight           != null) { wBuffer = wBuffer + "border-right:" + borderRight + ";"; }
   if ( borderRightColor      != null) { wBuffer = wBuffer + "border-right-color:" + borderRightColor + ";"; }
   if ( borderRightStyle      != null) { wBuffer = wBuffer + "border-right-style:" + borderRightStyle + ";"; }
   if ( borderRightWidth      != null) { wBuffer = wBuffer + "border-right-width:" + borderRightWidth + ";"; }
   if ( borderStyle           != null) { wBuffer = wBuffer + "border-style:" + borderStyle + ";"; }
   if ( borderTop             != null) { wBuffer = wBuffer + "border-top:" + borderTop + ";"; }
   if ( borderTopColor        != null) { wBuffer = wBuffer + "border-top-color:" + borderTopColor + ";"; }
   if ( borderTopStyle        != null) { wBuffer = wBuffer + "border-top-style:" + borderTopStyle + ";"; }
   if ( borderTopWidth        != null) { wBuffer = wBuffer + "border-top-width:" + borderTopWidth + ";"; }
   if ( borderWidth           != null) { wBuffer = wBuffer + "border-width:" + borderWidth + ";"; }
   if ( bottom                != null) { wBuffer = wBuffer + "bottom:" + bottom + ";"; }
   if ( clear                 != null) { wBuffer = wBuffer + "clear:" + clear + ";"; }
   if ( clip                  != null) { wBuffer = wBuffer + "clip:" + clip + ";"; }
   if ( color                 != null) { wBuffer = wBuffer + "color:" + color + ";"; }
   if ( cursor                != null) { wBuffer = wBuffer + "cursor:" + cursor + ";"; }
   if ( direction             != null) { wBuffer = wBuffer + "direction:" + direction + ";"; }
   if ( display               != null) { wBuffer = wBuffer + "display:" + display + ";"; }
   if ( filter                != null) { wBuffer = wBuffer + "filter:" + filter + ";"; }
   */
   if ( font                  != null) { wBuffer = wBuffer + "font-family:" + font + ";"; }
   //if ( fontFamily            != null) { wBuffer = wBuffer + "font-family:" + fontFamily + ";"; }
   if ( fontsize              > 0) { wBuffer = wBuffer + "font-size:" + fontsize + "pt;"; }
    /* if ( fontStyle             != null) { wBuffer = wBuffer + "font-style:" + fontStyle + ";"; }
   if ( fontVariant           != null) { wBuffer = wBuffer + "font-variant:" + fontVariant + ";"; }
   if ( fontWeight            != null) { wBuffer = wBuffer + "font-weight:" + fontWeight + ";"; }
    */
   // Height em <table> não deve ser informado para evitar stretch vertical
    if ( height                > 0    ) { wBuffer = wBuffer + "height:" + xsd.cStr(xsd.twipsperPixel(height)-22) +  "px;"; }

   /*if ( htmlfloat             != null) { wBuffer = wBuffer + "float:" + htmlfloat + ";"; }
   if ( layoutGrid            != null) { wBuffer = wBuffer + "layout-grid:" + layoutGrid + ";"; }
   if ( layoutGridChar        != null) { wBuffer = wBuffer + "layout-grid-char:" + layoutGridChar + ";"; }
   if ( layoutGridLine        != null) { wBuffer = wBuffer + "layout-grid-line:" + layoutGridLine + ";"; }
   if ( layoutGridMode        != null) { wBuffer = wBuffer + "layout-grid-mode:" + layoutGridMode + ";"; }
   if ( layoutGridType        != null) { wBuffer = wBuffer + "layout-grid-type:" + layoutGridType + ";"; }
   //if ( left                  > 0    ) { wBuffer = wBuffer + "left:" + left + unit + ";"; }
   if ( letterSpacing         != null) { wBuffer = wBuffer + "letter-spacing:" + letterSpacing + ";"; }
   if ( lineBreak             != null) { wBuffer = wBuffer + "line-break:" + lineBreak + ";"; }
   if ( lineHeight            != null) { wBuffer = wBuffer + "line-height:" + lineHeight + ";"; }
   if ( margin                != null) { wBuffer = wBuffer + "margin:" + margin + ";"; }
   if ( marginBottom          != null) { wBuffer = wBuffer + "margin-bottom:" + marginBottom + ";"; }
   if ( marginLeft            != null) { wBuffer = wBuffer + "margin-left:" + marginLeft + ";"; }
   if ( marginRight           != null) { wBuffer = wBuffer + "margin-right:" + marginRight + ";"; }
   if ( marginTop             != null) { wBuffer = wBuffer + "margin-top:" + marginTop + ";"; }
   if ( padding               != null) { wBuffer = wBuffer + "padding:" + padding + ";"; }
   if ( paddingBottom         != null) { wBuffer = wBuffer + "padding-bottom:" + paddingBottom + ";"; }
   if ( paddingLeft           != null) { wBuffer = wBuffer + "padding-left:" + paddingLeft + ";"; }
   if ( paddingRight          != null) { wBuffer = wBuffer + "padding-right:" + paddingRight + ";"; }
   if ( paddingTop            != null) { wBuffer = wBuffer + "padding-top:" + paddingTop + ";"; }
   if ( pageBreakAfter        != null) { wBuffer = wBuffer + "page-break-after:" + pageBreakAfter + ";"; }
   if ( pageBreakBefore       != null) { wBuffer = wBuffer + "page-break-before:" + pageBreakBefore + ";"; }
   if ( position              != null) { wBuffer = wBuffer + "position:" + position + ";"; }
   if ( right                 > 0    ) { wBuffer = wBuffer + "right:" + right + unit + ";"; }
   if ( tableLayout           != null) { wBuffer = wBuffer + "table-layout:" + tableLayout + ";"; }
   if ( textAlign             != null) { wBuffer = wBuffer + "text-align:" + textAlign + ";"; }
   if ( textAutospace         != null) { wBuffer = wBuffer + "text-auto-space:" + textAutospace + ";"; }
   if ( textDecoration        != null) { wBuffer = wBuffer + "text-decoration:" + textDecoration + ";"; }
   if ( textIndent            != null) { wBuffer = wBuffer + "text-indent:" + textIndent + ";"; }
   if ( textJustify           != null) { wBuffer = wBuffer + "text-justify:" + textJustify + ";"; }
   if ( textTransform         != null) { wBuffer = wBuffer + "text-transform:" + textTransform + ";"; }
   if ( textUnderlinePosition != null) { wBuffer = wBuffer + "text-underline-position:" + textUnderlinePosition + ";"; }
   */
   //if ( top                   > 0    ) { wBuffer = wBuffer + "top:" + xsd.cStr(xsd.twipsperPixel(top)) + "px;"; }
   /*
   if ( unicodeBidi           != null) { wBuffer = wBuffer + "unicode-bidi:" + unicodeBidi + ";"; }
   if ( visibility            != null) { wBuffer = wBuffer + "visibility:" + visibility + ";"; }

   // Width de <table> deve permanecer para garantir strech horizontal se cellwidth = 0
   if ( cellWidth == 0 )
   */
   {
    
      if ( xsd.twipsperPixel(width) > 20 ) 
      {  
         wBuffer = wBuffer + "width:" + xsd.cStr(xsd.twipsperPixel(width)-20) + "px;"; 
      }
      else
      {  
         wBuffer = wBuffer + "width:" + xsd.cStr(xsd.twipsperPixel(width)) + "px;"; 
      }
   }
  /*
   if ( wordBreak             != null) { wBuffer = wBuffer + "word-break:" + wordBreak + ";"; }
   if ( wordSpacing           != null) { wBuffer = wBuffer + "word-spacing:" + wordSpacing + ";"; }
   if ( zIndex                != null) { wBuffer = wBuffer + "z-index:" + zIndex + ";"; }
   if ( zoom                  != null) { wBuffer = wBuffer + "zoom:" + zoom + ";"; }
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
      
}

public void closeTag ()
   throws Exception
{
        String wBuffer = ">";
        out.println(wBuffer);

        Refresh();                    //Ler tabela e carrega os panels

        wBuffer = "</table>";
        out.println(wBuffer);

        wBuffer = "</span>";
        out.println(wBuffer);
}

public void openTag ()
    throws Exception
{
     String wBuffer;
     
    /* Criação do controle auxiliar para armazenar o value */
   /* if (get() != null)
    {
       wBuffer = "<input type=\"text\" id=\"_" + name + "\"" + " name=\"_" + name + "\"" + " value=\"" + outputRepair ( get() ) + "\" style=\"visibility:hidden;\">";
    }
    else
    {
       wBuffer = "<input type=\"text\" id=\"_" + name + "\"" + " name=\"_" + name + "\"" + " style=\"visibility:hidden;\">";
    }
    out.println(wBuffer);
*/
    /* Criação do controle propriamente dito */

    wBuffer = "<span style = \"overflow:auto;border-style:window-inset;border-width:1px;position:absolute;";

    wBuffer =  wBuffer + "height:" + xsd.cStr(xsd.twipsperPixel(height))  + "px;";
    wBuffer =  wBuffer + "width:" + xsd.cStr(xsd.twipsperPixel(width))  + "px;";
    wBuffer =  wBuffer + "top:" + xsd.cStr(xsd.twipsperPixel(top))  + "px;";
    wBuffer =  wBuffer + "left:" + xsd.cStr(xsd.twipsperPixel(left))  + "px;";

    if ( backcolor != null) 
    { 
       wBuffer = wBuffer + "background-color:" + backcolor + ";"; 
    }

    wBuffer =  wBuffer + "\">";

    out.println(wBuffer);

    wBuffer = "<table";

    wBuffer =  wBuffer + " id=\"id" + name + "\"";    

    out.print(wBuffer);

}

public void Refresh ()
   throws Exception
{
    try
    {        
        
        Statement wStatement;
        String wBuffer = "";
        String wListValue = "";
        String wSubmitValue = "";

        int i = 0;
        
        String wDatasource = XseedFunctions.replaceChar(datasource,'-','_');
        
        // Se dataSrc estiver vazio imprime apenas o Grid Caption 
        if( datasource == null || datasource.equals("") )
        {           
            printGridCaption();
            return;
        }
        
        /** Acessando base de dados */
        
        if(sqlconnection != null)
        {
            wStatement = sqlconnection.createStatement();
        }
        else
        {   return;
            //wStatement = session.DBConnection.connection.createStatement();
        }
        
        resultSet = wStatement.executeQuery(wDatasource);

        //Ler campos do select
        getDataSourceFields(resultSet);

        //Imprime Grid Caption
        printGridCaption();

        //Imprime columnLabels
        printColumnLabel();

        while (resultSet.next())
        {
            i++;

            /** Quebra somente se maxRows for maior que zeros */
          //  if ( (maxRows > 0) && (i > maxRows) )
          //  {
          //      break;
          //  }

            wBuffer = "<tr>";

            out.println(wBuffer);

            for(int j = 0; j < columnCount; j++)
            {
                if(columnLabel[j] == null)
                {
                    break;
                }

                // Recupera registro da tabela /
                wListValue = resultSet.getString(columnName[j]);                
              
                
            
                wBuffer = "<td";
                wBuffer = wBuffer + " style = \"";                                                         
                wBuffer = wBuffer + "background-color:" + backcolor + ";";
                wBuffer = wBuffer + "border-style:window-inset;"; 
                wBuffer = wBuffer + "border-width:1px;\"";                                                       
                wBuffer = wBuffer + "\"";
                wBuffer = wBuffer + ">";
 
                out.print(wBuffer);
    
                out.print(wListValue);
                wBuffer = "</td>";
 
                out.println(wBuffer);
              
            }

            wBuffer = "</tr>";

            out.println(wBuffer);
            
        }
        resultSet.close();
        wStatement.close();

    }
    catch (SQLException e)
    {
      /*  XseedFunctions.alert("Error on loading " + name + ":", session);
        XseedFunctions.alert("    " + e.getMessage(), session);
        XseedFunctions.alert("    " + "when executing sql:" + dataSrc, session);*/
    }
}

public void getDataSourceFields (ResultSet pResultSet)
    throws Exception
{
        int wFieldsCount;

        resultSetMetaData = pResultSet.getMetaData();
        wFieldsCount      = resultSetMetaData.getColumnCount();

        columnLabel       = new String[wFieldsCount];
        columnName        = new String[wFieldsCount];
        columnType        = new String[wFieldsCount];

        columnCount           = wFieldsCount;

        for(int i = 1; i <= wFieldsCount; i++)
        {
            columnLabel[i - 1] = resultSetMetaData.getColumnLabel(i);
            columnName[i - 1] = resultSetMetaData.getColumnName(i);
            columnType[i - 1] = resultSetMetaData.getColumnClassName(i);
        }
}

public void printGridCaption()
    throws Exception
{
    String wBuffer;

    if ( caption != null )
    {
       if (caption.equals("") == false)
       {
          if ( columnCount == 0 )
          {
             wBuffer = "<th";
          }
          else
          {
             wBuffer = "<th colspan = \"" + columnCount + "\"";
          }	

          wBuffer = wBuffer + " style = \"font-weight:bold;";

          // if (cellHeight > 0 )
          // {
          //   wBuffer = wBuffer + "height:" + cellHeight + unit + ";";
          // }	  

          wBuffer = wBuffer + "background-color:" + backcolor + ";";
          wBuffer = wBuffer + "border-style:window-inset;"; 
          wBuffer = wBuffer + "border-width:1px;\""; 
            
          wBuffer = wBuffer + ">" + caption + "</th>";
          
          out.println(wBuffer);
       }
    }
}

public void printColumnLabel()
    throws Exception
{
        String wBuffer = "<tr>";

        out.println(wBuffer);

        for(int j = 0; j < columnCount; j++)
        {
            if(columnLabel[j] == null)
            {
                break;
            }

            /** Inicia impressao do columnLabel se a coluna não for escondida */
            wBuffer = "<th";
            wBuffer = wBuffer + " style = \"";

            
            wBuffer = wBuffer + "background-color:" + backcolor + ";";
            wBuffer = wBuffer + "border-style:window-inset;";                         
            wBuffer = wBuffer + "border-width:1px;\">";          
            out.print(wBuffer);
            wBuffer = columnLabel[j];
            out.print(wBuffer);
            wBuffer = "</th>";
            out.println(wBuffer);            
        }

        wBuffer = "</tr>";
        out.println(wBuffer);
}


} // Final da Classe
