import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Connector to DB using Singleton pattern
 * @author User
 *
 */

/*connecting to MS SQL 
String connectionUrl = "jdbc:sqlserver://localhost:1433;" +  
        "databaseName=AdventureWorks;user=UserName;password=*****"; 
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
con = DriverManager.getConnection(connectionUrl);    */ 

public class MsSqlConnection {   // haven't done yet. It is working connection to a MySql
	String CONNECTION_STRING =
		//	jdbc:sqlserver://localhost:1433;databaseName=testdb;integratedSecurity=true;
			"jdbc:sqlserver://gk990-a05;databaseName=TRACKINGDBB234;integratedSecurity=true";
		//	"jdbc:ucanaccess://C:/Users/Eugene/Desktop/db1.mdb"; //need 4-5 ucanaccess jars included
//	String USER = "root";
//	String PASS = "root";
	private static MsSqlConnection instance = null;
	private Connection connection = null;

	public static MsSqlConnection getInstance() {
		System.out.println("Getting MS SQL connection");
		if (instance == null)
			instance = new MsSqlConnection();
		return instance;
	}

	private MsSqlConnection() {
		try {
			System.out.println("Creating new MS SQL connection");
		//	Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection
					(CONNECTION_STRING);
			System.out.println("connecting to a MS SQL");
		//	(CONNECTION_STRING, USER, PASS);
		} catch (Exception e) {
		//	e.printStackTrace();
			connection = null;
			System.out.println("Connection to SQL server problems:" + e.getMessage());
		}
	}

	public Connection getConnection() {
		return connection;
	}

}