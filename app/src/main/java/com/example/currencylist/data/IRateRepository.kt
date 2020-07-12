package com.example.currencylist.data

import com.example.currencylist.data.local.Rate

interface IRateRepository {

    fun loadRate(base: String = "EUR"): List<Rate>

}