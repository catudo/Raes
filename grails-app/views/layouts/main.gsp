<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>
			<g:layoutTitle default="Raes" />
		</title>
		<script type="text/javascript" src="${resource(dir:'js/jquery.use',file:'jquery.js')}"></script>
		<script src="${resource(dir:'js/jquery.use',file:'jquery.use.js')}"></script>
		<script type="text/javascript" src="${resource(dir:'js/jquery-ui/js',file:'jquery-ui-1.8.11.custom.min.js')}"></script>
		<script type="text/javascript" src="${resource(dir:'js/jquery.use',file:'jquery.tools.min.js')}"></script>
		<script type="text/javascript" src="${resource(dir:'js',file:'raes.general.js')}"></script>
		
		<link href="${resource(dir:'js/jquery-ui/css/smoothness',file:'jquery-ui-1.8.11.custom.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/template/',file:'960.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/template/',file:'grid.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/template/',file:'ie.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/template/',file:'ie6.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/template/',file:'nav.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/template/',file:'reset.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/template/',file:'text.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css/template/',file:'layout.css')}" rel="stylesheet" type="text/css" />
		<link href="${resource(dir:'css',file:'backEnd.css')}" rel="stylesheet" type="text/css" />
		
		
		<g:javascript src="dataTables/media/js/jquery.dataTables.js" />
		<g:layoutHead />
	</head>
	<body>
		<div class="container_16">
			<div class="grid_16">
				<h1 id="branding"><a href="../">Raes</a></h1>
			</div>
			<div class="clear"></div>
		
				<div class="grid_16">
					<ul class="nav main">
					<sec:ifAllGranted roles="ROLE_ADMIN">
						<li>
							<a  href="#" class="linkPath" link="${server}/user/index" >Usuarios</a>
						</li>
						<li >
							<a href="#" class="linkPath" link="${server}/rae/index">Administraci&oacute;n de la informaci&oacute;n</a>
						</li>
						
					</sec:ifAllGranted>
					
					<li >
							<a href="#" class="linkPath" link="${server}/queries/index">Consultas</a>
					</li>
						
					
					<li>
							<a href="#" class="linkPath" link="${server}/report/index">Reportes</a>
						</li>
					
						<li class="secondary" >
							<a href="${server}/logout/index">Cerrar Sesi&oacuten</a>
						</li>
						
						<li class="secondary" >
							<a href="#" >${user.names}</a>
						</li>
						
						
					</ul>
					
				</div>
			
			<div class="content grid_16">
				<g:hiddenField name="accessLog" value="${accessLog.id}" />
				<g:layoutBody />
				<div class="clear"></div>
				<div class="grid_16" id="site_info">
					<div class="box">
						<p>
							Copyright &copy; 2011
						</p>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</body>
</html>
