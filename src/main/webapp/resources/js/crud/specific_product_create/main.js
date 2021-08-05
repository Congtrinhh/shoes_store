
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
	const regex = /\D/gm;
	return !regex.test(value.trim());
}, 'Định dạng số chưa đúng');


/* --------------- ajax submit form --------------- */
$('.btn-submit').on('click', function(event){
	event.preventDefault();
	
	if ( $('#createForm').valid() ) {
		const inputs = document.querySelectorAll('#createForm [name]');
		let formData = '';
		inputs.forEach( input => {
			formData += `${input.name}=${input.value}&`;	
		})
		console.log(formData)		
		
		$.ajax({
			url: '/specific-product-create',
			type:'POST',
			data: formData,
			success: function(response){
				if (typeof response=='object') { // tức là thành công
					// clear inputs
					inputs.forEach( input =>{
						input.value = '';
					})
					
					// pop up insert thành công. tuy nhiên tạm thời set thông báo chung với thông báo lỗi, pop up để sau.
					$('.error-message').text('Đã thêm thành công');
					$('.error-message').addClass('on');
					
				}else if (typeof response=='string') {
					// hien thi thong bao loi
					$('.error-message').text(response);
					$('.error-message').addClass('on');
					
				}
			}
		})	
		
	}
})
$('#createForm').on('click', function(){ // remvoe tb lỗi khi có tác động vào form
	$('.error-message').removeClass('on');					
})