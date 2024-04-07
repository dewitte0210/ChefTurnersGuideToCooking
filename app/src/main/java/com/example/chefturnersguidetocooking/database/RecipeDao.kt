package com.example.chefturnersguidetocooking.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * From Recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

}