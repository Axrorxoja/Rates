package com.example.currencylist.data.repository.local

import com.example.currencylist.data.db.RateDao
import com.example.currencylist.data.db.RateItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class LocalRepository(
    private val dao: RateDao
) : ILocalRepository, CoroutineScope {
    private val job = SupervisorJob()
    override val coroutineContext = job + Dispatchers.IO

    override val liveRates = dao.liveRates()

    override fun batchUpdate(newAmount: Int, position: Int) {
        launch {
            dao.batchUpdate(newAmount, position)
        }
    }

    override fun update(item: RateItem) {
        launch { dao.update(item) }
    }

    override fun insertOrReplace(code: String, rate: Long) {
        launch {
            dao.insertOrReplace(code, rate)
        }
    }

    override fun cancel() {
        job.cancel()
    }

}