// ------------ validate form ---------------
$('#adminForm').validate({
	rules: {
		password: {
			minlength: 6,
			isValidPasswordPattern: true,
		},
		'confirmed-password': {
             equalTo: '[name="password"]',
        },
	},
	messages: {
		password: {
			required: 'Vui lòng nhập mật khẩu',
			minlength: 'Độ dài tối thiếu 6 ký tự'
		},
		'confirmed-password': {
			required: 'Vui lòng nhập lại mật khẩu',
             equalTo: 'Mật khẩu nhập lại không khớp',
        },
	},
	errorPlacement: function(errorElem, invalidElem){
		errorElem.insertAfter(invalidElem.parent());
	}
})

jQuery.validator.addMethod("isValidPasswordPattern", function(value){
	let regex = /^(?=.*[a-zA-Z])(?=.*[0-9])/g;
    return regex.test(value);
}, "Định dạng mật khẩu chưa đúng")