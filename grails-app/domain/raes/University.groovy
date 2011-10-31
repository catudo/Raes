package raes

class University {
	String name
	String description 

	
	static mapping = {
		description type: 'text'
	}
	
    static constraints = {
    }
	
	
	String toString(){
		return name;
	}
	
	
}
