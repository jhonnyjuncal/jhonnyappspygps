package es.jhonny.spygps.backingbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import es.jhonny.dao.ContactoDAO;
import es.jhonny.dao.impl.ContactoDAOImpl;
import es.jhonny.model.entity.Navegacion;
import es.jhonny.spygps.util.MessageFactory;
import es.jhonny.spygps.util.Validaciones;


public class ContactoBean extends Navegacion implements Serializable{
	
	private static final long serialVersionUID = 907974551866004326L;
	
	private String nombreContacto;
	private String emailContacto;
	private String mensajeContacto;
	private List<SelectItem> tiposCorreo;
	private Integer idTipoCorreoSeleccionado;

	
	/* SERVICIOS */
	public static ContactoDAO contactoService;
	
	
	static{
		contactoService = new ContactoDAOImpl();
	}
	
	
	public String getFechaActual(){
		DateTimeFormatter formatter = DateTimeFormat.shortDateTime();
		return new DateTime().toString(formatter);
	}
	
	public void setNombreContacto(String nombreContacto){
		this.nombreContacto = nombreContacto;
	}
	
	public String getNombreContacto(){
		return nombreContacto;
	}
	
	public void setEmailContacto(String emailContacto){
		this.emailContacto = emailContacto;
	}
	
	public String getEmailContacto(){
		return emailContacto;
	}
	
	public void setMensajeContacto(String mensajeContacto){
		this.mensajeContacto = mensajeContacto;
	}
	
	public String getMensajeContacto(){
		return mensajeContacto;
	}
	
	public void setTiposCorreo(List<SelectItem> tiposCorreo){
		this.tiposCorreo = tiposCorreo;
	}
	
	public List<SelectItem> getTiposCorreo(){
		if(tiposCorreo == null){
			tiposCorreo = new ArrayList<SelectItem>();
			
			SelectItem enBlanco = new SelectItem();
			enBlanco.setValue(0);
			enBlanco.setLabel("");
			tiposCorreo.add(enBlanco);
			
			try{				
				tiposCorreo.addAll(contactoService.getListaCorreos());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return tiposCorreo;
	}
	
	public void setIdTipoCorreoSeleccionado(Integer idTipoCorreoSeleccionado){
		this.idTipoCorreoSeleccionado = idTipoCorreoSeleccionado;
	}
	
	public Integer getIdTipoCorreoSeleccionado(){
		return idTipoCorreoSeleccionado;
	}
	
	public void cierraSesion(ActionEvent event){
		Validaciones.cierraSesionUsuario();
	}
	
	public Boolean getTieneMensajesError(){
		Iterator<String> it = FacesContext.getCurrentInstance().getClientIdsWithMessages();
		return (it != null && it.hasNext());
	}
	
	public void muestraMensajeLoginNecesario(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("formularioContacto:linkCuentaNo", new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFactory.getMessage("login_necesario"), null));
	}
}
