package com.example.smartphone.ui.smartphone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartphone.data.Smartphone
import com.example.smartphone.data.SmartphonesRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.map

class SmartphoneListViewModel(smartphonesRepository: SmartphonesRepository) : ViewModel() {
    val smartphoneList: StateFlow<List<Smartphone>> =
        smartphonesRepository.getAllSmartphonesStream()
            .map { it }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )
}
