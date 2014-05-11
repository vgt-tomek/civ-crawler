<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:basic pageTitle="Logout">
	
	<p class="message-info">
		You need to be logged in to view this content.
		<a href="${pageContext.request.contextPath}/login">Go to login form.</a>
	</p>
</t:basic>
