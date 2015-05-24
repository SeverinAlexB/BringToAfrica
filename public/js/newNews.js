$(document).ready(function () {
      $(document).on('pjax:error', function(event, xhr, textStatus, errorThrown, options){
        if (xhr.status == 400) {
          options.success(xhr.responseText, status, xhr);
           return false;
        }
       });

    $(document).on('submit', '#news-form', function(event) {
        e.preventDefault();
        $.pjax.submit(event, '#pjax-containerNews', {push: false});
    })
});