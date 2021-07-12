<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>

<div id="searchUI" >
	<h4>Search UI</h4>
	분류 : 
	<select name="prodLgu">
		<option value>분류</option>
		<c:forEach var="lprod" items="${lprodList}">
		<option value="${lprod['lprodGu'] }">${lprod["lprodNm"] }</option>
		</c:forEach>
	</select>
	거래처 : 
	<select name="prodBuyer">
		<option value>거래처</option>
		<c:forEach var="buyerVO" items="${buyerList }">
		<option value="${buyerVO.buyerId}" class="${buyerVO.buyerLgu }">${buyerVO.buyerName }</option>
		</c:forEach>
	</select>
	상품명 : <input type="text" name="prodName" value="${pagingVO.detailSearch.prodName }"/>
	<input type="button" value="검색" id="searchBtn"/>
</div>
<table class="table">
	<thead>
		<tr>
			<th>상품명</th>
			<th>상품분류</th>
			<th>거래처</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
		</tr>
	</thead>
	<tbody>
	</tbody>
	<tfoot>
		<tr>
			<td colspan="6">
				<div id="pagingArea">
				</div>
			</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm" >
	<h4>Hidden Form</h4>
	<input type="text" name="page" />
	<input type="text" name="prodLgu" value="${pagingVO.detailSearch.prodLgu }"/>
	<input type="text" name="prodBuyer" value="${pagingVO.detailSearch.prodBuyer }"/>
	<input type="text" name="prodName" value="${pagingVO.detailSearch.prodName }"/>
</form>

<script src="${pageContext.request.contextPath }/resources/js/paging.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.form.min.js"></script>
<script type="text/javascript">
	$(function(){
		$(document).ajaxComplete(function(event, xhr, options){
			searchForm.get(0).reset();
		}).ajaxError(function(event, xhr, options, error){
			console.log(event);
			console.log(xhr);
			console.log(options);
			console.log(error);
		});
		let prodBuyer = $("select[name='prodBuyer']");
		$("select[name='prodLgu']").on("change", function(){
			let lgu = $(this).val();
			prodBuyer.find("option").hide();
			prodBuyer.find("option."+lgu).show();
			prodBuyer.find("option:first").show();
		});
		
		let tbody = $("table>tbody").on("click", "tr" ,function(){
			let prod = $(this).data("prod");
			if(!prod) return false;
			let prodId = prod.prodId;
			location.href = "<%=request.getContextPath() %>/prod/prodView.do?what="+prodId;
		});
		let pagingArea = $("#pagingArea");
		function makeTrTag(prod){
			return $("<tr>").append(
				$("<td>").html(prod.prodName),		
				$("<td>").html(prod.lprodNm),		
				$("<td>").html(prod.buyer.buyerName),		
				$("<td>").html(prod.prodCost),		
				$("<td>").html(prod.prodPrice),		
				$("<td>").html(prod.prodMileage)		
			).data("prod", prod);
		}
		
		let searchForm = $("#searchForm").paging({
			pagingArea : "#pagingArea",
			pageLink : ".pageLink",
			searchUI : "#searchUI",
			btnSelector : "#searchBtn",
			pageKey : "page",
			pageParam : "page"
		}).ajaxForm({
			dataType:"json",
			success:function(pagingVO){
				tbody.empty();
				pagingArea.empty();
				let prodList = pagingVO.datalist;
				let trTags = [];
				if(prodList){
					$(prodList).each(function(idx, prod){
						trTags.push( makeTrTag(prod) );
					});
					pagingArea.html(pagingVO.pagingHTML);
				}else{
					trTags.push(
						$("<tr>").html(
							$("<td>").attr("colspan", "6").html("조건에 맞는 결과가 없음.")	
						)			
					);
				}
				tbody.append(trTags);
			} // success end
		}).submit();
	});
</script>
<jsp:include page="/includee/footer.jsp" />
</body>
</html>











