package es.jhonny.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import org.joda.time.DateTime;


@Entity
public class PuntoCreado implements Serializable{

	private static final long serialVersionUID = 7052803321139761862L;
	
	private Integer idCoordenada;
	private double latitud;
	private double longitud;
	private DateTime fecha;
	private DateTime hora;
	private Integer idUsuario;
	
	
	public void setIdCoordenada(Integer idCoordenada){
		this.idCoordenada = idCoordenada;
	}
	
	public Integer getIdCoordenada(){
		return idCoordenada;
	}
	
	public double getLatitud() {
		return latitud;
	}
	
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}
	
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	public DateTime getFecha() {
		return fecha;
	}
	
	public void setFecha(DateTime fecha) {
		this.fecha = fecha;
	}
	
	public DateTime getHora() {
		return hora;
	}
	
	public void setHora(DateTime hora) {
		this.hora = hora;
	}
	
	public void setIdUsuario(Integer idUsuario){
		this.idUsuario = idUsuario;
	}
	
	public Integer getIdUsuario(){
		return idUsuario;
	}
}