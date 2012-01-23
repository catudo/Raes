package raes

class University {
	String name
	String description 

	
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
