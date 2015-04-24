$(document).ready(function () {
    $(document).on('submit', '#news-form', function(event) {
        //event.preventDefault();
        $.pjax({
            type: 'POST',
            //url: "/news",
            container: '#pjax-containerNews',
            data: this,
            dataType: 'application/json'
        })
    })
});