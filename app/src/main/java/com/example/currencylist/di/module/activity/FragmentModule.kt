package com.example.currencylist.di.module.activity

import com.example.currencylist.di.module.app.DatabaseModule
import com.example.currencylist.di.module.app.NetModule
import com.example.currencylist.di.module.app.RepositoryModule
import com.example.currencylist.di.scope.FragmentScope
import com.example.currencylist.ui.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            NetModule::class,
            DatabaseModule::class,
            RepositoryModule::class
        ]
    )
    fun listFragment(): ListFragment

}
