package com.dicoding.nusatalaapp.presentation.product

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dicoding.nusatalaapp.presentation.ui.components.ButtonBase
import com.dicoding.nusatalaapp.presentation.ui.components.TopAppBarBase
import com.dicoding.nusatalaapp.presentation.ui.theme.Orange700
import com.dicoding.nusatalaapp.presentation.ui.theme.ProductTypography
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailProductScreen(
    id: Int,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val state = viewModel.state.collectAsState()

    viewModel.getProductById(id)
    Scaffold(
        topBar = {
            TopAppBarBase(title = "Detail Produk", filled = true, onBackClicked = navigateBack)
        }
    ) {
        if (state.value.product?.id != null) {
            val product = state.value.product
            DetailProductContent(
                image = product?.thumbnail ?: "",
                name = product?.name ?: "",
                price = product?.price ?: "",
                rating = product?.rating ?: 0f,
                link = product?.link ?: "",
                description = product?.description ?: ""
            )
        }
    }
}

@Composable
fun DetailProductContent(
    image: String,
    name: String,
    price: String,
    rating: Float,
    link: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Box {
            AsyncImage(
                model = image,
                contentDescription = name,
                contentScale = ContentScale.Crop,
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
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(text = name, style = ProductTypography.subtitle1)
            Divider(color = MaterialTheme.colors.primary, thickness = 1.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Rp. $price", style = ProductTypography.body1)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "rating",
                        tint = Orange700
                    )
                    Text(text = rating.toString())
                }

            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Deskripsi", style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = description, style = ProductTypography.body1)
            ButtonBase(text = "Beli", onClick = {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(link)
                context.startActivity(intent)
            })
        }
    }
}