import java.util.Random

import kotlin.collections.ArrayList


const val TOURNAMENT_SELECTION = 1
const val ROULETTE_WHEEL_SELECTION = 2
const val RANDOM_SELECTION = 3

/**
 * A basic Genetic Algorithm
 *
 * @param col The initial population
 * @param fitness The fitness function to test against members of the population
 * @param crossover The crossover function to apply to members within the population
 * @param mutation The mutation function to apply to members of the population*
 */
class GA<T>(internal var col: Collection<T>,
            internal var fitness: (T) -> Number,
            internal var crossover: (Collection<T>) -> Collection<T>,
            internal var mutation: (T) -> T) {


    //Generates the initial fitness collection
    internal var allFitness: Collection<Number> = col.map(fitness)
    internal var k = 0
	internal var maximise: Boolean = true





    /**
     * Runs through the GA algorithm
     *
     * For x amount of times;
     *
     *  creates a new population via tournament selection
     *  applies crossover & mutation to new population and saves in original population
     *  updates all of the fitness's
     *  prints out the best fitness
     *
     * @param iterations Number of iterations to execute for
     * @param tournamentRounds Amount of rounds the tournament selection should have
     * @param max Boolean indicates whether greater fitness is good or bad
     */
    fun run(iterations: Int, selectionMethod: Int = TOURNAMENT_SELECTION, tournamentRounds: Int = 2, max: Boolean = true) {
        this.k = tournamentRounds
        val newPopulation: ArrayList<T> = ArrayList()
		maximise = max
        for (i in 0..iterations) {

            //creates a new population via the given selection method method
            when (selectionMethod) {
                ROULETTE_WHEEL_SELECTION ->
                    newPopulation.addAll(rouletteWheelSelection(col))
                RANDOM_SELECTION ->
                    newPopulation.addAll(randomSelection(col))
                else ->
                    newPopulation.addAll(tournamentSelection(col))
            }

            //creates new crossover population, and then mutates every solution (maybe)
            col = crossover(newPopulation).map(mutation)

            //Updates all of the fitness values
            allFitness = col.map(fitness)

            val best = findBestFitness()
            print(best)
            println("\t\t" + fitness(best))
            newPopulation.clear()
        }
        println()
        println()
        val best = findBestFitness()
        println("The best solution is \n" + findBestFitness())
        println("with the fitness of \n" + fitness(best))


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
     * random selection method
     *
     * @param col population
     * @return the new population
     */
    fun randomSelection(col: Collection<T>): Collection<T> {
        val random = Random()
        val newPopulation: ArrayList<T> = ArrayList()

        while (newPopulation.size != col.size)
            newPopulation.add(col.elementAt(random.nextInt(col.size)))

        return newPopulation
    }

    /**
     * makes a new population using the RWS on the given population
     *
     * @param population the population we're performing RWS on
     * @return the new selected population
     */
    fun rouletteWheelSelection(population: Collection<T>) : Collection<T> {
        val random = Random()
        val newPopulation: ArrayList<T> = ArrayList()

        val totalFitness: Double = population.map(fitness).reduce{total, next -> total.toDouble() + next.toDouble()}.toDouble()


        while (newPopulation.size != population.size) {
//            println("in while")
            var value = random.nextDouble() * totalFitness
            for (i in population) {
//                println("in for")
                value -= fitness(i).toDouble()
                if (value <= 0 || i == population.last()) {
                    newPopulation.add(i)
                    break
                }

            }
//            newPopulation.add(population.last())
        }

        return newPopulation
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
            if (allFitness.elementAt(j).toDouble() > bestFitness) {
                bestFitness = allFitness.elementAt(j).toDouble()
                pos = j
            }
        }
        return col.elementAt(pos)
    }

}
