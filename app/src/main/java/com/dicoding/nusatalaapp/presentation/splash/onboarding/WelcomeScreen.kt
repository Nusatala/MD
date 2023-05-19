package com.dicoding.nusatalaapp.presentation.splash.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.presentation.ui.components.OnBoardingBase
import com.google.accompanist.pager.HorizontalPagerIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
) {
    val onBoardingPages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
    )

    val pagerState = rememberPagerState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        HorizontalPager(
            pageCount = onBoardingPages.size,
            state = pagerState,
        ) { position ->
            OnBoardingBase(onBoardingPage = onBoardingPages[position])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = onBoardingPages.size,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = {},
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = CircleShape
        ) {
            Text(text = "Mulai")
        }
    }
}