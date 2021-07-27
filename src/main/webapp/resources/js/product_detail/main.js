
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

    $('.carousel').carousel({
        interval: 3000,
    })




        
    /*
    Dùng js lấy ra giá trị đã lưu trên browser từ LocalStorage (giá trị ở đây
    là category_slug ở header của trang web, thành phần mà page nào của website
    cũng có) ->
    Gửi lên server qua hidden form với name là 'category_slug_list' gồm tên category
    và slug của nó ->
    Server check, nếu có parameter này, không cần load lại giá trị đó từ DB nữa.
    */

    $('#productOption').validate({
        ignore: '',
        submitHandler: function (form, event) {
					
			event.preventDefault();
            storeProductInfoIntoLS();	
			displayNumberIndicator();
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

	
    

    function storeProductInfoIntoLS() {
        let id = $('[name="hidden-id"]').val();
        let name = $('[name="hidden-name"]').val();
        let image = $('[name="hidden-image"]').val();
        let price = $('[name="hidden-price"]').val();
        let color = $('[name="hidden-color"]:checked').val();
        let size = $('[name="hidden-size"]:checked').val();
        let quantity = $('[name="product-quantity"]').val();

        
        let productListInLS = getProductListFromLS();

        if (productListInLS != null) { // danh sách không rỗng, cần kiểm tra id trùng
            let isDuplicatedId = productListInLS.some( product => {
                return product.id === id;
            })

            if (!isDuplicatedId) {
                productListInLS.push({
                    id, name, image, price, color, size, quantity,
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
                    }
                })
                localStorage.setItem('productList', JSON.stringify(productListInLS));
            }
        } else { // nếu list chưa có gì, thêm product luôn vào
            let productList = [];
            productList.push({
                id, name, image, price, color, size, quantity,
            })

            //console.log(productListInLS);

            localStorage.setItem('productList', JSON.stringify(productList));
        }
    }
   
})
    
