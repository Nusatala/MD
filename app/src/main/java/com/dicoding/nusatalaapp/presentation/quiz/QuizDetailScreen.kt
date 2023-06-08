package com.dicoding.nusatalaapp.presentation.quiz

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.nusatalaapp.presentation.ui.components.TopAppBarBase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuizDetailScreen(
    id: Long,
    image: String,
    question: String,
    firstAnswer: String,
    secondAnswer: String,
    thirdAnswer: String,
    forthAnswer: String,
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBarBase(title = "Quiz x", onBackClicked = {})
        },
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                model = image,
                contentDescription = "question",
                modifier = Modifier.sizeIn(
                    minWidth = 300.dp,
                    minHeight = 300.dp,
                    maxWidth = 300.dp,
                    maxHeight = 300.dp
                )
            )

            Surface {
                Column() {
                    Text(text = question)
                    Row {
                        IconButton(
                            onClick = { },
                            modifier = Modifier.border(
                                1.dp,
                                Color.Gray,
                                shape = RoundedCornerShape(16.dp)
                            )
                        ) {
                            Row() {
                                CircleWithLetter(letter = 'A')
                                Text(text = firstAnswer)
                            }
                        }
                        IconButton(onClick = { }) {
                            Row() {
                                CircleWithLetter(letter = 'B')
                                Text(text = secondAnswer)
                            }
                        }
                    }
                    Row {
                        IconButton(onClick = { }) {
                            Row() {
                                CircleWithLetter(letter = 'C')
                                Text(text = thirdAnswer)
                            }
                        }
                        IconButton(onClick = { }) {
                            Row() {
                                CircleWithLetter(letter = 'D')
                                Text(text = forthAnswer)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CircleWithLetter(letter: Char) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(RoundedCornerShape(50))
            .background(Color.White)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(50)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = letter.toString(), color = MaterialTheme.colors.primary)
    }
}

@Preview(showBackground = true)
@Composable
fun CirclePrev() {
    CircleWithLetter(letter = 'A')
}

