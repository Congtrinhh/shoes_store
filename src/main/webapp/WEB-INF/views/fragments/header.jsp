<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
						<li><a href="${pageContext.request.contextPath}/admin-login">Admin</a></li>
					</ul>

					<!--  -->
					<div class="entity-preview-info">
						<a
							href="${pageContext.request.contextPath}/<c:if test="${sessionScope.login_token=='admin'}">admin-dashboard</c:if><c:if test="${sessionScope.login_token=='user'}">user-manage</c:if>"
							class="img-container"> <img
							src="<c:if test="${sessionScope.login_token=='user'}">${pageContext.request.contextPath}/resources/imgs/avatar${sessionScope.logedInUser.u_avatar}</c:if>"
							alt="">
						</a>
						<p class="entity-name">
							<c:if test="${sessionScope.login_token=='admin'}">${logedInAdmin.ad_login_name}</c:if>
							<c:if test="${sessionScope.login_token=='user'}">${logedInUser.u_login_name}</c:if>
							<i class="fas fa-user-cog"></i>
						</p>
						<div class="action">
							<a
								href="${pageContext.request.contextPath}/<c:if test="${sessionScope.login_token=='admin'}">admin-dashboard</c:if><c:if test="${sessionScope.login_token=='user'}">user-manage</c:if>"
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
				<ul tab-name="${tabIndicator}">
					
					<li><a href="${pageContext.request.contextPath}/home"
						class="">home</a></li>
					<li><a href="${pageContext.request.contextPath}/men"> men
							<i class="far fa-laugh-beam"></i>

					</a></li>
					<li><a href="${pageContext.request.contextPath}/women">women
							<i class="far fa-laugh-wink"></i>
					</a></li>
					

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
			<p class="header__ribbon__message conveyor-belt">Giảm giá đến 25%
				cho mùa hè sôi động</p>
		</div>
	</div>
</header>