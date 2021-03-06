<%@tag description="Basic template" pageEncoding="UTF-8"%>
<%@attribute name="pageTitle" required="true" type="java.lang.String" %>
<%@attribute name="header" fragment="true"%>
<html>
<head>
	<link rel="stylesheet" type="text/css" href="/css/style.css" />
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<title>civ.org.pl forum crawler - ${pageTitle}</title>
</head>
<body>
	<div id="header">
		<%@include file="/WEB-INF/pages/includes/menu.jsp" %>
		<jsp:invoke fragment="header" />
	</div>
	<div id="content">
		<jsp:doBody />
	</div>
	<div id="footer">
		<p class="copyright"><a href="http://vgtworld.pl">VGT</a> 2014</p>
	</div>
</body>
</html>
