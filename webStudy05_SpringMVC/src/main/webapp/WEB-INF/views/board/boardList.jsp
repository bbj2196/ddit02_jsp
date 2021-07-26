<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
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
	<tbody id="listBody">
		<c:set var="boardList" value="${pagingVO.dataList }" />
		<c:choose>
			<c:when test="${empty boardList }">
				<tr>
					<td colspan="8">조건에 맞는 글이 없음.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${boardList }" var="board">
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
					<input type="date" name="startDate" class="form-control mr-2"/>
					<input type="date" name="endDate" class="form-control mr-2"/>
					<button type="button" id="searchBtn"  class="btn btn-success mr-2">검색</button>
					<button type="button" class="controlBtn btn btn-primary"
						data-gopage="${cPath }/board/boardInsert.do"
					>새글쓰기</button>
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm"  style="border: 5px solid red;" action="${cPath }/board/boardList.do">
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
		$(document).ajaxComplete(function(event, xhr, options){
			searchForm.find("[name='page']").val("");
		}).ajaxError(function(event, xhr, options, error){
			console.log(event);
			console.log(xhr);
			console.log(options);
			console.log(error);
		});
		
		$("[name='searchType']").val("${pagingVO.simpleSearch.searchType}");
		$("[name='searchWord']").val("${pagingVO.simpleSearch.searchWord}");
		$("[name='startDate']").val("${pagingVO.simpleSearch.startDate}");
		$("[name='endDate']").val("${pagingVO.simpleSearch.endDate}");
		$("[name='sortType']").val("${pagingVO.simpleSearch.sortType}");
		
		function makeTrTag(board){
			return $("<tr>").append(
				$("<td>").html(board.rnum),		
				$("<td>").html(board.boNo),		
				$("<td>").html(board.boTitle),		
				$("<td>").html(board.boWriter),		
				$("<td>").html(board.boDate),		
				$("<td>").html(board.boHit),		
				$("<td>").html(board.boRec),		
				$("<td>").html(board.boRep)		
			).prop("id", board.boNo);
		}
		
		let tbody = $("#listBody");
		let pagingArea = $("#pagingArea"); 
		$("#searchForm").paging()
						.ajaxForm({
							dataType:"json",
							success:function(pagingVO){
								tbody.empty();
								pagingArea.empty();
								let boardList = pagingVO.dataList;
								let trTags = [];
								if(boardList && boardList.length > 0){
									$(boardList).each(function(idx, board){
										trTags.push( makeTrTag(board) );
									});
									pagingArea.html(pagingVO.pagingHTML);
								}else{
									trTags.push(
										$("<tr>").html(
											$("<td>").attr("colspan", "8").html("조건에 맞는 결과가 없음.")	
										)			
									);
								}
								tbody.append(trTags);
							}
						});
		
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

