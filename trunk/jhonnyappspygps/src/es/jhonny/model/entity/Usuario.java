package es.jhonny.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import org.joda.time.DateTime;


@Entity
public class Usuario implements Serializable{
	
	private static final long serialVersionUID = -601135928904538144L;
	
	private int idUsuario;
	private String nombreUsuario;
	private String password;
	private DateTime fechaRegistro;
	private Integer idIdioma;
	
	
	public int getIdUsuario(){
		return idUsuario;
	}
	
	public void setIdUsuario(int idUsuario){
		this.idUsuario = idUsuario;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public DateTime getFechaRegistro() {
		return fechaRegistro;
	}
	
	public void setFechaRegistro(DateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public Integer getIdIdioma() {
		return idIdioma;
	}
	
	public void setIdIdioma(Integer idIdioma) {
		this.idIdioma = idIdioma;
	}
}
