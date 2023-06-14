package com.dicoding.nusatalaapp.presentation.shimmer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerArticleCardItem(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isLoading) {
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
            Box {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .shimmer(),
                )
            }
        }
    }
}

