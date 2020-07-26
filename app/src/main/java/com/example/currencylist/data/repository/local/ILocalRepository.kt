package com.example.currencylist.data.repository.local

import androidx.lifecycle.LiveData
import com.example.currencylist.data.db.RateItem
import kotlinx.coroutines.flow.Flow

interface ILocalRepository {

    val liveRates: LiveData<List<RateItem>>

    fun batchUpdate(newAmount: Int, position: Int)

    fun update(item: RateItem)

    fun insertOrReplace(code: String, rate: Long)

    fun cancel()

}