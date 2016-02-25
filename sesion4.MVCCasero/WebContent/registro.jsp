<!--%@ page contentType="text/html" pageEncoding="UTF-8" %-->
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Crear una cuenta</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="noScrollWrapper animatedBackground">
		<div class="centeredWrapper transparentBox">
			<form id="registerForm" method="POST" action="registrarse">
				<div class="inputLine"><img src="img/logo.png" height="200" width="200"></div>
				<div class="inputLine">
					<label class="inputIcon" for="name"><i class="fa fa-list-alt fa-2x"></i></label>
					<input type="text" name="name" id="name" class="textInput textInputWithIcon" placeholder="Nombre">
				</div>
				<div class="inputLine">
					<label class="inputIcon" for="surname"><i class="fa fa-list-alt fa-2x"></i></label>
					<input type="text" name="surname" id="surname" class="textInput textInputWithIcon" placeholder="Apellidos">
				</div>
				<div class="inputLine">
					<label class="inputIcon" for="email"><i class="fa fa-envelope-o fa-2x"></i></label>
					<input type="email" name="email" id="email" class="textInput textInputWithIcon" placeholder="Email">
				</div>
				<div class="inputLine">
					<label class="inputIcon" for="user"><i class="fa fa-user fa-2x"></i></label>
					<input type="text" name="user" id="user" class="textInput textInputWithIcon" placeholder="Usuario">
				</div>
				<div class="inputLine">
					<label class="inputIcon" for="password"><i class="fa fa-lock fa-2x"></i></label>
					<input type="password" name="password" id="password" class="textInput textInputWithIcon" placeholder="Contraseña"></div>
				<div class="inputLine">
					<label class="inputIcon" for="repeatPassword"><i class="fa fa-lock fa-2x"></i></label>
					<input type="password" name="repeatPassword" id="repeatPassword" class="textInput textInputWithIcon" placeholder="Repita su contraseña"></div>
				<div class="inputLine"><input type="submit" value="Crear cuenta" class="button"></div>
			</form>
		</div>
	</div>
	<jsp:include page="snippets/messages.jsp"></jsp:include>
</body>

</html>