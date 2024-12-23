package com.example.hackathon_2k24.ui.naivgation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.hackathon_2k24.ui.screens.ScreenRoutes
import com.example.hackathon_2k24.ui.screens.basket.BasketScreen
import com.example.hackathon_2k24.ui.screens.basket.BasketViewModel
import com.example.hackathon_2k24.ui.screens.checkout.CheckoutScreen
import com.example.hackathon_2k24.ui.screens.checkout.CheckoutViewModel
import com.example.hackathon_2k24.ui.screens.home.HomeScreen
import com.example.hackathon_2k24.ui.screens.home.HomeViewModel
import com.example.hackathon_2k24.utilities.sharedViewModel

fun NavGraphBuilder.navGraph(navController: NavHostController) {
    navigation(
        route = ScreenRoutes.App.route,
        startDestination = ScreenRoutes.App.Home.route
    ) {


        composable(
            ScreenRoutes.App.Home.route
        ) {
            val viewModel = it.sharedViewModel<HomeViewModel>(navController)
            HomeScreen()
        }

        composable(
            ScreenRoutes.App.Basket.route
        ) {
            val viewModel = it.sharedViewModel<BasketViewModel>(navController)
            BasketScreen()
        }

        composable(
            ScreenRoutes.App.Checkout.route
        ) {
            val viewModel = it.sharedViewModel<CheckoutViewModel>(navController)
            CheckoutScreen()
        }
    }

}

