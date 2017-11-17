<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   		
   
   
   
   
    <form:form action="registro-usuario" method="POST" modelAttribute="usuario"  name="f1">
	<div class="modal fade" data-backdrop="static" id="modalRegistro" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content col-sm-12">
	      <div class="modal-header">
	        <h2 class="modal-title" id="exampleModalLabel">Registro</h2>
	      
	      </div>
	      <div class="modal-body col-sm-12">
   	        <form:input path="nombreYApellido" type="text" class="col-sm-6 form-control" placeholder="Nombre y Apellido" /><br>
	        <form:input path="email" type="email" class="col-sm-6 form-control" placeholder="E-mail"/><br>
	        <form:input path="password" type="password" class="col-sm-6 form-control" name="clave1" placeholder="Password"/><br>
	      <!-- <form:input path="password" type="password" class="col-sm-6 form-control" name="clave2" placeholder="Repita Password"/><br> -->  
	      </div>
	      <div class="modal-footer col-sm-12">
	        <button type="submit" class="btn btn-primary" data-dismiss="modal" >Cancelar</button>
	        <button type="submit" class="btn btn-success" onClick="comprobarClave()">enviar</button>
	      </div>
	    </div>
	  </div>
	</div>
   	</form:form>
   	
   	
   	
   	
   	
   	
   	
   	<c:if test="${not empty error}">
   		<h4><span>${error}</span></h4>
   	</c:if>
    <form:form action="login" method="POST" modelAttribute="usuario">
	<div class="modal fade" data-backdrop="static" id="myModalApostar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content col-sm-12">
	      <div class="modal-header">
	        <h2 class="modal-title" id="exampleModalLabel">Para poder apostar primero debe iniciar sesi�n</h2>
	        
	      </div>
	     
	      <div class="modal-body col-sm-12">
	      
	      
	        <form:input path="email" type="email" class="col-sm-6 form-control" placeholder="E-mail" />
	        <br><br/>
	        <form:input path="password" type="password" class="col-sm-6 form-control" placeholder="Password" /><br>
	      </div>
	      
	      <div class="modal-footer col-sm-12">
	        <button type="submit" class="btn btn-primary" data-dismiss="modal" value="Cancelar">Cancelar</button>
	        <button type="submit" class="btn btn-success" value="Enviar">Enviar</button>
	      </div>
	    </div>
	  </div>
	</div>
   	</form:form>


    <form:form action="login" method="POST" modelAttribute="usuario">
	<div class="modal fade" data-backdrop="static" id="modalLogin" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content col-sm-12">
	      <div class="modal-header">
	        <h2 class="modal-title" id="exampleModalLabel">Iniciar sesi�n</h2>
	        
	      </div>
	     
	      <div class="modal-body col-sm-12">
	      
	      
	        <form:input path="email" type="email" class="col-sm-6 form-control" placeholder="E-mail" />
	        <br><br/>
	        <form:input path="password" type="password" class="col-sm-6 form-control" placeholder="Password" /><br>
	      </div>
	      
	      <div class="modal-footer col-sm-12">
	        <button type="submit" class="btn btn-primary" data-dismiss="modal" value="Cancelar">Cancelar</button>
	        <button type="submit" class="btn btn-success" value="Enviar">Enviar</button>
	      </div>
	    </div>
	  </div>
	</div>
   	</form:form>
   	
   		<form:form action="logout" method="POST">
	<div class="modal fade" id="logout" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content col-sm-12">
	      <div class="modal-header col-sm-8 col-sm-offset-2">
	        <h2 class="modal-title" id="exampleModalLabel">�Desea delogearse?</h2>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	        </button>
		      </div>
	      <div class="modal-footer col-sm-12">
	        <button type="submit" class="btn btn-primary" data-dismiss="modal" value="Cancelar">Cancelar</button>
	        <button type="submit" class="btn btn-success" value="Enviar">Enviar</button>
	      </div>
	    </div>
	  </div>
	</div>
	</form:form>
	
   	<c:if test="${not empty error}">
   		<h4><span>${error}</span></h4>
   	</c:if>
   	
   	
   	
