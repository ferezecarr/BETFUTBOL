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
	
	<div class="container-fluid">
		<div class="col-md-12 main">
			<div class="row">
		
	   	
				<div class="col-md-8 col-md-offset-2">
		 			<ol class="breadcrumb text-center">
	          			<li class="breadcrumb-item active">Partidos de la semana</li>
	     			</ol>
	     			
	     			<ol class="breadcrumb text-center">
	     			<li class="breadcrumb-item active">Apueste al equipo ganador o por un empate entre ambos.</li>
	     			</ol>
	     			
					<c:forEach items="${evento_apostarPorGanadorEmpate}" var="e">
			         <div class="panel panel-primary">
			            <div class="panel-heading text-center">${e.partido.local.nombre} - ${e.partido.visitante.nombre}</div>
			            <div class="panel-body text-center">
			            <p id="descripcion"> ${e.descripcion}</p>
			          	<c:forEach items="${e.cuotas}" var="c">
			          	
					            <a style="text-decoration:none" >
					            <!--  se quita  onclick="pickOption(this)" del <a> para que no desaparesca el panel del partido seleccionado-->
					           	${c.nombre}: 
					           	
					           	<a data-toggle="modal" onclick="pickOption(this)" eventoId="${e.id}" name="${c.nombre}" value="${c.valor}" href="#myModal" class="btn btn-success">
					           	${c.valor}
					           	</a>
					           	-
					            </a>  
			       		</c:forEach>
			          </div>
			        </div>
					</c:forEach>
					
					
					<ol class="breadcrumb text-center">
	         			<li class="breadcrumb-item active">Apueste a la cantidad de goles que anote un equipo.</li>
	     			</ol>
				 <c:forEach items="${evento_apostarPorGoles}" var="e">
			         <div class="panel panel-primary">
			            <div class="panel-heading text-center">${e.partido.local.nombre} - ${e.partido.visitante.nombre}</div>
			            <div class="panel-body text-center">
			            <p id="descripcion"> ${e.descripcion}</p>
			          	<c:forEach items="${e.cuotas}" var="c">
			          	
					            <a style="text-decoration:none" >
					            <!--  se quita  onclick="pickOption(this)" del <a> para que no desaparesca el panel del partido seleccionado-->
					           	${c.nombre}: 
					           	
					           	<a data-toggle="modal" onclick="pickOption(this)" eventoId="${e.id}" name="${c.nombre}" value="${c.valor}" href="#myModal" class="btn btn-success">
					           	${c.valor}
					           	</a>
					           	-
					            </a>  
			       		</c:forEach>
			          </div>
			        </div>
					</c:forEach>
					
				</div>
				
			</div>
			
		</div>
	</div>
	


</body>
</html>
