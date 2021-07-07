<%@page import="kr.or.ddit.enumtype.OperatorType"%>
<%@page import="kr.or.ddit.vo.OperVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.OperInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <!--  -->
<form name="calForm" action="<%=request.getContextPath()%>/calculate_sam.do">
    <input type="radio" name="mime" value="plain" data-type="text" data-success="parsePlain" checked>PLAIN
    <input type="radio" name="mime" value="json" data-type="json" data-success="parseJson">JSON
    <br>
<input class="form-control" type="number" name="left">
<br>
<!-- 연산자UI 하드코딩하지 않기 -->
<%

	OperatorType[] operators=OperatorType.values();
%>
<select name="operator">
<%
	for(OperatorType oper : operators){
		%>
		<option value="<%=oper.name()%>"><%=oper.getSign() %></option>
		
		<%
	}
%>
</select>
<br>
<input class="form-control" type="number" name="right">
<input type="submit" value="=">
<span id="resultArea"></span>
</form>
<!--  -->
<script type="text/javascript">
let resArea = $("#resultArea");
let functions={
		parsePlain:function(resp){
			resArea.text(resp)
		},
		parseJson:function(resp){
			resArea.text(resp.expression)
		}
}
$("[name = 'calForm']").on("submit",function(event){
	event.preventDefault();
	let url = this.action;
	let formData = new FormData(this)
	console.log(formData)
	console.log(formData.keys())
	let data= {};

	for(let key of formData.keys()){
		console.log(key)
		console.log(formData.getAll(key))
		let values = formData.getAll(key)
		data[key] = values && values.length > 1 ? values : values[0]
	}
	console.log(data)

	let method = this.method
	let dataType = $(this).find("[name='mime']:checked").data("type");
	let success = $(this).find("[name='mime']:checked").data("success");
	let options = {
		url : "",
		data : "",
		method : "",
		dataType : "",
		success : function(res) {

		},
		error : function(xhr) {
			alert(xhr.status)
		}
	}
	options.url=url;
	options.method="post";
	options.dataType=dataType;
	//options.data=data
	options.data=JSON.stringify(data)
	options.contentType="application/json;charset=utf-8";
	options.success=functions[success]
	console.log(options)
	$.ajax(options)
	return false;
})
</script>
