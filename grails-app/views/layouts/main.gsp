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
		<link href="${resource(dir:'css',file:'print.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css',file:'screen.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/plugins/buttons',file:'screen.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/plugins/rtl',file:'screen.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/plugins/fancy-type',file:'screen.css')}" rel="stylesheet" type="text/css" />
		<g:layoutHead />	
	</head>
<body>
	<div class="container showgrid">
	<div id="userInfo" class="span-4">
	${user.names}
	</div>
	<div id="epsilon" class="span-12">
	</div>
	<div id="logo" class="span-8">
	<img  class ="push-0" src="${resource(dir:'images',file:'books.jpg')}" height="100" width="150"  border="0" /></a>
	</div>
	
	<div id="userTab" class="span-3">
	</div>
	<div id="adminTab" class="span-3" >
	</div>
	<div id="queryTab" class= "span-3 last">
	</div>
	
    <div id="main" class="span-24" >
	<g:layoutBody />	
	</div>
	<div id="footer" class="span-24 prepend-12 append-5 last">
 	asdasdasdasdasdasdasd
	</div>
	
	</div>
	</div>
</body>
</html>
