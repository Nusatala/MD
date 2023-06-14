package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopAppBarBase(
    title: String,
    filled: Boolean = false,
    onBackClicked: () -> Unit,
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        },
        backgroundColor = if (filled) MaterialTheme.colors.primary else Color.Transparent,
        contentColor = if (filled) MaterialTheme.colors.onPrimary else MaterialTheme.colors.primary,
        elevation = 0.dp
    )
}