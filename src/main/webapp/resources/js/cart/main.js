	
    const moneySubfix = '.00';


	$('.btn-continue').on('click', function(event){
		event.preventDefault();
		
		// if người dùng chưa đăng nhập, chuyển qua trang đăng nhập
		if( $('[name="login-token-for-user"]').attr('value') != 'user' ) {
			document.querySelector('.btn-login').click();
			return;
		};
		
		
		
		let productListFormLS = getProductListFromLS();
		let quantityList = document.querySelectorAll('.tr:not(.tr:first-child) [name="product-quantity"]');// không lấy phần tử đầu (product row đầu dùng để clone dom)
	
		if (productListFormLS.length > 0){
			$('.loader').show();// app.y hiệu ứng chờ
			
			let formData = '';
			productListFormLS.forEach( (product, index) => {
				formData += `id-${index}=${product.id}&color-${index}=${product.color}&size-${index}=${product.size}&quantity-${index}=`
								+quantityList[index].getAttribute('value')+"&";	
			})
			
			$.ajax({
				url: '/ajax-cart',
				type: 'POST',
				data: formData,
				cache: false,
				success: function(response){
					$('.loader').fadeOut(300); // bỏ hiệu ứng chờ
					if (response!=null && response.startsWith('/')) {// không có lỗi
						// set quantity cho LS
						let quantityList = document.querySelectorAll('.tr:not(.tr:first-child) [name="product-quantity"]');
						productListFormLS.forEach( (product, index) => {
							product.quantity = quantityList[index].getAttribute('value');
						})
						storeProductList(productListFormLS);
						
						// chuyển sang trang checkout
						window.location.href = response;
						window.location.assign();
					}
					else {
						$('.error-message').text(response);
						$('.error-message').addClass('on');
					}
				}
			})
			
		}
		else {
			$('.error-message').text('Không có sản phẩm, hãy mua sắm nào');
			$('.error-message').addClass('on');
		}
	})

    loadProductToCart();
    function loadProductToCart(){
        let productListFormLS = getProductListFromLS();
				
        if (productListFormLS != null && productListFormLS.length>0) {
			$('.empty-cart').removeClass('on');
			$('.btn-continue').attr('disabled', 'disabled');
	
            productListFormLS.forEach( (item, index) => {
                let productDOM = cloneProductRowNode();
                
                productDOM.querySelector('[name="hidden-product-id"]').setAttribute('value', item.id);
                productDOM.querySelector('.initial-price').innerHTML = item.price;
                productDOM.querySelector('.card__product-info__name').innerHTML = item.name;
                productDOM.querySelector('.parent-image > img').src = item.image;
                productDOM.querySelector('.card__product-info__size > span').innerHTML = item.size;
                productDOM.querySelector('.card__product-info__color > span').style.backgroundColor = item.color;
                productDOM.querySelector('[name="product-quantity"]').setAttribute('value', item.quantity);
                productDOM.querySelector('.parent-image').href = item.categorySlug + item.productSlug;
                                        
                document.querySelector('.cart__products__left > .table > .tbody').appendChild(productDOM);
                
            })
        }
		else {
			$('.empty-cart').addClass('on');		
		}
        
    }

    
    
    function cloneProductRowNode() {
        const node = document.querySelector('.cart__products__left > .table > .tbody > .tr.product-row');
        return node.cloneNode(true);
    }
            
    setTotalPriceForEachProducts();
    setTotalPriceOfAllProducts();
            
    $('.btn-decrease').on('click', function() {
        changeProductQuantity($(this), 'decrease')
    })

    $('.btn-increase').on('click', function() {
        changeProductQuantity($(this), 'increase');
    })
    removeProduct();
    function removeProduct() {
        $('.remove-product').on('click', function() {
            // cập nhật giá trong bill, nếu không còn sp nào -> reset delivery và discount price thành 0
            let initialPrice = Number($(this).closest('.tr').find('.initial-price').text());
            let quantity = Number($(this).closest('.tr').find('input[name="product-quantity"]').val());
            let total = initialPrice*quantity;
            
            const subtitlePriceTag = $('.card__total-price .subtotal-price');
            let currentSubtitlePrice = Number(subtitlePriceTag.text());
            subtitlePriceTag.html( currentSubtitlePrice - total );

            if ( getProductListFromLS().length <= 0 ) {
                setDeliveryPrice(0);
                setDiscountPrice(0);
            }

            updateBill();
            

            // xóa sp khỏi localStorage
            let productListFromLS = getProductListFromLS();
            let productId = $(this).closest('.tr').find('[name="hidden-product-id"]').val();
            let newList = [];
            if (productListFromLS!=null) {
                newList = productListFromLS.filter( product => { // list product mới không còn sp vừa xóa
                    return product.id !== productId;
                })
            }
	
            localStorage.setItem('productList', JSON.stringify(newList));
			displayNumberIndicator();


            // xóa trường sp khỏi dom
            $(this).closest('.tr').remove();
        })
    }

    function changeProductQuantity(target, type) {
        let quantity = Number(target.parent().find('input[name="product-quantity"]').val());

        if (type=='increase') {
            target.parent().find('input[name="product-quantity"]').val(quantity+1);

            let initialPriceTag = target.closest('.tr').find('.initial-price');
            let totalPriceTag = target.closest('.tr').find('.total-price');
            
            let currentQuantity = quantity+1;
            let initialPrice = Number(initialPriceTag.text());
            totalPriceTag.html( (currentQuantity*initialPrice) +moneySubfix);

            setTotalPriceOfAllProducts();

			target.parent().find('[name="product-quantity"]').attr('value', currentQuantity);
        } 
        else if (type=='decrease') {
            if (quantity >= 2) {

                // quantity on input tag
                target.parent().find('input[name="product-quantity"]').val(quantity-1);

                const initialPriceTag = target.closest('.tr').find('.initial-price');
                const totalPriceTag = target.closest('.tr').find('.total-price');
                
                // total price for each item
                let currentQuantity = quantity-1;
                let initialPrice = Number(initialPriceTag.text());
                totalPriceTag.html( (currentQuantity*initialPrice) +moneySubfix );

                setTotalPriceOfAllProducts();

				target.parent().find('[name="product-quantity"]').attr('value', currentQuantity);
            }
        }
    }

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