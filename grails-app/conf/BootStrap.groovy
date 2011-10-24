import raes.Role


class BootStrap {

    def init = { servletContext ->
		
		def admin_role = Role.findById(1);
		(!admin_role) ? admin_role = new Role(authority:'ROLE_ADMIN').save(flush:true) : null;

		def user_role = Role.findById(2);
		(!user_role) ? user_role = new Role(authority:'ROLE_USER').save(flush:true) : null;
		
    }
    def destroy = {
    }
}
