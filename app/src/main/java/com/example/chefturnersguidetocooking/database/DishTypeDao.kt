package com.example.chefturnersguidetocooking.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DishTypeDao {
    @Query("SELECT * FROM DishType")
    fun getAllTypes(): Flow<List<DishType>>

    @Query("SELECT * FROM DishType WHERE typeName = :typeName")
    fun getType(typeName: String): Flow<List<DishType>>

    @Insert
    suspend fun insertType(dishType: DishType)

    @Delete
    suspend fun deleteType(dishType: DishType)
}