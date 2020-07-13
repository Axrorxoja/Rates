package com.example.currencylist.di.module.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.example.currencylist.di.module.activity.FragmentModule
import com.example.currencylist.di.scope.ActivityScope
import com.example.currencylist.ui.AppActivity

@Module
interface ActivitiesModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    fun bindActivity(): AppActivity
}
