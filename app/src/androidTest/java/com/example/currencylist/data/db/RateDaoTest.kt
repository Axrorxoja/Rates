package com.example.currencylist.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.currencylist.common.lazyFast
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RateDaoTest {

    private val db: InMemoryDB by lazyFast {
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            InMemoryDB::class.java
        ).allowMainThreadQueries().build()
    }
    private val dao: RateDao by lazyFast { db.rateDao }

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
        db.clearAllTables()
    }

    @Test
    fun loadRates() {
        val rate1 = RateItem("a", 1, 1, modifiedTime = 1)
        val rate2 = RateItem("b", 2, 2, modifiedTime = 2)
        val rate3 = RateItem("c", 3, 3, modifiedTime = 3)

        dao.insert(rate1)
        dao.insert(rate2)
        dao.insert(rate3)
        val list = dao.loadRates()
        assertTrue(list.isNotEmpty())
        assertTrue(list.size == 3)
        assertTrue(list[0] == rate3)
        assertTrue(list[1] == rate2)
        assertTrue(list[2] == rate1)
    }

    @Test
    fun insert() {
        val rate = RateItem("a", 1, 1, modifiedTime = 1)
        dao.insert(rate)
        val dbRate = dao.loadByCode(rate.code)
        assertTrue(dbRate == rate)
    }

    @Test
    fun update() {
        val rate = RateItem("a", 1, 1, modifiedTime = 1)
        dao.insert(rate)
        val updatedRate = RateItem("a", 5, 5, modifiedTime = 100)
        dao.update(updatedRate)
        val dbRate = dao.loadByCode("a")
        assertTrue(dbRate == updatedRate)
    }

    @Test
    fun updateByCode() {
        val rate = RateItem("a", 1, 1, modifiedTime = 1)
        dao.insert(rate)
        dao.updateByCode("a", 100)
        val dbRate = dao.loadByCode("a")
        assertTrue(dbRate!!.rate == 100L)
        assertTrue(dbRate.code == "a")
        assertTrue(dbRate.amount == 1)
        assertTrue(dbRate.modifiedTime == 1L)
    }

    @Test
    fun insertOrReplaceWithInsert() {
        val notExistCode = "b"
        val notExistRate = 100L
        dao.insertOrReplace(notExistCode, notExistRate)
        val dbRate = dao.loadByCode(notExistCode)
        assertTrue(dbRate!!.code == notExistCode)
        assertTrue(dbRate.rate == notExistRate)
        assertTrue(dbRate.amount == 1)
        assertTrue(dbRate.modifiedTime == 0L)
    }

    @Test
    fun insertOrReplaceWithUpdate() {
        val rate = RateItem("b", 100, 2, 2)
        dao.insert(rate)
        dao.insertOrReplace("b", 101)
        val dbRate = dao.loadByCode("b")
        assertTrue(dbRate!!.code == "b")
        assertTrue(dbRate.rate == 101L)
        assertTrue(dbRate.amount == 2)
        assertTrue(dbRate.modifiedTime == 2L)
    }

    @Test
    fun batchUpdate() {
        val rate1 = RateItem("a", 1, 1, modifiedTime = 1)
        val rate2 = RateItem("b", 2, 1, modifiedTime = 2)
        val rate3 = RateItem("c", 3, 1, modifiedTime = 3)

        dao.insert(rate1)
        dao.insert(rate2)
        dao.insert(rate3)
        dao.batchUpdate(4)

        val list = dao.loadRates()
        assertTrue(list.isNotEmpty())
        assertTrue(list.size == 3)
        assertTrue(list[0].amount == rate3.amount)
        assertTrue(list[1].amount == 4)
        assertTrue(list[2].amount == 4)
    }
}