package com.dicoding.nusatalaapp.presentation.navigation

import android.net.Uri

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Welcome : Screen("welcome")
    object Login : Screen("login")
    object Register : Screen("register")
    object Articles : Screen("articles")
    object Article : Screen("articles/{articleId}") {
        fun createRoute(id: Int) = "articles/$id"
    }
    object Cart : Screen("cart")
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
    object Store : Screen("store")
    object DetailStore : Screen("store/{id}") {
        fun createRoute(id: Int) = "store/$id"
    }
    object Scan : Screen("scan")
    object ScanResult : Screen("scan/{uri}") {
        fun createRoute(uri: Uri): String {
            val encodedUri = Uri.encode(uri.toString())
            return "scan/$encodedUri"
        }
    }

    object ScanResultDetail : Screen("scan/{labelId}/detail") {
        fun createRoute(labelId: Int) = "scan/$labelId/detail"
    }
}
