package com.example.currencylist

import android.app.Application
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}