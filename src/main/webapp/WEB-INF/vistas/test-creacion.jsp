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
			<form:form method="POST" action="recibir-test" modelAttribute="eventoDTO">
			    <form:select path="evento.nombre" class="selectNuevoEvento">
			    	<form:option value="Resultado"></form:option>
			    	<form:option value="Cuantos goles hace un equipo"></form:option>
			    	<form:option value="Cantidad de goles en un partido"></form:option>
			    	<form:option value="Cantidad de goles par o impar"></form:option>
			    </form:select><br/>
			    		<div id="contenedorInputs"></div>			 			
				 <button type="submit">Env&iacute;ar</button>
			</form:form>
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<!-- Js que se usa para el tema de setear cuotas segun el tipo de evento -->
		<script src="js/crearEvento.js" type="text/javascript"></script>
	</body>
</html>