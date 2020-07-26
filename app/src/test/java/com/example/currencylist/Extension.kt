package com.example.currencylist

import androidx.lifecycle.*
import timber.log.Timber
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <T> LiveData<T>.getOrAwaitValue(time: Long = 2, timeUnit: TimeUnit = TimeUnit.SECONDS): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            Timber.d("onChanged $t")
            data = t
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    if (!latch.await(time, timeUnit)) {
        return data
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

fun ViewModel.callOnCleared() {
    val viewModelStore = ViewModelStore()
    val viewModelProvider = ViewModelProvider(viewModelStore, object : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = this@callOnCleared as T
    })
    viewModelProvider.get(this@callOnCleared::class.java)

    //Run 2
    viewModelStore.clear()//To call clear() in ViewModel
}