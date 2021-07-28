function showHideSidebar() {
    $('#nav__toggle').on('click', function(e) {
        $('.nav').toggleClass('hide-side-bar');
        $('.content').toggleClass('span-main-content');
    })
}
showHideSidebar();


$('#createForm').validate({
    rules: {
        images: {
            required:true,
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



$('.btn-submit').on('click', function(event){
	event.preventDefault();
    if ( $('#createForm').valid() ) {
        let form = document.querySelector('#createForm');
        let formData = new FormData(form);
        let rs = $.ajax({
            url: '/create-product',
            type: 'POST',
            data: formData,
            contentType: false,
            cache: false,
            processData: false,
            success: function(data){
                if (data == null) { // tức là mọi thứ đều thành công, không lỗi lầm
					$('.error-message').removeClass('on');
				}
				else {
					$('.error-message').addClass('on');
					$('.error-message').html(data);
					$('#createForm').on('click', function(){
						$('.error-message').removeClass('on');
					})
				}
            }
        });
    }
})
