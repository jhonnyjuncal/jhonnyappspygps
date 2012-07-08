package es.jhonny.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import es.jhonny.dao.FormasPagoDAO;
import es.jhonny.model.entity.FormaDePago;
import es.jhonny.spygps.util.SpyGPSConnection;


public class FormasPagoDAOImpl implements FormasPagoDAO{
	
	
	@Override
	public List<SelectItem> getListaFormasPago() throws Exception {
		List<SelectItem> listaFormasPago = new ArrayList<SelectItem>();
		Connection conn = SpyGPSConnection.getConnection();
		try{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM formas_pago WHERE idIdioma = 1");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				SelectItem item = new SelectItem();
				item.setValue(rs.getInt("idTipoPago"));
				item.setLabel(rs.getString("descripcion"));
				listaFormasPago.add(item);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SpyGPSConnection.closeConnection(conn);
		}
		return listaFormasPago;
	}
	
	
	@Override
	public FormaDePago getFormaDePagoById(Integer idTipoPago) throws Exception{
		FormaDePago fp = null;
		Connection conn = SpyGPSConnection.getConnection();
		
		try{
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM formas_pago WHERE idIdioma = 1 AND idTipoPago = " + idTipoPago);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				fp = new FormaDePago();
				fp.setIdFormaPago(rs.getInt("idTipoPago"));
				fp.setDescripcion(rs.getString("descripcion"));
				fp.setIdIdioma(rs.getInt("idIdioma"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SpyGPSConnection.closeConnection(conn);
		}
		return fp;
	}
}
