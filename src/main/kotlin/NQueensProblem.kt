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
    var bestSolutionEnergy: Int = 100
        get() { return  bestSolution.energy }

    var timeElapsed: Long = 0
        private  set

    var bestSolution: List<Int>
        private set
    private var currentSolution: List<Int>
    private var workingSolution: List<Int>

    init {
        bestSolution = List(N) { return@List it }.tweakSolution()
        currentSolution = List(N) { return@List it }.shuffled()
        workingSolution = List(N) { return@List it }.tweakSolution()

    }
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
        var shouldSetNewArray = false

        workingSolution = currentSolution
        while (temperature > finalTemperature) {
            for (step in 1..stepsPerChange) {
                workingSolution = workingSolution.tweakSolution()

                if (workingSolution.energy <= currentSolution.energy) {
                    shouldSetNewArray = true
                } else {
                    if (Random.nextDouble(0.0, 1.0) < exp(-(workingSolution.energy - currentSolution.energy) / temperature)) {
                        shouldSetNewArray = true
                        numberOfAcceptedSolutions++
                    }
                }

                if (shouldSetNewArray) {
                    shouldSetNewArray = false
                    currentSolution = workingSolution

                    if (currentSolution.energy < bestSolution.energy) {
                        bestSolution = currentSolution
                    }
                } else {
                    workingSolution = currentSolution
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

        val x = Random.nextInt(until = size)
        var y: Int

        do{
            y = Random.nextInt(until = size)
        } while (x == y)

        val tmp = result[x]
        result[x] = result[y]
        result[y] = tmp

        return result
    }

    private fun List<Int>.shuffled(): List<Int> {
        var result = this.toMutableList()

        for (i in indices) {
            result = result.tweakSolution().toMutableList()
        }

        return result
    }
}