<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User info</title>

    <!-- jquery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
        integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- validation -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"
        integrity="sha512-37T7leoNS06R80c8Ulq7cdCDU5MNQBwlYoy1TX/WUsLFC2eYNqtKlV0QjH7r8JpG/S0GUMZwebnVFLPd6SU5yg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- bs 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"
        integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />
    <!-- css js -->  
    <link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/lib/utilities.css">
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/homepage/header_and_footer.css">
	<script defer
		src="${pageContext.request.contextPath}/resources/js/lib/utils.js"></script>
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/user_order_info/style.css">
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/homepage/header_and_footer_responsive.css">
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/user_order_info/responsive.css">
	<script defer
		src="${pageContext.request.contextPath}/resources/js/user_order_info/main.js"></script>
</head>
<body>
    
    <div id="app">
    <!-- Loader -->
		<jsp:include page="/WEB-INF/views/fragments/loader.html"></jsp:include>
    	<!-- Header -->
		<jsp:include page="/WEB-INF/views/fragments/header.jsp"></jsp:include>
		
       	<nav aria-label="breadcrumb" class="container">
		  <ol class="breadcrumb">
		    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
		    <li class="breadcrumb-item active" aria-current="page">User info</li>
		  </ol>
		</nav>
    
        <div class="main row container  mt-4 mt-md-0">
        
            <!-- Main content - user info -->
            <main class="col-12 col-md-8 col-lg-8  mt-4 mt-md-0">
                <h1 class="form-header">Đơn hàng của you</h1>
                <div class="item-table my-4">
                    <c:forEach items="${orderList}" var="order">
                    	<a href="${pageContext.request.contextPath}/" class="item-row my-4">
                       	 <div class="row__top p-3">
                            <div class="img-parent">
                                <img src="data:image/jpg;base64,${order.specificBase64String}" alt="">
                                <span class="more-indicator">${order.itemCount - 1}+</span>
                            </div>
                            <div class="item-info">
                                <h3 class="item-info__name">${order.specificItemName }</h3>
                                <div class="item-info__detail">
                                    <div class="detail__quantity">${order.specificItemQuantity }</div>
                                    <div class="detail__color">${order.specificItemColor }</div>
                                    <div class="detail__size">${order.specificItemSize }</div>
                                </div>
                                <div class="item-info__address"><span><b>Giao đến: </b>${order.address }</span></div>
                            </div>
                            <div class="status-badge">
                            	<span class="badge">${order.status }</span>
                            	<span>Ngày tạo: <span class="time">${order.created_at}</span></span>
                            </div>
                        </div>
                        <div class="row__bottom p-3">
                            <p class="total-money">Tổng tiền: <span class="money">${order.total_price }</span></p>
                        </div>
                    </a>
                    </c:forEach>
                </div>
            </main>
            
            <!-- User nav -->
            <jsp:include page="/WEB-INF/views/fragments/user/leftNavigation.jsp"></jsp:include>
        </div>
        
        <!-- Footer -->
		<jsp:include page="/WEB-INF/views/fragments/footer.jsp"></jsp:include>

    </div>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>