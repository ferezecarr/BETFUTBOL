 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <nav class="navbar navbar-default navegador">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand hoverNav" href="http://localhost:8080/proyecto-limpio-spring/index"><img src="img/bf.png" class="logo">
                </a>

            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                	<li class="hoverNav"><a href="http://localhost:8080/proyecto-limpio-spring/index">Inicio</a></li>
                    <li class="hoverNav"><a href="http://localhost:8080/proyecto-limpio-spring/mis-apuestas">Mis Apuestas</a></li>
                    <li class="hoverNav"><a href="http://localhost:8080/proyecto-limpio-spring/ranking">Ranking de apostadores</a></li>
                    
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        
                      <c:if test="${not empty userLogin}">
   						<span>${nombre}</span>
   					</c:if>
   					
                     <c:if test="${empty userLogin}">
   					<span>Entrar</span>
   					</c:if>
                        
                        <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><c:if test="${not empty userLogin}">
		   						<a href="/proyecto-limpio-spring/cerrarlogin" >salir</a>
		   						</c:if></li>
		   						
                            <li><c:if test="${empty nombre}"><a href="#" data-toggle="modal" data-target="#modalLogin">Login</a></c:if></li>
                            
                            <li><a href="#" data-toggle="modal" data-target="#modalRegistro">Registrarse</a></li>
                        </ul>
                    </li>
                </ul>
                
                
                
                
                
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
