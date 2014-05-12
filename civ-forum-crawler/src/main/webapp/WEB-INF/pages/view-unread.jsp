<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<t:basic pageTitle="Unread posts">
	<h1>Unread posts</h1>
	
	<c:if test="${fn:length(threads) > 0}">
	<a href="/mark-forum-read">Mark all read</a>
	<t:threads threads="${threads}" directUrls="false" />
	</c:if>
	
	<c:if test="${fn:length(threads) == 0}">
	<p class="message-info">
		No new posts since last visit.
	</p>
	</c:if>
	
</t:basic>
