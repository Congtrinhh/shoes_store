// -------------- highlight tab đang đứng --------------
$(window).on('load', function(){
	const tabIndicator = $('#user-navigation-bar').attr('tab-indicator');
	$('#user-navigation-bar > li > a').each(function(){
		if ( $(this).attr('href') == tabIndicator ) {
			$(this).addClass('active');
		}
	})
})

// ---------------- ẩn thanh user nav khi kích thước màn hình nhỏ xuống  ----------------
$(window).on('load', function() {
    if ( window.innerWidth <= 768 ) {
        $('.collapse').removeClass('show');
    }
})