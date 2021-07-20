<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>

<table class="table table-striped">
	<thead class="thead-dark">
		<tr>
			<th>일련번호</th>
			<th>글번호 <span id="BO_NO" class="sortBtn">▽</span></th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수 <span id="BO_HIT" class="sortBtn">▽</span></th>
			<th>추천수 <span id="BO_REC" class="sortBtn">▽</span></th>
			<th>신고수 <span id="BO_REP" class="sortBtn">▽</span></th>
		</tr>
	</thead>
	<tbody>
		<c:set var="boardList" value="${pagingVO.dataList }" />
		<c:choose>
			<c:when test="${empty boardList }">
				<tr>
					<td colspan="8">조건에 맞는 글이 없음.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${boardList }" var="board" varStatus="status">
					<tr id="${board.boNo}">
						<td>${status.index + pagingVO.startRow}</td>
						<td>${board.boNo}</td>
						<td>${board.boTitle}</td>
						<td>${board.boWriter}</td>
						<td>${board.boDate}</td>
						<td>${board.boHit}</td>
						<td>${board.boRec}</td>
						<td>${board.boRep}</td>
					</tr>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea" class="d-flex justify-content-center">
					${pagingVO.pagingHTML }
				</div>
				<div id="searchUI" class="form-inline mt-3 d-flex justify-content-center pb-2" style="border: 5px solid green;">
					<h4 class="col-12 text-center">Search UI</h4>
					<select name="searchType" class="form-control mr-2">
						<option value>전체</option>
						<option value="title">제목</option>
						<option value="writer">작성자</option>
						<option value="content">내용</option>
					</select>
					<input class="form-control mr-2" type="text" name="searchWord" onchange="$(this).siblings('#searchBtn').click();"/>
					<input type="date" name="startDate" class="form-control"/>
					<input type="date" name="endDate" class="form-control"/>
					<button type="button" id="searchBtn"  class="btn btn-success">검색</button>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<input type="button" id="writer" value="글 쓰기">
<form id="searchForm"  style="border: 5px solid red;">
	<h4>Hidden Form</h4>
	<input type="text" name="searchType" placeholder="search type"/>
	<input type="text" name="searchWord" placeholder="search word"/>
	<input type="text" name="startDate"/>
	<input type="text" name="endDate"/>
	<input type="text" name="sortType"/>
	<input type="text" name="page" placeholder="page number"/>
</form>
<script src="${pageContext.request.contextPath }/resources/js/paging.js"></script>
<script type="text/javascript">
	$(function(){
		$("#writer").on("click",function(){location.href="${pageContext.request.contextPath}/board/boardInsert.do"})
		$("[name='searchType']").val("${pagingVO.simpleSearch.searchType}");
		$("[name='searchWord']").val("${pagingVO.simpleSearch.searchWord}");
		$("[name='startDate']").val("${pagingVO.simpleSearch.startDate}");
		$("[name='endDate']").val("${pagingVO.simpleSearch.endDate}");
		
		$("#searchForm").paging();
		
		$("tbody").on("click", "tr[id]" ,function(){
			let boNo = this.id;
			location.href = "${cPath}/board/boardView.do?what="+boNo;
		}).css("cursor", "pointer");
		
		$(".sortBtn").on("click", function(){
			$("[name='sortType']").val(this.id);
			searchForm.submit();
		}).css("cursor", "pointer");
	});
</script>
<jsp:include page="/includee/footer.jsp" />
</body>
</html>

