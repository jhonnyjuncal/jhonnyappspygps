package es.jhonny.spygps.util;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import es.jhonny.dao.CuentaDAO;
import es.jhonny.model.entity.Usuario;


public class Validaciones implements Serializable{

	private static final long serialVersionUID = 2660622169799144436L;
	
	
	
	public static Usuario validaUsuario(String nombreUsuario, Object password, CuentaDAO cuentaService){
		Usuario usuario = null;
		try{
			usuario = cuentaService.getUsuarioByNombreYPass(nombreUsuario, password);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return usuario;
	}
	
	
	public static void cierraSesionUsuario(){
		try{
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().put("usuarioBean", null);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
