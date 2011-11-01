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
	static belongsTo =[university:University,category:Category]
	static hasMany = [authors:Author]
	//categories:Category
    static constraints = {
		authors nullable:true
    }
	
	static mapping = {
		summary type: 'text'
		
		
	}
}
