<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title><g:layoutTitle default="Raes" /></title>
		<g:layoutHead />


	  	<script type="text/javascript" src="${resource(dir:'js/jquery.use',file:'jquery.js')}"></script>
	  	<script src="${resource(dir:'js/jquery.use',file:'jquery.use.js')}"></script>
	  	<script type="text/javascript" src="${resource(dir:'js/jquery-ui/js',file:'jquery-ui-1.8.11.custom.min.js')}"></script>
	  	<script type="text/javascript" src="${resource(dir:'js/jquery.use',file:'jquery.tools.min.js')}"></script>
	  

	    

		<link href="${resource(dir:'css',file:'960.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'js/jquery-ui/css/smoothness',file:'jquery-ui-1.8.11.custom.css')}" rel="stylesheet" type="text/css" />

	    <link href="${resource(dir:'css',file:'front_end.css')}" rel="stylesheet" type="text/css" />

		
	</head>
<body>
    <div id="summary"></div>
    <div class="header fixed">
    	<div class="container">
        	<g:render template="/login/sign_in"></g:render>
        	<a href="" class="logo_container">
            	<img src="${resource(dir:'img/misc',file:'logo_mediros.jpg')}" width="160" height="55" />
            </a>
        </div>
    </div>
	<g:layoutBody />
	
</body>
</html>
