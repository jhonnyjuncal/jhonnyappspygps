package es.jhonny.spygps.backingbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import es.jhonny.dao.CuentaDAO;
import es.jhonny.dao.impl.CuentaDAOImpl;
import es.jhonny.model.entity.Navegacion;
import es.jhonny.model.entity.Usuario;
import es.jhonny.spygps.util.MessageFactory;
import es.jhonny.spygps.util.Validaciones;


public class LoginBean extends Navegacion implements Serializable{
	
	private static final long serialVersionUID = -4877390801854963817L;
	private final String PAGINA_PRINCIPAL = "http://localhost:8080/spygps/pages/principal.jsf";
	
	private String nombreUsuario;
	private String passwordUsuario;
	
	
	private static CuentaDAO cuentaService; 
	
	
	static{
		cuentaService = new CuentaDAOImpl();
	}
	
	

	public String getFechaActual(){
		DateTimeFormatter formatter = DateTimeFormat.shortDateTime();
		return new DateTime().toString(formatter);
	}
	
	public void setNombreUsuario(String nombreUsuario){
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getNombreUsuario(){
		return nombreUsuario;
	}
	
	public void setPasswordUsuario(String passwordUsuario){
		this.passwordUsuario = passwordUsuario;
	}
	
	public String getPasswordUsuario(){
		return passwordUsuario;
	}
	
	public void validaDatosAcceso(ActionEvent event){
		if(this.nombreUsuario != null && this.passwordUsuario != null){
			try{
				Usuario usuario = Validaciones.validaUsuario(nombreUsuario, passwordUsuario, cuentaService);
				FacesContext context = FacesContext.getCurrentInstance();
				
				ExternalContext e = context.getExternalContext();
				String url = e.encodeActionURL(PAGINA_PRINCIPAL);
				HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
				
				HttpServletRequest httpServletRequest = (HttpServletRequest) context.getExternalContext().getRequest();
				System.out.println("http://" + httpServletRequest.getLocalName() + ":" + httpServletRequest.getLocalPort() + "/" + httpServletRequest.getContextPath() + "/pages/principal.jsf");
				
				if(usuario != null){
					UsuarioBean uBean = (UsuarioBean) context.getELContext().getELResolver().getValue(context.getELContext(), null,"usuarioBean");
					uBean.setFechaAcceso(new DateTime());
					uBean.setUsuario(usuario);
					
					context.getExternalContext().getSessionMap().put("usuarioBean", uBean);
					response.sendRedirect(url);
				}else{
					context.getExternalContext().getSessionMap().put("usuarioBean", null);
					context.addMessage("formularioLogin:contra", new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFactory.getMessage("login_error_autenticacion"), null));
				}
			}catch(IOException e1){
				e1.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	public Boolean getTieneMensajesError(){
		Iterator<String> it = FacesContext.getCurrentInstance().getClientIdsWithMessages();
		return (it != null && it.hasNext());
	}
	
	public void muestraMensajeLoginNecesario(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("formularioLogin:linkMapaNo", new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFactory.getMessage("login_necesario"), null));
	}
}
