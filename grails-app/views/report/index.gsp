<g:javascript src="swfobject/swfobject.js" />
<g:javascript src="raes.report.js" />


<ul class="nav main">
	<li >
		<a id='university-evt' class="tab" >Universidades</a>
	</li>
	<li >
		<a id='year-evt' class="tab">A&ntilde;o</a>
	</li>
	<li >
		<a id=' methodology-evt' class="tab">Metodologia</a>
	</li>
	<li >
		<a id='keyword-evt' class="tab">Palabras Claves</a>
	</li>
	
</ul>


<div id="mainDiv"  style="margin-top:10px "   class="box grid_16 alfa omega">


<div id='universityDiv' >
<div id='university' class="grid_16" style="margin-top: 10px">

<script type="text/javascript">
swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"university", "1100", "560", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateUniversityChart')}'}

);			

</script>
</div>
</div>


<div id="yearDiv">
<div id='year' class="grid_4" style="display:none">

<script type="text/javascript">
swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"year", "1100", "560", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateYearsChart')}'}
);			

</script>
</div>

</div>
<div id='methodologyDiv'>
<div id='methodology' class="grid_4" style="display:none">

<script type="text/javascript">
swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"methodology", "1100", "560", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateMetodologyChart')}'}
		);			

					

</script>
</div>
</div>

<div id='keywordDiv'>
<div id='keywords' class="grid_4" style="display:none">

<script type="text/javascript">

swfobject.embedSWF( "${resource(dir:'flash',file:'open-flash-chart.swf')}",
		"keywords", "1100", "560", "9.0.0", "expressInstall.swf",
		{"data-file":'${resource(dir:'',file:'report/generateKeyWordsChart')}'} );
					

</script>
</div>
</div>


</div>