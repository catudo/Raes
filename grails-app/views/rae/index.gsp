<%@page import="raes.University"%>
<%@page import="raes.Category"%>
<g:javascript src="raes.rae.js" />

<ul class="nav main">
	<li >
		<a  class="tab"  id="saveTab">Almacenar Rae</a>
	</li>
	<li >
		<a class="tab"  id="listTab">Lista de Raes</a>
	</li>
</ul>
<div id="addRaeDiv">
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
						<g:textArea name="description"></g:textArea>
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
						<g:textArea name="description"></g:textArea>
					</p>
					<input class="evt-properties" type="submit" value="Almacenar" class="confirm button">
				</fieldset>
			</g:form>
		</div>
	</div>
</div>
<div  class="box grid_16 alfa omega">
	<h2><a id="toggle-search" href="#" >Agregar de Raes</a></h2>
	<div class="add">
		<div>
			<g:form name="raeForm">
				<fieldset>
					<legend>
						Contenido
					</legend>
					
					<g:hiddenField name="raeId"/>
					<p>
						<label>Nombre</label>
						<g:textField class="text" name="name"/>
					</p>
					
					<p>
						<label>Analista</label>
						<g:textField class="text" name="analyst"/>
					</p>
					<p>
						<label>Ciudad</label>
						<g:textField class="text" name="city"/>
					</p>
					<p>
						<label>Metodologia</label>
						<g:textField class="text" name="methodology"/>
					</p>
					<p>
						<label>Numero Topografico</label>
						<g:textField class="text" name="topographicalNumber"/>
					</p>
					<p>
						<label>autor(es)</label>
						<g:textField class="text" name="author"/>
						<br>
						</br><a class="button evt-addAuthor" href="#">Agregar Autor</a>
					</p>
					<p>
						<label>Palabras Claves</label>
						<g:textField class="text" name="keyWords"/>
						
					</p>
					<p>
						<label>Universidad</label>
						<g:select name="university" optionKey="id" from="${University.list()}"/>
					</p>
					
					<p>
						<label>Categoria</label>
						<g:select name="category"  optionKey="id" from="${Category.list()}"/>
					</p>
					
					<p>
						<label>Resultado</label>
						<g:textField class="text" name="result"/>
					</p>
					
					<p>
						<label>Resumen</label>
						<br>
						<g:textArea name="summary" style="width: 1348px; height: 167px;"></g:textArea>
					</p>
					<p>
						<label>Año</label>
						<g:datePicker name="year" value="${new Date()}" precision="year" years="${1900..2100}"/>
					</p>
					<input  class="evt-properties" type="submit" value="Almacenar" class="confirm button">
				</fieldset>
			</g:form>
		</div>
	</div>
</div>
</div>

<div id="listRaeDiv" style="display:none;">
<div  class="box grid_16 alfa omega">
	<h2><a id="toggle-search" href="#" >Lista de Raes</a></h2>
	
	<div id ="raeDiv">
	
	
	
	</div>
	
</div>
</div>
