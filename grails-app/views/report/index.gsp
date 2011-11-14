<g:javascript src="swfobject/swfobject.js" />

<div id="mainDiv"  style="margin-top:10px "   class="box grid_16 alfa omega">

<div id='university' class="grid_4">

<script type="text/javascript">
swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"university", "500", "500", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateUniversityChart')}'}

);			

</script>

</div>

<div id='year' class="grid_4">

<script type="text/javascript">
swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"year", "500", "500", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateYearsChart')}'}
);			

</script>

</div>

<div id='methodology' class="grid_4">

<script type="text/javascript">
swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"methodology", "500", "500", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateMetodologyChart')}'}
		);			

					

</script>

</div>

<div id='keywords' class="grid_4">

<script type="text/javascript">

swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"keywords", "500", "500", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateKeyWordsChart')}'} );
					

</script>

</div>


</div>