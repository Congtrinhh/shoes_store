
$(window).on('load', function(){
	window.scrollTo(0,0);
	
	doPaginationWindow();
});

// -------------------- ajax get load item khi click nút chuyển trang mới -----------------
$('.pagination > ul').on('click', '.btn-pagination', loadProductsPerPage);

// -------------------- ajax pót load item khi thay đổi item mỗi trang -----------------
$('#filterBtn').on('click', loadProducts);


function loadProducts() {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/ajax-men-products', true);

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	xhr.onloadstart = function(){
		// Thêm hiệu ứng chờ
		$('.loader').show();
	}
	
    xhr.onload = function(event) {
        if (this.status==200) {
			window.scrollTo(0,0); // quay về đầu trang
			// bỏ hiệu ứng chờ ...
			if (this.responseText.length>0){
				let responseArray = JSON.parse(this.responseText);
				const pageInfoObj = JSON.parse(responseArray[0]);
				let productList = JSON.parse(responseArray[1]);
				
				updatePageAttr(pageInfoObj);
				completePagination();
				parseProducts(productList);
			}
        }

		// Bỏ hiệu ứng chờ
		$('.loader').fadeOut(300);
    }
	
    let paramsArray = document.getElementById('filter').querySelectorAll('fieldset [name]');
    let params ='';
    paramsArray.forEach( field => {
        params += `${field.name}=${field.value}&`;
    } )
    
    if (params.lastIndexOf('&')===params.length-1) {
        params = params.substr(0, params.lastIndexOf('&'));
    }

    xhr.send(params);
}

function parseProducts(productArray) {
     document.querySelector('.product-row').innerHTML = '';

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
	$('[name="hidden-total-pages"]').attr('value', pageInfoObject.totalPages);
	$('[name="hidden-current-page"]').attr('value', pageInfoObject.currentPage);
	$('[name="hidden-product-count"]').attr('value', pageInfoObject.productCount);
}

function loadProductsPerPage(){
	if ( $(this).attr('pagination-value') == $('[name="hidden-current-page"]').attr('value') ) { // bấm vào trang đang đứng -> dừng hàm
		return;
	}
	
	$('.loader').addClass('on'); // them hieu ung cho
	
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/ajax-men-products'+'?requestedPage='+$(this).attr('pagination-value')); // gửi request với param là requestedPage
    
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    
    xhr.onload = function(event){
        if (this.status == 200){
            if (this.responseText.length > 0) {
				window.scrollTo(0,0); // quay về đầu trang
				let responseArray = JSON.parse(this.responseText);
				const pageInfoObject = JSON.parse(responseArray[0]);
				let productList =  JSON.parse(responseArray[1]);
				
				updatePageAttr(pageInfoObject);
            	completePagination();
				parseProducts(productList);

				$('.pagination .btn-pagination').on('click', loadProductsPerPage);
			}
			else {
				console.log("Có lỗi khi lấy dữ liệu ajax [GET] /ajax-men-products servlet");
			}
        }
		$('.loader').removeClass('on'); // bo hieu ung cho
    }

	xhr.onerror = function(event){
		console.log("Có lỗi khi lấy dữ liệu ajax [GET] /ajax-men-products servlet");
	}
    xhr.send();
}

function doPaginationWindow(){
    let currentPageIndex = Number($('[name="hidden-current-page"]').attr('value'));
    let totalPages = Number($('[name="hidden-total-pages"]').attr('value'));
	
	if (totalPages<=1) {
		document.querySelector('.pagination > ul').innerHTML = '';
		return;
	}

    let pageNumbersArray = doPagination(currentPageIndex, totalPages);

    document.querySelector('.pagination > ul').innerHTML =
	     pageNumbersArray.map( pageNumber => {
	        if (pageNumber == currentPageIndex) {
	            return `<li> <button type="button" class="btn btn-pagination active" pagination-value="${currentPageIndex}">${currentPageIndex}</button> </li>`
	        }
	        else {
	            return `<li> <button type="button" class="btn btn-pagination" pagination-value="${pageNumber}">${pageNumber}</button> </li>`
	        }
	     }).join('');
}


function completePagination(){

	let currentPageIndex = Number($('[name="hidden-current-page"]').attr('value'));
	let totalPages = Number($('[name="hidden-total-pages"]').attr('value'));
	if (typeof currentPageIndex == 'number' && typeof totalPages=='number') {
		if (totalPages <=1) {
			document.querySelector('.pagination > ul').innerHTML = '';
			return;
		}

		let pageNumbersArray = doPagination(currentPageIndex, totalPages);

	    document.querySelector('.pagination > ul').innerHTML =
			    pageNumbersArray.map( pageNumber => {
			        if (pageNumber == currentPageIndex) {
			            return `<li> <button type="button" class="btn btn-pagination active" pagination-value="${currentPageIndex}">${currentPageIndex}</button> </li>`
			        }
			        else {
			            return `<li> <button type="button" class="btn btn-pagination" pagination-value="${pageNumber}">${pageNumber}</button> </li>`
			        }
			    }).join('');
	}   
}

function doPagination(c, m) {
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