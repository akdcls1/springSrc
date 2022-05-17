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
	<h1>Login</h1>
	<c:if test="${not empty pageContext.request.userPrincipal }">
		${pageContext.request.userPrincipal }
		<p> is Login-In</p>
		USER ID : ${pageContext.request.userPrincipal.name }님 환영합니다 <br />
	</c:if>
	<c:if test="${empty pageContext.request.userPrincipal }">
		<p> is Log-Out</p>
	</c:if>
	<a href="${pageContext.request.contextPath}/j_spring_security_logout">Log Out</a>
</body>
</html>