package es.jhonny.spygps.backingbean;

import java.io.Serializable;
import java.util.Iterator;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import es.jhonny.model.entity.Navegacion;
import es.jhonny.spygps.util.MessageFactory;
import es.jhonny.spygps.util.Validaciones;


public class TerminosBean extends Navegacion implements Serializable{
	
	private static final long serialVersionUID = 6156112911656263520L;
	
	
	public void cierraSesion(ActionEvent event){
		Validaciones.cierraSesionUsuario();
	}
	
	protected String getFechaActual(){
		DateTimeFormatter formatter = DateTimeFormat.shortDateTime();
		return new DateTime().toString(formatter);
	}
	
	public Boolean getTieneMensajesError(){
		Iterator<String> it = FacesContext.getCurrentInstance().getClientIdsWithMessages();
		return (it != null && it.hasNext());
	}
	
	public void muestraMensajeLoginNecesario(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("formularioTerminos:linkMapaNo", new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFactory.getMessage("login_necesario"), null));
	}
}
