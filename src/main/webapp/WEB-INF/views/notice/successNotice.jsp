<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="UTF-8">
<title>Insert title here</title>
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
	
<style>
.message{
	color: green;
	font-size: 2.5rem;
	margin-bottom: 40px;
	margin-top: 30px;
}
</style>
</head>
<body>

	<div id="app" class="container">

		<h1 class="message">${message}</h1>	
		
		<a class="btn" href="${pageContext.request.contextPath}/">&lt;&lt; Trang chủ</a>
	</div>

</body>
</html>