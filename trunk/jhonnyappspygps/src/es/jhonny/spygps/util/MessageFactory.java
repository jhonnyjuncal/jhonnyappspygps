package es.jhonny.spygps.util;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.context.FacesContext;


public class MessageFactory implements Serializable{
	
	private static final long serialVersionUID = -1806739487721995423L;
	
	
	public static String login_error_autenticacion="login_error_autenticacion";
	public static String login_necesario="login_necesario";
	
	
	public  static String getMessage(String key) {
		return ResourceBundle.getBundle("errores").getString(key);
	}
	
	public  static String getMessage(String key, Locale locale) {
		if (locale != null)
			return ResourceBundle.getBundle("errores", locale).getString(key);	
		else
			return ResourceBundle.getBundle("errores",FacesContext.getCurrentInstance().getApplication().getDefaultLocale()).getString(key);		
	}
}
