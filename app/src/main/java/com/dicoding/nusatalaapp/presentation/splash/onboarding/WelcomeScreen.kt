package com.dicoding.nusatalaapp.presentation.splash.onboarding

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dicoding.nusatalaapp.R
import com.dicoding.nusatalaapp.presentation.ui.components.OnBoardingBase
import com.dicoding.nusatalaapp.presentation.ui.theme.InfoTypography
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
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            pageCount = onBoardingPages.size,
            state = pagerState,
            modifier = modifier.weight(10f)
        ) { position ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxSize()
            ) {
                OnBoardingBase(onBoardingPage = onBoardingPages[position])
            }
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = onBoardingPages.size,
            activeColor = MaterialTheme.colors.primaryVariant,
            modifier = modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = modifier.weight(1f).padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            AnimatedVisibility(
                visible = pagerState.currentPage == 2,
            ) {
                Button(
                    modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    shape = CircleShape,
                    onClick = {},
                ) {
                    Text(
                        text = stringResource(R.string.start_btn_text),
                        style = InfoTypography.button
                    )
                }
            }
        }

    }

}