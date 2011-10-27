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
		<link href="${resource(dir:'css',file:'raesCss.css')}" rel="stylesheet" type="text/css" />
		<g:layoutHead />	
	</head>
<body>
	
		<div id="wrapper">
			<div id="header">
				<div id="logo">
					 <h1><a >Raes<span>&amp;</span>Attorney</a></h1>
				</div>
				<div class="clr"></div>
			</div>
			
			<sec:ifAllGranted roles="ROLE_ADMIN">
			<div class="contentRaes">
			<ul id="menu">
					<li><a><strong>Usuarios</strong></a></li>
					<li><a><strong>Administraci&oacute;n de la informaci&oacute;n</strong></a></li>
					<li><a><strong>Consultas</strong></a></li>					
				</ul>
			</div>
			</sec:ifAllGranted>
			<div id="main">
			<g:layoutBody />
			</div>
				<div class="clr"></div>
			</div>
			<div id="footer">
				<p>Copyright &copy; 2010 </p>
			</div>
		</div>
</body>
</html>
