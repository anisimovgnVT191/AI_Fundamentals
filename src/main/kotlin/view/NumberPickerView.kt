package view

import javafx.event.EventHandler
import tornadofx.*
import view.internalComponents.Number
import java.awt.event.ActionEvent

class NumberPickerView: View() {
    // Callbacks
    private var didTapNumber: DidTapNumber? = null

    // Setters
    fun setDidTapNumber(action: DidTapNumber) {
        didTapNumber = action
    }

    // View overrides
    override val root = vbox {
        for (row in listOf(0, 5)) {
            hbox {
                for (number in row until row + 5) {
                    button {
                        text = Number.from(number).int.toString()
                        setPrefSize(48.0, 48.0)
                        hboxConstraints { marginBottom = 8.0; marginRight = 8.0 }
                        setOnAction { didTapNumber?.invoke(view = this@NumberPickerView, number = Number.from(number)) }
                    }
                }
            }
        }
    }


    // Inner interface
    fun interface DidTapNumber {
        fun invoke(view: NumberPickerView, number: Number)
    }
}