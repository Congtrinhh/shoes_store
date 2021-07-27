<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/utilities.css">
</head>
<body>

	<div class="container">
		<h1>admin info</h1>
		<div class="info">
			<h2 class="login-name">User name: ${logedInAdmin.ad_login_name}</h2>
			<div class="active-indicator">Status: ${logedInAdmin.ad_state}</div>
		</div>
		<div class="options">
			<a href="${pageContext.request.contextPath}/admin/manage">Manage
				your information</a>
		</div>
		
		<jsp:include page="./header.jsp" />
	</div>

</body>
</html>