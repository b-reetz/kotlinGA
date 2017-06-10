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
	 *Function to test if one solution dominates another
	 *
	 * @param a A generic which will be tested for pareto dominance
	 * @param b A generic which will be tested for pareto dominance
	 * @return a boolean whether a dominated b
	 */
	fun dominates(a: T, b: T): Boolean{
		val fit1 = totalFitness(a)
		val fit2 = totalFitness(b)
		//check dominance, return true if a dominates b
		if(fit1.elementAt(0).toDouble() >= fit2.elementAt(0).toDouble() && fit1.elementAt(1).toDouble() >= fit2.elementAt(1).toDouble()){
			return fit1.elementAt(0).toDouble() > fit2.elementAt(0).toDouble() || fit1.elementAt(1).toDouble() > fit2.elementAt(1).toDouble()
		}
		return false
	}
	
	
	/**
	 * Calculates the fitness values of the given solution
	 *
	 * @param sol The solution of which to calculate the fitness values
	 * @return  A collection of fitness values
	 */
	fun totalFitness(sol: T): Collection<Number>{
		val fitnesses: ArrayList<Number> = ArrayList()
		fitnessFns.mapTo(fitnesses) { it(sol) }
		return fitnesses
	}
	
	/**
	 * Performs GA tournament selection on the passed through population, return a population of the same size
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
				if (dominates(winner, contender))
					winner = contender

            }
            toReturn.add(winner)
        }
        return toReturn
    }
	
	/**
	 * Finds and returns the solution with the highest fitness in a population
	 *
	 * @param col The collection which will be find fittest
	 * @return The member with the highest fitness in the collection
	 */
	fun findFittest(col: Collection<T>): T{
		var best = col.elementAt(0)
		//loop through keeping the most dominant solution
		(1..col.size-1)
				.asSequence()
				.map { col.elementAt(it) }
				.filter { dominates(best, it) }
				.forEach { best = it }
		
		return best
	}
	
	
	fun archiveAcceptance(col: ArrayList<T>, newSol: T): ArrayList<T>{
		val archive: ArrayList<T> = col
		val toRemove = ArrayList<T>()
		//if archive is empty, add new element
		if(col.size == 0){
			archive.add(newSol)
			return archive
		}
		//if new element is dominated by any existing in archive, dont add
		col.filter { dominates(it, newSol) }.forEach { return col }
		//if new element dominates any in archive, remove dominated solutions
		col.filterTo(toRemove) { dominates(newSol, it) }
		
		archive.removeAll(toRemove)
		//add new element and return the archive
		archive.add(newSol)
		return archive
	}
	
	/**
	 * Runs the microGA program
	 *
	 * @param reps The number of repetitions to complete
	 * @param maximise Boolean of whether we are looking for minimum or maximum
	 */
    fun run(reps: Int = 10, targetSize: Int = 10, maximise: Boolean = true) {
		this.maximise = maximise
		val microPop: ArrayList<T> = ArrayList()
		val newPopulation: ArrayList<T> = ArrayList()
		var archive: ArrayList<T> = ArrayList()
		
        //BEGIN
		println("running")
		//initialise
		
		for(i in 1..microPopSize){
			microPop.add(col.elementAt(rand.nextInt(col.size)))
		}
		var best = findFittest(microPop)
		archive.add(best)
		//while not done
		while(archive.size < targetSize){
			//for number of reps required in evolution
			for(evolve in 1..reps){
				//add the best from population
				newPopulation.add(best)
				//selection from population
				newPopulation.addAll(tournamentSelection(microPop))
				//crossover and mutate
				col = crossover(newPopulation).map(mutation)
				//find best fitness
				best = findFittest(col)
				//run archive acceptance on the best solution
				archive = archiveAcceptance(archive, best)
				//reinitialise population
				microPop.clear()
				
				for(i in 1..microPopSize){
					microPop.add(col.elementAt(rand.nextInt(col.size)))
				}
			}
		}
		
		//print the archive and fitnesses
		println(archive)
		archive.forEach({b -> println(totalFitness(b))})

    }
	
	
	
	
	
	
	
	
	
	
	
	
}