package com.example.chefturnersguidetocooking.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeIngredientDao {
    @Query("SELECT * FROM RecipeIngredient")
    fun getAllRecipeIngredients(): Flow<List<RecipeIngredient>>

    @Insert
    suspend fun insertRecipeIngredient(recIng: RecipeIngredient)

    @Delete
    suspend fun deleteRecipeIngredient(recIng: RecipeIngredient)
}