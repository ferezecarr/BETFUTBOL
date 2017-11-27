<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="es">
<head><%@ include file="includes/cabecera.jsp"%>
</head>
<body>
	<%@ include file="includes/navegador.jsp"%>
	<%@ include file="includes/modales.jsp"%>


	<div class="container-fluid">
		<div class="col-md-12 main">
			<div class="row">
			
					<ol class="breadcrumb text-center">
						<li class="breadcrumb-item active">Partidos terminados:</li>
					</ol>

			</div>	
					
					<div class="col-md-8 col-md-offset-2">
					
				

					<c:forEach items="${eventos}" var="e">
						<div class="panel panel-primary">
							<div class="panel-heading text-center">
							${e.partido.local.nombre} - ${e.partido.visitante.nombre}
							</div>
							<div class="panel-body text-center">
								<p id="descripcion">${e.descripcion}</p>
								<p>${e.cuotaGanadora} por: ${e.partido.golesLocal} -  ${e.partido.golesVisitante}</p>
							</div>
						</div>
					</c:forEach>
			</div>
			</div>
	</div>


</body>
</html>
