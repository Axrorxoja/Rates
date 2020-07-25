package com.example.currencylist.presentation

import androidx.lifecycle.LiveData
import com.example.currencylist.data.db.RateItem
import com.example.currencylist.ui.list.adapter.ItemState
import kotlinx.coroutines.flow.Flow

interface IRateViewModel {
    val liveRates: LiveData<List<RateItem>>

    fun setAsPrimary(rate: RateItem)

    fun amountChanged(newAmount: Int)
}