package view

import javafx.collections.FXCollections
import tornadofx.*
import tornadofx.Stylesheet.Companion.tableView
import view.internalComponents.Number

class NumberProbabilitiesView: View() {
    // Properties
    private val numbersProbabilities = mutableListOf<Model>().asObservable()

    // Setters
    fun setNumbersProbabilities(probabilities: List<Double>) {
        probabilities
            .mapIndexed { number, probability -> Model(Number.from(number), probability) }
            .apply { numbersProbabilities.clear(); numbersProbabilities.addAll(this) }
    }

    // View overrides
    override val root = tableview(numbersProbabilities) {
        readonlyColumn("Цифра", Model::number)
        readonlyColumn("Вероятность", Model::probability)
        columnResizePolicy = SmartResize.POLICY
    }

    // Inner classes
    data class Model(val number: Number, val probability: Double)
}