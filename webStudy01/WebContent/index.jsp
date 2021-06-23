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
request.setCharacterEncoding("utf-8");
String user = (String)session.getAttribute("authId");
String message = request.getParameter("message");
if(message!=null && !message.isEmpty()){
	out.println(message);
}
if(user != null && !user.isEmpty()){ 
%>
<h4><%=user %>님 로그인 성공</h4>
<a href="<%=request.getContextPath() %>/login/logout.do">로그아웃</a>
<%}else{
	%>
	<h4>로그인을 해주세요</h4>
	<h4>
	<a href="<%=request.getContextPath()%>/login/loginForm.jsp">로그인 하기</a>
	</h4>
	<%	
}
%>
</body>
</html>