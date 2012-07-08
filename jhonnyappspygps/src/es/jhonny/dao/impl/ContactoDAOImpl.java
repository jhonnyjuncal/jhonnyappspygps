package es.jhonny.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

import es.jhonny.dao.ContactoDAO;
import es.jhonny.spygps.util.SpyGPSConnection;


public class ContactoDAOImpl implements ContactoDAO{

	
	@Override
	public List<SelectItem> getListaCorreos() throws Exception {
		List<SelectItem> tiposCorreo = new ArrayList<SelectItem>();
		Connection connection = SpyGPSConnection.getConnection();;
		
		try{
			PreparedStatement st = connection.prepareStatement("SELECT * FROM tipos_correo WHERE idIdioma = 1");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				SelectItem item = new SelectItem();
				item.setValue(rs.getInt("idTiposCorreo"));
				item.setLabel(rs.getString("descripcion"));
				tiposCorreo.add(item);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			SpyGPSConnection.closeConnection(connection);
		}
		return tiposCorreo;
	}
}