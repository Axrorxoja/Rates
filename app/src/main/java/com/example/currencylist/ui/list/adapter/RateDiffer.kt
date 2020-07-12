package com.example.currencylist.ui.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.currencylist.data.local.Rate


class RateDiffer : DiffUtil.ItemCallback<Rate>() {
    override fun areItemsTheSame(
        oldItem: Rate,
        newItem: Rate
    ) = oldItem.code == newItem.code

    override fun areContentsTheSame(
        oldItem: Rate,
        newItem: Rate
    ) = oldItem == newItem
}