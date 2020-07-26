package com.example.currencylist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import timber.log.Timber

@Dao
abstract class RateDao {

    @Query("select * from rates order by modifiedTime desc")
    abstract fun liveRates(): LiveData<List<RateItem>>

    @Query("select * from rates order by modifiedTime desc")
    abstract fun loadRates(): MutableList<RateItem>

    @Insert
    abstract fun insert(item: RateItem)

    @Update
    abstract fun update(item: RateItem)

    @Query("delete from rates")
    abstract fun clearTable()

    @Transaction
    open fun batchUpdate(newAmount: Int, position: Int) {
        val items = loadRates()
        if (items.isEmpty()) return
        if (items.size > position)
            items.removeAt(position)
        for (item in items) {
            item.amount = newAmount
            update(item)
            Timber.d("batchUpdate $item $newAmount")
        }
    }

    @Query("select * from rates where code=:code")
    abstract fun loadByCode(code: String): RateItem?

    @Query(
        """
            update rates 
                set rate = :rate
                where code=:code
    """
    )
    abstract fun updateByCode(code: String, rate: Long)

    @Transaction
    open fun insertOrReplace(code: String, rate: Long) {
        val item = loadByCode(code)
        Timber.d("insertOrReplace $code $item")
        if (item == null) {
            insert(RateItem(code, rate))
        } else {
            updateByCode(code, rate)
        }
    }
}