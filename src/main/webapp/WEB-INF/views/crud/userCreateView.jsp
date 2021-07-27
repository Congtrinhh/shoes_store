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
			action='${pageContext.request.contextPath}/create-user'>
			<div class="form-control">
				<label for="admin_id">admin id</label> <input type="number"
					name="admin_id" placeholder="admin id" id="admin_id">
			</div>

			<div class="form-control">
				<label for="u_name">u name</label> <input type="text" name="u_name"
					placeholder="u_name" id="u_name">
			</div>

			<div class="form-control">
				<label for="u_login_name">u login name</label> <input type="text"
					name="u_login_name" placeholder="u_login_name" id="u_login_name">
			</div>

			<div class="form-control">
				<label for="">u password</label> <input type="password"
					name="u_password" placeholder="u_password" id="u_password">
			</div>

			<div class="form-control">
				<label for="">u email</label> <input type="email" name="u_email"
					placeholder="u_email" id="u_email">
			</div>

			<div class="form-control">
				<label for="u_phone_number">u phone number</label> <input
					type="text" name="u_phone_number" placeholder="u_phone_number"
					id="u_phone_number">
			</div>

			<div class="form-control">
				<label for="u_address">u address</label> <input type="text"
					name="u_address" placeholder="u_address" id="u_address">
			</div>

			<button type="submit" class="btn">Submit`</button>
		</form>
	</div>

</body>
</html>