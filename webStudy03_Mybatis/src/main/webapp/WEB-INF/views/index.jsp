<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h4>웰컴 페이지</h4>
<%
request.setCharacterEncoding("utf-8");
MemberVO user = (MemberVO)session.getAttribute("authMember");
String message = (String)session.getAttribute("message");
session.removeAttribute("message");
if(message!=null && !message.isEmpty()){
	out.println(message);
}
if(user != null){ 
%>
<h4>
	<a href="<%=request.getContextPath()%>/mypage.do"><%=user.getMemName() %>님</a>
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
<script>
	console.log($);
	$(function(){
		console.log($.fn.modal);
	})
</script>