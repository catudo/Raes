package raes

class Author {
	
	String name

	static hasMany =[raes:Rae]
    
	static belongsTo = Rae
	static constraints = {
    }
}
