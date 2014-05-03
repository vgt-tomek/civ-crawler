<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:basic pageTitle="Home">
	<h1>Register</h1>
	
	<form method="POST" action="register">
		<div>
			<label for="register-login">Login</label>
			<input id="register-login" type="text" name="login" value="" />
		</div>
		<div>
			<label for="register-password">Password</label>
			<input id="register-password" type="password" name="password" value="" />
		</div>
		<div>
			<label for="register-repeat-password">Repeat password</label>
			<input id="register-repeat-password" type="password" name="password-repeat" value="" />
		</div>
		<div>
			<input type="submit" name="register-submit" value="Register" />
		</div>
	</form>
</t:basic>
