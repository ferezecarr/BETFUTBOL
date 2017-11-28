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
				<c:if test="${not empty aviso}">
					<ol class="breadcrumb text-center ">
						<li class="breadcrumb-item text-success "><h2>${aviso}</h2></li>
					</ol>
				</c:if>
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
	     			
	     			
	     	<!--  Empieza crear partido -->
	     		<div class="panel panel-success">
					<div class="panel-heading"><h3 class="panel-title">Crear nuevo partido:</h3></div>
							<ol class="breadcrumb text-center">
	          					<li class="breadcrumb-item active">Desde aquí podrá crear un nuevo partido, seleccione equipo local y visitante, e ingrese la fecha del encuentro.</li>
	     					</ol>
						<div class="container-fluid">

						<form:form class="form-horizontal" role="form" action="crear-partido" method="post" name="crearPartido" modelAttribute="partido">
							<div class="form-group"></div>

							<div class="form-group">
								<div class="col-md-12">
									<div class="row">
										
      									<div class="col-md-6">
											<div class="input-group">
												<span class="input-group-addon">Local</span>												
												<form:select class="form-control"  path="local.id">
													<c:forEach items="${equipos}" var="e">
														<form:option value="" >Elegir:</form:option>
														<form:option value="${e.id}">${e.nombre}</form:option>
													</c:forEach>	
												</form:select>
											</div>
										</div>
										
									  <div class="col-md-6">
											<div class="input-group">
												<span class="input-group-addon">Visitante</span>												
												<form:select class="form-control" path="visitante.id">
													<c:forEach items="${equipos}" var="e">
														<form:option value="" >Elegir:</form:option>
														<form:option value="${e.id}">${e.nombre}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>																				
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-8 col-md-offset-2">
									<div class="input-group">
										<span class="input-group-addon">Fecha</span> 
										<form:input type="text" path="fecha" class="form-control" />											
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
							<div class="panel-heading"><h3 class="panel-title">Modificación de partidos:</h3></div>
							<ol class="breadcrumb text-center">
	          					<li class="breadcrumb-item active">Desde aquí podrá modificar un partido existente cambiando la fecha del mismo, seleccione el partido deseado e ingrese la nueva fecha.</li>
	     					</ol>							
							<div class="container-fluid">
							<form:form class="form-horizontal" role="form" action="editar-Partido" method="post" name="modificarPartido" modelAttribute="partido">
								<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group center">
												<span class="input-group-addon">Partido a modificar:</span>
												 <form:select name="partidoParaModificar" class="form-control" required="required" path="id">										
													<c:forEach items="${partidos}" var="p">													
														<form:option value="${p.id}">${p.id} - [${p.local.nombre} vs ${p.visitante.nombre}] - ${p.fecha}</form:option>												
													</c:forEach>
												</form:select>
											</div>
										</div>
									</div>
									
								<div class="form-group">
									<div class="col-md-8 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon">Nueva fecha</span> 
											<form:input path="fecha" type="text" class="form-control" name="fechaPartido" placeholder="Ingrese la fecha del partido a disputar." required="required"/>
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
					
			<!-- empieza terminar partido -->			
				<div class="panel panel-warning">
						<div class="panel-heading acomodar"><h3 class="panel-title">Terminar partidos:</h3>
						<div class="centrar_derecha" aria-label="top Align" data-placement="rigth" data-toggle="tooltip" data-html="true" title="Los partidos se dan por finalizados dos horas después de la fecha de inicio automaticamente,
						pero pueden ser terminados abruptamente utilizando esta opción, dejandolos invisibles para los usuarios">
  						<div class="glyphicon glyphicon-question-sign" aria-hidden="true"></div>
						</div>
						</div>
							<ol class="breadcrumb text-center">
	          					<li class="breadcrumb-item active">Desde aquí podrá terminar un partido abruptamente, seleccionando el partido deseado y apretando en el botón.</li>
	     					</ol>						
					<div class="container-fluid">
						<form:form class="form-horizontal" role="form" action="terminar-partido" method="post" name="terminarPartido" modelAttribute="partido">
								<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group center">
												<span class="input-group-addon">Seleccione partido:</span>
												 <form:select name="partidoParaModificar" class="form-control" required="required" path="id">													
													<c:forEach items="${partidosSinTerminar}" var="p">													
														<form:option value="${p.id}">${p.id} - [${p.local.nombre} vs ${p.visitante.nombre}] - ${p.fecha}</form:option>												
													</c:forEach>
												</form:select>
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-offset-4 col-md-8">
											<a data-toggle="modal" data-target="#finish" class="btn btn-warning">Terminar partido seleccionado</a>								
										</div>
									</div>
						</form:form>
					</div>
				</div>	
			<!--  Termina panel para modificar partido -->		
						
				</div>
			</div>
		</div>
	</div>
</body>
</html>
