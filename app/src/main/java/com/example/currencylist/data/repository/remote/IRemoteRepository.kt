package com.example.currencylist.data.repository.remote

import com.example.currencylist.data.db.RateItem

interface IRemoteRepository {
    var code: String

    fun launch()

    fun setAsPrimary(rate: RateItem)

    fun cancel()
}