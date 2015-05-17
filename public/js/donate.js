$(document).ready(function () {
    $('.choose-pricing button').on('click', function () {
        var target = $(this).attr('data-target');
        $(target).attr('value', $(this).text());

    });
    $('.choose-pricing input.form-control').change(function() {
        var target = $(this).attr('data-target');
        $(target).attr('value', $(this).val());
    });
    $(document).on('submit', '#donate-form', function(event) {
        $.pjax.submit(event, '#pjax-container');
    })
});

$(".donationbtn-group .btn, .donationbtn-group .inpt-first").click(
function changeActiveButtonForDonationAmount(){
    var btngroup = $(this).parent();
    var activeButton = $(this);
    var valueKeeper = $('#' + btngroup.attr('id') + ' .valueKeeper')

    $('#' + btngroup.attr('id') + ' .btn').removeClass('active');
    $('#' + btngroup.attr('id') + ' .inpt-first').removeClass('active');
    activeButton.addClass('active');

    function activateDonateButton() {
        $('#donate-submit').removeAttr('disabled');
    }

    function deactivateDonateButton() {
        $('#donate-submit').prop('disabled', true);
    }

    if (parseInt($(this).text()) > 0) {
        activateDonateButton();
    } else {
        deactivateDonateButton();
    }

    if($(this).hasClass('inpt-first')) {
        if($(this).val() === ''){
            // Custom Field is empty
            valueKeeper.val('0');
        }
    }
});