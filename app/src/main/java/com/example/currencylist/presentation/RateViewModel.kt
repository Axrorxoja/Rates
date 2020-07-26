package com.example.currencylist.presentation

import androidx.lifecycle.ViewModel
import com.example.currencylist.data.db.RateItem
import com.example.currencylist.data.repository.local.ILocalRepository
import com.example.currencylist.data.repository.remote.IRemoteRepository
import timber.log.Timber

class RateViewModel(
    private val remoteRepo: IRemoteRepository,
    private val localRepo: ILocalRepository
) : ViewModel(), IRateViewModel {
    override val liveRates = localRepo.liveRates

    init {
        remoteRepo.launch()
    }

    override fun setAsPrimary(rate: RateItem) = remoteRepo.setAsPrimary(rate)

    override fun amountChanged(newAmount: Int) {
        localRepo.batchUpdate(newAmount, 0)
        Timber.d("amountChanged  $newAmount")
    }

    override fun onCleared() {
        super.onCleared()
        remoteRepo.cancel()
        localRepo.cancel()
    }
}


