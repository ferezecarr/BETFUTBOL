<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<%@ include file="includes/cabecera.jsp"%>
	<!-- Bootstrap core CSS -->
	<link href="css/bootstrap.min.css" rel="stylesheet" >
	<link href="css/estilos.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="css/bootstrap-theme.min.css" rel="stylesheet">  
	<script type="text/javascript" src="js/js.js"></script>   
</head>
<body>
	<%@ include file="includes/navegador.jsp"%>
	<%@ include file="includes/modales.jsp"%>
	<div class="container-fluid">
		<div class="col-md-12 main">
			<div class="row">
				No se pudo completar la solicitud
			</div>
		</div> 
		<button onclick="goBack()">Volver a la página principal</button>
	</div>
</body>
</html>