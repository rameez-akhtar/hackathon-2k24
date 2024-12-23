package com.example.hackathon_2k24.ui.screens.basket

import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import com.example.hackathon_2k24.domain.network.BaseService
import com.example.hackathon_2k24.domain.network.ResponseBean
import com.example.hackathon_2k24.ui.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val remote: BaseService,
    private val preference: SharedPreferences,
) : BaseViewModel<
        BasketIntent,
        BasketViewState>() {
    override fun initialState(): BasketViewState = BasketViewState()

    init {
        processIntent(BasketIntent.LoadBasket)
    }

    override fun handleIntent(intent: BasketIntent) {
        when (intent) {
            is BasketIntent.LoadBasket -> loadBasket()
            is BasketIntent.IncrementItem -> updateItemCount(intent.item, increment = true)
            is BasketIntent.DecrementItem -> updateItemCount(intent.item, increment = false)
            BasketIntent.Checkout -> checkout()
            BasketIntent.GoBack -> goBack()
        }
    }

    private fun loadBasket() {
        viewModelScope.launch {
            var items = listOf<ResponseBean>()
            // Proceed with AUTH Init
            val response = remote.products()

            if (response.isSuccessful && response.body() != null) {
                items = response.body().orEmpty()
            }

            val total = items.sumOf { it.price }
            updateState {
                copy(basketItems = items, total = total)
            }
        }
    }

    private fun updateItemCount(item: ResponseBean, increment: Boolean) {
        viewModelScope.launch {
            val updatedItems = state.value.basketItems.map {
                if (it.id == item.id) {
                    val newPrice = if (increment) it.price + 20000 else it.price - 20000
                    it.copy(price = newPrice)
                } else {
                    it
                }
            }.filter { it.price > 0 } // Remove items with price <= 0
            val newTotal = updatedItems.sumOf { it.price }
            updateState {
                copy(basketItems = updatedItems, total = newTotal)
            }
        }
    }

    private fun checkout() {
        // Implement checkout logic
    }

    private fun goBack() {
        // Handle back navigation
    }
}