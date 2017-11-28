<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
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
			<div id="formFinalizarEvento"></div>
		</div>
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<!-- JS para manejar el form dinamico de finalizacion -->
		<script src="js/finalizarEvento.js" type="text/javascript"></script>
	</body>
</html>