$(window).on('load', function(){
	$('.item-loader').hide();
})

// -------------------- ajax get load item khi scroll đến đáy trang -----------------
$(window).on('scroll', loadMoreProduct);

function loadMoreProduct(){
	if($(window).scrollTop() >= $(document).height() - $(window).height()) {
		
		let currentPage = Number($('[name="hidden-current-page"]').attr('value'));
		let totalPage = Number($('[name="hidden-total-page"]').attr('value'));
		let requestedPage = -1;
		if ( Number.isNaN(currentPage) || Number.isNaN(totalPage) ) {
			return;
		}
		requestedPage = currentPage+1;
		if ( requestedPage>totalPage || requestedPage<1 ) {
			console.log('requested page không hợp lệ')
			return;
		}
		
		$('.item-loader').show(); //  hieu ung
		
		$(window).off('scroll', loadMoreProduct); // tắt event listener
		
		$.ajax({
			url: '/ajax-search',
			type: 'GET',
			data: 'requested-page='+ requestedPage,
			success: function(response){
				if ( response!=null && Array.isArray(response) ) {
					const pageInfoObject = JSON.parse(response[0]);
					const listItem = JSON.parse(response[1]);
					
					updatePageAttr(pageInfoObject);
					
					appendItemList(listItem);
				}
				
				$(window).on('scroll', loadMoreProduct); // bật lại listener
				$('.item-loader').hide(); // an hieu ung
			}
		})
		
    }
}

function appendItemList(productArray){
	productArray.forEach( (product) => {

        document.querySelector('.product-row').innerHTML +=

        `<div class="col-12 col-md-6 col-xl-4 mb-4">
            <div class="product">
                <a class="product__img" href="${product.c_slug}${product.pr_slug}">
                    <img src='data:image/jpg;base64,${product.base64Image}' alt="">
                </a>
                <div class="product__info">
                    <a href="${product.c_slug}${product.pr_slug}">
                        <h2 class="product__info__title">${product.pr_name}</h2>
                    </a>
                    <p class="product__info__price money">${product.pr_price}</p>
                </div>
            </div>
        </div>`
    })  
}

function updatePageAttr(pageInfoObject){
	$('[name="hidden-total-page"]').attr('value', pageInfoObject.totalPage);
	$('[name="hidden-current-page"]').attr('value', pageInfoObject.currentPage);
}