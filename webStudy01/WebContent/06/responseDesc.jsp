<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
//     response.setContentType("text/plain; charset=utf-8");
//     response.setHeader("Content-type", "text/plain; charset=utf-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>HttpServletResponse (response 기본객체)</h4>
<pre>
	:서버에서 클라이언트로 전송되는 데이터를 갭슐화한 객체
	
	1. Response Line : protocol, status code
	<%
// 		response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,"강제서버에러");
// 	return;
	%>
		** 상태코드 : 요청처리의 결과를 표현하는 숫자 체계
		100~ : ing~~
		200~ : OK
		300~ : 처리완료를 위해 클라이언트로 부터 추가적인 액션이 필요한경우
			304(Not modified), 302/307(moved ◁ Location)
		400~ : client side fail
			404(NotFound),405(Not supported method)
			415(Not supported Media Type)
			400(Bad Request):잘못된 파라미터
			403(Forbidden),401(unAuthorized) - 권한 처리
		500~ : server side fail
			500(Internal Server Error)
			
	2. Response Header : Meta data, setHeader(name,value);
		* Content-Type : body영역의 데이터 mime
		* Cache-Control(v1.1), Pragma(v1.0), Expires : 캐시를 제어할 때 사용됨
			<a href="cacheContrl.jsp">캐시 제어 예지</a>
		* Refresh : autoRequest
			<a href="autoRequest.jsp">Refresh를 통한 요청</a>
		* Location
	3. Response Body
</pre>
<img alt="" src="<%=request.getContextPath() %>/resources/images/person_3.jpg">
</body>
</html>