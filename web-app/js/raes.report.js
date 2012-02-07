$(document).ready(function() {

	
	displayReportTabs();
	
	var counter = 0
	$('#mainDiv').children().each(function(){
		if(counter != 0){
			$(this).css('display','none')
			
		}
		counter++;
		
	})
	
	generateUniversitiesChart()
	
	generateYearsChart()
	generateMethodologyChart()
	generateKeyWordsChart()
	generateToolsChart()
	
	
});


function generateUniversitiesChart(){
	
	$.ajax({
		type : "POST",
		url : webroot + "/report/generateUniversityChart",
		
		success : function(response) {
			
			  var hbar1 = new RGraph.HBar('universityCanvas', response.universitySeries);

		      var grad = hbar1.context.createLinearGradient(275,0,900, 0);
		      grad.addColorStop(0, 'red');
		      grad.addColorStop(1, 'blue');

		      hbar1.Set('chart.strokestyle', 'rgba(0,0,0,0)');
		      hbar1.Set('chart.gutter.left', 350);
		      hbar1.Set('chart.gutter.right', 20);
		      hbar1.Set('chart.background.grid.autofit', true);
		      hbar1.Set('chart.title', 'Universidades');
		      hbar1.Set('chart.labels', response.universityLabels);
		      hbar1.Set('chart.shadow', true);
		      hbar1.Set('chart.shadow.color', 'gray');
		      hbar1.Set('chart.shadow.offsetx', 0);
		      hbar1.Set('chart.shadow.offsety', 0);
		      hbar1.Set('chart.shadow.blur', 15);
		      hbar1.Set('chart.colors', [grad]);
		      hbar1.Draw();	
					
		}	
				
		
		});
	
	
	
	
	
	
}




function generateYearsChart(){
	
	$.ajax({
		type : "POST",
		url : webroot + "/report/generateYearsChart",
		
		success : function(response) {
			
			  var hbar1 = new RGraph.HBar('yearCanvas', response.yearsSeries);

		      var grad = hbar1.context.createLinearGradient(275,0,900, 0);
		      grad.addColorStop(0, 'red');
		      grad.addColorStop(1, 'blue');

		      hbar1.Set('chart.strokestyle', 'rgba(0,0,0,0)');
		      hbar1.Set('chart.gutter.left', 275);
		      hbar1.Set('chart.gutter.right', 10);
		      hbar1.Set('chart.background.grid.autofit', true);
		      hbar1.Set('chart.title', 'Periodos');
		      hbar1.Set('chart.labels', response.yearsLabels);
		      hbar1.Set('chart.shadow', true);
		      hbar1.Set('chart.shadow.color', 'gray');
		      hbar1.Set('chart.shadow.offsetx', 0);
		      hbar1.Set('chart.shadow.offsety', 0);
		      hbar1.Set('chart.shadow.blur', 15);
		      hbar1.Set('chart.colors', [grad]);
		      hbar1.Draw();	
			
			
					
		}	
				
		
		});
	
	
	
	
	
	
}


function generateMethodologyChart(){
	
	$.ajax({
		type : "POST",
		url : webroot + "/report/generateMetodologyChart",
		
		success : function(response) {
			
			  var hbar1 = new RGraph.HBar('methodologyCanvas', response.methodologySeries);

		      var grad = hbar1.context.createLinearGradient(275,0,900, 0);
		      grad.addColorStop(0, 'red');
		      grad.addColorStop(1, 'blue');
		      hbar1.Set('chart.strokestyle', 'rgba(0,0,0,0)');
		      hbar1.Set('chart.gutter.left', 350);
		      hbar1.Set('chart.gutter.right', 10);
		      hbar1.Set('chart.background.grid.autofit', true);
		      hbar1.Set('chart.title', 'Metodologias');
		      hbar1.Set('chart.labels', response.methodologyLabels);
		      hbar1.Set('chart.shadow', true);
		      hbar1.Set('chart.shadow.color', 'gray');
		      hbar1.Set('chart.shadow.offsetx', 0);
		      hbar1.Set('chart.shadow.offsety', 0);
		      hbar1.Set('chart.shadow.blur', 15);
		      hbar1.Set('chart.colors', [grad]);
		      hbar1.Draw();	
			
			
					
		}	
				
		
		});
	
	
	
	
	
	
}


function generateKeyWordsChart(){
	
	$.ajax({
		type : "POST",
		url : webroot + "/report/generateKeyWordsChart",
		
		success : function(response) {
			
			  var hbar1 = new RGraph.HBar('keyWordsCanvas', response.universitySeries);

		      var grad = hbar1.context.createLinearGradient(275,0,900, 0);
		      grad.addColorStop(0, 'red');
		      grad.addColorStop(1, 'blue');

		      hbar1.Set('chart.strokestyle', 'rgba(0,0,0,0)');
		      hbar1.Set('chart.gutter.left', 275);
		      hbar1.Set('chart.gutter.right', 10);
		      hbar1.Set('chart.background.grid.autofit', true);
		      hbar1.Set('chart.title', 'Categorias');
		      hbar1.Set('chart.labels', response.universityLabels);
		      hbar1.Set('chart.shadow', true);
		      hbar1.Set('chart.shadow.color', 'gray');
		      hbar1.Set('chart.shadow.offsetx', 0);
		      hbar1.Set('chart.shadow.offsety', 0);
		      hbar1.Set('chart.shadow.blur', 15);
		      hbar1.Set('chart.colors', [grad]);
		      hbar1.Draw();	
			
			
					
		}	
				
		
		});
	
	
	
	
	
	
}


function generateToolsChart(){
	
	$.ajax({
		type : "POST",
		url : webroot + "/report/generateToolsChart",
		
		success : function(response) {
			
			  var hbar1 = new RGraph.HBar('toolsCanvas', response.keyWordsSeries);

		      var grad = hbar1.context.createLinearGradient(275,0,900, 0);
		      grad.addColorStop(0, 'red');
		      grad.addColorStop(1, 'blue');

		      hbar1.Set('chart.strokestyle', 'rgba(0,0,0,0)');
		      hbar1.Set('chart.gutter.left',400);
		      hbar1.Set('chart.gutter.right', 10);
		      hbar1.Set('chart.background.grid.autofit', true);
		      hbar1.Set('chart.title', 'Herramientas');
		      hbar1.Set('chart.labels', response.keyWordsLabels);
		      hbar1.Set('chart.shadow', true);
		      hbar1.Set('chart.shadow.color', 'gray');
		      hbar1.Set('chart.shadow.offsetx', 0);
		      hbar1.Set('chart.shadow.offsety', 0);
		      hbar1.Set('chart.shadow.blur', 15);
		      hbar1.Set('chart.colors', [grad]);
		      hbar1.Draw();	
			
			
					
		}	
				
		
		});
	
	
	
	
	
	
}




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