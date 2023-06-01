package com.dicoding.nusatalaapp.presentation.article

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun DetailArticleScreen(
    imageUrl: String,
    title: String,
    body: String,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            Surface(
                color = Color.Transparent,
                elevation = 0.dp, // Remove shadow
                contentColor = Color.White,
                modifier = modifier.padding(vertical = 8.dp)
            ) {
                TopAppBarBase(title = "Detail Article", onBackClicked = {})
            }
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Text(text = title, modifier = modifier.fillMaxWidth())
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp, max = 150.dp)
                    .padding(horizontal = 56.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
            Text(text = body, modifier = modifier.fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailArticlePreview() {
    DetailArticleScreen(
        imageUrl = "",
        title = "Article 4",
        body = "Praesent convallis eros eu turpis hendrerit, ac ullamcorper elit consequat."
    )
}