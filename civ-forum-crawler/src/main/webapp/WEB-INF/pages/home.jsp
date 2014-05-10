<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:basic pageTitle="Home">
	<h1>Home</h1>
	
	<h2>Last 24 hours activity</h2>
	<table>
		<tr>
			<th>Thread</th>
			<th>Board</th>
			<th>New posts</th>
			<th>Last post</th>
		</tr>
		<c:forEach items="${threads}" var="thread">
		<tr>
			<td><a href="${thread.url}"><c:out value="${thread.name}" /></a></td>
			<td>${thread.board}</td>
			<td>${thread.newPostCount}</td>
			<td>
				<span class="date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${thread.lastPostTimestamp}" /></span>
				by <c:out value="${thread.lastPostUserName}" />
			</td>
		</tr>
		</c:forEach>
	</table>
	
</t:basic>
