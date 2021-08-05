<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product detail</title>
<!-- jquery -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    
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
		<jsp:include page="/WEB-INF/views/fragments/header.jsp"></jsp:include>

        <main class="main container">
        	<div class="product-in-local-storage-info" style="display:none;">
				<input type="text" hidden name="hidden-id" value="${product.product_line_id}">
                <input type="text" hidden name="hidden-name" value="${product.pr_name}">
                <input type="text" hidden name="hidden-price" value="${product.pr_price}">
                <input type="text" hidden name="hidden-category-slug" value="${categorySlug}">
                <input type="text" hidden name="hidden-product-slug" value="${productLineSlug}">      				                                            
                  
                 <!-- array of specific product match with the product line retrieved from server under json format -->
				<input type="text" hidden name="specificProducts" value=${specificProductInfoList}>
				
				<!-- for buy now button to forward to cart page -->
				<a class="go-to-cart-page" href="${pageContext.request.contextPath}/cart" style="display:none;">go to cart page</a>
        	</div>
        	
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
                                <ol class="carousel-indicators">
                                    <c:forEach items="${imagesList}" var="image" varStatus="status">
                                    	<li data-target="#product-slider" data-slide-to="${status.index}" class="">
	                                        <img src="data:image/jpg;base64,${image.base64Image}" alt="">
	                                    </li>
                                    </c:forEach>
                                </ol>
                                
                                <div class="carousel-inner">
                                	<c:forEach items="${imagesList}" var="image">
	                                    <div class="carousel-item">
	                                        <img src="data:image/jpg;base64,${image.base64Image}"
	                                            class="d-block w-100" alt="...">
	                                    </div>
	                                    <!-- this hidden input must be here because it is relevant to c:forEach -->
	                                    <input type="text" hidden name="hidden-image" value="data:image/jpg;base64,${image.base64Image}">
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
                        <h1 class="detail__right__header">${product.pr_name}</h1>
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
                            <button class="btn btn-add-to-cart mb-2 mb-sm0" type="submit" form="productOption"><i class="fas fa-shopping-cart"></i> Thêm vào
                                giỏ hàng</button>
                            <button class="btn btn-buy-now mb-2 mb-sm0" type="submit" form="productOption">Mua ngay</button>
                        </div>
                    </div>
                </div>
        
                <div class="product__description">
                    <a class="btn" data-bs-toggle="collapse" href="#productDescriptionContent" role="button"
                        aria-expanded="false">
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
		<jsp:include page="/WEB-INF/views/fragments/footer.jsp"></jsp:include>
		
		<!-- Loader -->
		<jsp:include page="/WEB-INF/views/fragments/loader.html"></jsp:include>
		
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>