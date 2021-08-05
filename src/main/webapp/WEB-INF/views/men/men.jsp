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
		<jsp:include page="/WEB-INF/views/fragments/header.jsp"></jsp:include>


		<main class="filters-and-products main container row">
			<div class="page-info-block" style="display:none;">
				<input type="text" hidden name="hidden-total-pages" value="${totalPages}">
				<input type="text" hidden name="hidden-current-page" value="${currentPage}">
				<input type="text" hidden name="hidden-product-count" value="${productCount}">
			</div>
		
			<div class="filters col-lg-3">
				<form id="filter">

					<fieldset>
						<h3 class="form-header">hãng</h3>
						<select name='brand' id='brand' class='form-select'>
							<option value='0' selected>all</option>
							<c:forEach items="${brandList}" var="br">
								<option value='${br.id}'>${br.name}</option>
							</c:forEach>
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
			</div>
		</main>


		<!-- Footer -->
		<jsp:include page="/WEB-INF/views/fragments/footer.jsp"></jsp:include>
		<!-- Loader -->
		<jsp:include page="/WEB-INF/views/fragments/loader.html"></jsp:include>

	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</body>
</html>