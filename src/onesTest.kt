import java.util.Random

/**
 * A test class to
 */
private val rand = Random()
fun main(args: Array<String>) {
    val popSize = 50

    //generates the initial population
    val initPopulation = ArrayList<String>()
    for (i in 0..popSize)
        initPopulation.add(String.format("%32s", Integer.toBinaryString(rand.nextInt())).replace(' ', '0'))


    runBasicGA(initPopulation)

}
/**
 * The fitness function
 *
 * @param s The string to calculate fitness for
 * @return the number of '1's in s
 */
fun fitness(s: String) : Number = s.split('1').size - 1


private fun runBasicGA(population: Collection<String>) {

    /**
     * The crossover function for the bit strings
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

    //Runs the GA, favouring a higher fitness value
    GA(population, ::fitness, ::crossover, ::mutation, 4).run(50)
}

