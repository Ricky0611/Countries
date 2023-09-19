package com.example.countries.db

import com.example.countries.network.ApiService

class ApiHelper(private val apiService: ApiService) {
    suspend fun getCountries() = apiService.getCountries()
}