package com.example.hackathon_2k24.ui.screens.home

import android.content.SharedPreferences
import androidx.lifecycle.viewModelScope
import com.example.hackathon_2k24.domain.network.BaseService
import com.example.hackathon_2k24.domain.network.ResponseBean
import com.example.hackathon_2k24.ui.core.BaseViewModel
import com.example.hackathon_2k24.ui.screens.basket.BasketIntent
import com.example.hackathon_2k24.ui.screens.basket.BasketViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val remote: BaseService,
    private val preference: SharedPreferences,
): BaseViewModel<
        HomeIntent,
        HomeViewState>() {
    override fun initialState(): HomeViewState = HomeViewState()

    init {
        processIntent(HomeIntent.LoadBasket)
    }
    override fun handleIntent(intent: HomeIntent) {
        when (intent) {
            is HomeIntent.Search -> {
                updateState {
                    copy(
                        query = intent.query,
                        items = if (intent.query.isEmpty()) state.value.items else state.value.items.filter {
                            it.name.contains(intent.query, ignoreCase = true)
                        },
                        isSearching = intent.query.isNotEmpty()
                    )
                }
            }
            HomeIntent.ClearSearch -> {
                updateState {
                    copy(query = "", items = state.value.items, isSearching = false)
                }
            }

            HomeIntent.LoadBasket -> {
                loadBasket()
            }
        }
    }
    private fun loadBasket() {
        viewModelScope.launch {
            var items = listOf<ResponseBean>()

            val response = remote.products()

            if (response.isSuccessful && response.body() != null) {
                items = response.body().orEmpty()
            }

            val total = items.sumOf { it.price }
            updateState {
                copy(items = items, total = total)
            }
        }
    }

}