<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="es">
<head><%@ include file="includes/cabecera.jsp"%>
</head>
<body>

	<%@ include file="includes/navegador.jsp"%>
	<%@ include file="includes/modales.jsp"%>
	

		
			<div class="row">
			<div class="col-md-6 col-md-offset-3">
			<c:if test="${not empty aviso}">
				<ol class="breadcrumb text-center ">
						<li class="breadcrumb-item text-success "><h2>${aviso}</h2></li>
					</ol>
			</c:if>
							<div class="panel panel-primary">
							<div class="panel-heading text-center">Mi perfil</div>
							<div class="panel-body text-center">
								
							<div class="container-fluid">

							<form:form class="form-horizontal" role="form" action="actualizar-perfil" method="post" modelAttribute="usuario">
							<form:input type="hidden" value="${id}" path="id" class="form-control" />	
								<div class="form-group"></div>
							
								<div class="form-group">
									<div class="col-md-8 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon">Nombre y Apellido</span> 
											<form:input type="text" value="${nombreYApellido}" path="nombreYApellido" class="form-control" required="required" placeholder="Ingrese su nombre y apellido." />				
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-8 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon">Email</span> 
											<form:input type="email" value="${email}" path="email" class="form-control" required="required" placeholder="Ingrese su email. Por ejemplo: (usuario@correo.com)"/>				
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-8 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon">Contraseña</span> 
											<form:input type="password" value="${password}" path="password" class="form-control" required="required" placeholder="Ingrese su contraseña."/>				
										</div>
									</div>
								</div>
								
							<div class="form-group">
							<div class="col-md-8 col-md-offset-2">
								<button type="submit" data-toggle="modal" data-target="#cambiarDatos" class="btn btn-success">Guardar cambios</button>
							</div>
						</div>

						</form:form>
						 
							</div>
							

						</div>
			
			</div>
		</div>
		</div>

</body>
</html>