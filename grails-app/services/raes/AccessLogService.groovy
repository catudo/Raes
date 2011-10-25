package raes

class AccessLogService {

    static transactional = false

    def createAccessLog(def userId, controller) {
		def user = User.get(userId)
		def accessLog = AccessLog.findByUserAndGeneralController(user, controller)
		if(accessLog){
			accessLog = new AccessLog(user:user, controller)
			accessLog.save(flush:true)
		}
		
		return accessLog
    }
	
	
	def createEvent(){
		
	}
	
	
	
}
