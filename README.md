# kotlinGA

This library's goal is to provide generic Genetic Algorithm classes to create and test fitness, crossover, mutation and selection functions on. The functionality that is provided is dependent on the algorithm implemented.

The class's generic type when initialised is the type of the population members.

As an example, to create a test for the OnesProblem using the generic GA algorithm, you would do the following:

```kotlin
GA(col = initPopulation, fitness = ::fitness, crossover = ::crossover, mutation = ::mutation).run(iterations = 200, selectionMethod = selection, max = false)
```
where 
```kotlin
initPopulation
```
is expected to be a
```kotlin Collection<T>
```
where T is the generic type. In our test implementation, the OnesProblem type is represented as a String of 32 chars.
