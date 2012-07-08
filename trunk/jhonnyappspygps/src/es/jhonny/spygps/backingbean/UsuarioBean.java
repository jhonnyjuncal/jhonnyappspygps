package es.jhonny.spygps.backingbean;

import java.io.Serializable;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.joda.time.DateTime;

import es.jhonny.model.entity.Navegacion;
import es.jhonny.model.entity.Usuario;
import es.jhonny.spygps.util.MessageFactory;


public class UsuarioBean extends Navegacion implements Serializable{

	private static final long serialVersionUID = 8852581620804264578L;
	
	private Usuario usuario;
	private DateTime fechaAcceso;
	
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	public void setFechaAcceso(DateTime fechaAcceso){
		this.fechaAcceso = fechaAcceso;
	}
	
	public DateTime getFechaAcceso(){
		return fechaAcceso;
	}
	
	public Boolean getTieneMensajesError(){
		Iterator<String> it = FacesContext.getCurrentInstance().getClientIdsWithMessages();
		return (it != null && it.hasNext());
	}
	
	public void muestraMensajeLoginNecesario(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("formularioContacto:linkMapaNo", new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFactory.getMessage("login_necesario"), null));
	}
}
