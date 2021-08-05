<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>	
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product create</title>
    
	<!-- Jquery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    
    <!-- Bs 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    
    <!-- Jquery validation -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"
        integrity="sha512-37T7leoNS06R80c8Ulq7cdCDU5MNQBwlYoy1TX/WUsLFC2eYNqtKlV0QjH7r8JpG/S0GUMZwebnVFLPd6SU5yg=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <!-- Jquery additional methods -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/additional-methods.min.js"
        integrity="sha512-XZEy8UQ9rngkxQVugAdOuBRDmJ5N4vCuNXCh8KlniZgDKTvf7zl75QBtaVG1lEhMFe2a2DuA22nZYY+qsI2/xA=="
        crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
        integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/lib/utilities.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/lib/adminSidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/crud/product_create/style.css">
    <script defer src="${pageContext.request.contextPath}/resources/js/lib/utils.js"></script>
    <script defer src="${pageContext.request.contextPath}/resources/js/lib/adminSidebar.js"></script>
    <script defer src="${pageContext.request.contextPath}/resources/js/crud/product_create/main.js"></script>
    

</head>
<body>

	<div id="app">
        <!-- sidebar and logo top -->
        <jsp:include page="/WEB-INF/views/fragments/admin/sidebar.jsp"></jsp:include>

        <main class="content px-3">
            
            <div class="data-area">
                <!-- Form tạo mới (create) -->
                <div class="create-area">
                    <div class="form-header create-area__header">Tạo dòng sản phẩm mới</div>
                    <form id='createForm' action='${pageContext.request.contextPath}/' method='POST' enctype="multipart/form-data">

                        <!-- name -->
                        <div class='form-text'>
                            <input type='text' name='name' placeholder='' required>
                            <label for='' class='label-name'>
                                <p class='content-name__placeholder'>Tên sản phẩm</p>
                            </label>
                            <span></span>
                        </div>

                        <!-- slug -->
                        <div class='form-text'>
                            <input type='text' name='slug' placeholder='' required>
                            <label for='' class='label-name'>
                                <p class='content-name__placeholder'>Slug</p>
                            </label>
                            <span></span>
                        </div>

                        <!-- price -->
                        <div class='form-text'>
                            <input type='number' name='price' placeholder='' required>
                            <label for='' class='label-name'>
                                <p class='content-name__placeholder'>Giá</p>
                            </label>
                            <span></span>
                        </div>

                        <div class="fieldsets">
                            <!-- brand -->
                            <fieldset>
                                <div class="">
                                    <div class="form-header">Hãng (brand)</div>
                                    <select name='brand' class='form-select' required>
                                    	<!--  <option value="">-- Brand --</option>  -->
                                        <c:forEach items="${brandList}" var="brand">
                                        	<option value="${brand.id}">${brand.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </fieldset>
                            
                            <!-- Category -->
                            <fieldset>
                                <div class="">
                                    <div class="form-header">Phân loại (category)</div>
                                    <select name='category' class='form-select' required>
                                        <c:forEach items="${categoryList}" var="cate">
                                        	<option value="${cate.id}">${cate.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </fieldset>
                            
                            <!-- Image -->
                            <fieldset>
                                <div class="">
                                    <div class="form-header">Ảnh - jpg | png | jpeg (tối đa 6 ảnh)</div>
                                    <input type="file" multiple name="images">
                                </div>
                            </fieldset>
                            
                            <!-- description -->
                            <fieldset>
                                <div class="form-header">Mô tả</div>
                                <textarea name="description" cols="30" rows="4"></textarea>
                            </fieldset>
                        </div>

                        <button type="submit" class="btn btn-submit">Submit</button>
                        
                        <p class="error-message"></p>
                    </form>
                </div>

        </main>
        <!-- Toast, các text bên trong tự chỉnh thông qua .textContent, innnerHTML -->
		<jsp:include page="/WEB-INF/views/fragments/toast.html"></jsp:include>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>