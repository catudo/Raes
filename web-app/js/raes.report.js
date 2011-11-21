$(document).ready(function() {

	
	displayReportTabs();
	
	var counter = 0
	$('#mainDiv').children().each(function(){
		if(counter != 0){
			$(this).css('display','none')
			
		}
		counter++;
		
	})
	
	
});


function displayReportTabs(){

$(".content").delegate('.tab', "click", function() {
	var div = $(this).attr('id').replace('-evt','').replace(' ','')
	
	var currentDiv =$('#'+div+"Div")
	if(!(currentDiv.is(':visible'))){
		currentDiv.siblings().hide()
		currentDiv.show();
		
	}
	
	
})

}