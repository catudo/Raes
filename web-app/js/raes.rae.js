$(document).ready(function(){

	saveProperties()
	displayDivs()
	addAuthor()
	listRaes()
	deleteAuthor()
	showRae()
	

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
			
		case "raeForm":	
			controller = "saveRae"
		break;
				
			
		
		}
		
		$.ajax({
			type : "POST",
			url : webroot + "/rae/"+controller,
			data : params,
			success : function(response) {
				
				clear("#"+formId)
				
				
				if(controller!="saveRae")
				window.location.reload(true);
				else
				listRaes()


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


function listRaes(){
	$.ajax({
		type : "POST",
		url : webroot + "/rae/listRaes",
		
		success : function(response) {
			buildTable(response)	

		}
	});
}


function buildTable(response) {

	var data = response.data;
	var col = response.columns;

	$('#raeDiv').html('<table id="rae_table" ></table>');
	data_table = $('#rae_table').dataTable({
		"aaData" : data,
		"aoColumns" : col,
		"bJQueryUI" : true,
		//"bAutoWidth": true,
		"aaSorting" : [[1, "asc"]],
		//"sScrollY": "466px",
		"oLanguage" : {
			"sProcessing" : "Procesando",
			"sLengthMenu" : "Cantidad",
			"sZeroRecords" : "No hay resultados",
			"sInfo" : "Informacion",
			"sInfoEmpty" : "Vacio",
			"sInfoFiltered" : "Filtro",
			"sInfoPostFix" : "post",
			"sSearch" : "Busqueda",
			"sUrl" : "",
			"oPaginate" : {
				"sFirst" : "Primero",
				"sPrevious" : "Anterior",
				"sNext" : "Siguiente",
				"sLast" : "Ultimo"
			}

		},
		"sPaginationType" : "full_numbers",
		//sDom: 'HrtlpF',
		sDom : 'HIrtF',
		"iDisplayLength" : -1,
		"aLengthMenu" : [[25, 50, 100, -1], [25, 50, 100, "Todos"]],
		"fnInitComplete" : function() {

		}
	});

}


function addAuthor(){
	$(".add").delegate(".evt-addAuthor", "click", function(e) {
		e.preventDefault()
		$(this).before('<p><input type="text" value="" id="author" name="author" class="text"></p>')
	
	
	})
}


function deleteAuthor(){
	$("#raeDiv").delegate(".deleteRae", "click", function(e) {
		var raeID = $(this).attr("raeId")
		var params ={}
		params['raeId'] = raeID
		
		$.ajax({
			type : "POST",
			url : webroot + "/rae/deleteRae",
			data : params,
			success : function(response) {
				listRaes()
			}
		})
	})

}

function showRae(){
	$("#listRaeDiv").delegate('.editRae', "click", function(){
		var raeId = $(this).attr("raeId")
		var params = {}
		params["raeId"] = raeId
		$.ajax({
			type: "POST",
			url: webroot + "/rae/showRae",
			data: params,
			success: function(response){
				//clear("#raeForm")
				$("#raeId").val(raeId)
				for (var field in response) {
					if (field != "author") {
						
						if(field !="name"){
						var tag = $("#"+field)
						
						tag.val(response[field])
						
						}else{
						
						$("#raeForm").find('input[name="name"]').val(response[field])
							
						}
					}
					else {
						var authors = response[field]
						
						for (var i = 0; i < authors.length; i++) {
							if (i == 0) {
								$("#author").val(authors[i])
							}
							else {
								if(i<authors.length)
								$(".evt-addAuthor").before('<p><input type="text" value="' + authors[i] + '" id="author" name="author" class="text"></p>')
								
							}
						}
						
					}
					
					
				}
				$("#saveTab").click()
				
			}
		});
	});
}



