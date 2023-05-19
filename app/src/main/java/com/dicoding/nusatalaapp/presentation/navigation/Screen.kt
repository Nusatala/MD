package com.dicoding.nusatalaapp.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Welcome : Screen("welcome")
}
