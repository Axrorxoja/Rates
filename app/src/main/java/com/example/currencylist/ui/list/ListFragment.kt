package com.example.currencylist.ui.list

import android.os.Bundle
import android.provider.Telephony
import android.view.View
import androidx.fragment.app.Fragment
import com.example.currencylist.R
import com.example.currencylist.models.BaseRate
import com.example.currencylist.models.RateItem
import kotlinx.android.synthetic.main.fragment_first.*

class ListFragment : Fragment(R.layout.fragment_first) {

    private val rateAdapter by lazy(LazyThreadSafetyMode.NONE) { RateAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_rates.adapter = rateAdapter
        val mockData = BaseRate.generateMockRates()
        rateAdapter.submitList(mockData)
    }

}