(function($){
  $(function(){


  	$('#botao').click(function(){
  		var url = $(this).data('url');
  		window.location.replace(url);
  	});

  	$(document).mousemove(function(event){
  		var top=parseInt($('#botao').offset().top),left=parseInt($('#botao').offset().left),width=parseInt($('#botao').width()),height=parseInt($('#botao').height()),x=parseInt(event.clientX),y=parseInt(event.clientY);
  		if((y>top&&y<(top+height))&&(x>left&&x<(left+width))){
  			console.log(true);
  		}else{
  			console.log(false);
  		}
  	});

    
  }); // end of document ready
})(jQuery); // end of jQuery name space
