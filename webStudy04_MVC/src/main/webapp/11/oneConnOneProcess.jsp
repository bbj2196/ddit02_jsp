<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.Connection"%>
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
	long start = System.currentTimeMillis();

	try(
			Connection conn = ConnectionFactory.getConnection();
			Statement stmt = conn.createStatement();
			){
		String sql = "select ename From emp where mgr is null";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()){
			out.println(rs.getString("ename"));
		}
	}
	
	long end =System.currentTimeMillis();
	
%>
 소요시간 : <%=end-start %>ms
</body>
</html>