
$(window).on('load', function(){
	doPaginating();
	
	
})


// ----------------- gán handler cho chuyển trang ajax (GET) ---------------------
$('.pagination > ul').on('click', '.btn-pagination', goToAnotherPage);

// ----------------- gán handler cho thay đổi số lượng item ajax (POST) ---------------------
$('#item-per-page-select').on('change', changeItemPerPage);

// ----------------- gán id của item muốn xóa sang nút confirm của modal để xóa ---------------------
// ta dùng handler delegation ở đây (ủy quyền)
$('#product > tbody').on('click', '.btn-show-modal', function(){
	let itemId = $(this).attr('product-id');
	if ( Number.isNaN(itemId) ) {
		itemId = -1; // gia tri mac dinh, tranh loi
	}
	
	$('.btn-delete').attr('product-id', itemId);
})

// ----------------- gán handler cho việc xóa item ajax (GET) ---------------------
$('.btn-delete').on('click', deleteItem);


function deleteItem(){
		$('#confirmModal').modal('hide') // hide bootstrap modal
		
		let itemId =  $(this).attr('product-id');
		if ( itemId==-1 ) {
			return;
		}
		
		let formData = 'id='+ itemId;
		
		$.ajax({
			url: '/ajax-product-delete',
			type: 'GET',
			data: formData,
			success: function(response){ // response ở dạng string (vì bên server set contentType = text/html)
				if ( response!=null && Array.isArray(response) ){ // có message thành công trả về
					const pageInfoObject = JSON.parse(response[0]);
					const productList = JSON.parse(response[1]);
					
					update4PageAttr(pageInfoObject);
					doPaginating();
					loadNewProductList(productList);
					 	
					showToast();		
				}
				else { // thất baị
					console.log('có thể sản phẩm này là cha của nhiều record khác trong db, hãy xóa chúng trước (specific product hoặc con của nó)')
				}
			}
		})
}

function changeItemPerPage(){
	let itemPerPage =$(this).val();
		let formData = 'item-per-page='+itemPerPage;
		$.ajax({
			url: '/ajax-product-read',
			type: 'POST',
			data: formData,
			success: function(response){
				if ( response!=null && Array.isArray(response) ) {
					const pageInfoObject = JSON.parse(response[0]);
					const productList = JSON.parse(response[1]);
					
					update4PageAttr(pageInfoObject); // cần update trước sau đó mới phân trang được
					doPaginating();
					loadNewProductList(productList);
				}
			}
		})
}

function goToAnotherPage(){
		const requestedPage = $(this).attr('pagination-value');
		const currentPage = $('[name="hidden-current-page"]').attr('value');
		
		if ( (requestedPage == currentPage) || Number.isNaN(requestedPage) || /\./g.test(requestedPage) ){
			console.log('trung trang hoac trang khong dung');
			return;
		}
		
		const formData = 'requested-page='+ requestedPage;
		$.ajax({
			url: '/ajax-product-read',
			type: 'GET',
			data: formData,
			success: function(response) {
				if ( response!=null && Array.isArray(response) ) {
					const pageInfoObject = JSON.parse(response[0]); // object
					const productList = JSON.parse(response[1]); // array
					
					update4PageAttr(pageInfoObject);
					doPaginating();
					loadNewProductList(productList);
				}
			}
		})
}


function loadNewProductList(productList) {
	document.querySelector('table#product > tbody').innerHTML = 
			productList.map((p, index) => {
				return `<tr class="product-row">
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
						<a class="btn btn-update" href='/product-update/${p.product_line_id}' product-id=${p.product_line_id}><i class="fas fa-pen-square"></i></a> 
						<button type="button" product-id=${p.product_line_id} class="btn btn-show-modal" data-bs-toggle="modal" data-bs-target="#confirmModal"><i class="fas fa-minus-circle"></i></button>
					</td>
				</tr>`
			}).join('');
}

function update4PageAttr(pageInfoObject) { // cập nhật lại DOM của 3 value khi ajax trả về
	$('[name="hidden-item-per-page"]').attr('value', pageInfoObject.itemPerPage);
	$('[name="hidden-total-page"]').attr('value', pageInfoObject.totalPage);
	$('[name="hidden-current-page"]').attr('value', pageInfoObject.currentPage);
	$('[name="hidden-product-count"]').attr('value', pageInfoObject.itemCount);
}

function doPaginating() {
	let currentPage = $('[name="hidden-current-page"]').attr('value');
	let totalPage = $('[name="hidden-total-page"]').attr('value');
	
	let pageRangeArray = pagination(currentPage, totalPage);

	document.querySelector('.pagination > ul').innerHTML =
		pageRangeArray.map(item => {
			if (item == currentPage)
				return `<li> <button type="button" class="btn btn-pagination active" pagination-value="${currentPage}">${currentPage}</button> </li>`
			else
				return `<li> <button type="button" class="btn btn-pagination" pagination-value="${item}">${item}</button> </li>`
		}).join('');
}

function pagination(c, m) { // from github
	var current = c,
		last = m,
		delta = 2,
		left = current - delta,
		right = current + delta + 1,
		range = [],
		rangeWithDots = [],
		l;

	for (let i = 1; i <= last; i++) {
		if (i == 1 || i == last || i >= left && i < right) {
			range.push(i);
		}
	}

	for (let i of range) {
		if (l) {
			if (i - l === 2) {
				rangeWithDots.push(l + 1);
			} else if (i - l !== 1) {
				rangeWithDots.push('...');
			}
		}
		rangeWithDots.push(i);
		l = i;
	}

	return rangeWithDots;
}
