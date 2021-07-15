<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>El에서 집합객체사용</h4>
<pre>
	<%
	String[] arr = new String[]{"arry1","arry2"};
	List<String>list = Arrays.asList(arr);
	Set<String>set = Collections.singleton("setValue");
	Map<String,Object>map = new HashMap<>();
	map.put("key1", "value1");
	map.put("key2", "value2");
	map.put("key 3", "value3");
	map.put("key-4", "value4");
	
	pageContext.setAttribute("arr", arr);
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("set", set);
	pageContext.setAttribute("map", map);
	%>
	
	${arr[1] }, ${arr[5] }
	${list[0] }, ${list[5] }
	${set }
	${map.key2 }, ${map["key 3"] }, ${map.key-4 } =>null - 4
</pre>
</body>
</html>