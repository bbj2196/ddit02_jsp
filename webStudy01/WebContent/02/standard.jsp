<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>JSP 표준 구성요소</h4>
<pre>
	1. 정적 텍스트 (Front-End,client side) : HTML,JavaScript,CSS
	2. Back-End, Server side
		1) scriptlet : <%// java code %>, 지역코드화
		<%!
		public void test(){
			
		}
		%>
		2) expression : <%="출력데이터" %>
		3) directive : <%--@지시자명 --%>
			- page : 현재 jsp 페이지에 대한 설정정보(mime,import,errorPage...)
			- include : 정적내포
			- taglib : 
		4) declaration : <%!//전역멤버 선언 %>
		5) comment : <%-- --%>
			- client side comment : HTML, Javascript, CSS
<!-- 			<div></div> -->
				<script type="text/javascript">
// 				자바스크립트 주석
				</script>
				<style>
/* 				table{ */
/* 				} */
				</style>
			- server side comment : Java,Jsp
			<%
 			//싱글라인
			/*
			멀티라인
			*/
			/**
			doc
			**/
			%>
			<%--jsp주석 --%>
	3. 기본객체
	4. 액션태크
	5. EL(표현언어)
	6. JSTL(tag library)
</pre>
</body>
</html>