<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>웰컴 페이지</h4>
<%
String user = (String)session.getAttribute("authId");
if(user != null){ 
%>
<h4><%=user %>님 로그인 성공</h4>
<%}else{
	%>
	<h4>로그인을 해주세요</h4>
	<%	
}
%>
</body>
</html>