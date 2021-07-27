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
        $.ajax({
            url: '/create-product',
            type: 'POST',
            data: formData,
            contentType: false,
            cache: false,
            processData: false,
            success: function(data){
                console.log('submit form jquery ajax thanh cong voi data: ' + data);
            }
        })
    }
})