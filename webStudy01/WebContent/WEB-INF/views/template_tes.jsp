<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/main.css">
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<header>
<jsp:include page="/includee/headerMenu.jsp"></jsp:include>
</header>
<div id="leftMenu">
<jsp:include page="/includee/leftMenu.jsp"></jsp:include>
</div>
<div id="contents">
<%
	String contentsPage=(String)request.getAttribute("contentsPage");
	pageContext.include(contentsPage);
%>
<%-- 	<jsp:include page="<%=contentsPage %>"></jsp:include> --%>
</div>
<footer>
<jsp:include page="/includee/footer.jsp"></jsp:include>
</footer>
</body>
</html>