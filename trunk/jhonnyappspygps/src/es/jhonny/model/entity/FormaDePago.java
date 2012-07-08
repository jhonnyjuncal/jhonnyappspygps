package es.jhonny.model.entity;

import java.io.Serializable;

public class FormaDePago implements Serializable{
	
	private static final long serialVersionUID = -765242636686992402L;
	
	private Integer idFormaPago;
	private String descripcion;
	private Integer idIdioma;
	
	
	public Integer getIdFormaPago() {
		return idFormaPago;
	}
	public void setIdFormaPago(Integer idFormaPago) {
		this.idFormaPago = idFormaPago;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getIdIdioma() {
		return idIdioma;
	}
	public void setIdIdioma(Integer idIdioma) {
		this.idIdioma = idIdioma;
	}
	
}
