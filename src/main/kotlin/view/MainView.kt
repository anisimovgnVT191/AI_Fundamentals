package view

import controller.MainController
import javafx.geometry.Pos
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import tornadofx.*

class MainView: View() {
    // Properties
    private val controller: MainController by inject()

    // UI
    private val vwImageMatrix: ImageMatrixView by inject()
    private val vwNumberPicker: NumberPickerView by inject()
    private val vwNumbersProbabilities: NumberProbabilitiesView by inject()
    private var vwLearningRateInput: TextField by singleAssign()
    private var vwEpochCountInput: TextField by singleAssign()

    override val root = hbox {
        setPrefSize(800.0, 600.0)

        vbox {
            fitToParentHeight()

            alignment = Pos.TOP_LEFT
            hboxConstraints { marginTop = 16.0; marginLeft = 16.0; marginBottom = 16.0; marginRight = 16.0 }

            hbox {
                add(vwNumberPicker.apply {
                    setDidTapNumber { _, number ->
                        vwImageMatrix.imageMatrix = controller.getMatrixFor(number)
                    }
                })

                button {
                    fitToParentHeight()
                    hboxConstraints { marginLeft = 16.0 }
                    text = "Очистить"
                    action { vwImageMatrix.clear() }
                }
            }

            hbox {
                add(vwImageMatrix)

                button {
                    fitToParentHeight()
                    hboxConstraints { marginLeft = 16.0 }
                    text = "Распознать"
                    action { evaluateTapped() }
                }
            }

            hbox {
                fitToParentWidth()

                vbox {
                    hbox {
                        label(text = "Норма обучения")
                        vwLearningRateInput = textfield {
                            filterInput { it.controlNewText.isDouble() }
                        }
                    }
                    hbox {
                        label(text = "Количество эпох")
                        vwEpochCountInput = textfield {
                            filterInput { it.controlNewText.isInt() }
                        }
                    }
                }

                button {
                    fitToParentHeight()
                    text = "Обучить сеть"
                    action { trainTapped() }
                }
            }
        }

        vbox {
            fitToParentHeight()
            hboxConstraints { marginTop = 16.0; marginLeft = 16.0; marginBottom = 16.0; marginRight = 16.0 }
            add(vwNumbersProbabilities)
        }
    }

    // Actions
    private fun evaluateTapped() {
        root.runAsyncWithOverlay {
            controller.evaluate(vwImageMatrix.imageMatrix)
        }.ui { vwNumbersProbabilities.setNumbersProbabilities(it) }
    }

    private fun trainTapped() {
        root.runAsyncWithOverlay {
            controller.trainNetwork(
                learningRate = vwLearningRateInput.text.toDoubleOrNull() ?: MainController.learningRate,
                epochCount = vwEpochCountInput.text.toIntOrNull() ?: MainController.epochCount
            )
        }
    }
}