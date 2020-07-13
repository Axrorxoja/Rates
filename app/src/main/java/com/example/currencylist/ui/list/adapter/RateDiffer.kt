package com.example.currencylist.ui.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.currencylist.data.local.RateItem


class RateDiffer : DiffUtil.ItemCallback<RateItem>() {
    override fun areItemsTheSame(
        oldItem: RateItem,
        newItem: RateItem
    ) = oldItem.code == newItem.code

    override fun areContentsTheSame(
        oldItem: RateItem,
        newItem: RateItem
    ) = oldItem == newItem
}