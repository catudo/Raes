package Raes
import grails.converters.JSON

import java.util.Date

import org.codehaus.groovy.grails.commons.ConfigurationHolder as CH

import raes.AccessLog
import raes.Event
import raes.Role
import raes.User
import raes.UserRole
class UserController {

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
			def accessLog =  accessLogService.createAccessLog(sessionUser, "/user/index")
			[accessLog:accessLog,server:server]
		}
	}

    def list = {
        def userlist = User.list()
		
		List finalData = new ArrayList ();
		def columns = [
		 [ "sTitle": "Nombres" ,"sWidth": "1800px"],
		 [ "sTitle": "Apellidos" ,"sWidth": "180px"],
		 [ "sTitle": "email" ,"sWidth": "180px"],
		 [ "sTitle": "Usuario" ,"sWidth": "180px"],
		 [ "sTitle": "Estado" ,"sWidth": "180px"],
		 [ "sTitle": "" ,"sWidth": "180px"],
		 [ "sTitle": "" ,"sWidth": "180px"]
		];
		
		
		userlist.each{user->
			def row=[]
			row.add(user.names)
			row.add(user.lastName)
			row.add(user.email)
			row.add(user.username)
			row.add((user.enabled)?"Habilitado":"Desahabilitado")
			row.add("<a>Editar<a>")
			row.add("<a>Cambiar Estado<a>")
			finalData.add(row)
			
		}
		
		HashMap json_response= new HashMap();
		json_response.put("data",finalData);
		json_response.put("columns",columns);
		
		render json_response as JSON
		
    }

    def save = {
		def accessLogInstance = AccessLog.get(params.accessLog)
		params.remove("accessLog")
		def role = Role.get(params.role)
		params.remove("role")
		println params
		def userInstance = User.get(params.userId)
		params.remove("userId")
		
		def userBeforeUpdate
		if(userInstance){
		userBeforeUpdate = userInstance
		UserRole.remove userInstance, userInstance.role
		}
		if(params.passwd.equals("") || !params.passwd){
			params.passwd =userInstance.passwd ;
		}else {
			params.passwd = springSecurityService.encodePassword(params.passwd);
		}
		
		if(!userInstance){
		userInstance = new User(params)
		
		}
		
		if (userInstance.save(flush: true)) {
			def userRole = UserRole.findByUserAndRole(userInstance,role) 
			if(userRole)
			UserRole.create (userInstance, role)
			def userJson = userBeforeUpdate?.encodeAsJSON()
			
			def event = new Event(eventName:"save",accessLog:accessLogInstance,admissionDate:new Date(),domainName:"User",domainId:userInstance.id,beforeUpdateAttribute:userJson,AfterUpdateAttribute:userInstance.encodeAsJSON())
			event.save(flush:true)
        }
		render userInstance as JSON
    }

    def show = {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
            [userInstance: userInstance]
        }
    }

    def edit = {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [userInstance: userInstance]
        }
    }

    def update = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userInstance.version > version) {
                    
                    userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
                    render(view: "edit", model: [userInstance: userInstance])
                    return
                }
            }
            userInstance.properties = params
            if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
                redirect(action: "show", id: userInstance.id)
            }
            else {
                render(view: "edit", model: [userInstance: userInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            try {
                userInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }
}
