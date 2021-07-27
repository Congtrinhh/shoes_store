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

	<!-- ta thấy nên có 1 file css có các class có thể dùng nhiều lần -->

	<div class="container">
		<jsp:include page="./header.jsp" />
		
		<h3 class="msg">${message}</h3>
		<form method="post"
			action="${pageContext.request.contextPath}/create-admin" accept-charset="utf-8">
			<div class="form-control">
				<label for="ad_name">Admin name</label> <input type="text"
					name="ad_name" id="ad_name" placeholder="name">
			</div>

			<div class="form-control">
				<label for="ad_login_name">Admin login name (as easy as
					possible)</label> <input type="text" name="ad_login_name"
					id="ad_login_name" placeholder="login name">
			</div>

			<div class="form-control">
				<label for="ad_password">Password (as easy as possible)</label> <input
					type="password" name="ad_password" id="ad_password"
					placeholder="password">
			</div>

			<div class="form-control">
				<label for="ad_email">Email (as easy as possible)</label> <input
					type="email" name="ad_email" id="ad_email" placeholder="email">
			</div>

			<div class="form-control">
				<label for="ad_phone_number">Phone number (10 digits)</label> <input
					type="number" name="ad_phone_number" id="ad_phone_number"
					placeholder="ad_phone_number">
			</div>

			<div class="form-control">
				<input type="hidden" name="ad_state" id="ad_state" value="0">
			</div>

			<div class="form-control">
				<input type="hidden" name="ad_remember_token" id="ad_remember_token"
					value="0">
			</div>

			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>

</body>
</html>