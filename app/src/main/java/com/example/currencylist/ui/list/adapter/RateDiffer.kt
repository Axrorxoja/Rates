package com.example.currencylist.ui.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.currencylist.data.db.RateItem
import timber.log.Timber

class RateDiffer : DiffUtil.ItemCallback<RateItem>() {

    override fun areItemsTheSame(
        oldItem: RateItem,
        newItem: RateItem
    ) = oldItem.code == newItem.code

    override fun areContentsTheSame(
        oldItem: RateItem,
        newItem: RateItem
    ) = areItemsTheSame(oldItem, newItem) && oldItem == newItem

    override fun getChangePayload(oldItem: RateItem, newItem: RateItem): Long {
        return if (oldItem.amount != newItem.amount || oldItem.rate != newItem.rate)
            newItem.amount * newItem.rate
        else 1
    }
}