<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<h1>Seleccione un evento</h1> 
			<form:select path="eventos" id="eventos">
			    <option value="">--- Ninguno ---</option>
			    <c:forEach var="evento" items="${eventos}">
			        <form:option value="${evento.id}" desc="${evento.descripcion}" tipo="${evento.nombre}">
			        	<c:out value="[${evento.id}]"/>
			        	-
			        	<c:out value="${evento.descripcion}"/>
			        	<c:out value="(${evento.nombre})"/>
			        </form:option>
			    </c:forEach>
			</form:select>
			<div id="infoEvento"></div>
			<div id="cuotasAModificar"></div>
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		
		<!-- Javascript aca -->
		<script>			
			$( document ).ready(function() {
				$("#eventos").on('change', function() {
					var opt = this;
					
					//Atributos del evento
					var nombre = opt.options[opt.selectedIndex].getAttribute('tipo');
					var desc = opt.options[opt.selectedIndex].getAttribute('desc');
					var id = opt.options[opt.selectedIndex].getAttribute('value');	    

					//Mostrandolo en el HTML
					var info = document.getElementById("infoEvento");
					if(id != ""){
						$("#infoEvento").html("<h3>Información del evento seleccionado</h3>"
								+ "<br/>Id: " + id
								+ "<br/>Descripción: " + desc
								+ "<br/>Nombre: " + nombre);
						$.ajax({						
				            type: 'GET',
				            url: "http://localhost:8080/proyecto-limpio-spring/traerCuotasV2?id="+id,
				            dataType: 'json',
				            data: JSON.stringify(id),
				            contentType: "application/json; charset=utf-8",
					        success : function(data){
					        	$('#cuotasAModificar').text('');
					        	var form = "<h3>Cuotas</h3>";
					        	form += '<p><form action="actualizar-cuotasV2" id="evento" method="POST">';
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
				
				function msjError(xhr, ajaxOptions, thrownError){
					alert("Hubo un Error: " + xhr.status);
					console.log(xhr);
				}				
			});		
		</script>
	</body>
</html>