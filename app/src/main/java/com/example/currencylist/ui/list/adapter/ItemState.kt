package com.example.currencylist.ui.list.adapter

sealed class ItemState {

    class PrimaryItemChanged(val position: Int) : ItemState()
    class ValueChanged(val position: Int, val newValue: Float) : ItemState()
    class PrimaryItemValueChanged(val newValue: Float) : ItemState()
}