<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/lib/utilities.css" />
<style>
.container h2 {
	display: inline-block;
	margin-right: 50px;
}
</style>
</head>
<body>
	<div class="container">

		<jsp:include page="./views/crud/header.jsp" />
		
		<h1 class="py">
			<span style="color: orange; text-decoration: underline;">ADMIN</span>,
			WELCOME
		</h1>
		<h2>Do data entry</h2>


	</div>
</body>
</html>