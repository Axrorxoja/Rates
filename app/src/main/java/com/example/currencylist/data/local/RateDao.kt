package com.example.currencylist.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import timber.log.Timber

@Dao
abstract class RateDao {

    @Query("select * from rates order by modifiedTime desc")
    abstract fun liveRates(): LiveData<List<RateItem>>

    @Query("select * from rates order by modifiedTime desc")
    abstract fun loadRates(): MutableList<RateItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(item: RateItem)

    @Query("delete from rates")
    abstract fun clearTable()

    @Transaction
    open fun batchUpdate(newAmount: Float, position: Int) {
        val items = loadRates()
        if (items.isEmpty())return
        if (items.size>position)
            items.removeAt(position)
        for (item in items) {
            item.amount = newAmount
            insert(item)
            Timber.d("batchUpdate $item")
        }
    }
}