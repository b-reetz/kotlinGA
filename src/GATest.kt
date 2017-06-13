import java.util.Random

/**
 * Class to test the GA function
 *
 * Shows how you can call the GA for a problem
 */

private val rand = Random()
fun main(args: Array<String>) {

    //This are set up methods for testing the given functions, with different selection methods

//    sphereFunctionTest()
//    sphereFunctionTest(RANDOM_SELECTION)
//    sphereFunctionTest(ROULETTE_WHEEL_SELECTION)

//    rosenbrookFunctionTest()
//    rosenbrookFunctionTest(RANDOM_SELECTION)
//    rosenbrookFunctionTest(ROULETTE_WHEEL_SELECTION)

//    rastriginFunctionTest()
//    rastriginFunctionTest(RANDOM_SELECTION)
//    rastriginFunctionTest(ROULETTE_WHEEL_SELECTION)

    onesTest()
//    onesTest(RANDOM_SELECTION)
//    onesTest(ROULETTE_WHEEL_SELECTION)
}

/**
 * Tests the sphere function on the GA class
 */
private fun sphereFunctionTest(selection: Int = TOURNAMENT_SELECTION) {

    val popSize = 200 //size of the population
    val sphereN = 2 //size of the vectors each member of the population holds

    val initPopulation: ArrayList<ArrayList<Double>> = ArrayList()

    //Initialises population
    for (i in 0..popSize) {
        val temp: ArrayList<Double> = ArrayList()
        for (j in 0..sphereN-1)
            temp.add(rand.nextDouble())
        initPopulation.add(temp)
    }

    /**
     *
     *
     * @param col the given member of the population to calculate the fitness for
     * @return the fitness of this member of the population
     */
    fun fitness(col: Collection<Number>): Number {
        return col.reduce{total, next -> total.toDouble() + Math.pow(next.toDouble(), 2.0)}
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
    GA(col = initPopulation, fitness = ::fitness, crossover = ::crossover, mutation = ::mutation).run(iterations = 1000, selectionMethod = selection, max = false)
}

/**
 *
 */
private fun rosenbrookFunctionTest(selection: Int = TOURNAMENT_SELECTION) {
    val popSize = 500 //size of the population
    val sphereN = 5 //size of the vectors each member of the population holds

    val initPopulation: ArrayList<ArrayList<Double>> = ArrayList()

    //Initialises population
    for (i in 0..popSize) {
        val temp: ArrayList<Double> = ArrayList()
        for (j in 0..sphereN-1)
            temp.add(rand.nextGaussian())
        initPopulation.add(temp)
    }

    /**
     * Applies the rosenbrook function to a member of the population
     *
     * @param col the member of the population (a list of doubles)
     * @return the fitness value for the given member of the population
     */
    fun fitness(col: Collection<Number>): Number {
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
    GA(col = initPopulation, fitness = ::fitness, crossover = ::crossover, mutation = ::mutation).run(iterations = 10000, selectionMethod = selection, max = false)
}

private fun onesTest(selection: Int = TOURNAMENT_SELECTION) {
    val popSize = 50

    //generates the initial population
    val initPopulation = ArrayList<String>()
    for (i in 0..popSize)
        initPopulation.add(String.format("%32s", Integer.toBinaryString(rand.nextInt())).replace(' ', '0'))



    /**
     * The fitness function
     *
     * @param s The string to calculate fitness for
     * @return the number of '1's in s
     */
    fun fitness(s: String) : Number = s.split('1').size - 1

    /**
     * The crossover function for this GA
     *
     * @param x Our population
     * @return a new population that has been built up using this crossover method
     */
    fun crossover(x: Collection<String>) : Collection<String> {
        val newPopulation: ArrayList<String> = ArrayList()

        val sb: StringBuilder = StringBuilder()

        while (newPopulation.size != x.size) {
            sb.setLength(0)
            val a = x.elementAt(rand.nextInt(x.size))
            val b = x.elementAt(rand.nextInt(x.size))

            val k = rand.nextInt((a.length - 2) + 1) + 2

            sb.append(a.substring(0, k)).append(b.substring(k, b.length))
            newPopulation.add(sb.toString())
        }
        return newPopulation
    }

    /**
     * Takes in a member of the population and will flip a bit, based on a random value (low probability)
     *
     * @param x The member of the population to mutate
     * @return the mutated member of the population
     */
    fun mutation(x: String) : String {
        val prob = 1/x.length
        val charArray = x.toCharArray()
        for (i in 0..x.length) {
            if (rand.nextDouble() < prob)
                charArray[i] = if (charArray[i] == '0') '1' else '0'

        }
        return charArray.joinToString("")
    }


    GA(initPopulation, ::fitness, ::crossover, ::mutation).run(iterations = 50, selectionMethod = selection)
}

fun rastriginFunctionTest(selection: Int = TOURNAMENT_SELECTION) {
    val initPopulation: ArrayList<ArrayList<Number>> = ArrayList()

    //initlialise population
    val n = 3
    var newSol: ArrayList<Number>
    while (initPopulation.size != 50) {
        newSol = ArrayList()
        for (i in 1..n) {
            newSol.add(rand.nextDouble())
        }
        initPopulation.add(newSol)
    }

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

        val temp: ArrayList<Number> = ArrayList()

        (0..a.size - 1)
                .map { a.elementAt(it) }
                .mapTo(temp) { (it as Double) + (rand.nextGaussian() * 0.001) }
        return temp
    }
    //runs the GA
    GA(initPopulation, ::fitness, ::crossover, ::mutation).run(iterations = 20000, selectionMethod = selection, max = false)

}