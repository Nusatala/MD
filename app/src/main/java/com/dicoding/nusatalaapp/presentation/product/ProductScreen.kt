package com.dicoding.nusatalaapp.presentation.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dicoding.nusatalaapp.presentation.ui.components.SearchBarBase
import com.dicoding.nusatalaapp.presentation.ui.theme.Orange700
import com.dicoding.nusatalaapp.presentation.ui.theme.ProductTypography

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
    viewModel: ProductViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp, top = 8.dp)
    ) {
        SearchBarBase(query = "", onQueryChange = {})
        Spacer(modifier = Modifier.height(16.dp))
        if (state.value.products.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                content = {
                    items(state.value.products, key = { it.id }) {
                        ProductCardItem(
                            id = it.id,
                            image = it.thumbnail,
                            name = it.name,
                            price = it.price,
                            rating = it.rating,
                            navigateToDetail = navigateToDetail
                        )
                    }
                },
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(bottom = 56.dp)
            )
        }
    }
}

@Composable
fun ProductCardItem(
    id: Int,
    image: String,
    name: String,
    price: String,
    rating: Float,
    navigateToDetail: (Int) -> Unit,
) {
    Surface(
        modifier = Modifier
            .sizeIn(maxHeight = 224.dp, minHeight = 224.dp, maxWidth = 164.dp, minWidth = 164.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navigateToDetail(id)
                }
                .padding(8.dp),
        ) {
            AsyncImage(
                model = image,
                contentDescription = name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = name,
                    style = ProductTypography.subtitle1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "Rp. $price", style = ProductTypography.body1)
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "rating",
                            tint = Orange700
                        )
                        Text(text = rating.toString(), style = ProductTypography.body2)
                    }
                }
            }
        }
    }
}