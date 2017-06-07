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
	
	fun tournament(pop: ArrayList<Any>, K: Int, fitFn: (v : Any) -> Int): Any{
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

	fun baseGA(population: ArrayList<Any>, fitFn: (v : Any) -> Int, mutate: (o: Any) -> Any, k: Int){
		var newPopulation: MutableList<Any> = ArrayList<Any>()
		var fitnesses: MutableList<Int> = ArrayList<Int>()
		population.forEach({o:Any -> fitnesses.add(fitFn(o))})
		
		
		
		while(true){
			for(i in 0..population.size){
				newPopulation.add(tournament(population, k, fitFn))
			}
		}
		
	}











    /**
     * BRENDANSSSSSSSSSSSSSSSSSS
     */
}
