<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/fileUploadUsingFilter.do" method="post" enctype="multipart/form-data">
<input type="text" name="textParam" value="${textParam}">
<c:remove var="textParam" scope="session"/>
<input type="file" name="filePart" accept="image/*">
<br>
<input type="submit" value="전송전송">
</form>
<c:if test="${not empty imageURL}">
<img alt="${imageFile.originalFilename }" src="<c:url value='${imageURL}'/>">
<c:remove var="imageURL" scope="session"/>
</c:if>
</body>
</html>