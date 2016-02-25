<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uo.sdi.acciones.ListarViajesAction"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<% ListarViajesAction action = new ListarViajesAction();
action.execute(request, response);%>
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
						<a href="#"><img src="img/logo.png">Share My Trip</a>
					</div>
					<ul class="horizontalMenu_links">
						<li class="activeLink"><a href="#"><i
								class="fa fa-2x fa-car"></i>Viajes</a></li>
						<li><a href="#"><i class="fa fa-2x fa-users"></i>Mis
								Viajes</a></li>
						<li><a href="#"><i class="fa fa-2x fa-plus-circle"></i>Crear
								Viaje</a></li>
					</ul>
					<jsp:include page="snippets/menu.jsp" />
					<div class="clear"></div>
				</div>
			</nav>
		</header>
		<jsp:include page="snippets/messages.jsp"></jsp:include>
		<main>
		<h1>Viajes Disponibles</h1>
		<p>¡Bienvenido! En esta tabla encontrará las mejores ofertas con
			los mejores destinos. ¿Por qué pagar mas cuando puedes pagar menos?</p>
		<table class="table" id="dataTable">
			<thead>
				<tr>
					<th>Origen</th>
					<th>Destino</th>
					<th>Fecha de salida</th>
					<th>Fecha de llegada</th>
					<th>Fecha límite</th>
					<th>Plazas libres</th>
					<th>Ver</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="viaje" items="${listaViajes}">
					<tr>
						<td>${viaje.departure.city}</td>
						<td>${viaje.destination.city}</td>
						<td>${viaje.departureDate}</td>
						<td>${viaje.arrivalDate}</td>
						<td>${viaje.closingDate}</td>
						<td>${viaje.availablePax}</td>
						<td><a href="#">Ver</a></td>
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
</div>
</html>