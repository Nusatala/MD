package com.dicoding.nusatalaapp.presentation.article

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.size.Size
import com.dicoding.nusatalaapp.presentation.shimmer.ShimmerDetailArticle
import com.dicoding.nusatalaapp.presentation.ui.components.TopAppBarBase
import com.dicoding.nusatalaapp.presentation.ui.theme.ArticleTypography

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailArticleScreen(
    modifier: Modifier = Modifier,
    articleId: Int,
    viewModel: DetailArticleViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val state = viewModel.state.collectAsState()

    viewModel.getArticleById(articleId)
    Scaffold(
        topBar = {
            TopAppBarBase(title = "Detail Article", filled = true, onBackClicked = navigateBack)
        }
    ) {
        if (state.value.isLoading) {
            ShimmerDetailArticle(isLoading = state.value.isLoading)
        } else
            if (state.value.article?.id != null) {
                val article = state.value.article
                DetailArticleContent(
                    title = article?.title ?: "",
                    imageUrl = article?.image?.image ?: "",
                    body = article?.body ?: "",
                    sources = article?.sources ?: ""
                )
            }
    }
}

@Composable
fun DetailArticleContent(
    title: String,
    imageUrl: String,
    body: String,
    sources: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        Text(text = title, modifier = Modifier.fillMaxWidth(), style = ArticleTypography.subtitle2)
        AsyncImage(
            model = imageUrl,
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp, max = 180.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop,
        )
        Text(text = body, modifier = Modifier.fillMaxWidth(), style = ArticleTypography.body2)
        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "Sumber", modifier = Modifier.fillMaxWidth())
        Text(text = sources, modifier = Modifier.fillMaxWidth(), style = ArticleTypography.caption)
    }
}