package raes

class Event {
	static belongsTo =[accessLog: AccessLog]
	String eventName
	Date admissionDate
	String domainName
	long domainId
	String beforeUpdateAttribute
	String AfterUpdateAttribute
	static mapping = {
		accessLog fetch:'join'
	}

	static constraints = {
		beforeUpdateAttribute nullable:true
	}
	
	
}
