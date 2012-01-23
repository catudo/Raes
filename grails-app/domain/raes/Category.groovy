package raes

class Category {

	String name
	String description 
	
	//static hasMany = [raes:Rae]
	//static belongsTo = Rae
	static mapping = {
		description type: 'text'
	}
	
    static constraints = {
    	name unique:true
	}
	
	
	String toString(){
		return name;
	}
}
