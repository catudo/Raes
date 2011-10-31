package Raes
import grails.converters.JSON

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

import raes.Author
import raes.Category
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
}
