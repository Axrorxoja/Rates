package com.example.currencylist.ui.list

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.currencylist.R
import com.example.currencylist.common.Constant.DEFAULT_SCROLL_DELAY
import com.example.currencylist.common.lazyFast
import com.example.currencylist.presentation.IRateViewModel
import com.example.currencylist.presentation.RateViewModel
import com.example.currencylist.presentation.RateViewModelFactory
import com.example.currencylist.ui.list.adapter.ItemState
import com.example.currencylist.ui.list.adapter.RateAdapter
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
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

        lifecycleScope.launch {
            vm.flowRates.collect {
                Timber.d("vm.flowRates $it")
                rateAdapter.submitList(it)
            }
        }
        vm.liveData.observe(viewLifecycleOwner, Observer {
            progress.visibility = View.GONE
            rv_rates.visibility = View.VISIBLE
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
            is ItemState.PrimaryItemChanged -> vm.setAsPrimary(it.rate)
            is ItemState.AmountChanged -> vm.amountChanged(it.newAmount)
        }
    }
}