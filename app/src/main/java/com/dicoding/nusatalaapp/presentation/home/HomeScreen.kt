package com.dicoding.nusatalaapp.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.dicoding.nusatalaapp.R
import com.dicoding.nusatalaapp.domain.model.Article
import com.dicoding.nusatalaapp.presentation.ui.components.ArticleCardItem
import com.dicoding.nusatalaapp.presentation.ui.components.SearchBarBase
import com.dicoding.nusatalaapp.presentation.ui.components.TopFiveArticleCard
import com.dicoding.nusatalaapp.presentation.ui.theme.InfoTypography
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = MaterialTheme.colors.primary)

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
                            Text(text = "Selamat Pagi")
                            Text(text = "John Doe")
                        }
                        Image(
                            painter = painterResource(id = R.drawable.pohoto_profile),
                            contentDescription = "profilie",
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
//                    items(articles, key = { it.id }) {
//                        TopFiveArticleCard(
//                            articleId = it.id,
//                            imageUrl = it.imageUrl,
//                            title = it.title,
//                            onItemClicked = {}
//                        )
//                    }
                }
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = "Untuk Kamu")
                    SmallButtonBase(text = "Show more", onClick = {})
                }
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier.padding(bottom = 56.dp)
                ) {
//                    items(articles, key = { it.id }) {
//                        ArticleCardItem(
//                            articleId = it.id,
//                            imageUrl = it.imageUrl,
//                            title = it.title,
//                            body = it.body,
//                            views = it.views,
//                            onItemClicked = {}
//                        )
//                    }
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