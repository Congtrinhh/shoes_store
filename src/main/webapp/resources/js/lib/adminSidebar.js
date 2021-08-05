/**
 * js của sidebar + header trong mọi page của admin  
 */


toggleArrow()
function toggleArrow(){
    $('[data-bs-toggle="collapse"]').on('click', function(){
        $(this).find('.child-menu__trigger').toggleClass('on');
    })
}

showHideSidebar();
function showHideSidebar() {
    $('#nav__toggle').on('click', function() {
        $('.nav').toggleClass('hide-side-bar');
        $('.nav').siblings('main').toggleClass('span-main-content'); // nhớ đặt phần bao bọc thân trang là thẻ main 
    })
}

let sidebarWidth = $('.nav').css('width');
sidebarWidth = Number(sidebarWidth.replace(/[a-zA-Z]/g, ''));    // width of sidebar in Number datatype (lấy xong thấy thừa :v)
