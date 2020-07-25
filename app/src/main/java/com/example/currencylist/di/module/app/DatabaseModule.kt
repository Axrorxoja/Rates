package com.example.currencylist.di.module.app

import android.content.Context
import androidx.room.Room
import com.example.currencylist.data.db.InMemoryDB
import com.example.currencylist.data.db.RateDao
import com.example.currencylist.di.scope.AppScope
import com.example.currencylist.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @FragmentScope
    @Provides
    fun provideDao(context: Context): RateDao {
        val db = Room.inMemoryDatabaseBuilder(context, InMemoryDB::class.java)
            .build()
        return db.rateDao
    }
}