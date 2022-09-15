package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import ui.models.NQueensParameters

@Preview
@Composable
fun ParametersInputForm(modifier: Modifier = Modifier, onButtonTapped: (NQueensParameters) -> Unit = {}) {
    Column(modifier = modifier) {
        var n by remember { mutableStateOf("") }
        Input(labelText = R.NQueensParameterNames.N, value = n) { n = it}

        var initialTemperature by remember { mutableStateOf("") }
        Input(labelText = R.NQueensParameterNames.initialTemperature, value = initialTemperature) { initialTemperature = it }

        var finalTemperature by remember { mutableStateOf("") }
        Input(labelText = R.NQueensParameterNames.finalTemperature, value = finalTemperature) { finalTemperature = it }

        var alpha by remember { mutableStateOf("") }
        Input(labelText = R.NQueensParameterNames.alpha, value = alpha) { alpha = it }

        var stepsPerChange by remember { mutableStateOf("") }
        Input(labelText = R.NQueensParameterNames.stepsPerChange, value = stepsPerChange) { stepsPerChange = it }

        Button(
            onClick = {
                val parameters = NQueensParameters(
                    N = n.toIntOrNull() ?: 0,
                    initialTemperature = initialTemperature.toDoubleOrNull() ?: 0.0,
                    finalTemperature = finalTemperature.toDoubleOrNull() ?: 0.0,
                    alpha = alpha.toDoubleOrNull() ?: 0.0,
                    stepsPerChange = stepsPerChange.toIntOrNull() ?: 0
                )

                onButtonTapped.invoke(parameters)
            }
        ) {
            Text(R.ButtonsTitle.findSolution)
        }
    }
}

@Composable
fun Input(labelText: String, value: String, onValueChanged: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        label = { Text(labelText) }
    )
}