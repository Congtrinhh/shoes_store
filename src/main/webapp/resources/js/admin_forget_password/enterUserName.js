// --------------- validate form ------------------
$('#adminForm').validate({
	rules: {
		name: {
			isValidUserNamePattern: true,
		}
	},
	messages: {
		name: {
			required: "Vui lòng nhập tên tài khoản",
		}
	},
	errorPlacement: function(errorElem, invalidElem){
		errorElem.insertAfter(invalidElem.parent());
	}
})

jQuery.validator.addMethod("isValidUserNamePattern", function(value){
	const regex = /^\d+$|\W/gm;
	return !regex.test( value.trim() );
	
}, "Vui lòng nhập đúng định dạng tên. eg: congtrinhh, cong123");

// ------------------ submit tên tk ajax ---------------------
$('.btn-continue').on('click', function(e){
	e.preventDefault();
	if ( $('#adminForm').valid() ) {
		$.ajax({
			url: "/ajax-admin-forget-password",
			type: 'GET',
			data: $('#adminForm').serialize(),
			success: function(response){
				if ( (response != null) && (typeof response == 'string') && (response.length > 0) ) {
					if ( response.startsWith('/') ) {
						window.location.assign(response); // forward qua trang set mk
					}
					else {
						$('.error-message').text(response);
						$('.error-message').addClass('on');
					}
				}
			}
		})
	}
})

 $(window).on('load', function () {
	$('#app').on('click', function(){
		$('.error-message').removeClass('on');
	})
 });