import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

	public Connection getConnection(String pDSN, String pUSERNAME, String pPASSWORD, String pSERVER) throws ClassNotFoundException, SQLException {
		
		Connection con;
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	     con = DriverManager.getConnection("jdbc:sqlserver://" + pSERVER + ":1433;SelectMethod=cursor;User=" + pUSERNAME + ";PassWord=" + pPASSWORD + ";DataBaseName="  +pDSN);
	
		return con;
	}

}
