<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${user != null}">
<div class="user">
	Logged as <c:out value="${user.login}" />
	(<a href="${pageContext.request.contextPath}/logout">Logout</a>)
</div>
</c:if>

<c:if test="${user == null && hideTopLoginForm != true}">
<form class="user-login-form" method="POST" action="${pageContext.request.contextPath}/login">
	<label for="loginform-login">Login:</label>
	<input id="loginform-login" type="text" name="login" value="" />
	<label for="loginform-password">Password:</label>
	<input id="loginform-password" type="password" name="password" value="" />
	<input type="submit" name="register-submit" value="Login" />
</form>
</c:if>

<ul id="main-menu">
	<li><a href="${pageContext.request.contextPath}/">Home</a></li>
	
	<c:if test="${user == null}">
		<li><a href="${pageContext.request.contextPath}/login">Login</a></li>
		<li><a href="${pageContext.request.contextPath}/register">Register</a></li>
	</c:if>
	
	<c:if test="${user != null}">
		<li><a href="${pageContext.request.contextPath}/view-unread">Unread posts</a></li>
	</c:if>
</ul>