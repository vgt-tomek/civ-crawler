<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:basic pageTitle="Home">

	<h1>Last 24 hours activity</h1>
	<t:threads threads="${threads}" />
	
</t:basic>
