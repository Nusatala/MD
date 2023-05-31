package com.dicoding.nusatalaapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.nusatalaapp.presentation.article.ArticleScreen
import com.dicoding.nusatalaapp.presentation.article.DetailArticleScreen
import com.dicoding.nusatalaapp.presentation.home.HomeScreen
import com.dicoding.nusatalaapp.presentation.navigation.NavigationItem
import com.dicoding.nusatalaapp.presentation.navigation.Screen
import com.dicoding.nusatalaapp.presentation.splash.SplashScreen
import com.dicoding.nusatalaapp.presentation.splash.onboarding.WelcomeScreen
import com.dicoding.nusatalaapp.presentation.ui.auth.login.LoginScreen
import com.dicoding.nusatalaapp.presentation.ui.auth.register.RegisterScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NusatalaApp(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomNav(navController = navController)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Articles.route
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
                val articleId = it.arguments?.getLong("articleId") ?: -1L
                DetailArticleScreen(imageUrl = "", title = articleId.toString(), body = "")
            }

            composable(
                route = Screen.Cart.route
            ) {
                Text(text = Screen.Cart.route)
            }

            composable(route = Screen.Camera.route) {
                Text(text = Screen.Camera.route)
            }

            composable(route = Screen.Quiz.route) {
                Text(text = Screen.Quiz.route)
            }

            composable(route = Screen.Account.route) {
                Text(text = Screen.Account.route)
            }
        }
    }
}

@Composable
fun BottomNav(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(modifier = modifier) {
        val navBackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackEntry?.destination?.route

        val navigationItem = listOf(
            NavigationItem(
                title = "Home",
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = "Toko",
                icon = Icons.Default.ShoppingCart,
                screen = Screen.Cart
            ),
            NavigationItem(
                title = "Scan",
                icon = Icons.Default.Camera,
                screen = Screen.Camera
            ),
            NavigationItem(
                title = "Quiz",
                icon = Icons.Default.Quiz,
                screen = Screen.Quiz
            ),
            NavigationItem(
                title = "Akun",
                icon = Icons.Default.Person,
                screen = Screen.Account
            )
        )

        BottomNavigation() {
            navigationItem.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(text = item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}
