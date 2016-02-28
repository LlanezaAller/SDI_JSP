<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ver viaje</title>
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
						<a href="#"><img src="img/logo.png">Share My Trip</a>
					</div>
					<ul class="horizontalMenu_links">
						<li><a href="listarViajes"><i
								class="fa fa-2x fa-car"></i>Viajes</a></li>
						<li><a href="listarMisViajes"><i class="fa fa-2x fa-users"></i>Mis
								Viajes</a></li>
						<li><a href="crearViaje.jsp"><i class="fa fa-2x fa-plus-circle"></i>Crear
								Viaje</a></li>
					</ul>
					<jsp:include page="snippets/menu.jsp" />
					<div class="clear"></div>
				</div>
			</nav>
		</header>
		<jsp:include page="snippets/messages.jsp"></jsp:include>
		<main>
		<h1>
			Viaje a <span class="boldColored">${viaje.destination.city}</span>
			por <span class="boldColored">${viaje.promoter.login}</span>
		</h1>
		<table class="table">
			<thead>
				<tr>
					<th>Valoración</th>
					<th>Comentario</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="r" items="${listaRatings}">
					<tr>
						<td>${r.value}</td>
						<td>${r.comment}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		</main>
		<footer> </footer>
	</div>
	<script type="text/javascript"
		src="https://cdn.datatables.net/t/dt/jq-2.2.0,dt-1.10.11/datatables.min.js"></script>
	<script type="text/javascript" src="js/datatables.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
</body>

</html>