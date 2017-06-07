import java.util.ArrayList

/**
 * Created by Brendan on 6/8/17.

 */
class GA(col: Collection<*>,
         fitness: (Any?) -> Number,
         internal var mutation: () -> Unit,
         internal var k: Int) {

    internal var fitnesses: Collection<Number>

    init {
        fitnesses = col.map(fitness)
    }

    fun run() {}
}
