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
	static belongsTo =[university:University,category:Category]
	static hasMany = [authors:Author,keyWords:KeyWord,tools:Tools]
	//categories:Category
    static constraints = {
		authors nullable:true
		keyWords nullable:true
		category nullable:true
		university nullable:true
		tools nullable:true
		
		
    }
	
	static mapping = {
		summary type: 'text'
		
		
	}
	
	String toString(){
		return 'Rae-'+id+"-"+year
	}
	
}
