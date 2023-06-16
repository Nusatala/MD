package com.dicoding.nusatalaapp.presentation.scan.detail

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dicoding.nusatalaapp.domain.model.Product
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.presentation.product.ProductCardItem
import com.dicoding.nusatalaapp.presentation.ui.components.TopAppBarBase
import com.dicoding.nusatalaapp.presentation.ui.theme.ArticleTypography
import com.dicoding.nusatalaapp.presentation.ui.theme.InfoTypography

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailResultScreen(
    labelId: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailResultViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val state = viewModel.state.collectAsState()

    viewModel.getArticleByLabelId(labelId)
    Scaffold(
        topBar = {
            TopAppBarBase(title = "Hasil Prediksi", filled = true, onBackClicked = navigateBack)
        }
    ) {
        val states = state.value
        if (states.article != null && states.products != null && states.tutorial != null) {
            val article = states.article
            val products = states.products
            val tutorial = states.tutorial
            DetailResultContent(
                imgUrl = article.image.image ?: "",
                name = article.name,
                regionalOrigin = article.regionalOrigin,
                materials = article.materials,
                history = article.body,
                tutorial = tutorial.link ?: "",
                communities = emptyList(),
                products = products,
            )
        }

    }
}

@Composable
fun DetailResultContent(
    imgUrl: String,
    name: String,
    regionalOrigin: String,
    materials: String,
    history: String,
    tutorial: String,
    communities: List<User>,
    products: List<Product>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            AsyncImage(
                model = imgUrl,
                contentDescription = name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 340.dp, max = 340.dp)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 340.dp, max = 340.dp)
                    .background(Color.Black.copy(alpha = 0.2f))
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .offset(y = (-16).dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(Color.White)
                .padding(top = 24.dp, bottom = 8.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = name,
                style = InfoTypography.subtitle1,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            DetailResultExpandableCardItem(subtitle = "Asal Daerah", body = regionalOrigin)
            DetailResultExpandableCardItem(subtitle = "Bahan Pembuatan", body = materials)
            DetailResultExpandableCardItem(subtitle = "Sejarah", body = history)
            Text(text = "Produk Terkait", modifier = Modifier.padding(horizontal = 12.dp))
            LazyRow(
                contentPadding = PaddingValues(12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(products, key = { it.id }) { product ->
                    ProductCardItem(
                        id = product.id,
                        image = product.thumbnail,
                        name = product.name,
                        price = product.price,
                        rating = product.rating,
                        navigateToDetail = {}
                    )
                }

            }

        }
    }
}

@Composable
fun DetailResultExpandableCardItem(
    subtitle: String,
    body: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = 1.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { }
                .padding(vertical = 12.dp, horizontal = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = subtitle,
                    modifier = Modifier.weight(1f),
                    style = ArticleTypography.subtitle1
                )
                IconButton(
                    onClick = { expanded = !expanded },
                ) {
                    val icon: ImageVector = if (expanded) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    }
                    Icon(imageVector = icon, contentDescription = "Expand/Collapse")
                }
            }
            AnimatedVisibility(visible = expanded) {
                Text(text = body, style = ArticleTypography.body1)
            }
        }
    }
}