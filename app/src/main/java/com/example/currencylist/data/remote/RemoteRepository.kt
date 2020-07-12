package com.example.currencylist.data.remote

import com.example.currencylist.data.IRateRepository
import com.example.currencylist.data.local.Rate
import kotlinx.coroutines.CoroutineScope

class RemoteRepository(
    private val apiService: ApiService,
    private val scope: CoroutineScope
) : IRateRepository {

    override fun loadRate(base: String): List<Rate> {
        TODO("Not yet implemented")
    }

}