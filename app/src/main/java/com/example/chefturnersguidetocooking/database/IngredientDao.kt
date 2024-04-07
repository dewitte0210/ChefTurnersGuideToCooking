package com.example.chefturnersguidetocooking.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query("SELECT * FROM Ingredient")
    fun getAllIngredients(): Flow<List<Ingredient>>
}