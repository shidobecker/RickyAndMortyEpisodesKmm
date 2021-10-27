package br.com.shido.rickyandmortyepisodeskmm.android.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CardContainer(
    modifier: Modifier = Modifier,
    contentColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    shape: Shape = RoundedCornerShape(6.dp),
    elevation: Dp = 1.dp,
    content: @Composable () -> Unit
) {
    Card(
        contentColor = contentColor,
        backgroundColor = backgroundColor,
        elevation = elevation,
        shape = shape,
        modifier = modifier,
        content = content
    )
}

