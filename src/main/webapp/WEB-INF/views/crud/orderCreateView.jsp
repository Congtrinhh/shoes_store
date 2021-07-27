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
			action='${pageContext.request.contextPath}/create-order'>
			<div class="form-control">
				<label for="admin_id"></label> <input type="number" name="admin_id"
					placeholder="admin_id" id="admin_id">
			</div>
			<div class="form-control">
				<label for="user_id"></label> <input type="number" name="user_id"
					placeholder="user_id" id="user_id">
			</div>
			<div class="form-control">
				<label for="or_total_cost"></label> <input type="number"
					name="or_total_cost" placeholder="or_total_cost" id="or_total_cost">
			</div>
			<div class="form-control">
				<label for="or_shipping_cost"></label> <input type="number"
					name="or_shipping_cost" placeholder="or_shipping_cost"
					id="or_shipping_cost">
			</div>
			<div class="form-control">
				<label for="or_status"></label> <input type="number"
					name="or_status" placeholder="or_status"
					id="or_status">
			</div>
			<button type="submit" class="btn">Submit</button>
		</form>
	</div>

</body>
</html>