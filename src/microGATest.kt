import java.util.Random

private val rand = Random()
private val D = 30
fun main(args: Array<String>){
	var funcs: ArrayList<(Collection<Double>) -> Double> = ArrayList()
	funcs.add(fit1)
	funcs.add(fit2)
	var pop: ArrayList<ArrayList<Double>> = ArrayList()
	
	for(i in 1..10){
		var tmp = ArrayList<Double>()
		for(j in 1..D){
		 	tmp.add(rand.nextDouble() * 10)
		}
		pop.add(tmp)
	}
	
	var myMicro = MicroGA(pop, 10, ::mutation, ::crossover, funcs).run(50, false)
	
	
}

fun mutation(col: ArrayList<Double>): ArrayList<Double>{
	var newCol :ArrayList<Double> = ArrayList()
	for(i in 0..col.size-1){
		newCol.add(col.elementAt(i) + rand.nextGaussian())
	}
	
	return newCol
}

fun crossover(a: Collection<ArrayList<Double>>): Collection<ArrayList<Double>>{
	var c :ArrayList<ArrayList<Double>> = ArrayList()
	while(c.size != a.size){
		var m = a.elementAt(rand.nextInt(a.size))
		var n = a.elementAt(rand.nextInt(a.size))
		var o: ArrayList<Double> = ArrayList()
		for(i in 0..4){
			o.add(m.get(i))
		}
		for(i in 5..m.size-1){
			o.add(m.get(i))
		}
		c.add(o)		
	}

	return c
}
//var function: (String) -> String = { temp -> temp+"-"
var fit1: (Collection<Double>)->Double = {it.elementAt(0)}
var fit2: (Collection<Double>) -> Double = {
	var g = (1 + it.elementAt(0)/(D-1)) - (it.sum() - it.elementAt(0) - it.elementAt(1))
	var h = 1 - Math.sqrt(Math.abs(it.elementAt(0)/g))
	g*h
}

