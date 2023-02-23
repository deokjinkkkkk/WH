/*-----------------------------------------------------------------------------------

    Theme Name: Adventure - Tour and Travel Agency Template
    Description: Tour and Travel Agency Template
    Author: Chitrakoot Web
    Version: 1.0

    /* ----------------------------------

    JS Active Code Index
            
        01. Preloader
        02. Header class - on scroll
        03. Scroll To Top
        04. Parallax
        05. Resize function
        06. FullScreenHeight function
        07. ScreenFixedHeight function
        08. FullScreenHeight and screenHeight with resize function
        09. Copy to clipboard
        10. Sliders
        11. CountUp
        12. Countdown
        13. Isotop and Popup
                
    ---------------------------------- */    
    

(function($) {

    "use strict";

    var $window = $(window);


        /*------------------------------------
            01. Preloader
        --------------------------------------*/

        $('#preloader').fadeOut('normall', function() {
            $(this).remove();
        });

        /*------------------------------------
            02. Header class - on scroll
        --------------------------------------*/

        $window.on('scroll', function() {
            var scroll = $window.scrollTop();
            if (scroll <= 50) {
                $("header").removeClass("scrollHeader").addClass("fixedHeader");
            } 
            else {
                $("header").removeClass("fixedHeader").addClass("scrollHeader");
            }
        });

        /*------------------------------------
            03. Scroll To Top
        --------------------------------------*/

        $window.on('scroll', function() {
            if ($(this).scrollTop() > 500) {
                $(".scroll-to-top").fadeIn(400);

            } else {
                $(".scroll-to-top").fadeOut(400);
            }
        });

        $(".scroll-to-top").on('click', function(event) {
            event.preventDefault();
            $("html, body").animate({
                scrollTop: 0
            }, 600);
        });

        /*------------------------------------
            04. Parallax
        --------------------------------------*/

        // sections background image from data background
        var pageSection = $(".parallax,.bg-img");
        pageSection.each(function(indx) {

            if ($(this).attr("data-background")) {
                $(this).css("background-image", "url(" + $(this).data("background") + ")");
            }
        });
        
        /*------------------------------------
            05. Resize function
        --------------------------------------*/

        $window.resize(function(event) {
            setTimeout(function() {
                SetResizeContent();
            }, 500);
            event.preventDefault();
        });

        /*------------------------------------
            06. FullScreenHeight function
        --------------------------------------*/

        function fullScreenHeight() {
            var element = $(".full-screen");
            var $minheight = $window.height();
            element.css('min-height', $minheight);
        }

        /*------------------------------------
            07. ScreenFixedHeight function
        --------------------------------------*/

        function ScreenFixedHeight() {
            var $headerHeight = $("header").height();
            var element = $(".screen-height");
            var $screenheight = $window.height() - 200;
            element.css('height', $screenheight);
        }

        /*------------------------------------
            08. FullScreenHeight and screenHeight with resize function
        --------------------------------------*/        

        function SetResizeContent() {
            fullScreenHeight();
            ScreenFixedHeight();
        }

        SetResizeContent();

    // === when document ready === //
    $(document).on("ready", function() {

        /*------------------------------------
            09. Copy to clipboard
        --------------------------------------*/

        if ($(".copy-clipboard").length !== 0) {
            new ClipboardJS('.copy-clipboard');
            $('.copy-clipboard').on('click', function() {
                var $this = $(this);
                var originalText = $this.text();
                $this.text('Copied');
                setTimeout(function() {
                    $this.text('Copy')
                    }, 2000);
            });
        };

        $('.source-modal').magnificPopup({
                type: 'inline',
                mainClass: 'mfp-fade',
                removalDelay: 160
        });   

        $(".slow-redirect a[href^='#']").click(function(e) {
                e.preventDefault();

                var position = $($(this).attr("href")).offset().top - 100;

                $("body, html").animate({
                    scrollTop: position
                }, 1000);
        }); 
        

        /*------------------------------------
            10. Sliders
        --------------------------------------*/

        // Testmonials carousel1
        $('#testmonials-carousel').owlCarousel({
            loop: true,
            responsiveClass: true,
            autoplay: true,
            smartSpeed: 800,            
            nav: false,
            dots: false,
            margin: 0,
            responsive: {
                0: {
                    items: 1
                },
                768: {
                    items: 1
                },
                992: {
                    items: 1
                }
            }
        });

        // Departments carousel
        $('#destination').owlCarousel({
            loop: true,
            nav: true,
            navText: ['<span class="ti-angle-left"></span>', '<span class="ti-angle-right"></span>'],
            dots: false,
            autoplay: true,
            smartSpeed:500,
            responsiveClass: true,
            autoplayHoverPause: false,
            responsive: {
                0: {
                    items: 1,
                    margin: 0
                },
                576: {
                    items: 2,
                    nav: false,
                    margin: 14
                },                
                992: {
                    items: 3,
                    margin: 15,
                    },
                1200: {
                    items: 3,
                    margin: 20,
                }
            }
        });

        // Default owlCarousel
        $('.tour').owlCarousel({
            items: 2,
            loop:true,
            dots: false,
            margin: 20,
            autoplay:true,
            smartSpeed:500
        }); 

        // Default owlCarousel
        $('.blog-slider').owlCarousel({
            items: 1,
            loop:true,
            dots: true,
            margin: 20,
            autoplay:true,
            smartSpeed:500
        }); 

        // Default owlCarousel
        $('.owl-carousel').owlCarousel({
            items: 1,
            loop:true,
            dots: true,
            margin: 0,
            autoplay:true,
            smartSpeed:500
        });  

        /*------------------------------------
            11. CountUp
        --------------------------------------*/

        $('.countup').counterUp({
            delay: 25,
            time: 2000
        });


        /*------------------------------------
            12. Countdown
        --------------------------------------*/

        // CountDown for coming soon page
       /* $(".countdown").countdown({
            date: "01 Jan 2021 00:01:00", //set your date and time. EX: 15 May 2014 12:00:00
            format: "on"
        });*/
      
    });

    // === when window loading === //
    $window.on("load", function() {

        /*------------------------------------
            13. Isotop and Popup
        --------------------------------------*/

        // isotope with magnificPopup
        $('.gallery').magnificPopup({
            delegate: '.popimg',
            type: 'image',
            gallery: {
                enabled: true
            }
        });

        var $gallery = $('.gallery').isotope({
            // options
        });

        // filter items on button click
        $('.filtering').on('click', 'span', function() {
            var filterValue = $(this).attr('data-filter');
            $gallery.isotope({
                filter: filterValue
            });
        });

        $('.filtering').on('click', 'span', function() {
            $(this).addClass('active').siblings().removeClass('active');
        });

        // stellar
        $window.stellar();

        // price range
        if ($(".price-range").length !== 0) {
            $(".price-range").ionRangeSlider({
                type: "double",
                grid: true,
                min: 0,
                max: 1000,
                from: 200,
                to: 800,
                prefix: "$"
            });
        }


    });

})(jQuery);