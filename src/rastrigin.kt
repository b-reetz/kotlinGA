/**
 *Created 9/062017 00:05
 *
 */
import java.util.Random

val rand = Random()

fun main(args: Array<String>){
	
	fun formula(d: Double): Double{
	//((x[i] * x[i]) - (10 * Math.cos(2 * Math.PI * x[i])));
		return (d * d) - (10 * Math.cos(2 * Math.PI * d))		
	}
	
	fun fitness(a: Collection<Double>): Double{
		var result: Double = 10.0 * a.size
		var newSol: ArrayList<String> = ArrayList()
		for(d in a){
			result += formula(d)
					
		}
		return result
	}
	

	
	fun crossover(col: Collection<Collection<Number>>): Collection<Collection<Number>>{
		var k = 1 + rand.nextInt(a.size-2)
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
	
	fun mutation(a: Collection<Collection<Number>>): Collection<Collection<Number>>{
		val newPopulation: ArrayList<ArrayList<Number>> = ArrayList()
		var newSol: ArrayList<Number> = ArrayList()
		var i = 0
		for(i in 0..a.size-1){
			newSol = ArrayList()
			val m = a.elementAt(i)
			(0..m.size-1).mapTo(newSol) { (a.elementAt(it) + (rand.nextGaussian() * 0.001))}
			
			newPopulation.add(newSol)
		}
		return newPopulation
	}
	val myCollection = ArrayList<String>()
	val myGA = GA(myCollection, ::fitness, ::crossover, ::mutation, 4)
	
}

 