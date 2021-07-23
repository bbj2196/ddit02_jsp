<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

	<form action="" method="post" enctype="multipart/form-data">
	<input type="hidden" name="boNo" value="${board.boNo }">
		<table border="1">
			<tbody>
				<tr>
					<th>부모글번호</th>
					<td><input type="text" name="boParent"
						value="${board.boParent}"><label id="boParent-error"
						class="error" for="boParent">${errors.boParent}</label></td>
				</tr>
				<tr>
					<th>게시글제목</th>
					<td><input type="text" name="boTitle" required
						value="${board.boTitle}"><label id="boTitle-error"
						class="error" for="boTitle">${errors.boTitle}</label></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td><input type="text" name="boEmail"
						value="${board.boEmail}"><label id="boEmail-error"
						class="error" for="boEmail">${errors.boEmail}</label></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="text" name="boPass" required
						value=""><label id="boPass-error"
						class="error" for="boPass">${errors.boPass}</label></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="boWriter" required
						value="${board.boWriter}"><label id="boWriter-error"
						class="error" for="boWriter">${errors.boWriter}</label></td>
				</tr>
				<tr>
					<th>IP주소</th>
					<td><input type="text" name="boIp" required
						value="${pageContext.request.remoteAddr }" readonly><label id="boIp-error"
						class="error" for="boIp">${errors.boIp}</label>remote= ${pageContext.request.remoteAddr }/local = ${pageContext.request.localAddr }</td>
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
				<td><input type="file" name="files" multiple><label id="boFiles-error"
						class="error" for="boFiles">${errors.boFiles}</label></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea id="boContent" name="boContent" rows="5" cols="25" ></textarea>
					<label id="boContent-error"
						class="error" for="boContent">${errors.boContent}</label></td>
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
		</form>

	
	
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