package com.example.chefturnersguidetocooking.database

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Measurement(
    @PrimaryKey(autoGenerate = true)
        val mid: Long,
    @ColumnInfo(name = "name")
        val name: String
)
