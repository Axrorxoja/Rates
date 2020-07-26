package com.example.currencylist.common

import android.util.Log
import timber.log.Timber

class ProductionTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        //report crash to server
    }

}