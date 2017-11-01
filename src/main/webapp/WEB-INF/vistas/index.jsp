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
		 			<ol class="breadcrumb">
	          			<li class="breadcrumb-item active">Partidos de la semana</li>
	         			<li class="breadcrumb-item active">Apueste al equipo ganador o por un empate entre ambos.</li>
	     			</ol>
					<c:forEach items="${evento}" var="e">
			         <div class="panel panel-primary">
			            <div class="panel-heading text-center">${e.partido.local.nombre} Vs.${e.partido.visitante.nombre} ${e.descripcion}</div>
			            <div class="panel-body text-center">
			          	<c:forEach items="${e.cuotas}" var="c">
					            <a href="" class="btn btn-default">
					              <span class="glyphicon glyphicon-th-large"></span>
					              	${c.nombre}: ${c.valor} 
					            </a>  
			       		</c:forEach>
			          </div>
			        </div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>