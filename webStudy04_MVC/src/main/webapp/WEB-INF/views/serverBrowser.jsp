<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
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
<script type="text/javascript">
$("#tree")
	.fancytree({
		source : {
			url : location.pathname,
			cache : false
		},
		lazyLoad : function(event, data) {
			var node = data.node;
			// Load child nodes via Ajax GET /getTreeData?mode=children&parent=1234
			data.result = {
				url : location.pathname,
				data : {
					base:node.key
				},
				cache : false
			};
		}
	});

	// $.ajax({
	// 	dataType : "json",
	// 	success : function(res) {

	// 	},
	// 	error : function(xhr) {
	// 		alert(xhr.status)
	// 	}
	// })
</script>
</body>
</html>