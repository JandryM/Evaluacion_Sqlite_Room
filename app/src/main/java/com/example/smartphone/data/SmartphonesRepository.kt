package com.example.smartphone.data

import kotlinx.coroutines.flow.Flow

/**
 * Repository that provides insert, update, delete, and retrieve of [Smartphone] from a given data source.
 */
interface SmartphonesRepository {
    /**
     * Retrieve all the smartphones from the given data source.
     */
    fun getAllSmartphonesStream(): Flow<List<Smartphone>>

    /**
     * Retrieve a smartphone from the given data source that matches with the [id].
     */
    fun getSmartphoneStream(id: Int): Flow<Smartphone?>

    /**
     * Insert smartphone in the data source
     */
    suspend fun insertSmartphone(smartphone: Smartphone)

    /**
     * Delete smartphone from the data source
     */
    //suspend fun deleteSmartphone(smartphone: Smartphone)

    /**
     * Update smartphone in the data source
     */
    //suspend fun updateSmartphone(smartphone: Smartphone)
}
