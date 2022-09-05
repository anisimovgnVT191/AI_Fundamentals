import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val nQueensProblem = NQueensProblem(
        N = 5,
        initialTemperature = 60.0,
        finalTemperature = 0.5,
        alpha = 0.98,
        stepsPerChange = 100
    )

    val timeOfExecution = measureTimeMillis { nQueensProblem.compute() }

    println(timeOfExecution.toDouble() / 1000)
    println(nQueensProblem.numberOfAcceptedSolutions)

}