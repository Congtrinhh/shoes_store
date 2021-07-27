$('#adminForm').validate({
          messages: {
               name: "Vui lòng nhập tên tài khoản",
               password: "Vui lòng nhập mật khẩu",
           },
           errorPlacement: function(errorElement, invalidElement) {
               errorElement.insertAfter( invalidElement.parent() );
           }
        });

        jQuery.validator.addMethod("isNumber", function(value, element) {
            return (value.trim().search(/\D/) !== -1) ? false : true;
        }, 'Trường này phải là số')