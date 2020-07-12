package com.example.currencylist.ui.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencylist.R
import com.example.currencylist.common.lazyFast
import com.example.currencylist.data.models.BaseRate
import com.example.currencylist.ui.list.adapter.ItemState
import com.example.currencylist.ui.list.adapter.RateAdapter
import kotlinx.android.synthetic.main.fragment_first.*

class ListFragment : Fragment(R.layout.fragment_first) {

    private val rateAdapter by lazyFast { RateAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    private fun setupList() {
        rv_rates.adapter = rateAdapter

        rateAdapter
            .liveLastHoldItemPosition
            .observe(
                viewLifecycleOwner,
                Observer(this::onHoldItemPosition)
            )
    }

    private fun onHoldItemPosition(it: ItemState?) {
        if (it == null) return
        when (it) {
            is ItemState.PrimaryItemChanged -> {
            }
            is ItemState.PrimaryItemValueChanged -> {
            }
            is ItemState.ValueChanged -> {
            }
        }
    }
}