package es.jhonny.dao;

import java.util.List;
import javax.faces.model.SelectItem;


public interface PaisesDAO {

	public List<SelectItem> getListaPaises() throws Exception;
}
