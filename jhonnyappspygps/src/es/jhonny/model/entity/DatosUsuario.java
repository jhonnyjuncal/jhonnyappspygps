package es.jhonny.model.entity;

import java.io.Serializable;
import javax.persistence.Entity;


@Entity
public class DatosUsuario implements Serializable{

	private static final long serialVersionUID = -4902567708563463380L;
	
	private int idUsuario;
	private String nombreUsuario;
	private String password;
	private Boolean sexo_hombre;
	private Boolean sexo_mujer;
	private String nombre;
	private String apellidos;
	private String direccion;
	private Integer codigoPostal;
	private Integer idPais;
	private String telefono;
	private Integer idIdioma;
	private Boolean notificaciones;
	private Integer idTipoCuenta;
	private Integer idFormaPago;
	
	private CuentaUsuario cuenta;
	private FormaDePago formaPago;
	
	
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
	public Boolean getSexo_hombre() {
		return sexo_hombre;
	}
	public void setSexo_hombre(Boolean sexo_hombre) {
		this.sexo_hombre = sexo_hombre;
	}
	public Boolean getSexo_mujer() {
		return sexo_mujer;
	}
	public void setSexo_mujer(Boolean sexo_mujer) {
		this.sexo_mujer = sexo_mujer;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public Integer getIdPais() {
		return idPais;
	}
	public void setIdPais(Integer idPais) {
		this.idPais = idPais;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Integer getIdIdioma() {
		return idIdioma;
	}
	public void setIdIdioma(Integer idIdioma) {
		this.idIdioma = idIdioma;
	}
	public void setIdFormaPago(Integer idFormaPago) {
		this.idFormaPago = idFormaPago;
	}
	public Integer getIdFormaPago(){
		return idFormaPago;
	}
	public Integer getIdTipoCuenta(){
		return idTipoCuenta;
	}
	public void setIdTipoCuenta(Integer idTipoCuenta){
		this.idTipoCuenta = idTipoCuenta;
	}
	public Boolean getNotificaciones() {
		return notificaciones;
	}
	public void setNotificaciones(Boolean notificaciones) {
		this.notificaciones = notificaciones;
	}
	public CuentaUsuario getCuenta(){
		return cuenta;
	}
	public void setCuenta(CuentaUsuario cuenta){
		this.cuenta = cuenta;
	}
	public FormaDePago getFormaPago(){
		return formaPago;
	}
	public void setFormaPago(FormaDePago formaPago){
		this.formaPago = formaPago;
	}
}
