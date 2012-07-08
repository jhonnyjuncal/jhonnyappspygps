package es.jhonny.dao;

import es.jhonny.model.entity.FormaDePago;


public interface PagosDAO {

	public FormaDePago getFormaDePagoById(int idTipoPago) throws Exception;
}
