<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty message}">
	<c:choose>
	    <c:when test="${message.type == 'ERROR'}">
	       <div class="message errorMessage">
	    </c:when>
	    <c:when test="${message.type == 'WARNING'}">
	        <div class="message warningMessage">
	    </c:when>
	    <c:otherwise>
	        <div class="message okMessage">
	    </c:otherwise>
	</c:choose>
		<div class="messageIcon"><i class="fa fa-2x"></i></div>
		<div class="messageText"><c:out value="${message.text}" /></div>
	</div>
</c:if>