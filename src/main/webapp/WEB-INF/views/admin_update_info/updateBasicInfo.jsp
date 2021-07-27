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

            <h1>Sửa thông tin cơ bản</h1>
            
            <div class="action">
            	<a href="${pageContext.request.contextPath}/admin-update-advanced">Sửa thông tin nâng cao</a>
            </div>
            
            <form id='' method='POST' action='${pageContext.request.contextPath}/admin-update'>
                <div class="form-control">
                    <label for="ad_name">ad_name</label>
                    <input type="text" name="ad_name" id="ad_name">
                    <p class="error-message"></p>
                </div>
                <div class="form-control">
                    <label for="ad_login_name">ad_login_name</label>
                    <input type="text" name="ad_login_name" id="ad_login_name">
                    <p class="error-message"></p>
                </div>
                <div class="form-control">
                    <label for="ad_email">ad_email</label>
                    <input type="email" name="ad_email" id="ad_email">
                    <p class="error-message"></p>
                </div>
                <div class="form-control">
                    <label for="ad_phone_number">ad_phone_number</label>
                    <input type="number" name="ad_phone_number" id="ad_phone_number">
                    <p class="error-message"></p>
                </div>

                <div class="form-control">
                    <label for="ad_password">ad_password</label>
                    <input type="password" name="ad_password" id="ad_password">
                    <p class="error-message"></p>
                </div>
                
                <p class="error-message">${errorMessage}</p>
                
                <button class="btn" type="submit">Submit</button>
            </form>
        </div>

</body>
</html>