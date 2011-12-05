$(document).ready(function() {

	
	displayReportTabs();
	
	var counter = 0
	$('#mainDiv').children().each(function(){
		if(counter != 0){
			$(this).css('display','none')
			
		}
		counter++;
		
	})
	
	$.ajax({
		type : "POST",
		url : webroot + "/report/generateToolsNamesChart",
	
		success : function(response) {
		$('#labelsTools').html(response)
		}	
				
		
		});
	
	
	$.ajax({
		type : "POST",
		url : webroot + "/report/generateMethodologyLabels",
	
		success : function(response) {
		$('#methodologyLabels').html(response)
		}	
				
		
		});
	
	$.ajax({
		type : "POST",
		url : webroot + "/report/universitiesLabels",
	
		success : function(response) {
		$('#universitiesLabels').html(response)
		}	
				
		
		});
	
	
	
	$.ajax({
		type : "POST",
		url : webroot + "/report/categoriesList",
	
		success : function(response) {
		$('#categoriesList').html(response)
		}	
				
		
		});
	
	
	
	
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