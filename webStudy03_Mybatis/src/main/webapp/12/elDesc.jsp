<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>표현언어(Expression Laguage: EL)</h4>
<pre>
	속성을 출력할 목적만 가진언어
    \${속성명}
    <%
	String sample = "데이터";
   	request.setAttribute("sampleAttr", sample);
    %>
    jsp내 변수
    jsp : <%=sample %>
    el  : ${sample}
    
    속성 변수
    jsp : <%=request.getAttribute("sampleAttr") %>
    el  : ${sampleAttr }
    
     없을때
    jsp : <%=request.getAttribute("sampleAttrasdasd") %>
    el  : ${sampleAttrasdasd }
    
    <%
    pageContext.setAttribute("data", "페이지data");
    request.setAttribute("data", "리퀘data");
    session.setAttribute("data", "세션data");
    application.setAttribute("data", "어플data");
    %>
    ${data }
    : 작은 영역부터 검색해 가져온다
    
    ${requestScope.data }
    : 특정스코프를 앞에 써주면 해당 스코프내에서만 검색해 가져온다
    
    지원기능
    1. 연산자
    	산술 : +/*-%   
    		${5+2 },${"5"+"2" },${5/2 } null 은 0 이된다
    	논리 : &&(and)  키워드,기호 둘다 가능(키워드 권장)   
    		$(true and true), $("true" and "true") , null은 false취급
    	비교 : >(gt) , <(lt) , >=(ge), <=(le) , ==(eq), !=(ne)
    			${ 3 gt abc}, ${true ne false }, ${sampleAttr eq "데이터" }
    	단항 : empty  자바의 isBlank랑 같다  null이 아니면 크기값도 체크해 비어있다면 true 반환<%List list = new ArrayList();request.setAttribute("List", list); %>
    		${empty sample }, ${empty list }
    	삼항 :  조건식 ? 참 : 거짓 
    		${empty abc ? "있다" : "없다"}
    2. 자바 객체의 메소드 호출
    	${sampleAttr.length() }
    	<%MemberVO mem = MemberVO.builder().memName("김은대").build(); pageContext.setAttribute("member", mem); %>
    	${member.getMemName() }, ${member.memName }
    	${member.getMemTest() }, ${member.memTest }
    	 : 두번째의 " . "에 의한 접근은 해당속성을 직접접근하는게 아닌 getter를 호출하는 것이다
    3. collection 접근방법
    	<a href="./elCollection.jsp">Collection 가즈아 </a>
    4. 기본객체
    	Scope : pageScope, requestScope, sessionScope, applicationScope
    		${sessionScope.data },${sessionScope["data"] },${pageScope["member"]["memName"] }
    	요청 파라미터 : param,paramValues <a href="?param1=asdasd&param1=2222222">파라미터 확인(주소창)</a> 
    		${param.param1 }, ${paramValues.param1[0] },${paramValues.param1[1] }
    	(Map)요청 헤더 : header,headerValues
    		${header.accept },${header['accept'] }
    		${header.user-agent }
    		<%
    		Cookie[] cookies = request.getCookies();
    		// 이하 어쩌구~ 저쩌구~
    		%>
    	(Map) 쿠키(cookie): ${cookie.JSESSIONID }, ${cookie["JSESSIONID"].value }
    	(Map)컨텍스트파라미터 : initParam
    		<%=application.getInitParameter("cParam1") %> , ${initParam.cParam1 }
    	페이지 컨텍스트
    		<%=pageContext.getRequest() %>, ${pageContext.request.contextPath }
</pre>
</body>
</html>