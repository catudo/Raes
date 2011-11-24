$(document).ready(function(){
	showAbstract()
	listRaes()
	$(".content").delegate(".linkFile", "click", function(e) {
		 window.location.href=$(this).attr('link')
			
		});	

});

function showAbstract(){
	$(".content").delegate('.showAbstract', "click", function(e){
		e.preventDefault();
		var raeID = $(this).attr('raeId')
		var params={}
		
		params['raeId'] = raeID
		
		$.ajax({
		type : "POST",
		data:params,
		url : webroot + "/queries/"+"showAbstract",
		success : function(response) {
			
			$("#showAbstractDiv").html('')
			$("#showAbstractDiv").html(response)
			
			
		
		}	
		
	});
	});
}

function listRaes(){
	$("#divForm").delegate('.button', "click", function(event) {
		event.preventDefault();
		
		
		var params = $("#queryForm").serialize();
		//params = params + "&accessLog=" + $("#accessLog").val()
	
	
	$.ajax({
		type : "POST",
		url : webroot + "/queries/selectRaes",
		data : params,
		success : function(response) {
			buildTable(response)
			clear("#queryForm")
			$('#showAbstractDiv').html('')
			

		}
	});
})
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

