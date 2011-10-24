<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<title>RAES</title>
<link href="${resource(dir:'css',file:'print.css')}" rel="stylesheet" type="text/css" />
<link href="${resource(dir:'css',file:'screen.css')}" rel="stylesheet" type="text/css" />
<link href="${resource(dir:'css/plugins/buttons',file:'screen.css')}" rel="stylesheet" type="text/css" />
<link href="${resource(dir:'css/plugins/rtl',file:'screen.css')}" rel="stylesheet" type="text/css" />
<link href="${resource(dir:'css/plugins/fancy-type',file:'screen.css')}" rel="stylesheet" type="text/css" />


	  	<script type="text/javascript" src="${resource(dir:'js/jquery.use',file:'jquery.js')}"></script>
	  	<script src="${resource(dir:'js/jquery.use',file:'jquery.use.js')}"></script>
	  	<script type="text/javascript" src="${resource(dir:'js/jquery-ui/js',file:'jquery-ui-1.8.11.custom.min.js')}"></script>
	  	<script type="text/javascript" src="${resource(dir:'js/jquery.use',file:'jquery.tools.min.js')}"></script>
	  	<script type="text/javascript" src="${resource(dir:'js',file:'raes.general.js')}"></script>

		<link href="${resource(dir:'js/jquery-ui/css/smoothness',file:'jquery-ui-1.8.11.custom.css')}" rel="stylesheet" type="text/css" />

<style type="text/css">
.auto-style1 {
	text-align: left;
}
</style>
</head>

<body>

<div class="container showgrid">
	
	<div id="logo" class="span-24 append-1">
	
	<img  class ="push-0" src="${resource(dir:'images',file:'books.jpg')}" height="100" width="150"  border="0" /></a>
	
	
	</div>
	
	<div class="span-24">
	
	<div class="span-14 push-0" style="height: 500px" >
	
	Panel Izquierdo
	</div>
	
	
	<div class="span-8 last">
		<fieldset>
          
          <legend>Login</legend>
		<form action='${postUrl}' method='POST' id='loginForm' class='cssform' autocomplete='off'>	
          <p class="auto-style1">
            <label for="dummy3">Usuario</label>
            <br>
            <input type='text' class='text_' name='j_username' id='username' style="width: 284px"/>
          </p>

          <p class="auto-style1">
            <label for="dummy4">Password</label>
            <br>
            
            <input type='password' class='text_' name='j_password' id='password' style="width: 280px"/>
            
          </p>
         <a class="button" id="submitForm" value="" name="dummy3" id="dummy4" >  
           Ingresar
          </a>
         
          <g:if test='${flash.message}'>
			<br>
			<p class="auto-style1"> 
			<div class='error'><strong>${flash.message}</strong></div>
			</p>
		</g:if>
		
		</form>
        </fieldset>		
	</div>
	</div>
	
	
	
	<div class="span-24 prepend-12 append-5 last">
 	asdasdasdasdasdasdasd
	</div>
</div>


<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();

		$("#submitForm").click(function(){
			$("#loginForm").submit();

		})
		
	})();
	// -->
</script>

</body>

</html>
