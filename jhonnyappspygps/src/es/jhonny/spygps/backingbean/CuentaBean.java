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
import es.jhonny.dao.FormasPagoDAO;
import es.jhonny.dao.IdiomasDAO;
import es.jhonny.dao.PaisesDAO;
import es.jhonny.dao.impl.CuentaDAOImpl;
import es.jhonny.dao.impl.FormasPagoDAOImpl;
import es.jhonny.dao.impl.IdiomasDAOImpl;
import es.jhonny.dao.impl.PaisesDAOImpl;
import es.jhonny.model.entity.CuentaUsuario;
import es.jhonny.model.entity.DatosUsuario;
import es.jhonny.model.entity.FormaDePago;
import es.jhonny.model.entity.Navegacion;
import es.jhonny.model.entity.Usuario;
import es.jhonny.spygps.util.MessageFactory;
import es.jhonny.spygps.util.Validaciones;


public class CuentaBean extends Navegacion implements Serializable{

	private static final long serialVersionUID = 2294122563301315952L;
	
	private List<SelectItem> listaPaises;
	private List<SelectItem> listaTiposCuenta;
	private List<SelectItem> listaFormasPago;
	private List<SelectItem> listaIdiomas;
	
	private DatosUsuario datos = null;
	
	
	/* SERVICIOS */
	private static IdiomasDAO idiomaService;
	private static PaisesDAO paisesService;
	private static CuentaDAO cuentaService;
	private static FormasPagoDAO formasPagoService;
	
	
	static{
		idiomaService = new IdiomasDAOImpl();
		paisesService = new PaisesDAOImpl();
		cuentaService = new CuentaDAOImpl();
		formasPagoService = new FormasPagoDAOImpl(); 
	}
	
	public String getFechaActual(){
		DateTimeFormatter formatter = DateTimeFormat.shortDateTime();
		return new DateTime().toString(formatter);
	}
	
	public void setDatos(DatosUsuario datos){
		this.datos = datos;
	}
	
	public DatosUsuario getDatos(){
		if(datos == null){
			try{
				FacesContext context = FacesContext.getCurrentInstance();
				UsuarioBean uBean = (UsuarioBean) context.getELContext().getELResolver().getValue(context.getELContext(), null,"usuarioBean");
				datos = cuentaService.getDatosUsuarioById(uBean.getUsuario().getIdUsuario());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return datos;
	}
	
	
	public List<SelectItem> getListaPaises() {
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
	
	
	public List<SelectItem> getListaTiposCuenta() {
		if(listaTiposCuenta == null){
			listaTiposCuenta = new ArrayList<SelectItem>();
			
			SelectItem enBlanco = new SelectItem();
			enBlanco.setValue(0);
			enBlanco.setLabel("");
			listaTiposCuenta.add(enBlanco);
			
			try{				
				listaTiposCuenta.addAll(cuentaService.getListaTiposCuenta());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return listaTiposCuenta;
	}
	
	
	public List<SelectItem> getListaFormasPago() {
		if(listaFormasPago == null){
			listaFormasPago = new ArrayList<SelectItem>();
			
			SelectItem enBlanco = new SelectItem();
			enBlanco.setValue(0);
			enBlanco.setLabel("");
			listaFormasPago.add(enBlanco);
			
			try{
				listaFormasPago.addAll(formasPagoService.getListaFormasPago());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return listaFormasPago;
	}
	
	
	public List<SelectItem> getListaIdiomas(){
		if(listaIdiomas == null){
			listaIdiomas = new ArrayList<SelectItem>();
			
			SelectItem enBlanco = new SelectItem();
			enBlanco.setValue(0);
			enBlanco.setLabel("");
			listaIdiomas.add(enBlanco);
			
			try{
				listaIdiomas.addAll(idiomaService.getListaIdiomas());
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return listaIdiomas;
	}
	
	
	/**
	 * 
	 * @param event ActionEvent
	 */
	public void actualizarDatosCuenta(ActionEvent event){
		try{
			this.datos = cuentaService.actualizarDatosCuenta(datos);
			
			Usuario usuario = Validaciones.validaUsuario(datos.getNombreUsuario(), datos.getPassword(), cuentaService);
			FacesContext context = FacesContext.getCurrentInstance();
			
			if(usuario != null){
				UsuarioBean uBean = (UsuarioBean) context.getELContext().getELResolver().getValue(context.getELContext(), null,"usuarioBean");
				uBean.setFechaAcceso(new DateTime());
				uBean.setUsuario(usuario);
				
				context.getExternalContext().getSessionMap().put("usuarioBean", uBean);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param event ActionEvent
	 */
	public void recargaTipoCuenta(ActionEvent event){
		Integer idTipoCuenta = datos.getIdTipoCuenta();
		if(idTipoCuenta != null){
			try{
				CuentaUsuario nuevaCuenta = cuentaService.getCuentaById(idTipoCuenta);
				datos.setCuenta(nuevaCuenta);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 
	 * @param event ActionEvent
	 */
	public void recargaFormaDePago(ActionEvent event){
		Integer idFormaPago = datos.getIdFormaPago();
		if(idFormaPago != null){
			try{
				FormaDePago formaPago = formasPagoService.getFormaDePagoById(idFormaPago);
				datos.setFormaPago(formaPago);
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
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
		context.addMessage("formularioCuenta:linkMapaNo", new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageFactory.getMessage("login_necesario"), null));
	}
}
