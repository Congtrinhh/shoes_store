 //$(window).on('load', function() {
    const moneySubfix = '.00';

	/* ajax- xử lí khi click nút xác nhận */
	$('.btn-submit').on('click', function(event){
		event.preventDefault();
		if ( $('#orderContactAddress').valid() ) {
			// hieu ung cho
			let formData = '';
			const productsListFromLS = getProductListFromLS();
			if (productsListFromLS.length>0){
				productsListFromLS.forEach( (product, index) => {
					formData += `pr-id-${index}=${product.id}&spr-color-${index}=${product.color}&spr-size-${index}=${product.size}&spr-quantity-${index}=${product.quantity}&`;
				})
			}
			formData +=  $('#orderContactAddress').serialize();  // 1 string chứa keys và values trong form orderContactAddress
			
			$.ajax({
				url:'/ajax-checkout',
				type: 'POST',
				data: formData, //
				cache: false,
				success: function(response){
					console.log(`tra du lieu ve tu ajax thanh cong ${response}`)
					// tat hieu ung chox	
					if (response==null){
						// click nút để chuyển qua trang đặt hàng thành công
						document.querySelector('.btn-forward').click();
					}
					else {
						$('.error-message').text(response);
						$('.error-message').addClass('on');
					}
				}
			})		
	
		}
	})


    /* validate contact address form */
$(document).ready(function(){
    
    $('#orderContactAddress').validate({
        rules: {
            'full-name': {
                required: true,
            },
            'phone-number': {

            },
            'specific-address': {

            },
        },

        messages: {
            'full-name': {
                required: 'Vui lòng nhập họ và tên',
            },
            'phone-number': {
                required: 'Vui lòng nhập số điện thoại',
            },
            email: {
                required: 'Vui lòng nhập email',
                email: 'Email chưa đúng định dạng'
            },
            'specific-address': {
                required: 'Vui lòng nhập địa chỉ cụ thể'
            },
            province: {
                required: 'Vui lòng chọn tỉnh/thành phố'
            },
            district: {
                required: 'Vui lòng chọn quận/huyện'
            },
            ward: {
                required: 'Vui lòng chọn xã/phường'
            }
        },
        errorPlacement: function(errorElem, invalidElem){
            if (invalidElem.prop('tagName')==='SELECT') {
                errorElem.insertAfter(invalidElem);
            }
            else {
                errorElem.insertAfter(invalidElem.parent());
            }
        }
    });


});






    /* set up giá đơn hàng, hiển thị sp,... */
    loadProductToCart();
    function loadProductToCart(){
        let productListFormLS = getProductListFromLS();
				
        if (productListFormLS != null && productListFormLS.length>0) {
            productListFormLS.forEach( (item, index) => {
                let productDOM = cloneProductRowNode();
                
                productDOM.querySelector('[name="hidden-product-id"]').setAttribute('value', item.id);
                productDOM.querySelector('.initial-price').innerHTML = item.price;
                productDOM.querySelector('.card__product-info__name').innerHTML = item.name;
                productDOM.querySelector('.parent-image > img').src = item.image;
                productDOM.querySelector('.card__product-info__size > span').innerHTML = item.size;
                productDOM.querySelector('.card__product-info__color > span').style.backgroundColor = item.color;
                productDOM.querySelector('[name="product-quantity"]').setAttribute('value', item.quantity);
                
                                        
                document.querySelector('.cart__products__left > .table > .tbody').appendChild(productDOM);
                
            })
        }
    }
    
    
    function cloneProductRowNode() {
        const node = document.querySelector('.cart__products__left > .table > .tbody > .tr.product-row');
        return node.cloneNode(true);
    }
            
    setTotalPriceForEachProducts();
    setTotalPriceOfAllProducts();
            

    function setTotalPriceForEachProducts() {
        // array of tags contain total price
        const totalPriceArray =  $('.cart__products__left > .table .tr .total-price');

        totalPriceArray.each(function(index) {
            let quantity = Number($(this).closest('.tr').find('input[name="product-quantity"]').val());
            let price = Number($(this).closest('.tr').find('.initial-price').text());
            
            $(this).text( (price*quantity) + moneySubfix )
        })
    }

    function setTotalPriceOfAllProducts() {
        const totalPriceArray =  $('.cart__products__left > .table .tr .total-price');
        let totalPrice = 0;
        totalPriceArray.each( function(index) {
            let quantity = Number($(this).closest('.tr').find('input[name="product-quantity"]').val());
            let price = Number($(this).closest('.tr').find('.initial-price').text());
            totalPrice += price * quantity;
        })
        $('.card__total-price .subtotal-price').text(totalPrice + moneySubfix);
        updateBill();
    }
    function updateBill() {
        const finalTotalPriceTag = $('.card__total-price .total-price');
        
        let subtitlePrice = Number($('.card__total-price .subtotal-price').text());
        let deliveryPrice = Number($('.card__total-price .delivery-price').text());
        let discountPrice = Number($('.card__total-price .discount-price').text());

        finalTotalPriceTag.text( (subtitlePrice+deliveryPrice - discountPrice) +moneySubfix)
    }
    function setDeliveryPrice(newPrice) {
        if (typeof newPrice === 'number') {
            $('.card__total-price .delivery-price').text(newPrice + moneySubfix);
        }
    }
    function setDiscountPrice(newPrice) {
        if (typeof newPrice === 'number') {
            $('.card__total-price .discount-price').text(newPrice + moneySubfix);
        }
    }
            
//})