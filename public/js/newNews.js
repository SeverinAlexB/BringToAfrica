$(document).ready(function () {
      $(document).on('pjax:error', function(event, xhr, textStatus, errorThrown, options){
        if (xhr.status == 400) {
          options.success(xhr.responseText, status, xhr);
           return false;
        }
       });

    $(document).on('submit', '#news-form', function(event) {
        $.pjax.submit(event, '#pjax-containerNews', {push: false});

        /*//event.preventDefault();
        $.pjax({
            type: 'POST',
            //url: "/news",
            container: '#pjax-containerNews',
            data: this,
            dataType: 'application/json'
        })*/
    })
});