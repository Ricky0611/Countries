package com.example.countries.db

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getCountries() = apiHelper.getCountries()
}