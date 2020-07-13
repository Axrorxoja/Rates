package com.example.currencylist.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RateItem::class],
    version = 1,
    exportSchema = false
)
abstract class InMemoryDB : RoomDatabase() {
    abstract val rateDao: RateDao
}