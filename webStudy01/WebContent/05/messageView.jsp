<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/messageView</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<button class="msgBtn" data-lang="ko">한글 메시지 가져오기</button>
<button class="msgBtn" data-lang="en">영문 메시지 가져오기</button>
<h4></h4>
<script type="text/javascript">

	$(".msgBtn").on("click",function(){
		let lang = $(this).data("lang")
		let datas={}
		if(lang){
			data.lang=lang;
		}
		$.ajax({
			url : "<%=request.getContextPath()%>/05/messageServiceWithLocale",
			data: datas,
			method:"post",
			dataType : "json",
			success : function(res) {
				console.log(res)
				$("h4").text(res.bow)
			},
			error : function(xhr) {
				console.log(xhr.status)
			}
		})
	})
</script>
</body>
</html>