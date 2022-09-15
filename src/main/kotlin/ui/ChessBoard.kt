package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChessBoard(modifier: Modifier, queensPositions: List<Int>) {
    Column(modifier = modifier) {
        for (i in queensPositions.indices) {
            Row {
                for (j in queensPositions.indices) {
                    val isLightSquare = i %2 == j % 2
                    val squareColor = if (isLightSquare) Color.White else Color.Blue

                    Box(modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .background(squareColor)
                    ) {
                        if (queensPositions[i] == j) {
                            Box(modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                                .background(Color.Green)
                                .padding(5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}