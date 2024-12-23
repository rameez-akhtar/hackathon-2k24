package com.example.hackathon_2k24.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hackathon_2k24.domain.network.ResponseBean
import com.example.hackathon_2k24.ui.theme.BaseTheme
import com.example.hackathon_2k24.ui.theme.DarkLightPreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            if (state.isSearching) {
                TopAppBar(
                    title = { Text("Search") },
                    actions = {
                        IconButton(onClick = { /* Basket action */ }) {
                            Icon(Icons.Default.ShoppingCart, contentDescription = "Basket")
                        }
                    }
                )
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
            ) {
                if (!state.isSearching) {
                    TopSection()
                }
                SearchBar(
                    query = state.query,
                    onSearch = { query -> viewModel.processIntent(HomeIntent.Search(query)) },
                    onClearSearch = { viewModel.processIntent(HomeIntent.ClearSearch) }
                )
                ItemList(state.items)
            }
        }
    )
}

@Composable
fun TopSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Hello Tony,", style = MaterialTheme.typography.bodyLarge)
        Text(
            text = "What fruit salad combo do you want today?",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun SearchBar(query: String, onSearch: (String) -> Unit, onClearSearch: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = query,
            onValueChange = onSearch,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            singleLine = true
        )
        if (query.isNotEmpty()) {
            IconButton(onClick = onClearSearch) {
                Icon(Icons.Default.Close, contentDescription = "Clear Search")
            }
        }
    }
}

@Composable
fun ItemList(items: List<ResponseBean>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(items.size) { index ->
            ComboItem(items[index].id,items[index].name,items[index].price.toString(),){

            }
        }
    }
}
@Composable
fun ComboItem(
    id: Int,
    name: String,
    price: String,
    onAddToBasket: (Int) -> Unit // Callback for basket updates
) {
    var count by remember { mutableIntStateOf(0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "₦ $price", style = MaterialTheme.typography.titleSmall)
            }
            if (count > 0) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Minus Button
                    IconButton(onClick = {
                        if (count > 0) {
                            count--
                            onAddToBasket(count)
                        }
                    }) {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Remove item")
                    }

                    // Counter
                    Text(
                        text = count.toString(),
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    // Plus Button
                    IconButton(onClick = {
                        count++
                        onAddToBasket(count)
                    }) {
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Add item")
                    }
                }
            } else {
                // Add to Basket Button
                IconButton(onClick = {
                    count++
                    onAddToBasket(count)
                }) {
                    Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Add to basket")
                }
            }
        }
    }
}

@DarkLightPreviews
@Composable
fun CreateSearchAbhaScreenPreview() {
    BaseTheme {
        com.example.hackathon_2k24.ui.screens.ScreenRoutes.App.Home.route
    }
}