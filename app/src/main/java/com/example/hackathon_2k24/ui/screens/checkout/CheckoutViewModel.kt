package com.example.hackathon_2k24.ui.screens.checkout

import com.example.hackathon_2k24.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(

) : BaseViewModel<
        CheckoutIntent,
        CheckoutViewState>() {
    override fun initialState(): CheckoutViewState = CheckoutViewState()

    override fun handleIntent(intent: CheckoutIntent) {
        when (intent) {
            CheckoutIntent.Decrement -> {}
            CheckoutIntent.Increment -> {}
        }
    }
}