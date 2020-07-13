package com.example.currencylist.di.module.app

import android.content.Context
import com.example.currencylist.App
import com.example.currencylist.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @AppScope
    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }
}