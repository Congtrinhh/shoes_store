<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>	
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Specific product create</title>
    
    <!-- Jquery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    
    <!-- Bs 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    
    <!-- Jquery validation -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"
        integrity="sha512-37T7leoNS06R80c8Ulq7cdCDU5MNQBwlYoy1TX/WUsLFC2eYNqtKlV0QjH7r8JpG/S0GUMZwebnVFLPd6SU5yg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
        integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/lib/utilities.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/lib/adminSidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/crud/specific_product_update/style.css">
    <script defer src="${pageContext.request.contextPath}/resources/js/lib/adminSidebar.js"></script>
    <script defer src="${pageContext.request.contextPath}/resources/js/crud/specific_product_update/main.js"></script>
    

</head>
<body>

	<div id="app">
        <!-- sidebar and logo top -->
        <jsp:include page="/WEB-INF/views/fragments/admin/sidebar.jsp"></jsp:include>
    
        <main class="content px-3">
                
                   <!-- Form tạo mới (mượn bên create) --> 
                <div class="create-area">
                    <h1 class="form-header create-area__header">Cập nhật sản phẩm cụ thể</h1>
                    <form id='createForm' action='' method='POST'>
						<input hidden type="text" name="id" value="${product.id}">
                        <div class="fieldsets">
                            <fieldset>
                            	<h3 class="form-header">
                            		<span>Id dòng sản phẩm: <b>${product.product_line_id}</b></span>
                            		<button type="button" class="btn btn-edit"><i class="far fa-edit"></i></button>
                            	</h3>
                            	
                                <select name='product-line-id' class='form-select'>
	                                <option value=''>-- Id dòng sản phẩm --</option>
                                    <c:forEach items="${productList}" var="p">
                                    	<option value='${p.product_line_id}' <c:if test="${p.product_line_id==product.product_line_id}">selected</c:if> 
                                    	 >${p.product_line_id} - (${p.pr_name})</option>
                                    </c:forEach>
                                </select>
                            </fieldset>
                            
                            <fieldset>
                            	<h3 class="form-header">
                            		<span>Id màu: <b>${product.color_id}</b></span>
                            		<button type="button" class="btn btn-edit"><i class="far fa-edit"></i></button>
                            	</h3>
                            	
                                <select name='color-id' class='form-select'>
                                	<option value=''>-- Màu Id --</option>
                                    <c:forEach items="${colorList}" var="color">
                                    	<option value='${color.color_id}' <c:if test="${color.color_id == product.color_id}">selected</c:if>
                                    	>${color.color_id} - ${color.color_name}</option>
                                    </c:forEach>
                                </select>
                            </fieldset>

                            <fieldset>
                            	<h3 class="form-header">
                            		<span>Id size: <b>${product.size_id}</b></span>
                            		<button type="button" class="btn btn-edit"><i class="far fa-edit"></i></button>
                            	</h3>
                            
                                <select name='size-id' class='form-select'>
	                                <option value=''>-- Size Id --</option>
                                    <c:forEach items="${sizeList}" var="size">
                                    	<option value='${size.size_id}' <c:if test="${size.size_id == product.size_id}">selected</c:if>
                                    	>${size.size_id} - (size ${size.size_number})</option>
                                    </c:forEach>
                                </select>
                            </fieldset>
                        </div>

                        <div class="form-text-container">
                        	<div class='form-text'>
                            <input type='number' name='price' value="${product.spr_price}" placeholder='' required>
                            <label class='label-name'>
                                <p class='content-name__placeholder'>Giá</p>
                            </label>
                            <span></span>
	                        </div>
	
	                        <div class='form-text'>
	                            <input type='number' name='quantity' value="${product.spr_quantity}" placeholder='' required>
	                            <label class='label-name'>
	                                <p class='content-name__placeholder'>Số lượng ban đầu</p>
	                            </label>
	                            <span></span>
	                        </div>
                        </div>
                        
    
                        <button type="submit" class="btn btn-submit">Submit</button>
    
                        <p class="error-message"></p>
                    </form>
                </div>
                    
        </main>
    </div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>