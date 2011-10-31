package Raes
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

import raes.User



class QueriesController {
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	static layout='main'
	def springSecurityService

    def index = { 
		
		def sessionUser = springSecurityService.getCurrentUser()?.id;
		def server = CH.config.grails.serverURL
		if(sessionUser ==null)
			redirect(url:CH.config.grails.serverURL+"/login/auth")
		else{
			//def accessLog =  accessLogService.createAccessLog(sessionUser, "/user/index")
			//[accessLog:accessLog,server:server, user:User.get(sessionUser)]
			[user:User.get(sessionUser)]
		}
		
		
	}
}
