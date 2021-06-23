<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>기본 내장 객체</h4>

<pre>
<%-- <%=request.getContextPath() %> --%>
<%-- <%=((HttpServletRequest)pageContext.getRequest()).getContextPath() %> --%>
<%-- ${pageContext.request.contextPath} --%>
	:JSP container에 의해 서블릿 소스가 파싱될때 자동으로 생성되는 객체
	1. request : client와 request에 대한 정보를 가진 객체
	2. response : client로 전송될 response에 대한 정보를 가진 객체
	3. out(JspWriter) : 응답데이터를 버터에 기록할 출력 스트림
		: buffer를 제어하거나 상태를 확인할때도 활용된다
	4. session(HttpSession) : 하나의 클라이언트가 하나의 브라우저를 사용할때, 해당 클라이언트를 식별할 용도로 사용된다
		<a href="../08/sessionDesc.jsp">세션 유지법</a>
	5. application(ServletContext) : 현재서버와 어플리케이션 자체에 대한 정보를 가진 객체.
	6. config(ServletConfig) : 
	7. page : jsp 인스턴스 자체
	8. exception(Throwable) : 에러나 예외가 발생했을때 그 상황을 처리할 목적의 페이지에서 사용됨.
		page지시자의 isErrorPage="true"인 경우에만 활성화된다
	9. pageContext : 모든 기본객체중 가장 먼저 생성되고, 나머지 기본객체에 대한 참조를 가진다.
</pre>
</body>
</html>