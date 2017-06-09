//internal val fitnessFns: Collection<(Collection<Number>) -> Number> = ArrayList()
fun main(args: Array<String>){

	
	val one: ArrayList<Double> = ArrayList()
	val two: ArrayList<Double> = ArrayList()
	for(i in 0..3){
		one.add(3.0)
		two.add(2.0)
	}
	
	println(dominates(two, one))
}
	
fun dominates(a: Collection<Number>, b: Collection<Number>): Boolean{
	val dominates: ArrayList<Boolean> = ArrayList(a.size)
	for(i in 0..a.size-2){
		if(a.elementAt(i) as Double >= b.elementAt(i) as Double && a.elementAt(i+1) as Double >= b.elementAt(i+1) as Double){
			if(a.elementAt(i) as Double > b.elementAt(i) as Double ||a.elementAt(i+1) as Double >= b.elementAt(i+1) as Double){
				dominates.add(i, true)
			}
		}
		else{
			dominates.add(i, false)
		}		
	}
	for(i in 0..dominates.size-1){
		if (dominates.elementAt(i))
			continue
		else
			return false
	}
	return true
}