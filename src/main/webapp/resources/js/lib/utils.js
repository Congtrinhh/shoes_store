//$(window).on('load', function(){
      

			
            displayNumberIndicator();

            function displayNumberIndicator() {
                let productListFromLS = getProductListFromLS();
                if (productListFromLS == null || productListFromLS=='' || productListFromLS==undefined || productListFromLS==[]) {
                    $('.number-indicator').html(0);
                } else {
                    $('.number-indicator').html(productListFromLS.length);
                }
				$('.number-indicator').addClass('indicatorAnimation');
				setTimeout(function(){
					$('.number-indicator').removeClass('indicatorAnimation');
				}, 750);
            }
			
			/* local storage - get list products */
            function getProductListFromLS() {
				let list = localStorage.getItem('productList');
				
				if (list.length>0) {
					try {
						return JSON.parse(list);
					} catch(e) {
						console.log(e.message);
					} 
				}
                return null;
            }
			
			function storeProductList(list){
				localStorage.setItem("productList", JSON.stringify(list));
			}

// try -catch loi
			
			function removeProductList() {
				localStorage.removeItem("productList");
			}
			
			
			function toggleDropDownResultSearch(){
    $('[name="home-search"]').on('focus', function() {
        $(this).siblings('ul').addClass('on');
    })
    $('[name="home-search"]').on('blur', function() {
        $(this).siblings('ul').removeClass('on');
    })
}

toggleDropDownResultSearch();

function toggleMenu() {
    $('#menu-toggle').on('click', function(){
        $(this).toggleClass('on');
        $('.nav-and-search').toggleClass('on');
        $('.overlay').toggleClass('on');

    })
    $('.overlay').on('click', function(){
        $(this).removeClass('on');
        $('.nav-and-search').removeClass('on');
        $('#menu-toggle').toggleClass('on');
    })
}
toggleMenu();
			
function updateFooterContent() {
    if (window.innerWidth >= 576) {
        $('.footer__sub-content').addClass('show');
    } else {
        $('.footer__sub-content').removeClass('show');
    }
}
updateFooterContent()
         
//})