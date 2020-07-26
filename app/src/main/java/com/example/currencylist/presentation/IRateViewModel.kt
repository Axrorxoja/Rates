package com.example.currencylist.presentation

import androidx.lifecycle.LiveData
import com.example.currencylist.data.db.RateItem
import kotlinx.coroutines.flow.Flow

interface IRateViewModel {
    val flowRates: Flow<List<RateItem>>
    val liveData: LiveData<Unit>

    fun setAsPrimary(rate: RateItem)

    fun amountChanged(newAmount: Int)
}