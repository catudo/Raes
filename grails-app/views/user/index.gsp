<%@page import="raes.Role"%>
<g:javascript src="raes.user.js" />
<g:javascript src="jquery-validation/jquery.validate.min.js" />
<ul class="nav main">
	<li >
		<a >Edici&oacute;n de Usuarios</a>
	</li>
	<li >
		<a>Eventos</a>
	</li>
</ul>
<div  id="usersOperation">
	<div class="grid_4  box">
		<h2><a id="toggle-search" href="#" >Almacenar Usuarios</a></h2>
		<fieldset>
		
		<form method="post" action="" id="saveUser">
			<g:hiddenField name="userId"/>
			<p>
				<label for="names">Nombres</label>
				<br>
				<g:textField class="text" name="names"/>
			</p>
			<p>
				<label for="lastName">Apellidos</label>
				<br>
				<g:textField class="text" name="lastName"/>
			</p>
			<p>
				<label for="username">Usuario</label>
				<br>
				<g:textField class="text" name="username"/>
			</p>
			<p>
				<label for="email">Email</label>
				<br>
				<g:textField class="text" name="email"/>
			</p>
			<p>
				<label for="passwd">Password</label>
				<br>
				<g:passwordField class="text password"  name="passwd"/>
			</p>
			<p>
				<label for="role">ROL</label>
				<br>
				<g:select class="select" name="role" from="${Role.list()}"  optionValue="${{message(code:it.authority)}}" optionKey="id"></g:select>
			</p>
			<a id="saveButton" class="button right" id="submitForm" value=""  id="dummy4">Almacenar</a>
		</fieldset>
		</form>
		
	</div>
	<div id="userlist" style="" class="grid-9 omega"></div>
</div>
<div id="EventList" class="grid_16" style="display:none"></div>
