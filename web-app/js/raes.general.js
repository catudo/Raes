var webroot = '/Raes'
$(document).ready(function(){
clickTabs()
 generatePdf()

});

function clickTabs(){
	$(".nav").delegate(".linkPath", "click", function(e) {
		e.preventDefault()
	var redirectLink = $(this).attr("link");
		location.href = redirectLink;
	
	});
	
}

function clear(form) {
	$(form).each(function() {
		this.reset();
	});
}


function generatePdf(){
	
	$("#raeDiv").delegate(".printRae", "click", function(e) {
	
		$(this).parent().submit()
		
	})
	
}
