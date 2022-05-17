<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String context = request.getContextPath();
	%>
	<c:if test="${name != '' }">
		${name }
	</c:if>
	<c:if test="${id != '' }">
		${id }
	</c:if>
	<form action="<%=context%>/student/create">
		이름 : <input type="text" name="name"> <br />
		아이디 : <input type="text" name="id"> <br />
		<input type="submit" value="전송" ><br />
	</form>
</body>
</html>