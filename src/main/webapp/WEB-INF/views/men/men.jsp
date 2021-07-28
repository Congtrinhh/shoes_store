<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>homepage</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"
	integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/lib/utilities.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/homepage/header_and_footer.css">
<script defer
	src="${pageContext.request.contextPath}/resources/js/lib/utils.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/men/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/homepage/header_and_footer_responsive.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/men/responsive.css">
<script defer
	src="${pageContext.request.contextPath}/resources/js/men/main.js"></script>

</head>
<body>

	<div id="app">

		<!-- Header -->
		<header class="header">
			<div class="header__top container">
				<div class="logo-login-cart">
					<!-- Logo -->
					<a href="${pageContext.request.contextPath}/" class="logo-link"><h1
							class="logo">run youth</h1></a>
					<!-- Sign in and Cart -->
					<div class="login-and-cart">
						<div class="header__login-signup ${sessionScope.login_token}">
							<!-- Default - when no login -->
							<ul class="login-default">
								<li><a class="btn btn-login"
									href="${pageContext.request.contextPath}/login">Log in</a></li>
								<li><a class="btn"
									href="${pageContext.request.contextPath}/register">Sign up</a></li>
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
						<a href="/cart" class="header__cart"> <i
							class="fas fa-shopping-cart"></i>
							<p>cart</p> <span class="number-indicator"></span>
						</a>
					</div>

				</div>
				<div class="nav-and-search">

					<!-- Navbar -->
					<nav class="header__nav">
						<ul>
							<li><a href="${pageContext.request.contextPath}/home">home</a></li>
							<li><a href="${pageContext.request.contextPath}/men " class="active-link">men</a></li>
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
				<div id="menu-toggle">
					<i class="fas fa-bars"></i>
				</div>
			</div>

			<div class="header__ribbon">
				<div class="header__ribbon__inside">
					<p class="header__ribbon__message conveyor-belt">Giảm giá đến
						25% cho mùa hè sôi động</p>
				</div>
			</div>
		</header>


		<main class="filters-and-products main container row">
			<div class="filters col-lg-3">
				<form id="filter">

					<fieldset>
						<h3 class="form-header">hãng</h3>
						<select name='brand' id='brand' class='form-select'>
							<option value='1' selected>all</option>
							<option value='2'>adidas</option>
							<option value='3'>bitis</option>
							<option value='4'>nike</option>
							<option value='5'>ananas</option>
							<option value='6'>new balance</option>
						</select>
					</fieldset>

					<fieldset>
						<h3 class="form-header">ưu tiên</h3>
						<select name='priority' id='priority' class='form-select'>
							<option value='1' selected>mới nhất</option>
							<option value='2'>giá tăng dần</option>
							<option value='3'>giá giảm dần</option>
						</select>
					</fieldset>

					<fieldset>
						<h3 class="form-header">khoảng giá</h3>
						<div class='form-range'>
							<div class="">
								<label for="from-range">Từ</label> <input type='number'
									name='from-range' id='from-range' placeholder=''>
							</div>

							<div class="">
								<label for="to-range">Đến</label> <input type='number'
									name='to-range' id='to-range' placeholder=''>
							</div>
						</div>
					</fieldset>
					<button class="btn" id="filterBtn" type="button">Tìm sản
						phẩm</button>
				</form>
			</div>

			<div class="products col-lg-9">
				<div class="product-row row">

					<c:forEach items="${productList}" var="product">

						<div class="col-12 col-md-6 col-xl-4 mb-4">
							<div class="product">
								<a class="product__img"
									href="${pageContext.request.contextPath}${product.c_slug}${product.pr_slug}">
									<img
									src='data:image/jpg;base64,${product.base64Image}'
									alt="">
								</a>
								<div class="product__info">
									<a
										href="${pageContext.request.contextPath}${product.c_slug}${product.pr_slug}">
										<h2 class="product__info__title">${product.pr_name}</h2>
									</a>
									<p class="product__info__price money">${product.pr_price}</p>
								</div>
							</div>
						</div>

					</c:forEach>

				</div>

				<div class="pagination">
					<ul>
						
					</ul>
				</div>
				
				<div class="loader" style="background-image: url('${pageContext.request.contextPath}/resources/imgs/loader/ajax-loader.gif')"></div>
				
				<input type="text" hidden name="hidden-current-page"
					value="${currentPage}"> <input type="text" hidden
					name="hidden-total-pages" value="${totalPages}">
			</div>
		</main>


		<!-- Footer -->
		<footer class="footer container">
			<div class="row">
				<div
					class="col-10 offset-1 offset-sm-0 col-sm-4 col-md-4 mb-3 mb-md-0 footer__child-section">
					<div class="">
						<p class="footer__sub-header">
							about runyouth <a class="btn d-inline-block d-sm-none"
								data-toggle="collapse" href="#footer__sub-content-1"
								role="button" aria-expanded="false"
								aria-controls="footer__sub-content-1"><i class="fas fa-plus"></i></a>
						</p>
						<ul class="collapse footer__sub-content"
							id="footer__sub-content-1">
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

				<div
					class="col-10 offset-1 offset-sm-0 col-sm-4 col-md-4 mb-3 mb-md-0 footer__child-section">
					<div class="">
						<p class="footer__sub-header">
							CUSTOMER CARE <a class="btn d-inline-block d-sm-none"
								data-toggle="collapse" href="#footer__sub-content-2"
								role="button" aria-expanded="false"
								aria-controls="footer__sub-content-2"><i class="fas fa-plus"></i></a>
						</p>
						<ul class="collapse footer__sub-content"
							id="footer__sub-content-2">
							<li><a href="#">CONTACT</a></li>
							<li><a href="#">RETURNS/EXCHANGE</a></li>
							<li><a href="#">GIFT VOUCHER</a></li>
							<li><a href="#">SPECIAL</a></li>
							<li><a href="#">CUSTOMER SERVICES</a></li>
							<li><a href="#">SITE MAPS</a></li>
						</ul>
					</div>
				</div>

				<div
					class="col-10 offset-1 offset-sm-0 col-sm-4 col-md-4 mb-3 mb-md-0 footer__child-section">
					<div class="">
						<p class="footer__sub-header">
							CONTACT INFORMATION <a class="btn d-inline-block d-sm-none"
								data-toggle="collapse" href="#footer__sub-content-3"
								role="button" aria-expanded="false"
								aria-controls="footer__sub-content-3"><i class="fas fa-plus"></i></a>
						</p>
						<ul class="collapse footer__sub-content"
							id="footer__sub-content-3">
							<li><a href="#">CONTACT</a></li>
							<li><a href="#">RETURNS/EXCHANGE</a></li>
							<li><a href="#">GIFT VOUCHER</a></li>
							<li><a href="#">SPECIAL</a></li>
							<li><a href="#">CUSTOMER SERVICES</a></li>
						</ul>
					</div>
				</div>
			</div>


			<p class="copyright">Copyright &copy;Trịnh Quý Công - 2021 All
				rights reserved</p>
		</footer>

		<!-- Overlay (for menu bar mobile)-->
		<div class="overlay"></div>

		<a class="back-to-top btn" href="#app" id="${login_token}"><i
			class="fas fa-caret-up"></i></a>
	</div>
</body>
</html>