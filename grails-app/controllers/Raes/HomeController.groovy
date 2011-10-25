package Raes

import raes.User

class HomeController {

	static layout='main'
	def springSecurityService
	def accessLogService	
    def index = { }
	
	def admin = { 
	
	def sessionUser = springSecurityService.getCurrentUser()?.id;
	if(sessionUser ==null)
		redirect(url:CH.config.grails.serverURL+"/login/auth")
		else{
			def accessLog =  accessLogService.createAccessLog(sessionUser, "/user/index")
			[accessLog:accessLog,user:User.get(sessionUser)]
		}
		
	}
}
