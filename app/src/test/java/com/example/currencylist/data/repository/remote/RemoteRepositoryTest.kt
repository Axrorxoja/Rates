package com.example.currencylist.data.repository.remote

import com.example.currencylist.common.Constant.DEFAULT_CODE
import com.example.currencylist.data.TestDispatcher
import com.example.currencylist.data.db.RateItem
import com.example.currencylist.data.models.BaseRate
import com.example.currencylist.data.repository.local.ILocalRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

class RemoteRepositoryTest {
    private val localRepo: ILocalRepository = mock(ILocalRepository::class.java)
    private val apiService = mock(ApiService::class.java)
    private val testDispatcher = TestDispatcher()
    private val mockRemoteRepo: IRemoteRepository =
        RemoteRepository(apiService, localRepo, testDispatcher)

    @Test
    fun launch() {
        runBlocking {
            whenever(apiService.loadRates(DEFAULT_CODE))
                .thenReturn(BaseRate("eu", hashMapOf()))
            mockRemoteRepo.launch()
            delay(12000)
            mockRemoteRepo.cancel()
            verify(apiService, atLeast(10)).loadRates(DEFAULT_CODE)
        }
    }

    @Test
    fun setAsPrimary() {
        val rate = RateItem("a", 1, 1, 0)
        mockRemoteRepo.setAsPrimary(rate)
        verify(localRepo, atLeastOnce()).update(rate)
    }
}