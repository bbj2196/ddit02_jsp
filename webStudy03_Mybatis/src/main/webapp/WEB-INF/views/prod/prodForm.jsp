<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js" type="text/javascript"></script>

<c:if test="${not empty message }">
<script type="text/javascript">alert("${message}")</script>
</c:if>
</head>
<body>

	<form action="" method="post" id="addForm" enctype="multipart/form-data">
		<table>
			<tr>
				<th>상품 명</th>
				<td><input type="text" required name="prodName" value="${prod.prodName}"><label
					id="prodName-error" class="error" for="prodName">${errors["prodName"]}</label></td>
			</tr>
			<tr>
				<th>상품 분류 코드</th>
				<td><select name="prodLgu">
						<option value="*">분류</option>
						<c:if test="${not empty lprodList}">
							<c:forEach var="lprod" items="${lprodList}">
								<option value="${lprod['lprodGu'] }" ${lprod['lprodNm'] eq prod.lprodNm ? "selected":""}>${lprod["lprodNm"] }</option>
							</c:forEach>
						</c:if>
				</select><label id="prodLgu-error" class="error" for="prodLgu">${errors["prodLgu"]}</label></td>
			</tr>
			<tr>
				<th>거래처 코드</th>
				<td><select name="prodBuyer">
						<option >거래처</option>
						<c:if test="${not empty buyerList }">
							<c:forEach var="buyerVO" items="${buyerList }">
								<option value="${buyerVO.buyerId}" class="${buyerVO.buyerLgu }" ${buyerVO['buyerName'] eq prod.buyer.buyerName? "selected":"" } >${buyerVO.buyerName }</option>
							</c:forEach>
						</c:if>
				</select><label id="prodBuyer-error" class="error" for="prodBuyer">${errors["prodBuyer"]}</label>
				</td>
			</tr>
			<tr>
				<th>매입가</th>
				<td><input type="text" required name="prodCost" value="${prod.prodCost}"><label
					id="prodCost-error" class="error" for="prodCost">${errors["prodCost"]}</label></td>
			</tr>
			<tr>
				<th>소비자가</th>
				<td><input type="text" required name="prodPrice"
					value="${prod.prodPrice}"><label id="prodPrice-error"
					class="error" for="prodPrice">${errors["prodPrice"]}</label></td>
			</tr>
			<tr>
				<th>판매가</th>
				<td><input type="text" required name="prodSale" value="${prod.prodSale}"><label
					id="prodSale-error" class="error" for="prodSale">${errors["prodSale"]}</label></td>
			</tr>
			<tr>
				<th>상품 개략 설명</th>
				<td><input type="text" required name="prodOutline"
					value="${prod.prodOutline}"><label id="prodOutline-error"
					class="error" for="prodOutline">${errors["prodOutline"]}</label></td>
			</tr>
			<tr>
				<th>상품 상세 설명</th>
				<td><input type="text" name="prodDetail"
					value="${prod.prodDetail}"><label id="prodDetail-error"
					class="error" for="prodDetail">${errors["prodDetail"]}</label></td>
			</tr>
			<tr>
				<th>이미지(소)</th>
				<td>
				<input type="file" name="prodImage"><br>
				<input type="text" required name="prodImg" value="${prod.prodImg}"><label
					id="prodImg-error" class="error" for="prodImg">${errors["prodImg"]}</label></td>
			</tr>
			<tr>
				<th>재고수량</th>
				<td><input type="text" required name="prodTotalstock"
					value="${prod.prodTotalstock}"><label
					id="prodTotalstock-error" class="error" for="prodTotalstock">${errors["prodTotalstock"]}</label></td>
			</tr>
			<tr>
				<th>안전 재고 수량</th>
				<td><input type="text" required name="prodProperstock"
					value="${prod.prodProperstock}"><label
					id="prodProperstock-error" class="error" for="prodProperstock">${errors["prodProperstock"]}</label></td>
			</tr>
			<tr>
				<th>크기</th>
				<td><input type="text" name="prodSize" value="${prod.prodSize}"><label
					id="prodSize-error" class="error" for="prodSize">${errors["prodSize"]}</label></td>
			</tr>
			<tr>
				<th>색상</th>
				<td><input type="text" name="prodColor"
					value="${prod.prodColor}"><label id="prodColor-error"
					class="error" for="prodColor">${errors["prodColor"]}</label></td>
			</tr>
			<tr>
				<th>배달 특기 사항</th>
				<td><input type="text" name="prodDelivery"
					value="${prod.prodDelivery}"><label id="prodDelivery-error"
					class="error" for="prodDelivery">${errors["prodDelivery"]}</label></td>
			</tr>
			<tr>
				<th>단위(수량)</th>
				<td><input type="text" name="prodUnit" value="${prod.prodUnit}"><label
					id="prodUnit-error" class="error" for="prodUnit">${errors["prodUnit"]}</label></td>
			</tr>
			<tr>
				<th>총 입고 수량</th>
				<td><input type="number" name="prodQtyin"
					value="${prod.prodQtyin}"><label id="prodQtyin-error"
					class="error" for="prodQtyin">${errors["prodQtyin"]}</label></td>
			</tr>
			<tr>
				<th>총 판매 수량</th>
				<td><input type="number" name="prodQtysale"
					value="${prod.prodQtysale}"><label id="prodQtysale-error"
					class="error" for="prodQtysale">${errors["prodQtysale"]}</label></td>
			</tr>
			<tr>
				<th>개당 마일리지 점수</th>
				<td><input type="number" name="prodMileage"
					value="${prod.prodMileage}"><label id="prodMileage-error"
					class="error" for="prodMileage">${errors["prodMileage"]}</label></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="button" id="addBtn" value="등록하기">
					<input type="button" value="목록으로" class="controlBtn" data-gopage="${pageContext.request.contextPath}/prod/prodList.do">
				</td>
			</tr>
		</table>
		<input type="hidden" name="prodId" value="${prod.prodId}">
	</form>
	<jsp:include page="/includee/footer.jsp"></jsp:include>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/prodForm.js"></script>

</body>
</html>
