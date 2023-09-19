package com.example.countries.db

import com.example.countries.network.ApiService

class ApiHelper(val apiService: ApiService) {
    suspend fun getCountries() = apiService.getCountries()
}