<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h4>성능체크</h4>
<pre>
	소요시간 : process time + latency time
	<a href="oneConnOneProcess.jsp">한번연결과 한번처리</a> 10ms
	<a href="100Conn100Process.jsp">100번연결과 100번처리</a> 950ms
	<a href="oneConn100Process.jsp">1번연결과 100번처리</a> 18ms
	


</pre>
</body>
</html>