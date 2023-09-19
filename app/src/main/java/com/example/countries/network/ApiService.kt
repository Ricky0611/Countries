package com.example.countries.network

import com.example.countries.data.Country
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("countries.json")
    suspend fun getCountries(): Response<List<Country>>
}