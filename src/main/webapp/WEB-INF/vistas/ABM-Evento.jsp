<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
		
				<div class="col-md-8 col-md-offset-2">
		 			<ol class="breadcrumb text-center">
	          			<li class="breadcrumb-item active">Administración de eventos - Desde aquí los podrá visualizar, crear, modificar o eliminar.</li>
	     			</ol>
	     			
	     			
	     				     			
						<div class="panel panel-primary">
							<div class="panel-heading">Listado de eventos</div>
							<div class="container-fluid">
								<div class="row">
									<div class="table-responsive">
										<table class="table table-hover">
											<thead>		
											<tr>
												<th class="text-center">ID</th>
												<th class="text-center">Equipo Local</th>
												<th class="text-center">Equipo Visitante</th>
												<th class="text-center">Descripción del evento</th>
												<th class="text-center">Tipo de evento</th>
											</tr>
											</thead>
											<c:forEach items="${eventos}" var="e">
											<tbody class="text-center">
												<td>${e.id}</td>
												<td>${e.partido.local.nombre}</td>
												<td>${e.partido.visitante.nombre}</td>
												<td>${e.descripcion}</td>
												<td>${e.nombre}</td>
											</tbody>
											</c:forEach>
										</table>
									</div>
								</div>
							</div>
						</div>
				
				
					
				</div>
			</div>
		</div>
	</div>
	


</body>
</html>
