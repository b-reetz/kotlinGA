import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Brendan on 6/8/17.
<<<<<<< HEAD

 
class GA(col: Collection<*>,
         fitness: (Any?) -> Number,
         internal var mutation: () -> Unit,
=======
 *
 */
class GA<T>(internal var col: Collection<T>,
         internal var fitness: (T) -> Number,
         internal var mutation: (Collection<T>) -> Collection<T>,

         internal var k: Int) {



    init {
       // fitnesses = col.map(fitness)
    }

    fun run() {}


    internal var fitnesses: Collection<Number> = col.map(fitness)

    /**
     * Made method that kicks the GA off
     *
     * @param x Number of iterations to execute for
     */
    fun run(x: Int) {
        for (i in 0..x) {
            val newPopulation = tournamentSelection(col)

            col = mutation(newPopulation)
            fitnesses = col.map(fitness)

            println(findBestFitness(col))
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
                val contender = col.elementAt(x)
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
}

