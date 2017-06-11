# kotlinGA

This library's goal is to provide generic Genetic Algorithm classes to create and test fitness, crossover, mutation and selection functions on. The functionality that is provided is dependent on the algorithm implemented.

The class's generic type when initialised is the type of the population members.

As an example, to create a test for the OnesProblem using the generic GA algorithm, you would do the following:

```kotlin
GA(col = initPopulation, fitness = ::fitness, crossover = ::crossover, mutation = ::mutation)
		.run(iterations = 200, selectionMethod = selection, max = false)
```

The GA Constructor is 
```kotlin
GA<T>(col: Collection<T>, 
	fitness: (T) -> Number, 
	crossover: (Collection<T>) -> Collection<T>, 
	mutation: (T) -> T)
```
As our OnesProblem is being represented as a population of _Strings_ (it could be a _Collection&lt;Char&gt;_ in which case that is what the type (_T_) would be), the fitness function that you declare must take in a _String_ and return a _Number_. 
The crossover function supplied has to take in a population of _Strings_ and return a new population of _Strings_.
The mutation function takes in a _String_ and returns a _String_

The requirements for what each GA class constructor takes are specified in their corresponding KDocs, but generally they should be along the same lines. The more convoluted and different an algorithm's class is, perhaps the more specific the constructor will be. Coming into this project from a Java background, this seemed to be a non-efficient way of designing the classes. However, unlike Pojo's, Kotlin classes and functions can have default and named arguments which enable the class to have a lot more configuration in the constructor which the user does not have to initialise or edit.

The _run_ function takes the following parameters
```kotlin
run(iterations: Int, selectionMethod: Int = TOURNAMENT_SELECTION, 
	tournamentRounds: Int = 2, max: Boolean = true)
```
This is designed to allow the user to change what selection method in GA they use without having to define a whole new GA object. 
The _max_ parameter let's our algorithm know whether to prefer higher, or lower fitness values
