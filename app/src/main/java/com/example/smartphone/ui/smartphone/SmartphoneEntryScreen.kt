package com.example.smartphone.ui.smartphone

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.smartphone.R
import com.example.smartphone.SmartphoneTopAppBar
import com.example.smartphone.ui.AppViewModelProvider
import com.example.smartphone.ui.navigation.NavigationDestination
import com.example.smartphone.ui.theme.SmartphoneTheme
import kotlinx.coroutines.launch
import java.util.Currency
import java.util.Locale

object SmartphoneEntryDestination : NavigationDestination {
    override val route = "smartphone_entry"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmartphoneEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    navigateToList: () -> Unit,
    canNavigateBack: Boolean = false,
    viewModel: SmartphoneEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            SmartphoneTopAppBar(
                title = stringResource(SmartphoneEntryDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Sección superior con los textos
            Column(
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(
                    text = "JANDRY CLEDIS MOREIRA CHÁVEZ",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "SMARTPHONE",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "7mo 'A'",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Formulario de entrada de datos
            SmartphoneEntryBody(
                smartphoneUiState = viewModel.smartphoneUiState,
                onSmartphoneValueChange = viewModel::updateUiState,
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.saveSmartphone()
                        navigateToList() // Navegar a la lista después de guardar
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            // Botón para ver la lista de registros
            Button(
                onClick = { navigateToList() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(R.string.list_action))
            }
        }
    }
}

@Composable
fun SmartphoneEntryBody(
    smartphoneUiState: SmartphoneUiState,
    onSmartphoneValueChange: (SmartphoneDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
    ) {
        SmartphoneInputForm(
            smartphoneDetails = smartphoneUiState.smartphoneDetails,
            onValueChange = onSmartphoneValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            enabled = smartphoneUiState.isEntryValid,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save_action))
        }
    }
}

@Composable
fun SmartphoneInputForm(
    smartphoneDetails: SmartphoneDetails,
    modifier: Modifier = Modifier,
    onValueChange: (SmartphoneDetails) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = smartphoneDetails.modelName,
            onValueChange = { onValueChange(smartphoneDetails.copy(modelName = it)) },
            label = { Text(stringResource(R.string.smartphone_model_name)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = smartphoneDetails.brand,
            onValueChange = { onValueChange(smartphoneDetails.copy(brand = it)) },
            label = { Text(stringResource(R.string.smartphone_brand)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = smartphoneDetails.os,
            onValueChange = { onValueChange(smartphoneDetails.copy(os = it)) },
            label = { Text(stringResource(R.string.smartphone_os)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = smartphoneDetails.storage,
            onValueChange = { onValueChange(smartphoneDetails.copy(storage = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text(stringResource(R.string.smartphone_storage)) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = smartphoneDetails.price,
            onValueChange = { onValueChange(smartphoneDetails.copy(price = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text(stringResource(R.string.smartphone_price)) },
            leadingIcon = { Text(Currency.getInstance(Locale.getDefault()).symbol) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        Text(
            text = stringResource(R.string.required_fields),
            style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Light),
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.padding_small))
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SmartphoneEntryScreenPreview() {
    SmartphoneTheme {
        SmartphoneEntryBody(
            smartphoneUiState = SmartphoneUiState(
                SmartphoneDetails(
                    modelName = "Modelo X", brand = "Marca Y", os = "Android", storage = "128", price = "599.99"
                )
            ), onSmartphoneValueChange = {}, onSaveClick = {}
        )
    }
}

