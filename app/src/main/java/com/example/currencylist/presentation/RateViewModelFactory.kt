package com.example.currencylist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencylist.data.db.RateDao
import com.example.currencylist.data.repository.local.ILocalRepository
import com.example.currencylist.data.repository.remote.ApiService
import com.example.currencylist.data.repository.remote.IRemoteRepository
import com.example.currencylist.di.scope.FragmentScope
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@FragmentScope
class RateViewModelFactory @Inject constructor(
    private val remoteRepo:IRemoteRepository,
    private val localRepo:ILocalRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RateViewModel(remoteRepo,localRepo) as T
    }

}