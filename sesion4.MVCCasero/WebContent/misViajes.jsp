<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="snippets/comprobarNavegacion.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mis Viajes</title>
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
						<li><a href="listarViajes"><i class="fa fa-2x fa-car"></i>Viajes</a></li>
						<li class="activeLink"><a href="listarMisViajes"><i
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
		<h1>Mis Viajes</h1>
		<table class="table" id="dataTable">
			<thead>
				<tr>
					<th>Origen</th>
					<th>Destino</th>
					<th>Fecha</th>
					<th>Rol</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="trip" items="${listaApplications}">
					<tr>
						<td>${trip.departure.city}</td>
						<td>${trip.destination.city}</td>
						<td>${trip.departureDate}</td>
						<td><c:choose>
								<c:when test="${trip.closingDate.time > today.time}">
      Pendiente
    </c:when>
								<c:otherwise>
       Sin plaza
    </c:otherwise>

							</c:choose></td>
						<td><a href="verViaje?viajeID=${trip.id}">Ver</a></td>
					</tr>
				</c:forEach>
				<c:forEach var="seat" items="${listaSeats}">
					<tr>
						<td>${seat.trip.departure.city}</td>
						<td>${seat.trip.destination.city}</td>
						<td>${seat.trip.departureDate}</td>
						<td><c:choose>
								<c:when test="${seat.trip.promoter.id == user.id}">
      Promotor
    </c:when>
								<c:when test="${seat.status == 'ACCEPTED'}">
       Aceptado
    </c:when>
								<c:when test="${seat.status == 'EXCLUDED'}">
       Excluido
    </c:when>
							</c:choose></td>
						<td><a href="verViaje?viajeID=${seat.trip.id}">Ver</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</main>
		<jsp:include page="snippets/footer.jsp"/>
	</div>

	<script type="text/javascript"
		src="js/datatables.min.js"></script>
	<script type="text/javascript" src="js/datatables.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
</body>
</div>
</html>