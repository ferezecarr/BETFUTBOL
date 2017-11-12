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
<%-- 
<%@ include file="includes/navegador.jsp"%>
<%@ include file="includes/modales.jsp"%>
--%>
	<div class="container-fluid">
		<div class="col-md-12 main">
			<div class="row">				
				<div class="col-md-8 col-md-offset-2">
		 			<ol class="breadcrumb">
	          			<li class="breadcrumb-item active">Rankings</li>
	     			</ol>
					<c:forEach items="${ranking}" var="r">
						<div class="panel panel-primary">
							<div class="panel-heading text-center">
								Top 5
							</div>
							<div class="panel-body text-center">
								<p class="text-info">
									Usuario: <c:out value="${r.usuario}"/>, 
									Cantidad ganada: <c:out value="${r.ganancia}"/>
								</p>
							</div>
						</div>
					</c:forEach>
				</div>				
			</div>
		</div>	
	</div>
</body>
</html>