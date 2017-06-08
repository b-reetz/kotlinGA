import java.util.Random

/**
 * Tests out the rosenbrook function
 */

private val rand = Random()
fun main(args: Array<String>) {
    val popSize = 200 //size of the population
    val sphereN = 10 //size of the vectors each member of the population holds

    val initPopulation: ArrayList<ArrayList<Double>> = ArrayList()

    //Initialises population
    for (i in 0..popSize) {
        val temp: ArrayList<Double> = ArrayList()
        for (j in 0..sphereN-1)
            temp.add(rand.nextGaussian())
        initPopulation.add(temp)
    }

    runBasicGA(initPopulation)

}

/**
 * Applies the rosenbrook function to a member of the population
 *
 * @param col the member of the population (a list of doubles)
 * @return the fitness value for the given member of the population
 */
private fun fitness(col: Collection<Number>): Number {
    var total = 0.0

    val inDoubles: List<Double> = col.map{it.toDouble()}

    for (i in 0..col.size - 1 - 1) {
        val first = inDoubles[i + 1] - inDoubles[i] * inDoubles[i]
        val second = 100 * (first * first) //100(xi+1 - xi^2)^2
        val third = (inDoubles[i] - 1) * (inDoubles[i] - 1) //(xi - 1) ^ 2
        total += second + third
    }
    return total
}


/**
 * The basic GA method
 */
private fun runBasicGA(population: Collection<Collection<Number>>) {

    /**
     * The crossover function for this GA
     *
     * @param x Our population
     * @return a new population that has been built up using this crossover method
     *
     */
    fun crossover(x: Collection<Collection<Number>>) : Collection<Collection<Number>> {
        val newPopulation: ArrayList<ArrayList<Number>> = ArrayList()

        var newItem: ArrayList<Number>

        while (newPopulation.size != x.size) {
            newItem = ArrayList()
            val a = x.elementAt(rand.nextInt(x.size)) //Collection<Number>
            val b = x.elementAt(rand.nextInt(x.size))

            val k = rand.nextInt((a.size - 2) + 1) + 2

            (0..k-1).mapTo(newItem) { a.elementAt(it) }
            (k..a.size-1).mapTo(newItem) { b.elementAt(it) }

            newPopulation.add(newItem)
        }

        return newPopulation
    }

    /**
     * Mutates a given population and returns
     *
     * @param x the member of the population
     * @return the same member of the population, but mutated
     */
    fun mutation(x: Collection<Number>) : Collection<Number> {
        val prob = 1/x.size
        val toReturn: ArrayList<Double> = ArrayList()

        for (i in x) {
            if (rand.nextDouble() < prob)
                toReturn.add(i.toDouble() + (rand.nextGaussian() * 0.01))
            else
                toReturn.add(i.toDouble())
        }
        return toReturn
    }

    //Runs the GA with the given parameters, preferring lower fitness values
    GA(col = population, fitness = ::fitness, crossover = ::crossover, mutation = ::mutation, k = 4).run(200, false)
}