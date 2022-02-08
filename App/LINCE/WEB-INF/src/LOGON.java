// Generated by Xseed Version 9.7.4 ( Date: 04/02/2022 12:01:3838 hs  User: DEFAULT )
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.zip.*;
import java.math.*;
import java.lang.reflect.*;
import HLegacy.*;


import javax.servlet.annotation.*;
@WebServlet(value="/servlet/LOGON")
public class LOGON extends XseedIspec
{
Integer cycleCounter;
String runQueueNumber = "";
PrintWriter outHTML;


XseedCSS cssLOGON = new XseedCSS();
XseedTextbox USR = new XseedTextbox();
final String LAN_INVALIDMAINT           = "Invalid maintenance field.";
final String LAN_INVALIDINCLUSION       = "Invalid Add. Item already exists.";
final String LAN_SUCCESSINCLUSION       = "Successful Add.";
final String LAN_INVALIDMODIFY          = "Invalid Change. Item does not exist.";
final String LAN_SUCCESSMODIFY          = "Successful Change.";
final String LAN_INVALIDDELETE          = "Invalid Delete. Item does not exist.";
final String LAN_SUCCESSDELETE          = "Successful Delete.";
final String LAN_INVALIDPURGE           = "Invalid Purge. Item does not exist.";
final String LAN_SUCCESSPURGE           = "Successful Purge.";
final String LAN_INVALIDCONSULT         = "Inquiry not executed. Item does not exist.";
final String LAN_SUCCESSCONSULT         = "Successful Inquiry.";
final String LAN_DELETEDITEM            = "Inquiry executed. Item logically deleted.";
final String LAN_LOGICALLYDELETED       = "Inquiry not executed. Item logically deleted.";
final String LAN_FILLORDINATE           = "Ordinate must be entered.";
final String LAN_INQATENDOFLG           = "INQ not allowed at the end of LG.";
final String LAN_ITEMNOTREC             = "Item not registered on Ispec";
final String LAN_ITEMDELETED            = "Item logically deleted on Ispec";
final String LAN_ITEMMUSTBEFILLED       = "Item must be filled";
final String LAN_INVALIDDATE            = "Invalid Date";
final String LAN_ISPECNOTDEFINED        = "Ispec not defined";
final String LAN_REPORTNOTDEFINED       = "Report not defined";
final String LAN_INVALIDVALUE           = "Invalid numeric value on field ";
final String LAN_INVALIDNUMERIC         = "Invalid char in numeric fields.";
final String MSG_SUCCESSFULL_ENTRY      = "Successfull entry";
final String MSG_ORDINATE_REQUIRED      = "Ordinated required";
final String MSG_INVALIDKEYF11          = "The key F11 is disabled.";
final String MSG_INVALIDKEYALTLEFT      = "The key ALT <- is disabled.";
final String MSG_INVALIDKEYALTRIGHT     = "The key ALT -> is disabled.";
final String MSG_INVALIDKEYCTRLN        = "The key CTRL-N is disabled.";
final String MSG_INVALIDKEYCTRLU        = "The key CTRL-U is disabled.";
final String MSG_MANYDECIMALS           = " too many decimals on field ";
final String MSG_MANYNUMBER             = " numeric value too large on field ";
final String MSG_XMITERROR              = "Page already submitted. Wait ... ";
final String MSG_GOBACK                 = "Go Back";
final String MSG_INVALIDDCT             = "Invalid Value";
final String MSG_WAITXMIT               = "Transmitting the screen. Wait ... ";
final String MSG_REQUIREDDECIMALCHAR    = " decimal separator is required on field ";
String SCREEN_USR = "";
String USER = "";
String PAGENAME = "";
String WORK = "";
String GSDWORK = "";

Map<String,String> sessionValues = new HashMap<String, String>();

public void doGet(HttpServletRequest  request,
                  HttpServletResponse response)
       throws ServletException, IOException
{
    doPost(request,response);
}

public void doPost(HttpServletRequest request,
                   HttpServletResponse response)
       throws ServletException, IOException
{
LOGON ispecLOGON = new LOGON();
try
{
    response.setContentType("text/html");
    setNoCache(request,response);
    ispecLOGON.session = request.getSession(true);
    ispecLOGON.request = request;
    ispecLOGON.response = response;
    ispecLOGON.ISPEC = "LOGON";
    ispecLOGON.outHTML = response.getWriter();
    if (ispecLOGON.checkOnXmit(ispecLOGON.session) == true)
    {
        ispecLOGON.BuildErrorXmit(response.getWriter());
        return;
    }
    ispecLOGON.setOnXmit(ispecLOGON.session);
    if (ispecLOGON.checkTimeout(ispecLOGON.session) == true)
    {
       XseedINI xseedIni = new XseedINI();
       ispecLOGON.reloadRts(ispecLOGON.session, xseedIni );
    }
    ispecLOGON.ReadClipboard(ispecLOGON.session);
    //ispecLOGON.OpenFiles();
    ispecLOGON. initialProperties();
    if (request.getParameter("onSession")==null)
    {
        ispecLOGON.IspecLoad();
    }
    else
    {
        ispecLOGON.FormToScreen(request);
         ispecLOGON.IspecCycle();
    }
    ispecLOGON.WriteClipboard(ispecLOGON.session);
   // ispecLOGON.CommitTransaction();
    ispecLOGON.ispecRecall(request,response,ispecLOGON.session);
  //  ispecLOGON.CommitTransaction();
 //   ispecLOGON.CloseFiles();
    ispecLOGON.resetOnXmit(ispecLOGON.session);

}
catch (Exception e)
{
    if (ispecLOGON.GLB.ABORT == true)
    {
        try
        {
        ispecLOGON.FormToScreen(request);
      //  ispecLOGON.CommitTransaction();
        ispecLOGON.ispecRecall(request,response,ispecLOGON.session);
       // ispecLOGON.CommitTransaction();
       // ispecLOGON.CloseFiles();
        ispecLOGON.resetOnXmit(ispecLOGON.session);
        }
        catch (Exception eAbort)
        {
           if (ispecLOGON.routineErrorMsg.equals("") == true)
           {
              ispecLOGON.routineErrorMsg = eAbort.getMessage();
              ispecLOGON.routineErrorName = "doPost";
           }

           ispecLOGON.AbortTransaction(ispecLOGON.routineErrorMsg);
           try
           {
              ispecLOGON.CloseFiles();
           }
           catch (Exception exc) { }
           ispecLOGON.BuildErrorPage(response.getWriter(), ispecLOGON.routineErrorName, ispecLOGON.routineErrorMsg);
           ispecLOGON.resetOnXmit(ispecLOGON.session);

        }
    }
    else
    {
       if (ispecLOGON.routineErrorMsg.equals("") == true)
       {
          ispecLOGON.routineErrorMsg = e.getMessage();
          ispecLOGON.routineErrorName = "doPost";
       }

       ispecLOGON.AbortTransaction(ispecLOGON.routineErrorMsg);
       try
       {
          ispecLOGON.CloseFiles();
       }
       catch (Exception exc) { }
       ispecLOGON.BuildErrorPage(response.getWriter(), ispecLOGON.routineErrorName, ispecLOGON.routineErrorMsg);
       ispecLOGON.resetOnXmit(ispecLOGON.session);
    }
}
}

private void setNoCache(HttpServletRequest request,HttpServletResponse response)
{
    if (request.getProtocol().compareTo("HTTP/1.0") == 0)
    {
        response.setHeader("Pragma", "no-cache");
    }
    else if (request.getProtocol().compareTo("HTTP/1.1") == 0)
    {
        response.setHeader("Cache-Control", "no-cache");
    }
}

public void BuildPage (PrintWriter out, HttpServletRequest request)
  throws Exception
{
   try
   {
      AuditSCR("OUT");
      int i = 0;
      int index = 0;


      out.println("<HTML>");
      out.println("<HEAD>");
      out.println("<style type=\"Text/css\">");
      out.println("SPAN {");
      out.println("   font-family: VERDANA;");
      out.println("   font-size: 8pt;");
      out.println("   background-color: #FFFFFF;");
      out.println("   color: #000000;");
      out.println("   text-align:left");
      out.println("}");
      out.println("BODY {");
      out.println("   background-color: #FFFFFF;");
      out.println("}");
      out.println("INPUT {");
      out.println("   font-family: VERDANA;");
      out.println("   font-size: 8pt;");
      out.println("   text-align:left");
      out.println("}");
      out.println("BUTTON {");
      out.println("   font-family: VERDANA;");
      out.println("   font-size: 8pt;");
      out.println("   padding:0px;");
      out.println("   margin:0px;");
      out.println("}");
      out.println("SELECT {");
      out.println("   font-family: VERDANA;");
      out.println("   font-size: 8pt;");
      out.println("}");
      out.println("TABLE {");
      out.println("   font-family: VERDANA;");
      out.println("   font-size: 8pt;");
      out.println("   background-color: #FFFFFF;");
      out.println("}");
      out.println("FIELDSET {");
      out.println("   font-family: VERDANA;");
      out.println("   font-size: 8pt;");
      out.println("   background-color: #FFFFFF;");
      out.println("   color: #000000;");
      out.println("   padding:0px;");
      out.println("   margin:0px;");
      out.println("}");
      out.println("LEGEND {");
      out.println("   font-family: VERDANA;");
      out.println("   font-size: 8pt;");
      out.println("   background-color: #FFFFFF;");
      out.println("   color: #000000;");
      out.println("}");
      out.println("#idWorkArea{position:absolute;left:0px;top:20px;width:2050px;height:4600px}");
      out.println("#idStatusBar{position:absolute;left:0px;top:0px;width:2050px;height:20px}");
      out.println("#idMessage {position:absolute;color:FF0000;font-weight:bold;top:3px;width:774px;height:17px;}");
      out.println("#idUSR" + printStyle(USR,cssLOGON));
      out.println("</style>");
      out.println("<TITLE>TESTE</TITLE>");
      out.println("<script language = \"JavaScript\">");
      out.println("var flagEnter = 999");
      out.println("var flagTxt = 0");
      out.println("function jKeyPress(pEvent)");
      out.println("{");
      out.println("    var mNavigator = window.navigator.appName;");
      out.println("    var isIE = mNavigator.match(/Microsoft/gi);");
      out.println("    var isFF = mNavigator.match(/Netscape/gi);");
      out.println("    var evt;");
      out.println("    if (isIE) evt = pEvent.keyCode;");
      out.println("    else if (isFF) evt = pEvent.charCode;");
      if (GLB.DecimalChar==',')
          out.println("    return ( (evt<20) || (evt >47 && evt<58) || (evt==44) );");
      else
          out.println("    return ( (evt<20) || (evt >47 && evt<58) || (evt==46) );");
      out.println("}");
      out.println("function jFocusA(pObj)");
      out.println("{ pObj.select();");
      out.println("}");
      out.println("function jFocusN(pObj)");
      out.println("{ if (pObj.value == 0)");
      out.println("    pObj.value =\"\";");
      out.println("  pObj.select();");
      out.println("}");
      out.println("function jBlurN(pObj)");
      out.println("{ if (pObj.value == \"\")");
      out.println("    pObj.value = 0;");
      out.println("}");
      out.println("function jValN(pObj,pDad,pLength)");
      out.println("{ var i = 0;");
      out.println("  var number=\"\";");
      out.println("  newWord = pObj.value;");
      out.println("  for (i = 0; i < newWord.length; i++)");
      out.println("  { if (!(((newWord.charAt(i) >= \"0\") && (newWord.charAt(i) <= \"9\")) ||(newWord.charAt(i) == \"" + GLB.SeparatorChar + "\")))");
      out.println("    { alert(pObj.value + \" " + LAN_INVALIDVALUE + " \" + pDad + \".\");");
      out.println("      pObj.focus();");
      out.println("      return(false);");
      out.println("    }");
      out.println("  }");
      out.println("  for(i =0; i < newWord.length; i++)");
      out.println("  { if (newWord.charAt(i) != \"" + GLB.SeparatorChar + "\")");
      out.println("    {  number = number + newWord.charAt(i);");
      out.println("    }");
      out.println("  }");
      out.println("  if (number.length > pLength)");
      out.println("  {   alert(pObj.value + \"" + MSG_MANYNUMBER + "\" + pDad + \".\");");
      out.println("      pObj.focus();");
      out.println("      return(false);");
      out.println("  }");
      out.println("  return(true);");
      out.println("}");
      out.println("function jValDecN(pObj,pDad,pLength,pDecimals)");
      out.println("{ var i = 0;");
      out.println("  var number=\"\";");
      out.println("  newWord = pObj.value;");
      out.println("  for (i = 0; i < newWord.length; i++)");
      out.println("  { if (!(((newWord.charAt(i) >= \"0\") && (newWord.charAt(i) <= \"9\")) || (newWord.charAt(i) == \"" + GLB.DecimalChar + "\") || (newWord.charAt(i) == \"" + GLB.SeparatorChar + "\")))");
      out.println("    { alert(pObj.value + \" " + LAN_INVALIDVALUE + " \" + pDad + \".\");");
      out.println("      pObj.focus();");
      out.println("      return(false);");
      out.println("    }");
      out.println("  }");
      out.println("  for(i =0; i < newWord.length; i++)");
      out.println("  { if (newWord.charAt(i) != \"" + GLB.SeparatorChar + "\")");
      out.println("    {  number = number + newWord.charAt(i);");
      out.println("    }");
      out.println("  }");
      out.println("  if (number.indexOf(\"" + GLB.DecimalChar + "\") != -1) ");
      out.println("  {   for (i = 0; i < number.length; i++)");
      out.println("      {  if (number.charAt(i) == \"" +  GLB.DecimalChar + "\")");
      out.println("         {    if (i  >  pLength - pDecimals)");
      out.println("              {   alert(pObj.value + \"" + MSG_MANYNUMBER + "\" + pDad + \".\");");
      out.println("                  pObj.focus();");
      out.println("                  return(false);");
      out.println("              }");
      out.println("              if (number.length - i - 1 > pDecimals)");
      out.println("              {   alert(pObj.value + \"" + MSG_MANYDECIMALS + "\" + pDad + \".\");");
      out.println("                  pObj.focus();");
      out.println("                  return(false);");
      out.println("              }");
      out.println("         }");
      out.println("     }");
      out.println("  }");
      out.println("  else");
      out.println("  {");
      out.println("      if (number.length > pLength - pDecimals)");
      out.println("      {   alert(pObj.value + \"" + MSG_MANYNUMBER + "\" + pDad + \".\");");
      out.println("          pObj.focus();");
      out.println("          return(false);");
      out.println("      }");
      out.println("  }");
      out.println("  return(true);");
      out.println("}");
      out.println("function jValPag()");
      out.println("{");
      out.println("  return(true);");
      out.println("}");
      out.println("function jCursor ()");
      out.println("{");
      if (rTrim(GLB.CURSOR).equals(""))
      {
         GLB.CURSOR ="USR";
      }
      try
      {
          Field xsdField =  this.getClass().getDeclaredField(GLB.CURSOR);
          if ( xsdField.getType() == Class.forName("HLegacy.XseedFrame") )
          {
              out.println("  if ( (frmLOGON." + GLB.CURSOR + "[0].type != \"hidden\") && (frmLOGON." + GLB.CURSOR + "[0].style.visibility != \"hidden\"))");
              out.println("    frmLOGON." + GLB.CURSOR + "[0].focus();");
          }
          else
          {
              if ( xsdField.getType() == Class.forName("HLegacy.XseedCombobox") )
              {
                  Field xsdCombo = xsdField.getType().getDeclaredField("editable");
                  if(xsdCombo.get((XseedCombobox)xsdField.get(this)).equals(true))
                  {
                      GLB.CURSOR = GLB.CURSOR + "_txt";
                  }
              }
              out.println("  if ( (frmLOGON." + GLB.CURSOR + ".type != \"hidden\") && (frmLOGON." + GLB.CURSOR + ".style.visibility != \"hidden\"))");
              out.println("    frmLOGON." + GLB.CURSOR + ".focus();");
          }
      }
      catch(Exception e) {}
      out.println("}");
      out.println("function jXmit ()");
      out.println("{ if (jValPag() == true)");
      out.println("  {");
      out.println("    if (frmLOGON.__isProcessing.value == \"0\")");
      out.println("    {");
      out.println("        frmLOGON.__isProcessing.value = \"1\";");
      out.println("        frmLOGON.submit();");
      out.println("    }");
      out.println("    else");
      out.println("    {");
      out.println("       alert(\"" + MSG_XMITERROR.trim() + "\");");
      out.println("    }");
      out.println("  }");
      out.println("}");
      out.println("function jMaint (action)");
      out.println("{ frmLOGON.MAINT.value = action;");
      out.println("}");
      out.println("function jOption(ControlName, CheckedValue)");
      out.println("{ var i;");
      out.println("  for (i=0; i < frmLOGON.length; i++)");
      out.println("  { if (frmLOGON[i].type == \"radio\")");
      out.println("    { if (frmLOGON[i].name != ControlName)");
      out.println("      {");
      out.println("      }");
      out.println("      else");
      out.println("      { frmLOGON[i].value = CheckedValue;");
      out.println("      }");
      out.println("    }");
      out.println("  }");
      out.println("}");
      out.println("function jKey(pEvent)");
      out.println("{");
      out.println("  if (flagEnter == 0)");
      out.println("  {");
      out.println("     flagEnter = 999;");
      out.println("     return false;");
      out.println("  }");
      out.println("  if (pEvent.keyCode == 8)");
      out.println("  {");
      out.println("     if (flagTxt == 999)");
      out.println("     {");
      out.println("        return true;");
      out.println("     }");
      out.println("     window.event.keyCode = 0;");
      out.println("     return false;");
      out.println("  }");
      out.println("  if (pEvent.keyCode == 122)");
      out.println("  {");
      out.println("     alert(\"" + MSG_INVALIDKEYF11 + "\");");
      out.println("     window.event.keyCode = 0;");
      out.println("     return false;");
      out.println("  }");
      out.println("  if (pEvent.ctrlKey && pEvent.keyCode == 85)");
      out.println("  {");
      out.println("     alert(\"" + MSG_INVALIDKEYCTRLU + "\");");
      out.println("     return false;");
      out.println("  }");
      out.println("  if (pEvent.ctrlKey && pEvent.keyCode == 78)");
      out.println("  {");
      out.println("     alert(\"" + MSG_INVALIDKEYCTRLN + "\");");
      out.println("     return false;");
      out.println("  }");
      out.println("  if (pEvent.altKey && pEvent.keyCode == 37)");
      out.println("  {");
      out.println("     alert(\"" + MSG_INVALIDKEYALTLEFT + "\");");
      out.println("     return false;");
      out.println("  }");
      out.println("  if (pEvent.altKey && pEvent.keyCode == 39)");
      out.println("  {");
      out.println("     alert(\"" + MSG_INVALIDKEYALTRIGHT + "\");");
      out.println("     return false;");
      out.println("  }");
      out.println("  if (pEvent.keyCode == 27) jMsg(false);");
      out.println("  if (pEvent.keyCode == 34) jXmit();");
      out.println("  if (pEvent.keyCode == 13) jXmit();");
      out.println("  if (pEvent.keyCode == 35) frmLOGON.USR.focus();");
      out.println("  if (pEvent.keyCode == 36) frmLOGON.USR.focus();");
      out.println("}");
      out.println("function jMsg(pParam)");
      out.println("{ var message = \"\";");
      if ((GLB.MSGINDEX == 0) || (GLB.MSGINDEX == 1) || (GLB.MSGINDEX == -1))
      {
          out.println("  if (pParam == true)");
          out.println("  {");
          out.println("    return;");
          out.println("  }");
      }
      if (GLB.MSGINDEX != 0)
      {   if (GLB.MSGINDEX > 0)
          {  for (i = 1; i <= GLB.MSGINDEX; i++)
             {
                 out.println("  message = message + \""  + replaceSpecialCharacter(GLB.MSGHEADER[i].trim()) + " " + replaceSpecialCharacter(GLB.MSGTRAILER[i].trim()) + "\";");
                 if (i < GLB.MSGINDEX)
                     out.println("  message = message + \"\\n\";");
             }
          }
          else
          {  index = GLB.MSGINDEX;
             index = index * -1;
             for (i = index; i >= 1; i--)
             {
                 out.println("  message = message + \""  + replaceSpecialCharacter(GLB.MSGHEADER[i].trim()) + " " + replaceSpecialCharacter(GLB.MSGTRAILER[i].trim()) + "\";");
                 if (i > 1)
                    out.println("  message = message + \"\\n\";");
             }
          }
          out.println("  alert(message);");
      }
      else
      {
          out.println("  alert(\"No Messages found.\");");
      }
      out.println("}");
      out.println("function rTrim(value)");
      out.println("{ while(''+value.charAt(value.length-1)==' ')");
      out.println("    value=value.substring(0,value.length-1);");
      out.println("  return value;");
      out.println("}");
      out.println("function jForm()");
      out.println("{  var i;");
      if (SCREEN_USR.length() > 10)
         SCREEN_USR = SCREEN_USR.substring(0,10);
      out.println("  frmLOGON.USR.value = \"" + rTrim(replaceSpecialCharacter(SCREEN_USR)) + "\";");
      out.println("}");
      out.println("function jRep()");
      out.println("{");
      if (GLB.ReportFilename.equals("") == false)
      {
          SimpleDateFormat wFormatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
          String wNow = wFormatter.format(new java.util.Date());
          out.println("  var reportWindow = null;");
          out.println("  reportWindow = window.open(\"XseedRoc?name="  + rTrim(replaceSpecialCharacter(GLB.ReportFilename)) + "\",\"Rep" + wNow+ "\", \"toolbar=1, menubar=1,scrollbars=1,location=0,resizable=1\");");
          out.println("  reportWindow.focus();");
      }
      out.println("}");
      out.println("function jTeach()");
      out.println("{");
      if (GLB.TEACH.equals("") == false)
      {
          out.println("  var teachWindow = null;");
          out.println("  teachWindow = window.open(\"XseedDoc\",\"Doc\", \"toolbar=0, menubar=0,scrollbars=1,location=0,resizable=1,top=50,left=150,height=450,width=615\");");
          out.println("  teachWindow.focus();");
      }
      out.println("}");
      out.println("</script>");
      out.println("</HEAD>");
      out.println("<BODY onLoad=\"jForm();jMsg(true);jCursor();jRep();jTeach();\" onkeydown=\"return(jKey(event))\" " + GLB.BODYTAG + ">");
      out.println("<FORM Name = \"frmLOGON\" id = \"frmLOGON\" method = \"post\" >");
      out.println("<SPAN id=\"idWorkArea\">");
      out.println("  " + printTextbox(USR,cssLOGON) + " ");
      out.println("</SPAN>");
      out.println("<SPAN id =\"idStatusBar\">");
      if (GLB.MSGINDEX == 1)
      {    out.println("<SPAN id=\"idMessage\">" + GLB.MSGHEADER[GLB.MSGINDEX] + " " + GLB.MSGTRAILER[GLB.MSGINDEX] + "</SPAN>");
      }
      else if (GLB.MSGINDEX == -1)
      {    out.println("<SPAN id=\"idMessage\">" + GLB.MSGHEADER[GLB.MSGINDEX*-1] + " " + GLB.MSGTRAILER[GLB.MSGINDEX*-1] + "</SPAN>");
      }
      out.println("</SPAN>");
      out.println("<input type=\"hidden\" id=\"onSession\" name=\"onSession\">");
      out.println("<input type=\"hidden\" id=\"__isProcessing\" name=\"__isProcessing\" value =\"0\">");
      out.println("</FORM>");
      out.println("</BODY>");
      out.println("</HTML>");
   }
   catch (Exception e)
   {   ShowErrorMsg (e,"BuildPage");
   }
}
  public void BuildErrorPage(PrintWriter out, String routineName, String  routineMsg)
  {
      out.println("<HTML>");
      out.println("<style type=\"Text/css\">");
      out.println("BODY {");
      out.println("   background-color: #C0C0C0;");
      out.println("}");
      out.println("</style>");
      out.println("<script language=\"JavaScript\">");
      out.println("function validatePage()");
      out.println("{");
      out.println("return(true);");
      out.println("}");
      out.println("function jXmit()");
      out.println("{");
      out.println("   if (validatePage() == true)");
      out.println("       {       document.frmLOGONERROR.submit();");
      out.println("       }");
      out.println("}");
      out.println("function jKey(pEvent)");
      out.println("{");
      out.println("}");
      out.println("</script>");
      out.println("<BODY onkeydown=\"return(jKey(event))\">");
      out.println("<FORM Name = \"frmLOGONERROR\" id = \"frmLOGONERROR\" method = \"post\">");
      out.println("<font color=\"blue\" size=5>Fatal Error</font>");
      out.println("<BR>");
      out.println("<P>");
      out.println("<font color=\"black\" size=4>Ispec:LOGON</font>");
      out.println("<BR>");
      out.println("<P>");
      out.println("<font color=\"black\" size=4>Routine : " + routineName + "</font>");
      out.println("<P>");
      out.println("<font color=\"black\" size=4>System Message : " + routineMsg + "</font>");
      out.println("<P>");
      out.println("<input type=\"button\" name=\"Button1\" id=\"Button1\" value=\"Go Back\" onClick=\"jXmit();\">");
      out.println("</FORM>");
      out.println("</BODY>");
      out.println("</HTML>");
  }
  public void BuildErrorXmit(PrintWriter out)
  {
      out.println("<HTML>");
      out.println("<style type=\"Text/css\">");
      out.println("BODY {");
      out.println("   background-color: #C0C0C0;");
      out.println("}");
      out.println("</style>");
      out.println("<script language=\"JavaScript\">");
      out.println("function jXmit()");
      out.println("{");
      out.println("   document.frmLOGONONXMIT.submit();");
      out.println("}");
      out.println("function jKey(pEvent)");
      out.println("{");
      out.println("}");
      out.println("</script>");
      out.println("<BODY onkeydown=\"return(jKey(event))\">");
      out.println("<FORM Name = \"frmLOGONONXMIT\" id = \"frmLOGONONXMIT\" method = \"post\" ACTION=\"LOGON\">");
      out.println("<P>");
      out.println("<font color=\"red\" size=5>" + MSG_XMITERROR + "</font>");
      out.println("<BR>");
      out.println("<P>");
      out.println("<input type=\"button\" name=\"Button1\" id=\"Button1\" value=\"" + MSG_GOBACK + "\" onClick=\"jXmit();\">");
      out.println("</FORM>");
      out.println("</BODY>");
      out.println("</HTML>");
      out.close();
  }
public void FormToScreen(HttpServletRequest request)
   throws Exception
{
    try
    {
        if (request == null)
        {
            return;
        }
        if (request.getParameter("USR") == null)
        {    SCREEN_USR = "";
        }
        else
        {    SCREEN_USR = request.getParameter("USR");
        }
        AuditSCR("INP");
   }
   catch (Exception e)
   {   ShowErrorMsg (e,"FormToScreen");
   }
 }

public boolean preCheck(String pRoutine, String pIspec)
  throws Exception
{
    try
    {
        return(true);
    }
    catch(Exception e)
    {   ShowErrorMsg(e, "preCheck");
    	return(false);
    }
}


private void IspecLoad()
  throws Exception
{
    try
    {   /* Executing Initialize */

        InitialSettings();


        /* Executing PreCheck */

        if (preCheck("LOAD",ISPEC) == false)
        {
           return;
        }

        currentDate();
        InitScreen();
        MessageFromAnotherIspec();


        /* Executing PreScreen */
        PreScreen();
        updGlbWork();

    }
    catch (Exception e)
    {    ShowErrorMsg(e,"IspecLoad");
    }
}

public void IspecRefresh()
  throws Exception
{
    try
    {
        /* Executing Initialize */

        InitialSettings();
        currentDate();
        InitScreen();
        GLB.ERROR = "";

        /* Executing PreScreen */
        PreScreen();
        updGlbWork();

    }
    catch (Exception e)
    {    ShowErrorMsg(e,"IspecRefresh");
    }
}


public void IspecCycle()
   throws Exception
{
    try
    {   /* Executing Initialize */

        InitialSettings();


        /* Executing PreCheck */

        if ( preCheck("CYCLE",ISPEC) == false )
        {
           return;
        }

        currentDate();
        GLB.ERROR = "";

        /* Initializing Inquiry Fields */


        singleCycle();

        updGlbWork();


   }
   catch(Exception e)
   {    ShowErrorMsg(e, "IspecCycle");
   }
}


public void ispecRecall(HttpServletRequest requestObject, HttpServletResponse responseObject, HttpSession sessionObject)
 throws Exception
{
    try
    {	
       if (GLB.ABORTMSG.equals("") == false)
       {
           BuildAbortPage(responseObject.getWriter());
           GLB.ABORTMSG = "";
       }
       else if (GLB.REDIRECT.equals("") == false)
       {
          responseObject.sendRedirect(GLB.REDIRECT);
       }
       else if ((GLB.RECALL.equals("") == false) &&
              (GLB.RECALL.toUpperCase().equals(ISPEC.toUpperCase()) == false))
       {
           if (GLB.RECALL.equals("BYE") == true)
           {
               responseObject.sendRedirect("XseedBye");
           }
           else
           {   if (preCheck("RECALL",GLB.RECALL) == false)
               {   GLB.MSGHEADER[1] = GLB.MSGHEADER[GLB.MSGINDEX];
                   GLB.MSGTRAILER[1] = GLB.MSGTRAILER[GLB.MSGINDEX];
                   GLB.MSGINDEX = 1;
                   BuildPage(responseObject.getWriter(), requestObject);
                   return;
               }
               LoadIspec(responseObject, sessionObject, GLB.RECALL); // execute normal RECALL
           }
       }
       else
       {
           BuildPage(responseObject.getWriter(), requestObject);
       }
    }
    catch (Exception e)
    {   ShowErrorMsg (e," ispecRecall");
    }
}



public void PreScreen()
  throws Exception
{  try
   {
	
	GSDWORK= move (GLB.WORK,GSDWORK, 128);
	GSDWORK_GROUP();
     SCREEN_USR = USER;
   }
   catch(Exception e)
   {
       ShowErrorMsg(e, "PreScreen");
   }
}

public void PreLogic()
  throws Exception
{  try
   {
        GLB.TEACH = "";
   }
   catch(Exception e)
   {
       ShowErrorMsg(e, "PreLogic");
   }
}

public void Logic()
  throws Exception
{  try
   {
    USER = move(SCREEN_USR, USER, 10);
    USER_ITEM();
    PAGENAME = move("LOGON", PAGENAME, 5);
    PAGENAME_ITEM();
    GLB.WORK = move(GSDWORK, GLB.WORK, 128);
    
    /*sessionValues = (Map)session.getAttribute("XSDSESSION");
    if (sessionValues==null)
    {
    	sessionValues =new HashMap<String, String>();
    }
    sessionValues.put(USER, GLB.WORK);
    session.setAttribute("XSDSESSION", sessionValues);*/
    
    
    
   }
   catch(Exception e)
   {  ShowErrorMsg(e, "Logic");
   }
}

private void EdtLogic ()
    throws Exception
{
   try
   {
   }
   catch(Exception e)
   {  ShowErrorMsg(e, "EditLogic");
   }
}


public void Validate()
  throws Exception
{   try
    {
    }
    catch(Exception e)
    {    ShowErrorMsg(e, "Validate");
    }
}


public void Update()
  throws Exception
{  try
   {
   }
   catch(Exception e)
   {    ShowErrorMsg(e, "Update");
   }
}



public void ValidatePhase()
  throws Exception
{  try
   {

   }
   catch(Exception e)
   {
      ShowErrorMsg(e,"ValidatePhase");
   }
}

public void UpdatePhase ()
   throws Exception
{   try
    {
    }
    catch(Exception e)
    {
        ShowErrorMsg(e,"UpdatePhase");
    }
}


private void InitScreen()
  throws Exception
{  try
   {
    SCREEN_USR = "";
   }
   catch(Exception e)
   {    ShowErrorMsg(e, "InitScreen");
   }
}

private void ScreenToFile()
  throws Exception
{  try
   {
   }
   catch(Exception e)
   {
        ShowErrorMsg(e, "ScreenToFile");
   }
}


private void FileToScreen()
  throws Exception
{  try
   {
   }
   catch(Exception e)
   {    ShowErrorMsg(e, "FileToScreen");
   }
}


private void OpenFiles()
throws Exception
{   try
    {
       AuditBOT(request);
       if (GLB.DSN.trim().equals("") == true)
       {
           GLB.DSN = "LINCE";
       }
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       GLB.CONNECTION = DriverManager.getConnection("jdbc:odbc:" + GLB.DSN,GLB.USERNAME,GLB.PASSWORD);
       GLB.CONNECTION.setAutoCommit(false);
    }
    catch(SQLException e)
    {   ShowDataMsg(e, "OpenFiles","");
    }
    catch(ClassNotFoundException e)
    {   ShowErrorMsg (e,"OpenFiles");
    }
}

private void CloseFiles()
   throws Exception
{
   try
   {
        if (XSEED_SQL!=null)
        {
            XSEED_SQL.close();
            XSEED_SQL = null;
        }
        if (XSEED_CMD!=null)
        {
            XSEED_CMD.close();
            XSEED_CMD = null;
        }
        if (GLB.CALLABLECMD != null)
        {
           GLB.CALLABLECMD.close();
           GLB.CALLABLECMD=null;
        }
        GLB.CONNECTION.close();
        AuditEOT();
    }
    catch(SQLException e)
    {
        ShowDataMsg(e,"CloseFiles","");
        AuditEOT();
    }
}

private void AbortTransaction (String msg)
{  try
   {   GLB.ABORT = true;
       RollBackTransaction(msg);
   }
   catch (Exception e)
   {  // do not  throw Exception
   }
}

private void GSDWORK_GROUP ()
   throws Exception
{   try
    {
        USER = XseedFunctions.subItem(USER,GSDWORK,0,10,10);
        PAGENAME = XseedFunctions.subItem(PAGENAME,GSDWORK,10,15,5);
        WORK = XseedFunctions.subItem(WORK,GSDWORK,15,33,18);
    }
    catch (Exception e)
    {   ShowErrorMsg (e);
    }
}
private void USER_ITEM ()
   throws Exception
{   try
    {
        GSDWORK = XseedFunctions.groupItem(USER, GSDWORK, 0, 10, 10);
        USER = rTrim (USER,"*");
    }
    catch (Exception e)
    {   ShowErrorMsg (e);
    }
}
private void PAGENAME_ITEM ()
   throws Exception
{   try
    {
        GSDWORK = XseedFunctions.groupItem(PAGENAME, GSDWORK, 10, 15, 5);
        PAGENAME = rTrim (PAGENAME,"*");
    }
    catch (Exception e)
    {   ShowErrorMsg (e);
    }
}
private void WORK_ITEM ()
   throws Exception
{   try
    {
        GSDWORK = XseedFunctions.groupItem(WORK, GSDWORK, 15, 33, 18);
        WORK = rTrim (WORK,"*");
    }
    catch (Exception e)
    {   ShowErrorMsg (e);
    }
}



public void InitialSettings()
  throws Exception
{
   try
   {
        GLB.STATION = GLB.STN;
        GLB.RANDOM = 2729;
        GLB.APPNAME = "LINCE";
        GLB.DBTITLE = GLB.FILESDIR.trim() + "\\LINCE.MDB";
        GLB.BASE = 1957;
        GLB.CENTURY = Double.parseDouble(String.valueOf(GLB.BASE).substring(0,2).trim());
        GLB.DCTYPE = "UK";
        ISPEC = "LOGON";
        GLB.RESTORE = true;
        USER = "";
        PAGENAME = "";
        WORK = "";
    GSDWORK_GROUP_INIT ();
   }
   catch(Exception e)
   {    ShowErrorMsg(e, "InitialSettings");
   }
}

private void GSDWORK_GROUP_INIT ()
   throws Exception
{   try
    {
        GSDWORK = "                                 ";
    }
    catch (Exception e)
    {   ShowErrorMsg (e);
    }
}

public void AuditSCR (String pValue)
   throws Exception
{
    if (GLB.AUDITJOB == false)
    {
       return;
    }
    String msg="";
    try
    {

        msg = msg + "[" + SCREEN_USR.trim() + "]";

        PrintStream audit = new PrintStream(new FileOutputStream(GLB.AUDITACT,true));      			
        audit.println(pValue + ":" + msg.trim());
        audit.close();
    }
    catch (Exception e)
    {
        AuditCrash(pValue + ":" + msg.trim());
    }
}

public void initialProperties()
  throws Exception
{
    try
    {
        initialIspec();
        initialUSR();
    }
    catch (Exception e)
    {
        ShowErrorMsg(e, "initialProperties");
    }
}
public void initialIspec()
  throws Exception
{
    try
    {
        cssLOGON.autotab = false;
        cssLOGON.autotabtexts = false;
        cssLOGON.backcolor = "FFFFFF";
        cssLOGON.fontbold = false;
        cssLOGON.fontitalic = false;
        cssLOGON.fontsize = 8;
        cssLOGON.name = "LOGON";
    }
    catch (Exception e)
    {
        ShowErrorMsg(e, "initialIspec");
    }
}
public void initialUSR()
  throws Exception
{
    try
    {
        USR.out = outHTML;
        USR.backcolor = "";
        USR.height =  870;
        USR.left =  1440;
        USR.length = 10;
        USR.maxlength = 10;
        USR.name = "USR";
        USR.top =  870;
        USR.width = 2865;
    }
    catch (Exception e)
    {
        ShowErrorMsg(e, "initialUSR");
    }
}


public boolean checkSession() 
{
	try {
	String work =  request.getParameter("GLB_WORK");
	if (work!=null)
	{
		move(GLB.WORK, GSDWORK,128);
	    if (USER.equals(SCREEN_USR)) {
	    	SCREEN_USR= "OK";
	    	return true;
	    }
	}
	} catch (Exception e)
	{}
	return false;
}
public void updGlbWork()
  throws Exception
{
}

}
