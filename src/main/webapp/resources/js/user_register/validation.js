 $('#userForm').validate({
            rules: {
                'confirmed-password': {
                     equalTo: '[name="password"]',
                },
                name: {
                    minlength: 6,
                    isValidUserName: true,
                },
                password: {
                    minlength: 6,
                    isPasswordSecure: true,
                }
            },
            messages: {
                name: {
                    required: "Vui lòng nhập tên đăng nhập",
                    minlength: 'Độ dài tối thiểu 6 ký tự',
                },
                email: {
                    required: "Vui lòng nhập email",
                    email: "Vui lòng nhập đúng định dạng email"
                },
                password: {
                    required: "Vui lòng nhập mật khẩu",
                    minlength: 'Độ dài tối thiểu 6 ký tự',
                    // isPasswordSecure: 'Mật khẩu cần có ít nhất 1 chữ cái và 1 chữ số'
                },
                'confirmed-password': {
                    required: "Vui lòng xác nhận mật khẩu",
                    equalTo: "Mật khẩu xác nhận không đúng",
                },
                'policy-agree': "Vui lòng xác nhận",
            },
            errorPlacement: function(errorElement, invalidElement) {
                errorElement.insertAfter( invalidElement.parent() );
            },

});

jQuery.validator.addMethod( "isPasswordSecure", function(value) {
    let regex = /^(?=.*[a-zA-Z])(?=.*[0-9])/g;
    return regex.test(value);
}, 'Mật khẩu cần có ít nhất 1 chữ cái và 1 chữ số' )

jQuery.validator.addMethod("isValidUserName", function(value){
    const _1regex = /^[a-zA-Z]*$/g; // chỉ chứa chữ cái
    const _2regex = /^[a-zA-Z]+[a-zA-Z0-9]*$/g; // chỉ chứa chữ cái và số

    return _1regex.test(value) || _2regex.test(value);
}, "Định dạng không hợp lệ. Thử nhập định dạng: congtrinh hoặc congtrinh123")



$('.btn-submit').on('click', function(event){

    event.preventDefault();

    if ( $('#userForm').valid() ) {

        let xhr = new XMLHttpRequest();

        xhr.open('POST', '/user-register');

        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

        let params = ''; 
        document.querySelectorAll('.essential-data').forEach( (inputField, index) => {
            params += `${inputField.name}=${inputField.value}&`;
        } )

        params = params.substr(0, params.length-1);

        xhr.onload = function(event) {
            if (this.status == 200) {
                /*
                thành công -> không cần làm gì, vì servlet lo rồi
                thất bại -> hiển thị thông báo lỗi qua thẻ p đấy
                 */        
				console.log(this.responseText.length);
				
				if (this.responseText.length === 0) {
					// tức là thành công.
					// hiển thị nút trở về trang chủ
					$('.container').html(
						`<h2 class="text-success">Đăng ký tài khoản thành công</h2><br>
						<a href="/home" class="btn">Về trang chủ</a>`
						);
				}
				else {
					$('.error-message').html(this.responseText);
					$('.error-message').addClass('on');
					
					$('.container').on('click', function(){
						$('.error-message').removeClass('on');						
					})
				}
				
				
            }
        }

		xhr.send(params);
    }
	else {
		console.log('form invalid');
	}
})