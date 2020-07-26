package com.example.currencylist

import com.example.currencylist.common.ProductionTree
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
        } else {
            Timber.plant(ProductionTree())
        }
    }

    override fun applicationInjector() = DaggerAppComponent.factory().create(this)
}