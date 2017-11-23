<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<div class="modal fade in" id="add" role="dialog">
						<div class="modal-dialog" role="document">
							<div class="modal-content">

								<div class="modal-header">
									<a class="btn pull-right" data-dismiss="modal"><span
										class="glyphicon glyphicon-remove"></span></a>
									<h3 class="modal-title">Crear nuevo evento:</h3>
								</div>
								<div class="modal-body">
									<h4>�Est� seguro que sea a�adir un nuevo evento?</h4>
									<h4> Se guardar�n los datos ingresados.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button name="enviar" class="btn btn-success" onclick="CrearEvento()">
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
									<h3 class="modal-title">Eliminar evento seleccionado:</h3>
								</div>
								<div class="modal-body">
									<h4>�Est� seguro que desea eliminar este evento?</h4>
									<h4>Las cuotas que contengan este evento tambien ser�n eliminados.</h4>
								</div>
								<div class="modal-footer">
									<div class="form-group text-center">

										<button type="submit" name="enviar" class="btn btn-danger"  onclick="EliminarEvento()">
											<span class="glyphicon glyphicon-trash"></span> Eliminar
										</button>

									</div>
								</div>
							</div>
						</div>
					</div>	