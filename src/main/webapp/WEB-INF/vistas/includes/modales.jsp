    <form action="Registro-usuario" method="POST" modelAttribute="registroUsuario">
	<div class="modal fade" data-backdrop="static" id="modalRegistro" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content col-sm-12">
	      <div class="modal-header">
	        <h2 class="modal-title" id="exampleModalLabel">Registro</h2>
	      
	      </div>
	      <div class="modal-body col-sm-12">
   	        <input path="nombreYApellido" type="text" class="col-sm-6 form-control" placeholder="Nombre y Apellido" required><br>
	        <input path="email" type="email" class="col-sm-6 form-control" placeholder="E-mail" required><br>
	        <input path="password" type="password" class="col-sm-6 form-control" placeholder="Password" required><br>
	        <input path="password" type="password" class="col-sm-6 form-control" placeholder="Repita Password" required><br>
	      </div>
	      <div class="modal-footer col-sm-12">
	        <input type="button" class="btn btn-primary" data-dismiss="modal" value="Cancelar">
	        <input type="submit" class="btn btn-success" value="Cargar">
	      </div>
	    </div>
	  </div>
	</div>
   	</form>



    <form action="/validar-login" method="POST" modelAttribute="usuario">
	<div class="modal fade" data-backdrop="static" id="modalLogin" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content col-sm-12">
	      <div class="modal-header">
	        <h2 class="modal-title" id="exampleModalLabel">Login</h2>
	        
	      </div>
	     
	      <div class="modal-body col-sm-12">
	        <input path="email" type="email" class="col-sm-6 form-control" placeholder="E-mail" required/>
	        <br><br/>
	        <input path="password" type="password" class="col-sm-6 form-control" placeholder="Password" required/><br>
	      </div>
	      
	      <div class="modal-footer col-sm-12">
	        <input type="button" class="btn btn-primary" data-dismiss="modal" value="Cancelar">
	        <input type="submit" class="btn btn-success" value="Cargar">
	      </div>
	    </div>
	  </div>
	</div>
   	</form>
