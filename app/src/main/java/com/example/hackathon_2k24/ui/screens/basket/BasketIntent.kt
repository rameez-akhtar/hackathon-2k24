package com.example.hackathon_2k24.ui.screens.basket

import com.example.hackathon_2k24.domain.network.ResponseBean
import com.example.hackathon_2k24.ui.core.BaseIntent

sealed class BasketIntent:BaseIntent {
    data object LoadBasket : BasketIntent()
    data class IncrementItem(val item: ResponseBean) : BasketIntent()
    data class DecrementItem(val item: ResponseBean) : BasketIntent()
    data object Checkout : BasketIntent()
    data object GoBack : BasketIntent()
}