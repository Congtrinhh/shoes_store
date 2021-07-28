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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
    integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
    integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
    crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
    integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
    crossorigin="anonymous"></script>
<!-- Jquery validation -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"
        integrity="sha512-37T7leoNS06R80c8Ulq7cdCDU5MNQBwlYoy1TX/WUsLFC2eYNqtKlV0QjH7r8JpG/S0GUMZwebnVFLPd6SU5yg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<!-- fontawesome -->
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
	href="${pageContext.request.contextPath}/resources/css/product_detail/style.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/homepage/header_and_footer_responsive.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/product_detail/responsive.css">
    <script defer
	src="${pageContext.request.contextPath}/resources/js/product_detail/main.js"></script>
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
						<a href="/cart" class="header__cart">
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
					<p class="header__ribbon__message conveyor-belt">Our biggest sale yet 50%
						off all summer shoes</p>
					</div>
				</div>
		</header>
        


        <main class="main container">
            <!-- Breadcrumb -->
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">product detail</li>
                </ol>
            </nav>
        
            <!-- Product -->
            <div class="product-container">
                <div class="product__detail row">
                    <div class="detail__left col-12 col-md-6">
        
                        <div>
                            <div id="product-slider" class="carousel slide carousel-fade" data-ride="">
                                <!-- Hidden id -->
                                <input type="text" hidden name="hidden-id" value="${product.product_line_id}">
        
                                <ol class="carousel-indicators">
                                    <c:forEach items="${imagesList}" var="image" varStatus="status">
                                    	<li data-target="#product-slider" data-slide-to="${status.index}" class="">
	                                        <img src="data:image/jpg;base64,${image.base64Image}" alt="">
	                                        <input type="text" hidden name="hidden-image"
	                                            value="data:image/jpg;base64,${image.base64Image}">
	                                    </li>
                                    </c:forEach>
                                </ol>
                                
                                <div class="carousel-inner">
                                	<c:forEach items="${imagesList}" var="image">
	                                    <div class="carousel-item">
	                                        <img src="data:image/jpg;base64,${image.base64Image}"
	                                            class="d-block w-100" alt="...">
	                                    </div>
                                	</c:forEach>
	                            </div>
                                
                                <a class="carousel-control-prev" href="#product-slider" role="button" data-slide="prev">
                                    <i class="fas fa-angle-left"></i>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="carousel-control-next" href="#product-slider" role="button" data-slide="next">
                                    <i class="fas fa-angle-right"></i>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>
        
                    </div>
        
                    <div class="detail__right col-12 col-md-6">
                    	<input type="text" hidden name="specificProducts" value=${specificProductInfoList}>
                            <input type="text" hidden name="hidden-name" value="${product.pr_name}">
                        <h1 class="detail__right__header">${product.pr_name}
                        </h1>
                        <input type="text" hidden name="hidden-price" value="${product.pr_price}">
                        <p class="detail__right__price money">${product.pr_price}</p>
                        <p class="detail__right__short-description">${product.pr_description}</p>
                        <form id='productOption' method="GET" action='${pageContext.request.contextPath}/cart'>
                            <div class="detail__right__color select-option">
                                <p class="detail__right__list-header">color</p>
                                <ul class="detail__right__list">
                                    <li>
                                        <input type="radio" name="hidden-color" value="1" form="productOption">
                                    </li>
                                    <li>
                                        <input type="radio" name="hidden-color" value="2" form="productOption">
                                    </li>
                                    <li>
                                        <input type="radio" name="hidden-color" value="3" form="productOption">
                                    </li>
                                    <li>
                                        <input type="radio" name="hidden-color" value="4" form="productOption">
                                    </li>
                                    <li>
                                        <input type="radio" name="hidden-color" value="5" form="productOption">
                                    </li>
                                </ul>
                            </div>
                            <div class="detail__right__size select-option">
                                <p class="detail__right__list-header">size</p>
                                <ul class="detail__right__list">
                                    <li>
                                        <input type="radio" name="hidden-size" value="1" form="productOption">
                                    </li>
                                    <li>
                                        <input type="radio" name="hidden-size" value="2" form="productOption">
                                    </li>
                                    <li>
                                        <input type="radio" name="hidden-size" value="3" form="productOption">
                                    </li>
                                    <li>
                                        <input type="radio" name="hidden-size" value="4" form="productOption">
                                    </li>
                                    <li>
                                        <input type="radio" name="hidden-size" value="5" form="productOption">
                                    </li>
                                </ul>
                            </div>
                        </form>
                        <div class="detail__right__quantity">
                            <input type="button" value="-" class="btn btn-decrease">
                            <input type="text" name="product-quantity" value="1" disabled>
                            <input type="button" value="+" class="btn btn-increase">
                        </div>
                        <div class="detail__right__action">
                            <button class="btn mb-2 mb-sm0" type="submit" form="productOption"><i class="fas fa-shopping-cart"></i> Thêm vào
                                giỏ hàng</button>
                            <button class="btn mb-2 mb-sm0" type="submit" form="productOption">Mua ngay</button>
                        </div>
                    </div>
                </div>
        
                <div class="product__description">
                    <a class="btn" data-toggle="collapse" href="#productDescriptionContent" role="button"
                        aria-expanded="false" aria-controls="productDescriptionContent">
                        Description <i class="fas fa-angle-down"></i></a>
        
                    <div class="collapse product__description__content" id="productDescriptionContent">
                        <div class="card">
                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid.
                            Nihil anim keffiyeh
                            helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.Anim pariatur cliche
                            reprehenderit, enim
                            eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh
                            helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.Anim pariatur cliche
                            reprehenderit, enim
                            eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh
                            helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident.
                        </div>
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
    </div>
</body>
</html>