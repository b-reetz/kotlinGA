# kotlinGA

This library's goal is to provide generic Genetic Algorithm classes to create and test fitness, crossover, mutation and selection functions on. The functionality that is provided is dependent on the algorithm implemented.

The class's generic type when initialised is the type of the population members.

As an example, to create a test for the OnesProblem using the generic GA algorithm, you would do the following:

```kotlin
GA(col = initPopulation, fitness = ::fitness, crossover = ::crossover, mutation = ::mutation).run(iterations = 200, selectionMethod = selection, max = false)
```

The GA Constructor is 
```kotlin
GA<T>(col: Collection<T>, fitness: (T) -> Number, crossover: (Collection<T>) -> Collection<T>, mutation: (T) -> T)
```
As our OnesProblem is being represented as a population of _Strings_ (it could be a _Collection&lt;Char&gt;_ in which case that is what the type (_T_) would be), the fitness function that you declare must take in a _String_ and return a _Number_
