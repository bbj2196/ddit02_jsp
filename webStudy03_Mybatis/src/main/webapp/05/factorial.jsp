<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
    left 입력을 통해 숫자를 입력받고,
    값이 변경되는 순간 서버로 비동기 요청을 살생시킴
    서버에서 factorial 연산을 수행한후,
    선택한 mime의 형태로 응답을 전송
    plain : "2! = 2"
    json : 
    {
        left : 2,
        operator : !,
        expression : "2! = 2"
    }
    xml : 
    <result>
        <left>2</left>
        <expression>2! = 2</expression>
    </result>
    <form action="<%=request.getContextPath()%>/05/factorial">
        <input type="radio" name="mime" value="json" data-type="json" data-success="parseJson" >JSON
        <input type="radio" name="mime" value="plain" data-type="text" data-success="parsePlain" checked>PLAIN
        <input type="radio" name="mime" value="xml" data-type="xml" data-success="parseXml">XML
        <input type="number" name="left" min="1" max="10"/>!
    </form>
<div id="resultArea">

</div>
<!-- 
<script type="text/javascript">
$("form").on("change",function(){
	$(this).submit()
})

$("form").on("submit",function(){
        event.preventDefault()
		datas=$("form").serialize()
		let start = datas.indexOf("mime")+5
		let end = datas.indexOf("&")
		
		let mime = datas.substring(start,end)
		
        if(mime == "plain"){
        	mime = "text"
        }else if(mime == "xml"){
        	mime = "html"
        }else if(mime == "json"){
        	
        }else{
        	console.log("형식에러")
        	return false;
       }
        console.log("마임 : "+mime)
		$.ajax({
			url : "<%=request.getContextPath()%>/05/factorial",
			data : datas,
			method : "post",
			dataType : mime,
			success : function(res) {
				console.log(res)
				if(mime == "json"){
					let result=""
					$.each(res,function(key,value){
						result+=key+" : "+value+"<br>"
					})
					$("#resultArea").html(result)	
				}else{
				$("#resultArea").text(res)
				}
			},
			error : function(xhr) {
				alert(xhr.status)
			}
		})
		return false;
})
</script>
 -->
<script type="text/javascript">
let resultArea = $("#resultArea");
	function parsePlain(res){
		console.log(res);
		resultArea.html(res)
	}
	function parseJson(res){
		console.log(res);
		resultArea.html(res.expression)
	}
	function parseXml(res){
		console.log(res);
		resultArea.html($(res).find("expression").text())
	}
	$("form:first").on("submit",function(event){
		event.preventDefault();
		let url = this.action;
		let data = $(this).serialize();
		let method = this.method;
		let radio = $(this).find("[name='mime']:checked");
		let dataType= $(radio).data("type");
		let success = eval($(radio).data("success"));
		
		console.log(success)
		
		console.log(data);
		$.ajax({
			url : url,
			data : data,
			method : method,
			dataType : dataType,
			success : success,
			error : function(xhr) {
				alert(xhr.status)
			}
		})
		console.log(this);
		console.log("서브밋호출");
		return false;
	}).find(":input").on("change",function(){
		$(this.form).submit();
	});
</script>
</body>
</html>