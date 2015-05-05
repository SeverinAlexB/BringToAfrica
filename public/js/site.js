$(window).ready(function() {
	isMobile = navigator.userAgent.match(/(iPhone|iPod|Android|BlackBerry|iPad|IEMobile|Opera Mini)/);
	$('img.svg').each(function(){
            var $img = jQuery(this);
            var imgID = $img.attr('id');
            var imgClass = $img.attr('class');
            var imgURL = $img.attr('src');

            jQuery.get(imgURL, function(data) {
                // Get the SVG tag, ignore the rest
                var $svg = jQuery(data).find('svg');

                // Add replaced image's ID to the new SVG
                if(typeof imgID !== 'undefined') {
                    $svg = $svg.attr('id', imgID);
                }
                // Add replaced image's classes to the new SVG
                if(typeof imgClass !== 'undefined') {
                    $svg = $svg.attr('class', imgClass+' replaced-svg');
                }

                // Remove any invalid XML tags as per http://validator.w3.org
                $svg = $svg.removeAttr('xmlns:a');

                // Replace image with new SVG
                $img.replaceWith($svg);

            }, 'xml');

        });
	// Flexsliderfunction function
		$(window).load(function() {
			if($('.flexslider').length){
			
		$('.our-causes .flexslider').flexslider({
			animation : "slide",
			animationLoop : false,
			itemWidth :360,
			itemMargin : 30,
			start : function(slider) {
				$('body').removeClass('loading');
			}
		});
		
			$('.testimonial .flexslider, .donation-holder .flexslider,.flex-slide.flexslider').flexslider({
				
			animation : "slide",
			animationLoop : false
			
		});
		}
		
	});
	
// Accordion function

$('#accordion .panel-title').click(function () {
	 if($(this).find('.fa-plus-circle').hasClass('fa-minus-circle')){
			$(this).find('.fa-minus-circle').removeClass('fa-minus-circle');
		}
		else{

	$('#accordion .fa-minus-circle').removeClass('fa-minus-circle');
  $(this).find('.fa-plus-circle').addClass('fa-minus-circle');

  }	

})
$('#accordion1 .panel-title').click(function () {
	 if($(this).find('.fa-plus-circle').hasClass('fa-minus-circle')){
			$(this).find('.fa-minus-circle').removeClass('fa-minus-circle');
		}
		else{

	$('#accordion1 .fa-minus-circle').removeClass('fa-minus-circle');
  $(this).find('.fa-plus-circle').addClass('fa-minus-circle');

  }	

})
if($('#accordion .panel-heading').parents('.panel').find('.panel-collapse').hasClass('in')){
		$('.in').parents('.panel').find('.collape-plus').addClass('fa-minus');
		
		
	}
$('#accordion .panel-heading').click(function() {
	$('#accordion .fa-minus').removeClass('fa-minus');
	if($(this).parents('.panel').find('.panel-collapse').hasClass('in')){
		
		
		
	}
	else{
		$(this).find('.collape-plus').addClass('fa-minus');
		
	}
})

$('#accordion-right .panel-heading').click(function() {
	$('#accordion-right .fa-minus').removeClass('fa-minus');
	if($(this).parents('.panel').find('.panel-collapse').hasClass('in')){
		
		
		
	}
	else{
		$(this).find('.collape-plus').addClass('fa-minus');
		
	}
})


		
//Header Searh form
if($(window).width()>=768){

	$('.search-form button,.icon-search').click(function(){
		
		if($('.header-second .form-group').css('width') == '0px'){
		
			$('.header-second .form-group').animate({
				width:'180px'
			});
			$('.header-second .form-group').addClass('bottom-line');
			$('.header-second nav>ul').fadeOut();
			
		}
		else{
			
			$('.header-second .form-group').animate({
				width:'0px'
			});
			
			$('.bottom-line').removeClass('bottom-line');
			
			$('.header-second nav>ul').fadeIn();
		}


	})
}
//Donate form button
$('.btn-group *').click(function(){
		$('.btn-group button.active').removeClass('active');
		$(this).addClass('active')

	
})
$('.dropdown-menu a').click(function(){
		var donation_type = $(this).text();
		$('#dropdownMenu1 small').text(donation_type)

	
})

//EqualHeight Function	
	var highestBox = 0;
	$('.equal-block').each(function() {
		if ($(this).height() > highestBox) {
			highestBox = $(this).height();
		}
	});
	$('.equal-block').height(highestBox);	
	//=====	
var highestBox_1 = 0;
	$('.row .equal-box').each(function() {
		if ($(this).height() > highestBox_1) {
			highestBox_1 = $(this).height();
		}
	});
	$('.equal-box ').height(highestBox_1);
	
	
	
// Price Range Slider fucntion 
if($( "#slider-range" ).length){
$( "#slider-range" ).slider({
			range: true,
			min: 0,
			max: 500,
			values: [ 75, 300 ],
			slide: function( event, ui ) {
				$( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
			}
		});
		$( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
			" - $" + $( "#slider-range" ).slider( "values", 1 ) ); 	
}
//video-placeholder function
       $('.embed-responsive-16by9 img').click(function(){
        video = '<iframe src="'+ $(this).attr('data-video') +'"></iframe>';
        
        $(this).after(video);
        
     });
       $('.play-btn').click(function(){
        video1 = '<iframe src="'+ $('.video-section img').attr('data-video') +'"></iframe>';
        
        $('.video-section img').after(video1);
     return false; 	
     
    });

if(!isMobile){
			var animSection = function() {
				$('.anim-section').each(function() {
					if ($(window).scrollTop() > ($(this).offset().top - $(window).height() / 1.15)) {
						$(this).addClass('animate')
					}
				})
			}
			
				if ($('.anim-section').length) {
				animSection()
				$(window).scroll(function() {
					animSection()
				})
			}
			
			$(window).load(function(){
				if ($('.parallax').length) {
      $('.parallax').each(function() {
       parallax($(this), 0.1);
      })
     }
		})
			$(window).scroll(function(){
				if ($('.parallax').length) {
      $('.parallax').each(function() {
       parallax($(this), 0.1);
      })
     }
			})
			
			//Progressbar
	if ($('.progressbar').length) {
				$(window).scroll(function() {
					if ($(window).scrollTop() > ($('.progressbar').offset().top - $(window).height() /1.4)) {
						$('.progressbar').find('.progress').each(function() {
							var val = parseInt($(this).find('.progress-bar').attr('aria-valuenow'));
							$(this).find('.progress-bar').width(val + "%")
						})
					}
				})
			}

				}
			else{
					$('.progressbar').find('.progress').each(function() {
							var val = parseInt($(this).find('.progress-bar').attr('aria-valuenow'));
							$(this).find('.progress-bar').width(val + "%")
						})
			}	

		
  var parallax = function(id, val) {
    if ($(window).scrollTop() > id.offset().top - $(window).height() && $(window).scrollTop() < id.offset().top + id.outerHeight()) {
     var px = parseInt($(window).scrollTop() - (id.offset().top - $(window).height()))
     px *= -val;
     id.css({
      'background-position' : 'center ' + px + 'px'
     })
    }
   }

});	

//Sticky Header Yes or No Activate Function
$('#header').attr('data-sticky','no') //Choose here yes or no
  			fixedNav()
	$(window).scroll(function() {
				fixedNav()
			})

			var initScroll = $(window).scrollTop(), headerHeight = $('#header').height();
			
			 function fixedNav() {				
				 currentScroll = $(window).scrollTop()
				function inteligent() {
					if (currentScroll >= initScroll) {console.log(111)
						//console.log('up')
						$('#header').removeClass('down')
						$('#header').addClass('up')
						if (currentScroll == $(document).height() - $(window).height()) {console.log(1111)
							$('#header').removeClass('up')
							$('#header').addClass('down')
						}
						initScroll = currentScroll
					} else {console.log(111111)
						//console.log('down')
						$('#header').removeClass('up')
						$('#header').addClass('down')
						initScroll = currentScroll
					}
				}

				if ($('#header').attr('data-sticky') == "yes") {
					if (currentScroll > $('#header').height()) {
						$('#header').addClass('fixed')
						$('#wrapper').css("padding-top", headerHeight)
						inteligent()
					} else {
						
					}
				} else {
				if (currentScroll > $('#header').height()) {console.log(1)
					
						$('#wrapper').css("padding-top", "0")
					} else {console.log(11)
						$('#wrapper').css("padding-top", "0")
					}
				}
			}
