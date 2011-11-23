<%@page import="raes.Rae"%>
<g:javascript src="raes.rae.js" />
<g:javascript src="ajaxupload.js" />

<ul class="nav main">
	<li >
		<a  class="tab"  id="saveTab">Almacenar Ficha</a>
	</li>
	<li >
		<a class="tab"  id="listTab">Lista de Ficha</a>
	</li>
	<li >
		<a class="tab"  id="AtTab">Atributos de Ficha</a>
	</li>
	<li>
	<g:form name="reportForm" action="atributtesReport" >
	<g:hiddenField name="_file" value="atributos.jrxml"/>
	<g:hiddenField name="_format" value="PDF"/>
	<a class="printAttr">Reporte de Atributos</a>	
	</g:form>
	
	</li>
	
</ul>
<div id="addRaeDiv" class="grid_16" style="margin-top:10px; display: none;">
<div  class="box grid_16 alfa omega">
	<h2><a id="toggle-search" href="#" >Atributos de Raes</a></h2>
	<div class="add">
		<div class="grid_8 ">
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
						<g:textArea name="description"  rows="5" cols="61"></g:textArea>
					</p>
					<input class="evt-properties" type="submit" value="Almacenar" class="confirm button">
				</fieldset>
			</g:form>
		</div>
		<div class="grid_8 ">
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
						<g:textArea name="description" rows="5" cols="61"></g:textArea>
					</p>
					<input class="evt-properties" type="submit" value="Almacenar" class="confirm button">
				</fieldset>
			</g:form>
		</div>
		<div class="grid_16">
		<div id='universityListDiv'  style="overflow: auto;" class='grid_8'>
		
		
		</div>
		
		<div id='categoryListDiv'  style="overflow: auto;" class='grid_8'>
		
		
		</div>
		</div>
		
	</div>
</div>




</div>

<div id="raeFormDiv"  class="box grid_16 alfa omega" style='margin-top:10px;'>

</div>

<div id="listRaeDiv" class='grid_16' style="display:none; margin-top:10px;" >
<div  class="box grid_16 alfa omega">
	<h2><a id="toggle-search" href="#" >Lista de Raes</a></h2>
	
	<div id ="raeDiv"  style=" width:100%; overflow: auto;">
	
	
	
	</div>
	
</div>
</div>
