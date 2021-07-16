<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<div>

</div>
	<table border="1" class="table table-hover">
		<thead>
			<tr>
				<th>일련번호</th>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>추천수</th>
				<th>신고수</th>
			</tr>
		</thead>
		<tbody class="table-striped ">
			<c:if test="${empty pagingVO.datalist }">
				<td colspan="8">데이터가 없습니다</td>
			</c:if>
			<c:if test="${not empty pagingVO.datalist }">
				<c:forEach items="${pagingVO.datalist}" var="board" >
					<tr>
						<td>번호</td>
						<td>${board.boNo }</td>
						<td>${board.boTitle }</td>
						<td>${board.boWriter }</td>
						<td>${board.boDate }</td>
						<td>${board.boHit }</td>
						<td>${board.boRec }</td>
						<td>${board.boRep }</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	<jsp:include page="/includee/footer.jsp"></jsp:include>
</body>
</html>