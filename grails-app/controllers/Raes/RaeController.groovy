package Raes
import grails.converters.JSON

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

import raes.Author
import raes.Category
import raes.Rae;
import raes.University
import raes.User
class RaeController {
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	static layout='main'
	def springSecurityService
	
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
	
	
	def saveRae ={
		def raeId = params.raeId


		def properties =[year:(params.year.getYear()+1900),
					summary:params.summary,
					methodology:params.methodology,
					result:params.result,
					topographicalNumber:params.topographicalNumber,
					city:params.city,
					name:params.name,
					analyst:params.analyst,
					university:University.get(params.university),
					keyWords:params.keyWords,
					category:Category.get(params.category)
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

		def rae

		if(raeId.equals("")){
			rae = new Rae(properties)
		}else{
			rae = Rae.get(raeId)
			properties.each{key, value ->
				
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
			[ "sTitle": "Nombre" ],
			["sTitle": "Metodologia" ],
			["sTitle": "Año" ],
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
	
	
		raes.each{rae->
			def row=[]
			row.add(rae.name)
			row.add(rae.methodology)
			row.add(rae.year)
			row.add(rae.result)
			row.add(rae.topographicalNumber)
			row.add(rae.city)
			row.add(rae.analyst)
			row.add(rae.university.name)
			row.add(rae.keyWords)
			row.add(rae.category.name)
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
		rae.save(flush:true)
		rae.delete(flush:true)
		render rae as JSON
		
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
			"analyst",
			"keyWords"
		]
		
		
		def raeObject =[:]
		
		properties.each{property->
			raeObject.putAt(property, rae.getAt(property))
		}
		
		raeObject.putAt("university", rae.university.name)
		
		
		def authors = rae.authors
		
		def authorsNameList =[]
		
		authors.each{author->
			authorsNameList.add(author.name)
			
		}
		
		raeObject.putAt("author", authorsNameList)
		
		
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
		raeHash.putAt("category", rae.category.name)
		raeHash.putAt("university", rae.university.name)
		
		
		report.add(raeHash)
		
		chain(controller:'jasper',action:'index',model:[data:report],params:params)
		
	}
}
