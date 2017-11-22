<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
<!-- 					<ol class="breadcrumb text-center"> -->
<!-- 						<li class="breadcrumb-item active">vista encargada de abm de -->
<!-- 							equipo</li> -->
<!-- 					</ol> -->

					<div class="col-md-8 col-md-offset-2">

<!-- 						<div class="form-group"> -->
<!-- 							<a href="" class="btn btn-default">Listado</a>  -->
<!-- 							<a href="" class="btn btn-success active">Añadir equipo</a> -->
<!-- 							<a href=""class="btn btn-warning active">Modificar equipo</a> -->
<!-- 						</div> -->


						<div class="panel panel-primary">
							<div class="panel-heading">Listado de Equipos</div>
							<div class="container-fluid">
								<div class="row">
									<div class="table-responsive">
										<table class="table table-hover">
											<thead>		
											<tr>
												<th class="text-center">ID</th>
												<th class="text-center">Nombre</th>
											</tr>
											</thead>
											<c:forEach items="${equipos}" var="e">
											<tbody class="text-center">
												<td>${e.id}</td>
												<td>${e.nombre}</td>
											</tbody>
											</c:forEach>
										</table>
									</div>
								</div>
							</div>
						</div>


						<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">Creación de un nuevo equipo:</h3>
							</div>
							<div class="container-fluid">

								<form:form class="form-horizontal" role="form" action="" method="post">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-12">
											<div class="input-group">
												<span class="input-group-addon">Nombre</span> <input type="text" class="form-control" name="nombreEquipo" placeholder="Ingrese nombre." required>
											</div>
										</div>
									</div>
							

							<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<a type="submit" data-toggle="modal" data-target="#add" class="btn btn-success">Crear nuevo equipo</a>
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
									<h3 class="modal-title">Crear nuevo equipo:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que sea añadir un nuevo equipo? Se guardarán los datos ingresados.</h4>
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
								<h3 class="panel-title">Modificación de equipos:</h3>
							</div>
							<div class="container-fluid">

								<form:form class="form-horizontal" role="form" action="" method="post">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group center">
												<span class="input-group-addon">Equipo a modificar:</span> <select
													name="empleado" class="form-control" required>

													<c:forEach items="${equipos}" var="e">
														<option value="" selected hidden>Elegir:</option>

														<option value="${e.id}">${e.nombre}</option>
													</c:forEach>

												</select>
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-12">
											<div class="input-group">
												<span class="input-group-addon">Nombre</span> <input
													type="text" class="form-control" name="nombreEquipo"
													placeholder="Ingrese nuevo nombre." required>
											</div>
										</div>
									</div>
							

							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<a type="submit" data-toggle="modal" data-target="#update" class="btn btn-warning">Modicar equipo existente</a>
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
									<h3 class="modal-title">Modicar equipo existente:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que modificar el equipo? Se perderán datos anteriores.</h4>
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
								<h3 class="panel-title">Eliminación de equipos:</h3>
							</div>
							<div class="container-fluid">
							<form:form class="form-horizontal" role="form" action="" method="post">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group center">
												<span class="input-group-addon">Equipo a eliminar:</span> <select
													name="empleado" class="form-control" required>

													<c:forEach items="${equipos}" var="e">
														<option value="" selected hidden>Elegir:</option>

														<option value="${e.id}">${e.id} - ${e.nombre}</option>
													</c:forEach>

												</select>
											</div>
										</div>
									</div>

							

							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<a type="submit" data-toggle="modal" data-target="#delete" class="btn btn-danger">Eliminar equipo seleccionado</a>
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
									<h3 class="modal-title">Eliminar equipo seleccionado:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que desea eliminar el equipo? Se perderán todos sus datos.</h4>
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

					</form:form>s
						
				</div>		
						

				</div>
			</div>



		</div>
	</div>

</body>
</html>
