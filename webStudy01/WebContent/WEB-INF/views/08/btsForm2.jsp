<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/btsForm.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('select').on('change',function(){
		$(this).parent().submit();
	})
})
</script>
</head>
<body>
<form method="post">
	<select name="btsMember">
		<option value="">선택</option>
		<option value="bui">bui</option>
		<option value="jhop">jhop</option>
		<option value="jimin">jimin</option>
		<option value="jin">jin</option>
		<option value="jungkuk">jungkuk</option>
		<option value="rm">rm</option>
		<option value="suga">suga</option>
	</select>
</form>
</body>
</html>