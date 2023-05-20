package com.dicoding.nusatalaapp.presentation.ui.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            modifier = modifier.weight(2f),
            text = "Login"
        )
        Column(
            modifier = modifier.weight(10f)
        ) {

        }
    }
}