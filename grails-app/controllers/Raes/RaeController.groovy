package Raes
import grails.converters.JSON

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile

import raes.Author
import raes.Category
import raes.KeyWord
import raes.Rae
import raes.Tools;
import raes.University
import raes.User
class RaeController {
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	static layout='main'
	def springSecurityService
	def superFileUploadService
	def accessLogService

    def index = { 
		def sessionUser = springSecurityService.getCurrentUser()?.id;
		def server = CH.config.grails.serverURL
		if(sessionUser ==null)
			redirect(url:CH.config.grails.serverURL+"/login/auth")
		else{
			def accessLog =  accessLogService.createAccessLog(sessionUser, "/rae/index")
			[accessLog:accessLog,server:server, user:User.get(sessionUser)]
		}
		
		
	}
	
	
	
	def saveCategory={
		
		def category = new Category(params)
		
		if(!params.name.equals("")){
		category.save(flush:true)
		
		render category as JSON 
		}else{
		
		render [:] as JSON 
		}
	}
	
	
	def saveUniversity={
		if(!params.name.equals("")){
		def university = new University(params)
		university.save(flush:true)
		
		render  university  as JSON
		}else{
			render [:] as JSON
		}
	
	}
	
	def saveAuthor={
		if(!params.name.equals("")){
		def author = new Author(params)
		author.save(flush:true)
		render  author  as JSON
		}else{
		
		render [:] as JSON 
		}
	
	}
	
	def saveFile={
		try{
		def f = request.getFile('userfile')
		def server = CH.config.grails.serverURL
		if(!f.empty) {
			  f.transferTo( new File("${servletContext.getRealPath('/raeTemp')}/"+f.getOriginalFilename() ))
		  //response.sendError(200,'Done');
		}
		
		
		def sessionUser = springSecurityService.getCurrentUser()?.id;
		def accessLog =  accessLogService.createAccessLog(sessionUser, "/rae/index")
		
		
		
		}catch(Exception e){
		redirect(uri:"/rae/index") 
		}
	}
	
	
	
	def saveRae ={
		
		def raeId =params.raeId
		def properties =[
					summary:params.summary,
					methodology:params.methodology,
					result:params.result,
					topographicalNumber:params.topographicalNumber,
					city:params.city,
					name:params.name,
					analyst:params.analyst,
					university:University.get((params.university?.isNumber())?params.university:0),
					category:Category.get((params.category?.isNumber())?params.category:0)
					]

		def authorsNames = request.getParameterValues("author");

		def authorList = []
		authorsNames.each{authorName->
			def author = Author.findByName(authorName)

			if(author==null){
				author = new Author(name:authorName)
				if(!authorName.equals(""))
				author.save(flush:true)
			}

			authorList.add(author)
		}

		properties.put("authors", authorList)
		
		def keyWordsNames = request.getParameterValues("keyword");
		
		def keyWords = []
		keyWordsNames.each{word->
			def keyWord = KeyWord.findByName(word)

			if(keyWord==null){
				keyWord = new KeyWord(name:word)
				if(!word.equals(""))
				keyWord.save(flush:true)
			}

			keyWords.add(keyWord)
		}

		properties.put("keyWords", keyWords)
		
		
		def toolsNames = request.getParameterValues("tool");
		
		def tools = []
		toolsNames.each{toolName->
			def toolInstace = Tools.findByName(toolName)

			if(toolInstace==null){
				toolInstace = new Tools(name:toolName)
				if(!toolName.equals(""))
				toolInstace.save(flush:true)
			}

			tools.add(toolInstace)
		}

		properties.put("tools", tools)

		def rae = Rae.get(raeId)
		
		
		if(rae==null){
			properties.putAt('year', params.year)
			rae = new Rae(properties)
		}else{
		properties.putAt('year', rae.year)
			properties.each{key, value ->
				if(key.equals('year'))
				rae.putAt(key, value.toInteger())
				else
				rae.putAt(key, value)
				
			}
		}
		
		
		
		
		if(rae.save(flush:true)){
			
			render rae as JSON
			
			
		}else{
		
		render rae.errors as JSON
		}
		
	}
	
	
	def listRaes={
		def raes = Rae.list()
		List finalData = new ArrayList ();
		def columns = [
			[ "sTitle": "Ficha" ],
			[ "sTitle": "Nombre" ],
			["sTitle": "Metodologia" ],
			["sTitle": "A&ntilde;o" ],
			[ "sTitle": "Resultado" ],
			[ "sTitle": "Numero Topografico" ],
			[ "sTitle": "Ciudad"],
			[ "sTitle": "Analista"],
			[ "sTitle": "Universidad"],
			[ "sTitle": "Palabras Claves"],
			[ "sTitle": "Categoria"],
			["sTitle": "Autores" ],
			[ "sTitle": ""],
			[ "sTitle": ""],
			[ "sTitle": ""],

		];
	
		def server = CH.config.grails.serverURL
	
		
		def folder = server+"/raeTemp/"
		
		raes.each{rae->
			def row=[]
			
			def file = new File("${servletContext.getRealPath('/raeTemp')}/"+"rae-"+rae.id+"-"+rae.year+".docx")
			
			if(!file.exists())
			row.add("rae-"+rae.id+"-"+rae.year)
			else
			row.add('<a  href="#" class="linkFile" link='+folder+'rae-'+rae.id+"-"+rae.year+".docx"+'>'+"rae-"+rae.id+"-"+rae.year +'</a>')	
			
			row.add(rae.name)
			row.add(rae.methodology)
			row.add(rae.year)
			row.add(rae.result)
			row.add(rae.topographicalNumber)
			row.add(rae.city)
			row.add(rae.analyst)
			row.add(rae.university?.name)
			row.add(rae.keyWords.join(","))
			row.add(rae.category?.name)
			row.add(rae.authors.join(","))
			row.add("<a class='editRae' raeId="+rae.id+">Editar<a>")
			row.add(g.render(template: '/rae/importPdf',model:[raeId:rae.id]))
			row.add("<a class='deleteRae' raeId="+rae.id+">Eliminar<a>")
			finalData.add(row)
			
		}
		
		HashMap json_response= new HashMap();
		json_response.put("data",finalData);
		json_response.put("columns",columns);
		
		render json_response as JSON
		
	}
	
	
	def deleteRae={
		def rae = Rae.get(params.raeId)
		rae.authors = null
		rae.keyWords = null
		rae.save(flush:true)
		rae.delete(flush:true)
		render rae as JSON
		
	}
	
	
	def showForm={
		def universities = University.list()
		def categories = Category.list()
		def server = CH.config.grails.serverURL
		[categories:categories , universities:universities,server:server]
		
	}
	
