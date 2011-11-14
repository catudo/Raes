<meta name="layout" content="ajax">
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
						<g:textField class="text" name="keyword"/>
						<br>
						</br><a class="button evt-addKeyWords" href="#">Agregar Palabra Clave</a>
					</p>
					<p>
						<label>Universidad</label>
						<g:select name="university" optionKey="id" from="${universities}"/>
					</p>
					
					<p>
						<label>Categoria</label>
						<g:select name="category"  optionKey="id" from="${categories}"/>
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