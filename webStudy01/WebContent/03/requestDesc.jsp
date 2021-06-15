<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/requestDesc.jsp</title>
</head>
<body>
<h4>HttpSErvletRequest request</h4>
<pre>
 : 클라이언ㅇ트와 그로부터  발생한모든 정보를 가진객체
 
 http request spec
 1. Request Line : Protocol URL Method - 명령식별
 	Method : 요청을 발생시킨 목적(의도)
 	C - Get
 	R - Post
 	U - Put/Patch
 	D -	Delete
 	options : preflight 요청으로 특정 메소드의 지원여부를 확인할 목적으로 사용
 	head : response를 받아올때 body를 재외하고 싶은 요청에 사용
 	trace : server debugging
 	
 	
 2. Request Header
 3. Requset Body(Message Body,Contents Body) : 서버로 전송할 메시지 영역
 Line : <%=request.getProtocol() %> <%=request.getRequestURI() %> <%=request.getRequestURL() %>
 Body : <%=request.getInputStream().available() %>
 	<%=request.getCharacterEncoding() %>
 	<%=request.getContentLength() %>
</pre>
<table>
<thead>
<tr>
<th>헤더명</th>
<th>헤더값</th>
</tr>
</thead>
<tbody>
<%
Enumeration<String>names = request.getHeaderNames();
while(names.hasMoreElements()){
	String name = names.nextElement();
	String val = request.getHeader(name);
	%>
<tr>
<th><%=name %></th>
<td><%=val %></td>
</tr>	
	
	<%
}
%>

</tbody>
</table>
</body>
</html>