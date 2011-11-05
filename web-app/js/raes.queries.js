$(document).ready(function(){

	listRaes()

});




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

