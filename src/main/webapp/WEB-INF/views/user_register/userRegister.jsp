<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>User register</title>
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
	href="${pageContext.request.contextPath}/resources/css/user_register/style.css">
<!-- Js file - for validation with jquery validation -->
<script defer
	src="${pageContext.request.contextPath}/resources/js/user_register/validation.js"></script>
</head>
<body>
	
	<div id="app" class="container">

            <form id='userForm' method='POST' action='${pageContext.request.contextPath}/register'>
                <h1 class="form-header">Đăng ký</h1>

                <div class='form-text'>
                    <input type='text' class="essential-data" name='name'  required>
                    <label for='' class='label-name'>
                        <p class='content-name__placeholder'>Tên tài khoản</p>
                    </label>
                    <span></span>
                </div>

                <div class='form-text'>
                    <input type='email' class="essential-data" name='email'  required email>
                    <label for='email' class='label-name'>
                        <p class='content-name__placeholder'>Email</p>
                    </label>
                    <span></span>
                </div>

                <div class='form-text'>
                    <input type='password' class="essential-data" name='password'  required>
                    <label for='' class='label-name'>
                        <p class='content-name__placeholder'>Mật khẩu</p>
                    </label>
                    <span></span>
                </div>

                <div class='form-text'>
                    <input type='password' name='confirmed-password'  required>
                    <label for='confirmed-password' class='label-name'>
                        <p class='content-name__placeholder'>Xác nhận mật khẩu</p>
                    </label>
                    <span></span>
                </div>

                <div class='form-checkbox'>
                    <input type='checkbox' name='policy-agree' id='policy-agree' value='1' required>
                    <label for='policy-agree'>Đồng ý với điều khoản của trang web này</label>
                </div>

                <button class="btn btn-submit" type="submit">Đăng ký</button>
            </form>

            <p class="error-message">${errorMessage}</p>

            <div class="action">
                <a href="" class="forget-password">Quên mật khẩu</a>
                <a href="${pageContext.request.contextPath}/" class="to-homepage">Trang chủ</a>
            </div>

        </div>
</body>
</html>