package com.example.currencylist.data.repository.local

import com.example.currencylist.data.TestDispatcher
import com.example.currencylist.data.db.RateDao
import com.example.currencylist.data.db.RateItem
import kotlinx.coroutines.flow.flow
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

@RunWith(JUnit4::class)
class LocalRepositoryTest {

    private val dao = mock(RateDao::class.java)
    private val testDispatcher = TestDispatcher()
    private val mockLocalRepo: ILocalRepository = LocalRepository(dao, testDispatcher)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun getFlowRates() {
        val flow = flow<List<RateItem>> { }
        whenever(dao.flowRates())
            .thenReturn(flow)
        assertTrue(mockLocalRepo.flowRates == flow)
    }

    @Test
    fun batchUpdate() {
        val testAmount = 123
        mockLocalRepo.batchUpdate(testAmount)
        verify(dao, atLeastOnce()).batchUpdate(testAmount)
    }

    @Test
    fun update() {
        val rateItem = RateItem("a", 1L, 1, 1)
        mockLocalRepo.update(rateItem)
        verify(dao, atLeastOnce()).update(rateItem)
    }

    @Test
    fun insertOrReplace() {
        val testCode = "a"
        val testRate = 123L
        mockLocalRepo.insertOrReplace(testCode,testRate)
        verify(dao, atLeastOnce()).insertOrReplace(testCode,testRate)
    }

    @Test
    fun cancel() {
    }
}