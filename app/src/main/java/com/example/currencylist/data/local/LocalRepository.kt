package com.example.currencylist.data.local

import com.example.currencylist.data.IRateRepository

class LocalRepository :IRateRepository{
    override fun loadRate(base: String): List<Rate> {
        TODO("Not yet implemented")
    }

}