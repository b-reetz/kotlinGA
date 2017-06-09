import java.util.Random

class MicroGA<T> (
				internal var individuals: Collection<T>,
				internal var microPopSize: Int = 10,
				internal var mutation: (T) -> T,
				internal var crossover: (Collection<T>) -> Collection<T>,
				internal var fitnessFns: Collection<(T) -> Number>
				) {
	internal var maximise: Boolean = true
	internal var archive: ArrayList<Number> = ArrayList()
	
				
	val rand = Random()
	/**
	*	dominance function
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
	
	fun totalFitness(sol: T): Collection<Number>{
		var fitnesses: ArrayList<Number> = ArrayList()
		for(e in fitnessFns){
			fitnesses.add(e(sol))
		}	
		return fitnesses
	}
	
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
	//var functions: Collection<(T) -> Number> = ArrayList()

    fun run(reps: Int = 100, maximise: Boolean = true) {
		var microPop: ArrayList<T> = ArrayList()
		var newPopulation: ArrayList<T> = ArrayList()
		
        //BEGIN
		println("running")
		//initialise
		//var individuals = 
		for(i in 1..microPopSize){
			microPop.add(individuals.elementAt(rand.nextInt(individuals.size)))
		}
		var best = findFittest(microPop)
		//while not done
		for(evolve in 1..reps){
			newPopulation.add(best)
			//select from population
			newPopulation.addAll(tournamentSelection(microPop).take(microPopSize-1))
			//crossover and mutate
			individuals = crossover(newPopulation).map(mutation)
			
			//stor one arbitrarily and copy to next gen
			best = findFittest(individuals)
			
			//update archive with best
		}
		
		//insert into archive
		
		println(individuals)
		individuals.forEach({b -> println(totalFitness(b))})
		//println(totalFitness(findFittest(individuals)))
		//archive
    }
	
	
}