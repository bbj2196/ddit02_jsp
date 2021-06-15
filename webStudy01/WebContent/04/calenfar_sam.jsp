<%@page import="java.util.Arrays"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 클라이언트가 선택할수있는 타임존
	// 출력되는 서버의 시간이 해당타임존에 맞게
	request.setCharacterEncoding("utf-8");
	String yearStr = request.getParameter("year");
	String monthStr = request.getParameter("month");
	
	Locale loc = request.getLocale();

	String language = request.getParameter("language");
	if(language != null && !language.isEmpty()){
		loc=Locale.forLanguageTag(language);
	}
	
	DateFormatSymbols dfs = DateFormatSymbols.getInstance(loc);
	
	Calendar cal = getInstance();    
	int today = cal.get(DAY_OF_MONTH);
	int toMonth = cal.get(MONTH);
	int toYear = cal.get(YEAR);
	
	
	
	if(yearStr != null && yearStr.matches("\\d{4}")){
		
		cal.set(YEAR, Integer.parseInt(yearStr));
	}
	if(monthStr != null && monthStr.matches("\\d{1,2}")){
		cal.set(MONDAY,Integer.parseInt(monthStr));
	}
	int year = cal.get(YEAR);
	int month = cal.get(MONTH);
    cal.set(DAY_OF_MONTH, 1);
    int offset = cal.get(DAY_OF_WEEK)-1;
    int lastDate = cal.getActualMaximum(DAY_OF_MONTH);
    cal.add(MONTH, -1);
    int beforeYear = cal.get(YEAR);
    int beforeMonth = cal.get(MONTH);
    cal.add(MONTH,2);
    int nextYear= cal.get(YEAR);
    int nextMonth= cal.get(MONTH);
    cal.add(MONTH, -1);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/calendar.jsp</title>
<style type="text/css">
.sun{
	color:red;
}

.sat{
	color:blue;
}
.current{
	background-color:green;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

</head>
<body>
<h4>현재 서버의 시각 : <%=String.format(loc,"%tc",cal) %></h4>
<%=today%>
<%=toMonth %>
<%=toYear %>
<h4>
<a href="#" class="moveA" data-year="<%=beforeYear%>" data-month="<%=beforeMonth%>">이전달</a>
<%=String.format(loc,"%1$tY,%1$tB",cal) %>
<a href="#" class="moveA" data-year="<%=nextYear%>" data-month="<%=nextMonth%>">다음달</a>
</h4>
<form id="calendarForm">
<input type="number" name="year" placeholder="<%=year %>" value="<%=year %>"/>
<select name="month">
	<option value>월 선택</option>
	<%
	String optionPtrn = "<option %s value='%s'>%s</option>";
	String[] months = dfs.getMonths();
		for(int i = 0;i<12;i++){
			String selected = (i ==month)?"selected":"";
			out.println(String.format(optionPtrn,selected, i,months[i]));
		}
	%>
</select>
<select name="language">
<%
Locale[] locales = Locale.getAvailableLocales();
for(Locale tmpLoc:locales){
	String tag = tmpLoc.toLanguageTag();
	String name= tmpLoc.getDisplayName();
	String selected = (tmpLoc.equals(loc))?"selected":"";
	if(!name.isEmpty())
	out.println(String.format(optionPtrn, selected,tag,name));
}
%>
</select>
</form>
<table>
<thead>
	<tr>
	<%
	
	String[] weekDays = dfs.getWeekdays();
	String thPtrn = "<th>%s</th>";
	for(int idx = SUNDAY ; idx <=SATURDAY;idx++){
		out.println(String.format(thPtrn, weekDays[idx]));
	}
	%>
	</tr>
</thead>
	<tbody>
		<%
			String pattern = "<td %s>%s</td>";
			int number = 1;
			for(int row =1;row<=6;row++){
				out.println("<tr>");
				for(int col = SUNDAY; col <=SATURDAY; col++){
					int dateNum = number++ - offset;
					String printNum = dateNum >= 1 && dateNum <=lastDate ? dateNum+"" : "&nbsp;";
					String classType = "";
					if(SUNDAY == col){
						classType+="class='sun'";
					}else if(SATURDAY == col){
						classType+="class='sat'";
					}
					//
					if(dateNum == today && cal.get(MONTH) == toMonth && cal.get(YEAR) == toYear){
						classType+="class='current'";
					}
					//
					out.println(String.format(pattern, classType, printNum));
				}
				out.println("</tr>");
			}
		%>
	</tbody>
</table>
<script type="text/javascript">
	let calForm = $("#calendarForm").on("change",":input",function(){
		console.log(this.form);
		console.log(calForm[0])
		this.form.submit();
// 		calForm.submit();	
	}).on("submit",function(){
		console.log("==========================================")
		return true;
	});
	
	$(".moveA").on("click",function(event){
		event.preventDefault();
		let year = $(this).data("year");
		let month = $(this).data("month");
		calForm.find("input[name='year']").val(year);
		$(calForm.get(0).month).val(month);
		calForm.submit();
		return false;
	})
</script>
</body>
</html>



<%

//	switch(col){
//	case SUNDAY:
//		classType="class='sun'";
//		break;
//	case SATURDAY:
//		classType = "class='sat'";
//		break;
//	}
//	if(dateNum == today && cal.get(MONTH) == toMonth && cal.get(YEAR) == toYear) classType="class='current'";
%>