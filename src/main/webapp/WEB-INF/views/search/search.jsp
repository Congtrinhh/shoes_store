	<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Men</title>
<!-- jquery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<!-- fontawesome -->    
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
	href="${pageContext.request.contextPath}/resources/css/search/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/homepage/header_and_footer_responsive.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/search/responsive.css">
<script defer
	src="${pageContext.request.contextPath}/resources/js/search/main.js"></script>

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
		    <li class="breadcrumb-item active" aria-current="page">Search</li>
		  </ol>
		</nav>

		<main class="filters-and-products main container row">
			<div class="page-info-block" style="display:none;">
				<input type="text" hidden name="hidden-total-page" value="${totalPage}">
				<input type="text" hidden name="hidden-current-page" value="${currentPage}">
			</div>
		
			<div class="filters col-lg-3">
				<h3 class="form-header result-header">What's new?</h3>
				
				<picture>
				  <source media="(max-width: 991px)" srcset="${pageContext.request.contextPath}/resources/imgs/banner/h-banner-1.jpg">
				  <img src="${pageContext.request.contextPath}/resources/imgs/banner/v-banner-2.jpg" alt="a shoes banner">
				</picture>
				
				<picture>
				  <source media="(max-width: 991px)" srcset="">
				  <img src="${pageContext.request.contextPath}/resources/imgs/banner/v-banner-1.jpg" alt="a shoes banner">
				</picture>
				
			</div>

			<div class="products col-lg-9">
				<h3 class="result-header">Kết quả tìm kiếm cho "${sessionScope.productNameForSearching}":</h3>
			
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
					<!-- Item loader -->
					<jsp:include page="/WEB-INF/views/fragments/itemLoader.html"></jsp:include>
					</ul>
				</div>
			</div>
		</main>


		<!-- Footer -->
		<jsp:include page="/WEB-INF/views/fragments/footer.jsp"></jsp:include>

	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>