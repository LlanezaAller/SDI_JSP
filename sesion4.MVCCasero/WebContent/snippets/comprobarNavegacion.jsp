<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty user}">
YOLO
	<jsp:forward page="../login.jsp"/>
</c:if>
