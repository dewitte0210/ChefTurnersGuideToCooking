package com.example.chefturnersguidetocooking.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DishType(
    @PrimaryKey(autoGenerate = true)
    val dtid: Long = 0,
    @ColumnInfo(name = "name")
    val name: String
)
