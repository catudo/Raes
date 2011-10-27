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
			def accessLog =  accessLogService.createAccessLog(sessionUser.id, "/user/index")
			[accessLog:accessLog,server:server]
		}
	}

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def save = {
		def accessLogInstance = AccessLog.get(params.accessLog)
		params.remove("accessLog")
		def role = Role.get(params.role)
		params.remove("role")
		
		def userInstance = User.findBy(params.userId)
		params.remove("userId")
		
		
		if(userInstance)
		def userBeforeUpdate = userInstance
        
		if(params.passwd.equals("") || !params.passwd){
			params.passwd =userInstance.passwd ;
		}else {
			params.passwd = springSecurityService.encodePassword(params.passwd);
		}
		
		if(userInstance)
		userInstance = new User(params)
		
		
		if (userInstance.save(flush: true)) {
			def userRole = UserRole.findByUserAndRole(userInstance,role) 
			if(userRole)
			UserRole.create (userInstance, role)
			def event = new Event(eventName:"save",accessLog:accessLogInstance,admissionDate:new Date(),"User",domainId:userInstance.id,beforeUpdateAttribute:userBeforeUpdate?.encodeAsJSON(),AfterUpdateAttribute:userInstance.encodeAsJSON())
			render userInstance as JSON
        }
        
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
