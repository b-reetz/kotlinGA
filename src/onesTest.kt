import java.util.Random

/**
 * A test class to
 */

fun main(args: Array<String>) {

    val random = Random()

    val popSize = 50
    val rand = Random()
//    val temp: Array<IntArray>
    val myCollection = ArrayList<String>()
    for (i in 0..popSize)
        myCollection.add(String.format("%32s", Integer.toBinaryString(rand.nextInt())).replace(' ', '0'))


    /**
     * The fitness function
     *
     * @param s The string to calculate fitness for
     * @return the number of '1's in s
     *
     * @author Brendan
     */
    fun fitness(s: String) : Number = s.split('1').size - 1

    /**
     * The crossover function for this GA
     *
     * @param x Our population
     * @return a new population that has been built up using this crossover method
     *
     * @author Brendan
     */
    fun crossover(x: Collection<String>) : Collection<String> {
        val newPopulation: ArrayList<String> = ArrayList()

        val sb: StringBuilder = StringBuilder()

        while (newPopulation.size != x.size) {
            sb.setLength(0)
            val a = x.elementAt(random.nextInt(x.size))
            val b = x.elementAt(random.nextInt(x.size))

            val k = random.nextInt((a.length - 2) + 1) + 2

            sb.append(a.substring(0, k)).append(b.substring(k, b.length))
            newPopulation.add(sb.toString())
        }
        return newPopulation
    }

    fun mutation(x: String) : String {
        val prob = 1/x.length
        val charArray = x.toCharArray()
        for (i in 0..x.length) {
            if (rand.nextDouble() < prob)
                charArray[i] = if (charArray[i] == '0') '1' else '0'

        }
        return charArray.joinToString("")
    }

    val myGA = GA(myCollection, ::fitness, ::crossover, ::mutation, 4)
    myGA.run(50)

}

