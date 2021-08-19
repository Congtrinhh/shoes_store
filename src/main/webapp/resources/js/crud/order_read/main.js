$(window).on('load', function(){
	$('.loader').fadeOut();
	
	doPaginating();
})

// ---------------------- gán handler cho ul khi nút bên trong được bấm. nút này không nhất thiết phải tồn tại ngay khi gán handler, nó có thể xuất hiện sau -----------------------
$('.pagination > ul').on('click', '.btn-pagination', goToAnotherPage);

function goToAnotherPage(event){
	event.preventDefault();
	
	const requestedPage = $(this).attr('pagination-value');
	const currentPage = $('[name="hidden-current-page"]').attr('value');

	if ( (requestedPage == currentPage) || Number.isNaN(requestedPage) || /\./g.test(requestedPage) ) { //  /\./ để test liệu trang đó có giá trị là ... ?
		return;
	}
	
	$('.loader').show();
	
	$.ajax({
		url: '/ajax-order-read',
		type: 'GET',
		data: 'requested-page='+requestedPage,
		success: function(response){
			if ( response != null && Array.isArray(response) ) {
				const pageInfoObject = JSON.parse(response[0]); // object
				const itemList = JSON.parse(response[1]); // array
				
				updatePageAttr(pageInfoObject);
				doPaginating();
				loadItemToDOM(itemList);
			}
			$('.loader').fadeOut(300);
		}
	})
}

function loadItemToDOM(itemList){
	if ( Array.isArray(itemList) ) {
		document.querySelector('table#order > tbody').innerHTML = 
			itemList.map( order => {
				return `<tr class="order-row">
							<td>${order.order_id}</td>
							<td>${order.admin_id}</td>
							<td>${order.user_id}</td>
							<td>${order.or_shipping_cost}</td>
							<td>${order.or_status}</td>
							<td>${order.ward_id}</td>
							<td>${order.specific_address}</td>
							<td>${order.created_at}</td>
							<td>${order.updated_at}</td>
							<td>
								<!-- for update -->
								<a class="btn btn-update" href='/order-update/${order.order_id}' order-id=${order.order_id}><i class="fas fa-pen-square"></i></a> 

								<!-- for delete - button trigger modal -->
    							<button type="button" order-id=${order.order_id} class="btn btn-show-modal" data-bs-toggle="modal" data-bs-target="#confirmModal"><i class="fas fa-minus-circle"></i></button>
							</td>
						</tr>`
			}).join('');
	}
}

function updatePageAttr(pageInfoObject){
	$('[name="hidden-item-per-page"]').attr('value', pageInfoObject.itemPerPage);
	$('[name="hidden-total-page"]').attr('value', pageInfoObject.totalPage);
	$('[name="hidden-current-page"]').attr('value', pageInfoObject.currentPage);
	$('[name="hidden-item-count"]').attr('value', pageInfoObject.itemCount);
}

function doPaginating(){
	const currentPage = Number($('[name="hidden-current-page"]').attr('value'));
	const totalPage = Number($('[name="hidden-total-page"]').attr('value'));
	if ( !Number.isNaN(currentPage) && !Number.isNaN(totalPage) ) {
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