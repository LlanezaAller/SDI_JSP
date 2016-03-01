<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modificar viaje</title>
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
						<li><a href="listarViajes"><i class="fa fa-2x fa-car"></i>Viajes</a></li>
						<li><a href="listarMisViajes"><i
								class="fa fa-2x fa-users"></i>Mis Viajes</a></li>
						<li class="activeLink"><a href="crearViaje.jsp"><i
								class="fa fa-2x fa-plus-circle"></i>Crear Viaje</a></li>
					</ul>
					<jsp:include page="snippets/menu.jsp" />
					<div class="clear"></div>
				</div>
			</nav>
		</header>
		<jsp:include page="snippets/messages.jsp"></jsp:include>
		<main>
		<form method="POST" action="crearViaje" class="formDark">
			<input type="hidden" name="viajeID" value="${viaje.id}"/>
			<div class="inputLine">
				<label class="inputIcon" for="freeSeats"><i
					class="fa fa-car fa-2x"></i></label><input type="number" class="textInput"
					name="freeSeats" id="freeSeats" placeholder="Asientos libres*"
					value="${viaje.availablePax}" required>
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="totalCost"><i
					class="fa fa-eur fa-2x"></i></label><input type="number" class="textInput"
					name="totalCost" id="totalCost"
					placeholder="Coste total (A repartir entre los participantes)*"
					value="${viaje.estimatedCost}" required>
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="limitDatetime" title="Fecha límite"><i
					class="fa fa-clock-o fa-2x"></i></label><input type="datetime-local"
					class="textInput" name="limitDatetime" id="limitDatetime"
					placeholder="Fecha límite*" title="Fecha límite*"
					value="${viaje.closingDate}" required>
			</div>
			<!-- Salida -->
			<div class="inputLine">
				<label class="inputIcon" for="departureCountry"><i
					class="fa fa-globe fa-2x"></i></label><input type="text" class="textInput"
					name="departureCountry" id="departureCountry"
					placeholder="País de salida*" value="${viaje.departure.country}"
					required>
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="departureState"><i
					class="fa fa-map-o fa-2x"></i></label><input type="text" class="textInput"
					name="departureState" id="departureState"
					placeholder="Provincia de salida*" value="${viaje.departure.state}"
					required>
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="departureCity"><i
					class="fa fa-building fa-2x"></i></label><input type="text"
					class="textInput" name="departureCity" id="departureCity"
					placeholder="Ciudad de salida*" value="${viaje.departure.city}"
					required>
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="departureZip"><i
					class="fa fa-archive fa-2x"></i></label><input type="text"
					class="textInput" name="departureZip" id="departureZip"
					placeholder="C.P. de salida*" value="${viaje.departure.zipCode}"
					required>
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="departureStreet"><i
					class="fa fa-map-signs fa-2x"></i></label><input type="text"
					class="textInput" name="departureStreet" id="departureStreet"
					placeholder="Calle de salida*" value="${viaje.departure.address}"
					required>
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="departureLat"><i
					class="fa fa-map-marker fa-2x"></i></label><input type="number"
					class="textInput" name="departureLat" id="departureLat"
					placeholder="Latitud de salida"
					value="${viaje.departure.waypoint.lat}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="departureLon"><i
					class="fa fa-map-marker fa-2x"></i></label><input type="number"
					class="textInput" name="departureLon" id="departureLon"
					placeholder="Longitud de salida"
					value="${viaje.departure.waypoint.lon}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="departureDatetime"
					title="Hora y fecha de salida"><i
					class="fa fa-clock-o fa-2x"></i></label><input type="datetime-local"
					class="textInput" name="departureDatetime" id="departureDatetime"
					placeholder="Hora y fecha de salida*"
					title="Hora y fecha de salida" value="${viaje.departureDate}">
			</div>
			<!--
			 Llegada 
			-->
			<div class="inputLine">
				<label class="inputIcon" for="arrivalCountry"><i
					class="fa fa-globe fa-2x"></i></label><input type="text" class="textInput"
					name="arrivalCountry" id="arrivalCountry"
					placeholder="País de llegada*" required
					value="${viaje.destination.country}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="arrivalState"><i
					class="fa fa-map-o fa-2x"></i></label><input type="text" class="textInput"
					name="arrivalState" id="arrivalState"
					placeholder="Provincia de llegada*" required
					value="${viaje.destination.state}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="arrivalCity"><i
					class="fa fa-building fa-2x"></i></label><input type="text"
					class="textInput" name="arrivalCity" id="arrivalCity"
					placeholder="Ciudad de llegada*" required
					value="${viaje.destination.city}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="arrivalZip"><i
					class="fa fa-archive fa-2x"></i></label><input type="text"
					class="textInput" name="arrivalZip" id="arrivalZip"
					placeholder="C.P. de llegada*" required
					value="${viaje.destination.zipCode}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="arrivalStreet"><i
					class="fa fa-map-signs fa-2x"></i></label><input type="text"
					class="textInput" name="arrivalStreet" id="arrivalStreet"
					placeholder="Calle de llegada*" required
					value="${viaje.destination.address}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="arrivalLat"><i
					class="fa fa-map-marker fa-2x"></i></label><input type="number"
					class="textInput" name="arrivalLat" id="arrivalLat"
					placeholder="Latitud de llegada"
					value="${viaje.destination.waypoint.lat}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="arrivalLon"><i
					class="fa fa-map-marker fa-2x"></i></label><input type="number"
					class="textInput" name="arrivalLon" id="arrivalLon"
					placeholder="Longitud de llegada"
					value="${viaje.destination.waypoint.lon}">
			</div>
			<div class="inputLine">
				<label class="inputIcon" for="arrivalDatetime"
					title="Hora y fecha de llegada*"><i
					class="fa fa-clock-o fa-2x"></i></label><input type="datetime-local"
					class="textInput" name="arrivalDatetime" id="arrivalDatetime"
					placeholder="Hora y fecha de llegada*"
					title="Hora y fecha de llegada*" required
					value="${viaje.arrivalDate}">
			</div>
			<textarea id="description" name="description" class="textInput"
				style="resize: none; width: 100%; height: 200px;"
				placeholder="Cuéntanos más sobre el viaje...">${viaje.comments}</textarea>
			<div class="inputLine">
				<input type="submit" value="Crear viaje" class="button"
					style="width: 100%;">
			</div>
		</form>
		</main>
		<footer> </footer>
	</div>

	<script type="text/javascript" src="js/datatables.min.js"></script>
	<script type="text/javascript" src="js/datatables.js"></script>
	<script type="text/javascript" src="js/messages.js"></script>
</body>
</div>
</html>