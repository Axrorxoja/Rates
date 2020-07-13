package com.example.currencylist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencylist.data.remote.ApiService
import com.example.currencylist.di.scope.FragmentScope
import javax.inject.Inject

@FragmentScope
class RateViewModelFactory @Inject constructor(
    private val apiService: ApiService
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RateViewModel(apiService) as T
    }

}