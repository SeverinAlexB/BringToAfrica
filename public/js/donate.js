$(function () {
    $('.choose-pricing button').on('click', function updateValue() {
        var target = $(this).attr('data-target');
        $(target).attr('value', $(this).text());
    });

    $('.choose-pricing input.form-control').change(function updateValue() {
        var target = $(this).attr('data-target');
        $(target).attr('value', $(this).val());
    });

    $(document).on('submit', '#donate-form', function submitDonationForm(event) {
        $.pjax.submit(event, '#pjax-container', {push: false});
    });

    function changeActiveButtonForDonationAmount(activeButton) {
        var valueKeeper = findValueKeeper(activeButton);
        var btnGroup = activeButton.parent();

        $('#' + btnGroup.attr('id') + ' .btn').removeClass('active');
        $('#' + btnGroup.attr('id') + ' .inpt-first').removeClass('active');
        activeButton.addClass('active');
    }

    function activateDonateButton() {
        $('#donate-submit').removeAttr('disabled');
    }

    function deactivateDonateButton() {
        $('#donate-submit').prop('disabled', true);
    }

    function resetValueIfCustomInputEmpty(activeButton) {
        var valueKeeper = findValueKeeper(activeButton);

        if(activeButton.hasClass('inpt-first')) {
            if(activeButton.val() === '') {
                valueKeeper.val('0');
            }
        }
    }

    function findValueKeeper(activeButton) {
        var btnGroup = activeButton.parent();
        return $('#' + btnGroup.attr('id') + ' .valueKeeper')
    }

    function resetDoubleClickedButton(activeButton) {
        var valueKeeper = findValueKeeper(activeButton);

        if (activeButton.text() === valueKeeper.val()) {
            activeButton.removeClass('active');
            valueKeeper.val('0');
        }
    }

    function onButtonChange() {
        var activeButton = $(this);

        changeActiveButtonForDonationAmount(activeButton);
        resetDoubleClickedButton(activeButton);
        resetValueIfCustomInputEmpty(activeButton);

        var buttonValue = parseInt(activeButton.text()) || parseInt(activeButton.val())
        if (buttonValue > 0) {
            activateDonateButton();
        } else {
            deactivateDonateButton();
        }
    }

    $(".donationbtn-group .btn").click(onButtonChange);
    $(".donationbtn-group .inpt-first").change(onButtonChange);
});