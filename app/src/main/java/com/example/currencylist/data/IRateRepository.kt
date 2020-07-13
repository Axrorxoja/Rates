package com.example.currencylist.data

import com.example.currencylist.data.local.RateItem

interface IRateRepository {

    suspend fun loadRate(base: String = "EUR"): List<RateItem>

}