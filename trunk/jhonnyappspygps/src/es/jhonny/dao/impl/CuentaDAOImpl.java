package es.jhonny.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;
import es.jhonny.dao.CuentaDAO;
import es.jhonny.dao.FormasPagoDAO;
import es.jhonny.model.entity.CuentaUsuario;
import es.jhonny.model.entity.DatosUsuario;
import es.jhonny.model.entity.FormaDePago;
import es.jhonny.model.entity.Usuario;
import es.jhonny.spygps.util.SpyGPSConnection;


public class CuentaDAOImpl implements CuentaDAO{
	
	
	@Override
	public List<SelectItem> getListaTiposCuenta() throws Exception {
		List<SelectItem> listaTiposCuenta = new ArrayList<SelectItem>();
		Connection connection = SpyGPSConnection.getConnection();
		
		try{
			PreparedStatement st = connection.prepareStatement("SELECT * FROM tipos_cuenta WHERE idIdioma = 1");
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				SelectItem item = new SelectItem();
				item.setValue(rs.getInt("idTipoCuenta"));
				item.setLabel(rs.getString("descripcion"));
				listaTiposCuenta.add(item);
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}finally{
			SpyGPSConnection.closeConnection(connection);
		}
		return listaTiposCuenta;
	}
	
	
	
	@Override
	public DatosUsuario actualizarDatosCuenta(DatosUsuario datos) throws Exception {
		Connection conn = SpyGPSConnection.getConnection();
		
		String sql = "UPDATE usuarios SET ";
		sql += "nombreUsuario='" + datos.getNombreUsuario() + "',";
		sql += "password='" + datos.getPassword() + "',";
		sql += "sexo_hombre=" + datos.getSexo_hombre() + ",";
		sql += "sexo_mujer=" + datos.getSexo_mujer() + ",";
		sql += "nombre='" + datos.getNombre() + "',";
		sql += "apellidos='" + datos.getApellidos() + "',";
		sql += "direccion='" + datos.getDireccion() + "',";
		sql += "codigoPostal=" + datos.getCodigoPostal() + ",";
		sql += "idPais=" + datos.getIdPais() + ",";
		sql += "telefono='" + datos.getTelefono() + "',";
		sql += "idIdioma=" + datos.getIdIdioma() + ",";
		sql += "notificaciones=" + datos.getNotificaciones() + ",";
		sql += "idTipoCuenta=" + datos.getCuenta().getIdTipoCuenta() + ",";
		
		if(datos.getFormaPago() != null){
			if(datos.getFormaPago().getIdFormaPago() != null)
				sql += "idTipoPago=" + datos.getFormaPago().getIdFormaPago();
		}else
			sql += "idTipoPago=null";
		
		sql += " WHERE idUsuario = " + datos.getIdUsuario();
		
		try{
			PreparedStatement st = conn.prepareStatement(sql);
			st.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SpyGPSConnection.closeConnection(conn);
		}
		return datos;
	}
	
	
	
	@Override
	public Usuario getUsuarioByNombreYPass(String nombreUsuario, Object password) throws Exception {
		Connection conn = null;
		Usuario usuario = null;
		String sql = "SELECT idUsuario, nombreUsuario, idIdioma FROM usuarios WHERE nombreUsuario LIKE '";
		sql += nombreUsuario + "' AND password = '" + password.toString() + "'";
		
		try{
			conn = SpyGPSConnection.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNombreUsuario(rs.getString("nombreUsuario"));
				usuario.setIdIdioma(rs.getInt("idIdioma"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SpyGPSConnection.closeConnection(conn);
		}
		return usuario;
	}
	
	
	
	@Override
	public DatosUsuario getDatosUsuarioById(int idUsuario) throws Exception {
		Connection conn = null;
		String sql = "SELECT * FROM usuarios WHERE idUsuario = " + idUsuario;
		DatosUsuario datos = new DatosUsuario();
		
		try{
			conn = SpyGPSConnection.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				datos.setIdUsuario(rs.getInt("idUsuario"));
				datos.setNombreUsuario(rs.getString("nombreUsuario"));
				datos.setPassword(rs.getString("password"));
				datos.setSexo_hombre(rs.getBoolean("sexo_hombre"));
				datos.setSexo_mujer(rs.getBoolean("sexo_mujer"));
				datos.setNombre(rs.getString("nombre"));
				datos.setApellidos(rs.getString("apellidos"));
				datos.setDireccion(rs.getString("direccion"));
				datos.setCodigoPostal(rs.getInt("codigoPostal"));
				datos.setIdPais(rs.getInt("idPais"));
				datos.setTelefono(rs.getString("telefono"));
				datos.setIdIdioma(rs.getInt("idIdioma"));
				datos.setNotificaciones(rs.getBoolean("notificaciones"));
				datos.setIdTipoCuenta(rs.getInt("idTipoCuenta"));
				if(rs.getInt("idTipoPago") == 0)
					datos.setIdFormaPago(null);
				else
					datos.setIdFormaPago(rs.getInt("idTipoPago"));
			}
			
			if(datos.getIdTipoCuenta() != null){
				/* TIPO DE CUENTA DEL USUARIO */
				CuentaUsuario cu = getCuentaById(datos.getIdTipoCuenta());
				datos.setCuenta(cu);
			}
			
			if(datos.getIdFormaPago() != null){
				/* DATOS DE LA FORMA DE PAGO GUARDADA */
				FormasPagoDAO pagoService = new FormasPagoDAOImpl();
				FormaDePago fp = pagoService.getFormaDePagoById(datos.getIdFormaPago());
				datos.setFormaPago(fp);
			}	
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SpyGPSConnection.closeConnection(conn);
		}
		return datos;
	}
	
	
	
	@Override
	public CuentaUsuario getCuentaById(Integer idTipoCuenta) throws Exception {
		Connection conn = null;
		String sql = "SELECT * FROM tipos_cuenta WHERE idIdioma = 1 AND idTipoCuenta = " + idTipoCuenta;
		CuentaUsuario cu = new CuentaUsuario();
		
		try{
			conn = SpyGPSConnection.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()){
				cu.setIdTipoCuenta(rs.getInt("idTipoCuenta"));
				cu.setDescripcion(rs.getString("descripcion"));
				cu.setDias(rs.getInt("dias"));
				cu.setCoste(rs.getDouble("coste"));
				cu.setIdIdioma(rs.getInt("idIdioma"));
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			SpyGPSConnection.closeConnection(conn);
		}
		return cu;
	}
}
