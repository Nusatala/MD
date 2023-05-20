package com.dicoding.nusatalaapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.presentation.splash.onboarding.OnBoardingPage
import com.dicoding.nusatalaapp.presentation.ui.theme.InfoTypography

@Composable
fun OnBoardingBase(
    onBoardingPage: OnBoardingPage,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = onBoardingPage.imageId),
            contentDescription = null,
            modifier = modifier.size(312.dp)
        )
        Spacer(modifier = modifier.size(32.dp))
        Text(
            text = stringResource(id = onBoardingPage.titleId),
            style = InfoTypography.subtitle1,
            textAlign = TextAlign.Center,
            modifier = modifier.width(280.dp)
        )
        Spacer(modifier = modifier.size(16.dp))
        Text(
            text = stringResource(id = onBoardingPage.descriptionId),
            style = InfoTypography.body1,
            textAlign = TextAlign.Center,
            modifier = modifier.width(332.dp)
        )
    }    
}

@Composable
@Preview(showBackground = true)
fun DefaultPreview() {
    val onBoardingPage = OnBoardingPage.First
    OnBoardingBase(onBoardingPage)
}