<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/js/fancytree/skin-win8/ui.fancytree.min.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/fancytree/jquery.fancytree-all-deps.min.js"></script>
</head>

<body>
<div id="tree"></div>

<form id="fform" action="<%=request.getContextPath()%>/employee/empCRUD.do">

<table>
<tr>
<td>EMPNO</td>
<td><input type="text" name="empno"  id="empno" >   </td>
</tr>
<tr>
<td>ENAME</td>                          
<td><input type="text" name="ename"  id="ename" >   </td>
</tr>
<tr>
<td>JOB</td>
<td><input type="text" name="job"  id="job" >      </td>
</tr>
<tr>
<td>MGR</td>
<td><input type="text" name="mgr"  id="mgr" >      </td>
</tr>
<tr>
<td>HIREDATE</td>
<td><input type="text" name="hiredate"  id="hiredate"> </td>
</tr>
<tr>
<td>SAL</td>
<td><input type="text" name="sal"  id="sal" >     </td>
</tr>
<tr>
<td>COMM</td>
<td><input type="text" name="comm"  id="comm" >    </td>
</tr>
<tr>
<td>DEPTNO</td>
<td><input type="text" name="deptno"  id="deptno" >   </td>
</tr>
</table>

</form>
<script type="text/javascript">
let ffrom = $("#fform").on("submit",function(){
	
// 	$.ajax({
<%-- 		url : "<%=request.getContextPath()%>/employee/empCRUD.do", --%>
// 		data : "",
// 		method : "get",
// 		dataType : "json",
// 		success : function(res) {
			
// 		},
// 		error : function(xhr) {
// 			alert(xhr.status)
// 		}
// 	})
});
$("#tree").fancytree({
	source:{
		url:location.pathname,
		cache:true,
		
	},
	lazyLoad : function(event, data) {
	let node = data.node;
	console.log(node)
	// Load child nodes via Ajax GET /getTreeData?mode=children&parent=1234
	data.result = {
		url : location.pathname,
		data : {
			mgr:node.key
		},
		cache : false
		
	};
	
},
activate : function(event,data){
	let node = data.node
 	$.ajax({
			url : "<%=request.getContextPath()%>/employee/empCRUD.do",
	 		data : {empno:node.key},
	 		method : "get",
	 		dataType : "json",
	 		success : function(res) {
				$("#empno").val(res.empno)
				$("#ename").val(res.ename)
				$("#job").val(res.job)
				$("#mgr").val(res.mgr)
				$("#hiredate").val(res.hiredate)
				$("#sal").val(res.sal)
				$("#comm").val(res.comm)
				$("#deptno").val(res.deptno)
	 		},
	 		error : function(xhr) {
	 			alert(xhr.status)
	 		}
	 	})
	
}
})

</script>
</body>
</html>