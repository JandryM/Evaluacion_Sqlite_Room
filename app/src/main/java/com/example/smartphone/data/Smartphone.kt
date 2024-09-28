package com.example.smartphone.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity data class represents a single row in the database.
 */
@Entity(tableName = "smartphones")
data class Smartphone(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val modelName: String,
    val brand: String,
    val os: String,
    val storage: Int,
    val price: Double
)
