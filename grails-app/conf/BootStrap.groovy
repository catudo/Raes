import org.codehaus.groovy.grails.commons.ApplicationHolder

import raes.Role
import raes.User
import raes.UserRole


class BootStrap {

	def init = { servletContext ->
		/*
		def admin_role = Role.findById(1);
		(!admin_role) ? admin_role = new Role(authority:'ROLE_ADMIN').save(flush:true) : null;
		def user_role = Role.findById(2);
		(!user_role) ? user_role = new Role(authority:'ROLE_USER').save(flush:true) : null;
		def testUser = User.findByUsername('admin')
		if(!testUser){
			def ctx = ApplicationHolder.getApplication().getMainContext();
			def springSecurityService = ctx.getBean("springSecurityService");

			testUser = new User(
					username:'admin',
					identification:'0',
					passwd: springSecurityService.encodePassword('root'),
					names:'names',
					lastName:'admin',
					email:'admin@admin.com',
					enabled:true,
					accountExpired:false,
					accountLocked:false,
					passwordExpired:false
					)
			 testUser.save(flush: true)
			
			UserRole.create(testUser,Role.get(1))
		}
		*/
		
	}
	def destroy = {
	}
}
