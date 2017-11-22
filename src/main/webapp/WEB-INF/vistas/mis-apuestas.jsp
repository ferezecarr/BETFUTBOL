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
					<c:forEach items="${apuestas}" var="a">
 			         <div class="panel panel-primary">
			            <div class="panel-heading text-center">Evento apostado: ${a.evento.partido.local.nombre} - ${a.evento.partido.visitante.nombre} </div>
			            <div class="panel-body text-center"> 
			             <h4 id="descripcion"> ${a.evento.descripcion}</h4>
			             <p id="descripcion"> Tipo de apuesta: ${a.evento.nombre}</p>
			             <p id="descripcion"> Usted apostó por: ${a.cuotaNombre}</p>
			             <p id="descripcion"> Dinero apostado: $${a.cantidadApostada}</p>
			             <p id="descripcion"> Valor de la cuota: ${a.cuotaValor}</p>

 			          </div> 
 			        </div> 
					</c:forEach>
				</div>
				
			</div>
		</div>	
		</div>
	


</body>
</html>
