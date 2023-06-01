package com.dicoding.nusatalaapp.presentation.account

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AccountScreen(
    imageUrl: String,
    name: String,
    email: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = name,
                modifier = modifier
                    .size(128.dp)
                    .clip(RoundedCornerShape(50)),
                contentScale = ContentScale.Crop,
            )
            Text(text = name)
            Text(text = email)
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .weight(2f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(MaterialTheme.colors.primary)
                .padding(vertical = 24.dp, horizontal = 20.dp)
                .border(1.dp, Color.Black),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = "Pengaturan")
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Default.ArrowRight,
                        contentDescription = "pengaturan",
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = "Frequently Asked Question")
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.ArrowRight, contentDescription = "faq")
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = "Tentang Nusatala")
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.ArrowRight, contentDescription = "about")
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier.fillMaxWidth()
            ) {
                Text(text = "Log Out")
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.ArrowRight, contentDescription = "logout")
                }
            }
        }
    }
}