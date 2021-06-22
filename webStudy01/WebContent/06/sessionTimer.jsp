<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath() %>/resources/js/custom.js" type="text/javascript"></script>
<script>
	$(function () {
		let ele = $("#timerArea").sessionTimer({
			timeout:<%=session.getMaxInactiveInterval() %>,
			url:"<%=request.getContextPath()%>/sessionExtend",
			yesBtn:$("#yesBtn"),
			noBtn:$("#noBtn"),
			box:$("#msgArea")
		});
		console.log("=============================================================")
		console.log(ele)
	});
</script>
</head>
<body>
<h4>세션 타이머</h4>
<%=session.getId() %> : <%=session.getMaxInactiveInterval() %>
<h4 id="timerArea"></h4>
1. 1초마다 디스카운트
2. 1분남은 시점에 메시지를 출력.
	세션의 연장여부 확인
3. 세션연장 > 예 
	타이머 리셋, 새션연장을 위한 요청발생(비동기 /sessionExtend, body 미포함)
<!-- 	<div id="msgArea"> -->
<!-- 		세션을 연장하겠습니까?<br> -->
<!-- 		<input type="button" id="yesBtn" value="예"> -->
<!-- 		<input type="button" id="noBtn" value="아니오"> -->
<!-- 	</div> -->
	
<script type="text/javascript">
// let msgbox = $("#msgArea")
// let timer = $("#timerArea")
<%-- let max = <%=session.getMaxInactiveInterval() %>; --%>
// let initval =max
// let flag=false
// let inval=setInterval(function () {
// 	if(initval >60 || flag){
// 			msgbox.hide()
// 		}else{
// 			msgbox.show()
// 		}
// 		initval--
// 		let time = Math.floor(initval/60)+":"+(initval%60)
// 		timer.text(time)
// 		if(initval <=0){
// 			clearInterval(inval)
// 		}
// 	},1000)
	
// $("#yesBtn").on("click",function () {
// 	$.ajax({
<%-- 		url:"<%=request.getContextPath()%>/sessionExtend", --%>
// 		method:"head",
// 		success : function () {
// 			initval=max
// 		},
// 		error : function (xhr) {
// 			alert("에러코드 : "+xhr.status)
// 		}
// 	})	
// })
// $("#noBtn").on("click",function () {
// 	flag = true;
// })
</script>
</body>
</html>