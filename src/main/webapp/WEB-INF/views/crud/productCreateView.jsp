<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>	
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>

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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/crud/product_create/style.css">
    <script defer src="${pageContext.request.contextPath}/resources/js/crud/product_create/main.js"></script>
    

</head>
<body>

	<div id="app">
        <aside class="nav">       
            <h2 class="logo px-4 py-2">Dashboard</h2>

            <ul class="parent-menu px-4">
                <li>
                    <a href="#sub-menu-1" class="" data-toggle="collapse" role="button" aria-expanded="false" aria-controls="sub-menu-1">
                        <span>manage</span>
                        <span class="child-menu__trigger"><i class="fas fa-caret-right"></i></span>
                    </a>
                    <ul class="child-menu collapse px-3" id="sub-menu-1">
                        <li><a href="#">Admin</a></li>
                        <li><a href="#">User</a></li>
                        <li><a href="#">product</a></li>
                        <li><a href="#">order</a></li>
                        <li><a href="#">size</a></li>
                        <li><a href="#">color</a></li>
                    </ul>
                </li>
                <li><a href="../home/index.html" class="">home page</a></li>
            </ul>
        </aside>

        <div class="content px-3">
            <div class="content__top">
                <h1 class="page__header"></h1>

                <div id="nav__toggle" class="on">
                    <i class="nav__toggle__close fas fa-bars"></i>
                    <!-- <i class="nav__toggle__open fas fa-caret-right"></i> -->
                </div>

                <div class="admin-info">
                    <a href=""class="image-parent">
                        <img src="" alt="">
                    </a>
                    <p class="admin-name">Congtrinhh</p>
                    <p class="icon"></p>
                </div>
            </div>

            <div class="data-area">
                <!-- Form tạo mới (create) -->
                <div class="create-area">
                    <div class="form-header create-area__header">Tạo dòng sản phẩm mới</div>
                    <form id='createForm' action='${pageContext.request.contextPath}/' method='POST'>

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
                            <input type='number' name='' placeholder='' required>
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
                                        <c:forEach items="${brandsList}" var="brand">
                                        	<option value="${brand.name}">${brand.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </fieldset>
                            
                            <!-- Category -->
                            <fieldset>
                                <div class="">
                                    <div class="form-header">Phân loại (category)</div>
                                    <select name='category' class='form-select' required>
                                        <c:forEach items="${categoriesList}" var="cate">
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
                    </form>
                </div>

                <!-- Liệt kê các bản ghi hiện tại (read) -->
                <div class="enumerate-area">
                    <table class=''>
                        <caption class="select-wrapper">
                            <select name='data-base-quantity-selector' id='data-base-quantity-selector' class='form-select'>
                                <option value='5'>5</option>
                                <option value='10' selected>10</option>
                                <option value='25'>25</option>
                                <option value='50'>50</option>
                                <option value='100'>100</option>
                            </select>
                            <p>Số lượng bản ghi / trang</p>
                        </caption>

                        <thead>
                            <th>stt</th>
                            <th>id</th>
                            <th>họ tên</th>
                            <th>tên đăng nhập</th>
                            <th>mật khẩu</th>
                            <th>số điện thoại</th>
                            <th>email</th>
                            <th>trạng thái</th>
                            <th>ngày tạo</th>
                            <th>ngày sửa</th>
                            <th>hành động</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>1</td>
                                <td>họ tên</td>
                                <td>tên đăng nhập</td>
                                <td>mật khẩu</td>
                                <td>0531346871</td>
                                <td>email@gmail.com</td>
                                <td>hoạt động</td>
                                <td>ngày tạo</td>
                                <td>ngày sửa</td>
                                <td>
                                    <a href="/update" class="btn table-btn" type="submit"><i class="fas fa-edit"></i></a>
                                    <a href="/delete" class="btn table-btn" type="submit"><i class="fas fa-trash-alt"></i></a>
                                </td>
                            </tr>
                            <tr><!-- input type hidden send data-->
                                <td>1</td>
                                <td>1</td>
                                <td>họ tên</td>
                                <td>tên đăng nhập</td>
                                <td>mật khẩu</td>
                                <td>0531346871</td>
                                <td>email@gmail.com</td>
                                <td>hoạt động</td>
                                <td>ngày tạo</td>
                                <td>ngày sửa</td>
                                <td>
                                    <a href="/update" class="btn table-btn" type="submit"><i class="fas fa-edit"></i></a>
                                    <a href="/delete" class="btn table-btn" type="submit"><i class="fas fa-trash-alt"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>1</td>
                                <td>họ tên</td>
                                <td>tên đăng nhập</td>
                                <td>mật khẩu</td>
                                <td>0531346871</td>
                                <td>email@gmail.com</td>
                                <td>hoạt động</td>
                                <td>ngày tạo</td>
                                <td>ngày sửa</td>
                                <td>
                                    <a href="/update" class="btn table-btn" type="submit"><i class="fas fa-edit"></i></a>
                                    <a href="/delete" class="btn table-btn" type="submit"><i class="fas fa-trash-alt"></i></a>
                                </td>
                            </tr>
                            <tr>
                                <td>1</td>
                                <td>1</td>
                                <td>họ tên</td>
                                <td>tên đăng nhập</td>
                                <td>mật khẩu</td>
                                <td>0531346871</td>
                                <td>email@gmail.com</td>
                                <td>hoạt động</td>
                                <td>ngày tạo</td>
                                <td>ngày sửa</td>
                                <td>
                                    <a href="/update" class="btn table-btn" type="submit"><i class="fas fa-edit"></i></a>
                                    <a href="/delete" class="btn table-btn" type="submit"><i class="fas fa-trash-alt"></i></a>
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</body>
</html>