package com.example.hackathon_2k24.ui.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<I : BaseIntent, S : BaseState> : ViewModel() {

    private val _state = MutableStateFlow(initialState())
    val state: StateFlow<S> get() = _state.asStateFlow()

    protected abstract fun initialState(): S
    protected abstract fun handleIntent(intent: I)

    fun processIntent(intent: I) {
        handleIntent(intent)
    }

    protected fun updateState(reducer: S.() -> S) {
        _state.update(reducer)
    }
}