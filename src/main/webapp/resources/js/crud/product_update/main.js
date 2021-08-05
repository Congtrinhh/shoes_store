
// hiện phần chỉnh sửa khi bấm nút edit
$('.fieldsets .btn-edit').on('click', function(e) {
	e.preventDefault();
    $(this).closest('fieldset').find('select').css('display', 'block');
    
    $(this).closest('fieldset').find('.image-upload-container').css('display', 'block');
    $(this).closest('fieldset').find('.btn-delete-image').css('display', 'block');
})

// validate form
$('#createForm').validate({
    rules: {
        images: {
            extension: 'jpg|png|jpeg',
        },
    },
    messages: {
        images: {
            required: "Vui lòng thêm ảnh",
            extension: 'Định dạng file không hợp lệ',
        },
    },
    errorPlacement: function(errorElem, invalidElem) {
        errorElem.insertAfter(invalidElem.parent());
    },
});


// --------- preview anh ------------
$('[name="images"]').on('change', function(event){
	
	let fileList = event.target.files;
	
	document.querySelector('.image-preview-container').innerHTML = '';
	
	for (let file of fileList){
		let url = URL.createObjectURL(file);
		
		document.querySelector('.image-preview-container').innerHTML +=
			`<div class="">
                <div class="image-parent">
                    <img src="${url}" class="image-on-preview">
                </div>
            </div>`;
		$('.image-on-preview').on('load', function(){
			URL.revokeObjectURL($(this).attr('src'));
		})
		
	}
})

// ------------- ajax ----------------
$('.btn-submit').on('click', function(event) {
	event.preventDefault();
	
	if ( $('#createForm').valid() ) {

		let form = document.querySelector('#createForm');
		let formData = new FormData(form);
		console.log(formData)
		
		$.ajax({
			url: '/ajax-product-update',
			type: 'POST',
			data: formData,
			processData: false,
			contentType: false,
			success: function(response){
				if (typeof response == 'string') {
					if (response.startsWith("/")) { // thành công
					console.log('sucess', response);
						location.reload();
					} 
					else {
						$('.error-message').text(response)
						$('.error-message').addClass('on');
						
						$('#createForm').on('click', function(){
							$('.error-message').removeClass('on');
						})
					}
				}
			}	
		})
		
	}
})

// lưu thông tin cơ bản vào localStorage
$(window).on('load', function(){
	let data = {
		name: $('[name="name"]').attr('value'),
		slug: $('[name="slug"]').attr('value'),
		price: $('[name="price"]').attr('value'),
		description: $('[name="description"]').text(),
	}
	localStorage.setItem("tmpProductForAdmin", JSON.stringify(data));
	
	restoreBasicInfo(); // restore thông tin sp ban đầu
})

// restore thông tin sp ban đầu
function restoreBasicInfo(){
	
	$('.btn-restore').on('click',function(event){
		event.preventDefault();
		
		let infoString = localStorage.getItem("tmpProductForAdmin");
		let product;
		if (infoString!=null) {
			product = JSON.parse(infoString);
		}
		
		switch( $(this).siblings('[name]').attr('name') ) {
			case 'name': 
				$(this).siblings('[name]').val(product.name)
				break;
			case 'slug':
				$(this).siblings('[name]').val(product.slug)
				break;
			case 'price':
				$(this).siblings('[name]').val(product.price)
				break;
			case 'description':
				$(this).siblings('[name]').val(product.description)
				break;
			default:
				console.log('khong tim thay de restore');
		}
		
	})
}

// khi có sự kiện input được sửa, hiển thị nút restore
$('.form-text > input').on('change', showRestoreButton);
$('fieldset > textarea').on('change', showRestoreButton);
function showRestoreButton() {
	$(this).siblings('.btn-restore').addClass('on');
}


// khi nhấn xóa hình ảnh, remove hình đó khỏi dom
let deleteImageIndex=0;
$('.btn-delete-image').on('click', function(event){
	event.preventDefault();
	$(this).closest('.image-parent').parent().remove();
	let _this = $(this);
	document.querySelector('.delete-queue').innerHTML += 
			`<input hidden type="text" name="deleted-image-id-${deleteImageIndex}" value="${_this.attr('image-id')}">`;
	deleteImageIndex++;
})

// disable thuộc tính tự submit của button
