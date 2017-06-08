import java.util.*
import kotlin.collections.ArrayList

/**
 * A basic Genetic Algorithm approach to
 *
 * @param col The initial population
 * @param fitness The fitness function to test against members of the population
 * @param crossover The crossover function to apply to members within the population
 * @param mutation The mutation function to apply to members of the population
 * @param k The amount of rounds within the tournament selection
 *
 */
class GA<T>(internal var col: Collection<T>,
            internal var fitness: (T) -> Number,
            internal var crossover: (Collection<T>) -> Collection<T>,
            internal var mutation: (T) -> T,
            internal var k: Int) {

    //Generates the initial fitness collection
    internal var fitnesses: Collection<Number> = col.map(fitness)
	internal var maximise: Boolean = true



    /**
     * Runs through the GA algorithm
     *
     * for x amount of times
     *  creates a new population via tournament selection
     *  applies crossover & mutation to new population and saves in original population
     *  updates all of the fitness's
     *  prints out the best fitness
     *
     * @param x Number of iterations to execute for
     * @param max Boolean indicates whether greater fitness is good or bad
     */
    fun run(x: Int, max: Boolean = true) {
        val newPopulation: ArrayList<T> = ArrayList()
		maximise = max
        for (i in 0..x) {
            //creates a new population via the tournamentSelection method
            newPopulation.addAll(tournamentSelection(col))

            //creates new crossover population, and then mutates every solution (maybe)
            col = crossover(newPopulation).map(mutation)

            //Updates all of the fitness values
            fitnesses = col.map(fitness)


            println(findBestFitness())
            newPopulation.clear()
        }
    }

    /**
     * Performs GA tournament selection on the passed through population, return a population of the same size
     *
     * @param col The population to run tournament selection on
     * @return a new population that has items that have run through tournament selection k times
     *
     */
    fun tournamentSelection(col: Collection<T>): Collection<T> {
        val random = Random()
        val toReturn: ArrayList<T> = ArrayList()

        while (toReturn.size != col.size) {
            var winner = col.elementAt(random.nextInt(col.size))

            //iterate k many times, replacing winner with best selected item
            var x = 0
            while (++x < k) {
                val contender = col.elementAt(random.nextInt(col.size))
				if (fitness(contender).toDouble() > fitness(winner).toDouble() == maximise)
					winner = contender

            }
            toReturn.add(winner)
        }
        return toReturn
    }

    /**
     * Finds the best solution within the population and returns it
     * Uses the default toString method for the generic object
     *
     * @return the best solution
     */
    fun findBestFitness(): T {
        var bestFitness: Double = 0.0
        var pos = 0

        for (j in 1..col.size-1) {
            if (fitnesses.elementAt(j).toDouble() > bestFitness) {
                bestFitness = fitnesses.elementAt(j).toDouble()
                pos = j
            }
        }
        return col.elementAt(pos)
    }

}
