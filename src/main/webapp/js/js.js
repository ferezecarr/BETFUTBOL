
function goBack() {
    window.history.back();
}

function enviar_formulario(){
   document.formulario1.submit()
}



function CrearEquipo(){
	   document.crearEquipo.submit()
	}

function ModificarEquipo(){
	   document.modificarEquipo.submit()
	}

function EliminarEquipo(){
	   document.eliminarEquipo.submit()
	}




function CrearPartido(){
	   document.crearPartido.submit()
	}

function ModificarPartido(){
	   document.modificarPartido.submit()
	}
function TerminarPartido(){
	   document.terminarPartido.submit()
	}
function EliminarPartido(){
	   document.eliminarPartido.submit()
	}

function validarRegistro(){
	var p1 = $("#clave1").val();
	var p2 = $("#clave2").val();
	
	if(p1 == p2 && p1 != ""){
		enviar_formulario();
	}
	alert("Las claves no coinciden, verifique y vuelva a intentar!");
	return false;
}