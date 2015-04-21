$(function() {
    var display = false;
    if ($('#cbx').is(":checked")){
        display = true;
    }

    $(document).ready(function () {
        $("#showPasswordField").toggle(display);
        $("#cbx").change(function () {
            $("#showPasswordField").toggle(display);
            if(display){
                $("#cbx").val("true")
            }else{
                $("#cbx").val("false")
            }
            display = !display;
        }).change();
    });
});