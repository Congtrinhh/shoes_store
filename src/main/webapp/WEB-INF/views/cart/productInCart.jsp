<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
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

        <!-- Header -->
		<header class="header">
			<div class="header__top container">
				<div class="logo-login-cart">
					<!-- Logo -->
					<a href="${pageContext.request.contextPath}/" class="logo-link"><h1 class="logo">run youth</h1></a> 
					<!-- Sign in and Cart -->
					<div class="login-and-cart">
						<div class="header__login-signup ${sessionScope.login_token}">
							<!-- Default - when no login -->
							<ul class="login-default">
								<li><a class="btn btn-login" href="${pageContext.request.contextPath}/login">Log in</a></li>
								<li><a class="btn" href="${pageContext.request.contextPath}/register">Sign up</a></li>
								<li><a
									href="${pageContext.request.contextPath}/admin-login">Admin</a></li>
							</ul>

							<!--  -->
							<div class="entity-preview-info">
								<a
									href="${pageContext.request.contextPath}/<c:if test="${sessionScope.login_token=='admin'}">admin-manage</c:if><c:if test="${sessionScope.login_token=='user'}">user-manage</c:if>"
									class="img-container"> <img src="<c:if test="${sessionScope.login_token=='user'}">${pageContext.request.contextPath}/resources/imgs/avatar${sessionScope.logedInUser.u_avatar}</c:if>" alt="">
								</a>
								<p class="entity-name">
									<c:if test="${sessionScope.login_token=='admin'}">${logedInAdmin.ad_login_name}</c:if>
									<c:if test="${sessionScope.login_token=='user'}">${logedInUser.u_login_name}</c:if>
									<i class="fas fa-user-cog"></i>
								</p>
								<div class="action">
									<a
										href="${pageContext.request.contextPath}/<c:if test="${sessionScope.login_token=='admin'}">admin-manage</c:if><c:if test="${sessionScope.login_token=='user'}">user-manage</c:if>"
										class="manage-account">Quản lý tài khoản</a>
								</div>
							</div>

							
						</div>
						<a href="#" class="header__cart">
							<i class="fas fa-shopping-cart"></i>
							<p>cart</p>
							<span class="number-indicator"></span>
						</a>
					</div>

				</div>
				<div class="nav-and-search">
					
					<!-- Navbar -->
					<nav class="header__nav">
						<ul>
							<li><a href="${pageContext.request.contextPath}/home"
								class="active-link">home</a>
							</li>
							<li><a href="${pageContext.request.contextPath}/men">men</a></li>
							<li><a href="${pageContext.request.contextPath}/women">women</a></li>

							<li><a href="${pageContext.request.contextPath}/about">about</a></li>
							<li><a href="${pageContext.request.contextPath}/contact">contact</a></li>
						</ul>
					</nav>
					<!-- Search form -->
					<div class="header__form-control">
						<input type="text" name="home-search" placeholder="Search">
						<!--search for information through the website: products, articles, ..-->
						<button type="button" class="btn">
							<i class="fas fa-search"></i>
						</button>
						<ul class="drown-drop-result">
							<li><a href="">Giày sneaker</a></li>
							<li><a href="">Addidas</a></li>
							<li><a href="">Niceke</a></li>
							<li><a href="">Hehehe</a></li>
						</ul>
					</div>
				</div>
			</div>

			<!-- Toggle icon (for menu mobile) -->
			<div class="container menu-toggle-parent">
				<div id="menu-toggle"><i class="fas fa-bars"></i></div>
			</div>

			<div class="header__ribbon">
				<div class="header__ribbon__inside">
					<p class="header__ribbon__message conveyor-belt">Giảm giá đến 25% cho mùa hè sôi động</p>
					</div>
				</div>
		</header>


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
                <footer class="footer container">
                    <div class="row">
                        <div class="col-10 offset-1 offset-sm-0 col-sm-4 col-md-4 mb-3 mb-md-0 footer__child-section">
                            <div class="">
                                <p class="footer__sub-header">
                                    about runyouth
                                    <a class="btn d-inline-block d-sm-none" data-toggle="collapse"
                                        href="#footer__sub-content-1" role="button" aria-expanded="false"
                                        aria-controls="footer__sub-content-1"><i class="fas fa-plus"></i></a>
                                </p>
                                <ul class="collapse footer__sub-content" id="footer__sub-content-1">
                                    <li><a href="#">start-up shoes store</a></li>
                                    <li><a href="#">quality first</a></li>
                                    <li><a href="#">we on social medias</a></li>
                                </ul>
                                <ul class="footer__social-media">
							<li><a href="#"><i class="fab fa-twitter"></i></a></li>
							<li><a href="#"><i class="fab fa-facebook-f"></i></a></li>
							<li><a href="#"><i class="fab fa-linkedin"></i></a></li>
						</ul>
                            </div>
                        </div>

                        <div class="col-10 offset-1 offset-sm-0 col-sm-4 col-md-4 mb-3 mb-md-0 footer__child-section">
                            <div class="">
                                <p class="footer__sub-header">
                                    CUSTOMER CARE
                                    <a class="btn d-inline-block d-sm-none" data-toggle="collapse"
                                        href="#footer__sub-content-2" role="button" aria-expanded="false"
                                        aria-controls="footer__sub-content-2"><i class="fas fa-plus"></i></a>
                                </p>
                                <ul class="collapse footer__sub-content" id="footer__sub-content-2">
                                    <li><a href="#">CONTACT</a></li>
                                    <li><a href="#">RETURNS/EXCHANGE</a></li>
                                    <li><a href="#">GIFT VOUCHER</a></li>
                                    <li><a href="#">SPECIAL</a></li>
                                    <li><a href="#">CUSTOMER SERVICES</a></li>
                                    <li><a href="#">SITE MAPS</a></li>
                                </ul>
                            </div>
                        </div>

                        <div class="col-10 offset-1 offset-sm-0 col-sm-4 col-md-4 mb-3 mb-md-0 footer__child-section">
                            <div class="">
                                <p class="footer__sub-header">
                                    CONTACT INFORMATION
                                    <a class="btn d-inline-block d-sm-none" data-toggle="collapse"
                                        href="#footer__sub-content-3" role="button" aria-expanded="false"
                                        aria-controls="footer__sub-content-3"><i class="fas fa-plus"></i></a>
                                </p>
                                <ul class="collapse footer__sub-content" id="footer__sub-content-3">
                                    <li><a href="#">CONTACT</a></li>
                                    <li><a href="#">RETURNS/EXCHANGE</a></li>
                                    <li><a href="#">GIFT VOUCHER</a></li>
                                    <li><a href="#">SPECIAL</a></li>
                                    <li><a href="#">CUSTOMER SERVICES</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>


                    <p class="copyright">Copyright &copy;Trịnh Quý Công - 2021 All rights reserved</p>
                </footer>

                <!-- Overlay (for menu bar mobile)-->
                <div class="overlay"></div>

                <a class="back-to-top btn" href="#app" id="${login_token}"><i class="fas fa-caret-up"></i></a>
                
                <a href="${pageContext.request.contextPath}/checkout" class="btn btn-checkout" style="display:none">Checkout here</a>

    </div>
    <div class="loader" style="background-image:url('${pageContext.request.contextPath}/resources/imgs/loader/ajax-loader.gif')"></div>
</body>
</html>