<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   		
   <div class="modal fade in" id="add" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">

								<div class="modal-header">
									<a class="btn pull-right" data-dismiss="modal"><span
										class="glyphicon glyphicon-remove"></span></a>
									<h3 class="modal-title">Crear nuevo partido:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que sea crear un nuevo partido? Se guardarán los datos ingresados.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button name="enviar" class="btn btn-success" onclick="CrearPartido()">
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
									<h3 class="modal-title">Modificar partido seleccionado:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que desea modificar este partido?</h4>
									<h4>Los eventos y cuotas que contengan este partido podran ser afectados por el cambio.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button name="enviar" class="btn btn-warning" onclick="ModificarPartido()">
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
									<h3 class="modal-title">Eliminar partido seleccionado:</h3>
								</div>
								<div class="modal-body">
									<h4>¿Está seguro que desea eliminar este partido?</h4>
									<h4>Los eventos y cuotas que contengan este partido tambien serán eliminados.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button type="submit" name="enviar" class="btn btn-danger"  onclick="EliminarPartido()">
											<span class="glyphicon glyphicon-trash"></span> Eliminar
										</button>

									</div>
								</div>
							</div>
						</div>
					</div>					