package es.jhonny.spygps.util;

import java.sql.Connection;
import java.sql.SQLException;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;


public class SpyGPSConnection{
	
//	private static final Log LOG = LogFactory.getLog(SpyGPSConnection.class);
	
	
	public static Connection getConnection() throws Exception{
		try{
//			InitialContext initialContext = new InitialContext();
//			DataSource dataSource = (DataSource) initialContext.lookup("java:/spygps-ds");
//			Connection connection = dataSource.getConnection();
//			return connection;
	    }catch(Exception e){
	    	SpyGPSConnection.getConnection();
	    }
	    return null;
	}
	
	
	public static void closeConnection(Connection connection) throws Exception {
		if(connection != null){
			try{
//				connection.close();
//				LOG.debug("Cerrando conexion...");
			}catch (Exception e) {
				throw new SQLException("ERROR al cerrar la conexi�n con la base de datos: "+ e.getMessage());
			}
		}
	}
}
