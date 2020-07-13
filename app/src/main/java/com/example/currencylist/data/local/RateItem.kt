package com.example.currencylist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
data class RateItem(
    @PrimaryKey
    val code: String,
    val rate: Float,
    var amount: Float = 1F,
    var modifiedTime: Long = 0
)