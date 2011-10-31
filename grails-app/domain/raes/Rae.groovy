package raes

class Rae {
	int year
	String summary
	String methodology 
	String result
	String  topographicalNumber
	String city
	String name
	String analyst
	String keyWords
	static belongsTo =[unversity:University]
	static hasMany = [categories:Category,authors:Author]
	
    static constraints = {
    }
	
	static mapping = {
		summary type: 'text'
		
		
	}
}
