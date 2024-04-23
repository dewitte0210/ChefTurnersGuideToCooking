package com.example.chefturnersguidetocooking.database

import android.graphics.Bitmap
import android.media.Image
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity
data class Recipe(
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val rid : Long = 0,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "origin")
    val origin: String?,
    @ColumnInfo(name = "favorite")
    val favorite: Boolean = false,
    @ColumnInfo(name = "image")
    val image: Bitmap?,
    @ColumnInfo(name = "num_cooked")
    val numCooked: Int = 0,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "instructions")
    val instructions: String?,
    @ColumnInfo(name = "servings")
    val servings: Int?,
    @ColumnInfo(name = "calories")
    val calories: Int?,
    @ColumnInfo(name = "carbs")
    val carbs: Int?,
    @ColumnInfo(name = "fat")
    val fat: Int?,
    @ColumnInfo(name = "protein")
    val protein: Int?,
    @ColumnInfo(name = "prep_time")
    val prepTime: String?,
    @ColumnInfo(name = "cook_time")
    val cookTime: String?,
    @ColumnInfo(name = "total_time")
    val totalTime: String?
)
