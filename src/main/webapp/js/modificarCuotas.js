$( document ).ready(function() {
	//#Eventos referencia al dropdown. Cuando se cambia de opcion se ejecuta toda la funcion
	$("#eventos").on('change', function() {
		var opt = this;
					
		//Atributos del evento
		var nombre = opt.options[opt.selectedIndex].getAttribute('tipo');
		var desc = opt.options[opt.selectedIndex].getAttribute('desc');
		var id = opt.options[opt.selectedIndex].getAttribute('value');	    

		//Mostrandolo en el HTML
		var info = document.getElementById("infoEvento");
		if(id != ""){
			$("#infoEvento").html("<h3>Informaci&oacute;n del evento seleccionado</h3>"
					+ "<br/>Id: " + id
					+ "<br/>Descripci&oacute;n: " + desc
					+ "<br/>Nombre: " + nombre);
			
			/*Ajax para traer las cuotas. Se recibe un JSON que se usa en el 'success' 
			 * para generar el formulario de modificacion de cuotas dinamicamente.*/
			$.ajax({						
				type: 'GET',
				url: "http://localhost:8080/proyecto-limpio-spring/traerCuotas?id="+id,
				dataType: 'json',	//Que tipo de respuesta espera
				data: JSON.stringify(id),	//Como lo manda
				contentType: "application/json; charset=utf-8",
				success : function(data){
					$('#cuotasAModificar').text('');	//Vaciando el div en cada cambio
					
					//Generando el form y poniendolo en el div
					var form = "<h3>Cuotas</h3>";
					form += '<p><form action="actualizar-cuotas" id="evento" method="POST">';
					form += '<br/><input type="hidden" id="id" name="id" value="'+id+'"/>';
					for(let [index, key] of data.entries()){
						form += '<br/><input type="hidden" id="cuotas['+index+'].id" name="cuotas['+index+'].id" value="'+key.id+'" />';
					    form += '<br/>'+key.nombre+'<input type="text" id="cuotas['+index+'].valor" name="cuotas['+index+'].valor" value="'+key.valor+'" />';
					}
					form += '<br/><input type="submit" id="Actualizar" value="Actualizar"></input>';
					form += '<br/></form></p>';
					$("#cuotasAModificar").append(form);
			},
			error: msjError		        
		});
		}else{
			$('#infoEvento').text('');
			$('#cuotasAModificar').text('');
		}      
	})
				
	//Si algo sale mal, sale un alert con el codigo de error
	function msjError(xhr, ajaxOptions, thrownError){
		alert("Hubo un Error: " + xhr.status);
		console.log(xhr);
	}				
});		