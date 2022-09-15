package ui

import NQueensProblem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun MainScreen() {
    var queensPositions by remember { mutableStateOf(emptyList<Int>()) }
    var nQueensProblem by remember { mutableStateOf<NQueensProblem?>(null) }

    Column {
        Row {
           ChessBoard(
               modifier = Modifier.weight(2f),
               queensPositions = queensPositions)

            ParametersInputForm(
                modifier = Modifier.weight(1f)
            ) { nQueensParameters ->
                val problem = NQueensProblem(nQueensParameters)

                problem.computeWithTimeMeasurement()

                queensPositions = problem.bestSolution
                nQueensProblem = problem
            }

            nQueensProblem?.let { StatisticForm(it) }
        }
    }
}