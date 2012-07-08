package es.jhonny.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import es.jhonny.dao.PaisesDAO;
import es.jhonny.spygps.util.SpyGPSConnection;


public class PaisesDAOImpl implements PaisesDAO{
	
	
	@Override
	public List<SelectItem> getListaPaises() throws Exception {
		List<SelectItem> listaPaises = new ArrayList<SelectItem>();
		Connection conn = SpyGPSConnection.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM paises");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				SelectItem item = new SelectItem();
				item.setValue(rs.getInt("idPais"));
				item.setLabel(rs.getString("nombre"));
				listaPaises.add(item);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SpyGPSConnection.closeConnection(conn);
		}
		return listaPaises;
	}
}
