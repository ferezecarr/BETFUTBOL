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
			            <div class="panel-heading text-center">${e.partido.local.nombre} - ${e.partido.visitante.nombre}</div>
			            <div class="panel-body text-center">
			            <p id="descripcion">${e.descripcion}</p>
			          	<c:forEach items="${e.cuotas}" var="c">
					            <a data-toggle="modal" eventoId="${e.id}" name="${c.nombre}" value="${c.valor}" onclick="pickOption(this)" href="#myModal" class="btn btn-default">
					              <span class="glyphicon glyphicon"></span>
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
	
	<div class="container">
  <div class="row">

    <div id="myModal" class="modal fade in">
      <div class="modal-dialog">
        <div class="modal-content">

          <div class="modal-header">
            <a class="btn" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></a>
            <h3 class="modal-title">Confirmación de apuesta</h3>
          </div>
          <div class="modal-body">
            <h4>aa</h4>
          </div>
          <div class="modal-footer">
            <div class="form-group text-center">
	            <form:form action="procesar-apuesta" method="POST" modelAttribute="apuesta">	
					<form:hidden path="cuotaNombre" id="cuotaNombre"/>
					<form:hidden path="cuotaValor" id="cuotaValor"/>
					<form:hidden path="evento.id" id="evento.id"/>
					<form:input path="cantidadApostada"/>
					<button class="btn btn-lg btn-primary btn-block"  name="Submit" value="Confirmar" type="Submit">Confirmar</button> 
					<!-- 
					<button class="btn btn-success" data-dismiss="modal" name="Submit" type="Submit" data-toggle="page-alert" data-delay="5000" data-toggle-id="10"><span class="glyphicon glyphicon-ok"></span> Confirmar</button>
					-->
				</form:form>              
            </div>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dalog -->
    </div><!-- /.modal -->
  </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="./stylesheets/bootstrap/js/bootstrap.min.js"></script>




<script>
function pickOption(opcion){	
	document.getElementById("cuotaNombre").value = opcion.getAttribute("name");
	document.getElementById("cuotaValor").value = opcion.getAttribute("value");
	document.getElementById("evento.id").value = opcion.getAttribute("eventoId");
}
</script>
</body>
</html>
