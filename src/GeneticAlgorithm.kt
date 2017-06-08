/**
 * Created by Brendan on 6/7/17.
 *
 */
import java.util.*

private val RANDOM = Random()

fun main(Args: Array<String>){
    print("hi")
	println("Sam says hi too")


    /**
     * SAMSSSSSSSSSSSSSSSSSSSS
     */
	
	fun tournamentMaximise(pop: List<Any>, K: Int, fitFn: (v : Any) -> Int): Any{
		//get a random solution from population
		var winner = pop.get(RANDOM.nextInt(pop.size))
		//for the duration of the tournament
		for(k in 2..K){
			//pick random contender from population
			var contender = pop.get(RANDOM.nextInt(pop.size))
			//if the contender is more fit then it becomes the winner
			if(fitFn(winner) < fitFn(contender)){
				winner = contender
			}
		}
		//the winner is returned to the main algorithm
		return winner
	}
	
	fun tournamentMinimise(pop: List<Any>, K: Int, fitFn: (v : Any) -> Int): Any{
		//get a random solution from population
		var winner = pop.get(RANDOM.nextInt(pop.size))
		//for the duration of the tournament
		for(k in 2..K){
			//pick random contender from population
			var contender = pop.get(RANDOM.nextInt(pop.size))
			//if the contender is more fit then it becomes the winner
			if(fitFn(winner) > fitFn(contender)){
				winner = contender
			}
		}
		//the winner is returned to the main algorithm
		return winner
	}
	

	fun baseGA(population: ArrayList<Any>, fitFn: (v : Any) -> Int, mutate: (o: Any) -> Any, k: Int , crossover: (m: Any, n: Any) -> Any) {
		//lists to store various Collections
		var newPopulation: MutableList<Any> = population
		var fitnesses: MutableList<Int> = ArrayList<Int>()
		var tmpPopulation: MutableList<Any> = ArrayList<Any>()
		var tmpFitnesses: MutableList<Int> = ArrayList<Int>()
		population.forEach({o:Any -> fitnesses.add(fitFn(o))})
		
		
		//var max: Int = fitnesses.max()
		
		//while(max.compareTo(16) != 0){
		for(count in 1..100000){
			//initilase the selected population using tournament selection
			for(i in 1..newPopulation.size){
				tmpPopulation.add(tournamentMaximise(newPopulation, k, fitFn))
			}
			//mutate two random solutions from population and perform crossover, calculate new fitnesses
			for(i in 1..newPopulation.size){
				
				var a = mutate(newPopulation.get(RANDOM.nextInt(newPopulation.size)))
				var b = mutate(newPopulation.get(RANDOM.nextInt(newPopulation.size)))
				var c = crossover(a, b)
				tmpPopulation.set(i, c)
				tmpFitnesses.set(i, fitFn(c))	
			}
			//set the new populations and fitnesses
			newPopulation = tmpPopulation
			fitnesses = tmpFitnesses
		}
		
		var maxIndex: Int = fitnesses.indexOf(fitnesses.max())
		//print the best Solution
		println(newPopulation.get(maxIndex))
	}

	
	
	//********TEST SAMS CODE HERE********//

	fun fit(s:String): Int{
		var sArr = s.toCharArray()
		var total: Int = 0
		
		for(c in sArr){
			if(s.equals('1')){
				total++
			}
			
		}
		
		return total
	}
	fun mutate(s: String): String{
		var sArr = s.toCharArray()
		for(i in 0..(sArr.size-1)){
			if(RANDOM.nextDouble() <= 1/s.length){
				if(sArr[i].equals('1')){
					sArr[i] = '0'
				}
				else{
					sArr[i] = '1'
				}
			}
		}
		return sArr.contentToString()
	}
	
	fun onePointCrossover(a: String, b: String): String{
		var aLen: Int = a.length
		//var bLen: Int = b.length
		//var diff: Int = aLen - bLen
		var cutPoint: Int = RANDOM.nextInt(aLen)/aLen//if(diff > -1) RANDOM.nextInt(aLen)/aLen else RANDOM.nextInt(bLen)/bLen
		
		
		return a.substring(0, cutPoint-1) + b.substring(cutPoint, aLen)
	}

	
	var pop: ArrayList<String> = ArrayList<String>()
	for(c in 1..10){
		var str: String = ""
		for(d in 1..16){
			str += RANDOM.nextInt(2).toString()		
		}
		pop.add(str)
		str = ""
	}
	
	// need to sort generics so this works. 
	baseGA(pop, fit, mutate, 8, onePointCrossover)
	
	
	
	
	
	//***********************************//







    /**
     * BRENDANSSSSSSSSSSSSSSSSSS
     */

    //Look at the other two files

}
