$(document).ready(function(){
setUpForm()

$('#saveButton').click(function(event){
    	event.preventDefault();
    	$('#saveUser').submit();
		
		
});
listUsers()
});



function setUpForm(){

	var validator = $("#saveUser").validate({
		onsubmit: true,
		//errorLabelContainer: "#summary",
		showErrors: function(errorMap, errorList){
			$('#saveUser').find('label.error-label').html("");
			var is_valid = validator.numberOfInvalids();
			if (is_valid > 0) {
				var invalids = validator.invalid
				for (var field in invalids) {
					if (invalids[field]) {
						var msg = $.trim(validator.settings.messages[field].required)
						$("#" + field).after('<label class="error-label">' + msg + '</label>');
						
					}
					
				}
				
			}
		},
		rules: {
		
			names: {
				required: true,
				success: function(){
					if (validator.numberOfInvalids() > 0) {
					
					}
					else {
					
					}
					
				}
			},
			lastName: {
				required: true,
				success: function(){
					if (validator.numberOfInvalids() > 0) {
					
					}
					else {
					
					}
					
				}
			},
			passwd: {
				required: true,
				success: function(){
					if (validator.numberOfInvalids() > 0) {
					
					}
					else {
					
					}
					
				}
			},
			email: {
				required: true,
				email: true,
				success: function(){
					if (validator.numberOfInvalids() > 0) {
					
					}
					else {
					
					}
				}
			}
		},
		messages: {
			names: {
				required: "Los Nombres son Requeridos"
			},
			
			lastName: {
				required: "Los Apellidos son Requeridos"
			},
			passwd: {
				required: "El password es Requerido"
			},
			username: {
				required: "El Nombre de Usuario es Requerido"
			},
			email: {
				required: "El email es incorrecto",
				email: "formato de mail no valido"
			}
		},
		
		submitHandler: function(form){
			$('#summary').removeClass('error');
			var params = $(form).serialize();
			params = params + "&accessLog=" + $("#accessLog").val()
			
			$.ajax({
				type: "POST",
				url: webroot + "/user/save",
				data: params,
				success: function(message){
				
					clear(form)
					
				}
			});
			return false;
			
		},
		onfocusout: function(element){
			$(element).valid();
		}
		
		
	});
	
}


function listUsers(){
    $.ajax({
        type: "POST",
        url: webroot + "/user/list",
        
        success: function(message){
        buildTable(message)

            
        }
    });
}	
	
	
	
function buildTable(response){

    var data = response.data;
    var col = response.columns;

    $('#userlist').html('<table id="user_table" cellpadding="0" cellspacing="0"></table>' );
	data_table  = $('#user_table').dataTable( {
		"aaData": data,
		"aoColumns": col,
		"bJQueryUI": true,
		"bAutoWidth": false,
		"aaSorting": [[ 1, "asc" ]],
		"sScrollY": "230px",
		"oLanguage": {
			"sProcessing": "Procesando",
			"sLengthMenu": "Cantidad",
			"sZeroRecords": "No hay resultados",
			"sInfo": "Informacion",
			"sInfoEmpty": "Vacio",
			"sInfoFiltered": "Filtro",
			"sInfoPostFix": "post",
			"sSearch": "Busqueda",
			"sUrl": "",
			"oPaginate": {
				"sFirst":    "Primero",
				"sPrevious": "Anterior",
				"sNext":     "Siguiente",
				"sLast":    "Ultimo"
			}

		},
		"sPaginationType": "full_numbers",
		sDom: 'HirtlpF',
		"iDisplayLength": -1,
		"aLengthMenu": [[25, 50, 100, -1], [25, 50, 100, "Todos"]],
		"fnInitComplete": function() {

		}
	} );




}






