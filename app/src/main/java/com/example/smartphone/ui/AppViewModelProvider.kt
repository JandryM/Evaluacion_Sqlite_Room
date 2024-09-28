package com.example.smartphone.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.smartphone.SmartphoneApplication
import com.example.smartphone.ui.smartphone.SmartphoneEntryViewModel
import com.example.smartphone.ui.smartphone.SmartphoneListViewModel

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for SmartphoneEntryViewModel
        initializer {
            SmartphoneEntryViewModel(inventoryApplication().container.smartphonesRepository)
        }

        // Initializer for SmartphoneListViewModel
        initializer {
            SmartphoneListViewModel(inventoryApplication().container.smartphonesRepository)
        }
    }
}

/**
 * Extension function to query for [Application] object and return an instance of
 * [SmartphoneApplication].
 */
fun CreationExtras.inventoryApplication(): SmartphoneApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as SmartphoneApplication)
