package com.example.currencylist.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.currencylist.R
import com.example.currencylist.models.RateItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_rate.*

class RateAdapter : ListAdapter<RateItem, RateAdapter.RateVH>(RateDiffer()) {

    private val _liveLastHoldItemPosition = MutableLiveData<Int>()
    val liveLastHoldItemPosition: LiveData<Int>
        get() = _liveLastHoldItemPosition

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = RateVH.create(parent, _liveLastHoldItemPosition)

    override fun onBindViewHolder(
        holder: RateVH,
        position: Int
    ) = holder.onBind(getItem(position))

    class RateVH(
        override val containerView: View,
        liveLastHoldItemPosition: MutableLiveData<Int>
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnLongClickListener {
                liveLastHoldItemPosition.value = adapterPosition
                true
            }
        }

        @SuppressLint("SetTextI18n")
        fun onBind(item: RateItem) {
            et_amount.setText("" + item.amount)
        }

        companion object {
            fun create(
                parent: ViewGroup,
                liveLastHoldItemPosition: MutableLiveData<Int>
            ): RateVH {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_rate, parent, false)
                return RateVH(view, liveLastHoldItemPosition)
            }
        }
    }
}