package com.example.chefturnersguidetocooking.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Recipe::class, RecipeIngredient::class, Measurement::class, Ingredient::class, DishType::class, RecipeDishType::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class RecipeDatabase() : RoomDatabase() {
    // Dao objects for the database
    abstract fun RecipeDao() : RecipeDao
    abstract fun RecipeIngredientDao() : RecipeIngredientDao
    abstract fun MeasurementDao() : MeasurementDao
    abstract fun IngredientDao() : IngredientDao
    abstract fun RecipeDishTypeDao() : RecipeDishTypeDao
    abstract fun DishTypeDao() : DishTypeDao
    abstract fun SingleRecipeAllInfoDao(): SingleRecipeAllInfoDao
    //Singleton to create a database instance
    companion object {
        private var instance: RecipeDatabase? = null
        fun getInstance (context: Context) : RecipeDatabase{
            if(instance == null){
                instance = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe_db"
                )
                    //.createFromAsset("database/cookingApp_db.db")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance as RecipeDatabase
        }
    }
}