
   		
   
    <form:form action="registro-usuario" method="POST" modelAttribute="registroUsuario">
	<div class="modal fade" data-backdrop="static" id="modalRegistro" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content col-sm-12">
	      <div class="modal-header">
	        <h2 class="modal-title" id="exampleModalLabel">Registro</h2>
	      
	      </div>
	      <div class="modal-body col-sm-12">
   	        <input path="nombreYApellido" type="text" class="col-sm-6 form-control" placeholder="Nombre y Apellido" ><br>
	        <input path="email" type="email" class="col-sm-6 form-control" placeholder="E-mail" ><br>
	        <input path="password" type="password" class="col-sm-6 form-control" placeholder="Password" ><br>
	        <input path="password" type="password" class="col-sm-6 form-control" placeholder="Repita Password" ><br>
	      </div>
	      <div class="modal-footer col-sm-12">
	        <input type="button" class="btn btn-primary" data-dismiss="modal" value="Cancelar">
	        <input type="submit" class="btn btn-success" value="Enviar">
	      </div>
	    </div>
	  </div>
	</div>
   	</form:form>
   	
   	<c:if test="${not empty error}">
   		<h4><span>${error}</span></h4>
   	</c:if>



    <form:form action="index" method="POST" modelAttribute="usuario">
	<div class="modal fade" data-backdrop="static" id="modalLogin" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content col-sm-12">
	      <div class="modal-header">
	        <h2 class="modal-title" id="exampleModalLabel">Iniciar sesión</h2>
	        
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
   	
   	<c:if test="${not empty error}">
   		<h4><span>${error}</span></h4>
   	</c:if>
   	
   	
   	
