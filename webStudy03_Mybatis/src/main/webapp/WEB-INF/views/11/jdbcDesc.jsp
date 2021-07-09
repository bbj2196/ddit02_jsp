<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<h4>JDBC</h4>
<pre>
	:데이터 베이스 프로그래밍 단계
	1. 벤더가 제공하는 드라이버를 찾고 빌드패스 추가
	<%
// 		// 2. 드라이버 클래스 로딩
// 		Class.forName("oracle.jdbc.driver.OracleDriver");
// 		String url ="jdbc:oracle:thin:@localhost:1521/xe";
// 		String user ="pc13";
// 		String password ="java";
// 		// 3. Connection 객체 생성
// 		Connection conn=DriverManager.getConnection(url, user, password);
// 	 데이터베이스로부터 raw데이터 조회후
// 	 모든 property value에 조회날짜를 추가할것
// 	DataBasePropertyDAO dao = null;
		
	%>              
	2. 드라이버 클래스 로딩
	3. Connection 생성
	4. 쿼리 객체 생성
		- Statement
		- PreparedStatement
		- CallableStatement
	5. 쿼리 실행 : DML
		- ResultSet executeQuery() : select
		- int executeUpdate() : insert, update, delete
	6. 질의 결과 사용
	7. 자원 해제
</pre>
<table>
	<thead>
	<tr>
		<th>PROPERTY_NAME</th>
		<th>PROPERTY_VALUE</th>
		<th>DISCRPTION</th>
	</tr>
	</thead>
		<tbody>
			<%
// 	for(DataBasePropertyVO vo : list){
// 		out.println("<tr>");
// 		out.println("<td>"+vo.getProperty_name()+"</td>");
// 		out.println("<td>"+vo.getPorerty_value()+"</td>");
// 		out.println("<td>"+vo.getDescription()+"</td>");
// 		out.println("</tr>");
// 	}
	
	%>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="3">
				<div id="pagingArea">
				
				</div>
					<div id="searchUI">
						<input type="text" name="search"> 
						<input type="submit"value="검색" id="searchBtn">
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
<form id="searchForm">
	<input type="text" name="search">
	<input type="text" name="page">
</form>
<script type="text/javascript">
$(document).ajaxError(function(event,xhr,options,error){
	console.log(event)
	console.log(xhr)
	console.log(options)
	console.log(error)
}).ajaxComplete(function(event,xhr,options){
	searchForm.find("[name='page']").val("");
	searchForm.get[0].reset();
})
function makeTdFromData(propVO){
	let tds = [];
	for( let propName in propVO){
		let td = $("<td>").html(propVO[propName])
		tds.push(td)
	}
	return tds;
}
let searchUI = $("#searchUI").on("click","#searchBtn",function(){
	let inputs = searchUI.find(":input[name]")
	$(inputs).each(function(idx,input){
		let name = this.name
		let value = $(this).val();
		searchForm.find("[name='"+name+"']").val(value)
		
	})
	searchForm.submit()
})

	let pagingArea = $("#pagingArea").on("click",".pageLink",function(){
		let page = $(this).data("page");
		searchForm.find("[name='page']").val(page)
		searchForm.submit()
	})
	let searchForm=$("#searchForm").on("submit",function(eve){
		eve.preventDefault();
		let url = this.action;
		let method = this.method;
		let formData = new FormData(this);
// 		let data=[];
// 		for (let key of formData.keys()){
// 			data[key]=formData.get(key);
// 		}
		let data = $(this).serialize()
		$.ajax({
			url:url,
			method : method,
			data:data,
			dataType : "json",
			success : function(res) {
				let dataList = res.dataList;
				
				let trs=[];
				let table=$("tbody");
				pagingArea.empty();
				table.empty();
				$.each(res,function(i,v){  
					let row =  $("<tr>").append(makeTdFromData(v));
					
					row.appendTo(table)
				})
				pagingArea.html(res.pagingHTML)
			}
		})
		return false;
	}).submit()
	
</script>
</body>
</html>