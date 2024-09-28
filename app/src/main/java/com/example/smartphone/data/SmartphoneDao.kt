package com.example.smartphone.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Database access object to access the Smartphone table in the database.
 */
@Dao
interface SmartphoneDao {

    @Query("SELECT * from smartphones ORDER BY modelName ASC")
    fun getAllSmartphones(): Flow<List<Smartphone>>

    @Query("SELECT * from smartphones WHERE id = :id")
    fun getSmartphone(id: Int): Flow<Smartphone?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(smartphone: Smartphone)

    //@Update
    //suspend fun update(smartphone: Smartphone)

    //@Delete
    //suspend fun delete(smartphone: Smartphone)
}

