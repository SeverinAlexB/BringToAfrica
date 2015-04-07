$(function() {
    var rows = document.getElementById("main-table").getElementsByTagName("tbody")[0].getElementsByTagName("tr").length.toString();
    var amount = "amounts[" + rows + "]";
    var donation = "donations[" + rows + "]";
    $('#addGood').click(function(){
        $('#main-table tbody').append('<tr><td class="product-image"><input class="form-control" id="name" type="number" name="' + amount + '" value=""></td><td class="product-title"><input class="form-control" id="name" type="text" name="' + donation + '" value=""></td></tr>');
    });
    $('#deleteGood').click(function(){
        $("#main-table tbody tr:last").detach();
    });
});
