package com.example.currencylist.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Rate(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val code: String,
    val amount: Float,
    val value: Float
)