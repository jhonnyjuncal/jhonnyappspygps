package es.jhonny.spygps.backingbean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import es.jhonny.model.entity.Navegacion;
import es.jhonny.model.entity.PuntoCreado;
import es.jhonny.spygps.util.Constantes;
import es.jhonny.spygps.util.Validaciones;


public class MapaBean extends Navegacion implements Serializable{

	private static final long serialVersionUID = 4002757132004776915L;
	
	private Integer mapaZoom = Constantes.MAPA_CONST_ZOOM;
	private Integer mapaZoomMinimo = Constantes.MAPA_CONST_MIN_ZOOM;
	private Integer mapaZoomMaximo = Constantes.MAPA_CONST_MAX_ZOOM;
	private String mapaKey = Constantes.MAPA_KEY;
	
	private List<PuntoCreado> mapaCoordenadas;
	private PuntoCreado mapaCoorSeleccionada;
	private String arrayCoordenadas;

	
	public String getFechaActual(){
		DateTimeFormatter formatter = DateTimeFormat.shortDateTime();
		return new DateTime().toString(formatter);
	}
	
	public void setMapaZoom(Integer mapaZoom){
		this.mapaZoom = mapaZoom;
	}
	
	public Integer getMapaZoom(){
		return mapaZoom;
	}
	
	public void setMapaZoomMinimo(Integer mapaZoomMinimo){
		this.mapaZoomMinimo = mapaZoomMinimo;
	}
	
	public Integer getMapaZoomMinimo(){
		return mapaZoomMinimo;
	}
	
	public void setMapaZoomMaximo(Integer mapaZoomMaximo){
		this.mapaZoomMaximo = mapaZoomMaximo;
	}
	
	public Integer getMapaZoomMaximo(){
		return mapaZoomMaximo;
	}
	
	public String getMapaKey(){
		return mapaKey;
	}
	
	public void setMapaKey(String mapaKey){
		this.mapaKey = mapaKey;
	}
	
	public void setMapaCoordenadas(List<PuntoCreado> mapaCoordenadas){
		this.mapaCoordenadas = mapaCoordenadas;
	}
	
	public List<PuntoCreado> getMapaCoordenadas(){
		if(mapaCoordenadas == null){
			mapaCoordenadas = new ArrayList<PuntoCreado>();
			
			PuntoCreado lat_lon = new PuntoCreado();
			lat_lon.setLatitud(43.295512);
			lat_lon.setLongitud(-8.272634);
			lat_lon.setFecha(new DateTime());
			mapaCoordenadas.add(lat_lon);
			
			lat_lon = new PuntoCreado();
			lat_lon.setLatitud(43.298229);
			lat_lon.setLongitud(-8.274747);
			lat_lon.setFecha(new DateTime());
			mapaCoordenadas.add(lat_lon);
			
			lat_lon = new PuntoCreado();
			lat_lon.setLatitud(43.298629);
			lat_lon.setLongitud(-8.270155);
			lat_lon.setFecha(new DateTime());
			mapaCoordenadas.add(lat_lon);
			
			lat_lon = new PuntoCreado();
			lat_lon.setLatitud(43.298807);
			lat_lon.setLongitud(-8.267945);
			lat_lon.setFecha(new DateTime());
			mapaCoordenadas.add(lat_lon);
			
			lat_lon = new PuntoCreado();
			lat_lon.setLatitud(43.301649);
			lat_lon.setLongitud(-8.269039);
			lat_lon.setFecha(new DateTime());
			mapaCoordenadas.add(lat_lon);
			
			lat_lon = new PuntoCreado();
			lat_lon.setLatitud(43.301650);
			lat_lon.setLongitud(-8.269040);
			lat_lon.setFecha(new DateTime());
			mapaCoordenadas.add(lat_lon);
			
			lat_lon = new PuntoCreado();
			lat_lon.setLatitud(43.301651);
			lat_lon.setLongitud(-8.269041);
			lat_lon.setFecha(new DateTime());
			mapaCoordenadas.add(lat_lon);
		}
		return mapaCoordenadas;
	}
	
	public void setMapaCoorSeleccionada(PuntoCreado mapaCoorSeleccionada){
		this.mapaCoorSeleccionada = mapaCoorSeleccionada;
	}
	
	public PuntoCreado getMapaCoorSeleccionada(){
		if(mapaCoordenadas != null){
			setMapaCoorSeleccionada(mapaCoordenadas.get(0));
		}else{
			mapaCoorSeleccionada = getMapaCoordenadas().get(0);
		}
		return mapaCoorSeleccionada;
	}
	
	public String getArrayCoordenadas(){
		List<PuntoCreado> list = getMapaCoordenadas();
		arrayCoordenadas = new String();
		if(list != null){
			for(int i=0; i<list.size(); i++){
				arrayCoordenadas += list.get(i).getLatitud() + "?";
				arrayCoordenadas += list.get(i).getLongitud() + "?";
				DateTime fecha = list.get(i).getFecha();
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy'%'HH:mm:ss");
				arrayCoordenadas += df.format(fecha.toDate()) + "?";
			}
		}
		return arrayCoordenadas;
	}
	
	public void imprimirPDF(){
		
	}
	
	public void guardarComo(){
		
	}
	
	public void cierraSesion(ActionEvent event){
		Validaciones.cierraSesionUsuario();
	}
	
	public void puntoSeleccionado(ActionEvent event){
		
	}
	
	public Boolean getTieneMensajesError(){
		Iterator<String> it = FacesContext.getCurrentInstance().getClientIdsWithMessages();
		return (it != null && it.hasNext());
	}
}
