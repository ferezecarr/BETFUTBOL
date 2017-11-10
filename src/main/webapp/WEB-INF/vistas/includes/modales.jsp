
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



    <form:form action="validar-login" method="POST" modelAttribute="usuario">
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
   	
   	
   	
