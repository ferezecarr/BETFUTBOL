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
  <%@include file="includes/navegador.jsp"%>
 <%@ include file="includes/modales.jsp"%> 

	<div class="container-fluid">
		<div class="col-md-12 main">
			<div class="row">				
				<div class="col-md-6 col-md-offset-3">
		 			<ol class="breadcrumb">
	          			<li class="breadcrumb-item active">Ranking de Apostadores - Seleccione el ranking que desea ver:</li>
	     			</ol>
	     			</div>		
	     			</div>
	     			
	     			<div class="rc">
	     				<div class="recuadro">
	     						<c:url value="/ranking" var="url"/>
 								<a href="<c:out value='${url}'/>" class="btn btn-default b">General</a>
 								
								<c:url value="/ranking?filtro=Resultado" var="url"/>
 								<a href="<c:out value='${url}'/>" class="btn btn-default b">Resultado</a>
 								
								<c:url value="/ranking?filtro=Cuantos goles hace un equipo" var="url"/>
 								<a href="<c:out value='${url}'/>" class="btn btn-default b">Cuantos goles hace un equipo</a> 
 								
								<c:url value="/ranking?filtro=Cantidad de goles en un partido" var="url"/>
 								<a href="<c:out value='${url}'/>" class="btn btn-default b">Cantidad de goles en un partido</a>
 								
								<c:url value="/ranking?filtro=Cantidad de goles par o impar" var="url"/>
 								<a href="<c:out value='${url}'/>" class="btn btn-default b">Cantidad de goles par o impar</a> 								
								 								 								 																 																 	          				
	          			</li>

					
	     			</div>
	     			<div class="recuadro2">
	     				<div class="panel panel-primary">
						<div class="panel-heading text-center">
							Top 5<br/>														
						</div>
						<div class="panel-body text-center">
	     				<c:forEach items="${ranking}" var="r">
								<p class="text-info">
									Usuario: <c:out value="${r.usuario}"/>, 
									Cantidad ganada: <c:out value="${r.ganancia}"/>
								</p>
						
					</c:forEach>
						</div>
					</div>
	     			</div>					
					</div>						
		
		</div>	
	</div>
</body>
</html>