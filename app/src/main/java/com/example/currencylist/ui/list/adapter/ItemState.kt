package com.example.currencylist.ui.list.adapter

import com.example.currencylist.data.local.RateItem

sealed class ItemState {

    class PrimaryItemChanged(val rate: RateItem) : ItemState()
    class AmountChanged(val position: Int, val newAmount: Float) : ItemState()
    class PrimaryItemAmountChanged(val newAmount: Float) : ItemState()
}