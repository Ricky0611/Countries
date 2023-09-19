package com.example.countries.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.countries.db.MainRepository
import com.example.countries.ui.viewmodel.MainViewModel

class CountryViewModelFactory(private val mainRepository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(mainRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}