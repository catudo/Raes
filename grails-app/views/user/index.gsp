<%@page import="raes.Role"%>
<g:javascript src="raes.user.js" />
<g:javascript src="jquery-validation/jquery.validate.min.js" />





			<ul id="menu">
					<li id="userTab"><a><strong>Edici&oacute;n de Usuarios</strong></a></li>
					<li id="eventTab"><a><strong>Eventos</strong></a></li>
			</ul>


<div id="usersList">
<div class="prepend-16" >    
<div>
    <fieldset>
     <legend>Almacenar Usuarios</legend>
     <form method="post" action="" id="saveUser">
           
           <p>
              <label for="names">Nombres</label><br>
              <g:textField class="text" name="names"/>
              
              
            </p>
            
            
            <p>
              <label for="lastName">Apellidos</label><br>
              <g:textField class="text" name="lastName"/>
            </p>
            
            <p>
              <label for="username">Usuario</label><br>
              <g:textField class="text" name="username"/>
            </p>
            
            <p>
              <label for="email">Email</label><br>
              <g:textField class="text" name="email"/>
            </p>
            
            <p>
              <label for="passwd">Password</label><br>
              <g:passwordField class="text password"  name="passwd"/>
            </p>
			
			
			<p>
			<label for="role">ROL</label><br>
			<g:select class="select" name="role" from="${Role.list()}"  optionValue="${{message(code:it.authority)}}" optionKey="id"></g:select>
			</p>
		                
			<a id="saveButton" class="button right" id="submitForm" value=""  id="dummy4">Almacenar</a>
          
        </form>
	</fieldset>
     
     
</div>

<div id="userlist"></div>
</div>

<div id="EventList" style="display:none">
 
</div>

