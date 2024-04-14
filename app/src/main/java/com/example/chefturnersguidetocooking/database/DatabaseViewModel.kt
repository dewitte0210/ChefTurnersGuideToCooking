package com.example.chefturnersguidetocooking.database

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DatabaseViewModel(
    repository: DatabaseRepositoryInterface
) : ViewModel() {
    private val dbRepository: DatabaseRepositoryInterface

    init{
        dbRepository = repository
    }

    private val _recipes = dbRepository.getAllRecipes().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf()
    )
    private val _ingredients = dbRepository.getAllIngredients().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf()
    )
    private val _measurements = dbRepository.getAllMeasurements().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf()
    )
    private val _dishTypes = dbRepository.getAllRecipeDishTypes().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf()
    )
    private val _dbState = MutableStateFlow(DatabaseState())
    val dbState: StateFlow<DatabaseState> = combine(_dbState, _recipes, _ingredients, _measurements, _dishTypes)
    {dbState, recipes, ingredients, measurements, dishTypes ->
        dbState.copy(
            recipes = recipes,
            ingredients = ingredients,
            measurements = measurements,
            dishTypes = dishTypes
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DatabaseState()
    )

    //Inserts
    fun insertRecipe(name: String, origin: String, favorite: Boolean, image: String,
                     numCooked: Int, description: String, instructions: String, calories: Int,
                     carbs: Int, fat: Int, protein: Int){
        val recipe = Recipe(name = name, origin = origin, favorite = favorite, image = image,
            numCooked = numCooked, description = description, instructions = instructions,
            calories = calories, carbs = carbs, fat = fat, protein = protein)
        viewModelScope.launch{
            dbRepository.insertRecipe(recipe)
        }
    }

    fun insertIngredient(name: String) {
        val ingredient = Ingredient(name = name)
        viewModelScope.launch{
            dbRepository.insertIngredient(ingredient)
        }
    }

    fun insertMeasurement(name: String){
        val measurement = Measurement(name = name)
        viewModelScope.launch{
            dbRepository.insertMeasurement(measurement)
        }
    }
    /*
    fun insertDishType(name: String){
        val dishType = RecipeDishType(name = name)
        viewModelScope.launch {
            dbRepository.insertRecipeDishType(dishType)
        }
    }
*/
}