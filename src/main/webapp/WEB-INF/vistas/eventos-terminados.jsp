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
					<div class="panel panel-primary">
					<div class="panel-body text-center">
					<div class="table-responsive">
										<table class="table table-hover">
											<thead>		
											<tr>
												<th class="text-center">Local</th>
												<th class="text-center">Visitante</th>
													<th class="text-center">Descripcion</th>
														<th class="text-center">Cuota con premio</th>
															<th class="text-center">Goles del local</th>
																<th class="text-center">Goles del visitante</th>
												
											</tr>
											</thead>
											<c:forEach  items="${eventos}" var="e">
											
											<tbody class="text-center">
												<td>${e.partido.local.nombre} </td>
												<td>${e.partido.visitante.nombre}</td>
												<td>${e.descripcion}</td>
												<td>${e.cuotaGanadora}</td>
												<td>${e.partido.golesLocal}</td>
												<td>${e.partido.golesVisitante}</td>
											</tbody>
											
											</c:forEach>
										</table>
									</div>
									</div>
									</div>
					
			</div>
			</div>
	</div>


</body>
</html>
