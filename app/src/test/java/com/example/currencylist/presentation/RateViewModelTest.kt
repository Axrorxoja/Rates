package com.example.currencylist.presentation

import com.example.currencylist.callOnCleared
import com.example.currencylist.data.db.RateItem
import com.example.currencylist.data.repository.local.ILocalRepository
import com.example.currencylist.data.repository.remote.IRemoteRepository
import com.example.currencylist.getOrAwaitValue
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

class RateViewModelTest {

    private val localRepo = mock(ILocalRepository::class.java)
    private val remoteRepo = mock(IRemoteRepository::class.java)
    private val vm = RateViewModel(remoteRepo, localRepo)

    @Test
    fun getLiveData() {
        val flow = flow<List<RateItem>> {
            listOf(
                RateItem("a", 1),
                RateItem("b", 2)
            )
        }
        whenever(localRepo.flowRates).thenReturn(flow)
        runBlocking {
            vm.flowRates.collect {
                val item = vm.liveData.getOrAwaitValue()
                assertTrue(item != null)
            }
        }
    }

    @Test
    fun setAsPrimary() {
        val rate = RateItem("a", 1, 2, 3)
        vm.setAsPrimary(rate)
        verify(remoteRepo, atLeastOnce()).setAsPrimary(rate)
    }

    @Test
    fun amountChanged() {
        val testAmount = 1
        vm.amountChanged(testAmount)
        verify(localRepo).batchUpdate(testAmount)
    }

    @Test
    fun onCleared() {
        vm.callOnCleared()
        verify(remoteRepo, atLeastOnce()).cancel()
        verify(localRepo, atLeastOnce()).cancel()
    }
}