/* --------------- validate form --------------- */
$('#createForm').validate({
	rules: {
		price: {
			isNumber: true
		},
		quantity: {
			isNumber: true
		}
	},
	messages: {
		'product-line-id': 'Vui lòng chọn tên dòng sản phẩm',
		'color-id': 'Vui lòng chọn màu',
		'size-id': 'Vui lòng chọn size',
		price: {
			required: 'Vui lòng nhập giá',
		},
		quantity: {
			required: 'Vui lòng nhập số lượng ban đầu',
		},
	},
})

jQuery.validator.addMethod('isNumber', function(value){
	const regex = /[^\d\.]/g;
	return !regex.test(value.trim());
}, 'Định dạng số chưa đúng');


// ----------------- hiện menu chọn khi bấm nút edit -------------------
$('.btn-edit').on('click', function(){
	$(this).closest('fieldset').find('select').css('display', 'block');
})

// ------------------ ajax gửi thông tin cập nhật item -----------------
$('.btn-submit').on('click', function(event){
	event.preventDefault();
	
	if ( $('#createForm').valid() ) {
		
		let formData = $('#createForm').serialize();
		console.log(`form data: ` + formData)
		
		$.ajax({
			url: '/ajax-specific-product-update',
			type: 'GET',
			data: formData,
			success: function(response){
				if ( response != null && response.startsWith('/') ) {
					//window.location.href = response;
					window.location.assign(response);
				}
				else if ( response != null ) {
					$('.error-message').text(response);
					$('.error-message').addClass('on');	
				}
				else {
					console.log('that bai');
				}
			}
		})
	}
})