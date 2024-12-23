package com.example.hackathon_2k24.ui.screens

sealed class ScreenRoutes(val route: String) {
    data object App : ScreenRoutes("app") {
        data object Home : ScreenRoutes("home")
        data object Basket : ScreenRoutes("basket")
        data object Checkout : ScreenRoutes("orderCompleted")
    }
}
