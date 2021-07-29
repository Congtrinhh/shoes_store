<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>User login</title>
<!-- jQuery -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- jQuery validation -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"
	integrity="sha512-37T7leoNS06R80c8Ulq7cdCDU5MNQBwlYoy1TX/WUsLFC2eYNqtKlV0QjH7r8JpG/S0GUMZwebnVFLPd6SU5yg=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/utilities.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/user_login/style.css">
<!-- Js file - for validation with jquery validation -->
<script defer
	src="${pageContext.request.contextPath}/resources/js/user_login/validation.js"></script>
</head>
<body>


	<div id="app" class="container">
		<a href="${pageContext.request.contextPath}/" class="to-home-page"><<  Trang chủ</a>
		
		<form id='userForm' method='POST'
			action='${pageContext.request.contextPath}/login'>
			<h1 class="form-header">Đăng nhập</h1>

			<div class='form-text'>
				<input type='text' name='name' id='' placeholder='' required>
				<label for='' class='label-name'>
					<p class='content-name__placeholder'>Tên tài khoản</p>
				</label> <span></span>
			</div>

			<div class='form-text'>
				<input type='password' name='password' id='' placeholder='' required>
				<label for='' class='label-name'>
					<p class='content-name__placeholder'>Mật khẩu</p>
				</label> <span></span>
			</div>

			<div class='form-checkbox'>
				<input type='checkbox' name='remember-me' id='remember-me' value='1'>
				<label for='remember-me'>Remmeber me</label>
			</div>

			<button class="btn" type="submit">Đăng nhập</button>
		</form>

		<p class="error-message">${errorMessage}</p>
		<div class="action">
			<a href="" class="forget-password">Quên mật khẩu</a> <a href="${pageContext.request.contextPath}/register"
				class="">Chưa có tài khoản? Đăng ký</a>
		</div>

	</div>


</body>
</html>