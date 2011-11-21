$(document).ready(function(){

	saveProperties()
	displayDivs()
	addAuthorAndKeyWords()
	listRaes()
	deleteAuthor()
	showRae()
	getForm()
	listCategories()
	listUniversities()
	deleteProperty()

	$(".container_16").delegate(".linkFile", "click", function(e) {
	 window.location.href=$(this).attr('link')
		
	});	
		
	
	
	
});


function saveProperties(){
	$(".container_16").delegate(".evt-properties", "click", function(e) {
		
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
				getForm()
				
							listCategories()
							listUniversities()
						
			}	
					
			
			});
	});
		

	
	
}


function displayDivs(){
	$(".nav").delegate(".tab", "click", function(e) {
		e.preventDefault();
		
		var id =$(this).attr("id")
		
		
		if(id=="saveTab"){
			if(!$("#raeFormDiv").is(":visible")){
				$("#listRaeDiv").slideUp()
				$("#addRaeDiv").slideUp()
				$("#raeFormDiv").slideDown()
				
				
			}
		}else if(id=='listTab'){
			if(!$("#listRaeDiv").is(":visible")){
				$("#listRaeDiv").slideDown()
				$("#addRaeDiv").slideUp()
				$("#raeFormDiv").slideUp()
				
			}
			
		}else{
			if(!$("#addRaeDiv").is(":visible")){
				$("#listRaeDiv").slideUp()
				$("#addRaeDiv").slideDown()
				$("#raeFormDiv").slideUp()
				
				
			}
			
		}
		
		
	})
}


function assignDocumentWithRae(){
	
	
	
	
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


function listCategories(){
	$.ajax({
		type : "POST",
		url : webroot + "/rae/listCategories",
		
		success : function(response) {
			buildPropertiesTable(response,'listCategories')	

		}
	});
}


function listUniversities(){
	$.ajax({
		type : "POST",
		url : webroot + "/rae/listUniversities",
		
		success : function(response) {
			buildPropertiesTable(response,'listUniversities')	

		}
	});
}


function deleteProperty(){
	//deleteProperty
	$(".container_16").delegate(".deleteProperty", "click", function(e) {
		var params={}
		var id
		var controller 
		if($(this).hasClass('deleteUniversity')){
			id = $(this).attr('universityId')
			controller='deleteUniversity'
		
		}else{
			id = $(this).attr('categoryId')
			controller='deleteCategory'
		}
		
		params['id']=id
		$.ajax({
			type : "POST",
			url : webroot + "/rae/"+controller,
			data:params,
			success : function(response) {
				if(response.errors){
					alert(response.errors[0].message)
					
				}else{
					listCategories()
					listUniversities()
					getForm()	
				}
				

			}
		});
		
		
		
		
	})

}



function buildPropertiesTable(response,type){

	var data = response.data;
	var col = response.columns;

	var table
	if(type=='listUniversities'){
		$('#universityListDiv').html("<table style='width:100%;'  id='universityTable' ></table>");
		table =$('#universityTable')
	}else{
		$('#categoryListDiv').html("<table style='width:100%;' id='categoryTable' ></table>");
		table =$('#categoryTable')
	}
		
		table.dataTable({
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


function addAuthorAndKeyWords(){
	$("#raeFormDiv").delegate(".evt-addAuthor", "click", function(e) {
		e.preventDefault()
		$(this).before('<p><input type="text" value="" id="author" name="author" class="text raeText"></p>')
	
	
	})
	
	
	$("#raeFormDiv").delegate(".evt-addKeyWords", "click", function(e) {
		e.preventDefault()
		$(this).before('<p><input type="text" value="" id="keyword" name="keyword" class="text raeText"></p>')
	
	
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
					if (field != "author" && field !="keyWords") {
						
						if(field !="name"){
						var tag = $("#"+field)
						
						tag.val(response[field])
						
						}else{
						
						$("#raeForm").find('input[name="name"]').val(response[field])
							
						}
					}
					else if (field =="keyWords"){
						var keyWords = response[field]
						
						for (var i = 0; i < keyWords.length; i++) {
							if (i == 0) {
								$("#keyword").val(keyWords[i])
							}
							else {
								if(i<keyWords.length)
								$(".evt-addKeyWords").before('<p><input type="text" value="' + keyWords[i] + '" id="keyword" name="keyword" class="text"></p>')
								
							}
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

function getForm(){
	
	$.ajax({
		type : "POST",
		url : webroot + "/rae/"+"showForm",
		success : function(response) {
			
			$("#raeFormDiv").html('')
			$("#raeFormDiv").html(response)
			
			new AjaxUpload('documentFile', {
				action: webroot + "/rae/saveFile",
				onSubmit : function(file , ext){
				
				},
				onComplete : function(file){
				
				}
				});
			
			
			


		}
	});
}

