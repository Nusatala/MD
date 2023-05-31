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
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val articles = listOf(
        Article(
            id = 1,
            title = "Article 1",
            body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            imageUrl = "https://i.pinimg.com/550x/3c/81/b4/3c81b4177207d864fd59b2637bc600a7.jpg",
            views = 100
        ),
        Article(
            id = 2,
            title = "Article 2",
            body = "Praesent convallis eros eu turpis hendrerit, ac ullamcorper elit consequat.",
            imageUrl = "https://i.pinimg.com/736x/ca/4a/62/ca4a620f024a8fe4159c9412810bea24.jpg",
            views = 200
        ),
        Article(
            id = 3,
            title = "Article 3",
            body = "Vivamus eget nunc commodo, auctor erat a, aliquet sapien.",
            imageUrl = "https://i.pinimg.com/564x/26/b1/6e/26b16ef772023a5862b6f7b842072147.jpg",
            views = 150
        ),
        Article(
            id = 4,
            title = "Article 4",
            body = "Nullam dictum justo sed neque fermentum hendrerit.",
            imageUrl = "https://cerdika.com/wp-content/uploads/2020/03/Siter-760x626.jpg",
            views = 300
        ),
        Article(
            id = 5,
            title = "Article 5",
            body = "Suspendisse id elit eget tellus feugiat maximus.",
            imageUrl = "https://i.pinimg.com/236x/c7/8a/80/c78a800271a07f051e1a191559e8a020--tarian-indonesia.jpg",
            views = 250
        ),
        Article(
            id = 6,
            title = "Article 6",
            body = "Fusce eleifend eros sed nisi rutrum varius.",
            imageUrl = "https://i.pinimg.com/originals/d2/c9/8b/d2c98ba25bcee2d39f7494e9dc95cdbb.jpg",
            views = 180
        ),
        Article(
            id = 7,
            title = "Article 7",
            body = "Maecenas ultrices sapien in lorem finibus consequat.",
            imageUrl = "https://i.pinimg.com/736x/2c/d0/76/2cd076de1e3f4de5aa86ea506656496d.jpg",
            views = 400
        ),
        Article(
            id = 8,
            title = "Article 8",
            body = "Aenean lobortis nunc nec libero rhoncus, id mattis dui accumsan.",
            imageUrl = "https://i.pinimg.com/736x/2c/d0/76/2cd076de1e3f4de5aa86ea506656496d.jpg",
            views = 280
        ),
        Article(
            id = 9,
            title = "Article 9",
            body = "Cras ultricies elit eget ipsum pellentesque auctor.",
            imageUrl = "https://i.pinimg.com/736x/2c/d0/76/2cd076de1e3f4de5aa86ea506656496d.jpg",
            views = 320
        ),
        Article(
            id = 10,
            title = "Article 10",
            body = "Aliquam non velit in urna tincidunt posuere.",
            imageUrl = "https://i.pinimg.com/736x/2c/d0/76/2cd076de1e3f4de5aa86ea506656496d.jpg",
            views = 22099
        )
    )
    Scaffold(
        topBar = {
            Surface(
                color = Color.Transparent,
                elevation = 0.dp, // Remove shadow
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(articles, key = { it.id }) {
                ArticleCardItem(
                    articleId = it.id,
                    imageUrl = it.imageUrl,
                    title = it.title,
                    body = it.body,
                    views = it.views,
                    onItemClicked = navigateToDetail
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleScreenPreview() {
    ArticleScreen({})
}