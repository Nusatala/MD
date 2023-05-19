package com.dicoding.nusatalaapp.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.nusatalaapp.presentation.navigation.Screen
import com.dicoding.nusatalaapp.presentation.splash.SplashScreen
import com.dicoding.nusatalaapp.presentation.splash.onboarding.WelcomeScreen

@Composable
fun NusatalaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        composable(route = Screen.Welcome.route) {
            WelcomeScreen()
        }

        composable(route = Screen.Home.route) {
            HomeScreen()
        }

        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }
    }
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "testing")
    }
}