	def showRae={
		def rae = Rae.get(params.raeId)
		
		def properties =[
			"name",
			"year",
			"summary",
			"methodology",
			"result",
			"topographicalNumber",
			"city",
			"analyst"
		]
		
		
		def raeObject =[:]
		
		properties.each{property->
			raeObject.putAt(property, rae.getAt(property))
		}
		
		raeObject.putAt("university", rae.university?.name)
		
		
		def authors = rae.authors
		
		def authorsNameList =[]
		
		authors.each{author->
			authorsNameList.add(author.name)
			
		}
		
		
		raeObject.putAt("author", authorsNameList)
		
		def keyWords = rae.keyWords
		
		def keyWordsList =[]
		
		keyWords.each{
			keyWordsList.add(it.name)
		}
		
		
		
		raeObject.putAt("keyWords", keyWordsList)
		
		
		
		def tools = rae.tools
		
		def toolsList =[]
		
		tools.each{
			toolsList.add(it.name)
		}
		
		
		
		raeObject.putAt("tools", toolsList)
		
		render raeObject as JSON
		
	}
	
	def generatePdf={
		def rae = Rae.get(params.raeId)
		
		
		def properties =[
			"name",
			"year",
			"methodology",
			"result",
			"topographicalNumber",
			"city",
			"analyst",
			"keyWords",
			"summary",
		]
		
		
		
		def report = []
		
		def raeHash = [:] 
		
		properties.each{property->
			raeHash.putAt(property, rae.getAt(property))
		}
		def auth =  rae.authors.join(",")
		raeHash.putAt("authors", auth)
		raeHash.putAt("category", rae.category?.name)
		raeHash.putAt("university", rae.university?.name)
		
		
		report.add(raeHash)
		
		chain(controller:'jasper',action:'index',model:[data:report],params:params)
		
	}
	
	
	def atributtesReport={
		def report=[]
		
		def universities = University.list()
		def categories = Category.list()
		
		
	universities.each{
		def tuple=[:]
		tuple.putAt("un", "un")
		tuple.putAt("universityName", it.name)
		tuple.putAt("universityDescription", it.description)
		report.add(tuple)
		}
	
	
	universities.each{
		def tuple=[:]
		tuple.putAt("at", "at")
		tuple.putAt("categoryName", it.name)
		tuple.putAt("categoryDescription", it.description)
		report.add(tuple)
		}
	
	chain(controller:'jasper',action:'index',model:[data:report],params:params)
		
	}
	
	def listUniversities={
		def universities = University.list()
		List finalData = new ArrayList ();
		def columns = [
			[ "sTitle": "Nombre" ],
			["sTitle": "Descripcion" ],
			["sTitle": "" ]

		];
	
	
		universities.each{university->
			def row=[]
			row.add(university.name)
			row.add(university.description)
			row.add("<a class='deleteProperty deleteUniversity' universityId="+university.id+">Eliminar<a>")
			finalData.add(row)
			
		}
		
		HashMap json_response= new HashMap();
		json_response.put("data",finalData);
		json_response.put("columns",columns);
		
		render json_response as JSON
		
		
	}
	
	def listCategories={
		
		def categories = Category.list()
		List finalData = new ArrayList ();
		def columns = [
			[ "sTitle": "Nombre" ],
			["sTitle": "Descripcion" ],
			["sTitle": "" ]

		];
	
	
		categories.each{category->
			def row=[]
			row.add(category.name)
			row.add(category.description)
			row.add("<a class='deleteProperty deleteCategory' categoryId="+category.id+">Eliminar<a>")
			finalData.add(row)
			
		}
		
		HashMap json_response= new HashMap();
		json_response.put("data",finalData);
		json_response.put("columns",columns);
		
		render json_response as JSON
		
		
		
	}
	
	def deleteUniversity={
		def university = University.get(params.id)
		
		
		def raes = Rae.findAllByUniversity(university)
		
		if(raes.size()==0){
			university.delete(flush: true)
			render university as JSON 
		}else{
		
			university.errors.rejectValue("name","university.Constraint");

				render university.errors as JSON
		}
		
		
	}
	
	
	def deleteCategory={
		
		def category = Category.get(params.id)
	
		def raes = Rae.findAllByCategory(category)
		
		if(raes.size()==0){
			category.delete(flush: true)
			render category as JSON
		}
		else{
			category.errors.rejectValue("name","category.constraint");

				render category.errors as JSON

		}

		
	}
	
	
	
}
