package raes

class AccessLogService {

    static transactional = false

    def createAccessLog(def userId, def generalController) {
		def user = User.get(userId)
		def accessLog = AccessLog.findByUserAndGeneralController(user, generalController)
		
		if(!accessLog){
			accessLog = new AccessLog(user:user, generalController:generalController)
			accessLog.save(flush:true)
			
		}
		
		return accessLog
    }
	
	

	
	
}
