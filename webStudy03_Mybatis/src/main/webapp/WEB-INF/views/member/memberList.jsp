<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
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
<table border="1">
<thead>
<tr>
<th>아이디</th>
<th>이름</th>
<th>지역</th>
<th>휴대폰</th>
<th>이메일</th>
<th>마일리지</th>
</tr>
</thead>
<tbody>

<c:set var="dataList" value="${pagingVO.datalist}"></c:set>
<c:forEach items="${dataList}" var="data">
<tr id="${ data.memId}">
	<td>${data.memId}</td>
	<td>${data.memName}</td>
	<td>${data.memAdd1}</td>
	<td>${data.memHp}</td>
	<td>${data.memMail}</td>
	<td>${data.memMileage}</td>
	</tr>
</c:forEach>

</tbody>
<tfoot>
<tr>
<td colspan="6">
${pagingVO.pagingHTML}
<div id="searchUI">
	<select name="searchType">
		<option>전체</option>
		<option value="name">이름</option>
		<option value="address">지역</option>
		<option value="hp">휴대폰</option>
	</select>
	<input type="text" name="searchWord">
	<button type="button" id="searchBtn">검색</button>
</div>
</td>
</tr>
</tfoot>
</table>

<form id="searchForm">
	<input type="text" name="searchType">
	<input type="text" name="searchWord">
	<input type="text" name="page">
</form>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
// $("tbody").on("click","tr[id]",function(){
// 	let mem_id = this.id;
<%-- 	window.open("about:blank").location.href="<%=request.getContextPath()%>/member/memberView.do?who="+mem_id; --%>
// })




$(function(){
	$("[name='searchType']").val("${pagingVO.simpleSearch.searchType}");
	$("[name='searchWord']").val("${pagingVO.simpleSearch.searchWord}");
	$(".pageLink").on("click",function(eve){
		eve.preventDefault();
		let page=$(this).data("page");
		searchForm.find("[name='page']").val(page);
		searchForm.submit();
		
		return false;
	}).css("cursor","pointer");
let modal = $("#exampleModal");
	let searchForm = $("#searchForm");
	
	let searchUI = $("#searchUI").on("click","#searchBtn",function(){
		let inputs = searchUI.find(":input[name]")
		$(inputs).each(function(idx,input){
			let name = this.name
			let value = $(this).val();
			searchForm.find("[name='"+name+"']").val(value)
		})
		searchForm.submit()
	})
	
	
	modal.on("show.bs.modal",function(event){
		
		let trTag = event.relatedTarget;
		if(!trTag){
			return false;
		}
		let mem_id = trTag.id;
		$.ajax({
			url : "${pageContext.request.contextPath}/member/memberView.do",
			data:{"who":mem_id},
			method : "post",
			dataType : "html",
			success : function(res) {
				let body = $(modal.find(".modal-body"))
				body.html(res)
			},
			error : function(xhr) {
				alert(xhr.status)
			}
		})
		
	}).on("hidden.bs.modal",function(){
		modal.find(".modal-body").empty();
	})
	
	$("tbody").on("click","tr[id]",function(){
// 		let mem_id = this.id;
		modal.modal("show",this)
	})
	
})
</script>
<jsp:include page="/includee/footer.jsp"></jsp:include>
</body>
</html>