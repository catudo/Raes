
<g:javascript src="raes.rae.js" />

<ul class="nav main">
	<li >
		<a  class="tab"  id="saveTab">Almacenar Rae</a>
	</li>
	<li >
		<a class="tab"  id="listTab">Lista de Raes</a>
	</li>
</ul>
<div id="addRaeDiv" class="grid_16" style="margin-top:10px">
<div  class="box grid_16 alfa omega">
	<h2><a id="toggle-search" href="#" >Atributos de Raes</a></h2>
	<div class="add">
		<div class="box grid_5 block">
			<g:form name="universityForm">
				<fieldset>
					<legend>
						Agregar Universidad
					</legend>
					<p>
						<label>Nombre </label>
						<g:textField class="text" name="name"/>
					</p>
					<p>
						<label>descripci&oacute;n </label>
						<br>
						<g:textArea name="description"  rows="5" cols="55"></g:textArea>
					</p>
					<input class="evt-properties" type="submit" value="Almacenar" class="confirm button">
				</fieldset>
			</g:form>
		</div>
		<div class="box grid_5 block">
			<g:form name="categoryForm">
				<fieldset>
					<legend>
						Agregar Categoria
					</legend>
					<p>
						<label>Nombre </label>
						<g:textField class="text" name="name"/>
					</p>
					<p>
						<label>descripci&oacute;n </label>
						<br>
						<g:textArea name="description" rows="5" cols="55"></g:textArea>
					</p>
					<input class="evt-properties" type="submit" value="Almacenar" class="confirm button">
				</fieldset>
			</g:form>
		</div>
	</div>
</div>
<div id="raeFormDiv"  class="box grid_16 alfa omega">

</div>
</div>

<div id="listRaeDiv" class='grid_16' style="display:none; margin-top:10px;" >
<div  class="box grid_16 alfa omega">
	<h2><a id="toggle-search" href="#" >Lista de Raes</a></h2>
	
	<div id ="raeDiv"  style=" width:100%; overflow: auto;">
	
	
	
	</div>
	
</div>
</div>
