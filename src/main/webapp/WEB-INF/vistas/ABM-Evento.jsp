<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html lang="es">
<head><%@ include file="includes/cabecera.jsp"%>
</head>
<body>
<%@ include file="includes/navegador.jsp"%>
<%@ include file="includes/modales.jsp"%>
<%-- <%@ include file="includes/modalesABMevento.jsp"%> --%>
	
		<div class="container-fluid">
		<div class="col-md-12 main">
			<div class="row">
		
				<div class="col-md-8 col-md-offset-2">
		 			<ol class="breadcrumb text-center">
	          			<li class="breadcrumb-item active">Administración de eventos - Desde aquí los podrá visualizar, crear, modificar o eliminar.</li>
	     			</ol>
	     				     			
	     	<!-- Se listan los eventos -->	     			
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
						
				
			<!--  Empieza panel para crear evento -->
	     			<div class="panel panel-success">
							<div class="panel-heading">
								<h3 class="panel-title">Crear nuevo evento:</h3>
							</div>
							<div class="container-fluid">

								<form:form class="form-horizontal" role="form" method="post" action="crear-evento" name="crearEvento" modelAttribute="eventoDTO">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-12">

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group">
												<span class="input-group-addon">Partido</span>
												<form:select path="evento.partido.id" class="form-control" required="required">

													<c:forEach items="${partidos}" var="p">
														
 														<form:option value="${p.id}">${p.id} -  [${p.local.nombre} vs ${p.visitante.nombre}] - ${p.fecha}</form:option>													</c:forEach>

												</form:select>
											</div>
											</div>
											</div>
											
									<div class="form-group">		
									<div class="col-md-10 col-md-offset-1">
										<div class="input-group">
											<span class="input-group-addon">Descripcion</span>
																											
												<form:input type="text" placeholder="(Ej. Premier League)" path="evento.descripcion" class="form-control" />
										
										</div>
									</div>
									</div>
									
									<div class="form-group">
									<div class="col-md-10 col-md-offset-1">
										<div class="input-group">
											<span class="input-group-addon">Tipo de evento</span>

												<form:select path="evento.nombre" class=" form-control selectNuevoEvento"> 
													<form:option value="Resultado"></form:option> 
													<form:option value="Cuantos goles hace un equipo"></form:option> 
													<form:option value="Cantidad de goles en un partido"></form:option> 
 													<form:option value="Cantidad de goles par o impar"></form:option> 
 												</form:select> 																												
										</div>
									</div>
									</div>
									
									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">																			
											<div id="contenedorInputs"></div>										
										</div>
									</div>

									
											
								<div class="col-md-8 col-md-offset-2">
									<div class="input-group hidden" >
										<span class="input-group-addon">isTerminado</span> 																							
										<form:input path="evento.isTerminado" class="form-control" />
									</div>
								</div>
		
							</div>
						</div>
							
						<div class="form-group">
							<div class="col-md-8 col-md-offset-4">
								<a data-toggle="modal" type="submit" data-target="#add" class="btn btn-success">Crear nuevo evento</a>
							</div>
						</div>
						
							<div class="modal fade in" id="add" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">

								<div class="modal-header">
									<a class="btn pull-right" data-dismiss="modal"><span
										class="glyphicon glyphicon-remove"></span></a>
									<h3 class="modal-title">Crear nuevo evento:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que sea añadir un nuevo evento?</h4>
									<h4> Se guardarán los datos ingresados.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button name="enviar" class="btn btn-success" onclick="CrearEvento()">
											<span class="glyphicon glyphicon-check"></span> Confirmar
										</button>

									</div>
								</div>
							</div>
						</div>
					</div>						
				</form:form>
			  </div>
			</div>				
					<!--  Termina panel para crear evento -->
				


					<!--  Empieza panel para modificar evento -->															
		<%-- 		Notese que este form no tiene un boton de submit. Cada vez que se  --%>
		<%-- 		seleccione una opcion, Javascript se encarga de mandar el form usando ajax.  --%>
		<%-- 		La respuesta es la descripcion del evento y el formulario para modificar las cuotas. --%>
		<%-- 		Estas se colocan en los divs 'infoEvento' y 'cuotasAModificar. Abajo estoy trayendo  --%>
		<%-- 		el 'modificarCuotas.js' que tiene el codigo que hace esto. Ver los comentarios ahi para  --%>
		<%-- 		entender que esta pasando.' --%>
		
						<div class="panel panel-warning">
						<div class="panel-heading">
							<h3 class="panel-title">Modificar eventos:</h3>
						</div>
						<div class="container-fluid">
							
						<div class="form-horizontal">
							
							<div class="form-group"></div>
							<div class="form-group">	
								<div class="col-md-10 col-md-offset-1">
								<div class="input-group center">
									<span class="input-group-addon">Seleccione un evento:</span>
									
							<form:select path="eventos" id="eventos" class="form-control">
								<form:option value="" >Elegir:</form:option>
								<c:forEach var="evento" items="${eventos}">
								<form:option value="${evento.id}" desc="${evento.descripcion}" tipo="${evento.nombre}">
										<c:out value="[${evento.id}]" />
			        					<c:out value="${evento.descripcion}" />
										<c:out value="(${evento.nombre})" />
									</form:option>
								</c:forEach>	
							</form:select>	
								
								</div>
							</div>
								</div>
							</div>	

							<div class="form-group">	
							<div class="col-md-10 col-md-offset-1">
								<div id="infoEvento"></div>
							</div>
							</div>	
							
							<div class="form-group">	
							<div class="col-md-10 col-md-offset-1">	
								<div id="cuotasAModificar"></div>
							</div>
							</div>

						</div>
						</div>
				<!--  Termina panel para modificar evento -->
				
				
				<!--  Empieza panel para eliminar evento -->	
							<div class="panel panel-danger">
							<div class="panel-heading">
								<h3 class="panel-title">Eliminación de eventos:</h3>
							</div>
							<div class="container-fluid">
							
							<form:form class="form-horizontal" role="form" action="eliminar-Evento" method="post" name="eliminarEvento" modelAttribute="evento">

									<div class="form-group"></div>

									<div class="form-group">
										<div class="col-md-10 col-md-offset-1">
											<div class="input-group center">
												<span class="input-group-addon">Evento a eliminar:
												</span> <select name="eventoParaEliminar" class="form-control" required="required">

													<c:forEach items="${eventos}" var="e">
														<option value="" selected hidden>Elegir:</option>

														<option value="${e.id}">${e.id} - [${e.partido.local.nombre} vs ${e.partido.visitante.nombre}] - ${e.partido.fecha} - ${e.nombre}</option>
													</c:forEach>

												</select>
											</div>
										</div>
									</div>

							<div class="form-group">
								<div class="col-md-offset-4 col-md-8">
									<a data-toggle="modal" data-target="#delete" class="btn btn-danger">Eliminar evento seleccionado</a>
								</div>
							</div>
							</form:form>
							</div>
						</div>
				<!--  Termina panel para eliminar evento -->

				</div>
				</div>
			</div>
		</div>

		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" ></script>
		<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<script src="js/modificarCuotas.js" type="text/javascript"></script>
				<script src="js/crearEvento.js" type="text/javascript"></script>
		
</body>
</html>
