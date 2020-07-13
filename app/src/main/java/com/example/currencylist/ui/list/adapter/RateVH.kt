package com.example.currencylist.ui.list.adapter

import android.annotation.SuppressLint
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.currencylist.R
import com.example.currencylist.common.setTextQuietly
import com.example.currencylist.common.simple.SimpleTextWatcher
import com.example.currencylist.common.toFloatOrZero
import com.example.currencylist.common.value
import com.example.currencylist.data.local.RateItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rate.*
import timber.log.Timber

class RateVH(
    override val containerView: View,
    private val liveLastHoldItemPosition: MutableLiveData<ItemState>,
    private val watcherMap: HashMap<AppCompatEditText, TextWatcher>
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private lateinit var item: RateItem

    init {
        setUpLongClick()
        setUpWatcher()
    }

    private fun setUpWatcher() {
        val watcher = SimpleTextWatcher {
            val newAmount = et_value.value.toFloatOrZero() / item.rate
            val state = if (adapterPosition == 0) ItemState.PrimaryItemAmountChanged(
                newAmount
            )
            else ItemState.AmountChanged(
                adapterPosition,
                newAmount
            )
            Timber.d("setUpWatcher $adapterPosition $newAmount")
            liveLastHoldItemPosition.value = state
        }
        et_value.addTextChangedListener(watcher)
        watcherMap[et_value] = watcher
    }

    private fun setUpLongClick() {
        containerView.setOnLongClickListener {
            val state = ItemState.PrimaryItemChanged(item)
            Timber.d("setUpLongClick $adapterPosition $state")
            liveLastHoldItemPosition.value = state
            true
        }
    }

    @SuppressLint("SetTextI18n")
    fun onBind(item: RateItem) {
        this.item = item
        Timber.d("onBind $item")
        val watcher = watcherMap[et_value]
        et_value.setTextQuietly("" + item.rate * item.amount, watcher)
    }

    companion object {
        fun create(
            parent: ViewGroup,
            liveLastHoldItemPosition: MutableLiveData<ItemState>,
            watcherMap: HashMap<AppCompatEditText, TextWatcher>
        ): RateVH {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_rate, parent, false)
            return RateVH(
                view,
                liveLastHoldItemPosition,
                watcherMap
            )
        }
    }
}
