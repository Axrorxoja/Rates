package com.example.currencylist.data.repository.remote

import com.example.currencylist.data.db.RateItem

interface IRemoteRepository {

    fun launch()

    fun setAsPrimary(rate: RateItem)

    fun cancel()
}