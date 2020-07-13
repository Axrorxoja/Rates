package com.example.currencylist.di.module.app

import android.content.Context
import androidx.room.Room
import com.example.currencylist.data.local.InMemoryDB
import com.example.currencylist.data.local.RateDao
import com.example.currencylist.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @AppScope
    @Provides
    fun provideDao(context: Context): RateDao {
        val db = Room.inMemoryDatabaseBuilder(context, InMemoryDB::class.java)
            .build()
        return db.rateDao
    }
}