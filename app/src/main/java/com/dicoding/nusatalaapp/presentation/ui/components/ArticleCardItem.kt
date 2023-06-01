package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun ArticleCardItem(
    articleId: Long,
    imageUrl: String,
    title: String,
    body: String,
    views: Int,
    modifier: Modifier = Modifier,
    onItemClicked: (Long) -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .sizeIn(maxHeight = 120.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = modifier.fillMaxSize()
                .clickable {
                    onItemClicked(articleId)
                },
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = modifier.sizeIn(minWidth = 120.dp, maxWidth = 120.dp)
            )
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 12.dp, horizontal = 8.dp)
            ) {
                Text(
                    text = title,
                    modifier = modifier.weight(1f)
                )
                Text(
                    text = body,
                    modifier = modifier.weight(2f),
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        imageVector = Icons.Default.RemoveRedEye,
                        contentDescription = "views",
                        modifier = modifier.size(16.dp)
                    )
                    Spacer(modifier = modifier.size(4.dp))
                    Text(
                        text = views.toString(),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleCardItemPreview() {
    ArticleCardItem(
        articleId = 1,
        imageUrl = "",
        title = "Ichigoat",
        body = "asdfasdfasdf",
        views = 1000,
        onItemClicked = {}
    )
}