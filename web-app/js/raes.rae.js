$(document).ready(function(){

	saveProperties()
	displayDivs()
	addAuthor()

});



function saveProperties(){
	$(".add").delegate(".evt-properties", "click", function(e) {
		
		e.preventDefault()
		
		var form = $(this).parent().parent()
		
		var params = $(form).serialize();
	
		var formId = $(form).attr("id") 
		
		
		var controller
		
		switch (formId) {
		case "authorForm":
			controller = "saveAuthor"
			
			break;
			
		case "universityForm":
			controller = "saveUniversity"
			break;
			
		case "categoryForm":
			controller = "saveCategory"
			break;
		
		}
		
		$.ajax({
			type : "POST",
			url : webroot + "/rae/"+controller,
			data : params,
			success : function(response) {
				
				clear("#"+formId)
				window.location.reload(true);


			}
		});
		
	})
	
}


function displayDivs(){
	$(".nav").delegate(".tab", "click", function(e) {
		e.preventDefault();
		
		var id =$(this).attr("id")
		
		
		if(id=="saveTab"){
			if(!$("#addRaeDiv").is(":visible")){
				$("#listRaeDiv").slideUp()
				$("#addRaeDiv").slideDown()
				
			}
		}else{
			if(!$("#listRaeDiv").is(":visible")){
				$("#listRaeDiv").slideDown()
				$("#addRaeDiv").slideUp()
				
			}
			
		}
		
		
	})
}



function addAuthor(){
	$(".add").delegate(".evt-addAuthor", "click", function(e) {
		e.preventDefault()
		$(this).before('<p><input type="text" value="" id="author" name="author" class="text"></p>')
	
	
	})
}