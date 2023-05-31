package com.dicoding.nusatalaapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.nusatalaapp.presentation.article.ArticleScreen
import com.dicoding.nusatalaapp.presentation.article.DetailArticleScreen
import com.dicoding.nusatalaapp.presentation.navigation.Screen
import com.dicoding.nusatalaapp.presentation.splash.SplashScreen
import com.dicoding.nusatalaapp.presentation.splash.onboarding.WelcomeScreen
import com.dicoding.nusatalaapp.presentation.ui.auth.login.LoginScreen
import com.dicoding.nusatalaapp.presentation.ui.auth.register.RegisterScreen

@Composable
fun NusatalaApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen(navController = navController)
        }

        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController)
        }

        composable(route = Screen.Articles.route) {
            ArticleScreen(navigateToDetail = { articleId ->
                navController.navigate(Screen.Article.createRoute(articleId))
            })
        }

        composable(
            route = Screen.Article.route,
            arguments = listOf(navArgument("articleId") {
                type = NavType.LongType
            })
        ) {
            val articleId =it.arguments?.getLong("articleId") ?: -1L
            DetailArticleScreen(imageUrl = "", title = articleId.toString(), body = "")
        }
    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "testing")
    }
}