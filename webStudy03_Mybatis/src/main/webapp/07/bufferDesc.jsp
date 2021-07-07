<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"autoFlush="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<pre>
	buffer : 속도 차이를 보완하기 위한 임시 저장공간 (8kb)
	page 지시자의 buffer 속성으로 설정 변경.
	현재 버퍼의 설정 크기 : <%=out.getBufferSize() %>
	버퍼의 잔여크기 : <%=out.getRemaining() %>
	<%
	for(int i=1;i<=100;i++){
		if(out.getRemaining()<100){
			out.flush(); // 응답전송
// 			out.clear(); // 한번 이상 flush된 이후에는 에러
// 			out.clearBuffer();
		}
// 		if(i==99){
// 			throw new NullPointerException("강제오류 발생");
// 		}
		out.println(i+"번째 반복");
	}
	%>

</pre>
</body>
</html>