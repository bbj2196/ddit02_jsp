<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<h4>include 의 종류</h4>
<pre>
:include 되는 시점과 대상에 따른 분류
	web.xml에서 특정 경로 대상으로 전체 include가 가능하다
1. 정적 include : jsp 소스 파싱단계(소스해석),소스 코드가 내포됨
	코드의 중복을 제거하는데 활용됨(비추)
<%--@ include file="/02/standard.jsp" --%>
<%--
	test();
--%>
2. 동적 include : runtime , 실행 결과가 내포됨
<%
	String dest = "/02/standard.jsp";
	request.getRequestDispatcher(dest).include(request, response);
// 	pageContext.include(dest);
%>
남은 잔여코드
커스텀 태크
<jsp:include page="/02/standard.jsp"></jsp:include>
남은 잔여코드 : <%--=test() --%>

</pre>
</body>
</html>