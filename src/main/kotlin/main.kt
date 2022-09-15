import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.ChessBoard
import ui.MainScreen
import kotlin.system.measureTimeMillis

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Compose test",
        state = rememberWindowState(width = 1280.dp, height = 720.dp)
    ) {
        MaterialTheme {
            MainScreen()
        }
    }
}