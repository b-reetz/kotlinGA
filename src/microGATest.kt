import java.util.Random

private val rand = Random()
private val D = 30

/**
 *
 *calls the MicroGA function with given parameters
 *
 *@param args Arguments passed into program
 *
 */
fun main(args: Array<String>){
	//put fitness functions in the collection
	var funcs: ArrayList<(Collection<Double>) -> Double> = ArrayList()
	funcs.add(fit1)
	funcs.add(fit2)
	//generate initial population
	var pop: ArrayList<ArrayList<Double>> = ArrayList()
	
	for(i in 1..10){
		var tmp = ArrayList<Double>()
		for(j in 1..D){
		 	tmp.add(rand.nextDouble())
		}
		pop.add(tmp)
	}
	//run the microGA
	MicroGA(pop, 10, ::mutation, ::crossover, funcs).run(50, 10, false)
}

/**
 *
 *clamps values to ensure they are within the correct range 0..1
 *
 *@param d double to be clamped
 *@return clamped Double
 */
fun clamp(d: Double): Double{
	//tests the bounds and returns value accordingly
	if(d < 0)
		return 0.01
	else if(d > 1)
		return 0.99
	else
		return d
}

/**
 *
 *Mutates a Solution by adding random gaussian noise
 *
 *@param col Solution to be mutated
 *@return mutated solution
 */
fun mutation(col: ArrayList<Double>): ArrayList<Double>{
	var newCol :ArrayList<Double> = ArrayList()
	//loops through values in solution and adds gaussian noise
	for(i in 0..D-1){
		newCol.add(clamp(col.elementAt(i) + (rand.nextGaussian() * 0.1)))
	}
	
	return newCol
}

/**
 *
 *cCrossover function to generate children
 *
 *@param a population to generate children from
 *@return population of children
 */
fun crossover(a: Collection<ArrayList<Double>>): Collection<ArrayList<Double>>{
	var c :ArrayList<ArrayList<Double>> = ArrayList()
	//grab two random solutions and perform crossover
	while(c.size != a.size){
		var m = a.elementAt(rand.nextInt(a.size))
		var n = a.elementAt(rand.nextInt(a.size))
		var o: ArrayList<Double> = ArrayList()
		for(i in 0..(D/2) - 1){
			o.add(m.get(i))
		}
		for(i in (D/2)..m.size-1){
			o.add(n.get(i))
		}
		c.add(o)		
	}

	return c
}

//fitness functions to be passed into microGA function
var fit1: (Collection<Double>)->Double = {it.elementAt(0)}
var fit2: (Collection<Double>) -> Double = {
	var g: Double = 1 + (9.0/(D-1)) * (it.sum() - it.elementAt(0))
	var h: Double = 1.0 - Math.sqrt(Math.abs(it.elementAt(0)/g))
	g*h
}

