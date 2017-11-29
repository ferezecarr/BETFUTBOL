<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   		
   <form:form action="registro-usuario" method="POST" modelAttribute="usuario"  name="formulario1" class="input-group" onsubmit="return validarRegistro()">
	<div class="modal fade" data-backdrop="static" id="modalRegistro" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	    	    <a class="btn pull-right" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></a>	    
	      <div class="modal-header">
	        <h2 class="modal-title" id="exampleModalLabel">Registro de nuevo usuario:</h2>  
	      </div>
	      <div class="modal-body">
	      	<div class="input-group">
		      	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
	   	        <form:input path="nombreYApellido" type="text" class="col-sm-6 form-control" placeholder="Nombre y Apellido" required="required" onkeypress="if (event.keyCode == 13) enviar_formulario()"/><br>
	        </div>
	        <div class="input-group" id="email2">
		      	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		        <form:input path="email" type="email" class="col-sm-6 form-control email" placeholder="E-mail" required="required" onkeypress="if (event.keyCode == 13) enviar_formulario()" onchange="validarEmail()"/><br>
	        </div>
	        <div class="input-group">
		        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		        <form:input path="password" type="password" class="col-sm-6 form-control" name="clave1" id="clave1" placeholder="Password" required="required" onkeypress="if (event.keyCode == 13) enviar_formulario()"/><br>
		    </div>
		    <div class="input-group">
		        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		        <form:input path="" type="password" class="col-sm-6 form-control" name="clave2" id="clave2" placeholder="Repita Password"/><br>
	      	</div>
	      </div>
	      <div class="modal-footer">
	         <div class="form-group text-center">		      	        
	       		 <button type="submit" class="btn btn-success" >Registrarse</button>
	        	 <button  class="btn btn-default" data-dismiss="modal" >Cancelar</button>
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
   	</form:form>
   	
   	 	
    <form:form action="login" method="POST" modelAttribute="usuario" name="formulario1">
	<div class="modal fade" id="myModalApostar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	    	   <a class="btn pull-right" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></a>	    
	      <div class="modal-header">
	        <h2 class="modal-title" id="exampleModalLabel">Apuesta en curso:</h2>	        
	      </div>     
	      <div class="modal-body text-center">
	      	        <h4>Para apostar primero debe iniciar sesión.</h4>	      		      	      	      
	        <div class="input-group">
		      	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		        <form:input path="email" type="email" class="form-control" placeholder="Ingrese E-mail" id="email" required="required" onkeypress="if (event.keyCode == 13) enviar_formulario()"/><br>
	        </div>
	        <div class="input-group">
		        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		        <form:input path="password" type="password" class="form-control" placeholder="Ingrese contraseña" required="required" onkeypress="if (event.keyCode == 13) enviar_formulario()"/><br>
		    </div>
	      </div>	      
	      <div class="modal-footer">
	      	<div class="form-group text-center">	       
	          <button type="submit" class="btn btn-primary" value="enviar">Iniciar sesión</button>
	      	</div>
	      </div>
	    </div>
	  </div>
	</div>
   	</form:form>


    <form:form action="login" method="POST" modelAttribute="usuario" name="formulario1">
	<div class="modal fade" data-backdrop="static" id="modalLogin" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	    	    <a class="btn pull-right" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></a>	    
	      <div class="modal-header">
	        <h2 class="modal-title" id="exampleModalLabel">Iniciar sesión:</h2>	        
	      </div>	     
	      <div class="modal-body text-center">
	            	      		      
	        <div class="input-group">
		      	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
		        <form:input path="email" type="email" class="col-sm-6 form-control" placeholder="Ingrese E-mail" id="email" required="required" onkeypress="if (event.keyCode == 13) enviar_formulario()"/><br>
	        </div>
	        <div class="input-group">
		        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
		        <form:input path="password" type="password" class="col-sm-6 form-control" placeholder="Ingrese contraseña" required="required" onkeypress="if (event.keyCode == 13) enviar_formulario()"/><br>
		    </div>
	      </div>	      
	      <div class="modal-footer">
	      	 <div class="form-group text-center">		      
	       		 <button type="submit" class="btn btn-primary" value="Enviar">Iniciar sesión</button>
	          	 <button  class="btn btn-default" data-dismiss="modal" value="Cancelar">Cancelar</button>
	         </div>
	      </div>
	    </div>
	  </div>
	</div>
   	</form:form>
   	
   <form:form action="logout" method="POST"  >
	<div class="modal fade" id="logout" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	    	<a class="btn pull-right" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span></a>	    
	      <div class="modal-header">	      
	        <h2 class="modal-title" id="exampleModalLabel">Cerrar sesión:</h2>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	        </button>
		   </div>
		   <div class="modal-body center">
        	<h4>¿Está seguro que desea salir de la sesión actual?.</h4>
     		 </div>
	      <div class="modal-footer">
	     	 <div class="form-group text-center">	       
	        <button type="submit" class="btn btn-success" value="Enviar">Aceptar</button>
	        <button data-dismiss="modal" class="btn btn-default" value="Enviar">Cancelar</button>	        
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
	</form:form>
	

   	
