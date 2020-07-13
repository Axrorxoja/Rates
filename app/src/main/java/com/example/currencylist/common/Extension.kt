package com.example.currencylist.common

import android.text.TextWatcher
import android.widget.EditText

fun <T> lazyFast(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

val EditText.value
    get() = text.toString()

fun EditText.setTextQuietly(text: String, watcher: TextWatcher?) {
    removeTextChangedListener(watcher)
    setText(text)
    if (text.isNotEmpty()) setSelection(text.length)
    addTextChangedListener(watcher)
}

fun String.toFloatOrZero() = toFloatOrNull() ?: 0F