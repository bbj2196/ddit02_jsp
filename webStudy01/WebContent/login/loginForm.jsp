<%@page import="java.util.Map"%>
<%@page import="java.util.Objects"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    .error{
        color:red;
    }
</style>
<script src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js" type="text/javascript"></script>
</head>
<body>
<%
	//flash attribute
	String message = (String)session.getAttribute("message");
	session.removeAttribute("message");
%>
<div class="error">
<%-- <%=map.get("mem_id") %> --%>
<%=message %>
${errors }
</div>
<form name="loginForm" action="<%=request.getContextPath()%>/login/loginCheck.do" method="post">
<ul>
    <li>
<%--         id : <input data-msg-required="필수 데이터" required="required" type="text" name="mem_id" value="<%=Objects.toString(request.getAttribute("mem_id"),"")%>"> --%>
        id : <input data-msg-required="필수 데이터" required="required" type="text" name="mem_id" value=${mem_id}>
    </li>
    <li>
<!--         pass : <input pattern="^(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)(?=.*[!@#\\$%\\^\\&\\*]+).{4,8}$" required="required" data-msg-pattern="형식확인" type="text" name="mem_pass"> -->
        pass : <input data-msg-pattern="형식확인" type="text" name="mem_pass">
        <input type="submit" value="로그인">
    </li>
</ul>
</form>
<script type="text/javascript">
// 	$("[name='loginForm']").validate();
// 	$("[name='loginForm']").on("submit",function(){
//         let regexPtrn = this.mem_pass.pattern;
//         let pass = this.mem_pass.value;
//         let regexp = new RegExp("^(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)(?=.*[!@#\\$%\\^\\&\\*]+).{4,8}$","gm");
// //         let regexp = /^(?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)(?=.*[!@#\$%\^\&\*]+).{4,8}$/gm;
//         console.log("---------------------------------------------------------")
//         console.log(regexp.test(pass))
//         console.log(regexp.exec(pass))
// 		return true;
// 	})
</script>
</body>
</html>