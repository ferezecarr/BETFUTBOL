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
	<%@ include file="includes/modalesABMequipo.jsp"%>

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
	          			<li class="breadcrumb-item active">Administración de equipos - Desde aquí los podrá visualizar, crear, modificar o eliminar.</li>
	     			</ol>
	     			
	     			<c:if test="${aviso != null}">
	     			<ol class="breadcrumb text-center">
	          			<li class="breadcrumb-item active">${aviso}</li>
	     			</ol>
	     			</c:if>

<!-- 					

<!-- lisar equipos -->
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

<!--  Empieza panel para crear equipo -->

						<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">Creación de un nuevo equipo:</h3>
							</div>
							<div class="container-fluid">

								<form:form class="form-horizontal" role="form" action="crear-equipo" modelAttribute="equipo" method="post" name="crearEquipo">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-12">
											<div class="input-group">
												<span class="input-group-addon">Nombre</span> 
												
												<form:input type="text" class="form-control" path="nombre" name="nombreEquipo" placeholder="Ingrese nombre." required="required"/>
											</div>
										</div>
									</div>
							

							<div class="form-group">
							<div class="col-md-offset-4 col-md-8">
								<a type="submit" data-toggle="modal" data-target="#add" class="btn btn-success">Crear nuevo equipo</a>
							</div>
						</div>

					</form:form>
					</div>
					
					</div>

<!--  Termina panel para crear equipo -->




<!--  Empieza panel para modificar equipo -->
						<div class="panel panel-warning">
							<div class="panel-heading">
								<h3 class="panel-title">Modificación de equipos:</h3>
							</div>
							<div class="container-fluid">

								<form:form class="form-horizontal" role="form" action="actualizar-equipo" modelAttribute="equipo" method="post" name="modificarEquipo">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group center">
												<span class="input-group-addon">Equipo a modificar:</span>
												
												
												 <form:select path="id" class="form-control" required="required">

													<c:forEach items="${equipos}" var="e">
														

														<form:option value="${e.id}">${e.nombre}</form:option>
													</c:forEach>

												</form:select>
											</div>
										</div>
									</div>

									<div class="form-group">
										<div class="col-md-12">
											
										<form:input path="nombre"  type="text" class="form-control" name="nombreEquipo" placeholder="Ingrese nuevo nombre." required="required"/>
											
										</div>
									</div>
							

							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<a type="submit" data-toggle="modal" data-target="#update" class="btn btn-warning">Modicar equipo existente</a>
								</div>
							</div>
							
							
								</form:form>
							</div>
						</div>




						
				
<!--  Termina panel para modificar equipo -->	

<!--  Empieza panel para eliminar equipo -->	
						<div class="panel panel-danger">
							<div class="panel-heading">
								<h3 class="panel-title">Eliminación de equipos:</h3>
							</div>
							<div class="container-fluid">
							<form:form class="form-horizontal" role="form" action="eliminar-equipo" modelAttribute="equipo" method="post" name="eliminarEquipo">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group center">
												<span class="input-group-addon">Equipo a eliminar:</span> 

													<form:select path="nombre" class="form-control">
													<c:forEach items="${equipos}" var="e">
														<form:option value="${e.nombre}"></form:option>
														
													</c:forEach>
													</form:select>												
											</div>
										</div>
									</div>

							

							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<a type="submit" data-toggle="modal" data-target="#delete" class="btn btn-danger">Eliminar equipo seleccionado</a>
								</div>
							</div>
							
								</form:form>
							</div>
						</div>
				

				
<!--  Termina panel para eliminar equipo -->	
			
				</div>		
						
				</div>
			</div>

		</div>
	

</body>
</html>
