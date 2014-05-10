<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:basic pageTitle="Home">
	<h1>Home</h1>
	
	<h2>Last 24 hours activity</h2>
	<t:threads threads="${threads}" />
	
</t:basic>
