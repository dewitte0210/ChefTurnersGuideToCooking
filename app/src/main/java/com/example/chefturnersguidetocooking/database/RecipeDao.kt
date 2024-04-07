package com.example.chefturnersguidetocooking.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * From Recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe r " +
            "JOIN RecipeIngredient ri ON ri.rid = r.rid " +
            "JOIN Ingredient i ON i.iid = ri.iid " +
            "JOIN Measurement m ON m.mid = ri.mid " +
            "WHERE r.rid LIKE :recipeID")
    fun getRecipe(recipeID: Long)

    @Insert
    suspend fun insertRecipe(rec: Recipe)

    @Delete
    suspend fun deleteRecipe(rec: Recipe)
}