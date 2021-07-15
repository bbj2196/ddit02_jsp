<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
form{
	margin: 100px 200px;
	width : 300px;
	height : auto; 
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
td{
	height : 40px;
	width:50px; 
	text-align: center;
	font-size: 13px;
} 
</style>
</head>
<%!
// 일자(숫자)를 넣으면 요일글씨 반환 함수 선언
String day(int num){
	String date="";
	if(num>0 && num <=7){
	String[] strDay={"일","월","화","수","목","금","토"};
	date = strDay[num-1];
	}else{
		date="오류";
	}
	return date;
}
%>
<%

// 파라미터(년도_월) 받아옴
String date = request.getParameter("date");
// 캘린더(현재날짜로) 생성
Calendar cal = Calendar.getInstance();

// 만약 파라미터가 있따면 해당날짜로 캘린더 설정
if(date!=null && !date.isEmpty()){
	String[] data= date.split("_");
cal.set(Calendar.YEAR, Integer.parseInt(data[0]));
cal.set(Calendar.MONTH, Integer.parseInt(data[1]));
}

// 현재날짜
int nowDay = cal.get(Calendar.DAY_OF_MONTH);

// 캘린더를 일자를 1일로 설정해서 1일의 요일 구하기
cal.set(Calendar.DAY_OF_MONTH, 1);
int startday = cal.get(Calendar.DAY_OF_WEEK);

// 해당월의 마지막 날짜 구하기
int lastday = cal.getActualMaximum(Calendar.DATE);

// 캘린더의 일자를 마지막 날로 설정해서 마지막 주구하기
cal.set(Calendar.DAY_OF_MONTH, lastday);
int lastweek = cal.get(Calendar.WEEK_OF_MONTH);
%>
<body>
<form>
<table border="1">
<tr>
<td colspan="5"><%=cal.get(Calendar.YEAR)+"년"+(cal.get(Calendar.MONTH)+1)+"월" %></td>
<%
// 파라미터 전달용 캘린더 생성
Calendar paramcal = (Calendar)cal.clone();
// 윗버튼용 1달전 캘린더
paramcal.add(Calendar.MONTH,-1);
%>
<td><button name="date" value="<%=paramcal.get(Calendar.YEAR)+"_"+paramcal.get(Calendar.MONTH)%>">▲</button></td>
<%
// 아래버튼용 1달후 캘린더
paramcal.add(Calendar.MONTH,+2);
%>
<td><button name="date" value="<%=paramcal.get(Calendar.YEAR)+"_"+paramcal.get(Calendar.MONTH)%>">▼</button></td>
</tr>
<tr>
<%
// 요일 출력
for(int i =1;i<=7;i++){
	%>
<td><%=day(i) %></td>
	<%
}	%>
</tr>
<tr>
<%
// 1일전 공백 td
for(int i=1;i<startday;i++){
	%>
	<td> </td>
	<%
}
// 첫주일자 td 시작
// days = 첫주차 마지막날
int days = 8-startday;
for(int i = 1;i<=days;i++){
	%>
	<td><%=i%></td>
	<%
}
%>
</tr>


<%
// 마지막 주차 만큼 나머지 일수 출력
for(int i=2 ; i<=lastweek;i++){
	%>
	<tr>
	<%
	for(int j=1;j<=7;j++){
		// 마지막 날 이후 공백 출력
		%>
	<td><%=++days > lastday ? "":days %></td>
	<%	
	}
	%>
	</tr>
	<%
}

%>
</table>
</form>
</body>
</html>