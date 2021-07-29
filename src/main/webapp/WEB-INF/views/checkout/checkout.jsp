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
    <!-- validation -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"
        integrity="sha512-37T7leoNS06R80c8Ulq7cdCDU5MNQBwlYoy1TX/WUsLFC2eYNqtKlV0QjH7r8JpG/S0GUMZwebnVFLPd6SU5yg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
	href="${pageContext.request.contextPath}/resources/css/checkout/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/homepage/header_and_footer_responsive.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/checkout/responsive.css">
    <script defer
	src="${pageContext.request.contextPath}/resources/js/checkout/main.js"></script>
</head>
<body>
	





	

        <main class="main container">
            <!-- breadcrumb -->
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">checkout</li>
                </ol>
            </nav>

            <!-- main -->
            <div class="contact-address-and-product-overview row">
                <div class="contact-address col-12 col-lg-5">
                    <h2 class="form-header">Địa chỉ liên hệ</h2>
                    <form id='orderContactAddress' method='POST' action='/ajax-checkout'>
                        <div class='form-text'>
                            <input type='text' name='full-name' required>
                            <label class='label-name'>
                                <p class='content-name__placeholder'>Họ và tên</p>
                            </label>
                            <span></span>
                        </div>
                        <div class='form-text'>
                            <input type='number' name='phone-number' required>
                            <label class='label-name'>
                                <p class='content-name__placeholder'>Số điện thoại</p>
                            </label>
                            <span></span>
                        </div>
                        <div class='form-text'>
                            <input type='email' name='email' required email>
                            <label class='label-name'>
                                <p class='content-name__placeholder'>Email</p>
                            </label>
                            <span></span>
                        </div>
                        <div class='form-text'>
                            <input type='text' name='specific-address' required>
                            <label class='label-name'>
                                <p class='content-name__placeholder'>Địa chỉ cụ thể</p>
                            </label>
                            <span></span>
                        </div>

                        <div class="address-container">
                            <fieldset>
                                <select name="province" required>
                                    <option value="">Tỉnh/thành phố</option>
                                    <option value="1">Ha noi</option>
                                </select>
                            </fieldset>
                            
                            <fieldset>
                                <select name="district" required>
                                    <option value="">Quận/huyện</option>
                                    <option value="1">Ha noi</option>
                                </select>
                            </fieldset>
                            
                            <fieldset>
                                <select name="ward" required>
                                    <option value="">Xã/phường</option>
                                    <option value="1">Ha noi</option>
                                </select>
                            </fieldset>
                        </div>
                    </form>
                </div>

                <div class="product-overview col-12 col-lg-7 mt-5 mt-lg-0">
                    <div class="row">
                        <div class="cart__products__left col-12 col-sm-12">
                            <div class="table">
                                <div class="tbody">
                                    <div class="tr product-row row mb-4">
                                        <div class="td col-12 col-sm-12 col-lg-8">
                                            <div class="product__container">
                                                <input type="text" hidden name="hidden-product-id" value="">
                                                <div class="parent-image">
                                                    <img src="" alt="">
                                                    <div class="quantity-container">
                                                        <input type="text" name="product-quantity" disabled value="1">
                                                    </div>
                                                </div>
                                                <div class="card__product-info">
                                                    <h2 class="card__product-info__name"></h2>
                                                    <p class="card__product-info__size">size <span></span></p>
                                                    <p class="card__product-info__color">color <span></span></p>
                                                </div>
                                            </div>
                                        </div>
                        
                                        <div class="td col-6 col-sm-4 col-lg-2 mt-4 mt-lg-0 offset-sm-4 offset-lg-0">
                                            <p class="initial-price money"></p>
                                        </div>
                        
                                        <div class="td col-6 col-sm-4 col-lg-2 mt-4 mt-lg-0 ">
                                            <p class="total-price money"></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="cart__products__right col-12 col-sm-12 mt-5 mt-lg-0">
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
                        
                            <p class="error-message"></p>
                            <div class="confirm-checkout">
								<a href="${pageContext.request.contextPath}/cart" ><i class="fas fa-angle-double-left"></i> Quay lại giỏ hàng</a>                                
                                <button type="submit" class="btn btn-submit" form="orderContactAddress">Confirm</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
			<a class="btn-forward" href="${pageContext.request.contextPath}/checkout-complete" style="display:none;">qua trang order complete</a>
        </main>

    
</body>
</html>