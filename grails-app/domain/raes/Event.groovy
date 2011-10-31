package raes

class Event {
	static belongsTo =[accessLog: AccessLog]
	String eventName
	Date admissionDate
	String domainName
	long domainId
	String beforeUpdateAttribute
	String afterUpdateAttribute
	static mapping = {
		accessLog fetch:'join'
		beforeUpdateAttribute type: 'text'
		afterUpdateAttribute type: 'text'
		
		
		
	}		
	

	static constraints = {
		beforeUpdateAttribute nullable:true
	}
	
	
}
