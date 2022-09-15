import ui.models.NQueensParameters
import kotlin.math.exp
import kotlin.random.Random
import kotlin.system.measureTimeMillis

data class NQueensProblem(
    val N: Int,
    val initialTemperature: Double,
    val finalTemperature: Double,
    val alpha: Double,
    val stepsPerChange: Int
) {
    var numberOfAcceptedSolutions = 0
        private set
    var bestSolution = emptyList<Int>()
        private set
    var bestSolutionEnergy: Int = 100
        get() { return  bestSolution.energy }

    var timeElapsed: Long = 0
        private  set

    constructor(parameters: NQueensParameters) : this(
        N = parameters.N,
        initialTemperature = parameters.initialTemperature,
        finalTemperature = parameters.finalTemperature,
        alpha = parameters.alpha,
        stepsPerChange = parameters.stepsPerChange
    )

    fun computeWithTimeMeasurement() {
        timeElapsed = measureTimeMillis {
            compute()
        }
    }

    fun compute() {
        var temperature = initialTemperature
        var currentSolution = List(N) { return@List it }
        var shouldSetNewArray = false

        while (temperature > finalTemperature) {
            for (step in 1..stepsPerChange) {
                val working = currentSolution.shuffled()

                if (working.energy <= currentSolution.energy) {
                    shouldSetNewArray = true
                } else {
                    if (Random.nextDouble(0.0, 1.0) < exp(-(working.energy - currentSolution.energy) / temperature)) {
                        shouldSetNewArray = true
                        numberOfAcceptedSolutions++
                    }
                }

                if (shouldSetNewArray) {
                    shouldSetNewArray = false
                    currentSolution = working

                    if (currentSolution.energy < bestSolution.energy) {
                        bestSolution = currentSolution
                    }
                }
            }
            temperature *= alpha
        }
    }

    private val List<Int>.energy: Int
        get() {
            if (size == 0) return 100

            var conflicts = 0
            val dx = listOf(-1, 1, -1, 1)
            val dy = listOf(-1, 1, 1, -1)

            forEachIndexed { x, y ->
                dx.zip(dy).forEach {
                    var tmpX = x
                    var tmpY = y

                    while (true) {
                        tmpX += it.first
                        tmpY += it.second

                        if (tmpX < 0 || tmpX >= size || tmpY < 0 || tmpY >= size) {
                            break
                        }
                        if (get(tmpX) == tmpY) {
                            ++conflicts
                        }
                    }
                }
            }

            return  conflicts
        }

    private fun List<Int>.tweakSolution(): List<Int> {
        var result = this.toMutableList()

        for (i in indices) {
            var x: Int = Random.nextInt(until = size)
            var y: Int

            do {
                y = Random.nextInt(until = size)
            } while (x == y)

            var tmp = result[x]
            result[x] = y
            result[y] = tmp
        }

        return result
    }
}