package com.dicoding.nusatalaapp.presentation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Register : Screen("register")
    object Articles : Screen("articles")
    object Article : Screen("articles/{articleId}") {
        fun createRoute(id: Long) = "articles/$id"
    }
}
