package ui

import NQueensProblem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatisticForm(problem: NQueensProblem) {
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Text(text = R.Statistic.elapsedTime((problem.timeElapsed / 1000.0).toString()))

        Text(text = R.Statistic.bestEnergySolution(problem.bestSolutionEnergy.toString()))

        Text(text = R.Statistic.acceptedSolution(problem.numberOfAcceptedSolutions.toString()))
    }
}