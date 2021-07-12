
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js" type="text/javascript"></script>

<%-- <%
	String message = (String)request.getAttribute("message");
	if(StringUtils.isNotBlank(message)){
		
		%>
		<script type="text/javascript">
		alert("<%=message%>");
		</script>
		
		<%
	}
%> --%>

<c:if test="${not empty message }">
<script type="text/javascript">alert("${message}")</script>
</c:if>

<%-- 개억지 코드 --%>
<%-- ${	empty message ? "":"<script>alert('" }${message }${	empty message ? "" : "');</script>" } --%>
</head>
<body>



<form action="" method="post" id="memberForm">
		<table>
			<tr>
				<th>회원 ID</th>
				<td><input type="text" name="memId" required
					value="${member.memId }"> <label id="memId-error"
						class="error" for="memId">${errors["memId"]}</label></td>
			</tr>
			<tr>
				<th>비밀 번호</th>
				<td><input type="text" name="memPass" required
					value="${ member.memPass}"> <label
						id="memPass-error" class="error" for="memPass">${errors["memPass"]}</label></td>
			</tr>
			<tr>
				<th>회원 명</th>
				<td><input type="text" name="memName" required
					value="${ member.memName}"> <label
						id="memName-error" class="error" for="memName">${errors["memName"]}</label></td>
			</tr>
			<tr>
				<th>주민등록번호1</th>
				<td><input type="text" name="memRegno1"
					value="${ member.memRegno1}"> <label
						id="memRegno1-error" class="error" for="memRegno1">${errors["memRegno1"]}</label></td>
			</tr>
			<tr>
				<th>주민등록번호2</th>
				<td><input type="text" name="memRegno2"
					value="${ member.memRegno2}"> <label
						id="memRegno2-error" class="error" for="memRegno2">${errors["memRegno2"]}</label></td>
			</tr>
			<tr>
				<th>생일</th>
				<td><input type="date" name="memBir"
					value="${ member.memBir}"> <label id="memBir-error"
						class="error" for="memBir">${errors["memBir"]}</label></td>
			</tr>
			<tr>
				<th>우편 번호</th>
				<td><input type="text" name="memZip" required
					value="${ member.memZip}"> <label id="memZip-error"
						class="error" for="memZip">${errors["memZip"]}</label></td>
			</tr>
			<tr>
				<th>주소1</th>
				<td><input type="text" name="memAdd1" required
					value="${ member.memAdd1}"> <label
						id="memAdd1-error" class="error" for="memAdd1">${errors["memAdd1"]}</label></td>
			</tr>
			<tr>
				<th>주소2</th>
				<td><input type="text" name="memAdd2" required
					value="${ member.memAdd2}"> <label
						id="memAdd2-error" class="error" for="memAdd2">${errors["memAdd2"]}</label></td>
			</tr>
			<tr>
				<th>집 전화 번호</th>
				<td><input type="text" name="memHometel"
					value="${ member.memHometel}"> <label
						id="memHometel-error" class="error" for="memHometel">${errors["memHometel"]}</label></td>
			</tr>
			<tr>
				<th>회사 전화 번호</th>
				<td><input type="text" name="memComtel"
					value="${ member.memComtel}"> <label
						id="memComtel-error" class="error" for="memComtel">${errors["memComtel"]}</label></td>
			</tr>
			<tr>
				<th>이동 전화 번호</th>
				<td><input type="text" name="memHp"
					value="${ member.memHp}"> <label id="memHp-error"
						class="error" for="memHp">${errors["memHp"]}</label></td>
			</tr>
			<tr>
				<th>이메일 주소</th>
				<td><input type="text" name="memMail" required
					value="${ member.memMail}"> <label
						id="memMail-error" class="error" for="memMail">${errors["memMail"]}</label></td>
			</tr>
			<tr>
				<th>직업</th>
				<td><input type="text" name="memJob"
					value="${ member.memJob}"> <label id="memJob-error"
						class="error" for="memJob">${errors["memJob"]}</label></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="text" name="memLike"
					value="${ member.memLike}"> <label
						id="memLike-error" class="error" for="memLike">${errors["memLike"]}</label></td>
			</tr>
			<tr>
				<th>기념일 명</th>
				<td><input type="text" name="memMemorial"
					value="${ member.memMemorial}"> <label
						id="memMemorial-error" class="error" for="memMemorial">${errors["memMemorial"]}</label></td>
			</tr>
			<tr>
				<th>기념일 날짜</th>
				<td><input type="date" name="memMemorialday"
					value="${ member.memMemorialday}"> <label
						id="memMemorialday-error" class="error" for="memMemorialday">${errors["memMemorialday"]}</label></td>
			</tr>
			<tr>
				<th>마일리지</th>
				<td><input type="text" name="memMileage"
					value="${ member.memMileage}"> <label
						id="memMileage-error" class="error" for="memMileage">${errors["memMileage"]}</label></td>
			</tr>
			<tr>
				<th>삭제 여부</th>
				<td><input type="text" name="memDelete"
<%-- 					value="${ member.memDelete}" --%>
					> 
					<label id="memDelete-error" class="error" for="memDelete">${errors["memDelete"]}</label></td>
			</tr>
			<tr>
			<%
			if(session.getAttribute("authMember") == null){
				%>
				<td colspan="2"><input type="button" value="가입하기" id="create"></td>
				<%
			}else{
				%>
				<td colspan="2"><input type="submit" value="수정하기"></td>
				<%
			}
			%>
			</tr>
		</table>
	</form>
<script type="text/javascript">
$(function(){
	let memForm = $("#memberForm");
	memForm.validate();
	
	let createMem = $("#create").on("click",function(eve){
		eve.preventDefault();
		memForm.attr("action","${pageContext.request.contextPath}/member/create.do").submit();
		return false;
	})
	$("input").on("blur",function(){
		memForm.validate();
	})
})
</script>
</body>
</html>