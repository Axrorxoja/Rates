package com.example.currencylist.common

import android.widget.EditText

fun <T> lazyFast(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

val EditText.value
    get() = text.toString()

fun String.toFloatOrZero() = toFloatOrNull() ?: 0F