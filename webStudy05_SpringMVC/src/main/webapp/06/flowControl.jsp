<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>어플리케이션 흐름제어</h4>
1. Request Dispatch : 도착지로 이동하는 과정에서 원본 요청에 대한 정보를 가지고 분기
	1) forward : 이동하기전에 버터를 비운다
	2) include : 도착지에서 만들어진 결과데이터를 가지고 복귀
	 <%--
	 	String dest="/07/destination.jsp";
	 	RequestDispatcher rd = request.getRequestDispatcher(dest);
	 	rd.include(request, response);
	 --%>
	 
	 <%
	 String dest = "/webStudy01/07/destination.jsp";
	 response.sendRedirect(dest);
	 %>
	 
2. Redirect
	이동하는 과정에서 원본요청에 대한 응답이 먼저 전송
	- body가 없고, 상태코드(302)+header(Location)로 구성된 응답이 전송
	--> Location 방향으로 클라이언트의 새로운 요청이 발생함
</body>
</html>