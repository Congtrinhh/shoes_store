// ---------------- phân trang khi nhận request get (lần đầu vào trang) ------------
$(window).on('load', function(){
	$('.loader').fadeOut();
	
	//phân trang khi nhận request get (lần đầu vào trang)
	doPaginating();
	
	
})

// ---------------------- gán handler cho ul khi nút bên trong được bấm. nút này không nhất thiết phải tồn tại ngay khi gán handler, nó có thể xuất hiện sau -----------------------
$('.pagination > ul').on('click', '.btn-pagination', goToAnotherPage);

// ------------------ gán handler cho thẻ select khi giá trị của nó thay đổi -----------
$('#item-per-page-select').on('change', changeItemPerPageQuantity);

//  ------------------ gán handler cho việc xóa item và làm 1 số việc sau xóa -----------
$('.btn-delete').on('click', deleteItem);

//  ------------------ gán item id cho button xác nhận xóa của modal -----------
$('#item-table > tbody').on('click', '.btn-show-modal', function(event){
	event.preventDefault();
	
	let itemId = $(this).attr('item-id');
	if ( Number.isNaN(itemId) ) {
		itemId = -1; // set value mac dinh, tranh loi
	} 
	
	$('.btn-delete').attr('item-id', itemId);
})

//	

function deleteItem(event){
	event.preventDefault();
	$('.modal').modal('hide');
	
	let itemId = $(this).attr('item-id');	
	if (itemId == -1) {
		console.log('id khong hop le');
		return;
	}
	
	$.ajax({
		url: '/ajax-specific-product-delete',
		type: 'GET',
		data: 'id=' + itemId,
		success: function(response){
			if ( response != null && Array.isArray(response) ) {
				const pageInfoObject = JSON.parse(response[0]); // object
				const itemList = JSON.parse(response[1]); // array
				
				update4PageAttr(pageInfoObject);
				doPaginating();
				loadItemToDOM(itemList);
			}
		}
	})
	
	
}


function changeItemPerPageQuantity(event){
	event.preventDefault();
	
	const itemPerPage = $(this).val();
	
	if ( Number.isNaN(itemPerPage) ) {
		return;
	}
	
	$('.loader').show();
	
	$.ajax({
		url: '/ajax-specific-product-read',
		type: 'POST',
		data: 'item-per-page=' + itemPerPage,
		success: function(response){
			if (response != null && Array.isArray(response) ){
				const pageInfoObject = JSON.parse(response[0]); // object
				const itemList = JSON.parse(response[1]); // array
				
				update4PageAttr(pageInfoObject);
				doPaginating();
				loadItemToDOM(itemList); 
			}
			$('.loader').fadeOut(300);
		}
	})
	
}

function goToAnotherPage(event){
	event.preventDefault();
	
	const requestedPage = $(this).attr('pagination-value');
	const currentPage = $('[name="hidden-current-page"]').attr('value');

	if ( (requestedPage == currentPage) || Number.isNaN(requestedPage) || /\./g.test(requestedPage) ) { //  /\./ để test liệu trang đó có giá trị là ... ?
		return;
	}
	
	$('.loader').show();
	
	$.ajax({
		url: '/ajax-specific-product-read',
		type: 'GET',
		data: 'requested-page='+requestedPage,
		success: function(response){
			if ( response != null && Array.isArray(response) ) {
				const pageInfoObject = JSON.parse(response[0]); // object
				const itemList = JSON.parse(response[1]); // array
				
				update4PageAttr(pageInfoObject);
				doPaginating();
				loadItemToDOM(itemList);
			}
			$('.loader').fadeOut(300);
		}
	})
}


function loadItemToDOM(itemList){
	if ( Array.isArray(itemList) ) {
		document.querySelector('#item-table > tbody').innerHTML = 
			itemList.map( p =>{
				return `<tr class="product-row">
							<td>${p.id}</td>
							<td>${p.product_line_id}</td>
							<td>${p.color_id}</td>
							<td>${p.size_id}</td>
							<td>${p.spr_price}</td>
							<td>${p.spr_quantity}</td>
							<td>
								<!-- for update -->
								<a class="btn btn-update" href='/specific-product-update/${p.id}' item-id=${p.id}><i class="fas fa-pen-square"></i></a> 
								<!-- for delete - button trigger modal -->
    							<button type="button" item-id=${p.id} class="btn btn-show-modal" data-bs-toggle="modal" data-bs-target="#confirmModal"><i class="fas fa-minus-circle"></i></button>
							</td>
						</tr>`
			}).join('');
	}
}

function update4PageAttr(pageInfoObject) { // cập nhật lại DOM của 4 attr khi ajax trả về
	$('[name="hidden-item-per-page"]').attr('value', pageInfoObject.itemPerPage);
	$('[name="hidden-total-page"]').attr('value', pageInfoObject.totalPage);
	$('[name="hidden-current-page"]').attr('value', pageInfoObject.currentPage);
	$('[name="hidden-item-count"]').attr('value', pageInfoObject.itemCount);
}

function doPaginating(){
	const currentPage = Number($('[name="hidden-current-page"]').attr('value'));
	const totalPage = Number($('[name="hidden-total-page"]').attr('value'));
	if (typeof currentPage=='number' && typeof totalPage=='number') {
		let pageIndexList = pagination(currentPage, totalPage);
		
		document.querySelector('.pagination > ul').innerHTML = 
			pageIndexList.map( (pageIndex) => { // page index start from 1, not 0
				if (pageIndex==currentPage){
					return `<li><button type="button" pagination-value=${pageIndex} class="btn btn-pagination active">${pageIndex}</button></li>`;
				}
				else {
					return `<li><button type="button" pagination-value=${pageIndex} class="btn btn-pagination">${pageIndex}</button></li>`;
				}
			}).join('');
	}
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