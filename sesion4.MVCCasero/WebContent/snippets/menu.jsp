<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:choose>
    <c:when test="${not empty user}">
		<ul class="horizontalMenu_user_links">
			<li><a href="editarPerfil.jsp"><i class="fa fa-2x fa-user"></i>Editar Perfil</a></li>
			<li><a href="logout"><i class="fa fa-2x fa-sign-out"></i>Salir</a></li>
		</ul>		
    </c:when>
    <c:otherwise>
        <ul class="horizontalMenu_user_links">
			<li><a href="login.jsp"><i class="fa fa-2x fa-sign-in"></i>Entrar</a></li>
			<li><a href="registro.jsp"><i class="fa fa-2x fa-user-plus"></i>Crear cuenta</a></li>
		</ul>
    </c:otherwise>
</c:choose>
