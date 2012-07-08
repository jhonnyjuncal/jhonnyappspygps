package es.jhonny.dao;

import java.util.List;
import javax.faces.model.SelectItem;


public interface IdiomasDAO {
	
	public List<SelectItem> getListaIdiomas() throws Exception;
}
