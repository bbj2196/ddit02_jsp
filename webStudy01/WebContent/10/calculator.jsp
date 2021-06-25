<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <!--  -->
<form action="<%=request.getContextPath()%>/caculate.do">
    <input type="radio" name="mime" value="plain">PLAIN
    <input type="radio" name="mime" value="json">JSON
<input class="form-control" type="number" name="left">
<!-- 연산자UI 하드코딩하지 않기 -->
연산자UI : operator
<input class="form-control" type="number" name="right">
<input type="submit" value="전송">
<span id="resultArea"></span>
</form>
<!--  -->
</body>
</html>