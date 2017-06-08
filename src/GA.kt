import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Brendan on 6/8/17.
 *
 */
class GA<T>(internal var col: Collection<T>,
         internal var fitness: (T) -> Number,
         internal var mutation: (Collection<T>) -> Collection<T>,
         internal var k: Int) {


    internal var fitnesses: Collection<Number> = ArrayList(col.map(fitness))

    /**
     * Made method that kicks the GA off
     *
     * @param x Number of iterations to execute for
     */
    fun run(x: Int) {
        val newPopulation: ArrayList<T> = ArrayList()
        for (i in 0..x) {
            newPopulation.addAll(tournamentSelection(col))

            col = ArrayList(mutation(newPopulation))
            fitnesses = ArrayList(col.map(fitness))

            println(findBestFitness(col))
            newPopulation.clear()
        }

    }

    fun tournamentSelection(col: Collection<T>) : Collection<T> {
        val random = Random()
        val toReturn: ArrayList<T> = ArrayList()

        while (toReturn.size != col.size) {
            var winner = col.elementAt(random.nextInt(col.size))

            //iterate k many times, replacing winner with best selected item
            var x = 0
            while (++x < k) {
                val contender = col.elementAt(random.nextInt(col.size))
                if (fitness(contender).toDouble() > fitness(winner).toDouble())
                    winner = contender
            }
            toReturn.add(winner)
        }

        return toReturn
    }

    fun findBestFitness(col: Collection<T>) : T {
        var s: T = col.elementAt(0)
        col.forEach {
            if (fitness(it).toDouble() > fitness(s).toDouble())
                s = it
        }
        return s
    }

    fun printPopulation(col: Collection<T>) : Unit {
        for (i in col)
            println(i)
    }
}
