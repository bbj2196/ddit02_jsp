<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@page import="java.util.Set"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<%

	String msg = (String)session.getAttribute("message");

	if(msg !=null){
		%>
		<script type="text/javascript">alert(<%=msg%>)</script>
		<%
	}
%>
</head>
<body>
	<table>
<%
	MemberVO member=(MemberVO)request.getAttribute("member");
if(member == null){
	out.print("<tr><td>해당 멤버는 없습니다</td></tr>");
}else
{
	%>
		<tr>
			<th>회원 ID</th>
			<td><%=member.getMemId()%></td>
		</tr>
		<tr>
			<th>비밀 번호</th>
			<td><%=member.getMemPass()%></td>
		</tr>
		<tr>
			<th>회원 명</th>
			<td><%=member.getMemName()%></td>
		</tr>
		<tr>
			<th>주민등록번호1</th>
			<td><%=member.getMemRegno1()%></td>
		</tr>
		<tr>
			<th>주민등록번호2</th>
			<td><%=member.getMemRegno2()%></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><%=member.getMemBir()%></td>
		</tr>
		<tr>
			<th>우편 번호</th>
			<td><%=member.getMemZip()%></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><%=member.getMemAdd1()%></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><%=member.getMemAdd2()%></td>
		</tr>
		<tr>
			<th>집 전화 번호</th>
			<td><%=member.getMemHometel()%></td>
		</tr>
		<tr>
			<th>회사 전화 번호</th>
			<td><%=member.getMemComtel()%></td>
		</tr>
		<tr>
			<th>이동 전화 번호</th>
			<td><%=member.getMemHp()%></td>
		</tr>
		<tr>
			<th>이메일 주소</th>
			<td><%=member.getMemMail()%></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><%=member.getMemJob()%></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><%=member.getMemLike()%></td>
		</tr>
		<tr>
			<th>기념일 명</th>
			<td><%=member.getMemMemorial()%></td>
		</tr>
		<tr>
			<th>기념일 날짜</th>
			<td><%=member.getMemMemorialday()%></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=member.getMemMileage()%></td>
		</tr>
		<tr>
			<th>삭제 여부</th>
			<td><%=member.getMemDelete()%></td>
		</tr>
		<tr>
		<th>구매기록</th>
		<td><table>
			<thead>
			<tr>
			<th>상품분류</th>
			<th>거래처명</th>
			<th>거래처소재지</th>
			<th>상품명</th>
			<th>구매가</th>
			<th>판매가</th>
			<th>마일리지</th>
			</tr>
			</thead>
			<tbody>
			<%
			Set<ProdVO>prodList=member.getProdList();
			if(prodList.isEmpty()){
				%>
				<tr>
				<td colspan="7">구매기록 없음</td>
				</tr>
				<%
			}else{
				for(ProdVO prod:prodList){
					%>
					<tr>
					<td><%=prod.getLprodNm() %></td>
					<td><%=prod.getBuyer().getBuyerName() %></td>
					<td><%=prod.getBuyer().getBuyerAdd1() %></td>
					<td><%=prod.getProdName() %></td>
					<td><%=prod.getProdPrice() %></td>
					<td><%=prod.getProdSale() %></td>
					<td><%=prod.getProdMileage() %></td>
					</tr>
					<%
				}
			}
			%>
			</tbody>
		</table></td>
		
		</tr>
		<%
			MemberVO authMember = (MemberVO)session.getAttribute("authMember");
			boolean rendering = true;
			if(rendering){
		%>
		<tr>
			<td colspan="2">
				<input type="button" class="controlBtn" value="수정" data-gopage="<%=request.getContextPath()%>/member/update.do">
				<input type="button" value="탈퇴" id="deleteBtn">
				<form method="post" action="<%=request.getContextPath()%>/member/memberDelete.do" id = "deleteForm">
				<input type="hidden" name="memPass">
				</form>
				
			</td>
		</tr>
		<%	}
}
%>
	</table>
	<jsp:include page="/includee/footer.jsp"></jsp:include>
	<script type="text/javascript">
	$(function(){
		$("#deleteBtn").on("click",function(eve){
			eve.preventDefault();
			let pass =$("table td")[1]
			
			$("[name='memPass']").val(pass.innerText)
			$("#deleteForm").submit();
			return false;
		})
		$(".controlBtn").on("click",function(){
			let gopage = $(this).data("gopage");
			if(gopage){
			location.href=gopage;
			}
		})
	})
	</script>
</body>
</html>