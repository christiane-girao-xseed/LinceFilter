package HLegacy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class XseedJDBC
{   
    public String SQL;
	public String STATUS;
	public boolean EOF;
    public boolean SEEK;
    public ResultSet TAB;
    public Connection CON;
    public Statement CMD;
    public PreparedStatement PCMD;
    public String SUFFIX;
    
    public XseedJDBC()
    {
   		SUFFIX = "";
    }
}    
   