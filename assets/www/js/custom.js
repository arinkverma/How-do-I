$('.returnTopAction').live('click', function() {
   $('html, body').animate({scrollTop: '0'}, 700);
});




$('.link').live('tap', function(event) {
    url = $(this).attr("rel");   
    loadURL(url);
});



function loadURL(url){
    navigator.app.loadUrl(url, { openExternal:true });
    return false;
} 

