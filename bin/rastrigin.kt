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
		
		val newPopulation : ArrayList<ArrayList<Number>> = ArrayList()
		
		var newItem: ArrayList<Number>
		while(newPopulation.size < col.size){
			newItem = ArrayList()
			val a = col.elementAt(rand.nextInt(col.size))
			val b = col.elementAt(rand.nextInt(col.size))
			System.out.println(a.toString())
			val k = 2 + rand.nextInt((a.size-2) +1) 
			System.err.println(k)
			
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
	
	fun mutation(a: Collection<Number>): Collection<Number>{
		
		var newSol: ArrayList<Number> = ArrayList()
		var i = 0
		for(i in 0..a.size-1){
			newSol = ArrayList()
			val m = a.elementAt(i)
			newSol.add((m as Double) + (rand.nextGaussian() * 0.001))
			
			//newPopulation.add(newSol)
		}
		return newSol
	}
	val myCollection: ArrayList<ArrayList<Number>> = ArrayList()
	
	//initlialise population
	val n = 7
	var newSol: ArrayList<Number> = ArrayList()
	while(myCollection.size != 50){
		newSol = ArrayList()
		for(i in 0..n){
			newSol.add(rand.nextDouble())
		}
		myCollection.add(newSol)
	}
	val myGA = GA(myCollection, ::fitness, ::crossover, ::mutation, 4)
	myGA.run(100, false)
	
}

 