<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Product read</title>
<!-- Jquery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    
    <!-- Bs 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
        integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
        crossorigin="anonymous" referrerpolicy="no-referrer" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/lib/utilities.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/lib/adminSidebar.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/crud/product_read/style.css">
    <script defer src="${pageContext.request.contextPath}/resources/js/lib/utils.js"></script>
    <script defer src="${pageContext.request.contextPath}/resources/js/lib/adminSidebar.js"></script>
    <script defer src="${pageContext.request.contextPath}/resources/js/crud/product_read/main.js"></script>
</head>
<body>

	<div id="app">
		 <!-- sidebar and logo top -->
        <jsp:include page="/WEB-INF/views/fragments/admin/sidebar.jsp"></jsp:include>

		<div class="content px-3">

			<div class="data-area">
				<!-- Liệt kê các bản ghi hiện tại (read) -->
				<div class="enumerate-area">
					<div class="hidden-page-info" style="display: none;">
						<input type="text" hidden name="hidden-item-per-page"
							value=${sessionScope.itemPerPage}> 
						<input type="text"
							hidden name="hidden-current-page"
							value=${sessionScope.currentPage}> 
						<input type="text"
							hidden name="hidden-total-page" value=${sessionScope.totalPage}>
						<input type="text" hidden name="hidden-product-count"
							value=${sessionScope.itemCount}> 
					</div>
					<div class="item-per-page">
						<select name='item-per-page-select' id="item-per-page-select" class='form-select'>
							<option value='5'>5</option>
							<option value='10' selected>10</option>
							<option value='20'>20</option>
							<option value='30'>30</option>
							<option value='50'>50</option>
						</select> <span>Sản phẩm/trang</span>
						<a href="${pageContext.request.contextPath}/product-create" class="btn"><i class="far fa-plus-square"></i> create new</a>
					</div>
					<table id='product' class=''>
						<caption>product line</caption>
						<thead>
							<th>id</th>
							<th>admin id</th>
							<th>category id</th>
							<th>slug</th>
							<th>name</th>
							<th>brand id</th>
							<th>price</th>
							<th>sku</th>
							<th>description</th>
							<th>created at</th>
							<th>updated at</th>
							<th>action</th>
						</thead>
						<tbody>
							<c:forEach items="${productList}" var="p">
								<tr class="product-row">
									<td>${p.product_line_id}</td>
									<td>${p.admin_id}</td>
									<td>${p.category_id}</td>
									<td>${p.pr_slug}</td>
									<td>${p.pr_name}</td>
									<td>${p.pr_brand_id}</td>
									<td>${p.pr_price}</td>
									<td>${p.pr_sku}</td>
									<td>${p.pr_description}</td>
									<td>${p.created_at}</td>
									<td>${p.updated_at}</td>
									<td>
										<!-- for update -->
										<a class="btn btn-update" href='${pageContext.request.contextPath}/product-update/${p.product_line_id}' product-id=${p.product_line_id}><i class="fas fa-pen-square"></i></a> 

										<!-- for delete - button trigger modal -->
            							<button type="button" product-id=${p.product_line_id} class="btn btn-show-modal" data-bs-toggle="modal" data-bs-target="#confirmModal"><i class="fas fa-minus-circle"></i></button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div class="pagination">
						<ul>
						</ul>
					</div>
					
					<!-- Modal -->
		            <div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
		                aria-hidden="true">
		                <div class="modal-dialog" role="document">
		                    <div class="modal-content">
		                        <div class="modal-header">
		                            <h5 class="modal-title" id="exampleModalLabel">Xóa ?</h5>
		                            <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
		                                <span aria-hidden="true">&times;</span>
		                            </button>
		                        </div>
		                        <div class="modal-body">
		                            Sau khi xóa, bản ghi sẽ không thể khôi phục
		                        </div>
		                        <div class="modal-footer">
		                            <button type="button" class="btn btn-dismiss" data-bs-dismiss="modal">Cancel</button>
		                            <button type="button" class="btn btn-delete">Confirm</button>
		                        </div>
		                    </div>
		                </div>
		            </div>
		            
				</div>
			</div>

		</div>
		
		<!-- Toast, các text bên trong tự chỉnh thông qua .textContent, innnerHTML -->
		<jsp:include page="/WEB-INF/views/fragments/toast.html"></jsp:include>
		
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>