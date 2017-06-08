/**
 *Created 9/062017 00:05
 *
 */
import java.util.Random

private val rand = Random()
fun main(args: Array<String>) {

	val myCollection: ArrayList<ArrayList<Number>> = ArrayList()

	//initlialise population
	val n = 3
	var newSol: ArrayList<Number> = ArrayList()
	while (myCollection.size != 50) {
		newSol = ArrayList()
		for (i in 0..n) {
			newSol.add(rand.nextDouble())
		}
		myCollection.add(newSol)
	}

	runBasicGA(myCollection)

}




	fun formula(d: Double): Double{
	//((x[i] * x[i]) - (10 * Math.cos(2 * Math.PI * x[i])));
		return (d * d) - (10 * Math.cos(2 * Math.PI * d))		
	}
	
private	fun fitness(a: Collection<Number>): Number{
		var result: Double = 10.0 * a.size
		var newSol: ArrayList<String> = ArrayList()
		for(d in a){
			result += formula(d as Double)
					
		}
		return result
	}

private fun runBasicGA(population: Collection<Collection<Number>>) {
	
	fun crossover(col: Collection<Collection<Number>>): Collection<Collection<Number>>{
<<<<<<< HEAD:src/rastrigin.kt
		
		val newPopulation : ArrayList<ArrayList<Number>> = ArrayList()
		
		var newItem: ArrayList<Number>
		while(newPopulation.size < col.size){
			newItem = ArrayList()
			val a = col.elementAt(rand.nextInt(col.size))
			val b = col.elementAt(rand.nextInt(col.size))
			System.out.println(a.toString())
			val k = 2 + rand.nextInt((a.size-2) +1) 
			System.err.println(k)
			
=======
		var k = 1 + rand.nextInt(col.size-2)
		var newPopulation : ArrayList<ArrayList<Number>> = ArrayList()
		
		var newItem = ArrayList<Number>()
		while(newPopulation.size != col.size){
			newItem = ArrayList()
			val a = col.elementAt(rand.nextInt(col.size))
			val b = col.elementAt(rand.nextInt(col.size))
			
//			val k = 1 + rand.nextInt(col.size-2)

			val k = rand.nextInt((a.size - 2) + 1) + 2
>>>>>>> da1b92ee6808f2a2542f53d665b572c6d710ec6c:src/RastriginTest.kt
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
<<<<<<< HEAD:src/rastrigin.kt
	
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
=======

	fun mutation(x: Collection<Number>) : Collection<Number> {
		val prob = 1/x.size
		val toReturn: ArrayList<Double> = ArrayList()

		for (i in x) {
			if (rand.nextDouble() < prob)
				toReturn.add(i.toDouble() + (rand.nextGaussian() * 0.01))
			else
				toReturn.add(i.toDouble())
>>>>>>> da1b92ee6808f2a2542f53d665b572c6d710ec6c:src/RastriginTest.kt
		}
		return toReturn
	}
<<<<<<< HEAD:src/rastrigin.kt
	val myGA = GA(myCollection, ::fitness, ::crossover, ::mutation, 4)
	myGA.run(100, false)
	
=======

	GA(population, ::fitness, ::crossover, ::mutation, 4).run(500, false)
>>>>>>> da1b92ee6808f2a2542f53d665b572c6d710ec6c:src/RastriginTest.kt
}

 