$(document).ready(function () {
    $('.choose-pricing button').on('click', function () {
        var target = $(this).attr('data-target');
        $(target).val($(this).text());
    });
});