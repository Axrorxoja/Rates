package com.example.currencylist.data.remote

import com.example.currencylist.data.IRateRepository
import com.example.currencylist.data.local.RateItem

class RemoteRepository(
    private val apiService: ApiService
) : IRateRepository {

    override suspend fun loadRate(base: String): List<RateItem> {
//        return apiService.loadRates(base)
        TODO()
    }

}