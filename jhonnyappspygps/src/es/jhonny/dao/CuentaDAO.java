package es.jhonny.dao;

import java.util.List;
import javax.faces.model.SelectItem;
import es.jhonny.model.entity.CuentaUsuario;
import es.jhonny.model.entity.DatosUsuario;
import es.jhonny.model.entity.Usuario;


public interface CuentaDAO {

	public List<SelectItem> getListaTiposCuenta() throws Exception;
	public DatosUsuario actualizarDatosCuenta(DatosUsuario datos) throws Exception;
	public Usuario getUsuarioByNombreYPass(String nombreUsuario, Object password) throws Exception;
	public DatosUsuario getDatosUsuarioById(int idUsuario) throws Exception;
	public CuentaUsuario getCuentaById(Integer idTipoCuenta) throws Exception;
}
