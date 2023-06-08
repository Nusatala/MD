package com.dicoding.nusatalaapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dicoding.nusatalaapp.presentation.ui.theme.NusatalaAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NusatalaAppTheme {
                // A surface container using the 'background' color from the theme
                NusatalaApp()
            }
        }
    }
}
