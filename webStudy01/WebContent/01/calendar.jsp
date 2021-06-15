<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
  // 오늘 날짜로 달력 취득
  Calendar calendar = Calendar.getInstance();	
  Calendar today = Calendar.getInstance();
  
/*   // request 객체로 부터 넘어온 값이 있으면 처리
  if(request.getParameter("year") != null){
	  calendar.set(Calendar.YEAR, Integer.parseInt(request.getParameter("year")));
  }
  if(request.getParameter("month") != null){
	  calendar.set(Calendar.MONTH, Integer.parseInt(request.getParameter("month")));
  }
   */
  // 날을 1일로 셋팅
  calendar.set(Calendar.DATE, 1);
  
  // 1일의 '요일'을 취득
  int oneDayNum = calendar.get(Calendar.DAY_OF_WEEK);
  // 현재달의 최대 일 수
  int monthMaxNum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
  // 현재달의 주 수
  int weekSize = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
  // 현재 '년'을 취득
  int year = calendar.get(Calendar.YEAR);
  // 현재 '월'을 취득(0월 부터 11월 까지)
  int month = calendar.get(Calendar.MONTH) + 1;
  // 현재 '일'을 취득
  int day = calendar.get(Calendar.DATE);

%>
<meta charset="UTF-8">
<title>02/calendar.jsp</title>
<style>
form{
	margin: 100px 200px;
	width : 300px;
	height : 400px; 
	background: #424242;
	color: white;
	padding : 10px;
}
h4{
	display: inline-block;
	float: left;
	margin : 0;
	margin-left: 15px;
	font-weight: normal;
}
#up, #down{
	display: inline-block;
	float: right;
	background: none;
	border: none;
	font-size: 20px;
	font-weight: bold;
	color: white;
}
table{
	clear: both;
}
.t1{

	width:50px; 
	text-align: center;
	font-size: 13px;
} 
</style>
</head>
<body>  
<form>
<h4><%=year %>년 <%=month %>월</h4> <input type="button" value="∧" id="up"><input type="button" value="∨" id="down">
<br><br>
<table>
<tr>
<td class="t1">일</td>
<td class="t1">월</td>
<td class="t1">화</td>
<td class="t1">수</td>
<td class="t1">목</td>
<td class="t1">금</td>
<td class="t1">토</td>
</tr>

</table>
</form>
</body>
</html>