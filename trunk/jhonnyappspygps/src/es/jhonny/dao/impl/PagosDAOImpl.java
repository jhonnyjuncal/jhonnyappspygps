package es.jhonny.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import es.jhonny.dao.PagosDAO;
import es.jhonny.model.entity.CuentaUsuario;
import es.jhonny.model.entity.FormaDePago;
import es.jhonny.spygps.util.SpyGPSConnection;


public class PagosDAOImpl implements PagosDAO{

	@Override
	public FormaDePago getFormaDePagoById(int idTipoPago) throws Exception {
		Connection conn = null;
		String sql = "SELECT * FROM formas_pago WHERE idIdioma = 1 AND idTipoPago = " + idTipoPago;
		FormaDePago fp = new FormaDePago();
		
		try{
			conn = SpyGPSConnection.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				fp.setIdFormaPago(rs.getInt("idFormaPago"));
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
