import java.util.Random


/**
 * A basic Genetic Algorithm approach to
 *
 * @param col The initial population
 * @param microPopSize The size of the micro population to be generated ad run in the algorithm
 * @param fitnessFns The fitness functions to test against members of the population
 * @param crossover The crossover function to apply to members within the population
 * @param mutation The mutation function to apply to members of the population*
 */
class MicroGA<T> (
				internal var col: Collection<T>,
				internal var microPopSize: Int = 10,
				internal var mutation: (T) -> T,
				internal var crossover: (Collection<T>) -> Collection<T>,
				internal var fitnessFns: Collection<(T) -> Number>
				) {
	internal var maximise: Boolean = true
	internal var archive: ArrayList<Number> = ArrayList()
	
				
	val rand = Random()
	/**
 * A basic Genetic Algorithm approach to
 *
 * @param a A generic which will be tested for pareto dominance
 * @param b A generic which will be tested for pareto dominance
 * @return a boolean whether a dominated b
 */
	fun dominates(a: T, b: T): Boolean{
		var fit1 = totalFitness(a)
		var fit2 = totalFitness(b)
		
		if(fit1.elementAt(0).toDouble() >= fit2.elementAt(0).toDouble() && fit1.elementAt(1).toDouble() >= fit2.elementAt(1).toDouble()){
			if(fit1.elementAt(0).toDouble() > fit2.elementAt(0).toDouble() && fit1.elementAt(1).toDouble() > fit2.elementAt(1).toDouble()){
			
			}
		}
		return true
	}
	
	
	/**
	 * A basic Genetic Algorithm approach to
	 *
	 * @param sol The solution of which to calculate the fitness values
	 * @return  A collection of fitness values
	 */
	fun totalFitness(sol: T): Collection<Number>{
		var fitnesses: ArrayList<Number> = ArrayList()
		for(e in fitnessFns){
			fitnesses.add(e(sol))
		}	
		return fitnesses
	}
	
	/**
	 * A basic Genetic Algorithm approach to
	 *
	 * @param col The collection which will be selected from
	 * @return The winners of the tournament
	 */
	fun tournamentSelection(col: Collection<T>): Collection<T> {
        val random = Random()
        val toReturn: ArrayList<T> = ArrayList()

        while (toReturn.size != col.size) {
            var winner = col.elementAt(random.nextInt(col.size))

            //iterate k many times, replacing winner with best selected item
            var x = 0
            while (++x < 2) {
                val contender = col.elementAt(random.nextInt(col.size))
				if (dominates(totalFitness(winner), totalFitness(contender)) == maximise)
					winner = contender

            }
            toReturn.add(winner)
        }
        return toReturn
    }
	
	
	fun findFittest(col: Collection<T>): T{
		var best = col.elementAt(0)
		
		for(i in 1..col.size-1){
			var contender = col.elementAt(i)
			if(dominates(best, contender))
				best = contender
		}
		
		return best
	}
	
	

    fun run(reps: Int = 100, maximise: Boolean = true) {
		var microPop: ArrayList<T> = ArrayList()
		var newPopulation: ArrayList<T> = ArrayList()
		var front: ArrayList<T> = col.take(col.size)
		
        //BEGIN
		println("running")
		//initialise
		//var individuals = 
		for(i in 1..microPopSize){
			microPop.add(col.elementAt(rand.nextInt(col.size)))
		}
		var best = findFittest(microPop)
		//while not done
		for(evolve in 1..reps){
			newPopulation.add(best)
			//select from population
			newPopulation.addAll(tournamentSelection(microPop).take(microPopSize-1))
			//crossover and mutate
			col = crossover(newPopulation).map(mutation)
			
			best = findFittest(col)

		}
		
		//insert into archive
		
		println(col)
		col.forEach({b -> println(totalFitness(b))})

    }
	
	
}