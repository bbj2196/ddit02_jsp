<%@page import="java.nio.file.Files"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>
	:Servlet container(WAS)와 해당 컨테이너 내에서 운영되는
	어플리케이션(context)에 대한 정보를 가진 객체
	1. 컨텍스트 초기화 파라미터 확보
	<%=application.getInitParameter("contentsPath") %>
	2. 로그기록
	<%application.log("명시적 로그 데이터asdasd"); %>
	3. 서버나 컨텍스트에 대한 정보 확인
	<%=application.getServerInfo() %>
	<%=application.getMajorVersion() %>...<%=application.getMinorVersion() %>
	4. 웹리소스 확보(*****)
	/resources/images/cat2.png
	/08/cat2.png
	<%
	String fileName="person_1.jpg";
	String midlePath = "resources/images/";
	URL pathUrl = application.getResource("/");
	File filePath = new File(pathUrl.getPath()+midlePath+fileName);
	File resFile = new File(pathUrl.getPath()+"/08/"+fileName);
	byte[] buffer = new byte[1024];
	int leng = -1;
	try(
			FileInputStream fis = new FileInputStream(filePath);
			FileOutputStream fos = new FileOutputStream(resFile);
			){
		while((leng = fis.read(buffer))!= -1){
			fos.write(buffer, 0, leng);
		}
	}
	System.out.println("종료");
	%>
</h4>
<img alt="assad" src="<%=request.getContextPath()%>/08/person_1.jpg">
</body>
</html>