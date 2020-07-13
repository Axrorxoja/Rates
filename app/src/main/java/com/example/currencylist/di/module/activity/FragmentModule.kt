package com.example.currencylist.di.module.activity

import com.example.currencylist.di.module.fragment.ListFragmentModule
import com.example.currencylist.di.scope.FragmentScope
import com.example.currencylist.ui.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ListFragmentModule::class])
    fun listFragment(): ListFragment

}
