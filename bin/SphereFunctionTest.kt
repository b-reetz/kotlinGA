import java.util.Random

/**
 * A test class to test the sphere function works with the given type of Genetic Algorithm Class
 */

private val rand = Random()
fun main(args: Array<String>) {
    val popSize = 200 //size of the population
    val sphereN = 5 //size of the vectors each member of the population holds

    val initPopulation: ArrayList<ArrayList<Double>> = ArrayList()

    //Initialises population
    for (i in 0..popSize) {
        val temp: ArrayList<Double> = ArrayList()
        for (j in 0..sphereN-1)
            temp.add(rand.nextGaussian())
        initPopulation.add(temp)
    }

    runBasicGA(initPopulation)

    //TODO more GA's here
}

/**
 *
 *
 * @param col the given member of the population to calculate the fitness for
 * @return the fitness of this member of the population
 */
private fun fitness(col: Collection<Number>): Number {
    return col.reduce{total, next -> total.toDouble() + Math.pow(next.toDouble(), 2.0)}
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
     * Mutates a given member of the population, and returns it
     *
     * @param x the member of the population to mutate
     * @return the mutated member of the population
     */
    fun mutation(x: Collection<Number>) : Collection<Number> {
        val prob = 1/x.size
        val toReturn: ArrayList<Double> = ArrayList()

        for (i in x) {
            if (rand.nextDouble() < prob)
                toReturn.add(i.toDouble() + (rand.nextGaussian() * 0.0001))
            else
                toReturn.add(i.toDouble())
        }
        return toReturn
    }

    //runs the GA with the given parameters, preferring a small fitness
    GA(col = population, fitness = ::fitness, crossover = ::crossover, mutation = ::mutation, k = 4).run(200, false)
}