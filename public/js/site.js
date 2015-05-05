$(window).ready(function() {

isMobile = navigator.userAgent.match(/(iPhone|iPod|Android|BlackBerry|iPad|IEMobile|Opera Mini)/);

//Donate form button
$('.btn-group *').click(function(){
	$('.btn-group button.active').removeClass('active');
	$(this).addClass('active')
});

$('.dropdown-menu a').click(function(){
	var donation_type = $(this).text();
	$('#dropdownMenu1 small').text(donation_type);
});

$('.progressbar').find('.progress').each(function() {
	var val = parseInt($(this).find('.progress-bar').attr('aria-valuenow'));
	$(this).find('.progress-bar').width(val + "%")
});
});
