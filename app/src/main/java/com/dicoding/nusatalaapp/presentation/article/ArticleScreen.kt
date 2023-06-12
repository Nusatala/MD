package com.dicoding.nusatalaapp.presentation.article

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.domain.model.Article
import com.dicoding.nusatalaapp.presentation.ui.components.ArticleCardItem
import com.dicoding.nusatalaapp.presentation.ui.components.SearchBarBase

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleScreen(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            Surface(
                color = Color.Transparent,
                elevation = 0.dp,
                contentColor = Color.White,
                modifier = modifier.padding(vertical = 8.dp)
            ) {
                TopAppBar(
                    title = { Text(text = "test") },
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = modifier
                                .fillMaxWidth()
                                .offset(x = (-16).dp)
                        ) {
                            SearchBarBase(
                                query = "",
                                onQueryChange = {},
                            )
                        }
                    },
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.primary,
                    elevation = 0.dp
                )
            }
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 12.dp, horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.padding(bottom = 56.dp)
        ) {
//            items(articles, key = { it.id }) {
//                ArticleCardItem(
//                    articleId = it.id,
//                    imageUrl = it.imageUrl,
//                    title = it.title,
//                    body = it.body,
//                    views = it.views,
//                    onItemClicked = navigateToDetail
//                )
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    ArticleScreen({})
}