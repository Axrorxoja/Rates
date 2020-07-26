package com.example.currencylist.data.repository.remote

import com.example.currencylist.data.models.BaseRate
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/android/latest")
    suspend fun loadRates(@Query("base") baseRate: String): BaseRate

}