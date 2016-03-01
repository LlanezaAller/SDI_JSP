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
						<li><a href="listarViajes"><i class="fa fa-2x fa-car"></i>Viajes</a></li>
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
		<h1>
			Viaje a <span class="boldColored">${viaje.destination.city}</span>
			por <span class="boldColored">${viaje.promoter.login}</span>
		</h1>
		<table class="table">
			<thead>
				<tr>
					<th></th>
					<th>Salida</th>
					<th>Llegada</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="titleColumn">País:</td>
					<td>${viaje.departure.country}</td>
					<td>${viaje.destination.country}</td>
				</tr>
				<tr>
					<td class="titleColumn">Estado:</td>
					<td>${viaje.departure.state}</td>
					<td>${viaje.destination.state}</td>
				</tr>
				<tr>
					<td class="titleColumn">Ciudad:</td>
					<td>${viaje.departure.city}</td>
					<td>${viaje.destination.city}</td>
				</tr>
				<tr>
					<td class="titleColumn">Calle:</td>
					<td>${viaje.departure.address}</td>
					<td>${viaje.destination.address}</td>
				</tr>
				<tr>
					<td class="titleColumn">Fecha:</td>
					<td>${viaje.departureDate}</td>
					<td>${viaje.arrivalDate}</td>
				</tr>
			</tbody>
		</table>
		<h1>Descripción</h1>
		<p>${viaje.comments}</p>
		<h1>Viajeros</h1>
		<table class="table">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>Comentarios</th>
					<c:if
						test="${viaje.closingDate.time > today.time and viaje.promoter.id == user.id}">
						<th>Estado</th>
					</c:if>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="seat" items="${seats}">
					<tr>
						<td>${seat.user.name}</td>
						<td>${seat.user.surname}</td>
						<td><a href="listarRatings?userLogin=${seat.user.login}">Ver
								valoraciones</a></td>
						<c:if
							test="${viaje.closingDate.time > today.time and viaje.promoter.id == user.id}">
							<td><c:choose>
									<c:when test="${seat.user.id==viaje.promoter.id}">
										Promotor
									</c:when>
									<c:when test="${seat.status=='ACCEPTED'}">
										Aceptado
									</c:when>
									<c:otherwise>
										Excluido
									</c:otherwise>
								</c:choose> <c:if test="${seat.user.id != viaje.promoter.id}">
									<form method="POST" style="float:right;">
										<input type="hidden" value="${seat.user.login}" name="userLogin">
										<input type="hidden" value="${viaje.id}" name="viajeID">
										<c:if test="${seat.status=='EXCLUDED' }">
										<button class="button" type="submit" formaction="aceptarUsuarioViaje" style="width:46px;">
											<i class="fa fa-check"></i>
										</button>
										</c:if>
										<c:if test="${seat.status=='ACCEPTED' }">
										<button class="button" type="submit" formaction="excluirUsuarioViaje" style="width:46px;">
											<i class="fa fa-times"></i>
										</button>
										</c:if>
									</form>
								</c:if></td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:if
			test="${viaje.closingDate.time > today.time and viaje.promoter.id == user.id}">
			<h1>Solicitudes</h1>
			<table class="table">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellidos</th>
						<th>Comentarios</th>
						<c:if
							test="${viaje.closingDate.time > today.time and viaje.promoter.id == user.id}">
							<th>Estado</th>
						</c:if>
					</tr>
				</thead>
				<tbody>
					<%--
					--%>
					<c:forEach var="u" items="${applicants}">
						<tr>
							<td>${u.name}</td>
							<td>${u.surname}</td>
							<td><a href="listarRatings?userLogin=${u.login}">Ver
									valoraciones</a></td>
							<c:if
								test="${viaje.closingDate.time > today.time and viaje.promoter.id == user.id}">
								<td>
									<form method="POST" style="float:right;">
										<input type="hidden" value="${u.login}" name="userLogin"> <input
											type="hidden" value="${viaje.id}" name="viajeID">
										<button type="submit" formaction="aceptarUsuarioViaje" style="width:46px;">
											<i class="fa fa-check"></i>
										</button>
										<button type="submit" formaction="excluirUsuarioViaje" style="width:46px;">
											<i class="fa fa-times"></i>
										</button>
									</form>
								</td>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if> <c:choose>
			<c:when test="${viaje.closingDate.time > today.time}">
				<%-- Viaje abierto, los promotores pueden editar y el resto de la people pedir plazas --%>
				<c:choose>
					<c:when test="${viaje.promoter.id == user.id}">
						<%-- El usuario es promotor --%>
						<h1>Administrar viaje</h1>
						<form method="POST" action="conseguirViaje" class="formDark">
							<input type="hidden" value="${viaje.id}" name="viajeID"/> <input type="submit"
								class="button" value="Modificar viaje" />
						</form>
						<form method="POST" action="cancelarViaje" class="formDark">
							<input type="hidden" value="${viaje.id}" name="viajeID"/> <input type="submit"
								class="button" value="Cancelar viaje" />
						</form>
					</c:when>
					<c:otherwise>
						<%-- El usuario es un viajero que no tiene plaza pedida, puede pedir plaza --%>
						<c:if test="${viaje.availablePax > 0 and !hasSeatOrApplication}">
							<form method="POST" action="solicitarPlaza" class="formDark">
								<input type="hidden" name="viajeID" value="${viaje.id}" /> <input
									type="submit" class="button" value="Solicitar plaza" />
							</form>
						</c:if>
					</c:otherwise>

				</c:choose>
			</c:when>
			<c:otherwise>
				<%-- Viaje cerrado, si es un participante y el viaje está realizado ofrecer la posibilidad de valorar --%>
				<c:if
					test="${viaje.status=='DONE' and isParticipante and not hasRated}">
					<form action="valorarViaje" method="POST" class="formDark">
						<input type="hidden" name="viajeID" value="${viaje.id}" />
						<c:forEach var="seat" items="${seats}">
							<c:if test="${user.id!=seat.user.id and seat.status=='ACCEPTED'}">
								<div class="inputLine">
									<label class="inputIcon" for="rating-user-${seat.user.id}"><i class="fa fa-star fa-2x"></i></label>
									<input class="textInput textInputWithIcon" placeholder="Valoración sobre ${seat.user.name}" type="number" name="rating-user-${seat.user.id}" id="rating-user-${seat.user.id}" min="0" max="10" />
								</div>
								<div class="inputLine">
									<label class="inputIcon" for="comment-user-${seat.user.id}"><i class="fa fa-comment fa-2x"></i></label>
									<input class="textInput textInputWithIcon" placeholder="Comentario sobre ${seat.user.name}" type="text" name="comment-user-${seat.user.id}" id="comment-user-${seat.user.id}" min="0" max="10" />
								</div>
								<input type="submit" value="Enviar valoraciones" />
							</c:if>
						</c:forEach>
					</form>
				</c:if>
			</c:otherwise>
		</c:choose></main>
		<footer> </footer>
	</div>
	<script type="text/javascript"
		src="js/datatables.min.js"></script>
	<script type="text/javascript" src="js/datatables.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
</body>

</html>