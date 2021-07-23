<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="<%=request.getContextPath() %>/03/parameterProcess" method="get">
    <pre>
        일반텍스트 : <input type="text" name="param1">
        숫자 : <input type="number" name="param2">
        라디오버튼 : <input type="radio" name="param3" value="A">A <input type="radio" name="param3" value="B">B
        체크박스 : 
        <input type="checkbox" name="param4" value="C1">C1
        <input type="checkbox" name="param4" value="C2">C2
        <input type="checkbox" name="param4" value="C3">C3
        셀렉트(단일)
        <select name="param5">
            <option value="O1">option1</option>
            <option value="O2">option2</option>
        </select>
        셀렉트 (다중)
        <select name="param6" multiple size="3">
            <option>OPTIONA</option>
            <option>OPTIONB</option>
            <option>OPTIONC</option>
        </select>
        <textarea name="param7" cols="100" rows="5"></textarea>
        <button type="button">그냥버튼</button>
        <input type="submit" value="전송">
        <button type="reset">취소</button>
    </pre>
</form>
</body>
</html>