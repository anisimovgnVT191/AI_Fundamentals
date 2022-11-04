package controller

import model.NeuralNetwork
import model.NumbersTemplatesService
import tornadofx.Controller
import view.internalComponents.Number

class MainController: Controller() {
    // Properties
    private val templatesService = NumbersTemplatesService()
    private var network = NeuralNetwork(
        learningRate = learningRate,
        epochCount = epochCount,
        countOfOutputNeurons = countOfOutputNeurons,
        countOfHiddenNeurons = countOfHiddenNeurons,
        countOfInputNeurons = countOfInputsNeurons
    )

    private val trainData = NeuralNetwork.TrainModel(
        inputs = Number.values().map { templatesService.getTemplateFor(it) },
        target = Number.values().map { number -> List(countOfOutputNeurons) { if (number.int == it) { 1.0 } else { 0.0 } } }
    )

    fun getMatrixFor(number: Number): List<Double> {
        return templatesService.getTemplateFor(number)
    }

    fun trainNetwork(learningRate: Double, epochCount: Int) {
        network.learningRate = learningRate
        network.epochCount = epochCount

        network.train(trainData)
    }

    fun evaluate(imageMatrix: List<Double>): List<Double> {
        return network.evaluate(imageMatrix)
    }

    // Static
    companion object {
        const val learningRate = 0.2
        const val epochCount = 10_000
        const val countOfInputsNeurons = 80
        const val countOfOutputNeurons = 10
        const val countOfHiddenNeurons = 40
    }
}