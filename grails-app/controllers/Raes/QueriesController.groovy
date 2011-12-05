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
	def sessionFactory

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
				
		
		List finalData = new ArrayList ();
		def columns = [
			
			[ "sTitle": "Nombre" ],
			["sTitle": "Autores" ],
			[ "sTitle": "Universidad"],
			["sTitle": "A&ntilde;o" ],
			//ficha
			[ "sTitle": "" ],
			//descripcion
			[ "sTitle": ""],
		
			//imprimir
			[ "sTitle": ""]


		];
	
	def server = CH.config.grails.serverURL
		def raes = searchCriteria(params)
		
		def folder = server+"/raeTemp/"
		
		raes.each{rae->
			def row=[]
			row.add(rae.name)
			row.add(rae.authors.join(","))
			row.add(rae.university?.name)
			row.add(rae.year)
			
			def file = new File("${servletContext.getRealPath('/raeTemp')}/"+"rae-"+rae.id+"-"+rae.year+".docx")
			
			if(!file.exists())
			row.add("No hay Ficha Asociada")
			else
			row.add('<a  href="#" class="linkFile" link='+folder+'rae-'+rae.id+"-"+rae.year+".docx"+'>'+"RAE"+'</a>')	
			
			row.add("<a href='#' class='showAbstract' raeId='"+rae.id+"'>Abstract</>")
			row.add(g.render(template: '/rae/importPdf',model:[raeId:rae.id]))
			
			
			finalData.add(row)
			
		}
		
		HashMap json_response= new HashMap();
		json_response.put("data",finalData);
		json_response.put("columns",columns);
		
		render json_response as JSON
		
		
	}
	
	/*
	 * 
	 * SELECT rae.* FROM `rae`.`rae` as rae 
inner join `rae`.`rae_key_words` as rkw on(rkw.rae_id = rae.id)
inner join `rae`.key_word as kw on (kw.id= rkw.key_word_id )
inner join rae.rae_authors as raeAuthor on(raeAuthor.rae_id = rae.id)
inner join rae.author as author on (author.id= raeAuthor.author_id )
inner join rae.rae_tools as raeTools on(raeTools.rae_id = rae.id)
inner join rae.tools as tool on (tool.id= raeTools.tools_id )

where rae.name = 'asdasda' 
or rae.id = 100  
or category.name = 'ppasdasdp'
or rae.year = 2100
or kw.name='ppp'
or author.name = 'jjj'
or university.id ='76'
or tool.name = 'ooo'
	 * 
	 * 
	 */
	
	def searchCriteria(params){
		def results = [];
		
		def where =''
		def joinSql=''
		
		where =" where rae.name like '"+params.name +"'" 
		
		if(params.university.isNumber()){
		where = where +''' or rae.university_id ='''+params.university
				
		}
		
		if(params.topographicalNumber.isNumber()){
			where = where +''' or rae.id = '''+params.topographicalNumber
		
		}
		
		if(params.category.isNumber()){
			where = where +''' or rae.category_id ='''+params.category
		}
		
		
		if(params.year.isNumber()){
		 where = where +''' or rae.year ='''+params.year
		}
		
		
		if(!(params.keyWords.equals(''))){
			joinSql=' inner join `rae`.`rae_key_words` as rkw on(rkw.rae_id = rae.id) inner join `rae`.key_word as kw on (kw.id= rkw.key_word_id )'
			where = where +'''or kw.name="'''+params.keyWords+'''"'''
		}
		
		if(!(params.authors.equals(''))){
			joinSql=joinSql+' inner join rae.rae_authors as raeAuthor on(raeAuthor.rae_id = rae.id) inner join rae.author as author on (author.id= raeAuthor.author_id )'
			where = where +''' or author.name like "%'''+params.authors+'''%"'''
		}
		
		if(!(params.tool.equals(''))){
			joinSql=joinSql+' inner join rae.rae_tools as raeTools on(raeTools.rae_id = rae.id)inner join rae.tools as tool on (tool.id= raeTools.tools_id )'
			where = where +''' or tool.name like "%'''+params.tool+'''%"'''
		}
		
		
		
		
				

		def sql = '''
			SELECT {rae.*} FROM `rae`.`rae` as rae  '''+joinSql+where
			
		println sql;
		def currentSession = sessionFactory.currentSession
		def queryProfile = currentSession.createSQLQuery(sql)

		queryProfile.addEntity("rae",Rae);
		results = queryProfile.list()

		return results
	}
	
	
	def showAbstract={
		def rae = Rae.get(params.raeId)	
		[summary: rae.summary]
		
	}
	
	def generatePdf={
		chain(controller:'rae',action:'generatePdf',params:params)
	}
	
}
