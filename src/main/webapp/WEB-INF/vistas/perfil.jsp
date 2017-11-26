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
	

		<div class="col-md-6 col-md-offset-3">
			<div class="row">
			
							<div class="panel panel-primary">
							<div class="panel-heading text-center">Mi perfil</div>
							<div class="panel-body text-center">
								
							<div class="container-fluid">

							<form class="form-horizontal" role="form" action="actualizar-perfil" method="post" modelAttribute="usuario">
	
								<div class="form-group"></div>
							
								<div class="form-group">
									<div class="col-md-8 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon">Nombre</span> 
											<form:input type="text" value="${nombre}" path="nombre" class="form-control" />				
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-8 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon">Email</span> 
											<form:input type="text" value="${email}" path="email" class="form-control" />				
										</div>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-8 col-md-offset-2">
										<div class="input-group">
											<span class="input-group-addon">Contraseña</span> 
											<form:input type="password" value="${password}" path="password" class="form-control" />				
										</div>
									</div>
								</div>
								
							<div class="form-group">
							<div class="col-md-8 col-md-offset-2">
								<a type="submit" data-toggle="modal" data-target="#cambiarDatos" class="btn btn-success">Guardar cambios</a>
							</div>
						</div>

						</form>
						 
							</div>
							

						</div>
			
			</div>
		</div>
		</div>

</body>
</html>