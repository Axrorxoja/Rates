package com.example.currencylist.ui.list.adapter

import android.text.TextWatcher
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import com.example.currencylist.data.db.RateItem
import timber.log.Timber

class RateAdapter : ListAdapter<RateItem, RateVH>(RateDiffer()) {

    private val _liveLastHoldItemPosition = MutableLiveData<ItemState>()
    val liveLastHoldItemPosition: LiveData<ItemState>
        get() = _liveLastHoldItemPosition
    private val watcherMap = hashMapOf<AppCompatEditText, TextWatcher>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RateVH.create(
        parent,
        _liveLastHoldItemPosition,
        watcherMap
    )

    override fun onBindViewHolder(
        holder: RateVH,
        position: Int
    ) = holder.onBind(getItem(position))

    override fun onBindViewHolder(holder: RateVH, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
        if (payloads.isNotEmpty()) {
            val firstItem = payloads[0]
            if (firstItem is Long) {
                holder.onBind(getItem(position), firstItem)
            }
        } else {
            holder.onBind(getItem(position))
        }
    }
}