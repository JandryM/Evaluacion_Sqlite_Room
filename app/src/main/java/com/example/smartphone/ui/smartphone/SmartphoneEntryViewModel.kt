package com.example.smartphone.ui.smartphone

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.smartphone.data.Smartphone
import com.example.smartphone.data.SmartphonesRepository
import java.text.NumberFormat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel to validate and insert smartphones in the Room database.
 */
class SmartphoneEntryViewModel(private val smartphonesRepository: SmartphonesRepository) : ViewModel() {

    // Holds current smartphone UI state
    var smartphoneUiState by mutableStateOf(SmartphoneUiState())
        private set

    // Flow to manage UI feedback like showing success message
    private val _saveSuccess = MutableStateFlow(false)
    val saveSuccess = _saveSuccess.asStateFlow()

    /**
     * Updates the [smartphoneUiState] with the value provided in the argument.
     * This method also triggers a validation for input values.
     */
    fun updateUiState(smartphoneDetails: SmartphoneDetails) {
        smartphoneUiState = SmartphoneUiState(
            smartphoneDetails = smartphoneDetails,
            isEntryValid = validateInput(smartphoneDetails)
        )
    }

    /**
     * Validates the input before saving the smartphone.
     */
    private fun validateInput(uiState: SmartphoneDetails = smartphoneUiState.smartphoneDetails): Boolean {
        return with(uiState) {
            modelName.isNotBlank() && brand.isNotBlank() && os.isNotBlank() &&
                    storage.toIntOrNull() != null && price.toDoubleOrNull() != null
        }
    }

    /**
     * Saves the smartphone to the repository and updates the UI flow.
     */
    suspend fun saveSmartphone() {
        if (validateInput()) {
            smartphonesRepository.insertSmartphone(smartphoneUiState.smartphoneDetails.toSmartphone())
            _saveSuccess.value = true // Trigger success state after saving
            resetState() // Clear UI state after saving
        }
    }

    /**
     * Resets the UI state after a successful save or after the user wants to clear the form.
     */
    private fun resetState() {
        smartphoneUiState = SmartphoneUiState()
        _saveSuccess.value = false // Reset success state
    }
}

/**
 * Represents Ui State for a Smartphone.
 */
data class SmartphoneUiState(
    val smartphoneDetails: SmartphoneDetails = SmartphoneDetails(),
    val isEntryValid: Boolean = false
)

/**
 * Represents the detailed information for a smartphone.
 */
data class SmartphoneDetails(
    val id: Int = 0,
    val modelName: String = "",
    val brand: String = "",
    val os: String = "",
    val storage: String = "",  // As string for now, will convert later
    val price: String = "",    // As string for now, will convert later
)

/**
 * Extension function to convert [SmartphoneDetails] to [Smartphone].
 * If the value of [SmartphoneDetails.price] is not a valid [Double],
 * then the price will be set to 0.0. Similarly if the value of [SmartphoneDetails.storage]
 * is not a valid [Int], then the storage will be set to 0.
 */
fun SmartphoneDetails.toSmartphone(): Smartphone = Smartphone(
    id = id,
    modelName = modelName,
    brand = brand,
    os = os,
    storage = storage.toIntOrNull() ?: 0,  // Convert storage to Int
    price = price.toDoubleOrNull() ?: 0.0  // Convert price to Double
)

/**
 * Extension function to format the price.
 */
fun Smartphone.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

/**
 * Extension function to convert [Smartphone] to [SmartphoneUiState].
 */
fun Smartphone.toSmartphoneUiState(isEntryValid: Boolean = false): SmartphoneUiState = SmartphoneUiState(
    smartphoneDetails = this.toSmartphoneDetails(),
    isEntryValid = isEntryValid
)

/**
 * Extension function to convert [Smartphone] to [SmartphoneDetails].
 */
fun Smartphone.toSmartphoneDetails(): SmartphoneDetails = SmartphoneDetails(
    id = id,
    modelName = modelName,
    brand = brand,
    os = os,
    storage = storage.toString(),
    price = price.toString()
)
