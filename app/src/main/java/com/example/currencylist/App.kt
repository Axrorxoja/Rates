package com.example.currencylist

import com.example.currencylist.di.component.DaggerAppComponent
import dagger.android.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        setUpTimber()
    }

    private fun setUpTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector() = DaggerAppComponent.factory().create(this)
}