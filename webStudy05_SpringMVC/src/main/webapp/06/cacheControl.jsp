<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
response.setHeader("Cache-Control", "no-cache");
response.addHeader("Cache-Control", "no-store");
response.setHeader("Pragma", "no-cache");
response.addHeader("Pragma", "no-store");
response.setDateHeader("Expires", 0);
%>
<img alt="" src="<%=request.getContextPath() %>/resources/images/person_3.jpg">
</body>
</html>