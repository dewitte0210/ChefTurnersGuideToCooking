package com.example.chefturnersguidetocooking.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class RecipeIngredient(
    val rid: Long,
    val iid: Long,
    val mid: Long,
    @ColumnInfo(name = "amount")
        val amount: Double
)
