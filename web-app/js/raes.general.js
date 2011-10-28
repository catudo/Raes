var webroot = '/Raes'
$(document).ready(function(){
clickTabs()
 

});

function clickTabs(){
	$("#menu").delegate(".linkPath", "click", function() {
	var redirectLink = $(this).attr("link");
		location.href = redirectLink;
	
	});
	
}

function clear(form) {
	$(form).each(function() {
		this.reset();
	});
}
