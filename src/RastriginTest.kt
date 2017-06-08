/**
 *Created 9/062017 00:05
 *
 */
import java.util.Random

private val rand = Random()
fun main(args: Array<String>){
	
	fun formula(d: Double): Double{
	//((x[i] * x[i]) - (10 * Math.cos(2 * Math.PI * x[i])));
		return (d * d) - (10 * Math.cos(2 * Math.PI * d))		
	}
	
	fun fitness(a: Collection<Number>): Number{
		var result: Double = 10.0 * a.size
		var newSol: ArrayList<String> = ArrayList()
		for(d in a){
			result += formula(d as Double)
					
		}
		return result
	}
	

	
	fun crossover(col: Collection<Collection<Number>>): Collection<Collection<Number>>{
		var k = 1 + rand.nextInt(col.size-2)
		var newPopulation : ArrayList<ArrayList<Number>> = ArrayList()
		
		var newItem = ArrayList<Number>()
		while(newPopulation.size < col.size){
			newItem = ArrayList()
			val a = col.elementAt(rand.nextInt(col.size))
			val b = col.elementAt(rand.nextInt(col.size))
			
			val k = 1 + rand.nextInt(col.size-2)
			
			(0..k-1).mapTo(newItem) { a.elementAt(it)}
			(k..a.size-1).mapTo(newItem) { b.elementAt(it)}
			
			newPopulation.add(newItem)
		}
		

		return newPopulation
	}
	
	fun clamp(d: Double): Double{
		if(d < -5.12){
			return -5.12
		}
		else if( d > 5.12){
			return 5.12
		}
		else{
			return d	
		}
			
	}

	fun mutation(x: Collection<Number>) : Collection<Number> {
		val prob = 1/x.size
		val toReturn: ArrayList<Double> = ArrayList()

		for (i in x) {
			if (rand.nextDouble() < prob)
				toReturn.add(i.toDouble() + (rand.nextGaussian() * 0.01))
			else
				toReturn.add(i.toDouble())
		}
		return toReturn
	}
	val myCollection: ArrayList<ArrayList<Number>> = ArrayList()
	
	//initlialise population
	val n = 3
	var newSol: ArrayList<Number> = ArrayList()
	while(myCollection.size != 50){
		newSol = ArrayList()
		for(i in 0..n){
			newSol.add(rand.nextDouble())
		}
		myCollection.add(newSol)
	}
	val myGA = GA<Collection<Number>>(myCollection, ::fitness, ::crossover, ::mutation, 4)
	myGA.run(500, false)
	
}

 