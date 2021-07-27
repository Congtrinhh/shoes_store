<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/lib/utilities.css" />
</head>
<body>

	<div class="container">
		<jsp:include page="./header.jsp" />

		<h3 class="msg">${message }</h3>
		<form action="${pageContext.request.contextPath}/create-category"
			method="POST">
			<div class="form-control">
				<label for="admin_id">Admin id</label> <input type="number"
					name="admin_id" id="admin_id" placeholder="admin id">
			</div>
			<div class="form-control">
				<label for="c_slug">Category slug</label> <input type="text"
					name="c_slug" id="c_slug" placeholder="category slug">
			</div>
			<div class="form-control">
				<label for="c_name">Category name</label> <input type="text"
					name="c_name" id="c_name" placeholder="category name">
			</div>
			<button type="submit" class="btn">Submit</button>
		</form>
	</div>
</body>
</html>