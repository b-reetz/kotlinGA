/**
 * Created by Brendan on 6/8/17.
 *
 *
 

fun main(args: Array<String>) {

    val myCollection = listOf(1, 2, 3)
    fun fitness(x: Any?) : Number {
        if (x is Int) println(x+1)

        if (x is Int) return x+1
        else return -1
    }

    fun mutation() : Unit {

    }

    //val myGA = GA(myCollection, ::fitness, ::mutation, 3)

    myGA.run()

}*/

