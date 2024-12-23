package com.example.hackathon_2k24.ui.screens.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hackathon_2k24.ui.screens.basket.BasketIntent
import com.example.hackathon_2k24.ui.screens.basket.BasketViewModel
import com.example.hackathon_2k24.ui.screens.basket.BasketViewState
import com.example.hackathon_2k24.ui.theme.BaseTheme
import com.example.hackathon_2k24.ui.theme.DarkLightPreviews

@Composable
fun CheckoutScreen(viewModel: CheckoutViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    MainView(
        state = state,
        onIntent = { intent -> viewModel.processIntent(intent) }
    )
}

@Composable
fun MainView(
    state: CheckoutViewState,
    onIntent: (CheckoutIntent) -> Unit
) {
    Column {
        Text(
            text = "Counter: 1",
        )
        Button(onClick = { }) {
            Text("Increment")
        }
        Button(onClick = {  }) {
            Text("Decrement")
        }
    }
}

@DarkLightPreviews
@Composable
fun CreateSearchAbhaScreenPreview() {
    BaseTheme {
        CheckoutScreen()
    }
}