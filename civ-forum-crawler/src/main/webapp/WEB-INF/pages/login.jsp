<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:basic pageTitle="Login">
	<h1>Login</h1>
	
	<form method="POST" action="login">
		<div class="errors">
			<c:forEach items="${errors}" var="errorMessage">
				<p class="error">${errorMessage}</p>
			</c:forEach>
		</div>
		<div>
			<label for="loginform-login">Login</label>
			<input id="loginform-login" type="text" name="login" value='<c:out value="${dto.login}" />' />
		</div>
		<div>
			<label for="loginform-password">Password</label>
			<input id="loginform-password" type="password" name="password" value="" />
		</div>
		<div>
			<input type="submit" name="register-submit" value="Login" />
		</div>
	</form>
</t:basic>
