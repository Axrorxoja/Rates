package com.example.currencylist.ui.list.adapter

import android.annotation.SuppressLint
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.currencylist.R
import com.example.currencylist.common.setTextQuietly
import com.example.currencylist.common.simple.SimpleTextWatcher
import com.example.currencylist.common.value
import com.example.currencylist.data.db.RateItem
import com.example.currencylist.data.repository.CountryDataProvider
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rate.*
import timber.log.Timber
import kotlin.math.roundToInt


class RateVH(
    override val containerView: View,
    private val liveLastHoldItemPosition: MutableLiveData<ItemState>,
    private val watcherMap: HashMap<AppCompatEditText, TextWatcher>
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private lateinit var item: RateItem

    init {
        Timber.d("init ")
        setUpLongClick()
        setUpTextWatcher()
        setUpTouchListener()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setUpTouchListener() {
        et_value.setOnTouchListener { _, event ->
            if (event.action == ACTION_DOWN) {
                moveToTop()
            } else false
        }
    }

    private fun setUpTextWatcher() {
        val watcher = SimpleTextWatcher {
            val newAmount: Int = (et_value.value.toFloatOrNull() ?: 0F / item.rate).roundToInt()
            val state = ItemState.AmountChanged(newAmount)
            Timber.d("setUpWatcher $adapterPosition $newAmount")
            liveLastHoldItemPosition.value = state
        }
        et_value.addTextChangedListener(watcher)
        watcherMap[et_value] = watcher
    }

    private fun setUpLongClick() {
        containerView.setOnLongClickListener { moveToTop() }
    }

    private fun moveToTop(): Boolean {
        val state = ItemState.PrimaryItemChanged(item)
        Timber.d("setUpLongClick $adapterPosition $state")
        liveLastHoldItemPosition.value = state
        return true
    }

    @SuppressLint("SetTextI18n")
    fun onBind(item: RateItem) {
        this.item = item
        tv_code.text = item.code
        tv_flag.text = CountryDataProvider.flagMap[item.code]
        Timber.d("onBind $item")
        val value = item.rate * item.amount
        setValue(value)
    }

    @SuppressLint("SetTextI18n")
    fun onBind(item: RateItem, value: Long) {
        Timber.d("onBind payload $item")
        this.item = item
        setValue(value)
    }

    private fun setValue(value: Long) {
        val watcher = watcherMap[et_value]
        val number: Number = if (adapterPosition != 0) {
            value / 100F
        } else {
            value
        }
        et_value.setTextQuietly("" + number, watcher)

    }

    companion object {
        fun create(
            parent: ViewGroup,
            liveLastHoldItemPosition: MutableLiveData<ItemState>,
            watcherMap: HashMap<AppCompatEditText, TextWatcher>
        ): RateVH {
            Timber.d("create VH")
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
