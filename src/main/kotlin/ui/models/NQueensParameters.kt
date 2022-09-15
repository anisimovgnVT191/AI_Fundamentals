package ui.models

data class NQueensParameters (
    val N: Int,
    val initialTemperature: Double,
    val finalTemperature: Double,
    val alpha: Double,
    val stepsPerChange: Int
    )