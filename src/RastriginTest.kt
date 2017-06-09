/**
 *Created 9/062017 00:05
 *
 */
import java.util.Random

private val rand = Random()
fun main(args: Array<String>) {

    val myCollection: ArrayList<ArrayList<Number>> = ArrayList()

    //initlialise population
    val n = 3
    var newSol: ArrayList<Number>
    while (myCollection.size != 50) {
        newSol = ArrayList()
        for (i in 1..n) {
            newSol.add(rand.nextDouble())
        }
        myCollection.add(newSol)
    }

    runBasicGA(myCollection)

}

private fun runBasicGA(population: Collection<Collection<Number>>) {

    /**
     * The Rastrigin function applied to a double
     *
     * @param d the double to apply the rastrigin function to
     * @return the resulting value
     */
    fun formula(d: Double): Double {
        //((x[i] * x[i]) - (10 * Math.cos(2 * Math.PI * x[i])));
        return (d * d) - (10 * Math.cos(2 * Math.PI * d))
    }

    /**
     * Calculates the fitness for a given member of the population using the Rastrigin function
     *
     * @param a the member of the population
     * @return the fitness for this member of the population
     */
    fun fitness(a: Collection<Number>): Number {
        val result: Double = 10.0 * a.size + a.sumByDouble { formula(it as Double) }
        return result
    }

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
     * Gets given a member of the population, mutates and returns it
     *
     * @param a member of the population
     * @return the mutated member of the population
     */
    fun mutation(a: Collection<Number>): Collection<Number> {

        val newSol: ArrayList<Number> = ArrayList()

        (0..a.size - 1)
                .map { a.elementAt(it) }
                .mapTo(newSol) { (it as Double) + (rand.nextGaussian() * 0.001) }
        return newSol
    }
    //runs the GA
    GA(population, ::fitness, ::crossover, ::mutation).run(500, TOURNAMENT_SELECTION, 4, false)
}

 