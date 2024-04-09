package com.example.chefturnersguidetocooking.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    val rid : Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "origin")
    val origin: String,
    @ColumnInfo(name = "favorite")
    val favorite: Boolean,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "num_cooked")
    val numCooked: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "instructions")
    val instructions: String,
    @ColumnInfo(name = "calories")
    val calories: Int,
    @ColumnInfo(name = "carbs")
    val carbs: Int,
    @ColumnInfo(name = "fat")
    val fat: Int,
    @ColumnInfo(name = "protein")
    val protein: Int
)
