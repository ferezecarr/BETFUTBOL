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
	     			
	     			
	     			
	     			<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">Crear nuevo partido:</h3>
							</div>
							<div class="container-fluid">

								<form:form class="form-horizontal" role="form" action="" method="post">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-12">
										<div class="row">
										
      									<div class="col-md-6">
											<div class="input-group">
												<span class="input-group-addon">Local</span>
												<select name="equipoLocal" class="form-control" required>

													<c:forEach items="${equipos}" var="e">
														<option value="" selected hidden>Elegir equipo:</option>
														<option value="${e.id}">${e.nombre}</option>
													</c:forEach>

												</select>
											</div>
										</div>
										
									  <div class="col-md-6">
											<div class="input-group">
												<span class="input-group-addon">Visitante</span>
												
												<select name="equipoVisitante" class="form-control" required>

													<c:forEach items="${equipos}" var="e">
														<option value="" selected hidden>Elegir equipo:</option>
														<option value="${e.id}">${e.nombre}</option>
													</c:forEach>

												</select>
											</div>
										</div>
										
										</div>
										</div>
									</div>

								<div class="form-group">
									<div class="col-md-8 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon">Fecha</span> 
											<input type="date" class="form-control" name="fechaPartido "placeholder="Ingrese la fecha del partido a disputar." required>
										</div>
									</div>
								</div>


								<div class="form-group">
							<div class="col-md-8 col-md-offset-4">
								<a type="submit" data-toggle="modal" data-target="#add" class="btn btn-success">Crear nuevo partido</a>
							</div>
						</div>

					</div>
					</div>

	
					<div class="modal fade in" id="add" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">

								<div class="modal-header">
									<a class="btn pull-right" data-dismiss="modal"><span
										class="glyphicon glyphicon-remove"></span></a>
									<h3 class="modal-title">Crear nuevo partido:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que sea crear un nuevo partido? Se guardarán los datos ingresados.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button type="submit" name="enviar" class="btn btn-success">
											<span class="glyphicon glyphicon-check"></span> Confirmar
										</button>

									</div>
								</div>

							</div>
						</div>
					</div>
					</form:form>

							<div class="panel panel-warning">
							<div class="panel-heading">
								<h3 class="panel-title">Modificación de partidos:</h3>
							</div>
							<div class="container-fluid">
							<form:form class="form-horizontal" role="form" action="" method="post">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group center">
												<span class="input-group-addon">Partido a eliminar:</span>
												 <select name="empleado" class="form-control" required>
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
											<input type="date" class="form-control" name="fechaPartido "placeholder="Ingrese la fecha del partido a disputar." required>
										</div>
									</div>
								</div>
									

							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<a type="submit" data-toggle="modal" data-target="#update" class="btn btn-warning">Modificar partido seleccionado</a>
								</div>
							</div>
							
							</div>
						</div>

						<div class="modal fade in" id="update" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">

								<div class="modal-header">
									<a class="btn pull-right" data-dismiss="modal"><span
										class="glyphicon glyphicon-remove"></span></a>
									<h3 class="modal-title">Modificar partido seleccionado:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que desea modificar este partido?</h4>
									<h4>Los eventos y cuotas que contengan este partido podran ser afectados por el cambio.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button type="submit" name="enviar" class="btn btn-warning">
											<span class="glyphicon glyphicon-check"></span> Confirmar
										</button>

									</div>
								</div>
							</div>
						</div>
					</div>

					</form:form>
				
				
							<div class="panel panel-danger">
							<div class="panel-heading">
								<h3 class="panel-title">Eliminación de partidos:</h3>
							</div>
							<div class="container-fluid">
							<form:form class="form-horizontal" role="form" action="" method="post">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group center">
												<span class="input-group-addon">Partido a eliminar:</span> <select
													name="empleado" class="form-control" required>

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
									<a type="submit" data-toggle="modal" data-target="#delete" class="btn btn-danger">Eliminar partido seleccionado</a>
								</div>
							</div>
							
							</div>
						</div>

						<div class="modal fade in" id="delete" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">

								<div class="modal-header">
									<a class="btn pull-right" data-dismiss="modal"><span
										class="glyphicon glyphicon-remove"></span></a>
									<h3 class="modal-title">Eliminar partido seleccionado:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que desea eliminar este partido?</h4>
									<h4>Los eventos y cuotas que contengan este partido tambien serán eliminados.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button type="submit" name="enviar" class="btn btn-danger">
											<span class="glyphicon glyphicon-trash"></span> Eliminar
										</button>

									</div>
								</div>
							</div>
						</div>
					</div>

					</form:form>
				
					
				</div>
			</div>
		</div>
	</div>
	


</body>
</html>
