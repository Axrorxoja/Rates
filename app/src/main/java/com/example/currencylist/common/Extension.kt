package com.example.currencylist.common

import android.text.TextWatcher
import android.widget.EditText
import com.example.currencylist.data.db.RateItem

fun <T> lazyFast(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)

val EditText.value
    get() = text.toString()

fun EditText.setTextQuietly(text: String, watcher: TextWatcher?) {
    removeTextChangedListener(watcher)
    setText(text)
    addTextChangedListener(watcher)
}

fun RateItem.loadFlagByCode() = "https://www.countryflags.io/${code.substring(0..1)}/flat/64.png"