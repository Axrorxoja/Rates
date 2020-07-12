package com.example.currencylist.data.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RateDao {

    @Query("SELECT * FROM RATE")
    fun loadRates(): Flow<List<Rate>>
}