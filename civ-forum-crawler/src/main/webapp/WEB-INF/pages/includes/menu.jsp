<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul id="main-menu">
	<c:if test="${user != null}">
		<li>Logged as <c:out value="${user.login}" /></li>
	</c:if>
	<li><a href="<%= request.getContextPath() %>/">Home</a></li>
	
	<c:if test="${user == null}">
		<li><a href="<%= request.getContextPath() %>/login">Login</a></li>
		<li><a href="<%= request.getContextPath() %>/register">Register</a></li>
	</c:if>
</ul>