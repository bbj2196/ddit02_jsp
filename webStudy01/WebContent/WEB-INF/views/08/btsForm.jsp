<%@page import="kr.or.ddit.enumtype.BtsType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%

%>
</head>
<body>
폼접근을 위한 서블릿
폼에서 데이터를 받아 상세페이지호출할 서블릿?
ㄴㄴ 여기에서 하나로 할것 
enum써라
<form method="post">
	<select name="btsMember">
		<%
		for(BtsType mem : BtsType.values()){
			%>
			<option><%=mem.getName() %></option>
			<%
		}
		%>
	</select>
	<input type="submit" value="전송">
</form>
</body>
</html>