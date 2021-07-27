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
			action='${pageContext.request.contextPath}/create-product-in-order'>
			<div class="form-control">
				<label for="product_line_id"></label> <input type="number"
					name="product_line_id" placeholder="product_line_id"
					id="product_line_id">
			</div>
			<div class="form-control">
				<label for="order_id"></label> <input type="number" name="order_id"
					placeholder="order_id" id="order_id">
			</div>
			<div class="form-control">
				<label for="pio_size"></label> <input type="number" name="pio_size"
					placeholder="pio_size" id="pio_size">
			</div>

			<div class="form-control">
				<label for="pio_color"></label> <input type="text" name="pio_color"
					placeholder="pio_color" id="pio_color">
			</div>

			<div class="form-control">
				<label for="pio_quantity"></label> <input type="number"
					name="pio_quantity" placeholder="pio_quantity" id="pio_quantity">
			</div>
			<div class="form-control">
				<label for="pio_discount_percent"></label> <input type="number"
					name="pio_discount_percent" placeholder="pio_discount_percent"
					id="pio_discount_percent">
			</div>

			<button type="submit" class="btn">Submit</button>
		</form>
	</div>

</body>
</html>