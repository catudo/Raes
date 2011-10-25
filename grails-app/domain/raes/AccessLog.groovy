package raes

class AccessLog {
	static belongsTo =[user: User]
	String generalController
	static mapping = {
		user fetch:'join'
	}

	
	
}
