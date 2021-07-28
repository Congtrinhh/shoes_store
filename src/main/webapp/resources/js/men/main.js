window.scrollTo(0,0);

$('#filterBtn').on('click', loadProducts);
function loadProducts() {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', '/ajax-men-products', true);

    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	xhr.onloadstart = function(){
		// Thêm hiệu ứng chờ
		$('.loader').addClass('on');
	}
	
    xhr.onload = function(event) {
        if (this.status==200) {
			window.scrollTo(0,0); // quay về đầu trang
			// bỏ hiệu ứng chờ ...
            parseProducts(JSON.parse(this.responseText));
			completePagination(JSON.parse(this.responseText));

			$('.pagination .btn-pagination').on('click', loadProductsPerPage);
        }

		// Bỏ hiệu ứng chờ
		$('.loader').removeClass('on');
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



$('.pagination .btn-pagination').on('click', loadProductsPerPage);

function loadProductsPerPage(){
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/ajax-men-products'+'?requestedPage='+$(this).attr('pagination-value')); // gửi request với param là requestedPage
    
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    
    xhr.onload = function(event){
        if (this.status == 200){
            if (this.responseText.length > 0) {
				window.scrollTo(0,0); // quay về đầu trang
	
				parseProducts(JSON.parse(this.responseText));
            	completePagination(JSON.parse(this.responseText));

				$('.pagination .btn-pagination').on('click', loadProductsPerPage);
			}
			else {
				console.log("Có lỗi khi lấy dữ liệu ajax [GET] /ajax-men-products servlet");
			}
        }
    }

	xhr.onerror = function(event){
		console.log("Có lỗi khi lấy dữ liệu ajax [GET] /ajax-men-products servlet");
	}
    xhr.send();
}

$(window).on('load', doPaginationWindow);
function doPaginationWindow(){
    let currentPageIndex = Number($('[name="hidden-current-page"]').attr('value'));
    let totalPages = Number($('[name="hidden-total-pages"]').attr('value'));
	
	if (totalPages<=1) {
		document.querySelector('.pagination > ul').innerHTML = '';
		return;
	}

    console.log(currentPageIndex,totalPages);
    let pageNumbersArray = doPagination(currentPageIndex, totalPages);

    document.querySelector('.pagination > ul').innerHTML = '';

    document.querySelector('.pagination > ul').innerHTML =
     pageNumbersArray.map( pageNumber => {
        if (pageNumber == currentPageIndex) {
            return `<li> <button type="button" class="btn btn-pagination active" pagination-value="${currentPageIndex}">${currentPageIndex}</button> </li>`
        }
        else {
            return `<li> <button type="button" class="btn btn-pagination" pagination-value="${pageNumber}">${pageNumber}</button> </li>`
        }
    } ).join('');

$('.pagination .btn-pagination').on('click', loadProductsPerPage);
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
function completePagination(productsArray){

	if (productsArray.length>0) {
		let currentPageIndex = Number(productsArray[0].currentPage);
    	let totalPages = Number(productsArray[0].totalPages);
   		if (typeof currentPageIndex == 'number' && typeof totalPages=='number') {
			if (totalPages <=1) {
				document.querySelector('.pagination > ul').innerHTML = '';
				return;
			}
	
			let pageNumbersArray = doPagination(currentPageIndex, totalPages);

		    document.querySelector('.pagination > ul').innerHTML = '';
		
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
	else {
		console.log('khong co san pham');
	}    
}