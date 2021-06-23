<%@page import="java.nio.file.StandardCopyOption"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.nio.file.Paths"%>
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
<pre>
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
	String imageURL="/resources/images/person_1.jpg";
	String realPath = application.getRealPath(imageURL);
	System.out.println(realPath);
// 	File readFile = new File(realPath);
// 	FileInputStream fis = new FileInputStream(readFile);
	String destURLstr = "/08/cat2.png";
	URL destURL = application.getResource(destURLstr);
	Path target=null;
	if(destURL==null){
		String destRealPath = application.getRealPath(destURLstr);
		target=Paths.get(destRealPath);
	}else{
	target = Paths.get(destURL.toURI());
	}
	InputStream is = application.getResourceAsStream(imageURL);
	Files.copy(is, target,StandardCopyOption.REPLACE_EXISTING);
	
	out.println("종료");
	%>
</pre>
<img alt="assad" src="<%=application.getContextPath() %>/08/person_1.jpg">
</body>
</html>