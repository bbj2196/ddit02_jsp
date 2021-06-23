<%@page import="kr.or.ddit.enumtype.BtsType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
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