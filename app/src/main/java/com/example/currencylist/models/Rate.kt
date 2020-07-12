package com.example.currencylist.models

class BaseRate(
    val baseCurrency: String,
    val rates: List<RateItem>
) {
    companion object {
        fun generateMockRates() =
            List(20) {
                RateItem("USD", 1.5F)
            }
    }
}

data class RateItem(
    val code: String,
    val amount: Float
)