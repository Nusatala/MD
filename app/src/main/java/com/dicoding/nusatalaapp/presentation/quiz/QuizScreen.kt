package com.dicoding.nusatalaapp.presentation.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.dicoding.nusatalaapp.R
import com.dicoding.nusatalaapp.domain.model.QuizLevel

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
) {
    val quizLevels = listOf(
        QuizLevel(
            image = 1,
            title = "Level 1",
            progress = 3
        ),
        QuizLevel(
            image = 1,
            title = "Level 2",
            progress = 7
        ),
        QuizLevel(
            image = 1,
            title = "Level 3",
            progress = 10
        )
    )
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 56.dp)
    ) {
        items(quizLevels, key = { it.title }) {
            QuizLevelCardBase(
                image = it.image,
                title = it.title,
                progress = it.progress
            )
        }
    }
}

@Composable
fun QuizLevelCardBase(
    image: Int,
    title: String,
    progress: Int,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .sizeIn(maxHeight = 120.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.pohoto_profile),
                contentDescription = "quiz img",
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(text = title)
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "Selesai")
                    LinearProgressIndicator(
                        progress = progress / 10f,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .sizeIn(minWidth = 80.dp, maxWidth = 80.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.PlayCircle,
                        contentDescription = "play",
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizLevelCardPreview() {
    QuizLevelCardBase(image = 1, title = "Level Mudah", progress = 2)
}