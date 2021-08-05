<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>
<!-- jquery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">    
<!-- Fontawesome -->        
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
	href="${pageContext.request.contextPath}/resources/css/homepage/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/homepage/header_and_footer_responsive.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/homepage/responsive.css">
<script defer
	src="${pageContext.request.contextPath}/resources/js/homepage/main.js"></script>

</head>
<body>

	<div id="app">
		
		<!--Header-->
		<jsp:include page="/WEB-INF/views/fragments/header.jsp"></jsp:include>


		<!-- Slider -->
		<div class="slider" id="main-slider">
			<div class="slides__wrapper">

				<c:forEach items="${ctaList}" var="cta">
					<div class="slide"
						style="background-image: url('${pageContext.request.contextPath}/resources/imgs/cta/${cta.cta_banner_location}');">
						<div class="slide__info">
							<p class="slide__info__header">${cta.cta_title}</p>
							<a href="${pageContext.request.contextPath}${cta.cta_slug}"
								class="btn btn-cta">${cta.cta_button_text}</a>
						</div>
					</div>
				</c:forEach>

			</div>
			<div class="slider__indicator">
				<c:forEach items="${ctaList}" var="cta">
					<div class="dot"></div>
				</c:forEach>
			</div>
		</div>



		<div class="slogan container">Hàng chính hãng đến từ các tên tuổi lớn trong và ngoài nước</div>

		<div class="collection row">

			<div class="col-12 col-md-6 mb-5 mb-md-0">
				<div class="collection__men"
					style="background-image: url('${pageContext.request.contextPath}/resources/imgs/collection/ebay-authentication.jpg')"></div>
				<h3>Shop Men's Collection</h3>
			</div>
			<div class="col-12 col-md-6 mb-5 mb-md-0">
				<div class="collection__women"
					style="background-image: url('${pageContext.request.contextPath}/resources/imgs/collection/ox-street-7k4TGXsQCPY-unsplash.jpg')"></div>
				<h3>New Arrival's Collection</h3>
			</div>

		</div>

		<main class="main container">
			<section class="best-sellers">
				<h2 class="best-sellers__header">BEST SELLERS</h2>
				<div class="best-sellers__list row">


					<c:forEach items="${productList}" var="product">
						<div class="col-12 col-md-6 col-lg-4 col-xl-3">
							<div class="product buy-now">
								<a class="product__img" href="${pageContext.request.contextPath}${product.c_slug}${product.pr_slug}">
									<img src="data:image/jpg;base64,${product.base64Image}" alt="a pair of shoes">
								</a>
								<div class="product__info">
									<a href="${pageContext.request.contextPath}${product.c_slug}${product.pr_slug}">
										<h2 class="product__info__title">${product.pr_name}</h2>
									</a>
									<p class="product__info__price">$${product.pr_price}</p>
								</div>
							</div>
						</div>
					</c:forEach>

  
				</div>
			</section>


			<section class="partners">
				<p class="partners__header">trusted partners</p>
				<div class="partners__list row">
					<div class="col-6 col-md-4 col-lg-3 mb-4 mb-lg-0">
						<div class="partner"
							style="background-image: url('${pageContext.request.contextPath}/resources/imgs/trusted_partner/brand-1.jpg');"></div>
					</div>
					<div class="col-6 col-md-4 col-lg-3 mb-4 mb-lg-0">
						<div class="partner"
							style="background-image: url('${pageContext.request.contextPath}/resources/imgs/trusted_partner/brand-2.jpg');"></div>
					</div>
					<div class="col-6 col-md-4 col-lg-3 mb-4 mb-lg-0">
						<div class="partner"
							style="background-image: url('${pageContext.request.contextPath}/resources/imgs/trusted_partner/brand-3.jpg');"></div>
					</div>
					<div class="col-6 col-md-4 col-lg-3 mb-4 mb-lg-0">
						<div class="partner"
							style="background-image: url('${pageContext.request.contextPath}/resources/imgs/trusted_partner/brand-5.jpg');"></div>
					</div>
				</div>
			</section>
		</main>
		<!-- Footer -->
		<jsp:include page="/WEB-INF/views/fragments/footer.jsp"></jsp:include>
		
		<!-- Loader -->
		<jsp:include page="/WEB-INF/views/fragments/loader.html"></jsp:include>
	</div>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>