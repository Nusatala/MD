package com.dicoding.nusatalaapp.presentation.account

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.dicoding.nusatalaapp.presentation.ui.theme.AccountTypography

@Composable
fun AccountScreen(
    modifier: Modifier = Modifier,
    navigateToSetting: () -> Unit,
    navigateToFaq: () -> Unit,
    onSuccessLogout: () -> Unit,
    viewModel: AccountViewModel = hiltViewModel(),
) {
    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        val state = viewModel.user.collectAsState()
        val status = viewModel.logoutStatus.collectAsState()

        LaunchedEffect(status.value) {
            if (status.value) {
                Log.d("userLogin", "success logout")
                onSuccessLogout()
            }
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AsyncImage(
                model = state.value.photo,
                contentDescription = state.value.name,
                modifier = modifier
                    .size(128.dp)
                    .clip(RoundedCornerShape(50)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = state.value.name ?: "", style = AccountTypography.subtitle1)
            Text(text = state.value.email ?: "", style = AccountTypography.body1)
        }
        Column(
            modifier = modifier
                .fillMaxSize()
                .weight(2f)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(MaterialTheme.colors.primary)
                .padding(vertical = 24.dp, horizontal = 20.dp),
        ) {
            AccountContent(text = "Pengaturan", onItemClicked = navigateToSetting)
            AccountContent(text = "FAQ", onItemClicked = navigateToFaq)
            AccountContent(text = "Logout", onItemClicked = {
                viewModel.logout()
            })
        }
    }
}

@Composable
fun AccountContent(
    modifier: Modifier = Modifier,
    text: String,
    onItemClicked: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(text = text, style = AccountTypography.h6)
        IconButton(onClick = onItemClicked) {
            Icon(
                imageVector = Icons.Default.ArrowRight,
                contentDescription = text,
                tint = Color.White
            )
        }
    }
}