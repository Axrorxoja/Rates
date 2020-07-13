package com.example.currencylist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencylist.data.local.RateItem
import com.example.currencylist.data.remote.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RateViewModel(
    private val apiService: ApiService
) : ViewModel(), IRateViewModel {
    override val liveRates = MutableLiveData<List<RateItem>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiService.loadRates("EUR")
            val list = ArrayList<RateItem>(response.rates.size + 1)
            list += RateItem(response.baseCurrency, 1F)
            for (entry in response.rates.entries) {
                list += RateItem(entry.key, entry.value)
            }
            liveRates.postValue(list)
        }
    }

}


