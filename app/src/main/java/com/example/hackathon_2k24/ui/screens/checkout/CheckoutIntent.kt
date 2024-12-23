package com.example.hackathon_2k24.ui.screens.checkout

import com.example.hackathon_2k24.ui.core.BaseIntent

sealed class CheckoutIntent:BaseIntent {
    data object Increment : CheckoutIntent()
    data object Decrement : CheckoutIntent()
}