package com.example.smartphone.data

import kotlinx.coroutines.flow.Flow

class OfflineSmartphonesRepository(private val smartphoneDao: SmartphoneDao) : SmartphonesRepository {
    override fun getAllSmartphonesStream(): Flow<List<Smartphone>> = smartphoneDao.getAllSmartphones()

    override fun getSmartphoneStream(id: Int): Flow<Smartphone?> = smartphoneDao.getSmartphone(id)

    override suspend fun insertSmartphone(smartphone: Smartphone) = smartphoneDao.insert(smartphone)

    //override suspend fun deleteSmartphone(smartphone: Smartphone) = smartphoneDao.delete(smartphone)

    //override suspend fun updateSmartphone(smartphone: Smartphone) = smartphoneDao.update(smartphone)
}

