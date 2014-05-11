<%@tag description="Threads" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="threads" required="true" type="pl.vgtworld.civcrawler.services.dao.ThreadWithNewPosts[]"%>

<table class="thread-list">
	<thead>
	<tr>
		<th>Thread</th>
		<th>Board</th>
		<th>New posts</th>
		<th>Last post</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach items="${threads}" var="thread">
	<tr>
		<td class="thread"><a href="${pageContext.request.contextPath}/post-redirect?messageId=${thread.messageId}"><c:out value="${thread.name}" /></a></td>
		<td class="board">${thread.board}</td>
		<td class="post-count">${thread.newPostCount}</td>
		<td class="last-post">
			<span class="date"><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${thread.lastPostTimestamp}" /></span>
			by <c:out value="${thread.lastPostUserName}" />
		</td>
	</tr>
	</c:forEach>
	</tbody>
</table>
