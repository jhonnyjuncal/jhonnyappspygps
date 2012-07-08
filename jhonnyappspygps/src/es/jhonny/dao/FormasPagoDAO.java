package es.jhonny.dao;

import java.util.List;
import javax.faces.model.SelectItem;

import es.jhonny.model.entity.FormaDePago;


public interface FormasPagoDAO {

	public List<SelectItem> getListaFormasPago() throws Exception;
	public FormaDePago getFormaDePagoById(Integer idTipoPago) throws Exception;
}
