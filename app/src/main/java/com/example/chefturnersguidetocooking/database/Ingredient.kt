package com.example.chefturnersguidetocooking.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["ingredientName"], unique = true)])
data class Ingredient(
    @NonNull
    @PrimaryKey(autoGenerate = true)
        val iid: Long = 0,
    @ColumnInfo(name = "ingredientName")
        val ingredientName: String?
)
