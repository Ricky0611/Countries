package com.example.countries.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countries.data.Country
import com.example.countries.db.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {

    private val _countryList = MutableLiveData<List<Country>>()
    val countryList: LiveData<List<Country>> = _countryList
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = mainRepository.getCountries()
                if (response.isSuccessful) {
                    if (response.body().isNullOrEmpty()) {
                        _countryList.postValue(emptyList())
                    } else {
                        _countryList.postValue(response.body())
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue("Error: $e")
            }
        }
    }
}