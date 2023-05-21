package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.presentation.ui.theme.InfoTypography

@Composable
fun ButtonBase(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 16.dp)
            .heightIn(min = 48.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
    ) {
        Text(
            text = text,
            style = InfoTypography.button
        )
    }
}