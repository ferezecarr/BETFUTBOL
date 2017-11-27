$(document).ready(function() {
	// Como por default esta seleccionado Resultado, seteo las
	// cosas y llamo a la funcion
	var cantidadInputs = 3;
	var opcionDefault = "Resultado";
	var nombreCuota = [ "Gana el local", "Empate", "Gana el visitante" ];
	agregarInputs(opcionDefault, nombreCuota);

	// Funcion que vacia el div que contiene los inputs
	function limpiarInputs() {
		$('#contenedorInputs').text('');
	}

	// Funcion que va agregando inputs al div segun la cantidad de opciones
	function agregarInputs(opcion, nombreCuota) {
		limpiarInputs();
		for (i = 0; i < cantidadInputs; i++) {
			$("#contenedorInputs").append(
			'<div class="form-group">',
			'<div class="input-group center">',
			'<span class="input-group-addon">'+ nombreCuota[i]+'</span>',			
			'<input class="form-control" type="hidden" tipo="'+opcion+'" id="cuotas['+i+'].nombre" name="cuotas['+i+'].nombre" value="'+nombreCuota[i]+'"/>',								
			'<input class="form-control" type="text" tipo="'+opcion+'" id="cuotas['+i+'].valor" name="cuotas['+i+'].valor" value="1.0"/>',
			'</div>',
			'</div>'
			);
		}		
	}

	// Funcion que en cada cambio del select, define la cantidad de inputs
	var info = document.getElementById("infoEvento");
	$(".selectNuevoEvento").on('change', function() {
		var opt = this;
		var opcion = opt.options[opt.selectedIndex].getAttribute('value');
		switch (opcion) {
			case "Resultado":
				cantidadInputs = 3;
				nombreCuota = [ "Gana el local", "Empate", "Gana el visitante" ];
				break;
			case "Cantidad de goles en un partido":
				cantidadInputs = 6;
				nombreCuota = [ "0", "1", "2", "3", "4", "+4" ];
				break;
			case "Cantidad de goles par o impar":
				cantidadInputs = 2;
				nombreCuota = [ "Par", "Impar" ];
				break;
			default: return;
		}
		agregarInputs(opcion, nombreCuota);
	});
});