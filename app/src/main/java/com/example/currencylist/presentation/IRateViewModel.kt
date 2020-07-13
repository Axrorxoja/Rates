package com.example.currencylist.presentation

import androidx.lifecycle.LiveData
import com.example.currencylist.data.local.RateItem

interface IRateViewModel {
    val liveRates: LiveData<List<RateItem>>
}