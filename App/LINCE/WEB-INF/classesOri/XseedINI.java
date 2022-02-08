import HLegacy.*;
public class XseedINI extends XseedConfiguration
{
    static String AppName = "LINCE";
    static String AuditPath = "D:\\XseedWork\\Java\\LINCE\\Audit";
    static String AuditSession = "FALSE";
    static String BackupPath = "D:\\XseedWork\\Java\\LINCE\\Backup";
    static String BatchPath = "D:\\XseedWork\\Java\\LINCE\\Batch";
    static String ClassesPath = "D:\\XseedWork\\Java\\LINCE\\WEB-INF\\classesOri";
    static String CloneApp = "FALSE";
    static String CloneAppPath = "D:\\XseedWork\\Java\\LINCE\\Package";
    static String CloneRts = "";
    static String CloneRtsPath = "D:\\XseedWork\\Java\\LINCE\\RtsPackage";
    static String DBName = "LINCE";
    static String DBPassword = "";
    static String DBType = "ACCESS";
    static String DBUsername = "";
    static String DocPath = "D:\\XseedWork\\Java\\LINCE\\Doc";
    static String DSN = "LINCE";
    static String ExtractsPath = "D:\\XseedWork\\Java\\LINCE\\Extracts";
    static String FilesPath = "D:\\XseedWork\\Java\\LINCE\\Files";
    static String FireupIspec = "";
    static String IconsPath = "D:\\XseedWork\\Java\\LINCE\\Icons";
    static String MsgsPath = "D:\\XseedWork\\Java\\LINCE\\Msgs";
    static String MsgTimer = "0";
    static String Server = "";
    static String SystemPath = "D:\\XseedWork\\Java\\LINCE";
    static String STN = "";
    static String TargetOS = "";
    static String TempPath = "D:\\XseedWork\\Java\\LINCE\\Temp";
    static String UserCode = "";
    public static String getAppName()
    {   return(AppName);
    }
    public static String getAuditPath()
    {   return(AuditPath);
    }
    public static String getAuditSession()
    {   return(AuditSession);
    }
    public static String getBackupPath()
    {   return(BackupPath);
    }
    public static String getBatchPath()
    {   return(BatchPath);
    }
    public static String getClassesPath()
    {   return(ClassesPath);
    }
    public static String getCloneApp()
    {   return(CloneApp);
    }
    public static String getCloneAppPath()
    {   return(CloneAppPath);
    }
    public static String getCloneRts()
    {   return(CloneApp);
    }
    public static String getCloneRtsPath()
    {   return(CloneAppPath);
    }
    public static String getDBName()
    {   return(DBName);
    }
    public static String getDBPassword()
    {   return(DBPassword);
    }
    public static String getDBType()
    {   return(DBType);
    }
    public static String getDBUsername()
    {   return(DBUsername);
    }
    public static String getDocPath()
    {   return(DocPath);
    }
    public static String getDSN()
    {   return(DSN);
    }
    public static String getExtractsPath()
    {   return(ExtractsPath);
    }
    public static String getFilesPath()
    {   return(FilesPath);
    }
    public static String getFireupIspec()
    {   return(FireupIspec);
    }
    public static String getIconsPath()
    {   return(IconsPath);
    }
    public static String getMsgsPath()
    {   return(MsgsPath);
    }
    public static String getMsgTimer()
    {   return(MsgTimer);
    }
    public static String getServer()
    {   return(Server);
    }
    public static String getSystemPath()
    {   return(SystemPath);
    }
    public static String getSTN()
    {   return(STN);
    }
    public static String getTargetOS()
    {   return(TargetOS);
    }
    public static String getTempPath()
    {   return(TempPath);
    }
    public static String getUserCode()
    {   return(UserCode);
    }
    public String getProperty(String pPropertyName)
    {
        if (pPropertyName.equals("AppName"))
        {   return(AppName);
        }
        else if (pPropertyName.equals("AuditPath"))
        {   return(AuditPath);
        }
        else if (pPropertyName.equals("AuditSession"))
        {   return(AuditSession);
        }
        else if (pPropertyName.equals("BackupPath"))
        {   return(BackupPath);
        }
        else if (pPropertyName.equals("BatchPath"))
        {   return(BatchPath);
        }
        else if (pPropertyName.equals("ClassesPath"))
        {   return(ClassesPath);
        }
        else if (pPropertyName.equals("CloneApp"))
        {   return(CloneApp);
        }
        else if (pPropertyName.equals("CloneAppPath"))
        {   return(CloneAppPath);
        }
        else if (pPropertyName.equals("CloneRts"))
        {   return(CloneRts);
        }
        else if (pPropertyName.equals("CloneRtsPath"))
        {   return(CloneRtsPath);
        }
        else if (pPropertyName.equals("DBName"))
        {   return(DBName);
        }
        else if (pPropertyName.equals("DBPassword"))
        {   return(DBPassword);
        }
        else if (pPropertyName.equals("DBType"))
        {   return(DBType);
        }
        else if (pPropertyName.equals("DBUsername"))
        {   return(DBUsername);
        }
        else if (pPropertyName.equals("DocPath"))
        {   return(DocPath);
        }
        else if (pPropertyName.equals("DSN"))
        {   return(DSN);
        }
        else if (pPropertyName.equals("ExtractsPath"))
        {   return(ExtractsPath);
        }
        else if (pPropertyName.equals("FilesPath"))
        {   return(FilesPath);
        }
        else if (pPropertyName.equals("FireupIspec"))
        {   return(FireupIspec);
        }
        else if (pPropertyName.equals("IconsPath"))
        {   return(IconsPath);
        }
        else if (pPropertyName.equals("MsgsPath"))
        {   return(MsgsPath);
        }
        else if (pPropertyName.equals("MsgTimer"))
        {   return(MsgTimer);
        }
        else if (pPropertyName.equals("Server"))
        {   return(Server);
        }
        else if (pPropertyName.equals("SystemPath"))
        {   return(SystemPath);
        }
        else if (pPropertyName.equals("STN"))
        {   return(STN);
        }
        else if (pPropertyName.equals("TargetOS"))
        {   return(TargetOS);
        }
        else if (pPropertyName.equals("TempPath"))
        {   return(TempPath);
        }
        else if (pPropertyName.equals("UserCode"))
        {   return(UserCode);
         }
        else
        {
            return("");
        }
    }
}
