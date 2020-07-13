package com.example.currencylist.ui.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.currencylist.R
import com.example.currencylist.common.lazyFast
import com.example.currencylist.presentation.IRateViewModel
import com.example.currencylist.presentation.RateViewModel
import com.example.currencylist.presentation.RateViewModelFactory
import com.example.currencylist.ui.list.adapter.ItemState
import com.example.currencylist.ui.list.adapter.RateAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_first.*
import javax.inject.Inject

class ListFragment : Fragment(R.layout.fragment_first) {

    private val rateAdapter by lazyFast { RateAdapter() }

    @Inject
    lateinit var vmFactory: RateViewModelFactory
    private val vm: IRateViewModel by viewModels<RateViewModel> { vmFactory }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
    }

    private fun setupList() {
        rv_rates.adapter = rateAdapter
        vm.liveRates.observe(viewLifecycleOwner, Observer { rateAdapter.submitList(it) })

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