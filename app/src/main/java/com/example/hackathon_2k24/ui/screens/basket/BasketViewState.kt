package com.example.hackathon_2k24.ui.screens.basket

import com.example.hackathon_2k24.domain.network.ResponseBean
import com.example.hackathon_2k24.ui.core.BaseState

data class BasketViewState(
    val basketItems: List<ResponseBean> = emptyList(),
    val total: Int = 0
) : BaseState