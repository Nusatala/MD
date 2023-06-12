package com.dicoding.nusatalaapp.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val InfoTypography = Typography(
    subtitle1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = Typography.h6.fontWeight,
        fontSize = Typography.h6.fontSize,
        color = Orange500
    ),
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = Typography.subtitle1.fontWeight,
        fontSize = Typography.subtitle1.fontSize,
        color = Orange700
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = Typography.subtitle1.fontWeight,
        fontSize = 14.sp,
        color = Color.Red
    )
)
