package com.example.hackathon_2k24.ui.naivgation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.hackathon_2k24.ui.screens.ScreenRoutes

@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = ScreenRoutes.App.route
    ) {
        navGraph(navController)
    }
}







