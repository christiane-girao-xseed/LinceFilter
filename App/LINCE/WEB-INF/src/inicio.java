import java.io.*;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import HLegacy.*;

@WebServlet(value="/servlet/inicio")
public class inicio extends HttpServlet
{

    private HttpServletRequest request;
    private HttpServletResponse response;

    public inicio()
    {
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        doPost(httpservletrequest, httpservletresponse);
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws IOException, ServletException
    {
        try
        {
            request = httpservletrequest;
            response = httpservletresponse;

			writeAttributes();
	    
            String s = new String("");
            String s1 = "http://" + httpservletrequest.getServerName() + ":8080/LINCE/servlet/LOGON";
            
         /*   String aux="?";
            
            Enumeration paramNames = request.getParameterNames();

            while(paramNames.hasMoreElements()) {
               String paramName = (String)paramNames.nextElement();
               //String paramValue = request.getParameterValues(paramName);
               
               String[] paramValues = request.getParameterValues(paramName);
               
               String x = paramName + "=" + paramValues[0];
               
               s1 = s1 + aux + paramName + "=" +  paramValues[0];
               aux="&";
               System.out.println(x);
            }*/
            
            if(httpservletrequest.getParameter("session") != null)
                s = "?session=" + httpservletrequest.getParameter("session");
		
            s1 = s1 + s;
            String s3 = new String("");
            if(httpservletrequest.getParameter("origem") != null)
                s3 = "&origem=" + httpservletrequest.getParameter("origem");
	
            s1 = s1 + s3;
		
            httpservletresponse.sendRedirect(s1);
        }
        catch(Exception exception) { }
    }

 /*   private void buildPage()
        throws Exception
    {
        String s = "newWindow = window.open(";
        if(!Rts.startupPage.equals(""))
            s = s + "'" + Rts.startupPage + "',";
        else
        if(!XseedINI.getFireupIspec().equals(""))
            s = s + "'" + XseedINI.getFireupIspec() + "',";
        else
            s = s + "'XseedController',";
        SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String s1 = simpledateformat.format(new Date());
        s = s + "'" + Rts.appName + s1 + "',";
        s = s + "\"";
        if(Rts.browserToolBar.toUpperCase().equals("TRUE"))
            s = s + "toolbar = yes";
        else
            s = s + "toolbar = no";
        if(Rts.browserStatusBar.toUpperCase().equals("TRUE"))
            s = s + ", status = yes";
        else
            s = s + ", status = no";
        if(Rts.browserMenuBar.toUpperCase().equals("TRUE"))
            s = s + ", menubar = yes";
        else
            s = s + ", menubar = no";
        if(Rts.browserLocation.toUpperCase().equals("TRUE"))
            s = s + ", location = yes";
        else
            s = s + ", location = no";
        if(Rts.browserResizable.toUpperCase().equals("TRUE"))
            s = s + ", resizable = yes";
        else
            s = s + ", resizable = no";
        if(Rts.browserScrollbars.toUpperCase().equals("TRUE"))
            s = s + ", scrollbars = yes";
        else
            s = s + ", scrollbars = no";
        if(Rts.browserTop.equals(""))
            s = s + ", top = 0";
        else
            s = s + ", top = " + Rts.browserTop;
        if(Rts.browserLeft.equals(""))
            s = s + ", left = 0";
        else
            s = s + ", left = " + Rts.browserLeft;
        if(Rts.browserHeight.equals(""))
            s = s + ", height = 460";
        else
            s = s + ", height = " + Rts.browserHeight;
        if(Rts.browserWidth.equals(""))
            s = s + ", width = 790,";
        else
            s = s + ", width = " + Rts.browserWidth;
        s = s + "\");";
        response.setContentType("text/html");
        PrintWriter printwriter = response.getWriter();
        printwriter.println("<html>");
        printwriter.println("<head>");
        printwriter.println("<script language=\"javascript\">");
        printwriter.println("function OpenBrowser(){ ");
        printwriter.println(s);
        printwriter.println("newWindow.focus(); parent.opener = top; parent.close(); } ");
        printwriter.println("</script>");
        printwriter.println("</head>");
        printwriter.println("<body onLoad=\"OpenBrowser()\">");
        printwriter.println("</body></html>");
    }*/

    public void writeAttributes()
        throws Exception
    {
        HttpSession httpsession = request.getSession(true);
      /*  httpsession.setAttribute("Xseed_description", Rts.description);
        httpsession.setAttribute("Xseed_targetOS", Rts.targetOS);
        httpsession.setAttribute("Xseed_targetCompiler", Rts.targetCompiler);
        httpsession.setAttribute("Xseed_user", Rts.user);
        httpsession.setAttribute("Xseed_ipAddress", Rts.ipAddress);
        httpsession.setAttribute("Xseed_clipboard", Rts.clipboard);
        httpsession.setAttribute("Xseed_appName", Rts.appName);
        httpsession.setAttribute("Xseed_appKey", Rts.appKey);
        httpsession.setAttribute("Xseed_auditSession", Rts.auditSession);
        httpsession.setAttribute("Xseed_systemPath", Rts.systemPath);
        httpsession.setAttribute("Xseed_metaInfPath", Rts.metaInfPath);
        httpsession.setAttribute("Xseed_webInfPath", Rts.webInfPath);
        httpsession.setAttribute("Xseed_classesPath", Rts.classesPath);
        httpsession.setAttribute("Xseed_libraryPath", Rts.libraryPath);
        httpsession.setAttribute("Xseed_sourcePath", Rts.sourcePath);
        httpsession.setAttribute("Xseed_auditPath", Rts.auditPath);
        httpsession.setAttribute("Xseed_backupPath", Rts.backupPath);
        httpsession.setAttribute("Xseed_batchPath", Rts.batchPath);
        httpsession.setAttribute("Xseed_databasePath", Rts.databasePath);
        httpsession.setAttribute("Xseed_docPath", Rts.docPath);
        httpsession.setAttribute("Xseed_filesPath", Rts.filesPath);
        httpsession.setAttribute("Xseed_imagesPath", Rts.imagesPath);
        httpsession.setAttribute("Xseed_msgsPath", Rts.msgsPath);
        httpsession.setAttribute("Xseed_tempPath", Rts.tempPath);
        httpsession.setAttribute("Xseed_DBName", Rts.DBName);
        httpsession.setAttribute("Xseed_DBUsername", Rts.DBUsername);
        httpsession.setAttribute("Xseed_DBPassword", Rts.DBPassword);
        httpsession.setAttribute("Xseed_DBServer", Rts.DBServer);
        httpsession.setAttribute("Xseed_DBDataSource", Rts.DBDataSource);
        httpsession.setAttribute("Xseed_DBEngine", Rts.DBEngine);
        httpsession.setAttribute("Xseed_DBConnection", Rts.DBConnection);
        httpsession.setAttribute("Xseed_DBStatement", Rts.DBSqlStatements);
        httpsession.setAttribute("Xseed_browserToolBar", Rts.browserToolBar);
        httpsession.setAttribute("Xseed_browserMenuBar", Rts.browserMenuBar);
        httpsession.setAttribute("Xseed_browserLocation", Rts.browserLocation);
        httpsession.setAttribute("Xseed_browserResizable", Rts.browserResizable);
        httpsession.setAttribute("Xseed_browserScrollbars", Rts.browserScrollbars);
        httpsession.setAttribute("Xseed_browserLeft", Rts.browserLeft);
        httpsession.setAttribute("Xseed_browserTop", Rts.browserTop);
        httpsession.setAttribute("Xseed_browserWidth", Rts.browserWidth);
        httpsession.setAttribute("Xseed_browserHeight", Rts.browserHeight);*/
        httpsession.setAttribute("GLB_APPNAME", XseedINI.getAppName());
        httpsession.setAttribute("GLB_AUDITDIR", XseedINI.getAuditPath());
        httpsession.setAttribute("GLB_BACKUPDIR", XseedINI.getBackupPath());
        httpsession.setAttribute("GLB_BATCHDIR", XseedINI.getBatchPath());
        httpsession.setAttribute("GLB_CLONEAPP", XseedINI.getCloneApp());
        httpsession.setAttribute("GLB_CLONEAPPDIR", XseedINI.getCloneAppPath());
        httpsession.setAttribute("GLB_CLONERTS", XseedINI.getCloneRts());
        httpsession.setAttribute("GLB_CLONERTSDIR", XseedINI.getCloneRtsPath());
        httpsession.setAttribute("GLB_DBNAME", XseedINI.getDBName());
        httpsession.setAttribute("GLB_PASSWORD", XseedINI.getDBPassword());
        httpsession.setAttribute("GLB_USERNAME", XseedINI.getDBUsername());
        httpsession.setAttribute("GLB_DOCDIR", XseedINI.getDocPath());
        httpsession.setAttribute("GLB_DSN", XseedINI.getDSN());
        httpsession.setAttribute("GLB_EXTRACTSDIR", XseedINI.getExtractsPath());
        httpsession.setAttribute("GLB_FILESDIR", XseedINI.getFilesPath());
        httpsession.setAttribute("GLB_FIREUPISPEC", XseedINI.getFireupIspec());
        httpsession.setAttribute("GLB_ICONSDIR", XseedINI.getIconsPath());
        httpsession.setAttribute("GLB_MSGSDIR", XseedINI.getMsgsPath());
        httpsession.setAttribute("GLB_MSGTIMER", XseedINI.getMsgTimer());
        httpsession.setAttribute("GLB_SERVER", XseedINI.getServer());
        httpsession.setAttribute("GLB_SYSTEMDIR", XseedINI.getSystemPath());
        httpsession.setAttribute("GLB_STN", XseedINI.getSTN());
        httpsession.setAttribute("GLB_TARGETOS", XseedINI.getTargetOS());
        httpsession.setAttribute("GLB_TEMPDIR", XseedINI.getTempPath());
        httpsession.setAttribute("GLB_USERCODE", XseedINI.getUserCode());
        httpsession.setAttribute("GLB_FULLSTN", request.getRemoteAddr());
        DecimalFormatSymbols decimalformatsymbols = new DecimalFormatSymbols();
       /* if(Rts.decimalSeparator.equals("$(ask)"))
            httpsession.setAttribute("Xseed_decimalSeparator", "" + decimalformatsymbols.getDecimalSeparator());
        else
            httpsession.setAttribute("Xseed_decimalSeparator", Rts.decimalSeparator);
        if(Rts.groupingSeparator.equals("$(ask)"))
            httpsession.setAttribute("Xseed_groupingSeparator", "" + decimalformatsymbols.getGroupingSeparator());
        else
            httpsession.setAttribute("Xseed_groupingSeparator", Rts.groupingSeparator);
        httpsession.setAttribute("Xseed_invalidAction", Msg.invalidAction);
        httpsession.setAttribute("Xseed_invalidDelete", Msg.invalidDelete);
        httpsession.setAttribute("Xseed_invalidFind", Msg.invalidFind);
        httpsession.setAttribute("Xseed_invalidFirst", Msg.invalidFirst);
        httpsession.setAttribute("Xseed_invalidInclude", Msg.invalidInclude);
        httpsession.setAttribute("Xseed_invalidLast", Msg.invalidLast);
        httpsession.setAttribute("Xseed_invalidModify", Msg.invalidModify);
        httpsession.setAttribute("Xseed_invalidNext", Msg.invalidNext);
        httpsession.setAttribute("Xseed_invalidPrevious", Msg.invalidPrevious);
        httpsession.setAttribute("Xseed_successfullDelete", Msg.successfullDelete);
        httpsession.setAttribute("Xseed_successfullFind", Msg.successfullFind);
        httpsession.setAttribute("Xseed_successfullFirst", Msg.successfullFirst);
        httpsession.setAttribute("Xseed_successfullInclude", Msg.successfullInclude);
        httpsession.setAttribute("Xseed_successfullLast", Msg.successfullLast);
        httpsession.setAttribute("Xseed_successfullModify", Msg.successfullModify);
        httpsession.setAttribute("Xseed_successfullNext", Msg.successfullNext);
        httpsession.setAttribute("Xseed_successfullPrevious", Msg.successfullPrevious);*/
    }
}
