package com.example.countries.db

class MainRepository(val apiHelper: ApiHelper) {
    suspend fun getCountries() = apiHelper.getCountries()
}