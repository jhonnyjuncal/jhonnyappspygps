package es.jhonny.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import es.jhonny.dao.IdiomasDAO;
import es.jhonny.spygps.util.SpyGPSConnection;


public class IdiomasDAOImpl implements IdiomasDAO{

	
	@Override
	public List<SelectItem> getListaIdiomas() throws Exception {
		List<SelectItem> listaIdiomas = new ArrayList<SelectItem>();
		Connection conn = SpyGPSConnection.getConnection();
		
		try{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM idiomas");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				SelectItem item = new SelectItem();
				item.setValue(rs.getInt("idIdioma"));
				item.setLabel(rs.getString("descripcion"));
				listaIdiomas.add(item);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SpyGPSConnection.closeConnection(conn);
		}
		return listaIdiomas;
	}
}
