package com.example.smartphone.ui.smartphone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartphone.data.Smartphone
import com.example.smartphone.ui.AppViewModelProvider
import com.example.smartphone.ui.theme.SmartphoneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartphoneListScreen(
    viewModel: SmartphoneListViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateBack: () -> Unit
) {
    // Obtener la lista de smartphones del StateFlow
    val smartphoneList by viewModel.smartphoneList.collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Lista de Smartphones") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (smartphoneList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "No hay smartphones disponibles")
            }
        } else {
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier.fillMaxSize()
            ) {
                items(smartphoneList) { smartphone ->
                    SmartphoneListItem(smartphone)
                }
            }
        }
    }
}

@Composable
fun SmartphoneListItem(smartphone: Smartphone) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Modelo: ${smartphone.modelName}", style = MaterialTheme.typography.titleLarge)
            Text("Marca: ${smartphone.brand}", style = MaterialTheme.typography.bodyLarge)
            Text("OS: ${smartphone.os}", style = MaterialTheme.typography.bodyLarge)
            Text("Almacenamiento: ${smartphone.storage} GB", style = MaterialTheme.typography.bodyLarge)
            Text("Precio: ${smartphone.formatedPrice()}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SmartphoneListScreenPreview() {
    SmartphoneTheme {
        SmartphoneListScreen(navigateBack = {})
    }
}
