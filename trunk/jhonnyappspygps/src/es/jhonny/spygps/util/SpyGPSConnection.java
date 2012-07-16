package es.jhonny.spygps.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;


public class SpyGPSConnection{
	
//	private static final Log LOG = LogFactory.getLog(SpyGPSConnection.class);
	
	
	public static Connection getConnection() throws Exception{
		String conn = "jdbc:mysql://instance21166.db.xeround.com:3867/schema_spygps?user=jhonny&password=14743430";
		Connection c = null;
		try{
//			InitialContext initialContext = new InitialContext();
//			DataSource dataSource = (DataSource) initialContext.lookup("java:/spygps-ds");
//			Connection connection = dataSource.getConnection();
//			return connection;
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(conn);
			return c;
	    }catch(Exception e){
	    	c = DriverManager.getConnection(conn);
	    	if(c != null)
	    		return c;
	    	e.printStackTrace();
	    }
	    return null;
	}
	
	
	public static void closeConnection(Connection connection) throws Exception {
		if(connection != null){
			try{
				connection.close();
			}catch (Exception e) {
				throw new SQLException("ERROR al cerrar la conexi�n con la base de datos: "+ e.getMessage());
			}
		}
	}
}
