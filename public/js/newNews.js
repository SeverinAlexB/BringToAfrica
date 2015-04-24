$(document).ready(function () {
    $(document).on('submit', '#news-form', function(event) {
        $.pjax.submit(event, '#pjax-containerNews');
    })
});