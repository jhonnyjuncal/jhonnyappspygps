package es.jhonny.dao;

import es.jhonny.model.entity.DatosUsuario;


public interface RegistroDAO {
	public void guardarDatosPersonales(DatosUsuario datosUsuario) throws Exception;
	public void actualizarDatosPersonales(DatosUsuario datosUsuario) throws Exception;
}
