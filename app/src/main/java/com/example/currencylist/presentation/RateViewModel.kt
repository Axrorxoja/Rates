package com.example.currencylist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencylist.data.local.RateDao
import com.example.currencylist.data.local.RateItem
import com.example.currencylist.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class RateViewModel(
    private val apiService: ApiService,
    private val dao: RateDao
) : ViewModel(), IRateViewModel {
    override val liveRates = dao.liveRates()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.loadRates("EUR")
            dao.insert(RateItem(response.baseCurrency, 1F))
            for (entry in response.rates.entries) {
                dao.insert(RateItem(entry.key, entry.value))
            }
        }
    }

    override fun setAsPrimaryRate(rate: RateItem) {
        viewModelScope.launch(Dispatchers.IO) {
            rate.modifiedTime = System.currentTimeMillis()
            dao.insert(rate)
        }
    }

    override fun primaryItemAmountChanged(newAmount: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.batchUpdate(newAmount,0)
            Timber.d("primaryItemValueChanged  $newAmount")
        }
    }

    override fun amountChanged(newAmount: Float, position: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.batchUpdate(newAmount,position)
            Timber.d("amountChanged  $newAmount")
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("onCleared ")
    }
}


