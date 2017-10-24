<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html lang="en">
    <head> 
		<meta name="viewport" content="width=device-width, initial-scale=1">


		<!-- Website CSS style -->
		<link href="css/bootstrap.min.css" rel="stylesheet">

		<!-- Website Font style -->
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
		<link rel="stylesheet" href="style.css">
		<!-- Google Fonts -->
		<link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
		<link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>

		<title>Registrar Usuario</title>
	</head>
	<body>
		<div class="container">
			<div class="row main">
				<div class="main-login main-center">
				<h1>Registrar Usuario.</h1>
					<form:form action="registrar-usuario" method="POST" modelAttribute="usuario">
					


<!--         <label for="email">Ingresar Correo Electronico</label> -->
<%--         <form:input path="email" id="email" type="email" class="form-control"/> --%>

<!--         <br> -->
<!--         <label for="password">Ingresar Contraseña</label> -->
<%--         <form:input path="password" type="password" id="password" class="form-control"/> --%>

<!--         <br> -->

<!--         <div class="form-group col-lg-12"> -->
<!--             <button type="submit" class="btn btn-success">Registrar</button> -->
<!--         </div> -->
						
						<div class="form-group">
							<label for="name" class="cols-sm-2 control-label">Su Nombre</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
									
									
									<input type="text" class="form-control" name="nombre" id="nombre"  placeholder="Ingrese su nombre"/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="username" class="cols-sm-2 control-label">Su Apellido</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="apellido" id="apellido"  placeholder="Ingrese su apellido"/>
								</div>
							</div>
						</div>


						<div class="form-group">
							<label for="email" class="cols-sm-2 control-label">Su Email</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
									<input type="text" class="form-control" name="email" id="email"  placeholder="Ingrese su Email"/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="password" class="cols-sm-2 control-label">Password</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input type="password" class="form-control" name="password" id="password"  placeholder="Ingrese Su Password"/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="confirm" class="cols-sm-2 control-label">Confirm Password</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
									<input type="password" class="form-control" name="confirm" id="confirm"  placeholder="Confirme su Password"/>
								</div>
							</div>
						</div>

						<div class="form-group ">
<!-- 							<a href="http://deepak646.blogspot.in" target="_blank" type="button" id="button" class="btn btn-primary btn-lg btn-block login-button">Register</a> -->
						 <button type="submit" class="btn btn-success">Registrar</button>
						</div>
<!--             <button type="submit" class="btn btn-success">Registrar</button> -->						
					</form:form> 
				</div>
			</div>
		</div>

		 <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	</body>
</html>




<!-- <html> -->
<!-- <head> -->
<!--     <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!--     <title>Registro Usuario</title> -->
<!-- </head> -->
<!-- <body> -->


<!-- <div> -->
<%--     <form:form action="registrar-usuario" method="POST" modelAttribute="usuario"> --%>

<!--         <label for="nombre">Ingresar Nombre</label> -->
<%--         <form:input path="nombre" id="nombre" type="text" class="form-control"/> --%>
<!--         <br> -->

<!--         <label for="apellido">Ingresar Apellido</label> -->
<%--         <form:input path="apellido" id="apellido" type="text" class="form-control"/> --%>
<!--         <br> -->

<!--         <label for="email">Ingresar Correo Electronico</label> -->
<%--         <form:input path="email" id="email" type="email" class="form-control"/> --%>

<!--         <br> -->
<!--         <label for="password">Ingresar Contraseña</label> -->
<%--         <form:input path="password" type="password" id="password" class="form-control"/> --%>

<!--         <br> -->

<!--         <div class="form-group col-lg-12"> -->
<!--             <button type="submit" class="btn btn-success">Registrar</button> -->
<!--         </div> -->

<!--         <br> -->

<%--     </form:form> --%>
<!-- </div> -->

<!-- </body> -->
<!-- </html> -->

