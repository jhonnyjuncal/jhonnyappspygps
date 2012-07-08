package es.jhonny.dao;

import java.util.List;
import javax.faces.model.SelectItem;


public interface ContactoDAO{

	public List<SelectItem> getListaCorreos() throws Exception;
}
