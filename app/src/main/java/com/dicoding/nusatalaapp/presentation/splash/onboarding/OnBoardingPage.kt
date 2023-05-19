package com.dicoding.nusatalaapp.presentation.splash.onboarding

import com.dicoding.nusatalaapp.R

sealed class OnBoardingPage(
    val imageId: Int,
    val titleId: Int,
    val descriptionId: Int,
) {
    object First : OnBoardingPage(
        imageId = R.drawable.onboarding_1,
        titleId = R.string.ob_title1,
        descriptionId = R.string.ob_desc1,
    )

    object Second : OnBoardingPage(
        imageId = R.drawable.onboarding_2,
        titleId = R.string.ob_title2,
        descriptionId = R.string.ob_desc2,
    )

    object Third : OnBoardingPage(
        imageId = R.drawable.onboarding_3,
        titleId = R.string.ob_title3,
        descriptionId = R.string.ob_desc3,
    )
}
