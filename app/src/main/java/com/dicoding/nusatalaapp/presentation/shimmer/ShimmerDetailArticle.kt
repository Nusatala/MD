package com.dicoding.nusatalaapp.presentation.shimmer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.nusatalaapp.presentation.ui.theme.ArticleTypography

@Composable
fun ShimmerDetailArticle(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(32.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(24.dp))
                .shimmer()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 180.dp, max = 180.dp)
                .clip(RoundedCornerShape(16.dp))
                .shimmer(),
        )
        Spacer(modifier = Modifier.height(24.dp))
        for (i in 1..5) {
            Box(
                modifier = Modifier
                    .height(24.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(24.dp))
                    .shimmer()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}