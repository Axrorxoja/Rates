package com.example.currencylist.data.repository.remote

import com.example.currencylist.common.Constant.DEFAULT_CODE
import com.example.currencylist.data.IDispatcher
import com.example.currencylist.data.db.RateItem
import com.example.currencylist.data.models.BaseRate
import com.example.currencylist.data.repository.local.ILocalRepository
import kotlinx.coroutines.*
import timber.log.Timber

class RemoteRepository(
    private val apiService: ApiService,
    private val localRepo: ILocalRepository,
    dispatcher: IDispatcher
) : IRemoteRepository, CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext = job + dispatcher.io
    private var code = DEFAULT_CODE

    override fun launch() {
        launch {
            while (isActive) {
                delay(1000)
                val response = apiService.loadRates(code)
                parseResponse(response)
            }
        }
    }

    override fun setAsPrimary(rate: RateItem) {
        launch {
            code = rate.code
            rate.modifiedTime = System.currentTimeMillis()
            localRepo.update(rate)
        }
    }

    private fun parseResponse(response: BaseRate) {
        localRepo.insertOrReplace(response.baseCurrency, 1)
        for (entry in response.rates.entries) {
            localRepo.insertOrReplace(entry.key, (entry.value * 100).toLong())
        }
    }

    override fun cancel() {
        coroutineContext.cancel()
    }
}
