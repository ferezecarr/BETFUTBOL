$( document ).ready(function() {				
	//#EventosFinalizables referencia al dropdown. Cuando se cambia de opcion se ejecuta toda la funcion
	$("#eventosFinalizables").on('change', function() {
		$('#infoEventosFinalizables').text('');	//Se limpia todo al cambiar
		$('#formFinalizarEvento').text('');
		var opt = this;
								
		//Atributos del evento
		var nombre = opt.options[opt.selectedIndex].getAttribute('tipo');
		var desc = opt.options[opt.selectedIndex].getAttribute('desc');
		var id = opt.options[opt.selectedIndex].getAttribute('value');	    
	
		//Mostrandolo en el HTML
		var info = document.getElementById("infoEventosFinalizables");
		if(id != ""){
			$("#infoEventosFinalizables").html(
					  "<br>"
					+ "<ol class='breadcrumb text-center'>"
					+ "<li class='breadcrumb-item active'>Informaci&oacute;n del evento seleccionado:</li>"
					+ "</ol>"
					+ "<br/>Id: " + id
					+ "<br/>Descripci&oacute;n: " + desc
					+ "<br/>Nombre: " + nombre);
					
			/*Ajax para traer los eventos. Se recibe un JSON que se usa en el 'success' 
		 	* para generar el formulario dinamicamente. En el success, se comienza a 
		 	construir el form y se hace una segunda llamada al servidor para traer las 
		 	cuotas. Con una no se puede por el @JSONIgnore. No varia mucho, solo que es 
		 	una llamada mas con respecto a la modificacion*/
			$.ajax({						
				type: 'GET',
				url: "http://localhost:8080/proyecto-limpio-spring/traerEvento?id="+id,
				dataType: 'json',			
				data: JSON.stringify(id),	
				contentType: "application/json; charset=utf-8",
				success: function(data){
					var form = "";
					form += '<br>';
					form += '<ol class="breadcrumb text-center">';
					form += '<li class="breadcrumb-item active">Resultado final del partido '+data.partido.local.nombre+ ' vs ' +data.partido.visitante.nombre+ ':</li>';
					form += '</ol>';
					form += '<form action="finalizar-evento" id="eventoAFinalizar" method="POST" autocomplete="off">';
					form += '<input type="hidden" id="id" name="id" value="'+id+'"/>';
					form += '<input type="hidden" id="partido.id" name="partido.id" value="'+data.partido.id+'"/>';
					
					form += '<div class="form-group">';
					form += '<div class="input-group center">';
					form += '<span class="input-group-addon">'+data.partido.local.nombre+':</span>';
					form += '<input type="text" class="form-control" id="partido.golesLocal" name="partido.golesLocal" value="'+data.partido.golesLocal+'"/>';
					form += '<span class="input-group-addon">Goles</span>';
				    form += '</div>';
				    form += '</div>';
				    
					form += '<div class="form-group">';
					form += '<div class="input-group center">';
					form += '<span class="input-group-addon">'+data.partido.visitante.nombre+':</span>';
					form += '<input type="text" class="form-control" id="partido.golesVisitante+" name="partido.golesVisitante" value="'+data.partido.golesVisitante+'"/>';
					form += '<span class="input-group-addon">Goles</span>';
				    form += '</div>';
				    form += '</div>';
				    
					$("#formFinalizarEvento").append(form);	
					traerCuotas(id);
				}		        
			});
		}else{
			$('#infoEventosFinalizables').text('');	//Se limpia todo al cambiar
			$('#formFinalizarEvento').text('');
		}      
	})
							
	//Si algo sale mal, sale un alert con el codigo de error
	function msjError(xhr, ajaxOptions, thrownError){
		alert("Hubo un Error: " + xhr.status);
		console.log(xhr);
	}
				
	//Segunda llamada para traer las cuotas. Termina de crear el formulario
	function traerCuotas(id){
		$.ajax({						
			type: 'GET',
			url: "http://localhost:8080/proyecto-limpio-spring/traerCuotas?id="+id,
			dataType: 'json',			
			data: JSON.stringify(id),
			contentType: "application/json; charset=utf-8",
			success: function(data2){
				
				var form = '<br>';
				form += '<ol class="breadcrumb text-center">';
				form += '<li class="breadcrumb-item active">Cuota ganadora:</li>';
				form += '</ol>';
				
				form += '<div class="form-group">';
				form += '<div class="input-group center">';				
				form += '<span class="input-group-addon">Seleccione cuota: </span>';
				form += '<select class="form-control" id="cuotaGanadora" name="cuotaGanadora" required="required">';
				form += '<option value="" >Elegir:</option>';
			
				for(let [index, key] of data2.entries()){
					form += '<option value="'+key.nombre+'" name="'+key.nombre+'">'+key.nombre+'</option>';
				}
				form += '</select>';
				form += '</div>';
				form += '</div>';
				
				form += '<div class="form-group">';
				form += '<br><input type="submit" id="Finalizar" class="btn btn-danger center-block" value="Finalizar evento"></input>';		
				form += '</div>';
				
				form += '<br/></form></p>';							
				$("#eventoAFinalizar").append(form);	
			},
			error: function(){
				msjError;
				return "Hubo un error al traer las cuotas";							
			}
		});				
	}
});	