package com.example.chefturnersguidetocooking.database

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DatabaseViewModel(
    repository: DatabaseRepositoryInterface
) : ViewModel() {
    private val dbRepository: DatabaseRepositoryInterface

    private val _dbState = MutableStateFlow(DatabaseState())
    val dbState: StateFlow<DatabaseState> = _dbState
    init{
        dbRepository = repository
        Log.d("Database View Model", "Creating Database")
    }
}