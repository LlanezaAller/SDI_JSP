<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uo.sdi.acciones.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	if (request.getAttribute("listaViajes") == null) {
		ActionExecuter executor = new ActionExecuter();
		ListarViajesAction action = new ListarViajesAction();
		executor.execute(action, request, response);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Viajes</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/t/dt/jq-2.2.0,dt-1.10.11/datatables.min.css" />
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="wrapper">
		<header>
			<nav class="horizontalMenu">
				<div class="horizontalMenuInnerWrapper">
					<div class="menuLogo">
						<a href="listarViajes"><img src="img/logo.png">Share My
							Trip</a>
					</div>
					<ul class="horizontalMenu_links">
						<li class="activeLink"><a href="listarViajes"><i
								class="fa fa-2x fa-car"></i>Viajes</a></li>
						<li><a href="listarMisViajes"><i
								class="fa fa-2x fa-users"></i>Mis Viajes</a></li>
						<li><a href="crearViaje.jsp"><i
								class="fa fa-2x fa-plus-circle"></i>Crear Viaje</a></li>
					</ul>
					<jsp:include page="snippets/menu.jsp" />
					<div class="clear"></div>
				</div>
			</nav>
		</header>
		<jsp:include page="snippets/messages.jsp"></jsp:include>
		<main>
		<h1>Editar Perfil</h1>
		<form method="POST" action="modificarPerfil" class="formDark">
			<div class="inputLine">
				<label class="inputIcon" for="name"><i
					class="fa fa-list-alt fa-2x"></i></label> <input type="text" name="name"
					id="name" class="textInput textInputWithIcon" placeholder="Nombre"
					value="${user.name}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="surname"><i
					class="fa fa-list-alt fa-2x"></i></label> <input type="text" name="surname"
					id="surname" class="textInput textInputWithIcon"
					placeholder="Apellidos" value="${user.surname}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="email"><i
					class="fa fa-envelope-o fa-2x"></i></label> <input type="email"
					name="email" id="email" class="textInput textInputWithIcon"
					placeholder="Email" value="${user.email}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="password"><i
					class="fa fa-lock fa-2x"></i></label> <input type="password"
					name="password" id="password" class="textInput textInputWithIcon"
					placeholder="Nueva contraseña">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="repeatPassword"><i
					class="fa fa-lock fa-2x"></i></label> <input type="password"
					name="repeatPassword" id="repeatPassword"
					class="textInput textInputWithIcon"
					placeholder="Repita su nueva contraseña">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="oldPassword"><i
					class="fa fa-lock fa-2x"></i></label> <input type="password"
					name="oldPassword" id="oldPassword"
					class="textInput textInputWithIcon"
					placeholder="Contraseña antigua">
			</div>
			<div class="inputLine">
				<input type="submit" value="Editar perfil" class="button">
			</div>
		</form>
		</main>
		<footer> </footer>
	</div>

	<script type="text/javascript"
		src="https://cdn.datatables.net/t/dt/jq-2.2.0,dt-1.10.11/datatables.min.js"></script>
	<script type="text/javascript" src="js/datatables.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
</body>
</div>
</html>