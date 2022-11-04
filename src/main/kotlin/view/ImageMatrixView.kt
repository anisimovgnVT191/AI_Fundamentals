package view

import javafx.scene.Parent
import javafx.scene.layout.Border
import javafx.scene.layout.BorderStroke
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.shape.Shape
import tornadofx.*
import kotlin.contracts.contract
import kotlin.math.abs

class ImageMatrixView: View() {
    // Properties
    private val _imageMatrix = MutableList(size = ROWS_COUNT * COLUMN_COUNT) { 0.0 }

    var imageMatrix: List<Double>
        get() { return _imageMatrix }
        set(value) {
            _imageMatrix.clear()
            _imageMatrix.addAll(value)
            updateUi()
        }

    // Inputs
    fun clear() {
        _imageMatrix.replaceAll { 0.0 }
        updateUi()
    }

    // View overrides
    override val root = vbox {
        (0 until ROWS_COUNT).forEach { row ->
            hbox {
                (0 until COLUMN_COUNT).forEach { column ->
                    rectangle {
                        fill = Color.WHITE
                        width = 36.0
                        height= 36.0
                        stroke = Color.DARKGRAY

                        setOnMouseClicked {
                            _imageMatrix[row * COLUMN_COUNT + column] = abs(_imageMatrix[row * COLUMN_COUNT + column] - 1.0)
                            updateUi()
                        }
                    }
                }
            }
        }
    }

    // MARK: - Private
    private fun updateUi() {
        (0 until ROWS_COUNT).forEach { row ->
            root.getChildList()?.getOrNull(row)?.let { rowLayout ->
                (0 until COLUMN_COUNT).forEach { column ->
                    (rowLayout.getChildList()?.getOrNull(column) as? Rectangle)?.apply {
                        fill = if (_imageMatrix[row * COLUMN_COUNT + column] == 1.0) { Color.BLACK } else { Color.WHITE }
                    }
                }
            }
        }
    }

    // Constants
    companion object {
        const val ROWS_COUNT = 10
        const val COLUMN_COUNT = 8
    }
}