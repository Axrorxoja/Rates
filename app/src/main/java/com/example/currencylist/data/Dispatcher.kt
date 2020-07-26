package com.example.currencylist.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface IDispatcher {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val immediate: CoroutineDispatcher
}

class DefaultDispatcher : IDispatcher {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val immediate = Dispatchers.Main.immediate
}

class TestDispatcher : IDispatcher {
    override val io = Dispatchers.Unconfined
    override val main = Dispatchers.Unconfined
    override val immediate = Dispatchers.Unconfined
}

