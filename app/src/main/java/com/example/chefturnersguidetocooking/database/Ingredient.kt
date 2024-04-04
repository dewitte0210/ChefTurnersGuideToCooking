package com.example.chefturnersguidetocooking.database

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Ingredient(
    @PrimaryKey(autoGenerate = true)
        val iid: Long,
    @ColumnInfo(name = "name")
        val name: String
)
