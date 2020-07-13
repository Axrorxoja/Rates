package com.example.currencylist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
data class RateItem(
    val code: String,
    val rate: Float,
    val amount: Float = 0F,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)