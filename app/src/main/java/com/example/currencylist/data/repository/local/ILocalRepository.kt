package com.example.currencylist.data.repository.local

import com.example.currencylist.data.db.RateItem
import kotlinx.coroutines.flow.Flow

interface ILocalRepository {

    val flowRates: Flow<List<RateItem>>

    fun batchUpdate(newAmount: Int)

    fun update(item: RateItem)

    fun insertOrReplace(code: String, rate: Long)

    fun cancel()

}