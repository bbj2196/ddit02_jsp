<%@page import="kr.or.ddit.enumtype.BrowserType"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="com.sun.xml.internal.bind.v2.model.nav.Navigator"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="java.util.regex.Pattern"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	const PATTERN = "당신의 브라우저는 %s 입니다 OS의 종류는 %o 입니다";
	let resultArea = $("#resultArea"); 
	$("a:first").on("click",function(){
		event.preventDefault();
		$.ajax({
			url:"<%=request.getContextPath()%>/04/getBrowserName",
			data:"",
			method:"",
			dataType:"json", // request header(Accept) / response header(ContentType)
							// text : text/plain, html : text/html, json : application/json, script: text/javascript
			success : function(res){
				let meg = null;
				let os = null;
				console.log(typeof res);
				if(typeof res == "object"){
					meg = res.browser
					os = res.os
				}else{
					meg="???"
					os="???"
				}
				resultArea.empty();
				resultArea.append($("<p>").html(PATTERN.replace("%s",meg).replace("%o",os)));
			},
			error : function(xhr){
				console.log(xhr)
			}
		})
		return false;
	})

});
</script>
</head>
<body>
<a>브라우저의 이름 받아오기(비동기)</a>
<div id="resultArea"></div>
<!-- 사용자의 브라우저를 식별하고, 각브라우저에 맞는 메시지 출력 -->
<!-- 당신의 브라우저는 000입니다 형식으로 포멧팅 메시지를 사용함 -->
<%
// String megForm = "<p>당신의 브라우저는 %s 입니다</p>";
String header = request.getHeader("user-agent");
// String secChUa = request.getHeader("sec-ch-ua");
// // Mozlia  IE
// // Edg		엣지
// // chrom,safari	크롬
// // safari	사파리
// //   '/'를 기준으로 양옆
// String regex = "[\\S]+/[\\S]+"; 
// Pattern pattern = Pattern.compile(regex);
// Matcher matcher = pattern.matcher(header);
// // while(matcher.find()){
// // 	out.println("문자 : "+matcher.group()+"<br>");
// // }

// String browser= BrowserType.parseUserAgent(header);
// out.println(String.format(megForm, browser));
%>
<%=header %>
</body>
</html>