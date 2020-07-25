package com.example.currencylist.ui.list.adapter

import com.example.currencylist.data.db.RateItem

sealed class ItemState {
    class PrimaryItemChanged(val rate: RateItem) : ItemState()
    class AmountChanged(val newAmount: Int) : ItemState()
}