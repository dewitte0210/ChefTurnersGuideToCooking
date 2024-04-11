package com.example.chefturnersguidetocooking.database

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class DatabaseViewModel(
    repository: DatabaseRepositoryInterface
) : ViewModel() {
    private val dbRepository: DatabaseRepositoryInterface

    private val _dbState = MutableStateFlow(DatabaseState())
    init{
        dbRepository = repository
    }
}