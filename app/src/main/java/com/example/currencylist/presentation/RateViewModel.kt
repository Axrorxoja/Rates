package com.example.currencylist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencylist.data.db.RateItem
import com.example.currencylist.data.repository.local.ILocalRepository
import com.example.currencylist.data.repository.remote.IRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class RateViewModel(
    private val remoteRepo: IRemoteRepository,
    private val localRepo: ILocalRepository
) : ViewModel(), IRateViewModel {
    override val flowRates: Flow<List<RateItem>>
        get() = localRepo
            .flowRates
            .onEach { if (liveData.value == null) liveData.postValue(Unit) }

    override val liveData = MutableLiveData<Unit>()

    init {
        remoteRepo.launch()
    }

    override fun setAsPrimary(rate: RateItem) = remoteRepo.setAsPrimary(rate)

    override fun amountChanged(newAmount: Int) {
        localRepo.batchUpdate(newAmount)
        Timber.d("amountChanged  $newAmount")
    }

    public override fun onCleared() {
        super.onCleared()
        remoteRepo.cancel()
        localRepo.cancel()
    }
}


