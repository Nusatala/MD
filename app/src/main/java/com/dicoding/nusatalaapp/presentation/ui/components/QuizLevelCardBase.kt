package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.R

@Composable
fun QuizLevelCardBase(
    image: Int,
    title: String,
    progress: Int,
) {
    Card() {
        Row() {
            Image(
                painter = painterResource(id = R.drawable.pohoto_profile),
                contentDescription = "quiz img",
                modifier = Modifier.size(96.dp)
            )
            
            Column {
                Text(text = title)
                Text(text = "Selesai")
                LinearProgressIndicator(
                    progress = progress / 10f,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.PlayCircle, contentDescription = "play")
            }
        }
    }
}