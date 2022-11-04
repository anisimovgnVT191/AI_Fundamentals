package model

import org.jetbrains.bio.viktor.F64Array
import kotlin.math.exp
import kotlin.random.Random

typealias ImageVector = List<Boolean>

data class NeuralNetwork(
    var learningRate: Double,
    var epochCount: Int = EPOCH_COUNT,
    val countOfInputNeurons: Int,
    val countOfHiddenNeurons: Int,
    val countOfOutputNeurons: Int
) {
    // Properties
    // Веса
    private var hiddenWeights = F64Array(numRows = 1, numColumns = 1) { _, _ -> return@F64Array 0.0 }
    private var outputWeights = F64Array(numRows = 1, numColumns = 1) { _, _ -> return@F64Array 0.0 }

    // Активаторы
    private var inputs: List<Double> = emptyList()
    private var hidden: List<Double> = emptyList()
    private var target: List<Double> = emptyList()
    private var actual: List<Double> = emptyList()

    // Ошибки
    private var outputsErrors: List<Double> = emptyList()
    private var hiddenErrors: List<Double> = emptyList()

    // Public
    fun train(trainData: TrainModel) {
        var currentSample = 0

        initWeights()
        println("Just starting")
        (0..epochCount).forEach {
            if (currentSample == trainData.maxSamples) { currentSample = 0 }

            inputs = trainData.inputs[currentSample]
            target = trainData.target[currentSample]

            evaluate(inputs)

            backPropagate()

            currentSample++
        }

        println("All done")
    }

    fun evaluate(input: List<Double>): List<Double> {
        inputs = input

        // Вычислить вход в скрытый слой
        hidden = (0 until countOfHiddenNeurons)
            .map { hid ->
                inputs.foldIndexed(0.0) { inp, acc, d ->
                    acc + d * hiddenWeights.V[inp][hid]
                }
            }.map { it.sigmoid() }

        // Вычислить вход в выходной слой
        actual = (0 until countOfOutputNeurons)
            .map { out ->
                hidden.foldIndexed(0.0) { hid, acc, d ->
                    acc + d * outputWeights.V[hid][out]
                }
            }.map { it.sigmoid() }

        return actual
    }

    // Private
    private fun backPropagate() {
        // Вычисляем ошибку выходного слоя
        outputsErrors = (0 until countOfOutputNeurons)
            .map { out ->
                (target[out] - actual[out]) * actual[out].sigmoidDerivative()
            }

        // Вычисляем ошибку скрытого слоя
        hiddenErrors = (0 until countOfHiddenNeurons)
            .map { hid ->
                outputsErrors.foldIndexed(0.0) { out, acc, error ->
                    acc + error * outputWeights.V[hid][out]
                }
            }.mapIndexed { hid, d -> d * hidden[hid] }

        // Обновляем веса для выходного слоя
        outputsErrors.forEachIndexed { out, error ->
            hidden.forEachIndexed { hid, activator ->
                outputWeights.V[hid][out] += learningRate * error * activator
            }
        }

        // Обнволяем веса для скрытого слоя
        hiddenErrors.forEachIndexed { hid, error ->
            inputs.forEachIndexed { inp, activator ->
                hiddenWeights.V[inp][hid] += learningRate * error * activator
            }
        }
    }

    private fun initWeights() {
        hiddenWeights = F64Array(numRows = countOfInputNeurons, numColumns = countOfHiddenNeurons) { _, _ ->
            return@F64Array Random.nextDouble(from = -0.5, until = 0.5)
        }
        outputWeights = F64Array(numRows = countOfHiddenNeurons, numColumns = countOfOutputNeurons) { _, _ ->
            return@F64Array Random.nextDouble(from = -0.5, until = 0.5)
        }
    }

    private fun Double.sigmoid(): Double { return 1.0 / (1.0 + exp(-this)) }

    private fun Double.sigmoidDerivative(): Double { return this * (1.0 - this) }

    // Internal classes
    data class TrainModel(
        val inputs: List<List<Double>>,
        val target: List<List<Double>>
    ) {
        val maxSamples: Int
            get() = inputs.size

        init {
            assert(inputs.size == target.size)
        }
    }

    // Static
    companion object {
        private const val EPOCH_COUNT = 1_000_000
    }
}
