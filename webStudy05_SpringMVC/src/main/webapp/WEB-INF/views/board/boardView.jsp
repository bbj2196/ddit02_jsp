<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
</head>
<c:if test="${not empty message }">
<script type="text/javascript">
alert("${message }");
</script>
<c:remove var="message" scope="session"/>
</c:if>
<body>
	<table class="table table-bordered">
		<tr>
			<th>글번호</th>
			<td>${board.boNo }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${board.boTitle }</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${board.boWriter }</td>
		</tr>
		<tr>
			<th>아이피</th>
			<td>${board.boIp }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${board.boEmail }</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${board.boDate }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${board.boHit }</td>
		</tr>
		<tr>
			<th>신고수</th>
			<td><span id="REPORT">${board.boRep }</span> <span class="incrementBtn" data-type="REPORT">신고!</span></td>
		</tr>
		<tr>
			<th>추천수</th>
			<td><span id="RECOMMEND">${board.boRec }</span> <span class="incrementBtn" data-type="RECOMMEND">추천!</span></td>
		</tr>
		<tr>
			<th>부모글</th>
			<td>${board.boParent }</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:forEach items="${board.attatchList }" var="attatch" varStatus="vs">
				<c:url value="/board/download.do" var="downURL">
				<c:param name="what" value="${attatch.attNo }"></c:param>
				</c:url>
					<a href="${downURL }">${attatch.attFilename }</a> ${not vs.last ? ",":"" }
				</c:forEach>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${board.boContent }</td>
		</tr>
		
			<tr>
			<td colspan="2">
			<c:url value="/board/boardInsert.do" var="insertURL">
			<c:param name="boParent" value="${board.boNo }"></c:param>
			</c:url>
			<c:url value="/board/boardUpdate.do" var="updateURL">
			<c:param name="boNo" value="${board.boNo }"></c:param>
			</c:url>
			<%-- <c:url value="/board/boardDelete.do" var="deleteURL">
			<c:param name="boNo" value="${board.boNo }"></c:param>
			</c:url> --%>
			<input type="button" value="답글쓰기" class="controlBtn" data-gopage="${insertURL }">
			<input type="button" value="수정하기" class="controlBtn" data-gopage="${updateURL }">
			<input id="delBtn" type="button" value="삭제하기">
			</td>
			</tr>
	</table>
	<form id="incrementForm" method="post" action="${cPath }/board/boardView.do" style="border: 5px solid red;">
		<h4>Hidden Form</h4>
		<input type="text" name="what" value="${board.boNo }" /> 
		<input type="text" name="countType" value="" /> 
	</form>
	<form id="delForm" action="${cPath }/board/boardDelete.do" method="post">
		<input type="text" name="boNo" value="${board.boNo }" required="required" /> 
		<input type="text" name="boPass" required="required" >
	</form>
	<script type="text/javascript">
		let incrementForm = $("#incrementForm").ajaxForm({
			dataType:"json",
			success:function(resp){
				for(let prop in resp){
					let value = resp[prop];
					if(value!="OK") return;
					let incArea = $("#"+prop);
					let before = incArea.text();
					incArea.text(parseInt(before)+1);
				}
			},
			resetForm : true
		});
		
		$(".incrementBtn").on("click", function(event){
			let countType = $(this).data("type");
			incrementForm.find("[name='countType']").val(countType);
			incrementForm.submit();
		}).css("cursor", "pointer");
		
		$("#delBtn").on("click",function(eve){
			eve.preventDefault();
			
			let pass = prompt("비밀번호를 입력해주세요");
			$("#delForm").find("[name='boPass']").val(pass);
			$("#delForm").submit();
			
			return false;
		})
		
	</script>
	<jsp:include page="/includee/footer.jsp" />
</body>
</html>