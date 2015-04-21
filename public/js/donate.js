$(document).ready(function () {
    $('.choose-pricing button').on('click', function () {
        var valueHolder = $(this).closest('input [type="hidden"]')
        valueHolder.val($(this).text());
    });
});