<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="es">
<head><%@ include file="includes/cabecera.jsp"%>
</head>
<body>
<%@ include file="includes/navegador.jsp"%>
<%@ include file="includes/modales.jsp"%>
<%@ include file="includes/modalesABMpartido.jsp"%>
	
		<div class="container-fluid">
		<div class="col-md-12 main">
			<div class="row">
		
				<div class="col-md-8 col-md-offset-2">
		 			<ol class="breadcrumb text-center">
	          			<li class="breadcrumb-item active">Administración de partidos - Desde aquí los podrá visualizar, crear, modificar o eliminar.</li>
	     			</ol>
	     			
	     			
						<div class="panel panel-primary">
							<div class="panel-heading">Listado de partidos</div>
							<div class="container-fluid">
								<div class="row">
									<div class="table-responsive">
										<table class="table table-hover">
											<thead>		
											<tr>
												<th class="text-center">ID</th>
												<th class="text-center">Local</th>
												<th class="text-center">Visitante</th>
												<th class="text-center">Fecha</th>
											</tr>
											</thead>
											<c:forEach items="${partidos}" var="p">
											<tbody class="text-center">
												<td>${p.id}</td>
												<td>${p.local.nombre}</td>
												<td>${p.visitante.nombre}</td>
												<td>${p.fecha}</td>
											</tbody>
											</c:forEach>
										</table>
									</div>
								</div>
							</div>
						</div>
	     			
	     			
	     		<!--  Empieza panel para crear partido -->
	     			<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">Crear nuevo partido:</h3>
							</div>
							<div class="container-fluid">

								<form:form class="form-horizontal" role="form" action="crear-partido" method="post" name="crearPartido" modelAttribute="partido">
	
									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-12">
										<div class="row">
										
										
										
      									<div class="col-md-6">
											<div class="input-group">
												<span class="input-group-addon">Local</span>
												
												<form:select class="form-control"  path="local">
													<c:forEach items="${equipos}" var="e">
<%-- 														<form:option value="NONE" selected hidden>Elegir equipo:</form:option>  --%>
														<form:option value="${e.id}">${e.nombre}</form:option>
													</c:forEach>	
												</form:select>
												
<%-- 									<form:input type="text" path="local" class="form-control" /> --%>
											</div>
										</div>
										
									  <div class="col-md-6">
											<div class="input-group">
												<span class="input-group-addon">Visitante</span>
												
												<form:select class="form-control" path="visitante">

													<c:forEach items="${equipos}" var="e">
<%-- 														<form:option value="NONE" selected hidden>Elegir equipo:</form:option> --%>
														<form:option value="${e.id}">${e.nombre}</form:option>
													</c:forEach>

												</form:select>

												
<%-- 												<form:input type="text" path="visitante" class="form-control" /> --%>


											</div>
										</div>
										
										</div>
										</div>
									</div>

								<div class="form-group">
									<div class="col-md-8 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon">Fecha</span> 
											<form:input type="date" path="fecha" class="form-control" />
											
										</div>
									</div>
								</div>
								

								<div class="form-group">
							<div class="col-md-8 col-md-offset-4">
								<a type="submit" data-toggle="modal" data-target="#add" class="btn btn-success">Crear nuevo partido</a>
							</div>
						</div>

						</form:form>
					</div>
					</div>			
	<!--  Termina panel para crear partido -->
	
	
	<!--  Empieza panel para modificar partido -->
							<div class="panel panel-warning">
							<div class="panel-heading">
								<h3 class="panel-title">Modificación de partidos:</h3>
							</div>
							<div class="container-fluid">
							<form:form class="form-horizontal" role="form" action="editar-Partido" method="post" name="modificarPartido" modelAttribute="partido">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group center">
												<span class="input-group-addon">Partido a modificar:</span>
												 <select name="partidoParaModificar" class="form-control" required="required">
													<c:forEach items="${partidos}" var="p">
														<option value="" selected hidden>Elegir:</option>

														<option value="${p.id}">${p.id} - [${p.local.nombre} vs ${p.visitante.nombre}] - ${p.fecha}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									
								<div class="form-group">
									<div class="col-md-8 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon">Nueva fecha</span> 
											<form:input path="fecha" type="date" class="form-control" name="fechaPartido" placeholder="Ingrese la fecha del partido a disputar." required="required"/>
										</div>
									</div>
								</div>
									

							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<a data-toggle="modal" data-target="#update" class="btn btn-warning">Modificar partido seleccionado</a>
								
								</div>
							</div>
							
							
							</form:form>
							</div>
						</div>

						
					
			<!--  Termina panel para modificar partido -->	
				
			<!--  Empieza panel para eliminar partido -->	
							<div class="panel panel-danger">
							<div class="panel-heading">
								<h3 class="panel-title">Eliminación de partidos:</h3>
							</div>
							<div class="container-fluid">
							
							<form:form class="form-horizontal" role="form" action="eliminar-Partido" method="post" name="eliminarPartido" modelAttribute="partido">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group center">
												<span class="input-group-addon">Partido a eliminar:
												</span> <select name="partidoParaEliminar" class="form-control" required="required">

													<c:forEach items="${partidos}" var="p">
														<option value="" selected hidden>Elegir:</option>

														<option value="${p.id}">${p.id} - [${p.local.nombre} vs ${p.visitante.nombre}] - ${p.fecha}</option>
													</c:forEach>

												</select>
											</div>
										</div>
									</div>

							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<a data-toggle="modal" data-target="#delete" class="btn btn-danger">Eliminar partido seleccionado</a>
								</div>
							</div>
							</form:form>
							</div>
						</div>

					
					
				<!--  Termina panel para eliminar partido -->
					
				</div>
			</div>
		</div>
	</div>
	


</body>
</html>
