package raes

class KeyWord {
	
	String name
	static hasMany =[raes:Rae]
	static belongsTo = Rae

    static constraints = {
    }
	
	String toString(){
		return name
	}
}
