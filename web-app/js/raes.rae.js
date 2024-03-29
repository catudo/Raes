var data_table
var table
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
	cancelForm()
	

	$(".container_16").delegate(".linkFile", "click", function(e) {
	 window.location.href=$(this).attr('link')
		
	});
	
	
	$(".container_16").delegate(".printAttr", "click", function(e) {
		 $(this).parent().submit()
			
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
				clear("#"+formId)
							listCategories()
							listUniversities()
							listRaes()
							getForm()
						
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
		
		data_table.fnDraw();
		var oTable = $('#categoryTable').dataTable();
	    oTable.fnDraw();
		
		
	    var oTable2 = $('#universityTable').dataTable();
	    oTable2.fnDraw();
		
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
		"sScrollY": "512px",
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
	    "bFilter": true,
		"aoColumns" : col,
		"bJQueryUI" : true,
		"bAutoWidth": true,
		"aaSorting" : [[1, "asc"]],
		"sScrollY": "500px",
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
		$(this).before('<p><input type="text" value="" id="keyword" name="keyword" class="text raeText autocompleteKey"></p>')
			autocompleteKeyWord()

	
	
	})
	
	
	$("#raeFormDiv").delegate(".evt-addTool", "click", function(e) {
		e.preventDefault()
		$(this).before('<p><input type="text" value="" id="tool" name="tool" class="text raeText autocompleteTools"></p>')
					autocompleteTools()
	
	})
	
}
function autocompleteTools(){
	
	$( ".autocompleteTools" ).autocomplete({
		source: webroot+"/rae/autocompleteTools",
		
		select: function( event, ui ) {
			event.preventDefault();
			$(this).val( ui.item.label );
			
		},focus: function(event, ui){
            event.preventDefault();
            $(this).val(ui.item.label);
			
        },
        
		minLength: 3
	});
	
	
}


function autocompleteKeyWord(){
	
	$( ".autocompleteKey" ).autocomplete({
		source: webroot+"/rae/autocompleteKeyWord",
		
		select: function( event, ui ) {
			event.preventDefault();
			$(this).val( ui.item.label );
			$( ".autocompleteKey" ).val( ui.item.value );
		},focus: function(event, ui){
            event.preventDefault();
            $(this).val(ui.item.label);
			
        },
        
		minLength: 3
	});
	
	
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
		getForm()
		var raeId = $(this).attr("raeId")
		setTimeout(function(){
		
		var params = {}
		params["raeId"] = raeId
		$.ajax({
			type: "POST",
			url: webroot + "/rae/showRae",
			data: params,
			success: function(response){
				
				clear("#raeForm")
				
				$("#raeId").val(raeId)
				for (var field in response) {
					if (field != "author" && field !="keyWords" && field !="tools") {
						
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
						
					}else if(field=='tools'){
						
						var tools = response[field]
						
						for (var i = 0; i < tools.length; i++) {
							if (i == 0) {
								$("#tool").val(tools[i])
							}
							else {
								if(i<tools.length)
								$(".evt-addTool").before('<p><input type="text" value="' + tools[i] + '" id="tool" name="tool" class="text"></p>')
								
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
				$('#year').attr('disabled','disabled')
				$("#saveTab").click()
				
			}
		});
		},500);
	});
}

function cancelForm(){
	$("#raeFormDiv").delegate('#cancelEdition', "click", function(e){
	e.preventDefault()
	//$('#year').attr('disabled',false)
	//clear('#raeForm')
	getForm()
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
			
			autocompleteKeyWord()
			autocompleteTools()
			


		}
	});



	
	
}

