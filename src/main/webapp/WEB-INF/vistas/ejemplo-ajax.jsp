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
		<%-- 
		Notese que este form no tiene un boton de submit. Cada vez que se 
		seleccione una opcion, Javascript se encarga de mandar el form usando ajax. 
		La respuesta es la descripcion del evento y el formulario para modificar las cuotas.
		Estas se colocan en los divs 'infoEvento' y 'cuotasAModificar. Abajo estoy trayendo 
		el 'modificarCuotas.js' que tiene el codigo que hace esto. Ver los comentarios ahi para 
		entender que esta pasando.'
		--%>
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
		<script src="js/modificarCuotas.js" type="text/javascript"></script>
	</body>
</html>