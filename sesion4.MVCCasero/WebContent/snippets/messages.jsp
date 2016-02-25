
<c:if test="${not empty message}">
	<c:choose>
	    <c:when test="${message.type == error}">
	       <div class="message errorMessage">
	    </c:when>
	    <c:when test="${message.type == warning}">
	        <div class="message warningMessage">
	    </c:when>
	    <c:otherwise>
	        <div class="message errorMessage">
	    </c:otherwise>
	</c:choose>
		<div class="messageIcon"><i class="fa fa-2x"></i></div>
		<div class="messageText"><c:out value="${message.text}" /></div>
	</div>
</c:if>