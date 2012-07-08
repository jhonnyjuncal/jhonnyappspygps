package es.jhonny.dao.impl;

import es.jhonny.dao.RegistroDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import es.jhonny.model.entity.DatosUsuario;
import es.jhonny.spygps.util.SpyGPSConnection;


public class RegistroDAOImpl implements RegistroDAO{
	
	@Override
	public void guardarDatosPersonales(DatosUsuario datosUsuario) throws Exception {
		String sql = "INSERT INTO usuarios values (null,";
		Connection conn = null;
		
		try{
			sql += "'" + datosUsuario.getNombreUsuario() + "',";
			sql += "'" + datosUsuario.getPassword() + "',";
			sql += datosUsuario.getSexo_hombre() + ",";
			sql += datosUsuario.getSexo_mujer() + ",";
			sql += "'" + datosUsuario.getNombre() + "',";
			sql += "'" + datosUsuario.getApellidos() + "',";
			sql += "'" + datosUsuario.getDireccion() + "',";
			sql += datosUsuario.getCodigoPostal() + ",";
			sql += datosUsuario.getIdPais() + ",";
			sql += "'" + datosUsuario.getTelefono() + "',";
			sql += datosUsuario.getIdIdioma() + ",";
			sql += datosUsuario.getNotificaciones() + ")";
			
			conn = SpyGPSConnection.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.execute();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SpyGPSConnection.closeConnection(conn);
		}
	}
	
	
	@Override
	public void actualizarDatosPersonales(DatosUsuario datosUsuario) throws Exception {
		try{
			Connection conn = SpyGPSConnection.getConnection();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
