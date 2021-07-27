<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/lib/utilities.css" />
</head>
<!-- trang này nhằm để khi người dùng đăng nhập là admin, mà lại vào url dẫn đến trang đăng nhập của user, hoặc ngược lại.
	ta yêu cầu người dùng đăng xuất trước khi muốn đăng nhập tài khoản khác. -->
<body>

<h1>Bạn đang đăng nhập, hãy đăng xuất trước.</h1>
<a href="${pageContext.request.contextPath}/logout">Đăng xuất user</a>
</body>
</html>