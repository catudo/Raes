<g:javascript src="swfobject/swfobject.js" />

<div id="mainDiv"  class="box grid_16 alfa omega">
<div id='university'>

<script type="text/javascript">
swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"university", "500", "500", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateUniversityChart')}'}

);			

</script>

</div>

<div id='year'>

<script type="text/javascript">
swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"year", "500", "500", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateYearsChart')}'}
);			

</script>

</div>

<div id='methodology'>

<script type="text/javascript">
swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"methodology", "500", "500", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateMetodologyChart')}'}
		);			

					

</script>

</div>

<div id='keywords'>

<script type="text/javascript">

swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"keywords", "500", "500", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateKeyWordsChart')}'} );
					

</script>

</div>


</div>