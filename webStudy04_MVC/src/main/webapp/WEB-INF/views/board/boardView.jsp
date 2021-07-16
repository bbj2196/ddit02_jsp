<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>

	<table border="1" class="table table-hover">
		<tbody>
			<tr>
				<th>작성일</th>
				<td>${freeboard.boDate}</td>
			</tr>
			<tr>
				<th>신고수</th>
				<td>${freeboard.boRep}
				<input type="button" id="rep" value="신고하기">
				</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${freeboard.boHit}</td>
			</tr>
			<tr>
				<th>추천수</th>
				<td>${freeboard.boRec}
				<input type="button"id="rec" value="추천하기">
				</td>
			</tr>
			<tr>
				<th>부모글번호</th>
				<td>${freeboard.boParent}</td>
			</tr>
			<tr>
				<th>게시글 번호</th>
				<td>${freeboard.boNo}</td>
			</tr>
			<tr>
				<th>게시글제목</th>
				<td>${freeboard.boTitle}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${freeboard.boWriter}</td>
			</tr>
			<tr>
				<th>IP주소</th>
				<td>${freeboard.boIp}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${freeboard.boEmail}</td>
			</tr>
<!-- 			<tr> -->
<!-- 				<th>비밀번호</th> -->
<%-- 				<td>${freeboard.boPass}</td> --%>
<!-- 			</tr> -->
			<tr>
				<th>내용</th>
				<td>${freeboard.boContent}</td>
			</tr>
			<tr>
			<th>첨부파일목록</th>
			<td>
			<c:if test="${not empty freeboard.attatchList }">
			<table border="1">
			<thead>
			<tr>
			<th>파일번호</th>
			<th>게시글번호</th>
			<th>파일명</th>
			<th>크기요약</th>
			<th>다운로드수</th>
			</tr>
			</thead>
			<tbody>
			
			<c:forEach items="${freeboard.attatchList }" var="attatch">
								<tr>
									<td>${attatch.attNo}</td>
									<td>${attatch.boNo}</td>
									<td>${attatch.attFilename}</td>
									<td>${attatch.attFancysize}</td>
									<td>${attatch.attDownCnt}</td>
								</tr>
							</c:forEach>
			
			</tbody>
			</table>
			</c:if>
			</td>
			</tr>
			<tr>
			<th>댓글 기록</th>
			<td>
			<c:if test="${not empty freeboard.replyList }">
			<table border="1">
			<thead>
								<tr>
									<th>댓글번호</th>
									<th>게시글번호</th>
									<th>작성자</th>
									<th>댓글내용</th>
									<th>이메일</th>
									<th>날짜</th>
									<th>부모글번호</th>
								</tr>
							</thead>
			<tbody>
			<c:forEach items="${freeboard.replyList }" var="freereply">
									<tr>
										<td>${freereply.repNo}</td>
										<td>${freereply.boNo}</td>
										<td>${freereply.repWriter}</td>
										<td>${freereply.repContent}</td>
										<td>${freereply.repMail}</td>
										<td>${freereply.repDate}</td>
										<td>${freereply.repParent}</td>
									</tr>
								</c:forEach>
			</tbody>
			</table>
			</c:if>
			</td>
			</tr>
		</tbody>
	</table>
	<form id="hiddenForm" action="">
	<input type="hidden" name="type">
	<input type="hidden" name="what" value="${freeboard.boNo }">
	</form>
	<script type="text/javascript">
	$(function(){
		let hidden = $("#hiddenForm");
		hidden.on("submit",function(eve){
			eve.preventDefault();
			
			$.ajax({
				url : "${pageContext.request.contextPath }",
				data : hidden.serialize(),
				method : "post",
				dataType : "text",
				success : function(res) {
					console.log(res);
				},
				error : function(xhr) {
					alert(xhr.status)
				}
			})
			return false;
		})
		$("#rec").on("click",function(){
			hidden.find("[name='type']").val("rec");
			hidden.submit();
		});
		$("#rep").on("click",function(){
			hidden.find("[name='type']").val("rep");
			hidden.submit();
		});
		
	})
	</script>
	<jsp:include page="/includee/footer.jsp"></jsp:include>
</body>
</html>