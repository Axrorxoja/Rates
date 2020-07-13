package com.example.currencylist.presentation

import androidx.lifecycle.LiveData
import com.example.currencylist.data.local.RateItem

interface IRateViewModel {
    val liveRates: LiveData<List<RateItem>>

    fun setAsPrimaryRate(rate: RateItem)

    fun primaryItemAmountChanged(newAmount: Float)

    fun amountChanged(newAmount: Float, position: Int)
}