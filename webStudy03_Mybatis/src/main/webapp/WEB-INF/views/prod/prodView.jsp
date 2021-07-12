<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tbody>
			<tr>
				<th>상품 코드</th>
				<td>${prodVO.prodId}</td>
			</tr>
			<tr>
				<th>상품 명</th>
				<td>${prodVO.prodName}</td>
			</tr>
			<tr>
				<th>상품 분류 코드</th>
				<td>${prodVO.prodLgu}</td>
			</tr>
			<tr>
				<th>거래처 코드</th>
				<td>${prodVO.prodBuyer}</td>
			</tr>
			<tr>
				<th>매입가</th>
				<td>${prodVO.prodCost}</td>
			</tr>
			<tr>
				<th>소비자가</th>
				<td>${prodVO.prodPrice}</td>
			</tr>
			<tr>
				<th>판매가</th>
				<td>${prodVO.prodSale}</td>
			</tr>
			<tr>
				<th>상품 개략 설명</th>
				<td>${prodVO.prodOutline}</td>
			</tr>
			<tr>
				<th>상품 상세 설며ㅇ</th>
				<td>${prodVO.prodDetail}</td>
			</tr>
			<tr>
				<th>이미지(소)</th>
				<td>${prodVO.prodImg}</td>
			</tr>
			<tr>
				<th>재고수량</th>
				<td>${prodVO.prodTotalstock}</td>
			</tr>
			<tr>
				<th>신규일자(등록일)</th>
				<td>${prodVO.prodInsdate}</td>
			</tr>
			<tr>
				<th>안전 재고 수량</th>
				<td>${prodVO.prodProperstock}</td>
			</tr>
			<tr>
				<th>크기</th>
				<td>${prodVO.prodSize}</td>
			</tr>
			<tr>
				<th>색상</th>
				<td>${prodVO.prodColor}</td>
			</tr>
			<tr>
				<th>배달 특기 사항</th>
				<td>${prodVO.prodDelivery}</td>
			</tr>
			<tr>
				<th>단위(수량)</th>
				<td>${prodVO.prodUnit}</td>
			</tr>
			<tr>
				<th>총 입고 수량</th>
				<td>${prodVO.prodQtyin}</td>
			</tr>
			<tr>
				<th>총 판매 수량</th>
				<td>${prodVO.prodQtysale}</td>
			</tr>
			<tr>
				<th>개당 마일리지 점수</th>
				<td>${prodVO.prodMileage}</td>
			</tr>
			<tr>
				<th>구매자정보</th>
				<td><c:set var="memList" value="${prodVO.memberList }"></c:set>
					<c:if test="${not empty memList}">
						<c:forEach var="mem" items="${memList}">
							<table>
								<tr>
									<th>이름</th>
									<td>${mem.memName}</td>
									<th>번호</th>
									<td>${mem.memHp}</td>
									<th>아이디</th>
									<td>${mem.memId}</td>
								</tr>
							</table>

						</c:forEach>
					</c:if> <c:if test="${empty memList }">
						<span>구매자가 없습니다</span>
					</c:if></td>
			</tr>
		</tbody>
	</table>
</body>
</html>