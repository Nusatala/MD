package com.dicoding.nusatalaapp.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dicoding.nusatalaapp.R
import com.dicoding.nusatalaapp.domain.model.Article
import com.dicoding.nusatalaapp.presentation.shimmer.ShimmerArticleCardItem
import com.dicoding.nusatalaapp.presentation.shimmer.ShimmerArticleListItem
import com.dicoding.nusatalaapp.presentation.ui.components.ArticleCardItem
import com.dicoding.nusatalaapp.presentation.ui.components.SearchBarBase
import com.dicoding.nusatalaapp.presentation.ui.components.TopFiveArticleCard
import com.dicoding.nusatalaapp.presentation.ui.theme.InfoTypography
import com.dicoding.nusatalaapp.presentation.ui.theme.ProfileTypography
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.primary)

    val state = viewModel.state.collectAsState()
    val user = viewModel.user.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = modifier
                    .background(MaterialTheme.colors.primary)
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 48.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column(modifier = modifier) {
                            Text(text = "Selamat datang,", style = ProfileTypography.h6)
                            Text(text = user.value.name ?: "", style = ProfileTypography.h6)
                        }
                        AsyncImage(
                            model = user.value.photo,
                            contentDescription = "profile",
                            modifier = modifier
                                .sizeIn(
                                    minWidth = 64.dp,
                                    maxWidth = 64.dp,
                                    minHeight = 64.dp,
                                    maxHeight = 64.dp
                                )
                                .clip(
                                    RoundedCornerShape(50)
                                ),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Column(
                modifier = modifier
                    .fillMaxSize()
            ) {
                SearchBarBase(
                    query = "",
                    onQueryChange = {},
                    modifier = modifier
                        .offset(y = (-25).dp)
                        .padding(horizontal = 24.dp)
                )
                Text(text = "Untuk Kamu", modifier = modifier.padding(horizontal = 16.dp))
                LazyRow(
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (state.value.isLoading) {
                        items(5) {
                            ShimmerArticleCardItem(isLoading = state.value.isLoading)
                        }
                    } else {
                        items(state.value.latestArticles, key = { it.id ?: -1 }) { article ->
                            article.let {
                                TopFiveArticleCard(
                                    articleId = it.id ?: -1,
                                    imageUrl = it.image?.image ?: "",
                                    title = it.title ?: "",
                                    onItemClicked = {}
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = "Artikel")
                    SmallButtonBase(text = "Show more", onClick = {})
                }
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier.padding(bottom = 56.dp)
                ) {
                    if (state.value.isLoading) {
                        items(5) {
                            ShimmerArticleListItem(isLoading = state.value.isLoading)
                        }
                    } else {
                        items(state.value.articles, key = { it.id ?: -1 }) { article ->
                            article.let {
                                ArticleCardItem(
                                    articleId = it.id ?: -1,
                                    imageUrl = it.image?.image ?: "",
                                    title = it.title ?: "",
                                    body = it.body ?: "",
                                    views = it.views ?: -1,
                                    onItemClicked = {}
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SmallButtonBase(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background
        )
    ) {
        Text(
            text = text,
            style = InfoTypography.button
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}