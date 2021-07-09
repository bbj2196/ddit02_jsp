<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ProdVO prod = (ProdVO)request.getAttribute("prodVO");
%>

<table>
<tbody>
<tr><th>상품 코드</th><td><%=prod.getProdId()%></td></tr>
<tr><th>상품 명</th><td><%=prod.getProdName()%></td></tr>
<tr><th>상품 분류 코드</th><td><%=prod.getProdLgu()%></td></tr>
<tr><th>거래처 코드</th><td><%=prod.getProdBuyer()%></td></tr>
<tr><th>매입가</th><td><%=prod.getProdCost()%></td></tr>
<tr><th>소비자가</th><td><%=prod.getProdPrice()%></td></tr>
<tr><th>판매가</th><td><%=prod.getProdSale()%></td></tr>
<tr><th>상품 개략 설명</th><td><%=prod.getProdOutline()%></td></tr>
<tr><th>상품 상세 설며ㅇ</th><td><%=prod.getProdDetail()%></td></tr>
<tr><th>이미지(소)</th><td><%=prod.getProdImg()%></td></tr>
<tr><th>재고수량</th><td><%=prod.getProdTotalstock()%></td></tr>
<tr><th>신규일자(등록일)</th><td><%=prod.getProdInsdate()%></td></tr>
<tr><th>안전 재고 수량</th><td><%=prod.getProdProperstock()%></td></tr>
<tr><th>크기</th><td><%=prod.getProdSize()%></td></tr>
<tr><th>색상</th><td><%=prod.getProdColor()%></td></tr>
<tr><th>배달 특기 사항</th><td><%=prod.getProdDelivery()%></td></tr>
<tr><th>단위(수량)</th><td><%=prod.getProdUnit()%></td></tr>
<tr><th>총 입고 수량</th><td><%=prod.getProdQtyin()%></td></tr>
<tr><th>총 판매 수량</th><td><%=prod.getProdQtysale()%></td></tr>
<tr><th>개당 마일리지 점수</th><td><%=prod.getProdMileage()%></td></tr>
<tr>
<th>구매자정보</th>
<td>
<%
List<MemberVO> customer = prod.getMemberList();
if(customer == null || customer.size() == 0 ){
	out.print("구매자가 없습니다");
}else{
	out.println("<table>");
	for(MemberVO mem : customer){
	%>
	<tr>
	<th>이름</th>
	<td><%=mem.getMemName() %></td>
	<th>번호</th>
	<td><%=mem.getMemHp() %></td>
	<th>아이디</th>
	<td><%=mem.getMemId() %></td>
	</tr>
	<%
	}
	out.println("</table>");
}
%>

</td>
</tr>
</tbody>

</table>
</body>
</html>