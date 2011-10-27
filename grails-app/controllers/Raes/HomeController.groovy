package Raes

import raes.User
import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH
class HomeController {

	static layout='main'
	def springSecurityService
	def accessLogService	
    def index = { }
	
	def admin = { 
	
	def sessionUser = springSecurityService.getCurrentUser()?.id;
	def server = CH.config.grails.serverURL
	if(sessionUser ==null)
		redirect(url:CH.config.grails.serverURL+"/login/auth")
		else{
			def accessLog =  accessLogService.createAccessLog(sessionUser, "/user/index")
			[accessLog:accessLog,user:User.get(sessionUser),server:server]
		}
		
	}
}
