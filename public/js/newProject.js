$(function() {
    $('#addGood').click(function(){
        $('#main-table tbody').append('<tr><td class="product-image"><input class="form-control" id="name" type="number" name="amountField" value=""></td><td class="product-title"><input class="form-control" id="name" type="text" name="donationField" value=""></td></tr>');
    });
    $('#deleteGood').click(function(){
        $("#main-table tbody tr:last").detach();
    });
});
