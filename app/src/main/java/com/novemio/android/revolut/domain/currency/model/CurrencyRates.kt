package com.novemio.android.revolut.domain.currency.model

/**
 * Created by novemio on 2/23/20.
 */

data class CurrencyRates(val baseCurrency:String, val rates:List<Rate>)
