<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><g:layoutTitle default="Raes" /></title>
		
	  	<script type="text/javascript" src="${resource(dir:'js/jquery.use',file:'jquery.js')}"></script>
	  	<script src="${resource(dir:'js/jquery.use',file:'jquery.use.js')}"></script>
	  	<script type="text/javascript" src="${resource(dir:'js/jquery-ui/js',file:'jquery-ui-1.8.11.custom.min.js')}"></script>
	  	<script type="text/javascript" src="${resource(dir:'js/jquery.use',file:'jquery.tools.min.js')}"></script>
	  	<script type="text/javascript" src="${resource(dir:'js',file:'raes.general.js')}"></script>

		<link href="${resource(dir:'js/jquery-ui/css/smoothness',file:'jquery-ui-1.8.11.custom.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/print.css')}" rel="stylesheet" type="text/css" />
		<g:layoutHead />	
	</head>
<body>
	<div id="userInfo" class="column sort ui-selectee span-4 ui-ClickSelectee" style="opacity: 1; -moz-user-select: none;">
	</div>
	<div id="epsilon" class="column ui-selectee sort span-12 ui-ClickSelectee" style="opacity: 1; display: block; position: relative; -moz-user-select: none;">
	</div>
	<div id="logo" class="column ui-selectee sort span-8 last ui-ClickSelectee" style="display: block; position: relative; opacity: 1; -moz-user-select: none;">
	<img src="${resource(dir:'images',file:'books.png')}"  border="0" /></a>
	</div>
	
	<div id="userTab" class="column ui-selectee sort span-3 ui-ClickSelectee" style="display: block; position: relative; -moz-user-select: none; right: 0px; top: 0px; height: 18px;">
	</div>
	<div id="adminTab" class="column ui-selectee sort span-3 ui-ClickSelectee" style="display: block; position: relative; -moz-user-select: none;">
	</div>
	<div id="queryTab" class="column ui-selectee sort span-3 last ui-ClickSelectee" style="-moz-user-select: none; display: block; position: relative;">
	</div>
	
    <div id="main" class="column sort ui-selectee span-24 ui-ClickSelectee" style="opacity: 1; -moz-user-select: none;">
	<g:layoutBody />	
	</div>
	<div id="footer" class="column ui-selectee sort span-24 ui-selected" style="opacity: 1; -moz-user-select: none; display: block; position: relative; right: 0px; top: 0px;">
		
	</div>
	
	</div>
	
</body>
</html>
