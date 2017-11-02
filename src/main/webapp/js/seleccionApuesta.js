function pickOption(opcion){
	document.getElementById("cuotaNombre").value = opcion.getAttribute("name");
	document.getElementById("cuotaValor").value = opcion.getAttribute("value");
	document.getElementById("evento.id").value = opcion.getAttribute("eventoId");
}