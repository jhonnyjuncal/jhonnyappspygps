function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; 
  for(i=0; a && i<a.length && (x=a[i]) && x.oSrc; i++)
	  x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function addMarkerToMap1(coordenadas){
	var listaNueva = new Array();
	var tam = coordenadas.length;
	
	while(tam > 0){
		var posicion = coordenadas.indexOf("?");
		var coord = coordenadas.substring(0, posicion);
		listaNueva.push(coord);
		coordenadas = coordenadas.replace(coord.concat("?"), "");
		tam = coordenadas.length;
	}
	
	for(var i=0; i<listaNueva.length; i++){
		var lat = listaNueva[i];
		var lng = listaNueva[++i];
		var fecha = listaNueva[++i];
		var latlng = new google.maps.LatLng(lat,lng);
		var marker = new GMarker(latlng);
		
		marker = crearListener(marker, fecha);
		map.addOverlay(marker);
	}
}

function crearListener(marcador, fecha){
	var pos = fecha.indexOf("%");
	var fechaCorta = fecha.substring(0, pos);
	var hora = fecha.replace(fechaCorta.concat("%"), "");
	GEvent.addListener(marcador, "click", function(){
		marcador.openInfoWindowHtml("<spam><h2>Datos de este punto</h2><br/>" +
				"<strong>FECHA/DATE:&nbsp;</strong>" + fechaCorta + "<br/>" +
				"<strong>HORA/HOUR:&nbsp;</strong>" + hora + "</spam>");
	});
	return marcador;
}

function addMarkerToMap2(lat, lng){
	var marker = new GMarker(lat, lng);
	map.addOverlay(marker);
}

function validateForm(frm){
	var id = frm.id;
	
	if(id == 'formularioLogin')
		return frmLogin();
	if(id == 'formularioRegistro')
		return frmRegistro();
}

function frmLogin(){
	var val1 = document.getElementById('formularioLogin:nombreUsuario').value;
	var val2 = document.getElementById('formularioLogin:contra').value;
	
	if(val2 != ''){
		var encPassw = hex_md5(val2); //b64_md5(val2); //str_md5(val2); //str_hmac_md5(val2);
		document.getElementById("formularioLogin:encPassw").value = encPassw;
		document.getElementById("formularioLogin:contra").value='';
	}
	
//	var errors = false;
//	
//	if(!val1 || val1 == ""){
//		document.getElementById('message1').style.display='block'; //show();
//		errors = true;
//	}
//	
//	if(!val2 || val2==""){
//		document.getElementById('message2').style.display='block'; //show();
//		errors = true;
//	}
//
//	if(errors){
//		Richfaces.showModalPanel(errorPanelId,{width:450, top:200});
//		return false;
//	}
	return true;
}

function frmRegistro(){
	var val1 = document.getElementById('formularioRegistro:contra1').value;
	var val2 = document.getElementById('formularioRegistro:contra2').value;
	
	if(val1 == null || val1 == "")
		return false;
	if(val2 == null || val2 == "")
		return false;
	if(val1 == val2){
		var encriptPassw = hex_md5(val1);
		document.getElementById("formularioRegistro:encrPassw1").value = encriptPassw;
		document.getElementById("formularioRegistro:contra1").value='';
		document.getElementById("formularioRegistro:contra2").value='';
		return true;
	}else{
		return false;
	}
}

function selecionaHombre(){
	document.getElementById("formularioRegistro:sbm").value=false;
}

function selecionaMujer(){
	document.getElementById("formularioRegistro:sbh").value=false;
}

function showPanelMensajeError(){
	var input = document.getElementById("mensajeErrorEnPagina");
	
	if(input != null)
		input = input.value;
	if(input == "true")
		Richfaces.showPopupPanel('panelError');
}

function showPanelCoordenada(){
	Richfaces.showModalPanel('panelCoordenadaSeleccionada');
}