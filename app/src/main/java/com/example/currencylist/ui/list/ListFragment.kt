package com.example.currencylist.ui.list

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencylist.R
import com.example.currencylist.common.lazyFast
import com.example.currencylist.presentation.IRateViewModel
import com.example.currencylist.presentation.RateViewModel
import com.example.currencylist.presentation.RateViewModelFactory
import com.example.currencylist.ui.list.adapter.ItemState
import com.example.currencylist.ui.list.adapter.RateAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_first.*
import timber.log.Timber
import javax.inject.Inject

class ListFragment : Fragment(R.layout.fragment_first) {

    private val rateAdapter by lazyFast { RateAdapter() }

    @Inject
    lateinit var vmFactory: RateViewModelFactory
    private val vm: IRateViewModel by viewModels<RateViewModel> { vmFactory }
    private val handler = Handler()

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
        val lm = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_rates.layoutManager = lm
        vm.liveRates.observe(viewLifecycleOwner,
            Observer {
                Timber.d("vm.liveRates.observe $it")
                rateAdapter.submitList(it)
                handler.postDelayed({ lm.scrollToPosition(0) }, 500)
            })

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
            is ItemState.PrimaryItemChanged -> vm.setAsPrimaryRate(it.rate)
            is ItemState.PrimaryItemAmountChanged -> vm.primaryItemAmountChanged(it.newAmount)
            is ItemState.AmountChanged -> vm.amountChanged(it.newAmount,it.position)
        }
    }
}