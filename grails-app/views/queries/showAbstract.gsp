<meta name="layout" content="ajax">
<fieldset class="login">
<legend>Abstract</legend>


<g:if test="${!(url.equals('')) && url !=null }" >
<b>Url:</b>${url}
<p></p>
</g:if>

<p>
${summary} 
</p>

<input type="submit" class="close"  value="Cerrar">

</fieldset>