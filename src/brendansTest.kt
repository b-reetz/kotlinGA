import java.util.Random

/**
 * Created by Brendan & Sam on 1/8/17.
 *
 */

fun main(args: Array<String>) {

    val random = Random()
//    val myCollection = listOf(1, 2, 3).map{""+it}

    val popSize = 100
    val rand = Random()
    val myCollection = ArrayList<String>()
    for (i in 0..popSize)
        myCollection.add(String.format("%32s", Integer.toBinaryString(rand.nextInt())).replace(' ', '0'))


    /**
     * TODO Write
     *
     * @param s
     * @return
     *
     * @author Brendan
     */
    fun fitness(s: String) : Number = s.split('1').size - 1

    /**
     * TODO write
     *
     * @param x
     * @return
     *
     * @author Brendan
     */
    fun mutation(x: Collection<String>) : Collection<String> {
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

    val myGA = GA(myCollection, ::fitness, ::mutation, 3)
    myGA.run(100, false)

}

