<%@tag description="Threads" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="threads" required="true" type="pl.vgtworld.civcrawler.services.dao.ThreadWithNewPosts[]"%>
 
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
