<%@page import="java.util.LinkedHashMap"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h4>웰컴 페이지</h4>
<p>누적방문자수 :${userCount} , 현재 방문자수 : ${currentUserCount}</p>
<!-- 로그인이 되어있다면 접속자 리스트 -->
<%
request.setCharacterEncoding("utf-8");
MemberVO user = (MemberVO)session.getAttribute("authMember");
String message = (String)session.getAttribute("message");
session.removeAttribute("message");
if(message!=null && !message.isEmpty()){
	out.println(message);
}
if(user != null){
// 	LinkedHashMap<String, MemberVO> currentUsers=(LinkedHashMap<String, MemberVO>) application.getAttribute("currentUserList");
// 	for (String key : currentUsers.keySet()) {
// 		out.println(key);
// 	}
%>
<div>
<h4>접속 유저리스트</h4>
<c:forEach items="${currentUserList }" var="user"  >
<p>${user.key}</p>
</c:forEach>
</div>
<h4>
	<c:if test="${not empty authMember.memImg }">
	<img alt="프로필이미지" src="data:image/*;base64,${authMember.base64Thumb }">
	</c:if>
	<br><a href="<%=request.getContextPath()%>/mypage.do"><%=user.getMemName() %>님</a>
	<br>
	<a href="<%=request.getContextPath() %>/login/logout.do">로그아웃</a>
</h4>
<%}else{
	%>
	<h4>로그인을 해주세요</h4>
	<h4>
	<a href="<%=request.getContextPath()%>/login/loginForm.jsp">로그인 하기</a>
	<br>
	<a href="<%=request.getContextPath()%>/member/create.do">회원가입</a>
	</h4>
	<%	
}
%>