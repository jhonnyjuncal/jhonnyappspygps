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

import es.jhonny.dao.CuentaDAO;
import es.jhonny.dao.IdiomasDAO;
import es.jhonny.dao.PaisesDAO;
import es.jhonny.dao.RegistroDAO;
import es.jhonny.dao.impl.CuentaDAOImpl;
import es.jhonny.dao.impl.IdiomasDAOImpl;
import es.jhonny.dao.impl.PaisesDAOImpl;
import es.jhonny.dao.impl.RegistroDAOImpl;
import es.jhonny.model.entity.DatosUsuario;
import es.jhonny.model.entity.Navegacion;
import es.jhonny.model.entity.Usuario;
import es.jhonny.spygps.util.MessageFactory;
import es.jhonny.spygps.util.Validaciones;


public class RegistroBean extends Navegacion implements Serializable{

	private static final long serialVersionUID = 2859996346632830700L;
	
	private DatosUsuario datos = new DatosUsuario();
	private List<SelectItem> listaIdiomas;
	private List<SelectItem> listaPaises;
	
	
	/* SERVICIOS */
	private static RegistroDAO registroService;
	private static PaisesDAO paisesService;
	private static IdiomasDAO idiomasService;
	private static CuentaDAO cuentaService;
	
	static{
		paisesService = new PaisesDAOImpl();
		registroService = new RegistroDAOImpl();
		idiomasService = new IdiomasDAOImpl();
		cuentaService = new CuentaDAOImpl();
	}
	
	
	public String getFechaActual(){
		DateTimeFormatter formatter = DateTimeFormat.shortDateTime();
		return new DateTime().toString(formatter);
	}
	
	public DatosUsuario getDatos(){
		return datos;
	}
	
	public void setDatos(DatosUsuario datos){
		this.datos = datos;
	}
	
	public List<SelectItem> getListaIdiomas(){
		if(listaIdiomas == null){
			listaIdiomas = new ArrayList<SelectItem>();
			
			SelectItem enBlanco = new SelectItem();
			enBlanco.setValue(0);
			enBlanco.setLabel("");
			listaIdiomas.add(enBlanco);
			
			try{
				listaIdiomas.addAll(idiomasService.getListaIdiomas());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return listaIdiomas;
	}
	
	
	public void setListaIdiomas(List<SelectItem> listaIdiomas){
		this.listaIdiomas = listaIdiomas;
	}
	
	
	public List<SelectItem> getListaPaises(){
		if(listaPaises == null){
			listaPaises = new ArrayList<SelectItem>();
			
			SelectItem enBlanco = new SelectItem();
			enBlanco.setValue(0);
			enBlanco.setLabel("");
			listaPaises.add(enBlanco);
			
			try{
				listaPaises.addAll(paisesService.getListaPaises());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return listaPaises;
	}
	
	public void setListaPaises(List<SelectItem> listaPaises){
		this.listaPaises = listaPaises;
	}
	
	
	public void guardarDatos(ActionEvent event){
		try{
			if(validacionDatos()){
				//guardar datos personales
				registroService.guardarDatosPersonales(datos);
				loginDeUsuario(datos.getNombre(), datos.getPassword(), cuentaService);
			}else{
				//mensaje de error por pantalla
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	private boolean validacionDatos(){
		if(datos.getNombreUsuario() == null || datos.getNombreUsuario().equals("")){
			return false;
		}
		if(datos.getPassword() == null || datos.getPassword().equals("")){
			return false;
		}
		return true;
	}
	
	
	private void loginDeUsuario(String nombreUsuario, Object password, CuentaDAO cuentaService){
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = Validaciones.validaUsuario(nombreUsuario, password, cuentaService);
		
		if(usuario != null){
			UsuarioBean uBean = (UsuarioBean) context.getELContext().getELResolver().getValue(context.getELContext(), null,"usuarioBean");
			uBean.setFechaAcceso(new DateTime());
			uBean.setUsuario(usuario);
			
			context.getExternalContext().getSessionMap().put("usuarioBean", uBean);
		}else{
			context.getExternalContext().getSessionMap().put("usuarioBean", null);
			FacesMessage msg = new FacesMessage();
			context.addMessage("DATOS INCORRECTOS", msg);
		}
	}
	
	
	public void limpiarDatos(ActionEvent event){
//		datos.setNombreUsuario(null);
//		datos.setPassword(null);
//		datos.setSexo_hombre(null);
//		datos.setSexo_mujer(null);
//		datos.setNombre(null);
//		datos.setApellidos(null);
//		datos.setDireccion(null);
//		datos.setCodigoPostal(null);
//		datos.setPais(null);
//		datos.setTelefono(null);
//		datos.setListaIdiomas(null);
//		datos.setNotificaciones(null);
		setDatos(new DatosUsuario());
	}
	
	
	public void seleccionaHombre(ActionEvent event){
		getDatos().setSexo_hombre(true);
		getDatos().setSexo_mujer(false);
	}
	
	
	public void seleccionaMujer(ActionEvent event){
		getDatos().setSexo_mujer(true);
		getDatos().setSexo_hombre(false);
	}
	
	public Boolean getTieneMensajesError(){
		Iterator<String> it = FacesContext.getCurrentInstance().getClientIdsWithMessages();
		return (it != null && it.hasNext());
	}
	
	public void muestraMensajeLoginNecesario(ActionEvent event){
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("formularioRegistro:linkMapaNo", new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFactory.getMessage("login_necesario"), null));
	}
}
