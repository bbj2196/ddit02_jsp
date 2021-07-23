<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Refresh" content="5;url=http://www.naver.com"> -->
<title>Insert title here</title>
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js" type="text/javascript"></script>
</head>
<body>
<%
// 	response.setIntHeader("Refresh", 1);
%>
<h3><span id="timer"></span>초뒤 네이버 전송</h3>
<h4>현재 서버의 시간 : <%=new Date() %></h4>
<h4 id="watch">현재클라이언트의 시간 : <span></span></h4>
<h4> 자동요청을 통해 데이터를 갱신하는 방법</h4>
	1. server side : Refresh 응답 헤더를 통해 자동요청
	2. client side :
		1) HTML : meta 태그의 http-equiv 를 통해 refresh라는 html헤더를 설정
		2) Javascript : 스케줄링 함수의 활용
			** 스케줄링함수
			- setInterval : 주기적 반복
			- setTimeout : 한번 실행
<script type="text/javascript">
// 	let timer = $("#timer")
// 	let initval = 5
// 	timer.text(initval)
// 	$("#watch>span").text(new Date())
// 	setInterval(function(){
// 		$("#watch>span").text(new Date())
// 		timer.text(--initval)
// 	},1000)
</script>
</body>
</html>