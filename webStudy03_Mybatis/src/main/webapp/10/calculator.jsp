<%@page import="kr.or.ddit.vo.OperVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.OperInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<body>
    <!--  -->
<form action="<%=request.getContextPath()%>/caculate.do">
    <input type="radio" name="mime" value="text" data-success="parsePlain" checked>PLAIN
    <input type="radio" name="mime" value="json" data-success="parseJson">JSON
    <br>
<input class="form-control" type="number" name="left">
<br>
<!-- 연산자UI 하드코딩하지 않기 -->
<%
// <input type="radio" name="oper" value="plus">
	OperInfoVO infoVO = (OperInfoVO)application.getAttribute("operInfo");
	List<OperVO>list = infoVO.getOperlist();
	for(OperVO vo : list){
		%>
		<input type="radio" name="oper" value="<%=vo.getText()%>"><span><%=vo.getSign() %></span>
		<%
	}
%>
<!--  -->
<br>
<input class="form-control" type="number" name="right">
<input type="submit" value="=">
<span id="resultArea"></span>
</form>
<!--  -->
<script type="text/javascript">
let resultArea = $("#resultArea")
function parsePlain(res){
	console.log(res);
	resultArea.html(res)
}
function parseJson(res){
	console.log(res);
	resultArea.html(res.expression)
}
$("form").on("submit",function(event){
	event.preventDefault();
	let url = this.action;
	let data = $(this).serialize();
	let method = this.method;
	let radio = $(this).find("[name='mime']:checked");
	let dataType= radio.val();
	let success = eval($(radio).data("success"));
	
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
	return false;
})

</script>
