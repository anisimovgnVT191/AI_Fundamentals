package ui

import NQueensProblem
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun StatisticForm(problem: NQueensProblem) {
    Column {
        Text(text = R.Statistic.elapsedTime((problem.timeElapsed / 1000).toString()))

        Text(text = R.Statistic.bestEnergySolution(problem.bestSolutionEnergy.toString()))

        Text(text = R.Statistic.acceptedSolution(problem.numberOfAcceptedSolutions.toString()))
    }
}