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
    object Cart : Screen("cart")
    object Camera : Screen("camera")
    object Quiz : Screen("quiz")
    object DetailQuiz : Screen("quiz/{levelId}") {
        fun createRoute(id: Long) = "quiz/$id"
    }
    object Account : Screen("account")
    object DetailAccount : Screen("account/{accountId}") {
        fun createRoute(id: Long) = "account/$id"
    }
    object Setting : Screen("setting")
    object Faq : Screen("faq")
}
