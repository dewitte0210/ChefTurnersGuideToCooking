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

    @Query("UPDATE Recipe SET favorite = :fav WHERE rid = :rid")
    suspend fun updateFav(fav: Boolean, rid: Long)

    @Insert
    suspend fun insertRecipe(rec: Recipe)

    @Delete
    suspend fun deleteRecipe(rec: Recipe)
}