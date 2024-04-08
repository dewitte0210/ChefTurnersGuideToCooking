package com.example.chefturnersguidetocooking.database

import androidx.lifecycle.ViewModel

class DatabaseViewModel(
    repository: DatabaseRepositoryInterface
) : ViewModel() {
    private val dbRepository: DatabaseRepositoryInterface

    init{
        dbRepository = repository
    }
}