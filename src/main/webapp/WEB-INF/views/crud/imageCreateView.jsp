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

		<h3 class="msg">${message}</h3>
		<form id='' method='POST'
			action='${pageContext.request.contextPath}/create-image'>
			<div class="form-control">
				<label for="admin_id"></label> <input type="number" name="admin_id"
					placeholder="admin_id" id="admin_id">
			</div>
			<div class="form-control">
				<label for="product_line_id"></label> <input type="number"
					name="product_line_id" placeholder="product_line_id"
					id="product_line_id">
			</div>

			<div class="form-control">
				<label for="img_name"></label> <input type="text" name="img_name"
					placeholder="img_name" id="img_name">
			</div>
			<div class="form-control">
				<label for="img_location"></label> <input type="text"
					name="img_location" placeholder="img_location" id="img_location">
			</div>

			<button type="submit" class="btn">Submit</button>
		</form>
	</div>
	
	<img src="/resources/imgs/men/nike-force-1-le-younger-shoe-rg3gD7_2.jpg"> 

</body>
</html>