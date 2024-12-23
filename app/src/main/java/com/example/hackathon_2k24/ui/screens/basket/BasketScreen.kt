package com.example.hackathon_2k24.ui.screens.basket

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hackathon_2k24.R
import com.example.hackathon_2k24.domain.network.ResponseBean
import com.example.hackathon_2k24.ui.theme.BaseTheme
import com.example.hackathon_2k24.ui.theme.DarkLightPreviews

@Composable
fun BasketScreen(viewModel: BasketViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()
    MainView(
        state = state,
        onIntent = { intent -> viewModel.processIntent(intent) }
    )
}

@Composable
fun MainView(
    state: BasketViewState,
    onIntent: (BasketIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Go back")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "My Basket",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.weight(1f),
                color = Color(0xFFf57c00)
            )
        }

        // Basket Items
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(state.basketItems) { item ->
                BasketItemRow(
                    item = item,
                    onIncrement = {  },
                    onDecrement = { }
                )
            }
        }

        // Total and Checkout
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Total",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = "₦ ${state.total}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFF512DA8)
                )
            }

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(Color(0xFFFFA000)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = "Checkout",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Composable
fun BasketItemRow(
    item: ResponseBean,
    onIncrement: @Composable (ResponseBean) -> Unit,
    onDecrement: (ResponseBean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Item Image
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = item.name,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.width(16.dp))

        // Item Details
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF512DA8)
            )
            Text(
                text = "₦ ${item.price}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black
            )
        }

        // Increment and Decrement Buttons
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onDecrement(item) }) {
                Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Decrease item count")
            }
            Text(
                text = "${item.price / 20000}", // Calculate item count based on price
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            IconButton(onClick = { }) {
                Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Increase item count")
            }
        }
    }
}

@DarkLightPreviews
@Composable
fun CreateSearchAbhaScreenPreview() {
    BaseTheme {
        BasketScreen()
    }
}