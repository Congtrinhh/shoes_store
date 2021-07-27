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
			action='${pageContext.request.contextPath}/create-size-product-line'>
			<div class="form-control">
				<label for="product_line_id"></label> <input type="number"
					name="product_line_id" placeholder="product_line_id"
					id="product_line_id">
			</div>
			<div class="form-control">
				<label for="size_id"></label> <input type="number" name="size_id"
					placeholder="size_id" id="size_id">
			</div>
			<div class="form-control">
				<label for="spl_quantity"></label> <input type="number"
					name="spl_quantity" placeholder="spl_quantity" id="spl_quantity">
			</div>
			<button type="submit" class="btn">Submit</button>
		</form>
	</div>

</body>
</html>