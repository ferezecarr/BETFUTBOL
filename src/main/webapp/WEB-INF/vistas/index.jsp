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
	
	<div class="container">
  <div class="row">

    <div id="myModal" class="modal fade in">
      <div class="modal-dialog">
        <div class="modal-content">

          <div class="modal-header">
            <h3 class="modal-title">Apuesta en curso</h3>
          </div>
          <div class="modal-body">
            <h5 id="descripcion">Indique la cantidad de dinero que sea apostar.</h5>
          </div>
          <div class="modal-footer">
            <div class="form-group text-center">
	            <form:form action="procesar-apuesta" method="POST" modelAttribute="apuesta">	
					<form:hidden path="cuotaNombre" class="text-info"/>
					<form:hidden path="cuotaValor" class="text-info"/>
					<form:hidden path="evento.id" class="text-info"/>
					
					<div class="col-md-5">
   					<div class="input-group">
       				<span class="input-group-addon">AR$</span>
    				<form:input class="form-control" path="cantidadApostada"/>
 					</div>
					</div>

					<button class="btn btn-success" name="submit" value="Confirmar" type="Submit" data-toggle="page-alert" data-delay="5000" data-toggle-id="10">Apostar</button>
					<button class="btn btn-default"  data-dismiss="modal" value="Cancelar" type="Submit">Cancelar</button> 
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
<script src="js/seleccionApuesta.js"></script>
</body>
</html>
