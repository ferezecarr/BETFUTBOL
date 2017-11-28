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
	<%@ include file="includes/modal-Apuesta.jsp"%>

	<div class="container-fluid">
		<div class="col-md-12 main">
			<div class="row">
				<c:if test="${not empty aviso}">
					<ol class="breadcrumb text-center ">
						<li class="breadcrumb-item text-success "><h2>${aviso}</h2></li>
					</ol>
				</c:if>				
			</div>						
				<div class="col-md-8 col-md-offset-2">
					<ol class="breadcrumb text-center">
						<li class="breadcrumb-item active">Partidos disponibles para apostar - Seleccione la categoría que desea ver:</li>
					</ol>
										
					<c:url value="/index?filtro=Resultado" var="url" />
					<a href="<c:out value='${url}'/>" class="btn btn-default botonesFiltro">Resultado específico (Ganador o empate)</a>

					<c:url value="/index?filtro=Cantidad de goles en un partido" var="url" />
					<a href="<c:out value='${url}'/>" class="btn btn-default botonesFiltro">Cantidad de goles en un partido (0 a +4)</a>
					
					<c:url value="/index?filtro=Cantidad de goles par o impar" var="url" />
					<a href="<c:out value='${url}'/>" class="btn btn-default botonesFiltro">Cantidad de goles en un partido (Par o Impar)</a>
					
					<c:forEach items="${eventos}" var="e">
						<div class="panel panel-primary primario">
							<div class="panel-heading text-center">${e.partido.local.nombre} - ${e.partido.visitante.nombre}</div>
							<div class="panel-body text-center">
								<p id="descripcion">${e.descripcion}</p>
								<c:forEach items="${e.cuotas}" var="c">
									<a style="text-decoration: none"> ${c.nombre}: <c:choose>
										<c:when test="${userId != null}">
											<a data-toggle="modal" onclick="pickOption(this)" eventoId="${e.id}" name="${c.nombre}" value="${c.valor}" href="#myModal" class="btn btn-success"> ${c.valor} </a>
										</c:when>
										<c:otherwise>
											<a data-toggle="modal" onclick="pickOption(this)"  href="#myModalApostar" class="btn btn-success"> ${c.valor} </a>
										</c:otherwise>
										</c:choose> 
									</a>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
