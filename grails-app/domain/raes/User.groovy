package raes

class User {

	

	String username
	String passwd
	String names
	String lastName 
	String email
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
		username blank: false, unique: true
		passwd blank: false
	}

	static mapping = {
		passwd column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}
	
}
