// -------------- highlight tab đang đứng --------------
$(window).on('load', function(){
	const tabIndicator = $('#user-navigation-bar').attr('tab-indicator');
	$('#user-navigation-bar > li > a').each(function(){
		if ( $(this).attr('href') == tabIndicator ) {
			$(this).addClass('active');
		}
	})
})

// ---------------- ajax post submit form (update user info) ----------------
$('.btn-submit').on('click', function(event){
	event.preventDefault();
	
	if ( $('#userForm').valid() ) {
		
		$.ajax({
			url: '/ajax-user-info',
			type: 'POST',
			data: $('#userForm').serialize(),
			//processData: false,
			//contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
			success: function(response){
				if ( response!=null && typeof response=='string' ) {
					if ( response.startsWith('/') ) {
						location.reload();
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

$('form').on('click', function(){
	$('.error-message').removeClass('on');
})

// ---------------- ajax get ward list khi chọn 1 district nào đó ----------------
$('#district').on('change', getWardList);

// ---------------- ajax get district list khi chọn 1 province nào đó ----------------
$('#province').on('change', getDistrictList);

function getWardList(event){
	let districtId = Number( $(this).val() );
	$.ajax({
		url: '/ajax-user-info',
		type: 'GET',
		data: 'district=' + districtId,
		success: function(response){
			if ( response!=null && Array.isArray(response) ) {
				parseWardList( response );
			}
		}
	})
}
function parseWardList(wardList){
	document.querySelector('#ward').innerHTML = '<option value="">Xã/phường</option>';
	document.querySelector('#ward').innerHTML +=
			wardList.map( ward => {
				return `<option value="${ward.id}">${ward.name}</option>`;
			}).join('');
}

function getDistrictList(event){
	let provinceId = Number( $(this).val() );
	
	$.ajax({
		url: '/ajax-user-info',
		type: 'GET',
		data: 'province=' + provinceId,
		success: function(response){
			if ( response!=null && Array.isArray(response) ) {
				parseDistrictList( response );
			}
		}
	})
}
function parseDistrictList(districtList){
	document.querySelector('#district').innerHTML = '<option value="">Quận/huyện</option>';
	document.querySelector('#district').innerHTML +=
			districtList.map( district => {
				return `<option value="${district.id}">${district.name}</option>`;
			}).join('');
	// clear all ward list		
	document.querySelector('#ward').innerHTML = '<option value="">Xã/phường</option>';
}

// ---------------- ẩn / hiện nút đổi mk ----------------
$('.btn-show').on('click', function(e) {
    $('.update-password-area').toggleClass('on');
    $(this).toggleClass('on');
    if ( $(this).hasClass('on') ) {
        $(this).text('Hủy')
		$('[name="current-password"]').val(null);
		$('[name="new-password"]').val(null);
		$('[name="confirmed-password"]').val(null);
    } else {
        $(this).text('Đổi mật khẩu')
    }
})
// ---------------- ẩn thanh user nav khi kích thước màn hình nhỏ xuống  ----------------
$(window).on('load', function() {
    if ( window.innerWidth <= 768 ) {
        $('.collapse').removeClass('show');
    }
})

// -------------- validate form --------------------
$('#userForm').validate({
    rules: {
        'user-name': {
            minlength:6,
            isValidUserName: true,
        },
        'full-name': {
            isValidName: true,
        }, 
        'phone-number': {
            minlength:10,
            isPhoneNumber: true,
        },
        'specific-address': {
            isValidAddressName: true,
        },
        'current-password': {

        },
        'new-password': {
            minlength: 6,
            isPasswordSecure: true,
        },
        'confirmed-password':{
            equalTo: '[name="new-password"]',
        }
    },
    messages: {
        'user-name': {
            required: 'Vui lòng nhập tên đăng nhập',
            minlength: 'Hãy nhập ít nhất 6 ký tự'
            // isValidUserName: true,
        },
        'full-name': {
            required: 'Vui lòng nhập họ và tên',
        }, 
        'phone-number': {
            required: 'Vui lòng nhập số điện thoại',
            minlength: 'Độ dài số điện thoại là 10'
        },
        email: {
            required: 'Vui lòng nhập email',
            email: 'Định dạng email không hợp lệ'
        },
        province:{
            required: 'Vui lòng chọn tỉnh/thành phố'
        },
        district:{
            required: 'Vui lòng chọn quận/huyện'
        },
        ward:{
            required: 'Vui lòng chọn xã/phường'
        },
        'specific-address': {
            required: 'Vui lòng nhập địa chỉ cụ thể',
        },
        'current-password': {
            required: 'Vui lòng nhập mật khẩu',
        },
        'new-password': {
            required: 'Vui lòng nhập mật khẩu mới',
            minlength: 'Ít nhất 6 ký tự',
            isPasswordSecure: 'Chưa đủ độ an toàn (cần có cả chữ và số)',
        },
        'confirmed-password':{
            required: 'Vui lòng xác nhận mật khẩu mới',
            equalTo: 'Mật khẩu xác nhận không khớp',
        }
    },
    errorPlacement: function(errorElem, invalidElem){
        if ( invalidElem.prop('tagName') == 'SELECT' ) {
            errorElem.insertAfter(invalidElem.closest('.select-wrapper'));
        }
        else {
            errorElem.insertAfter(invalidElem.parent());
        }
    }
})
jQuery.validator.addMethod( "isPasswordSecure", function(value) {
    let regex = /^(?=.*[a-zA-Z])(?=.*[0-9])/g;
    return regex.test(value);
}, 'Mật khẩu cần có ít nhất 1 chữ cái và 1 chữ số' )

jQuery.validator.addMethod("isValidUserName", function(value){
    const _1regex = /^[a-zA-Z]*$/g; // chỉ chứa chữ cái
    const _2regex = /^[a-zA-Z]+[a-zA-Z0-9]*$/g; // chỉ chứa chữ cái và số

    return _1regex.test(value) || _2regex.test(value);
}, "Định dạng không hợp lệ")

jQuery.validator.addMethod( "isPhoneNumber", function(value) {
    const regex = /^0[\d]{9}$/gm;
    return regex.test( value.trim() );
}, 'Định dạng không hợp lệ' )

jQuery.validator.addMethod( "isValidName", function(value) {
    const regex = /[^\p{L}\s]/gu;
    return !regex.test( value.trim() );
}, 'Định dạng không hợp lệ' )

jQuery.validator.addMethod( "isValidAddressName", function(value) {
    const regex1 = /[^\p{L}\s\d,.-]/gu; // match string contains characters that are not unicode or number or letter or space,...
    const regex2 = /^[\d]+$/gm; // match string contains only number
    return !regex1.test( value.trim() ) && !regex2.test( value.trim());
}, 'Định dạng không hợp lệ' )