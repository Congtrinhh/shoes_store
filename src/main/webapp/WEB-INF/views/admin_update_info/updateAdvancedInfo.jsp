<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/utilities.css">
</head>
<body>

	<div class="container">
		<jsp:include page="${pageContext.request.contextPath}/WEB-INF/views/crud/header.jsp" />
	
		<h1>Sửa thông tin admin</h1>
		<form id='' method='POST'
			action='${pageContext.request.contextPath}/admin-update-advanced'>
			<div class="form-control">
				<label for="ad_old_password">ad_old_password</label> <input
					type="text" name="ad_old_password" id="ad_old_password">
				<p class="error-message"></p>
			</div>

			<div class="form-control">
				<label for="ad_new_password">ad_new_password</label> <input
					type="text" name="ad_new_password" id="ad_new_password">
				<p class="error-message"></p>
			</div>

			<div class="form-control">
				<label for="ad_confirmed_password">ad_confirmed_password</label> <input
					type="text" name="ad_confirmed_password" id="ad_confirmed_password">
				<p class="error-message"></p>
			</div>
			
			<p class="error-message">${errorMessage}</p>

			<button class="btn" type="submit">Submit</button>
		</form>
	</div>

</body>
</html>