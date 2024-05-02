package com.example.chefturnersguidetocooking.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query("SELECT * FROM Ingredient")
    fun getAllIngredients(): Flow<List<Ingredient>>

    @Query("SELECT * FROM Ingredient WHERE name = :ingredientName")
    fun getIngredient(ingredientName: String): Flow<List<Ingredient>>

    @Insert
    suspend fun insertIngredient(ingr: Ingredient): Long

    @Delete
    suspend fun deleteIngredient(
        ingr: Ingredient
    )
}