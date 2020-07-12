package com.example.currencylist.di.module.activity

import androidx.fragment.app.ListFragment
import com.example.currencylist.di.module.fragment.ListFragmentModule
import com.example.currencylist.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ListFragmentModule::class])
    fun listFragment(): ListFragment

}
