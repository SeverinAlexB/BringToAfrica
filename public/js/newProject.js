$(function() {
    $('#addGood').click(function(){
        var rows = $('#main-table >tbody >tr').length;
        var amount = "amounts[" + rows + "]";
        var donation = "donations[" + rows + "]";
        if(rows > 3) return;
        $('#main-table tbody').append('<tr><td class="product-image"><input class="form-control" id="name" type="number" name="' + amount + '" value="" required></td><td class="product-title"><input class="form-control" id="name" type="text" name="' + donation + '" value="" required></td></tr>');
    });
    $('#deleteGood').click(function(){
    	var rows = $('#main-table >tbody >tr').length;
    	if(rows < 2) return;
        $("#main-table tbody tr:last").detach();
    });

    $('#btnContinue1').click(function(){
  		$('.nav-tabs > .active').next('li').find('a').trigger('click');
	});

	$('#btnContinue2').click(function(){
  		$('.nav-tabs > .active').next('li').find('a').trigger('click');
	});
});
