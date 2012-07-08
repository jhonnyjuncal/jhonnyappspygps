package es.jhonny.model.entity;

import java.io.Serializable;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import es.jhonny.spygps.util.Constantes;


public class Navegacion implements Serializable{
	
	private static final long serialVersionUID = 489657732788100088L;
	
	
	/* NAVEGACION */
	public String navegaRegistro(){return Constantes.PAGINA_REGISTRO;}
	public String navegaInicio(){return Constantes.PAGINA_INICIO;}
	public String navegaProductos(){return Constantes.PAGINA_PRODUCTOS;}
	public String navegaCuenta(){return Constantes.PAGINA_CUENTA;}
	public String navegaMapa(){return Constantes.PAGINA_MAPA;}
	public String navegaContacto(){return Constantes.PAGINA_CONTACTO;}
	public String navegaEnlaces(){return Constantes.PAGINA_ENLACES;}
	public String navegaLogin(){return Constantes.PAGINA_LOGIN;}
	public String navegaPoliticas(){return Constantes.PAGINA_POLITICAS;}
	public String navegaTerminos(){return Constantes.PAGINA_TERMINOS;}
}
