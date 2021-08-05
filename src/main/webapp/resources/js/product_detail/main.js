let specificProductsList = [];
const moneySubfix = '.00';
$(window).on('load', function(){
    $('.btn-increase').on('click', function(event) {
        let currentValue = Number($('[name="product-quantity"]').attr('value'));
        $('[name="product-quantity"]').attr('value', currentValue + 1);
    })

    $('.btn-decrease').on('click', function(event) {
        let currentValue = Number($('[name="product-quantity"]').attr('value'));
        if (currentValue >= 1) {
            $('[name="product-quantity"]').attr('value', currentValue - 1);
        }
    })

   
	$('.carousel .carousel-indicators > li:first-child').addClass('active');
	$('.carousel .carousel-inner .carousel-item:first-child').addClass('active');

	/* ------------ for choosing color, size and displaying real price -------------- */
	specificProductsList = JSON.parse($('[name="specificProducts"]').attr('value')); // get list of specific product info from jsp 
	
	renderFirstSpecificProductOptions();
	function renderFirstSpecificProductOptions(){
		
		let colorsList =[];
		let sizesList = [];
		
		specificProductsList.forEach( item => {
			if ( !colorsList.includes(item.color) ) {
				colorsList.push(item.color);			
			}
		})
		
		updateColorsListInDOM(colorsList); // hiển thị danh sách color
		$('.detail__right__color > ul > li:first-child > [name="hidden-color"]').attr('checked', 'checked'); // set color đầu tiên check mặc định
		updateSizesOnColorChange($('[name="hidden-color"]:checked').attr('value')); // hiển thị danh sách size theo color vừa được check
		var i=0;
		specificProductsList.forEach(productInfo=>{
			if (productInfo.color == $('[name="hidden-color"]:checked').attr('value')) {
				$('.detail__right__price').text(productInfo.price + moneySubfix);
				$('[name="hidden-price"]').attr('value', productInfo.price + moneySubfix); // dùng cho LocalStorage
			}
		})
	}
	
	$('[name="hidden-color"]').on('click', function(){
		updateSizesOnColorChange($(this).attr('value'));
	})
	
	$('[name="hidden-size"]').on('click', function(){
		console.log('size box clicked')
		if ( $('[name="hidden-color"]:checked') ) {
			showSpecificPrice($(this).attr('value'), $('[name="hidden-color"]:checked').attr('value'));
		}
	})
	function showSpecificPrice(clickedSize, clickedColor){
		specificProductsList.forEach( productInfo => {
			if ( productInfo.size==clickedSize && productInfo.color==clickedColor ) {
				$('.detail__right__price').text(productInfo.price + moneySubfix);
				$('[name="hidden-price"]').attr('value', productInfo.price + moneySubfix); // dùng cho LocalStorage
			}	
		})
	}
	
	function updateSizesOnColorChange(clickedValue){ // khi một radio color được bấm vào, hàm này sẽ được gọi
		$('.detail__right__size > ul').html('');
		
		let sizesList = [];
		
		specificProductsList.forEach( productInfo => {
			if (productInfo.color == clickedValue) {
				sizesList.push(productInfo.size);
			}	
		})
		
		updateSizesListInDOM(sizesList);
	}
	
	function updateSizesListInDOM(sizesArray) {
		let innerHtmlSize = '';
		sizesArray.forEach(item =>{
			innerHtmlSize += `<li>
                                   <input type="radio" name="hidden-size" value="${item}" form="productOption">
									<span>${item}</span>
                               </li>`
		})
		document.querySelector('.detail__right__size > ul').innerHTML = innerHtmlSize;
		
		//lắng nghe thay đổi với dom với
		$('[name="hidden-size"]').on('click', function(){
		console.log('size box clicked')
		if ( $('[name="hidden-color"]:checked') ) {
			showSpecificPrice($(this).attr('value'), $('[name="hidden-color"]:checked').attr('value'));
		}
	})
	}
	function updateColorsListInDOM(colorsArray){
		let innerHtmlColor = '';
		colorsArray.forEach(item =>{
			innerHtmlColor += `<li>
                                   <input type="radio" name="hidden-color" value="${item}" form="productOption">
									<span style="background-color: ${item}; opacity: 0.9;"></span>
                               </li>`
		})
		document.querySelector('.detail__right__color > ul').innerHTML = innerHtmlColor;
	}
	

        
    /*
    Dùng js lấy ra giá trị đã lưu trên browser từ LocalStorage (giá trị ở đây
    là category_slug ở header của trang web, thành phần mà page nào của website
    cũng có) ->
    Gửi lên server qua hidden form với name là 'category_slug_list' gồm tên category
    và slug của nó ->
    Server check, nếu có parameter này, không cần load lại giá trị đó từ DB nữa.
    */

    $('#productOption').validate({
		
        submitHandler: function (form, event) {
			event.preventDefault();
        },
        rules: {
            'hidden-color': { // <- NAME of every radio in the same group
                required: true
            },
            'hidden-size': {
                required: true,
            }
        },
        messages: {
            'hidden-color': {
                required: "Vui lòng chọn màu"
            },
            'hidden-size': {
                required: "Vui lòng chọn size"
            }
        },
        errorPlacement: function (errorElement, invalidElement) {
            errorElement.insertAfter(invalidElement.closest('ul'));
        },
    })
	
	$('.btn-add-to-cart').on('click',function(event){
		console.log('add to cart clicked');
		storeProductInfoIntoLS();	
		displayNumberIndicator();
	})
	
	$('.btn-buy-now').on('click',function(event){
		console.log('buy now clicked');
		storeProductInfoIntoLS();	
		displayNumberIndicator();
		document.querySelector('.go-to-cart-page').click(); // go to cart page
	})
	
    
	/* -------- lưu thông tin sp trong giỏ hàng thông qua LocalStorage ------------- */
    function storeProductInfoIntoLS() {
        let id = $('[name="hidden-id"]').val();
        let name = $('[name="hidden-name"]').val();
        let image = $('[name="hidden-image"]').val();
        let price = $('[name="hidden-price"]').val();
        let color = $('[name="hidden-color"]:checked').val();
        let size = $('[name="hidden-size"]:checked').val();
        let quantity = $('[name="product-quantity"]').val();
		let categorySlug = $('[name="hidden-category-slug"]').val();
		let productSlug = $('[name="hidden-product-slug"]').val();

        
        let productListInLS = getProductListFromLS();

        if (productListInLS!=null && productListInLS.length>0) { // danh sách không rỗng, cần kiểm tra id trùng
            let isDuplicatedId = productListInLS.some( product => {
                return product.id === id;
            })

            if (!isDuplicatedId) {
                productListInLS.push({
                    id, name, image, price, color, size, quantity, categorySlug, productSlug
                })

                localStorage.setItem('productList', JSON.stringify(productListInLS));
            } else { // nếu trùng id, cập nhật các chỉ số khác, trừ id
                productListInLS.forEach( (item, index) => {
                    if (item.id === id) {
                        item.name = name;
                        item.image = image;
                        item.price = price;
                        item.color = color;
                        item.size = size;
                        item.quantity = quantity;
						item.categorySlug = categorySlug;
						item.productSlug = productSlug;
                    }
                })
                localStorage.setItem('productList', JSON.stringify(productListInLS));
            }
        } else { // nếu list chưa có gì, thêm product luôn vào
            let productList = [];
            productList.push({
                id, name, image, price, color, size, quantity, categorySlug, productSlug
            })

            localStorage.setItem('productList', JSON.stringify(productList));
        }
    }
   
})