/**
 * Created by Brendan on 6/7/17.
 *
 */
import java.util.Random

private val RANDOM = Random()

fun main(Args: Array<String>){
    print("hi")
	println("Sam says hi too")


    /**
     * SAMSSSSSSSSSSSSSSSSSSSS
     */
	
	fun tournamentMaximise(pop: List<String>, K: Int, fitFn: (v : String) -> Int): String{
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
	
	fun copy(s: String): String{
		var s = "";
		for(c in s.toCharArray()){
			s += c
		}
		return s
	}
	

	fun baseGA(population: ArrayList<String>, fitFn: (v : String) -> Int, mutate: (o: String) -> String, k: Int , crossover: (m: String, n: String) -> String) {
		//lists to store various Collections
		var newPopulation: MutableList<String> = population
		var fitnesses: MutableList<Int> = ArrayList<Int>()
		var tmpPopulation: MutableList<String> = ArrayList<String>()
		var tmpFitnesses: MutableList<Int> = ArrayList<Int>()
		//population.forEach({o:String -> fitnesses.add(fitFn(o.toString()))})
		
		
		//var max: Int = fitnesses.max()
		
		//while(max.compareTo(16) != 0){
		for(count in 1..10){
			//initilase the selected population using tournament selection
			for(i in 1..newPopulation.size){
				tmpPopulation.add(tournamentMaximise(newPopulation, k, fitFn))
			}
			//mutate two random solutions from population and perform crossover, calculate new fitnesses
			for(i in 0..newPopulation.size-1){
				
				var a = newPopulation.get(RANDOM.nextInt(newPopulation.size)) //was mutating
				var b = newPopulation.get(RANDOM.nextInt(newPopulation.size)) //was mutating
				var c = crossover(a, b)
				//System.err.println(c)
				tmpPopulation.set(i, c)
				//tmpFitnesses.add(fitFn(c))	
			}
			System.out.println(fitFn(tmpPopulation.get(0)))
			//set the new populations and fitnesses
			newPopulation = ArrayList<String>()
			newPopulation.addAll(tmpPopulation)
			tmpPopulation = ArrayList<String>()
			println(count)
		}
		fitnesses.clear()
		for(x in newPopulation){
			fitnesses.add(fitFn(x))
		}
		
		var maxIndex = fitnesses.indexOf(fitnesses.max())
		//print the best Solution
		System.out.println(fitnesses.max())
		System.out.println(newPopulation.get(maxIndex))
	}

	
	
	//********TEST SAMS CODE HERE********//

	fun fit(s:String): Int{
		return s.split('1').size -1
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
		return sArr.joinToString("")
	}
	
	fun onePointCrossover(a: String, b: String): String{
		var aLen: Int = a.length
		//var bLen: Int = b.length
		//var diff: Int = aLen - bLen
		var cutPoint: Int = RANDOM.nextInt(aLen)//if(diff > -1) RANDOM.nextInt(aLen)/aLen else RANDOM.nextInt(bLen)/bLen
		
		return a.substring(0, cutPoint) + b.substring(cutPoint, aLen)
	}

	
	var pop: ArrayList<String> = ArrayList<String>()
	for(c in 1..10){
		var str: String = ""
		for(d in 1..8){
			str += RANDOM.nextInt(2)	
		}
		pop.add(str)

		//str = ""
	}
	
	// need to sort generics so this works. 
	baseGA(pop, ::fit, ::mutate, 8, ::onePointCrossover)
	
	
	
	
	
	//***********************************//







    /**
     * BRENDANSSSSSSSSSSSSSSSSSS
     */

    //Look at the other two files

}
