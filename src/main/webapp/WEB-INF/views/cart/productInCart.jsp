<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart</title>
    <!-- jquery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Fontawesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
        integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/utilities.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/homepage/header_and_footer.css">
	<script defer
	src="${pageContext.request.contextPath}/resources/js/lib/utils.js"></script>
    <link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/cart/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/homepage/header_and_footer_responsive.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cart/responsive.css">
    <script defer
	src="${pageContext.request.contextPath}/resources/js/cart/main.js"></script>
</head>
<body>
    
    <div id="app">

        <!--Header-->
		<jsp:include page="/WEB-INF/views/fragments/header.jsp"></jsp:include>


        <!-- Card - main -->
        <main class="cart main container">
        	<div style="display:none;" my-note="khu vực dành cho chức năng nếu người dùng chưa đăng nhập, chuyển qua trang login">
        		<input type="text" hidden name="login-token-for-user" value="${sessionScope.login_token}"> <!-- js căn cứ vào đây để biết ng dùng đã login hay chưa, nếu chưa, khi click nút 'tiếp tục' sẽ đc chuyển sang trang login -->
        	<a class="btn-login" href="${pageContext.request.contextPath}/user-login" style="display:none;">qua trang login</a>
        	</div>
        	
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">cart</li>
                </ol>
            </nav>
        
            <div class="checkout-map">
                <ul>
                    <li class="active">01</li>
                    <li></li>
                    <li>02</li>
                    <li></li>
                    <li>03</li>
                </ul>
            </div>
        
            <div class="cart__products row">
                <div class="cart__products__left col-12 col-lg-9">
                    
                    <div class="table">
                        <div class="tbody">
                            <div class="tr product-row row mb-4">

                                <div class="td col-10 col-sm-6 col-md-7">
                                    <div class="product__container">
                                        <input type="text" hidden name="hidden-product-id" value="">
                                        <a class="parent-image" href=""><img src="" alt=""></a>
                                        <div class="card__product-info">
                                            <h2 class="card__product-info__name"></h2>
                                            <p class="card__product-info__size">size <span></span></p>
                                            <p class="card__product-info__color">color <span></span></p>
                                        </div>
                                    </div>
                                </div>

                                <div class="td col-3 col-sm-1 col-md-1 offset-1 offset-sm-0 mt-4 mt-sm-0">
                                    <p class="initial-price money"></p>
                                </div>

                                <div class="td col-3 offset-2 offset-sm-0 col-sm-3 col-md-2 mt-4 mt-sm-0">
                                    <div class="quantity_container">
                                        <input type="button" value="-" class="btn-decrease">
                                        <input type="text" name="product-quantity" disabled value="1">
                                        <input type="button" value="+" class="btn-increase">
                                    </div>
                                </div>

                                <div class="td col-3 col-sm-1 col-md-1 mt-4 mt-sm-0">
                                    <p class="total-price money"></p>
                                </div>

                                <div class="td col-2 col-sm-1 col-md-1">
                                    <button type="button" class="btn remove-product">&times;</button>
                                </div>

                            </div>
                        </div>
                    </div>
                    
                    <div class="empty-cart col-12">
                    	<img src="${pageContext.request.contextPath}/resources/imgs/exception/empty-cart.png">
                    </div>

                </div>
        
                <div class="cart__products__right col-12 col-lg-3 mt-5 mt-lg-0">
                    <div class="apply-coupon">
                        <input type="text" placeholder="Your Coupon...">
                        <button class="btn">apply coupon</button>
                    </div>
        
                    <div class="card__total-price">
                        <ul>
                            <li>
                                <p class="title">Subtotal</p>
                                <p class="price money subtotal-price"></p>
                            </li>
                            <li>
                                <p class="title">Delivery</p>
                                <p class="price money delivery-price"></p>
                            </li>
                            <li>
                                <p class="title">Discount</p>
                                <p class="price money discount-price"></p>
                            </li>
                            <hr>
                            <li>
                                <p class="title">Total</p>
                                <p class="price money total-price"></p>
                            </li>
                        </ul>
                    </div>
        
                    <div class="continue-checkout">
                        <a href="" class="btn btn-continue">Continue</a>
                        <p class="error-message"></p>
                    </div>
                    
                    
                </div>
            </div>
        </main>


        <!-- Footer -->
		<jsp:include page="/WEB-INF/views/fragments/footer.jsp"></jsp:include>
		
    	<!-- Loader -->
    	<jsp:include page="/WEB-INF/views/fragments/loader.html"></jsp:include>
    	
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>