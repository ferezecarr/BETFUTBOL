<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   		
   
	<div class="modal fade in" id="add" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">

								<div class="modal-header">
									<a class="btn pull-right" data-dismiss="modal"><span
										class="glyphicon glyphicon-remove"></span></a>
									<h3 class="modal-title">Crear nuevo equipo:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que sea añadir un nuevo equipo? Se guardarán los datos ingresados.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button name="enviar" class="btn btn-success" onclick="CrearEquipo()">
											<span class="glyphicon glyphicon-check"></span> Confirmar
										</button>

									</div>
								</div>

							</div>
						</div>
					</div>
   	
   	
   	
   	
   		<div class="modal fade in" id="update" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">

								<div class="modal-header">
									<a class="btn pull-right" data-dismiss="modal"><span
										class="glyphicon glyphicon-remove"></span></a>
									<h3 class="modal-title">Modicar equipo existente:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que modificar el equipo? Se perderán datos anteriores.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button  onclick="ModificarEquipo()" name="enviar" class="btn btn-warning">
											<span class="glyphicon glyphicon-check"></span> Confirmar
										</button>

									</div>
								</div>

							</div>
						</div>
					</div>
					
					
					
			<div class="modal fade in" id="delete" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">

								<div class="modal-header">
									<a class="btn pull-right" data-dismiss="modal"><span
										class="glyphicon glyphicon-remove"></span></a>
									<h3 class="modal-title">Eliminar equipo seleccionado:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que desea eliminar el equipo? Se perderán todos sus datos.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button  name="enviar" class="btn btn-danger" onclick="EliminarEquipo()">
											<span class="glyphicon glyphicon-trash"></span> Eliminar
										</button>

									</div>
								</div>
							</div>
						</div>
					</div>					
