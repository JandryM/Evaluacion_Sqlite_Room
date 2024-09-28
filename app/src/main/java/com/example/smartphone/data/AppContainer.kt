package com.example.smartphone.data

import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val smartphonesRepository: SmartphonesRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineSmartphonesRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [SmartphonesRepository]
     */
    override val smartphonesRepository: SmartphonesRepository by lazy {
        OfflineSmartphonesRepository(SmartphoneDatabase.getDatabase(context).smartphoneDao())
    }
}
