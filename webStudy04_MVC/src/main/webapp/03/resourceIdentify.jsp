<%@page import="kr.or.ddit.servlet01.ImageListServlet"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
img{
	width:100px;
	height:100px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>자원 식별</h4>
<pre>
1. file system resource : 		d:\contents\cat01.jpg
2. web resource : (URL/URI), 	http://localhost:port/contextPath/images/cat01.jpg
3. classpath resource : /cat01.jpg
<%
	File fileSystemRes = new File("d:/contents/place-1.jpg");
	String realPath = application.getRealPath("/images/place-1.jpg");
	File webRes = new File(realPath);
	String realPath2 = ImageListServlet.class.getResource("/kr/or/ddit/servlet01/place-1.jpg").getFile();
	File classPathRes = new File(realPath2);
%>
<%=fileSystemRes.length() %>
<%=realPath %>:<%=webRes.length() %>
<%=realPath2 %> : <%=classPathRes.length() %>

** web resource 식별방법
URI (Uniform Resource Identifier.jsp)
URL (Uniform Resource Locator) : 자원의 위치를 기준으로 식별
URN (Uniform Resource Name) : 자원의 등록된 이름으로 식별
URC (Uniform Resource ContextPath) : 자원의 등록된 컨텐츠로 식별
<%=request.getRequestURI() %>
<%=request.getRequestURL() %>

자원에 접근하는 경로 표기법
1. 상대경로 : 경로가 생략된 구조 wild card(./ 또는 ../)
			현재 위치를 기준으로 실제 자원의 절대 경로를 판단함
2. 절대경로  : "/"로 시작
	1) client side : context root 부터 시작되는 경로표기
	2) server side : context root 이후의 경로를 표기함
</pre>
<img src="../images/place-1.jpg">
<img src="http://localhost/webStudy01/images/place-1.jpg">
<img alt="asdasd" src="<%=request.getContextPath() %>/images/place-1.jpg">
</body>
</html>