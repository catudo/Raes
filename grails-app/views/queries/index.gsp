<%@page import="raes.University"%>
<%@page import="raes.Category"%>
<g:javascript src="raes.queries.js" />

<div style="padding:0; margin-top:10px " class="box grid_16">
	<h2 style="margin:0"><a id="toggle-grid" style="background-position: 99.3% 50%; cursor: pointer;" href="#" class="visible">Parametros</a></h2>
	
	<div id="divForm"  class="block" style="margin: 0px;">
						<g:form name="queryForm" action="">
							<fieldset class="login">
								<legend>Parametros de Consulta</legend>
								
								<div class="grid_5">
								
								<p>
									<label>Nombre </label>
									<g:textField name="name"/>
									
								</p>
								<p>
									<label>Numero Asignado </label>
									<g:textField name="topographicalNumber"/>
									
								</p>
								
								<p>
									<label>Categoria</label>
									<g:select class="select" name="category" optionKey="id" from="${Category.list()}"   noSelection="${['null':'']}"/>
								</p>
								
							
							</div>
							<div class="grid_5">
								<p>
									<label>Año</label>
									<% def currentYear = new Date().getYear()+1900
									
									def years = []
									
									
									for(int i = 2005; i<=2100; i++)
									years.add(i)
									
									%>
									
									<g:select class="select" name="year"  value=''  from="${years}"  noSelection="${['':'']}"/>
									
								</p>
								<p>
									<label>Palabras Claves </label>
									<g:textField name="keyWords"/>
									
								</p>
								
								<p>
									<label>Herramientas </label>
									<g:textField name="tool"/>
									
								</p>
							
							
							</div>
							
							
							<div class="grid_6">
							
							<p>
									<label>Autor </label>
									<input type="text" name="authors">
								</p>
								<p>
									<label>Universidad</label>
									<g:select class="select" name="university" optionKey="id" from="${University.list()}"   noSelection="${['null':'']}"/>
								</p>
								
								
							
							<input type="submit" value="Buscar" class="button">
							</div>
							
							
							</fieldset>
						</g:form>
						
					</div>
</div>
<div style="padding:0;" class="grid_16">
<div id="raeDiv">

</div>
</div>