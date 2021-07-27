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
		<jsp:include
			page="${pageContext.request.contextPath}/WEB-INF/views/crud/header.jsp" />
			
            <h1>Manage Admin info</h1>

            <div class="info">
                <h2>Nick name: 
                    <span class="piece-of-info">${logedInAdmin.ad_name}</span>
                </h2>
                <h2>Name: 
                    <span class="piece-of-info">${logedInAdmin.ad_login_name}</span>
                </h2>
                <p>Email: 
                    <span class="piece-of-info">${logedInAdmin.ad_email}</span>
                </p>
                <p>Phone number: 
                    <span class="piece-of-info">${logedInAdmin.ad_phone_number}</span>
                </p>
            </div>

            <div class="action">
                <a href="${pageContext.request.contextPath}/admin-update">Cập nhật tài khoản</a>
                <a href="${pageContext.request.contextPath}/admin-logout">Đăng xuất</a>
            </div>

        </div>

</body>
</html>