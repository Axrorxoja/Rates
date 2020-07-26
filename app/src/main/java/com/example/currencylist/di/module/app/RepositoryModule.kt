package com.example.currencylist.di.module.app

import com.example.currencylist.data.db.RateDao
import com.example.currencylist.data.repository.local.ILocalRepository
import com.example.currencylist.data.repository.local.LocalRepository
import com.example.currencylist.data.repository.remote.ApiService
import com.example.currencylist.data.repository.remote.IRemoteRepository
import com.example.currencylist.data.repository.remote.RemoteRepository
import com.example.currencylist.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    @FragmentScope
    fun provideRemoteRepository(
        apiService: ApiService,
        localRepo: ILocalRepository
    ): IRemoteRepository = RemoteRepository(apiService, localRepo)

    @Provides
    @FragmentScope
    fun provideLocalRepo(dao: RateDao): ILocalRepository = LocalRepository(dao)
}