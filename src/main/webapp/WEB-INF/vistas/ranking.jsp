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
		<div class="col-md-8 col-md-offset-2">
			<div class="row">				
		 			<ol class="breadcrumb text-center">
	          			<li class="breadcrumb-item active">Ranking de Apostadores - Seleccione el ranking que desea ver:</li>
	     			</ol>
	     		     			
	     			<div class="contenedorCentarl">
	     				<div class="recuadro">
	     						<c:url value="/ranking" var="url"/>
 								<a href="<c:out value='${url}'/>" class="btn btn-default botonesFiltro">General</a>
 								
								<c:url value="/ranking?filtro=Resultado" var="url"/>
 								<a href="<c:out value='${url}'/>" class="btn btn-default botonesFiltro">Resultado específico</a>
 								
								<c:url value="/ranking?filtro=Cantidad de goles en un partido" var="url"/>
 								<a href="<c:out value='${url}'/>" class="btn btn-default botonesFiltro">Cantidad de goles en un partido</a>
 								
								<c:url value="/ranking?filtro=Cantidad de goles par o impar" var="url"/>
 								<a href="<c:out value='${url}'/>" class="btn btn-default botonesFiltro">Cantidad de goles (Par o Impar)</a> 								
								
	     			</div>
	     			<div class="recuadro2">
	     				<div class="panel panel-primary">
						<div class="panel-heading text-center">Top 5<br/></div>
						<div class="panel-body text-center">
											
						<div class="table-responsive">
								<table class="table table-hover">
									<thead>		
										<tr>
											<th class="text-center">Usuario</th>
											<th class="text-center">Ganancia</th>												
										</tr>
										</thead>
										<c:forEach  items="${ranking}" var="r">
											<tbody class="text-center">
												<td>${r.usuario}</td>
												<td>${r.ganancia}</td>												
											</tbody>
										</c:forEach>
								</table>
						</div>
						</div>
					</div>
	     		</div>					
				</div>		
				</div>
									
		</div>	
	</div>
</body>
</html>