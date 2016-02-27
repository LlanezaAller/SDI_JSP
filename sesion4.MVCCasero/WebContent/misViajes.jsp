<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Viajes</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
  <link rel="stylesheet" type="text/css" href="css/reset.css">
  <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/t/dt/jq-2.2.0,dt-1.10.11/datatables.min.css"/>
  <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
  <div class="wrapper">
    <header>
      <nav class="horizontalMenu">
        <div class="horizontalMenuInnerWrapper">
          <div class="menuLogo"><a href="#"><img src="img/logo.png">Share My Trip</a></div>
          <ul class="horizontalMenu_links">
						<li><a href="#"><i
								class="fa fa-2x fa-car"></i>Viajes</a></li>
						<li class="activeLink"><a href="misViajes"><i class="fa fa-2x fa-users"></i>Mis
								Viajes</a></li>
						<li><a href="crearViaje.jsp"><i class="fa fa-2x fa-plus-circle"></i>Crear
								Viaje</a></li>
					</ul>
					<jsp:include page="snippets/menu.jsp" />
					<div class="clear"></div>
        </div>
      </nav>
    </header>
    
    <main>
      <h1>Mis Viajes</h1>
      <table class="table" id="dataTable">
        <thead>
          <tr>
            <th>Origen</th>
            <th>Destino</th>
            <th>Fecha de salida</th>
            <th>Fecha de llegada</th>
            <th>Promotor</th>
            <th>Plazas</th>
            <th>Ver/Modificar</th>
          </tr>
        </thead>
        <tbody>
          <tr class="myTrip">
            <td>Origen</td>
            <td>Destino</td>
            <td>Fecha de salida</td>
            <td>Fecha de llegada</td>
            <td>Fecha lÃ­mite</td>
            <td>Plazas libres</td>
            <td><a href="#">Modificar</a></td>
          </tr>
          <tr class="myTrip">
            <td>Origen</td>
            <td>Destino</td>
            <td>Fecha de salida</td>
            <td>Fecha de llegada</td>
            <td>Fecha lÃ­mite</td>
            <td>Plazas libres</td>
            <td><a href="#">Ver</a></td>
          </tr>
        </tbody>
      </table>
    </main>
    <footer>
    </footer>
    </div>

    <script type="text/javascript" src="https://cdn.datatables.net/t/dt/jq-2.2.0,dt-1.10.11/datatables.min.js"></script>
    <script type="text/javascript" src="js/datatables.js"></script>
    <script type="text/javascript" src="js/messages.js"></script>
  </body>
  </div>
</html>