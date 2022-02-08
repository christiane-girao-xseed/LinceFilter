package HLegacy;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class XseedJDBCRecovery implements Serializable
{   
    public String SQL;
	public String STATUS;
	public boolean EOF;
    public boolean SEEK;
    
    
    public transient ResultSet TAB;
    public transient Connection CON;
    public transient Statement CMD;
    public transient PreparedStatement PCMD;
    public String SUFFIX;
    
    public XseedJDBCRecovery()
    {
   		SUFFIX = "";
    }
}    
   