package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun TopFiveArticleCard(
    articleId: Long,
    imageUrl: String,
    title: String,
    modifier: Modifier = Modifier,
    onItemClicked: (Long) -> Unit,
) {
    Surface(
        modifier = modifier
            .sizeIn(
                minWidth = 250.dp,
                maxWidth = 250.dp,
                maxHeight = 150.dp,
                minHeight = 120.dp
            ),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
    ) {
        Box(
            modifier = modifier.clickable {  }
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = modifier
                    .fillMaxSize()
                    .alpha(0.8f),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)) // Set the transparency level of the overlay
                    .padding(16.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title,
                    style = TextStyle(color = Color.White, fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }
}