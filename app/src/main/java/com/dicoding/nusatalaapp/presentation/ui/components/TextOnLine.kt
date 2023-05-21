package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.presentation.ui.theme.Orange700

@Composable
fun TextOnLine(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 64.dp, bottom = 25.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            drawLine(
                color = Orange700,
                strokeWidth = 1.dp.toPx(),
                start = Offset(0f, size.height / 2),
                end = Offset(size.width, size.height / 2),
            )
        }
        Text(
            text = text,
            modifier = modifier
                .background(MaterialTheme.colors.background)
                .padding(horizontal = 8.dp)
                .align(Alignment.Center),
            color = MaterialTheme.colors.primaryVariant,
        )
    }
}