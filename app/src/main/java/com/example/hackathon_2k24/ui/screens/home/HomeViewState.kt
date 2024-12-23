package com.example.hackathon_2k24.ui.screens.home

import com.example.hackathon_2k24.domain.network.ResponseBean
import com.example.hackathon_2k24.ui.core.BaseState

data class HomeViewState(
    val items: List<ResponseBean> = emptyList(),
    val query: String = "",
    val isSearching: Boolean = false,
    val total: Int = 0
) : BaseState