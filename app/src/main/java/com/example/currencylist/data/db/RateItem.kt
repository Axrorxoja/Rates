package com.example.currencylist.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
data class RateItem(
    @PrimaryKey
    val code: String,
    val rate: Long,
    var amount: Int = 1,
    var modifiedTime: Long = 0
)