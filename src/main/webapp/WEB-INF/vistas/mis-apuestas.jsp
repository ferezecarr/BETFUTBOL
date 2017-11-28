<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head><%@ include file="includes/cabecera.jsp"%>
</head>
<body>
<%@ include file="includes/navegador.jsp"%>
 <%@ include file="includes/modales.jsp"%> 
  <%@ include file="includes/modal-Apuesta.jsp"%> 
	
	<div class="container-fluid">
		<div class="col-md-12 main">
			<div class="row">
			
				
				<div class="col-md-8 col-md-offset-2">
				
				
		 			<ol class="breadcrumb text-center">
	          			<li class="breadcrumb-item active">Mis Apuestas realizadas</li>
	     			</ol>
			
					<div class="panel panel-primary">
					<div class="panel-body text-center">
					<div class="table-responsive">
										<table class="table table-hover">
											<thead>		
											<tr>
												<th class="text-center">Local</th>
												<th class="text-center">Visitante</th>
													<th class="text-center">Evento</th>
														<th class="text-center">Tipo de apuesta</th>
															<th class="text-center">Cantidad apostada</th>
																<th class="text-center">Valor de cuota</th>
												
											</tr>
											</thead>
											<c:forEach  items="${apuestas}" var="a">
											
											<tbody class="text-center">
											
												<td>  ${a.evento.partido.local.nombre}</td>
												<td> ${a.evento.partido.visitante.nombre} </td>
												<td> ${a.evento.nombre}</td>
												<td>${a.cuotaNombre}</td>
												<td>$${a.cantidadApostada}</td>
												<td>${a.cuotaValor}</td>
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
</body>
</html>
