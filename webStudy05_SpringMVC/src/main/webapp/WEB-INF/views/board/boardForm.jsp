<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js"
	type="text/javascript"></script>
	
	<script type="text/javascript" src="${cPath }/resources/ckeditor/ckeditor.js"></script>
</head>
<body>
${message }
	<form:form action="" method="post" enctype="multipart/form-data" commandName="board">
	<input type="hidden" name="boNo" value="${board.boNo }">
		<table border="1">
			<tbody>
				<tr>
					<%-- <th>부모글번호</th>
					<td>
					<input type="text" name="boParent" value="${board.boParent}">
					<form:errors path="boParent" element="label" id="boParent-error" cssClass="error" for="boParent"></form:errors>	
					</td> --%>
				</tr>
				<tr>
					<th>게시글제목</th>
					<td><input type="text" name="boTitle" required
						value="${board.boTitle}"><form:errors path="boTitle" element="label" id="boTitle-error"
						cssClass="error" for="boTitle"></form:errors></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" name="boEmail"
						value="${board.boEmail}"><form:errors  element="label" id="boEmail-error"  path="boEmail"
						class="error" for="boEmail">${errors.boEmail}</form:errors></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="text" name="boPass" required
						value=""><form:errors id="boPass-error"  element="label" path="boPass"
						class="error" for="boPass">${errors.boPass}</form:errors></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="boWriter" required
						value="${board.boWriter}"><form:errors id="boWriter-error"  element="label" path="boWriter"
						class="error" for="boWriter">${errors.boWriter}</form:errors></td>
				</tr>
				<tr>
					<th>IP주소</th>
					<td><input type="text" name="boIp" required
						value="${pageContext.request.remoteAddr }" readonly><form:errors id="boIp-error"  element="label" path="boIp"
						class="error" for="boIp">${errors.boIp}</form:errors>remote= ${pageContext.request.remoteAddr }/local = ${pageContext.request.localAddr }</td>
				</tr>
				<c:if test="${not empty board.attatchList }">
				<tr>
				<th>저장된 파일</th>
				<td>
						<c:forEach items="${board.attatchList }" var="attat">
						<span class="fileGroup" data-attno=" ${attat.attNo }">${attat.attFilename },</span>
						</c:forEach>
						</td>
				</tr>
				</c:if>
				<tr>
				<th>첨부파일</th>
				<td><input type="file" name="boFiles" multiple><form:errors id="boFiles-error"  element="label" path="boFiles"
						class="error" for="boFiles">${errors.boFiles}</form:errors></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea id="boContent" name="boContent" rows="5" cols="25" ></textarea>
					<form:errors id="boContent-error"  element="label" path="boContent"
						class="error" for="boContent">${errors.boContent}</form:errors></td>
				</tr>
				<tr>
				<td colspan="2">
				<input type="submit" value="작성">
				<input type="button" id="list" value="리스트로 돌아가기">
				</td>
				</tr>
			</tbody>
		</table>
		<div id="hidden">
		
		</div>
		</form:form>

	
	
	<jsp:include page="/includee/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
	CKEDITOR.replace('boContent',{
		filebrowserImageUploadUrl:"${cPath}/board/uploadImage.do?type=Images"
	})
	let file;
	$(function(){
		$("#list").on("click",function(){
			location.href="${pageContext.request.contextPath}/board/boardList.do";
		})
		$(".fileGroup").on("click",function(){
			file=$(this);
			let val = $(this).data("attno");
			$(this).hide();
			$("<input>").attr("name","delAttNos").val(val).appendTo($("#hidden"));
		})
			
			
			


	})
	</script>
</body>
</html>