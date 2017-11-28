<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
	<head>
		<!-- Bootstrap core CSS -->
	    <link href="css/bootstrap.min.css" rel="stylesheet" >
	    <!-- Bootstrap theme -->
	    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
	</head>
	<body>
		<div class = "container">
			<h1>Bienvenidos a Taller Web 1</h1>
			<form:select path="eventosFinalizables" id="eventosFinalizables">
				<form:option value="" >Elegir:</form:option>
				<c:forEach var="evento" items="${eventosFinalizables}">
					<form:option value="${evento.id}" desc="${evento.descripcion}" tipo="${evento.nombre}">
						<c:out value="[${evento.id}]" />
       					<c:out value="${evento.descripcion}" />
					</form:option>
				</c:forEach>	
			</form:select>
			<div id="infoEventosFinalizables"></div>	
			<div id="listaDeCuotas"></div>
			<div id="formFinalizarEvento"></div>
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		
		<!-- LA PARTE DE JS -->
		
		<script>
			$( document ).ready(function() {
				//#EventosFinalizables referencia al dropdown. Cuando se cambia de opcion se ejecuta toda la funcion
				$("#eventosFinalizables").on('change', function() {
					var opt = this;
								
					//Atributos del evento
					var nombre = opt.options[opt.selectedIndex].getAttribute('tipo');
					var desc = opt.options[opt.selectedIndex].getAttribute('desc');
					var id = opt.options[opt.selectedIndex].getAttribute('value');	    
	
					//Mostrandolo en el HTML
					var info = document.getElementById("infoEventosFinalizables");
					if(id != ""){
						$("#infoEventosFinalizables").html(
								"<ol class='breadcrumb text-center'>"
								+"<li class='breadcrumb-item active'>Informaci&oacute;n del evento seleccionado:</li>"
								+"</ol>"
								+ "<br/>Id: " + id
								+ "<br/>Descripci&oacute;n: " + desc
								+ "<br/>Nombre: " + nombre);
						
						/*Ajax para traer las cuotas. Se recibe un JSON que se usa en el 'success' 
						 * para generar el formulario de modificacion de cuotas dinamicamente.*/
						$.ajax({						
							type: 'GET',
							url: "http://localhost:8080/proyecto-limpio-spring/traerEvento?id="+id,
							dataType: 'json',			//Que tipo de respuesta espera
							data: JSON.stringify(id),	//Como lo manda
							contentType: "application/json; charset=utf-8",
							success: function(data){
								alert("Funciona :D");
								var form = "";
								form += '<br/><form action="finalizar-evento" id="eventoAFinalizar" method="POST">';
								form += '<br/><input type="hidden" id="id" name="id" value="'+id+'"/>';
								form += '<br/><input type="hidden" id="partido.id" name="partido.id" value="'+data.partido.id+'"/>';
								form += '<h3>Resultado final</h3>';
								form += '<br/>'+data.partido.local.nombre+': <input type="text" id="partido.golesLocal" name="partido.golesLocal" value="'+data.partido.golesLocal+'"/>';
								form += '<br/>'+data.partido.visitante.nombre+': <input type="text" id="partido.golesVisitante+" name="partido.golesVisitante" value="'+data.partido.golesVisitante+'"/>';
								//Falta la parte de elegir la cuota ganadora								
								form += '<br/><input type="submit" id="Finalizar" value="Finalizar Evento"></input>';								
								form += '<br/></form></p>';	
								$("#formFinalizarEvento").append(form);								
								
								//alert(data.partido.local.nombre);
							}
							/*success : function(data){
								$('#listaDeCuotas').text('');	//Vaciando el div en cada cambio
								
								//Generando el form y poniendolo en el div
								
								var form = "<h3></h3>";
								form += '<ol class="breadcrumb text-center">';
								form += '<li class="breadcrumb-item active">Cuotas:</li>';
								form += '</ol>';
					
								form += '<form action="actualizar-cuotas" id="evento" method="POST">';
								form += '<input type="hidden" id="id" name="id" value="'+id+'"/>';
								for(let [index, key] of data.entries()){
									
									form += '<div class="form-group">';
									form += '<div class="input-group center">';
									form += '<span class="input-group-addon">'+key.nombre+'</span>';
									form += '<input type="hidden" id="cuotas['+index+'].id" name="cuotas['+index+'].id" value="'+key.id+'" />';
								    form += '<input class="form-control" type="text" id="cuotas['+index+'].valor" name="cuotas['+index+'].valor" value="'+key.valor+'" />';
								    form += '</div>';
								    form += '</div>';
								}
	
								form += '<input type="submit" id="Actualizar" class="btn btn-warning center-block" value="Modificar cuotas"></input>';
	
	
								form += '</form></p>';
	
								$("#cuotasAModificar").append(form);
						},
						error: msjError*/		        
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
		</script>
	</body>
</html>