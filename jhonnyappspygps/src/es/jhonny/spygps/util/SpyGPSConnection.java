package es.jhonny.spygps.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import com.google.appengine.api.rdbms.AppEngineDriver;


public class SpyGPSConnection{
	
//	private static final Log LOG = LogFactory.getLog(SpyGPSConnection.class);
	
	
	public static Connection getConnection() throws Exception{
		String conn = null;
		Connection c = null;
		try{
			// jboss en local
//			InitialContext initialContext = new InitialContext();
//			DataSource dataSource = (DataSource) initialContext.lookup("java:/spygps-ds");
//			Connection connection = dataSource.getConnection();
//			return connection;
			
			
			// appengine en local
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.registerDriver(new AppEngineDriver());
			conn = "jdbc:mysql://instance21166.db.xeround.com:3867/schema_spygps?user=jhonny&password=14743430";
			c = DriverManager.getConnection(conn);
			return c;
			
			
			// appengine en host xeround
//			conn = "http://instance21166.db.xeround.com:3867";
//			URL url = new URL(conn);
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			connection.setDoOutput(true);
//			connection.setUseCaches(false);
//			connection.setRequestMethod("POST");
//			connection.setRequestProperty("Content-Type", "application/xml");
//			connection.getOutputStream();
	    }catch(Exception e){
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
