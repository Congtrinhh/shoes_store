// nhớ rằng đây là ES6
const autoSlider = () => {
    let slideIndex = 0;
    const zIndexValue = 100;

    slide();

    function slide () {
        const slides = document.querySelectorAll('.slide');
        const dots = document.querySelectorAll('.slider__indicator .dot');
        const titles = document.querySelectorAll('.slide__info');
        let i;

        slideIndex+=1;
        if (slideIndex>slides.length) {
            slideIndex = 1;
        }
        
        for (i=0; i<slides.length; i++) {
            slides[i].style.zIndex = 0;
        }
        for (i=0; i<dots.length; i++) {
            dots[i].classList.remove('active');
        }
        for (i=0; i<titles.length; i++) {
            titles[i].classList.remove('rise-top');
        }

        slides[slideIndex-1].style.zIndex = zIndexValue;
        dots[slideIndex-1].classList.add('active');
        titles[slideIndex-1].classList.add('rise-top');
        setTimeout(slide, 5000);
    }
}
    
autoSlider();   

