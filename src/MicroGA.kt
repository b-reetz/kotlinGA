import java.util.Random

class MicroGA<T>(
				internal var nonreplacableIndividuals: Collection<T>,
				internal var replacableIndividuals: Collection<T>,
				internal var microPopSize: Int = 10,
				internal var mutation: (Collection<T>) -> Collection<T>,
				internal var crossover: (Collection<T>) -> Collection<T>,
				internal var fitnessFns: Collection<(T) -> Number>
				) {
	internal var maximise: Boolean = true
	internal var archive: ArrayList<Number> = ArrayList()
	
				
	val rand = Random()
	/**
	*	dominance function
	*/
	fun dominates(a: Collection<Number>, b: Collection<Number>): Boolean{
		val dominates: ArrayList<Boolean> = ArrayList(a.size)
		for(i in 0..a.size-2){
			if(a.elementAt(i) as Double >= b.elementAt(i) as Double && a.elementAt(i+1) as Double >= b.elementAt(i+1) as Double){
				if(a.elementAt(i) as Double > b.elementAt(i) as Double ||a.elementAt(i+1) as Double >= b.elementAt(i+1) as Double){
					dominates.add(i, true)
				}
			}
			else{
				dominates.add(i, false)
			}		
		}
		for(i in 0..dominates.size-1){
			if (dominates.elementAt(i))
				continue
			else
				return false
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
	
	fun tournamentSelection(col: Collection<T>): ArrayList<T> {
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
	//var functions: Collection<(T) -> Number> = ArrayList()

    fun run(reps: Int = 100, maximise: Boolean = true) {
		var microPop: ArrayList<T> = ArrayList()
		var newPopulation: ArrayList<T> = ArrayList()
		var best: T
        //BEGIN
		println("running")
		//initialise
		val individuals = nonreplacableIndividuals.union(replacableIndividuals)
		for(i in 1..microPopSize){
			microPop.add(individuals.elementAt(rand.nextInt(individuals.size)))
		}
		//while not done
		for(evolve in 1..reps){
			//select from population
			newPopulation.addAll(tournamentSelection(microPop))
			//crossover and mutate
			//microPop = crossover(newPopulation).map(mutation)
			//stor one arbitrarily and copy to next gen
			best = microPop.elementAt(rand.nextInt(microPop.size-1))
			
			
		}
		//archive
    }
	
	
}