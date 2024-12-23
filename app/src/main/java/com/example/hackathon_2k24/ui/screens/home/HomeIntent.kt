package com.example.hackathon_2k24.ui.screens.home

import com.example.hackathon_2k24.ui.core.BaseIntent
import com.example.hackathon_2k24.ui.screens.basket.BasketIntent

sealed class HomeIntent:BaseIntent {
    data object LoadBasket : HomeIntent()
    data class Search(val query: String) : HomeIntent()
    data object ClearSearch : HomeIntent()
}