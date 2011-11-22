package Raes
import grails.converters.JSON

import org.apache.jasper.compiler.Node.ParamsAction;
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

import raes.Author;
import raes.KeyWord;
import raes.Rae
import raes.University;
import raes.User



class QueriesController {
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
			def accessLog =  accessLogService.createAccessLog(sessionUser, "/queries/index")
			[accessLog:accessLog  ,server:server, user:User.get(sessionUser)]
			
		}
		
		
	}
	
	
	
	def selectRaes={
		def properties =[
			"name",
			"year",
			"author",
			"topographicalNumber",
			"keyWords",
			"university",
		]
		
		

		def raes = Rae.createCriteria().list{

			or{
				if(params.university.isNumber()){
					university{

						eq('id',params.university.toLong())
					}
					
					join "University"
				}
				if(!params.topographicalNumber?.equals('')){	
				eq('id',params.topographicalNumber)
				}
				if(params.year.isNumber()){
				eq('year', params.year.toInteger())
				}
				
				if(params.category.isNumber()){
					category{
					eq('id', params.category.toLong())
					}
					join 'Category'
				}
				
				eq('name', params.name)
				
			}
			
			
			
			
		}
		
		
		
		if(!(params.authors.equals(''))){
			def authors = Author.findByName(params.authors)
			def raesAuthor = authors.raes
			raes.addAll(raesAuthor)
			
		}
		
		if(!(params.keyWords.equals(''))){
			def keyWords = KeyWord.findByName(params.keyWords)
			def raesKeyWords = keyWords.raes
			raes.addAll(raesKeyWords)
			
		}
		
		
		
		
		
		
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
			row.add(g.render(template: '/rae/importPdf',model:[raeId:rae.id]))
			finalData.add(row)
			
		}
		
		HashMap json_response= new HashMap();
		json_response.put("data",finalData);
		json_response.put("columns",columns);
		
		render json_response as JSON
		
		
	}
	
	def generatePdf={
		chain(controller:'rae',action:'generatePdf',params:params)
	}
	
}
