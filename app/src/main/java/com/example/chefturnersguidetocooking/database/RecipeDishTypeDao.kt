package com.example.chefturnersguidetocooking.database

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface RecipeDishTypeDao {
    @Query("SELECT * FROM RecipeDishType")
    fun getAllRecipeDishTypes(): Flow<List<RecipeDishType>>

    @Insert
    suspend fun insertRecipeDishType(recDishType: RecipeDishType)

    @Delete
    suspend fun deleteRecipeDishType(recDishType: RecipeDishType)
}