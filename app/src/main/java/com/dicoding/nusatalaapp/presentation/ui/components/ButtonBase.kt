package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.presentation.ui.theme.InfoTypography

@Composable
fun ButtonBase(
    modifier: Modifier = Modifier,
    text: String,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 16.dp)
            .heightIn(min = 48.dp)
            .clickable { !isLoading },
        shape = RoundedCornerShape(10.dp),
//        enabled = !isLoading,
        onClick = onClick,
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = if (isLoading) "Loading" else text,
                style = InfoTypography.button,
                modifier = Modifier.align(Alignment.Center)
            )
            if (isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.CenterEnd)
                )
            }
        }
    }
}